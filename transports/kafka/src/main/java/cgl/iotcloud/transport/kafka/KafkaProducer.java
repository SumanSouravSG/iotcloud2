package cgl.iotcloud.transport.kafka;

import cgl.iotcloud.core.api.thrift.TSensorMessage;
import cgl.iotcloud.core.msg.MessageContext;
import cgl.iotcloud.core.transport.Manageable;
import cgl.iotcloud.core.utils.SerializationUtils;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;

public class KafkaProducer implements Manageable {
    private static Logger LOG = LoggerFactory.getLogger(KafkaProducer.class);

    private Producer<byte[], byte []> producer;

    private BlockingQueue outQueue;

    private String topic;

    private String brokerList;

    private String serializerClass;

    private String partitionClass;

    private String requestRequiredAcks;

    private boolean run = true;

    private String siteId;

    public KafkaProducer(BlockingQueue outQueue,
                         String topic, String brokerList, String serializerClass,
                         String partitionClass, String requestRequiredAcks, String siteId) {
        this.outQueue = outQueue;
        this.topic = topic;
        this.brokerList = brokerList;
        this.serializerClass = serializerClass;
        this.partitionClass = partitionClass;
        this.requestRequiredAcks = requestRequiredAcks;
        this.siteId = siteId;
    }

    public void start() {
        Properties props = new Properties();
        props.put("metadata.broker.list", brokerList);
        if (serializerClass != null) {
            props.put("serializer.class", serializerClass);
        }
        if (partitionClass != null) {
            props.put("partitioner.class", partitionClass);
        }
        if (requestRequiredAcks != null) {
            props.put("request.required.acks", requestRequiredAcks);
        }
        ProducerConfig config = new ProducerConfig(props);
        producer = new Producer<byte[], byte []>(config);

        Thread t = new Thread(new Worker());
        t.start();
    }

    public void stop() {
        run = false;
    }

    private class Worker implements Runnable {
        @Override
        public void run() {
            int errorCount = 0;
            while (run) {
                try {
                    try {
                        Object input = outQueue.take();
                        if (input instanceof MessageContext) {
                            MessageContext messageContext = (MessageContext) input;
                            String sensorId = messageContext.getSensorId();

                            ByteBuffer buffer = ByteBuffer.wrap(messageContext.getBody());
                            TSensorMessage message = new TSensorMessage(sensorId, siteId, buffer);
                            for (Map.Entry<String, Object> e : messageContext.getProperties().entrySet()) {
                                message.putToProperties(e.getKey(), e.getValue().toString());
                            }

                            byte body[] = SerializationUtils.serializeThriftObject(message);
                            KeyedMessage<byte[], byte []> data = new KeyedMessage<byte[], byte []>(topic,
                                    sensorId.getBytes(), body);

                            producer.send(data);
                        } else {
                            LOG.error("Unexpected message type");
                        }
                    } catch (InterruptedException e) {
                        LOG.error("Exception occurred in the worker listening for consumer changes", e);
                    }
                } catch (Throwable t) {
                    errorCount++;
                    if (errorCount <= 3) {
                        LOG.error("Error occurred " + errorCount + " times.. trying to continue the worker", t);
                    } else {
                        LOG.error("Error occurred " + errorCount + " times.. terminating the worker", t);
                        run = false;
                    }
                }
            }
        }
    }
}
