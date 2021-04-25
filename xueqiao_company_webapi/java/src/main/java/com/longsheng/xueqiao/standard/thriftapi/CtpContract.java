/**
 * Autogenerated by Thrift Compiler (0.9.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.longsheng.xueqiao.standard.thriftapi;

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

public class CtpContract implements org.apache.thrift.TBase<CtpContract, CtpContract._Fields>, java.io.Serializable, Cloneable, Comparable<CtpContract> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("CtpContract");

  private static final org.apache.thrift.protocol.TField CTP_CONTRACT_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("ctpContractId", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField CTP_COMMODITY_FIELD_DESC = new org.apache.thrift.protocol.TField("ctpCommodity", org.apache.thrift.protocol.TType.STRUCT, (short)2);
  private static final org.apache.thrift.protocol.TField CONTRACT_CODE_FIELD_DESC = new org.apache.thrift.protocol.TField("contractCode", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField PLATFORM_ENV_FIELD_DESC = new org.apache.thrift.protocol.TField("platformEnv", org.apache.thrift.protocol.TType.I32, (short)8);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new CtpContractStandardSchemeFactory());
    schemes.put(TupleScheme.class, new CtpContractTupleSchemeFactory());
  }

  public int ctpContractId; // optional
  public CtpCommodity ctpCommodity; // optional
  public String contractCode; // optional
  /**
   * 
   * @see PlatformEnv
   */
  public PlatformEnv platformEnv; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    CTP_CONTRACT_ID((short)1, "ctpContractId"),
    CTP_COMMODITY((short)2, "ctpCommodity"),
    CONTRACT_CODE((short)3, "contractCode"),
    /**
     * 
     * @see PlatformEnv
     */
    PLATFORM_ENV((short)8, "platformEnv");

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
        case 1: // CTP_CONTRACT_ID
          return CTP_CONTRACT_ID;
        case 2: // CTP_COMMODITY
          return CTP_COMMODITY;
        case 3: // CONTRACT_CODE
          return CONTRACT_CODE;
        case 8: // PLATFORM_ENV
          return PLATFORM_ENV;
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
  private static final int __CTPCONTRACTID_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  private _Fields optionals[] = {_Fields.CTP_CONTRACT_ID,_Fields.CTP_COMMODITY,_Fields.CONTRACT_CODE,_Fields.PLATFORM_ENV};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.CTP_CONTRACT_ID, new org.apache.thrift.meta_data.FieldMetaData("ctpContractId", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.CTP_COMMODITY, new org.apache.thrift.meta_data.FieldMetaData("ctpCommodity", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, CtpCommodity.class)));
    tmpMap.put(_Fields.CONTRACT_CODE, new org.apache.thrift.meta_data.FieldMetaData("contractCode", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.PLATFORM_ENV, new org.apache.thrift.meta_data.FieldMetaData("platformEnv", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, PlatformEnv.class)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(CtpContract.class, metaDataMap);
  }

  public CtpContract() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public CtpContract(CtpContract other) {
    __isset_bitfield = other.__isset_bitfield;
    this.ctpContractId = other.ctpContractId;
    if (other.isSetCtpCommodity()) {
      this.ctpCommodity = new CtpCommodity(other.ctpCommodity);
    }
    if (other.isSetContractCode()) {
      this.contractCode = other.contractCode;
    }
    if (other.isSetPlatformEnv()) {
      this.platformEnv = other.platformEnv;
    }
  }

  public CtpContract deepCopy() {
    return new CtpContract(this);
  }

  @Override
  public void clear() {
    setCtpContractIdIsSet(false);
    this.ctpContractId = 0;
    this.ctpCommodity = null;
    this.contractCode = null;
    this.platformEnv = null;
  }

  public int getCtpContractId() {
    return this.ctpContractId;
  }

  public CtpContract setCtpContractId(int ctpContractId) {
    this.ctpContractId = ctpContractId;
    setCtpContractIdIsSet(true);
    return this;
  }

  public void unsetCtpContractId() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __CTPCONTRACTID_ISSET_ID);
  }

  /** Returns true if field ctpContractId is set (has been assigned a value) and false otherwise */
  public boolean isSetCtpContractId() {
    return EncodingUtils.testBit(__isset_bitfield, __CTPCONTRACTID_ISSET_ID);
  }

  public void setCtpContractIdIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __CTPCONTRACTID_ISSET_ID, value);
  }

  public CtpCommodity getCtpCommodity() {
    return this.ctpCommodity;
  }

  public CtpContract setCtpCommodity(CtpCommodity ctpCommodity) {
    this.ctpCommodity = ctpCommodity;
    return this;
  }

  public void unsetCtpCommodity() {
    this.ctpCommodity = null;
  }

  /** Returns true if field ctpCommodity is set (has been assigned a value) and false otherwise */
  public boolean isSetCtpCommodity() {
    return this.ctpCommodity != null;
  }

  public void setCtpCommodityIsSet(boolean value) {
    if (!value) {
      this.ctpCommodity = null;
    }
  }

  public String getContractCode() {
    return this.contractCode;
  }

  public CtpContract setContractCode(String contractCode) {
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

  /**
   * 
   * @see PlatformEnv
   */
  public PlatformEnv getPlatformEnv() {
    return this.platformEnv;
  }

  /**
   * 
   * @see PlatformEnv
   */
  public CtpContract setPlatformEnv(PlatformEnv platformEnv) {
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

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case CTP_CONTRACT_ID:
      if (value == null) {
        unsetCtpContractId();
      } else {
        setCtpContractId((Integer)value);
      }
      break;

    case CTP_COMMODITY:
      if (value == null) {
        unsetCtpCommodity();
      } else {
        setCtpCommodity((CtpCommodity)value);
      }
      break;

    case CONTRACT_CODE:
      if (value == null) {
        unsetContractCode();
      } else {
        setContractCode((String)value);
      }
      break;

    case PLATFORM_ENV:
      if (value == null) {
        unsetPlatformEnv();
      } else {
        setPlatformEnv((PlatformEnv)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case CTP_CONTRACT_ID:
      return Integer.valueOf(getCtpContractId());

    case CTP_COMMODITY:
      return getCtpCommodity();

    case CONTRACT_CODE:
      return getContractCode();

    case PLATFORM_ENV:
      return getPlatformEnv();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case CTP_CONTRACT_ID:
      return isSetCtpContractId();
    case CTP_COMMODITY:
      return isSetCtpCommodity();
    case CONTRACT_CODE:
      return isSetContractCode();
    case PLATFORM_ENV:
      return isSetPlatformEnv();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof CtpContract)
      return this.equals((CtpContract)that);
    return false;
  }

  public boolean equals(CtpContract that) {
    if (that == null)
      return false;

    boolean this_present_ctpContractId = true && this.isSetCtpContractId();
    boolean that_present_ctpContractId = true && that.isSetCtpContractId();
    if (this_present_ctpContractId || that_present_ctpContractId) {
      if (!(this_present_ctpContractId && that_present_ctpContractId))
        return false;
      if (this.ctpContractId != that.ctpContractId)
        return false;
    }

    boolean this_present_ctpCommodity = true && this.isSetCtpCommodity();
    boolean that_present_ctpCommodity = true && that.isSetCtpCommodity();
    if (this_present_ctpCommodity || that_present_ctpCommodity) {
      if (!(this_present_ctpCommodity && that_present_ctpCommodity))
        return false;
      if (!this.ctpCommodity.equals(that.ctpCommodity))
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

    boolean this_present_platformEnv = true && this.isSetPlatformEnv();
    boolean that_present_platformEnv = true && that.isSetPlatformEnv();
    if (this_present_platformEnv || that_present_platformEnv) {
      if (!(this_present_platformEnv && that_present_platformEnv))
        return false;
      if (!this.platformEnv.equals(that.platformEnv))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  @Override
  public int compareTo(CtpContract other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetCtpContractId()).compareTo(other.isSetCtpContractId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCtpContractId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.ctpContractId, other.ctpContractId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetCtpCommodity()).compareTo(other.isSetCtpCommodity());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCtpCommodity()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.ctpCommodity, other.ctpCommodity);
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
    StringBuilder sb = new StringBuilder("CtpContract(");
    boolean first = true;

    if (isSetCtpContractId()) {
      sb.append("ctpContractId:");
      sb.append(this.ctpContractId);
      first = false;
    }
    if (isSetCtpCommodity()) {
      if (!first) sb.append(", ");
      sb.append("ctpCommodity:");
      if (this.ctpCommodity == null) {
        sb.append("null");
      } else {
        sb.append(this.ctpCommodity);
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
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
    if (ctpCommodity != null) {
      ctpCommodity.validate();
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
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class CtpContractStandardSchemeFactory implements SchemeFactory {
    public CtpContractStandardScheme getScheme() {
      return new CtpContractStandardScheme();
    }
  }

  private static class CtpContractStandardScheme extends StandardScheme<CtpContract> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, CtpContract struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // CTP_CONTRACT_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.ctpContractId = iprot.readI32();
              struct.setCtpContractIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // CTP_COMMODITY
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.ctpCommodity = new CtpCommodity();
              struct.ctpCommodity.read(iprot);
              struct.setCtpCommodityIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // CONTRACT_CODE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.contractCode = iprot.readString();
              struct.setContractCodeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 8: // PLATFORM_ENV
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.platformEnv = PlatformEnv.findByValue(iprot.readI32());
              struct.setPlatformEnvIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, CtpContract struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.isSetCtpContractId()) {
        oprot.writeFieldBegin(CTP_CONTRACT_ID_FIELD_DESC);
        oprot.writeI32(struct.ctpContractId);
        oprot.writeFieldEnd();
      }
      if (struct.ctpCommodity != null) {
        if (struct.isSetCtpCommodity()) {
          oprot.writeFieldBegin(CTP_COMMODITY_FIELD_DESC);
          struct.ctpCommodity.write(oprot);
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
      if (struct.platformEnv != null) {
        if (struct.isSetPlatformEnv()) {
          oprot.writeFieldBegin(PLATFORM_ENV_FIELD_DESC);
          oprot.writeI32(struct.platformEnv.getValue());
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class CtpContractTupleSchemeFactory implements SchemeFactory {
    public CtpContractTupleScheme getScheme() {
      return new CtpContractTupleScheme();
    }
  }

  private static class CtpContractTupleScheme extends TupleScheme<CtpContract> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, CtpContract struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetCtpContractId()) {
        optionals.set(0);
      }
      if (struct.isSetCtpCommodity()) {
        optionals.set(1);
      }
      if (struct.isSetContractCode()) {
        optionals.set(2);
      }
      if (struct.isSetPlatformEnv()) {
        optionals.set(3);
      }
      oprot.writeBitSet(optionals, 4);
      if (struct.isSetCtpContractId()) {
        oprot.writeI32(struct.ctpContractId);
      }
      if (struct.isSetCtpCommodity()) {
        struct.ctpCommodity.write(oprot);
      }
      if (struct.isSetContractCode()) {
        oprot.writeString(struct.contractCode);
      }
      if (struct.isSetPlatformEnv()) {
        oprot.writeI32(struct.platformEnv.getValue());
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, CtpContract struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(4);
      if (incoming.get(0)) {
        struct.ctpContractId = iprot.readI32();
        struct.setCtpContractIdIsSet(true);
      }
      if (incoming.get(1)) {
        struct.ctpCommodity = new CtpCommodity();
        struct.ctpCommodity.read(iprot);
        struct.setCtpCommodityIsSet(true);
      }
      if (incoming.get(2)) {
        struct.contractCode = iprot.readString();
        struct.setContractCodeIsSet(true);
      }
      if (incoming.get(3)) {
        struct.platformEnv = PlatformEnv.findByValue(iprot.readI32());
        struct.setPlatformEnvIsSet(true);
      }
    }
  }

}
