/**
 * Autogenerated by Thrift Compiler (0.9.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package xueqiao.company.service.maintenance.webapi.thriftapi;

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

/**
 * 公司服务详情
 */
public class CompanyGroupServiceDetail implements org.apache.thrift.TBase<CompanyGroupServiceDetail, CompanyGroupServiceDetail._Fields>, java.io.Serializable, Cloneable, Comparable<CompanyGroupServiceDetail> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("CompanyGroupServiceDetail");

  private static final org.apache.thrift.protocol.TField GROUP_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("groupId", org.apache.thrift.protocol.TType.I64, (short)1);
  private static final org.apache.thrift.protocol.TField GROUP_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("groupName", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField TRADE_TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("tradeType", org.apache.thrift.protocol.TType.I32, (short)3);
  private static final org.apache.thrift.protocol.TField VERSION_TAG_FIELD_DESC = new org.apache.thrift.protocol.TField("versionTag", org.apache.thrift.protocol.TType.STRING, (short)4);
  private static final org.apache.thrift.protocol.TField HOSTING_SERVICE_STATE_FIELD_DESC = new org.apache.thrift.protocol.TField("hostingServiceState", org.apache.thrift.protocol.TType.I32, (short)5);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new CompanyGroupServiceDetailStandardSchemeFactory());
    schemes.put(TupleScheme.class, new CompanyGroupServiceDetailTupleSchemeFactory());
  }

  public long groupId; // optional
  public String groupName; // optional
  /**
   * 
   * @see TradeType
   */
  public TradeType tradeType; // optional
  public String versionTag; // optional
  /**
   * 
   * @see HostingServiceState
   */
  public HostingServiceState hostingServiceState; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    GROUP_ID((short)1, "groupId"),
    GROUP_NAME((short)2, "groupName"),
    /**
     * 
     * @see TradeType
     */
    TRADE_TYPE((short)3, "tradeType"),
    VERSION_TAG((short)4, "versionTag"),
    /**
     * 
     * @see HostingServiceState
     */
    HOSTING_SERVICE_STATE((short)5, "hostingServiceState");

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
        case 1: // GROUP_ID
          return GROUP_ID;
        case 2: // GROUP_NAME
          return GROUP_NAME;
        case 3: // TRADE_TYPE
          return TRADE_TYPE;
        case 4: // VERSION_TAG
          return VERSION_TAG;
        case 5: // HOSTING_SERVICE_STATE
          return HOSTING_SERVICE_STATE;
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
  private static final int __GROUPID_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  private _Fields optionals[] = {_Fields.GROUP_ID,_Fields.GROUP_NAME,_Fields.TRADE_TYPE,_Fields.VERSION_TAG,_Fields.HOSTING_SERVICE_STATE};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.GROUP_ID, new org.apache.thrift.meta_data.FieldMetaData("groupId", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.GROUP_NAME, new org.apache.thrift.meta_data.FieldMetaData("groupName", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.TRADE_TYPE, new org.apache.thrift.meta_data.FieldMetaData("tradeType", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, TradeType.class)));
    tmpMap.put(_Fields.VERSION_TAG, new org.apache.thrift.meta_data.FieldMetaData("versionTag", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.HOSTING_SERVICE_STATE, new org.apache.thrift.meta_data.FieldMetaData("hostingServiceState", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, HostingServiceState.class)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(CompanyGroupServiceDetail.class, metaDataMap);
  }

  public CompanyGroupServiceDetail() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public CompanyGroupServiceDetail(CompanyGroupServiceDetail other) {
    __isset_bitfield = other.__isset_bitfield;
    this.groupId = other.groupId;
    if (other.isSetGroupName()) {
      this.groupName = other.groupName;
    }
    if (other.isSetTradeType()) {
      this.tradeType = other.tradeType;
    }
    if (other.isSetVersionTag()) {
      this.versionTag = other.versionTag;
    }
    if (other.isSetHostingServiceState()) {
      this.hostingServiceState = other.hostingServiceState;
    }
  }

  public CompanyGroupServiceDetail deepCopy() {
    return new CompanyGroupServiceDetail(this);
  }

  @Override
  public void clear() {
    setGroupIdIsSet(false);
    this.groupId = 0;
    this.groupName = null;
    this.tradeType = null;
    this.versionTag = null;
    this.hostingServiceState = null;
  }

  public long getGroupId() {
    return this.groupId;
  }

  public CompanyGroupServiceDetail setGroupId(long groupId) {
    this.groupId = groupId;
    setGroupIdIsSet(true);
    return this;
  }

  public void unsetGroupId() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __GROUPID_ISSET_ID);
  }

  /** Returns true if field groupId is set (has been assigned a value) and false otherwise */
  public boolean isSetGroupId() {
    return EncodingUtils.testBit(__isset_bitfield, __GROUPID_ISSET_ID);
  }

  public void setGroupIdIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __GROUPID_ISSET_ID, value);
  }

  public String getGroupName() {
    return this.groupName;
  }

  public CompanyGroupServiceDetail setGroupName(String groupName) {
    this.groupName = groupName;
    return this;
  }

  public void unsetGroupName() {
    this.groupName = null;
  }

  /** Returns true if field groupName is set (has been assigned a value) and false otherwise */
  public boolean isSetGroupName() {
    return this.groupName != null;
  }

  public void setGroupNameIsSet(boolean value) {
    if (!value) {
      this.groupName = null;
    }
  }

  /**
   * 
   * @see TradeType
   */
  public TradeType getTradeType() {
    return this.tradeType;
  }

  /**
   * 
   * @see TradeType
   */
  public CompanyGroupServiceDetail setTradeType(TradeType tradeType) {
    this.tradeType = tradeType;
    return this;
  }

  public void unsetTradeType() {
    this.tradeType = null;
  }

  /** Returns true if field tradeType is set (has been assigned a value) and false otherwise */
  public boolean isSetTradeType() {
    return this.tradeType != null;
  }

  public void setTradeTypeIsSet(boolean value) {
    if (!value) {
      this.tradeType = null;
    }
  }

  public String getVersionTag() {
    return this.versionTag;
  }

  public CompanyGroupServiceDetail setVersionTag(String versionTag) {
    this.versionTag = versionTag;
    return this;
  }

  public void unsetVersionTag() {
    this.versionTag = null;
  }

  /** Returns true if field versionTag is set (has been assigned a value) and false otherwise */
  public boolean isSetVersionTag() {
    return this.versionTag != null;
  }

  public void setVersionTagIsSet(boolean value) {
    if (!value) {
      this.versionTag = null;
    }
  }

  /**
   * 
   * @see HostingServiceState
   */
  public HostingServiceState getHostingServiceState() {
    return this.hostingServiceState;
  }

  /**
   * 
   * @see HostingServiceState
   */
  public CompanyGroupServiceDetail setHostingServiceState(HostingServiceState hostingServiceState) {
    this.hostingServiceState = hostingServiceState;
    return this;
  }

  public void unsetHostingServiceState() {
    this.hostingServiceState = null;
  }

  /** Returns true if field hostingServiceState is set (has been assigned a value) and false otherwise */
  public boolean isSetHostingServiceState() {
    return this.hostingServiceState != null;
  }

  public void setHostingServiceStateIsSet(boolean value) {
    if (!value) {
      this.hostingServiceState = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case GROUP_ID:
      if (value == null) {
        unsetGroupId();
      } else {
        setGroupId((Long)value);
      }
      break;

    case GROUP_NAME:
      if (value == null) {
        unsetGroupName();
      } else {
        setGroupName((String)value);
      }
      break;

    case TRADE_TYPE:
      if (value == null) {
        unsetTradeType();
      } else {
        setTradeType((TradeType)value);
      }
      break;

    case VERSION_TAG:
      if (value == null) {
        unsetVersionTag();
      } else {
        setVersionTag((String)value);
      }
      break;

    case HOSTING_SERVICE_STATE:
      if (value == null) {
        unsetHostingServiceState();
      } else {
        setHostingServiceState((HostingServiceState)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case GROUP_ID:
      return Long.valueOf(getGroupId());

    case GROUP_NAME:
      return getGroupName();

    case TRADE_TYPE:
      return getTradeType();

    case VERSION_TAG:
      return getVersionTag();

    case HOSTING_SERVICE_STATE:
      return getHostingServiceState();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case GROUP_ID:
      return isSetGroupId();
    case GROUP_NAME:
      return isSetGroupName();
    case TRADE_TYPE:
      return isSetTradeType();
    case VERSION_TAG:
      return isSetVersionTag();
    case HOSTING_SERVICE_STATE:
      return isSetHostingServiceState();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof CompanyGroupServiceDetail)
      return this.equals((CompanyGroupServiceDetail)that);
    return false;
  }

  public boolean equals(CompanyGroupServiceDetail that) {
    if (that == null)
      return false;

    boolean this_present_groupId = true && this.isSetGroupId();
    boolean that_present_groupId = true && that.isSetGroupId();
    if (this_present_groupId || that_present_groupId) {
      if (!(this_present_groupId && that_present_groupId))
        return false;
      if (this.groupId != that.groupId)
        return false;
    }

    boolean this_present_groupName = true && this.isSetGroupName();
    boolean that_present_groupName = true && that.isSetGroupName();
    if (this_present_groupName || that_present_groupName) {
      if (!(this_present_groupName && that_present_groupName))
        return false;
      if (!this.groupName.equals(that.groupName))
        return false;
    }

    boolean this_present_tradeType = true && this.isSetTradeType();
    boolean that_present_tradeType = true && that.isSetTradeType();
    if (this_present_tradeType || that_present_tradeType) {
      if (!(this_present_tradeType && that_present_tradeType))
        return false;
      if (!this.tradeType.equals(that.tradeType))
        return false;
    }

    boolean this_present_versionTag = true && this.isSetVersionTag();
    boolean that_present_versionTag = true && that.isSetVersionTag();
    if (this_present_versionTag || that_present_versionTag) {
      if (!(this_present_versionTag && that_present_versionTag))
        return false;
      if (!this.versionTag.equals(that.versionTag))
        return false;
    }

    boolean this_present_hostingServiceState = true && this.isSetHostingServiceState();
    boolean that_present_hostingServiceState = true && that.isSetHostingServiceState();
    if (this_present_hostingServiceState || that_present_hostingServiceState) {
      if (!(this_present_hostingServiceState && that_present_hostingServiceState))
        return false;
      if (!this.hostingServiceState.equals(that.hostingServiceState))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  @Override
  public int compareTo(CompanyGroupServiceDetail other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetGroupId()).compareTo(other.isSetGroupId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetGroupId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.groupId, other.groupId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetGroupName()).compareTo(other.isSetGroupName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetGroupName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.groupName, other.groupName);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetTradeType()).compareTo(other.isSetTradeType());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTradeType()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.tradeType, other.tradeType);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetVersionTag()).compareTo(other.isSetVersionTag());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetVersionTag()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.versionTag, other.versionTag);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetHostingServiceState()).compareTo(other.isSetHostingServiceState());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetHostingServiceState()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.hostingServiceState, other.hostingServiceState);
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
    StringBuilder sb = new StringBuilder("CompanyGroupServiceDetail(");
    boolean first = true;

    if (isSetGroupId()) {
      sb.append("groupId:");
      sb.append(this.groupId);
      first = false;
    }
    if (isSetGroupName()) {
      if (!first) sb.append(", ");
      sb.append("groupName:");
      if (this.groupName == null) {
        sb.append("null");
      } else {
        sb.append(this.groupName);
      }
      first = false;
    }
    if (isSetTradeType()) {
      if (!first) sb.append(", ");
      sb.append("tradeType:");
      if (this.tradeType == null) {
        sb.append("null");
      } else {
        sb.append(this.tradeType);
      }
      first = false;
    }
    if (isSetVersionTag()) {
      if (!first) sb.append(", ");
      sb.append("versionTag:");
      if (this.versionTag == null) {
        sb.append("null");
      } else {
        sb.append(this.versionTag);
      }
      first = false;
    }
    if (isSetHostingServiceState()) {
      if (!first) sb.append(", ");
      sb.append("hostingServiceState:");
      if (this.hostingServiceState == null) {
        sb.append("null");
      } else {
        sb.append(this.hostingServiceState);
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

  private static class CompanyGroupServiceDetailStandardSchemeFactory implements SchemeFactory {
    public CompanyGroupServiceDetailStandardScheme getScheme() {
      return new CompanyGroupServiceDetailStandardScheme();
    }
  }

  private static class CompanyGroupServiceDetailStandardScheme extends StandardScheme<CompanyGroupServiceDetail> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, CompanyGroupServiceDetail struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // GROUP_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.groupId = iprot.readI64();
              struct.setGroupIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // GROUP_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.groupName = iprot.readString();
              struct.setGroupNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // TRADE_TYPE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.tradeType = TradeType.findByValue(iprot.readI32());
              struct.setTradeTypeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // VERSION_TAG
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.versionTag = iprot.readString();
              struct.setVersionTagIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // HOSTING_SERVICE_STATE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.hostingServiceState = HostingServiceState.findByValue(iprot.readI32());
              struct.setHostingServiceStateIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, CompanyGroupServiceDetail struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.isSetGroupId()) {
        oprot.writeFieldBegin(GROUP_ID_FIELD_DESC);
        oprot.writeI64(struct.groupId);
        oprot.writeFieldEnd();
      }
      if (struct.groupName != null) {
        if (struct.isSetGroupName()) {
          oprot.writeFieldBegin(GROUP_NAME_FIELD_DESC);
          oprot.writeString(struct.groupName);
          oprot.writeFieldEnd();
        }
      }
      if (struct.tradeType != null) {
        if (struct.isSetTradeType()) {
          oprot.writeFieldBegin(TRADE_TYPE_FIELD_DESC);
          oprot.writeI32(struct.tradeType.getValue());
          oprot.writeFieldEnd();
        }
      }
      if (struct.versionTag != null) {
        if (struct.isSetVersionTag()) {
          oprot.writeFieldBegin(VERSION_TAG_FIELD_DESC);
          oprot.writeString(struct.versionTag);
          oprot.writeFieldEnd();
        }
      }
      if (struct.hostingServiceState != null) {
        if (struct.isSetHostingServiceState()) {
          oprot.writeFieldBegin(HOSTING_SERVICE_STATE_FIELD_DESC);
          oprot.writeI32(struct.hostingServiceState.getValue());
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class CompanyGroupServiceDetailTupleSchemeFactory implements SchemeFactory {
    public CompanyGroupServiceDetailTupleScheme getScheme() {
      return new CompanyGroupServiceDetailTupleScheme();
    }
  }

  private static class CompanyGroupServiceDetailTupleScheme extends TupleScheme<CompanyGroupServiceDetail> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, CompanyGroupServiceDetail struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetGroupId()) {
        optionals.set(0);
      }
      if (struct.isSetGroupName()) {
        optionals.set(1);
      }
      if (struct.isSetTradeType()) {
        optionals.set(2);
      }
      if (struct.isSetVersionTag()) {
        optionals.set(3);
      }
      if (struct.isSetHostingServiceState()) {
        optionals.set(4);
      }
      oprot.writeBitSet(optionals, 5);
      if (struct.isSetGroupId()) {
        oprot.writeI64(struct.groupId);
      }
      if (struct.isSetGroupName()) {
        oprot.writeString(struct.groupName);
      }
      if (struct.isSetTradeType()) {
        oprot.writeI32(struct.tradeType.getValue());
      }
      if (struct.isSetVersionTag()) {
        oprot.writeString(struct.versionTag);
      }
      if (struct.isSetHostingServiceState()) {
        oprot.writeI32(struct.hostingServiceState.getValue());
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, CompanyGroupServiceDetail struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(5);
      if (incoming.get(0)) {
        struct.groupId = iprot.readI64();
        struct.setGroupIdIsSet(true);
      }
      if (incoming.get(1)) {
        struct.groupName = iprot.readString();
        struct.setGroupNameIsSet(true);
      }
      if (incoming.get(2)) {
        struct.tradeType = TradeType.findByValue(iprot.readI32());
        struct.setTradeTypeIsSet(true);
      }
      if (incoming.get(3)) {
        struct.versionTag = iprot.readString();
        struct.setVersionTagIsSet(true);
      }
      if (incoming.get(4)) {
        struct.hostingServiceState = HostingServiceState.findByValue(iprot.readI32());
        struct.setHostingServiceStateIsSet(true);
      }
    }
  }

}

