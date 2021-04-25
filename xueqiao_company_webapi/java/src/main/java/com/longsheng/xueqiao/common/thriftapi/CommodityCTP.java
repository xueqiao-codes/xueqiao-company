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

public class CommodityCTP implements org.apache.thrift.TBase<CommodityCTP, CommodityCTP._Fields>, java.io.Serializable, Cloneable, Comparable<CommodityCTP> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("CommodityCTP");

  private static final org.apache.thrift.protocol.TField COMODITY_BASE_FIELD_DESC = new org.apache.thrift.protocol.TField("comodityBase", org.apache.thrift.protocol.TType.STRUCT, (short)1);
  private static final org.apache.thrift.protocol.TField CTP_EXTRA_FIELD_DESC = new org.apache.thrift.protocol.TField("ctpExtra", org.apache.thrift.protocol.TType.STRUCT, (short)2);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new CommodityCTPStandardSchemeFactory());
    schemes.put(TupleScheme.class, new CommodityCTPTupleSchemeFactory());
  }

  public CommodityBase comodityBase; // optional
  public CTPCommodityExtra ctpExtra; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    COMODITY_BASE((short)1, "comodityBase"),
    CTP_EXTRA((short)2, "ctpExtra");

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
        case 1: // COMODITY_BASE
          return COMODITY_BASE;
        case 2: // CTP_EXTRA
          return CTP_EXTRA;
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
  private _Fields optionals[] = {_Fields.COMODITY_BASE,_Fields.CTP_EXTRA};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.COMODITY_BASE, new org.apache.thrift.meta_data.FieldMetaData("comodityBase", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, CommodityBase.class)));
    tmpMap.put(_Fields.CTP_EXTRA, new org.apache.thrift.meta_data.FieldMetaData("ctpExtra", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, CTPCommodityExtra.class)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(CommodityCTP.class, metaDataMap);
  }

  public CommodityCTP() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public CommodityCTP(CommodityCTP other) {
    if (other.isSetComodityBase()) {
      this.comodityBase = new CommodityBase(other.comodityBase);
    }
    if (other.isSetCtpExtra()) {
      this.ctpExtra = new CTPCommodityExtra(other.ctpExtra);
    }
  }

  public CommodityCTP deepCopy() {
    return new CommodityCTP(this);
  }

  @Override
  public void clear() {
    this.comodityBase = null;
    this.ctpExtra = null;
  }

  public CommodityBase getComodityBase() {
    return this.comodityBase;
  }

  public CommodityCTP setComodityBase(CommodityBase comodityBase) {
    this.comodityBase = comodityBase;
    return this;
  }

  public void unsetComodityBase() {
    this.comodityBase = null;
  }

  /** Returns true if field comodityBase is set (has been assigned a value) and false otherwise */
  public boolean isSetComodityBase() {
    return this.comodityBase != null;
  }

  public void setComodityBaseIsSet(boolean value) {
    if (!value) {
      this.comodityBase = null;
    }
  }

  public CTPCommodityExtra getCtpExtra() {
    return this.ctpExtra;
  }

  public CommodityCTP setCtpExtra(CTPCommodityExtra ctpExtra) {
    this.ctpExtra = ctpExtra;
    return this;
  }

  public void unsetCtpExtra() {
    this.ctpExtra = null;
  }

  /** Returns true if field ctpExtra is set (has been assigned a value) and false otherwise */
  public boolean isSetCtpExtra() {
    return this.ctpExtra != null;
  }

  public void setCtpExtraIsSet(boolean value) {
    if (!value) {
      this.ctpExtra = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case COMODITY_BASE:
      if (value == null) {
        unsetComodityBase();
      } else {
        setComodityBase((CommodityBase)value);
      }
      break;

    case CTP_EXTRA:
      if (value == null) {
        unsetCtpExtra();
      } else {
        setCtpExtra((CTPCommodityExtra)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case COMODITY_BASE:
      return getComodityBase();

    case CTP_EXTRA:
      return getCtpExtra();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case COMODITY_BASE:
      return isSetComodityBase();
    case CTP_EXTRA:
      return isSetCtpExtra();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof CommodityCTP)
      return this.equals((CommodityCTP)that);
    return false;
  }

  public boolean equals(CommodityCTP that) {
    if (that == null)
      return false;

    boolean this_present_comodityBase = true && this.isSetComodityBase();
    boolean that_present_comodityBase = true && that.isSetComodityBase();
    if (this_present_comodityBase || that_present_comodityBase) {
      if (!(this_present_comodityBase && that_present_comodityBase))
        return false;
      if (!this.comodityBase.equals(that.comodityBase))
        return false;
    }

    boolean this_present_ctpExtra = true && this.isSetCtpExtra();
    boolean that_present_ctpExtra = true && that.isSetCtpExtra();
    if (this_present_ctpExtra || that_present_ctpExtra) {
      if (!(this_present_ctpExtra && that_present_ctpExtra))
        return false;
      if (!this.ctpExtra.equals(that.ctpExtra))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  @Override
  public int compareTo(CommodityCTP other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetComodityBase()).compareTo(other.isSetComodityBase());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetComodityBase()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.comodityBase, other.comodityBase);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetCtpExtra()).compareTo(other.isSetCtpExtra());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCtpExtra()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.ctpExtra, other.ctpExtra);
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
    StringBuilder sb = new StringBuilder("CommodityCTP(");
    boolean first = true;

    if (isSetComodityBase()) {
      sb.append("comodityBase:");
      if (this.comodityBase == null) {
        sb.append("null");
      } else {
        sb.append(this.comodityBase);
      }
      first = false;
    }
    if (isSetCtpExtra()) {
      if (!first) sb.append(", ");
      sb.append("ctpExtra:");
      if (this.ctpExtra == null) {
        sb.append("null");
      } else {
        sb.append(this.ctpExtra);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
    if (comodityBase != null) {
      comodityBase.validate();
    }
    if (ctpExtra != null) {
      ctpExtra.validate();
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

  private static class CommodityCTPStandardSchemeFactory implements SchemeFactory {
    public CommodityCTPStandardScheme getScheme() {
      return new CommodityCTPStandardScheme();
    }
  }

  private static class CommodityCTPStandardScheme extends StandardScheme<CommodityCTP> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, CommodityCTP struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // COMODITY_BASE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.comodityBase = new CommodityBase();
              struct.comodityBase.read(iprot);
              struct.setComodityBaseIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // CTP_EXTRA
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.ctpExtra = new CTPCommodityExtra();
              struct.ctpExtra.read(iprot);
              struct.setCtpExtraIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, CommodityCTP struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.comodityBase != null) {
        if (struct.isSetComodityBase()) {
          oprot.writeFieldBegin(COMODITY_BASE_FIELD_DESC);
          struct.comodityBase.write(oprot);
          oprot.writeFieldEnd();
        }
      }
      if (struct.ctpExtra != null) {
        if (struct.isSetCtpExtra()) {
          oprot.writeFieldBegin(CTP_EXTRA_FIELD_DESC);
          struct.ctpExtra.write(oprot);
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class CommodityCTPTupleSchemeFactory implements SchemeFactory {
    public CommodityCTPTupleScheme getScheme() {
      return new CommodityCTPTupleScheme();
    }
  }

  private static class CommodityCTPTupleScheme extends TupleScheme<CommodityCTP> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, CommodityCTP struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetComodityBase()) {
        optionals.set(0);
      }
      if (struct.isSetCtpExtra()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetComodityBase()) {
        struct.comodityBase.write(oprot);
      }
      if (struct.isSetCtpExtra()) {
        struct.ctpExtra.write(oprot);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, CommodityCTP struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        struct.comodityBase = new CommodityBase();
        struct.comodityBase.read(iprot);
        struct.setComodityBaseIsSet(true);
      }
      if (incoming.get(1)) {
        struct.ctpExtra = new CTPCommodityExtra();
        struct.ctpExtra.read(iprot);
        struct.setCtpExtraIsSet(true);
      }
    }
  }

}
