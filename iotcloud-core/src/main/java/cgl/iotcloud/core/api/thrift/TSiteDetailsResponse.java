/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 * Autogenerated by Thrift Compiler (0.9.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package cgl.iotcloud.core.api.thrift;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("all") public class TSiteDetailsResponse implements org.apache.thrift.TBase<TSiteDetailsResponse, TSiteDetailsResponse._Fields>, java.io.Serializable, Cloneable, Comparable<TSiteDetailsResponse> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TSiteDetailsResponse");

  private static final org.apache.thrift.protocol.TField DETAILS_FIELD_DESC = new org.apache.thrift.protocol.TField("details", org.apache.thrift.protocol.TType.STRUCT, (short)1);
  private static final org.apache.thrift.protocol.TField STATE_FIELD_DESC = new org.apache.thrift.protocol.TField("state", org.apache.thrift.protocol.TType.I32, (short)2);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new TSiteDetailsResponseStandardSchemeFactory());
    schemes.put(TupleScheme.class, new TSiteDetailsResponseTupleSchemeFactory());
  }

  public TSiteDetails details; // optional
  /**
   * 
   * @see TResponseState
   */
  public TResponseState state; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  @SuppressWarnings("all") public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    DETAILS((short)1, "details"),
    /**
     * 
     * @see TResponseState
     */
    STATE((short)2, "state");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // DETAILS
          return DETAILS;
        case 2: // STATE
          return STATE;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private _Fields optionals[] = {_Fields.DETAILS};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.DETAILS, new org.apache.thrift.meta_data.FieldMetaData("details", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, TSiteDetails.class)));
    tmpMap.put(_Fields.STATE, new org.apache.thrift.meta_data.FieldMetaData("state", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, TResponseState.class)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TSiteDetailsResponse.class, metaDataMap);
  }

  public TSiteDetailsResponse() {
  }

  public TSiteDetailsResponse(
    TResponseState state)
  {
    this();
    this.state = state;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TSiteDetailsResponse(TSiteDetailsResponse other) {
    if (other.isSetDetails()) {
      this.details = new TSiteDetails(other.details);
    }
    if (other.isSetState()) {
      this.state = other.state;
    }
  }

  public TSiteDetailsResponse deepCopy() {
    return new TSiteDetailsResponse(this);
  }

  @Override
  public void clear() {
    this.details = null;
    this.state = null;
  }

  public TSiteDetails getDetails() {
    return this.details;
  }

  public TSiteDetailsResponse setDetails(TSiteDetails details) {
    this.details = details;
    return this;
  }

  public void unsetDetails() {
    this.details = null;
  }

  /** Returns true if field details is set (has been assigned a value) and false otherwise */
  public boolean isSetDetails() {
    return this.details != null;
  }

  public void setDetailsIsSet(boolean value) {
    if (!value) {
      this.details = null;
    }
  }

  /**
   * 
   * @see TResponseState
   */
  public TResponseState getState() {
    return this.state;
  }

  /**
   * 
   * @see TResponseState
   */
  public TSiteDetailsResponse setState(TResponseState state) {
    this.state = state;
    return this;
  }

  public void unsetState() {
    this.state = null;
  }

  /** Returns true if field state is set (has been assigned a value) and false otherwise */
  public boolean isSetState() {
    return this.state != null;
  }

  public void setStateIsSet(boolean value) {
    if (!value) {
      this.state = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case DETAILS:
      if (value == null) {
        unsetDetails();
      } else {
        setDetails((TSiteDetails)value);
      }
      break;

    case STATE:
      if (value == null) {
        unsetState();
      } else {
        setState((TResponseState)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case DETAILS:
      return getDetails();

    case STATE:
      return getState();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case DETAILS:
      return isSetDetails();
    case STATE:
      return isSetState();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof TSiteDetailsResponse)
      return this.equals((TSiteDetailsResponse)that);
    return false;
  }

  public boolean equals(TSiteDetailsResponse that) {
    if (that == null)
      return false;

    boolean this_present_details = true && this.isSetDetails();
    boolean that_present_details = true && that.isSetDetails();
    if (this_present_details || that_present_details) {
      if (!(this_present_details && that_present_details))
        return false;
      if (!this.details.equals(that.details))
        return false;
    }

    boolean this_present_state = true && this.isSetState();
    boolean that_present_state = true && that.isSetState();
    if (this_present_state || that_present_state) {
      if (!(this_present_state && that_present_state))
        return false;
      if (!this.state.equals(that.state))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  @Override
  public int compareTo(TSiteDetailsResponse other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetDetails()).compareTo(other.isSetDetails());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDetails()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.details, other.details);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetState()).compareTo(other.isSetState());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetState()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.state, other.state);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("TSiteDetailsResponse(");
    boolean first = true;

    if (isSetDetails()) {
      sb.append("details:");
      if (this.details == null) {
        sb.append("null");
      } else {
        sb.append(this.details);
      }
      first = false;
    }
    if (!first) sb.append(", ");
    sb.append("state:");
    if (this.state == null) {
      sb.append("null");
    } else {
      sb.append(this.state);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (state == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'state' was not present! Struct: " + toString());
    }
    // check for sub-struct validity
    if (details != null) {
      details.validate();
    }
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class TSiteDetailsResponseStandardSchemeFactory implements SchemeFactory {
    public TSiteDetailsResponseStandardScheme getScheme() {
      return new TSiteDetailsResponseStandardScheme();
    }
  }

  private static class TSiteDetailsResponseStandardScheme extends StandardScheme<TSiteDetailsResponse> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TSiteDetailsResponse struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // DETAILS
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.details = new TSiteDetails();
              struct.details.read(iprot);
              struct.setDetailsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // STATE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.state = TResponseState.findByValue(iprot.readI32());
              struct.setStateIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, TSiteDetailsResponse struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.details != null) {
        if (struct.isSetDetails()) {
          oprot.writeFieldBegin(DETAILS_FIELD_DESC);
          struct.details.write(oprot);
          oprot.writeFieldEnd();
        }
      }
      if (struct.state != null) {
        oprot.writeFieldBegin(STATE_FIELD_DESC);
        oprot.writeI32(struct.state.getValue());
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TSiteDetailsResponseTupleSchemeFactory implements SchemeFactory {
    public TSiteDetailsResponseTupleScheme getScheme() {
      return new TSiteDetailsResponseTupleScheme();
    }
  }

  private static class TSiteDetailsResponseTupleScheme extends TupleScheme<TSiteDetailsResponse> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TSiteDetailsResponse struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeI32(struct.state.getValue());
      BitSet optionals = new BitSet();
      if (struct.isSetDetails()) {
        optionals.set(0);
      }
      oprot.writeBitSet(optionals, 1);
      if (struct.isSetDetails()) {
        struct.details.write(oprot);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TSiteDetailsResponse struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.state = TResponseState.findByValue(iprot.readI32());
      struct.setStateIsSet(true);
      BitSet incoming = iprot.readBitSet(1);
      if (incoming.get(0)) {
        struct.details = new TSiteDetails();
        struct.details.read(iprot);
        struct.setDetailsIsSet(true);
      }
    }
  }

}

