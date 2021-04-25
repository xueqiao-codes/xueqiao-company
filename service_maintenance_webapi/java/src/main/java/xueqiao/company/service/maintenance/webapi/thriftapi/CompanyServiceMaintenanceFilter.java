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
 * 公司维护信息过滤条件
 */
public class CompanyServiceMaintenanceFilter implements org.apache.thrift.TBase<CompanyServiceMaintenanceFilter, CompanyServiceMaintenanceFilter._Fields>, java.io.Serializable, Cloneable, Comparable<CompanyServiceMaintenanceFilter> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("CompanyServiceMaintenanceFilter");

  private static final org.apache.thrift.protocol.TField COMPANY_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("companyId", org.apache.thrift.protocol.TType.I64, (short)1);
  private static final org.apache.thrift.protocol.TField COMPANY_NAME_PARTICAL_FIELD_DESC = new org.apache.thrift.protocol.TField("companyNamePartical", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField VERSION_TAG_FIELD_DESC = new org.apache.thrift.protocol.TField("versionTag", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField MAINTENANCE_TIME_SPAN_FIELD_DESC = new org.apache.thrift.protocol.TField("maintenanceTimeSpan", org.apache.thrift.protocol.TType.I32, (short)4);
  private static final org.apache.thrift.protocol.TField MAINTENANCE_STATE_FIELD_DESC = new org.apache.thrift.protocol.TField("maintenanceState", org.apache.thrift.protocol.TType.I32, (short)5);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new CompanyServiceMaintenanceFilterStandardSchemeFactory());
    schemes.put(TupleScheme.class, new CompanyServiceMaintenanceFilterTupleSchemeFactory());
  }

  public long companyId; // optional
  public String companyNamePartical; // optional
  public String versionTag; // optional
  /**
   * 
   * @see xueqiao.company.service.maintenance.MaintenanceTimeSpan
   */
  public xueqiao.company.service.maintenance.MaintenanceTimeSpan maintenanceTimeSpan; // optional
  /**
   * 
   * @see xueqiao.company.service.maintenance.MaintenanceState
   */
  public xueqiao.company.service.maintenance.MaintenanceState maintenanceState; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    COMPANY_ID((short)1, "companyId"),
    COMPANY_NAME_PARTICAL((short)2, "companyNamePartical"),
    VERSION_TAG((short)3, "versionTag"),
    /**
     * 
     * @see xueqiao.company.service.maintenance.MaintenanceTimeSpan
     */
    MAINTENANCE_TIME_SPAN((short)4, "maintenanceTimeSpan"),
    /**
     * 
     * @see xueqiao.company.service.maintenance.MaintenanceState
     */
    MAINTENANCE_STATE((short)5, "maintenanceState");

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
        case 1: // COMPANY_ID
          return COMPANY_ID;
        case 2: // COMPANY_NAME_PARTICAL
          return COMPANY_NAME_PARTICAL;
        case 3: // VERSION_TAG
          return VERSION_TAG;
        case 4: // MAINTENANCE_TIME_SPAN
          return MAINTENANCE_TIME_SPAN;
        case 5: // MAINTENANCE_STATE
          return MAINTENANCE_STATE;
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
  private static final int __COMPANYID_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  private _Fields optionals[] = {_Fields.COMPANY_ID,_Fields.COMPANY_NAME_PARTICAL,_Fields.VERSION_TAG,_Fields.MAINTENANCE_TIME_SPAN,_Fields.MAINTENANCE_STATE};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.COMPANY_ID, new org.apache.thrift.meta_data.FieldMetaData("companyId", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.COMPANY_NAME_PARTICAL, new org.apache.thrift.meta_data.FieldMetaData("companyNamePartical", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.VERSION_TAG, new org.apache.thrift.meta_data.FieldMetaData("versionTag", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.MAINTENANCE_TIME_SPAN, new org.apache.thrift.meta_data.FieldMetaData("maintenanceTimeSpan", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, xueqiao.company.service.maintenance.MaintenanceTimeSpan.class)));
    tmpMap.put(_Fields.MAINTENANCE_STATE, new org.apache.thrift.meta_data.FieldMetaData("maintenanceState", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, xueqiao.company.service.maintenance.MaintenanceState.class)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(CompanyServiceMaintenanceFilter.class, metaDataMap);
  }

  public CompanyServiceMaintenanceFilter() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public CompanyServiceMaintenanceFilter(CompanyServiceMaintenanceFilter other) {
    __isset_bitfield = other.__isset_bitfield;
    this.companyId = other.companyId;
    if (other.isSetCompanyNamePartical()) {
      this.companyNamePartical = other.companyNamePartical;
    }
    if (other.isSetVersionTag()) {
      this.versionTag = other.versionTag;
    }
    if (other.isSetMaintenanceTimeSpan()) {
      this.maintenanceTimeSpan = other.maintenanceTimeSpan;
    }
    if (other.isSetMaintenanceState()) {
      this.maintenanceState = other.maintenanceState;
    }
  }

  public CompanyServiceMaintenanceFilter deepCopy() {
    return new CompanyServiceMaintenanceFilter(this);
  }

  @Override
  public void clear() {
    setCompanyIdIsSet(false);
    this.companyId = 0;
    this.companyNamePartical = null;
    this.versionTag = null;
    this.maintenanceTimeSpan = null;
    this.maintenanceState = null;
  }

  public long getCompanyId() {
    return this.companyId;
  }

  public CompanyServiceMaintenanceFilter setCompanyId(long companyId) {
    this.companyId = companyId;
    setCompanyIdIsSet(true);
    return this;
  }

  public void unsetCompanyId() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __COMPANYID_ISSET_ID);
  }

  /** Returns true if field companyId is set (has been assigned a value) and false otherwise */
  public boolean isSetCompanyId() {
    return EncodingUtils.testBit(__isset_bitfield, __COMPANYID_ISSET_ID);
  }

  public void setCompanyIdIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __COMPANYID_ISSET_ID, value);
  }

  public String getCompanyNamePartical() {
    return this.companyNamePartical;
  }

  public CompanyServiceMaintenanceFilter setCompanyNamePartical(String companyNamePartical) {
    this.companyNamePartical = companyNamePartical;
    return this;
  }

  public void unsetCompanyNamePartical() {
    this.companyNamePartical = null;
  }

  /** Returns true if field companyNamePartical is set (has been assigned a value) and false otherwise */
  public boolean isSetCompanyNamePartical() {
    return this.companyNamePartical != null;
  }

  public void setCompanyNameParticalIsSet(boolean value) {
    if (!value) {
      this.companyNamePartical = null;
    }
  }

  public String getVersionTag() {
    return this.versionTag;
  }

  public CompanyServiceMaintenanceFilter setVersionTag(String versionTag) {
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
   * @see xueqiao.company.service.maintenance.MaintenanceTimeSpan
   */
  public xueqiao.company.service.maintenance.MaintenanceTimeSpan getMaintenanceTimeSpan() {
    return this.maintenanceTimeSpan;
  }

  /**
   * 
   * @see xueqiao.company.service.maintenance.MaintenanceTimeSpan
   */
  public CompanyServiceMaintenanceFilter setMaintenanceTimeSpan(xueqiao.company.service.maintenance.MaintenanceTimeSpan maintenanceTimeSpan) {
    this.maintenanceTimeSpan = maintenanceTimeSpan;
    return this;
  }

  public void unsetMaintenanceTimeSpan() {
    this.maintenanceTimeSpan = null;
  }

  /** Returns true if field maintenanceTimeSpan is set (has been assigned a value) and false otherwise */
  public boolean isSetMaintenanceTimeSpan() {
    return this.maintenanceTimeSpan != null;
  }

  public void setMaintenanceTimeSpanIsSet(boolean value) {
    if (!value) {
      this.maintenanceTimeSpan = null;
    }
  }

  /**
   * 
   * @see xueqiao.company.service.maintenance.MaintenanceState
   */
  public xueqiao.company.service.maintenance.MaintenanceState getMaintenanceState() {
    return this.maintenanceState;
  }

  /**
   * 
   * @see xueqiao.company.service.maintenance.MaintenanceState
   */
  public CompanyServiceMaintenanceFilter setMaintenanceState(xueqiao.company.service.maintenance.MaintenanceState maintenanceState) {
    this.maintenanceState = maintenanceState;
    return this;
  }

  public void unsetMaintenanceState() {
    this.maintenanceState = null;
  }

  /** Returns true if field maintenanceState is set (has been assigned a value) and false otherwise */
  public boolean isSetMaintenanceState() {
    return this.maintenanceState != null;
  }

  public void setMaintenanceStateIsSet(boolean value) {
    if (!value) {
      this.maintenanceState = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case COMPANY_ID:
      if (value == null) {
        unsetCompanyId();
      } else {
        setCompanyId((Long)value);
      }
      break;

    case COMPANY_NAME_PARTICAL:
      if (value == null) {
        unsetCompanyNamePartical();
      } else {
        setCompanyNamePartical((String)value);
      }
      break;

    case VERSION_TAG:
      if (value == null) {
        unsetVersionTag();
      } else {
        setVersionTag((String)value);
      }
      break;

    case MAINTENANCE_TIME_SPAN:
      if (value == null) {
        unsetMaintenanceTimeSpan();
      } else {
        setMaintenanceTimeSpan((xueqiao.company.service.maintenance.MaintenanceTimeSpan)value);
      }
      break;

    case MAINTENANCE_STATE:
      if (value == null) {
        unsetMaintenanceState();
      } else {
        setMaintenanceState((xueqiao.company.service.maintenance.MaintenanceState)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case COMPANY_ID:
      return Long.valueOf(getCompanyId());

    case COMPANY_NAME_PARTICAL:
      return getCompanyNamePartical();

    case VERSION_TAG:
      return getVersionTag();

    case MAINTENANCE_TIME_SPAN:
      return getMaintenanceTimeSpan();

    case MAINTENANCE_STATE:
      return getMaintenanceState();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case COMPANY_ID:
      return isSetCompanyId();
    case COMPANY_NAME_PARTICAL:
      return isSetCompanyNamePartical();
    case VERSION_TAG:
      return isSetVersionTag();
    case MAINTENANCE_TIME_SPAN:
      return isSetMaintenanceTimeSpan();
    case MAINTENANCE_STATE:
      return isSetMaintenanceState();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof CompanyServiceMaintenanceFilter)
      return this.equals((CompanyServiceMaintenanceFilter)that);
    return false;
  }

  public boolean equals(CompanyServiceMaintenanceFilter that) {
    if (that == null)
      return false;

    boolean this_present_companyId = true && this.isSetCompanyId();
    boolean that_present_companyId = true && that.isSetCompanyId();
    if (this_present_companyId || that_present_companyId) {
      if (!(this_present_companyId && that_present_companyId))
        return false;
      if (this.companyId != that.companyId)
        return false;
    }

    boolean this_present_companyNamePartical = true && this.isSetCompanyNamePartical();
    boolean that_present_companyNamePartical = true && that.isSetCompanyNamePartical();
    if (this_present_companyNamePartical || that_present_companyNamePartical) {
      if (!(this_present_companyNamePartical && that_present_companyNamePartical))
        return false;
      if (!this.companyNamePartical.equals(that.companyNamePartical))
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

    boolean this_present_maintenanceTimeSpan = true && this.isSetMaintenanceTimeSpan();
    boolean that_present_maintenanceTimeSpan = true && that.isSetMaintenanceTimeSpan();
    if (this_present_maintenanceTimeSpan || that_present_maintenanceTimeSpan) {
      if (!(this_present_maintenanceTimeSpan && that_present_maintenanceTimeSpan))
        return false;
      if (!this.maintenanceTimeSpan.equals(that.maintenanceTimeSpan))
        return false;
    }

    boolean this_present_maintenanceState = true && this.isSetMaintenanceState();
    boolean that_present_maintenanceState = true && that.isSetMaintenanceState();
    if (this_present_maintenanceState || that_present_maintenanceState) {
      if (!(this_present_maintenanceState && that_present_maintenanceState))
        return false;
      if (!this.maintenanceState.equals(that.maintenanceState))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  @Override
  public int compareTo(CompanyServiceMaintenanceFilter other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetCompanyId()).compareTo(other.isSetCompanyId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCompanyId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.companyId, other.companyId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetCompanyNamePartical()).compareTo(other.isSetCompanyNamePartical());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCompanyNamePartical()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.companyNamePartical, other.companyNamePartical);
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
    lastComparison = Boolean.valueOf(isSetMaintenanceTimeSpan()).compareTo(other.isSetMaintenanceTimeSpan());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetMaintenanceTimeSpan()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.maintenanceTimeSpan, other.maintenanceTimeSpan);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetMaintenanceState()).compareTo(other.isSetMaintenanceState());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetMaintenanceState()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.maintenanceState, other.maintenanceState);
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
    StringBuilder sb = new StringBuilder("CompanyServiceMaintenanceFilter(");
    boolean first = true;

    if (isSetCompanyId()) {
      sb.append("companyId:");
      sb.append(this.companyId);
      first = false;
    }
    if (isSetCompanyNamePartical()) {
      if (!first) sb.append(", ");
      sb.append("companyNamePartical:");
      if (this.companyNamePartical == null) {
        sb.append("null");
      } else {
        sb.append(this.companyNamePartical);
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
    if (isSetMaintenanceTimeSpan()) {
      if (!first) sb.append(", ");
      sb.append("maintenanceTimeSpan:");
      if (this.maintenanceTimeSpan == null) {
        sb.append("null");
      } else {
        sb.append(this.maintenanceTimeSpan);
      }
      first = false;
    }
    if (isSetMaintenanceState()) {
      if (!first) sb.append(", ");
      sb.append("maintenanceState:");
      if (this.maintenanceState == null) {
        sb.append("null");
      } else {
        sb.append(this.maintenanceState);
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

  private static class CompanyServiceMaintenanceFilterStandardSchemeFactory implements SchemeFactory {
    public CompanyServiceMaintenanceFilterStandardScheme getScheme() {
      return new CompanyServiceMaintenanceFilterStandardScheme();
    }
  }

  private static class CompanyServiceMaintenanceFilterStandardScheme extends StandardScheme<CompanyServiceMaintenanceFilter> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, CompanyServiceMaintenanceFilter struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // COMPANY_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.companyId = iprot.readI64();
              struct.setCompanyIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // COMPANY_NAME_PARTICAL
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.companyNamePartical = iprot.readString();
              struct.setCompanyNameParticalIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // VERSION_TAG
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.versionTag = iprot.readString();
              struct.setVersionTagIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // MAINTENANCE_TIME_SPAN
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.maintenanceTimeSpan = xueqiao.company.service.maintenance.MaintenanceTimeSpan.findByValue(iprot.readI32());
              struct.setMaintenanceTimeSpanIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // MAINTENANCE_STATE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.maintenanceState = xueqiao.company.service.maintenance.MaintenanceState.findByValue(iprot.readI32());
              struct.setMaintenanceStateIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, CompanyServiceMaintenanceFilter struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.isSetCompanyId()) {
        oprot.writeFieldBegin(COMPANY_ID_FIELD_DESC);
        oprot.writeI64(struct.companyId);
        oprot.writeFieldEnd();
      }
      if (struct.companyNamePartical != null) {
        if (struct.isSetCompanyNamePartical()) {
          oprot.writeFieldBegin(COMPANY_NAME_PARTICAL_FIELD_DESC);
          oprot.writeString(struct.companyNamePartical);
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
      if (struct.maintenanceTimeSpan != null) {
        if (struct.isSetMaintenanceTimeSpan()) {
          oprot.writeFieldBegin(MAINTENANCE_TIME_SPAN_FIELD_DESC);
          oprot.writeI32(struct.maintenanceTimeSpan.getValue());
          oprot.writeFieldEnd();
        }
      }
      if (struct.maintenanceState != null) {
        if (struct.isSetMaintenanceState()) {
          oprot.writeFieldBegin(MAINTENANCE_STATE_FIELD_DESC);
          oprot.writeI32(struct.maintenanceState.getValue());
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class CompanyServiceMaintenanceFilterTupleSchemeFactory implements SchemeFactory {
    public CompanyServiceMaintenanceFilterTupleScheme getScheme() {
      return new CompanyServiceMaintenanceFilterTupleScheme();
    }
  }

  private static class CompanyServiceMaintenanceFilterTupleScheme extends TupleScheme<CompanyServiceMaintenanceFilter> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, CompanyServiceMaintenanceFilter struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetCompanyId()) {
        optionals.set(0);
      }
      if (struct.isSetCompanyNamePartical()) {
        optionals.set(1);
      }
      if (struct.isSetVersionTag()) {
        optionals.set(2);
      }
      if (struct.isSetMaintenanceTimeSpan()) {
        optionals.set(3);
      }
      if (struct.isSetMaintenanceState()) {
        optionals.set(4);
      }
      oprot.writeBitSet(optionals, 5);
      if (struct.isSetCompanyId()) {
        oprot.writeI64(struct.companyId);
      }
      if (struct.isSetCompanyNamePartical()) {
        oprot.writeString(struct.companyNamePartical);
      }
      if (struct.isSetVersionTag()) {
        oprot.writeString(struct.versionTag);
      }
      if (struct.isSetMaintenanceTimeSpan()) {
        oprot.writeI32(struct.maintenanceTimeSpan.getValue());
      }
      if (struct.isSetMaintenanceState()) {
        oprot.writeI32(struct.maintenanceState.getValue());
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, CompanyServiceMaintenanceFilter struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(5);
      if (incoming.get(0)) {
        struct.companyId = iprot.readI64();
        struct.setCompanyIdIsSet(true);
      }
      if (incoming.get(1)) {
        struct.companyNamePartical = iprot.readString();
        struct.setCompanyNameParticalIsSet(true);
      }
      if (incoming.get(2)) {
        struct.versionTag = iprot.readString();
        struct.setVersionTagIsSet(true);
      }
      if (incoming.get(3)) {
        struct.maintenanceTimeSpan = xueqiao.company.service.maintenance.MaintenanceTimeSpan.findByValue(iprot.readI32());
        struct.setMaintenanceTimeSpanIsSet(true);
      }
      if (incoming.get(4)) {
        struct.maintenanceState = xueqiao.company.service.maintenance.MaintenanceState.findByValue(iprot.readI32());
        struct.setMaintenanceStateIsSet(true);
      }
    }
  }

}
