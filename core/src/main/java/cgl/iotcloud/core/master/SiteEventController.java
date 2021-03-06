package cgl.iotcloud.core.master;

import cgl.iotcloud.core.Configuration;
import cgl.iotcloud.core.api.thrift.TSensor;
import cgl.iotcloud.core.api.thrift.TSensorState;
import cgl.iotcloud.core.api.thrift.TSite;
import cgl.iotcloud.core.master.events.MSensorSiteEvent;
import cgl.iotcloud.core.master.events.MSiteEvent;
import cgl.iotcloud.core.sensorsite.SensorState;
import cgl.iotcloud.core.zk.SensorUpdater;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SiteEventController {
    private Logger LOG = LoggerFactory.getLogger(SiteEventController.class);

    private MasterContext context;

    private HeartBeats heartBeats;

    private CuratorFramework curatorFramework;

    public SiteEventController(MasterContext context, EventBus siteEvents) {
        this.context = context;
        this.heartBeats = new HeartBeats(siteEvents);

        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        this.curatorFramework = CuratorFrameworkFactory.newClient(Configuration.getZkConnectionString(context.getConf()), retryPolicy);
    }

    public void start() {
        this.curatorFramework.start();
    }

    @Subscribe
    public void handleEvent(MSiteEvent event) {
        if (event.getState() == SiteState.DEACTIVATED) {
            deactivateSite(event);
        } else if (event.getState() == SiteState.ACTIVE) {
            activateSite(event);
        } else if (event.getState() == SiteState.ADDED) {
            addSite(event);
        } else if (event.getState() == SiteState.REMOVED) {
            deactivateSite(event);
        }
    }

    @Subscribe
    public void updateSensor(MSensorSiteEvent updateEvent) {
        if (updateEvent.getState() == SensorState.DEPLOY) {
            sensorAdded(updateEvent);
        } else if (updateEvent.getState() == SensorState.UN_DEPLOY) {
            sensorRemoved(updateEvent);
        } else if (updateEvent.getState() == SensorState.ACTIVATE) {
            TSensor sensorDescriptor = context.getSensor(updateEvent.getSite(), updateEvent.getId());
            sensorDescriptor.setState(TSensorState.ACTIVE);
        } else if (updateEvent.getState() == SensorState.DEACTIVATE) {
            TSensor sensorDescriptor = context.getSensor(updateEvent.getSite(), updateEvent.getId());
            sensorDescriptor.setState(TSensorState.DE_ACTIVATE);
        } else if (updateEvent.getState() == SensorState.UPDATE) {
            context.removeSensor(updateEvent.getSite(), updateEvent.getId());
            context.addSensor(updateEvent.getSite(), updateEvent.getSensor());
        } else {
            LOG.warn("Unrecognized event type {}", updateEvent.getState());
        }
    }

    private void sensorAdded(MSensorSiteEvent updateEvent) {
        context.addSensor(updateEvent.getSite(), updateEvent.getSensor());

        SensorUpdater.addSensor(curatorFramework, context, updateEvent.getSite(), updateEvent.getSensor());
    }

    private void sensorRemoved(MSensorSiteEvent updateEvent) {
        context.removeSensor(updateEvent.getSite(), updateEvent.getId());

        SensorUpdater.markSensorForDeletion(curatorFramework, context, updateEvent.getSite(), updateEvent.getSensor());
    }

    private void addSite(MSiteEvent event) {
        TSite site = event.getSite();

        String id = site.getSiteId();
        String host = site.getHost();
        int port = site.getPort();

        TSite descriptor = new TSite(id, port, host);
        descriptor.setMetadata(site.getMetadata());
        context.addSensorSite(descriptor);

        SensorUpdater.addSite(curatorFramework, Configuration.getZkRoot(context.getConf()), site);
        heartBeats.scheduleForSite(event.getSiteId(), descriptor.getHost(), descriptor.getPort());
        LOG.info("A new site added {} with host {} and port {}", event.getSiteId(), descriptor.getHost(), descriptor.getPort());
    }

    private void deactivateSite(MSiteEvent event) {
        // first get the sensors for this site
        for (TSensor sensor : context.getSensors(event.getSiteId())) {
            SensorUpdater.markSensorForDeletion(curatorFramework, context, event.getSiteId(), sensor);
        }

        context.makeSiteOffline(event.getSiteId());
        // stop the timers
        heartBeats.stopForSite(event.getSiteId());
        LOG.info("Deactivating the site {}", event.getSiteId());
    }

    private void activateSite(MSiteEvent event) {
        TSite descriptor = context.getSensorSite(event.getSiteId());
        heartBeats.scheduleForSite(event.getSiteId(), descriptor.getHost(), descriptor.getPort());

        LOG.info("Activating the site {}", event.getSiteId());
    }
}
