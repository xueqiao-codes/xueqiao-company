/**
 * Autogenerated by Thrift Compiler (0.9.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.longsheng.xueqiao.common.thriftapi;

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

public class SledTopic implements org.apache.thrift.TBase<SledTopic, SledTopic._Fields>, java.io.Serializable, Cloneable, Comparable<SledTopic> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("SledTopic");

  private static final org.apache.thrift.protocol.TField SLED_CONTRACT_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("sledContractId", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField PLATFORM_FIELD_DESC = new org.apache.thrift.protocol.TField("platform", org.apache.thrift.protocol.TType.I32, (short)2);
  private static final org.apache.thrift.protocol.TField PLATFORM_ENV_FIELD_DESC = new org.apache.thrift.protocol.TField("platformEnv", org.apache.thrift.protocol.TType.I32, (short)3);
  private static final org.apache.thrift.protocol.TField CONTRACT_CODE_FIELD_DESC = new org.apache.thrift.protocol.TField("contractCode", org.apache.thrift.protocol.TType.STRING, (short)4);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new SledTopicStandardSchemeFactory());
    schemes.put(TupleScheme.class, new SledTopicTupleSchemeFactory());
  }

  public int sledContractId; // optional
  /**
   * 
   * @see com.longsheng.xueqiao.standard.thriftapi.Platform
   */
  public com.longsheng.xueqiao.standard.thriftapi.Platform platform; // optional
  /**
   * 
   * @see com.longsheng.xueqiao.standard.thriftapi.PlatformEnv
   */
  public com.longsheng.xueqiao.standard.thriftapi.PlatformEnv platformEnv; // optional
  public String contractCode; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    SLED_CONTRACT_ID((short)1, "sledContractId"),
    /**
     * 
     * @see com.longsheng.xueqiao.standard.thriftapi.Platform
     */
    PLATFORM((short)2, "platform"),
    /**
     * 
     * @see com.longsheng.xueqiao.standard.thriftapi.PlatformEnv
     */
    PLATFORM_ENV((short)3, "platformEnv"),
    CONTRACT_CODE((short)4, "contractCode");

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
        case 1: // SLED_CONTRACT_ID
          return SLED_CONTRACT_ID;
        case 2: // PLATFORM
          return PLATFORM;
        case 3: // PLATFORM_ENV
          return PLATFORM_ENV;
        case 4: // CONTRACT_CODE
          return CONTRACT_CODE;
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
  private static final int __SLEDCONTRACTID_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  private _Fields optionals[] = {_Fields.SLED_CONTRACT_ID,_Fields.PLATFORM,_Fields.PLATFORM_ENV,_Fields.CONTRACT_CODE};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.SLED_CONTRACT_ID, new org.apache.thrift.meta_data.FieldMetaData("sledContractId", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.PLATFORM, new org.apache.thrift.meta_data.FieldMetaData("platform", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, com.longsheng.xueqiao.standard.thriftapi.Platform.class)));
    tmpMap.put(_Fields.PLATFORM_ENV, new org.apache.thrift.meta_data.FieldMetaData("platformEnv", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, com.longsheng.xueqiao.standard.thriftapi.PlatformEnv.class)));
    tmpMap.put(_Fields.CONTRACT_CODE, new org.apache.thrift.meta_data.FieldMetaData("contractCode", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(SledTopic.class, metaDataMap);
  }

  public SledTopic() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public SledTopic(SledTopic other) {
    __isset_bitfield = other.__isset_bitfield;
    this.sledContractId = other.sledContractId;
    if (other.isSetPlatform()) {
      this.platform = other.platform;
    }
    if (other.isSetPlatformEnv()) {
      this.platformEnv = other.platformEnv;
    }
    if (other.isSetContractCode()) {
      this.contractCode = other.contractCode;
    }
  }

  public SledTopic deepCopy() {
    return new SledTopic(this);
  }

  @Override
  public void clear() {
    setSledContractIdIsSet(false);
    this.sledContractId = 0;
    this.platform = null;
    this.platformEnv = null;
    this.contractCode = null;
  }

  public int getSledContractId() {
    return this.sledContractId;
  }

  public SledTopic setSledContractId(int sledContractId) {
    this.sledContractId = sledContractId;
    setSledContractIdIsSet(true);
    return this;
  }

  public void unsetSledContractId() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __SLEDCONTRACTID_ISSET_ID);
  }

  /** Returns true if field sledContractId is set (has been assigned a value) and false otherwise */
  public boolean isSetSledContractId() {
    return EncodingUtils.testBit(__isset_bitfield, __SLEDCONTRACTID_ISSET_ID);
  }

  public void setSledContractIdIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __SLEDCONTRACTID_ISSET_ID, value);
  }

  /**
   * 
   * @see com.longsheng.xueqiao.standard.thriftapi.Platform
   */
  public com.longsheng.xueqiao.standard.thriftapi.Platform getPlatform() {
    return this.platform;
  }

  /**
   * 
   * @see com.longsheng.xueqiao.standard.thriftapi.Platform
   */
  public SledTopic setPlatform(com.longsheng.xueqiao.standard.thriftapi.Platform platform) {
    this.platform = platform;
    return this;
  }

  public void unsetPlatform() {
    this.platform = null;
  }

  /** Returns true if field platform is set (has been assigned a value) and false otherwise */
  public boolean isSetPlatform() {
    return this.platform != null;
  }

  public void setPlatformIsSet(boolean value) {
    if (!value) {
      this.platform = null;
    }
  }

  /**
   * 
   * @see com.longsheng.xueqiao.standard.thriftapi.PlatformEnv
   */
  public com.longsheng.xueqiao.standard.thriftapi.PlatformEnv getPlatformEnv() {
    return this.platformEnv;
  }

  /**
   * 
   * @see com.longsheng.xueqiao.standard.thriftapi.PlatformEnv
   */
  public SledTopic setPlatformEnv(com.longsheng.xueqiao.standard.thriftapi.PlatformEnv platformEnv) {
    this.platformEnv = platformEnv;
    return this;
  }

  public void unsetPlatformEnv() {
    this.platformEnv = null;
  }

  /** Returns true if field platformEnv is set (has been assigned a value) and false otherwise */
  public boolean isSetPlatformEnv() {
    return this.platformEnv != null;
  }

  public void setPlatformEnvIsSet(boolean value) {
    if (!value) {
      this.platformEnv = null;
    }
  }

  public String getContractCode() {
    return this.contractCode;
  }

  public SledTopic setContractCode(String contractCode) {
    this.contractCode = contractCode;
    return this;
  }

  public void unsetContractCode() {
    this.contractCode = null;
  }

  /** Returns true if field contractCode is set (has been assigned a value) and false otherwise */
  public boolean isSetContractCode() {
    return this.contractCode != null;
  }

  public void setContractCodeIsSet(boolean value) {
    if (!value) {
      this.contractCode = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case SLED_CONTRACT_ID:
      if (value == null) {
        unsetSledContractId();
      } else {
        setSledContractId((Integer)value);
      }
      break;

    case PLATFORM:
      if (value == null) {
        unsetPlatform();
      } else {
        setPlatform((com.longsheng.xueqiao.standard.thriftapi.Platform)value);
      }
      break;

    case PLATFORM_ENV:
      if (value == null) {
        unsetPlatformEnv();
      } else {
        setPlatformEnv((com.longsheng.xueqiao.standard.thriftapi.PlatformEnv)value);
      }
      break;

    case CONTRACT_CODE:
      if (value == null) {
        unsetContractCode();
      } else {
        setContractCode((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case SLED_CONTRACT_ID:
      return Integer.valueOf(getSledContractId());

    case PLATFORM:
      return getPlatform();

    case PLATFORM_ENV:
      return getPlatformEnv();

    case CONTRACT_CODE:
      return getContractCode();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case SLED_CONTRACT_ID:
      return isSetSledContractId();
    case PLATFORM:
      return isSetPlatform();
    case PLATFORM_ENV:
      return isSetPlatformEnv();
    case CONTRACT_CODE:
      return isSetContractCode();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof SledTopic)
      return this.equals((SledTopic)that);
    return false;
  }

  public boolean equals(SledTopic that) {
    if (that == null)
      return false;

    boolean this_present_sledContractId = true && this.isSetSledContractId();
    boolean that_present_sledContractId = true && that.isSetSledContractId();
    if (this_present_sledContractId || that_present_sledContractId) {
      if (!(this_present_sledContractId && that_present_sledContractId))
        return false;
      if (this.sledContractId != that.sledContractId)
        return false;
    }

    boolean this_present_platform = true && this.isSetPlatform();
    boolean that_present_platform = true && that.isSetPlatform();
    if (this_present_platform || that_present_platform) {
      if (!(this_present_platform && that_present_platform))
        return false;
      if (!this.platform.equals(that.platform))
        return false;
    }

    boolean this_present_platformEnv = true && this.isSetPlatformEnv();
    boolean that_present_platformEnv = true && that.isSetPlatformEnv();
    if (this_present_platformEnv || that_present_platformEnv) {
      if (!(this_present_platformEnv && that_present_platformEnv))
        return false;
      if (!this.platformEnv.equals(that.platformEnv))
        return false;
    }

    boolean this_present_contractCode = true && this.isSetContractCode();
    boolean that_present_contractCode = true && that.isSetContractCode();
    if (this_present_contractCode || that_present_contractCode) {
      if (!(this_present_contractCode && that_present_contractCode))
        return false;
      if (!this.contractCode.equals(that.contractCode))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  @Override
  public int compareTo(SledTopic other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetSledContractId()).compareTo(other.isSetSledContractId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSledContractId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.sledContractId, other.sledContractId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetPlatform()).compareTo(other.isSetPlatform());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPlatform()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.platform, other.platform);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetPlatformEnv()).compareTo(other.isSetPlatformEnv());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPlatformEnv()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.platformEnv, other.platformEnv);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetContractCode()).compareTo(other.isSetContractCode());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetContractCode()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.contractCode, other.contractCode);
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
    StringBuilder sb = new StringBuilder("SledTopic(");
    boolean first = true;

    if (isSetSledContractId()) {
      sb.append("sledContractId:");
      sb.append(this.sledContractId);
      first = false;
    }
    if (isSetPlatform()) {
      if (!first) sb.append(", ");
      sb.append("platform:");
      if (this.platform == null) {
        sb.append("null");
      } else {
        sb.append(this.platform);
      }
      first = false;
    }
    if (isSetPlatformEnv()) {
      if (!first) sb.append(", ");
      sb.append("platformEnv:");
      if (this.platformEnv == null) {
        sb.append("null");
      } else {
        sb.append(this.platformEnv);
      }
      first = false;
    }
    if (isSetContractCode()) {
      if (!first) sb.append(", ");
      sb.append("contractCode:");
      if (this.contractCode == null) {
        sb.append("null");
      } else {
        sb.append(this.contractCode);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
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
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class SledTopicStandardSchemeFactory implements SchemeFactory {
    public SledTopicStandardScheme getScheme() {
      return new SledTopicStandardScheme();
    }
  }

  private static class SledTopicStandardScheme extends StandardScheme<SledTopic> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, SledTopic struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // SLED_CONTRACT_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.sledContractId = iprot.readI32();
              struct.setSledContractIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // PLATFORM
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.platform = com.longsheng.xueqiao.standard.thriftapi.Platform.findByValue(iprot.readI32());
              struct.setPlatformIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // PLATFORM_ENV
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.platformEnv = com.longsheng.xueqiao.standard.thriftapi.PlatformEnv.findByValue(iprot.readI32());
              struct.setPlatformEnvIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // CONTRACT_CODE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.contractCode = iprot.readString();
              struct.setContractCodeIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, SledTopic struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.isSetSledContractId()) {
        oprot.writeFieldBegin(SLED_CONTRACT_ID_FIELD_DESC);
        oprot.writeI32(struct.sledContractId);
        oprot.writeFieldEnd();
      }
      if (struct.platform != null) {
        if (struct.isSetPlatform()) {
          oprot.writeFieldBegin(PLATFORM_FIELD_DESC);
          oprot.writeI32(struct.platform.getValue());
          oprot.writeFieldEnd();
        }
      }
      if (struct.platformEnv != null) {
        if (struct.isSetPlatformEnv()) {
          oprot.writeFieldBegin(PLATFORM_ENV_FIELD_DESC);
          oprot.writeI32(struct.platformEnv.getValue());
          oprot.writeFieldEnd();
        }
      }
      if (struct.contractCode != null) {
        if (struct.isSetContractCode()) {
          oprot.writeFieldBegin(CONTRACT_CODE_FIELD_DESC);
          oprot.writeString(struct.contractCode);
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class SledTopicTupleSchemeFactory implements SchemeFactory {
    public SledTopicTupleScheme getScheme() {
      return new SledTopicTupleScheme();
    }
  }

  private static class SledTopicTupleScheme extends TupleScheme<SledTopic> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, SledTopic struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetSledContractId()) {
        optionals.set(0);
      }
      if (struct.isSetPlatform()) {
        optionals.set(1);
      }
      if (struct.isSetPlatformEnv()) {
        optionals.set(2);
      }
      if (struct.isSetContractCode()) {
        optionals.set(3);
      }
      oprot.writeBitSet(optionals, 4);
      if (struct.isSetSledContractId()) {
        oprot.writeI32(struct.sledContractId);
      }
      if (struct.isSetPlatform()) {
        oprot.writeI32(struct.platform.getValue());
      }
      if (struct.isSetPlatformEnv()) {
        oprot.writeI32(struct.platformEnv.getValue());
      }
      if (struct.isSetContractCode()) {
        oprot.writeString(struct.contractCode);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, SledTopic struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(4);
      if (incoming.get(0)) {
        struct.sledContractId = iprot.readI32();
        struct.setSledContractIdIsSet(true);
      }
      if (incoming.get(1)) {
        struct.platform = com.longsheng.xueqiao.standard.thriftapi.Platform.findByValue(iprot.readI32());
        struct.setPlatformIsSet(true);
      }
      if (incoming.get(2)) {
        struct.platformEnv = com.longsheng.xueqiao.standard.thriftapi.PlatformEnv.findByValue(iprot.readI32());
        struct.setPlatformEnvIsSet(true);
      }
      if (incoming.get(3)) {
        struct.contractCode = iprot.readString();
        struct.setContractCodeIsSet(true);
      }
    }
  }

}

