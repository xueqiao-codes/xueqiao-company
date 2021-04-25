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

public class HostingExecOrderDealInfo implements org.apache.thrift.TBase<HostingExecOrderDealInfo, HostingExecOrderDealInfo._Fields>, java.io.Serializable, Cloneable, Comparable<HostingExecOrderDealInfo> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("HostingExecOrderDealInfo");

  private static final org.apache.thrift.protocol.TField DEAL_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("dealId", org.apache.thrift.protocol.TType.STRUCT, (short)1);
  private static final org.apache.thrift.protocol.TField ORDER_INSERT_DATE_TIME_FIELD_DESC = new org.apache.thrift.protocol.TField("orderInsertDateTime", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField CTP_DEAL_INFO_FIELD_DESC = new org.apache.thrift.protocol.TField("ctpDealInfo", org.apache.thrift.protocol.TType.STRUCT, (short)3);
  private static final org.apache.thrift.protocol.TField ESUNNY9_DEAL_INFO_FIELD_DESC = new org.apache.thrift.protocol.TField("esunny9DealInfo", org.apache.thrift.protocol.TType.STRUCT, (short)4);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new HostingExecOrderDealInfoStandardSchemeFactory());
    schemes.put(TupleScheme.class, new HostingExecOrderDealInfoTupleSchemeFactory());
  }

  public HostingExecOrderDealID dealId; // optional
  public String orderInsertDateTime; // optional
  public HostingExecOrderDealCTPInfo ctpDealInfo; // optional
  public HostingExecOrderDealESunny9Info esunny9DealInfo; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    DEAL_ID((short)1, "dealId"),
    ORDER_INSERT_DATE_TIME((short)2, "orderInsertDateTime"),
    CTP_DEAL_INFO((short)3, "ctpDealInfo"),
    ESUNNY9_DEAL_INFO((short)4, "esunny9DealInfo");

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
        case 1: // DEAL_ID
          return DEAL_ID;
        case 2: // ORDER_INSERT_DATE_TIME
          return ORDER_INSERT_DATE_TIME;
        case 3: // CTP_DEAL_INFO
          return CTP_DEAL_INFO;
        case 4: // ESUNNY9_DEAL_INFO
          return ESUNNY9_DEAL_INFO;
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
  private _Fields optionals[] = {_Fields.DEAL_ID,_Fields.ORDER_INSERT_DATE_TIME,_Fields.CTP_DEAL_INFO,_Fields.ESUNNY9_DEAL_INFO};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.DEAL_ID, new org.apache.thrift.meta_data.FieldMetaData("dealId", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, HostingExecOrderDealID.class)));
    tmpMap.put(_Fields.ORDER_INSERT_DATE_TIME, new org.apache.thrift.meta_data.FieldMetaData("orderInsertDateTime", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.CTP_DEAL_INFO, new org.apache.thrift.meta_data.FieldMetaData("ctpDealInfo", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, HostingExecOrderDealCTPInfo.class)));
    tmpMap.put(_Fields.ESUNNY9_DEAL_INFO, new org.apache.thrift.meta_data.FieldMetaData("esunny9DealInfo", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, HostingExecOrderDealESunny9Info.class)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(HostingExecOrderDealInfo.class, metaDataMap);
  }

  public HostingExecOrderDealInfo() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public HostingExecOrderDealInfo(HostingExecOrderDealInfo other) {
    if (other.isSetDealId()) {
      this.dealId = new HostingExecOrderDealID(other.dealId);
    }
    if (other.isSetOrderInsertDateTime()) {
      this.orderInsertDateTime = other.orderInsertDateTime;
    }
    if (other.isSetCtpDealInfo()) {
      this.ctpDealInfo = new HostingExecOrderDealCTPInfo(other.ctpDealInfo);
    }
    if (other.isSetEsunny9DealInfo()) {
      this.esunny9DealInfo = new HostingExecOrderDealESunny9Info(other.esunny9DealInfo);
    }
  }

  public HostingExecOrderDealInfo deepCopy() {
    return new HostingExecOrderDealInfo(this);
  }

  @Override
  public void clear() {
    this.dealId = null;
    this.orderInsertDateTime = null;
    this.ctpDealInfo = null;
    this.esunny9DealInfo = null;
  }

  public HostingExecOrderDealID getDealId() {
    return this.dealId;
  }

  public HostingExecOrderDealInfo setDealId(HostingExecOrderDealID dealId) {
    this.dealId = dealId;
    return this;
  }

  public void unsetDealId() {
    this.dealId = null;
  }

  /** Returns true if field dealId is set (has been assigned a value) and false otherwise */
  public boolean isSetDealId() {
    return this.dealId != null;
  }

  public void setDealIdIsSet(boolean value) {
    if (!value) {
      this.dealId = null;
    }
  }

  public String getOrderInsertDateTime() {
    return this.orderInsertDateTime;
  }

  public HostingExecOrderDealInfo setOrderInsertDateTime(String orderInsertDateTime) {
    this.orderInsertDateTime = orderInsertDateTime;
    return this;
  }

  public void unsetOrderInsertDateTime() {
    this.orderInsertDateTime = null;
  }

  /** Returns true if field orderInsertDateTime is set (has been assigned a value) and false otherwise */
  public boolean isSetOrderInsertDateTime() {
    return this.orderInsertDateTime != null;
  }

  public void setOrderInsertDateTimeIsSet(boolean value) {
    if (!value) {
      this.orderInsertDateTime = null;
    }
  }

  public HostingExecOrderDealCTPInfo getCtpDealInfo() {
    return this.ctpDealInfo;
  }

  public HostingExecOrderDealInfo setCtpDealInfo(HostingExecOrderDealCTPInfo ctpDealInfo) {
    this.ctpDealInfo = ctpDealInfo;
    return this;
  }

  public void unsetCtpDealInfo() {
    this.ctpDealInfo = null;
  }

  /** Returns true if field ctpDealInfo is set (has been assigned a value) and false otherwise */
  public boolean isSetCtpDealInfo() {
    return this.ctpDealInfo != null;
  }

  public void setCtpDealInfoIsSet(boolean value) {
    if (!value) {
      this.ctpDealInfo = null;
    }
  }

  public HostingExecOrderDealESunny9Info getEsunny9DealInfo() {
    return this.esunny9DealInfo;
  }

  public HostingExecOrderDealInfo setEsunny9DealInfo(HostingExecOrderDealESunny9Info esunny9DealInfo) {
    this.esunny9DealInfo = esunny9DealInfo;
    return this;
  }

  public void unsetEsunny9DealInfo() {
    this.esunny9DealInfo = null;
  }

  /** Returns true if field esunny9DealInfo is set (has been assigned a value) and false otherwise */
  public boolean isSetEsunny9DealInfo() {
    return this.esunny9DealInfo != null;
  }

  public void setEsunny9DealInfoIsSet(boolean value) {
    if (!value) {
      this.esunny9DealInfo = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case DEAL_ID:
      if (value == null) {
        unsetDealId();
      } else {
        setDealId((HostingExecOrderDealID)value);
      }
      break;

    case ORDER_INSERT_DATE_TIME:
      if (value == null) {
        unsetOrderInsertDateTime();
      } else {
        setOrderInsertDateTime((String)value);
      }
      break;

    case CTP_DEAL_INFO:
      if (value == null) {
        unsetCtpDealInfo();
      } else {
        setCtpDealInfo((HostingExecOrderDealCTPInfo)value);
      }
      break;

    case ESUNNY9_DEAL_INFO:
      if (value == null) {
        unsetEsunny9DealInfo();
      } else {
        setEsunny9DealInfo((HostingExecOrderDealESunny9Info)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case DEAL_ID:
      return getDealId();

    case ORDER_INSERT_DATE_TIME:
      return getOrderInsertDateTime();

    case CTP_DEAL_INFO:
      return getCtpDealInfo();

    case ESUNNY9_DEAL_INFO:
      return getEsunny9DealInfo();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case DEAL_ID:
      return isSetDealId();
    case ORDER_INSERT_DATE_TIME:
      return isSetOrderInsertDateTime();
    case CTP_DEAL_INFO:
      return isSetCtpDealInfo();
    case ESUNNY9_DEAL_INFO:
      return isSetEsunny9DealInfo();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof HostingExecOrderDealInfo)
      return this.equals((HostingExecOrderDealInfo)that);
    return false;
  }

  public boolean equals(HostingExecOrderDealInfo that) {
    if (that == null)
      return false;

    boolean this_present_dealId = true && this.isSetDealId();
    boolean that_present_dealId = true && that.isSetDealId();
    if (this_present_dealId || that_present_dealId) {
      if (!(this_present_dealId && that_present_dealId))
        return false;
      if (!this.dealId.equals(that.dealId))
        return false;
    }

    boolean this_present_orderInsertDateTime = true && this.isSetOrderInsertDateTime();
    boolean that_present_orderInsertDateTime = true && that.isSetOrderInsertDateTime();
    if (this_present_orderInsertDateTime || that_present_orderInsertDateTime) {
      if (!(this_present_orderInsertDateTime && that_present_orderInsertDateTime))
        return false;
      if (!this.orderInsertDateTime.equals(that.orderInsertDateTime))
        return false;
    }

    boolean this_present_ctpDealInfo = true && this.isSetCtpDealInfo();
    boolean that_present_ctpDealInfo = true && that.isSetCtpDealInfo();
    if (this_present_ctpDealInfo || that_present_ctpDealInfo) {
      if (!(this_present_ctpDealInfo && that_present_ctpDealInfo))
        return false;
      if (!this.ctpDealInfo.equals(that.ctpDealInfo))
        return false;
    }

    boolean this_present_esunny9DealInfo = true && this.isSetEsunny9DealInfo();
    boolean that_present_esunny9DealInfo = true && that.isSetEsunny9DealInfo();
    if (this_present_esunny9DealInfo || that_present_esunny9DealInfo) {
      if (!(this_present_esunny9DealInfo && that_present_esunny9DealInfo))
        return false;
      if (!this.esunny9DealInfo.equals(that.esunny9DealInfo))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  @Override
  public int compareTo(HostingExecOrderDealInfo other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetDealId()).compareTo(other.isSetDealId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDealId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.dealId, other.dealId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetOrderInsertDateTime()).compareTo(other.isSetOrderInsertDateTime());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetOrderInsertDateTime()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.orderInsertDateTime, other.orderInsertDateTime);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetCtpDealInfo()).compareTo(other.isSetCtpDealInfo());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCtpDealInfo()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.ctpDealInfo, other.ctpDealInfo);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetEsunny9DealInfo()).compareTo(other.isSetEsunny9DealInfo());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetEsunny9DealInfo()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.esunny9DealInfo, other.esunny9DealInfo);
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
    StringBuilder sb = new StringBuilder("HostingExecOrderDealInfo(");
    boolean first = true;

    if (isSetDealId()) {
      sb.append("dealId:");
      if (this.dealId == null) {
        sb.append("null");
      } else {
        sb.append(this.dealId);
      }
      first = false;
    }
    if (isSetOrderInsertDateTime()) {
      if (!first) sb.append(", ");
      sb.append("orderInsertDateTime:");
      if (this.orderInsertDateTime == null) {
        sb.append("null");
      } else {
        sb.append(this.orderInsertDateTime);
      }
      first = false;
    }
    if (isSetCtpDealInfo()) {
      if (!first) sb.append(", ");
      sb.append("ctpDealInfo:");
      if (this.ctpDealInfo == null) {
        sb.append("null");
      } else {
        sb.append(this.ctpDealInfo);
      }
      first = false;
    }
    if (isSetEsunny9DealInfo()) {
      if (!first) sb.append(", ");
      sb.append("esunny9DealInfo:");
      if (this.esunny9DealInfo == null) {
        sb.append("null");
      } else {
        sb.append(this.esunny9DealInfo);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
    if (dealId != null) {
      dealId.validate();
    }
    if (ctpDealInfo != null) {
      ctpDealInfo.validate();
    }
    if (esunny9DealInfo != null) {
      esunny9DealInfo.validate();
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

  private static class HostingExecOrderDealInfoStandardSchemeFactory implements SchemeFactory {
    public HostingExecOrderDealInfoStandardScheme getScheme() {
      return new HostingExecOrderDealInfoStandardScheme();
    }
  }

  private static class HostingExecOrderDealInfoStandardScheme extends StandardScheme<HostingExecOrderDealInfo> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, HostingExecOrderDealInfo struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // DEAL_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.dealId = new HostingExecOrderDealID();
              struct.dealId.read(iprot);
              struct.setDealIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // ORDER_INSERT_DATE_TIME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.orderInsertDateTime = iprot.readString();
              struct.setOrderInsertDateTimeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // CTP_DEAL_INFO
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.ctpDealInfo = new HostingExecOrderDealCTPInfo();
              struct.ctpDealInfo.read(iprot);
              struct.setCtpDealInfoIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // ESUNNY9_DEAL_INFO
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.esunny9DealInfo = new HostingExecOrderDealESunny9Info();
              struct.esunny9DealInfo.read(iprot);
              struct.setEsunny9DealInfoIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, HostingExecOrderDealInfo struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.dealId != null) {
        if (struct.isSetDealId()) {
          oprot.writeFieldBegin(DEAL_ID_FIELD_DESC);
          struct.dealId.write(oprot);
          oprot.writeFieldEnd();
        }
      }
      if (struct.orderInsertDateTime != null) {
        if (struct.isSetOrderInsertDateTime()) {
          oprot.writeFieldBegin(ORDER_INSERT_DATE_TIME_FIELD_DESC);
          oprot.writeString(struct.orderInsertDateTime);
          oprot.writeFieldEnd();
        }
      }
      if (struct.ctpDealInfo != null) {
        if (struct.isSetCtpDealInfo()) {
          oprot.writeFieldBegin(CTP_DEAL_INFO_FIELD_DESC);
          struct.ctpDealInfo.write(oprot);
          oprot.writeFieldEnd();
        }
      }
      if (struct.esunny9DealInfo != null) {
        if (struct.isSetEsunny9DealInfo()) {
          oprot.writeFieldBegin(ESUNNY9_DEAL_INFO_FIELD_DESC);
          struct.esunny9DealInfo.write(oprot);
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class HostingExecOrderDealInfoTupleSchemeFactory implements SchemeFactory {
    public HostingExecOrderDealInfoTupleScheme getScheme() {
      return new HostingExecOrderDealInfoTupleScheme();
    }
  }

  private static class HostingExecOrderDealInfoTupleScheme extends TupleScheme<HostingExecOrderDealInfo> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, HostingExecOrderDealInfo struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetDealId()) {
        optionals.set(0);
      }
      if (struct.isSetOrderInsertDateTime()) {
        optionals.set(1);
      }
      if (struct.isSetCtpDealInfo()) {
        optionals.set(2);
      }
      if (struct.isSetEsunny9DealInfo()) {
        optionals.set(3);
      }
      oprot.writeBitSet(optionals, 4);
      if (struct.isSetDealId()) {
        struct.dealId.write(oprot);
      }
      if (struct.isSetOrderInsertDateTime()) {
        oprot.writeString(struct.orderInsertDateTime);
      }
      if (struct.isSetCtpDealInfo()) {
        struct.ctpDealInfo.write(oprot);
      }
      if (struct.isSetEsunny9DealInfo()) {
        struct.esunny9DealInfo.write(oprot);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, HostingExecOrderDealInfo struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(4);
      if (incoming.get(0)) {
        struct.dealId = new HostingExecOrderDealID();
        struct.dealId.read(iprot);
        struct.setDealIdIsSet(true);
      }
      if (incoming.get(1)) {
        struct.orderInsertDateTime = iprot.readString();
        struct.setOrderInsertDateTimeIsSet(true);
      }
      if (incoming.get(2)) {
        struct.ctpDealInfo = new HostingExecOrderDealCTPInfo();
        struct.ctpDealInfo.read(iprot);
        struct.setCtpDealInfoIsSet(true);
      }
      if (incoming.get(3)) {
        struct.esunny9DealInfo = new HostingExecOrderDealESunny9Info();
        struct.esunny9DealInfo.read(iprot);
        struct.setEsunny9DealInfoIsSet(true);
      }
    }
  }

}
