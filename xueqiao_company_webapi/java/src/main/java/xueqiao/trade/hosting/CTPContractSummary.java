/**
 * Autogenerated by Thrift Compiler (0.9.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package xueqiao.trade.hosting;

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

public class CTPContractSummary implements org.apache.thrift.TBase<CTPContractSummary, CTPContractSummary._Fields>, java.io.Serializable, Cloneable, Comparable<CTPContractSummary> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("CTPContractSummary");

  private static final org.apache.thrift.protocol.TField CTP_EXCHANGE_CODE_FIELD_DESC = new org.apache.thrift.protocol.TField("ctpExchangeCode", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField CTP_COMMODITY_CODE_FIELD_DESC = new org.apache.thrift.protocol.TField("ctpCommodityCode", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField CTP_COMMODITY_TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("ctpCommodityType", org.apache.thrift.protocol.TType.I16, (short)3);
  private static final org.apache.thrift.protocol.TField CTP_CONTRACT_CODE_FIELD_DESC = new org.apache.thrift.protocol.TField("ctpContractCode", org.apache.thrift.protocol.TType.STRING, (short)4);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new CTPContractSummaryStandardSchemeFactory());
    schemes.put(TupleScheme.class, new CTPContractSummaryTupleSchemeFactory());
  }

  public String ctpExchangeCode; // optional
  public String ctpCommodityCode; // optional
  public short ctpCommodityType; // optional
  public String ctpContractCode; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    CTP_EXCHANGE_CODE((short)1, "ctpExchangeCode"),
    CTP_COMMODITY_CODE((short)2, "ctpCommodityCode"),
    CTP_COMMODITY_TYPE((short)3, "ctpCommodityType"),
    CTP_CONTRACT_CODE((short)4, "ctpContractCode");

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
        case 1: // CTP_EXCHANGE_CODE
          return CTP_EXCHANGE_CODE;
        case 2: // CTP_COMMODITY_CODE
          return CTP_COMMODITY_CODE;
        case 3: // CTP_COMMODITY_TYPE
          return CTP_COMMODITY_TYPE;
        case 4: // CTP_CONTRACT_CODE
          return CTP_CONTRACT_CODE;
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
  private static final int __CTPCOMMODITYTYPE_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  private _Fields optionals[] = {_Fields.CTP_EXCHANGE_CODE,_Fields.CTP_COMMODITY_CODE,_Fields.CTP_COMMODITY_TYPE,_Fields.CTP_CONTRACT_CODE};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.CTP_EXCHANGE_CODE, new org.apache.thrift.meta_data.FieldMetaData("ctpExchangeCode", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.CTP_COMMODITY_CODE, new org.apache.thrift.meta_data.FieldMetaData("ctpCommodityCode", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.CTP_COMMODITY_TYPE, new org.apache.thrift.meta_data.FieldMetaData("ctpCommodityType", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I16)));
    tmpMap.put(_Fields.CTP_CONTRACT_CODE, new org.apache.thrift.meta_data.FieldMetaData("ctpContractCode", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(CTPContractSummary.class, metaDataMap);
  }

  public CTPContractSummary() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public CTPContractSummary(CTPContractSummary other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetCtpExchangeCode()) {
      this.ctpExchangeCode = other.ctpExchangeCode;
    }
    if (other.isSetCtpCommodityCode()) {
      this.ctpCommodityCode = other.ctpCommodityCode;
    }
    this.ctpCommodityType = other.ctpCommodityType;
    if (other.isSetCtpContractCode()) {
      this.ctpContractCode = other.ctpContractCode;
    }
  }

  public CTPContractSummary deepCopy() {
    return new CTPContractSummary(this);
  }

  @Override
  public void clear() {
    this.ctpExchangeCode = null;
    this.ctpCommodityCode = null;
    setCtpCommodityTypeIsSet(false);
    this.ctpCommodityType = 0;
    this.ctpContractCode = null;
  }

  public String getCtpExchangeCode() {
    return this.ctpExchangeCode;
  }

  public CTPContractSummary setCtpExchangeCode(String ctpExchangeCode) {
    this.ctpExchangeCode = ctpExchangeCode;
    return this;
  }

  public void unsetCtpExchangeCode() {
    this.ctpExchangeCode = null;
  }

  /** Returns true if field ctpExchangeCode is set (has been assigned a value) and false otherwise */
  public boolean isSetCtpExchangeCode() {
    return this.ctpExchangeCode != null;
  }

  public void setCtpExchangeCodeIsSet(boolean value) {
    if (!value) {
      this.ctpExchangeCode = null;
    }
  }

  public String getCtpCommodityCode() {
    return this.ctpCommodityCode;
  }

  public CTPContractSummary setCtpCommodityCode(String ctpCommodityCode) {
    this.ctpCommodityCode = ctpCommodityCode;
    return this;
  }

  public void unsetCtpCommodityCode() {
    this.ctpCommodityCode = null;
  }

  /** Returns true if field ctpCommodityCode is set (has been assigned a value) and false otherwise */
  public boolean isSetCtpCommodityCode() {
    return this.ctpCommodityCode != null;
  }

  public void setCtpCommodityCodeIsSet(boolean value) {
    if (!value) {
      this.ctpCommodityCode = null;
    }
  }

  public short getCtpCommodityType() {
    return this.ctpCommodityType;
  }

  public CTPContractSummary setCtpCommodityType(short ctpCommodityType) {
    this.ctpCommodityType = ctpCommodityType;
    setCtpCommodityTypeIsSet(true);
    return this;
  }

  public void unsetCtpCommodityType() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __CTPCOMMODITYTYPE_ISSET_ID);
  }

  /** Returns true if field ctpCommodityType is set (has been assigned a value) and false otherwise */
  public boolean isSetCtpCommodityType() {
    return EncodingUtils.testBit(__isset_bitfield, __CTPCOMMODITYTYPE_ISSET_ID);
  }

  public void setCtpCommodityTypeIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __CTPCOMMODITYTYPE_ISSET_ID, value);
  }

  public String getCtpContractCode() {
    return this.ctpContractCode;
  }

  public CTPContractSummary setCtpContractCode(String ctpContractCode) {
    this.ctpContractCode = ctpContractCode;
    return this;
  }

  public void unsetCtpContractCode() {
    this.ctpContractCode = null;
  }

  /** Returns true if field ctpContractCode is set (has been assigned a value) and false otherwise */
  public boolean isSetCtpContractCode() {
    return this.ctpContractCode != null;
  }

  public void setCtpContractCodeIsSet(boolean value) {
    if (!value) {
      this.ctpContractCode = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case CTP_EXCHANGE_CODE:
      if (value == null) {
        unsetCtpExchangeCode();
      } else {
        setCtpExchangeCode((String)value);
      }
      break;

    case CTP_COMMODITY_CODE:
      if (value == null) {
        unsetCtpCommodityCode();
      } else {
        setCtpCommodityCode((String)value);
      }
      break;

    case CTP_COMMODITY_TYPE:
      if (value == null) {
        unsetCtpCommodityType();
      } else {
        setCtpCommodityType((Short)value);
      }
      break;

    case CTP_CONTRACT_CODE:
      if (value == null) {
        unsetCtpContractCode();
      } else {
        setCtpContractCode((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case CTP_EXCHANGE_CODE:
      return getCtpExchangeCode();

    case CTP_COMMODITY_CODE:
      return getCtpCommodityCode();

    case CTP_COMMODITY_TYPE:
      return Short.valueOf(getCtpCommodityType());

    case CTP_CONTRACT_CODE:
      return getCtpContractCode();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case CTP_EXCHANGE_CODE:
      return isSetCtpExchangeCode();
    case CTP_COMMODITY_CODE:
      return isSetCtpCommodityCode();
    case CTP_COMMODITY_TYPE:
      return isSetCtpCommodityType();
    case CTP_CONTRACT_CODE:
      return isSetCtpContractCode();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof CTPContractSummary)
      return this.equals((CTPContractSummary)that);
    return false;
  }

  public boolean equals(CTPContractSummary that) {
    if (that == null)
      return false;

    boolean this_present_ctpExchangeCode = true && this.isSetCtpExchangeCode();
    boolean that_present_ctpExchangeCode = true && that.isSetCtpExchangeCode();
    if (this_present_ctpExchangeCode || that_present_ctpExchangeCode) {
      if (!(this_present_ctpExchangeCode && that_present_ctpExchangeCode))
        return false;
      if (!this.ctpExchangeCode.equals(that.ctpExchangeCode))
        return false;
    }

    boolean this_present_ctpCommodityCode = true && this.isSetCtpCommodityCode();
    boolean that_present_ctpCommodityCode = true && that.isSetCtpCommodityCode();
    if (this_present_ctpCommodityCode || that_present_ctpCommodityCode) {
      if (!(this_present_ctpCommodityCode && that_present_ctpCommodityCode))
        return false;
      if (!this.ctpCommodityCode.equals(that.ctpCommodityCode))
        return false;
    }

    boolean this_present_ctpCommodityType = true && this.isSetCtpCommodityType();
    boolean that_present_ctpCommodityType = true && that.isSetCtpCommodityType();
    if (this_present_ctpCommodityType || that_present_ctpCommodityType) {
      if (!(this_present_ctpCommodityType && that_present_ctpCommodityType))
        return false;
      if (this.ctpCommodityType != that.ctpCommodityType)
        return false;
    }

    boolean this_present_ctpContractCode = true && this.isSetCtpContractCode();
    boolean that_present_ctpContractCode = true && that.isSetCtpContractCode();
    if (this_present_ctpContractCode || that_present_ctpContractCode) {
      if (!(this_present_ctpContractCode && that_present_ctpContractCode))
        return false;
      if (!this.ctpContractCode.equals(that.ctpContractCode))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  @Override
  public int compareTo(CTPContractSummary other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetCtpExchangeCode()).compareTo(other.isSetCtpExchangeCode());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCtpExchangeCode()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.ctpExchangeCode, other.ctpExchangeCode);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetCtpCommodityCode()).compareTo(other.isSetCtpCommodityCode());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCtpCommodityCode()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.ctpCommodityCode, other.ctpCommodityCode);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetCtpCommodityType()).compareTo(other.isSetCtpCommodityType());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCtpCommodityType()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.ctpCommodityType, other.ctpCommodityType);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetCtpContractCode()).compareTo(other.isSetCtpContractCode());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCtpContractCode()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.ctpContractCode, other.ctpContractCode);
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
    StringBuilder sb = new StringBuilder("CTPContractSummary(");
    boolean first = true;

    if (isSetCtpExchangeCode()) {
      sb.append("ctpExchangeCode:");
      if (this.ctpExchangeCode == null) {
        sb.append("null");
      } else {
        sb.append(this.ctpExchangeCode);
      }
      first = false;
    }
    if (isSetCtpCommodityCode()) {
      if (!first) sb.append(", ");
      sb.append("ctpCommodityCode:");
      if (this.ctpCommodityCode == null) {
        sb.append("null");
      } else {
        sb.append(this.ctpCommodityCode);
      }
      first = false;
    }
    if (isSetCtpCommodityType()) {
      if (!first) sb.append(", ");
      sb.append("ctpCommodityType:");
      sb.append(this.ctpCommodityType);
      first = false;
    }
    if (isSetCtpContractCode()) {
      if (!first) sb.append(", ");
      sb.append("ctpContractCode:");
      if (this.ctpContractCode == null) {
        sb.append("null");
      } else {
        sb.append(this.ctpContractCode);
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

  private static class CTPContractSummaryStandardSchemeFactory implements SchemeFactory {
    public CTPContractSummaryStandardScheme getScheme() {
      return new CTPContractSummaryStandardScheme();
    }
  }

  private static class CTPContractSummaryStandardScheme extends StandardScheme<CTPContractSummary> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, CTPContractSummary struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // CTP_EXCHANGE_CODE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.ctpExchangeCode = iprot.readString();
              struct.setCtpExchangeCodeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // CTP_COMMODITY_CODE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.ctpCommodityCode = iprot.readString();
              struct.setCtpCommodityCodeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // CTP_COMMODITY_TYPE
            if (schemeField.type == org.apache.thrift.protocol.TType.I16) {
              struct.ctpCommodityType = iprot.readI16();
              struct.setCtpCommodityTypeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // CTP_CONTRACT_CODE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.ctpContractCode = iprot.readString();
              struct.setCtpContractCodeIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, CTPContractSummary struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.ctpExchangeCode != null) {
        if (struct.isSetCtpExchangeCode()) {
          oprot.writeFieldBegin(CTP_EXCHANGE_CODE_FIELD_DESC);
          oprot.writeString(struct.ctpExchangeCode);
          oprot.writeFieldEnd();
        }
      }
      if (struct.ctpCommodityCode != null) {
        if (struct.isSetCtpCommodityCode()) {
          oprot.writeFieldBegin(CTP_COMMODITY_CODE_FIELD_DESC);
          oprot.writeString(struct.ctpCommodityCode);
          oprot.writeFieldEnd();
        }
      }
      if (struct.isSetCtpCommodityType()) {
        oprot.writeFieldBegin(CTP_COMMODITY_TYPE_FIELD_DESC);
        oprot.writeI16(struct.ctpCommodityType);
        oprot.writeFieldEnd();
      }
      if (struct.ctpContractCode != null) {
        if (struct.isSetCtpContractCode()) {
          oprot.writeFieldBegin(CTP_CONTRACT_CODE_FIELD_DESC);
          oprot.writeString(struct.ctpContractCode);
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class CTPContractSummaryTupleSchemeFactory implements SchemeFactory {
    public CTPContractSummaryTupleScheme getScheme() {
      return new CTPContractSummaryTupleScheme();
    }
  }

  private static class CTPContractSummaryTupleScheme extends TupleScheme<CTPContractSummary> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, CTPContractSummary struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetCtpExchangeCode()) {
        optionals.set(0);
      }
      if (struct.isSetCtpCommodityCode()) {
        optionals.set(1);
      }
      if (struct.isSetCtpCommodityType()) {
        optionals.set(2);
      }
      if (struct.isSetCtpContractCode()) {
        optionals.set(3);
      }
      oprot.writeBitSet(optionals, 4);
      if (struct.isSetCtpExchangeCode()) {
        oprot.writeString(struct.ctpExchangeCode);
      }
      if (struct.isSetCtpCommodityCode()) {
        oprot.writeString(struct.ctpCommodityCode);
      }
      if (struct.isSetCtpCommodityType()) {
        oprot.writeI16(struct.ctpCommodityType);
      }
      if (struct.isSetCtpContractCode()) {
        oprot.writeString(struct.ctpContractCode);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, CTPContractSummary struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(4);
      if (incoming.get(0)) {
        struct.ctpExchangeCode = iprot.readString();
        struct.setCtpExchangeCodeIsSet(true);
      }
      if (incoming.get(1)) {
        struct.ctpCommodityCode = iprot.readString();
        struct.setCtpCommodityCodeIsSet(true);
      }
      if (incoming.get(2)) {
        struct.ctpCommodityType = iprot.readI16();
        struct.setCtpCommodityTypeIsSet(true);
      }
      if (incoming.get(3)) {
        struct.ctpContractCode = iprot.readString();
        struct.setCtpContractCodeIsSet(true);
      }
    }
  }

}
