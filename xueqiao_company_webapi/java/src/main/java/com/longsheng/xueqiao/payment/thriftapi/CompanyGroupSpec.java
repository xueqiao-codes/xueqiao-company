/**
 * Autogenerated by Thrift Compiler (0.9.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.longsheng.xueqiao.payment.thriftapi;

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

public class CompanyGroupSpec implements org.apache.thrift.TBase<CompanyGroupSpec, CompanyGroupSpec._Fields>, java.io.Serializable, Cloneable, Comparable<CompanyGroupSpec> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("CompanyGroupSpec");

  private static final org.apache.thrift.protocol.TField COMPANY_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("companyId", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField GROUP_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("groupId", org.apache.thrift.protocol.TType.I32, (short)2);
  private static final org.apache.thrift.protocol.TField EXPIRED_TIMESTAMP_FIELD_DESC = new org.apache.thrift.protocol.TField("expiredTimestamp", org.apache.thrift.protocol.TType.I64, (short)3);
  private static final org.apache.thrift.protocol.TField PRODUCT_TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("productType", org.apache.thrift.protocol.TType.I32, (short)4);
  private static final org.apache.thrift.protocol.TField CREATE_TIMESTAMP_FIELD_DESC = new org.apache.thrift.protocol.TField("createTimestamp", org.apache.thrift.protocol.TType.I64, (short)9);
  private static final org.apache.thrift.protocol.TField LAST_MODIFY_TIMESTAMP_FIELD_DESC = new org.apache.thrift.protocol.TField("lastModifyTimestamp", org.apache.thrift.protocol.TType.I64, (short)10);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new CompanyGroupSpecStandardSchemeFactory());
    schemes.put(TupleScheme.class, new CompanyGroupSpecTupleSchemeFactory());
  }

  public int companyId; // optional
  public int groupId; // optional
  public long expiredTimestamp; // optional
  /**
   * 
   * @see com.longsheng.xueqiao.payment.product.thriftapi.ProductType
   */
  public com.longsheng.xueqiao.payment.product.thriftapi.ProductType productType; // optional
  public long createTimestamp; // optional
  public long lastModifyTimestamp; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    COMPANY_ID((short)1, "companyId"),
    GROUP_ID((short)2, "groupId"),
    EXPIRED_TIMESTAMP((short)3, "expiredTimestamp"),
    /**
     * 
     * @see com.longsheng.xueqiao.payment.product.thriftapi.ProductType
     */
    PRODUCT_TYPE((short)4, "productType"),
    CREATE_TIMESTAMP((short)9, "createTimestamp"),
    LAST_MODIFY_TIMESTAMP((short)10, "lastModifyTimestamp");

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
        case 2: // GROUP_ID
          return GROUP_ID;
        case 3: // EXPIRED_TIMESTAMP
          return EXPIRED_TIMESTAMP;
        case 4: // PRODUCT_TYPE
          return PRODUCT_TYPE;
        case 9: // CREATE_TIMESTAMP
          return CREATE_TIMESTAMP;
        case 10: // LAST_MODIFY_TIMESTAMP
          return LAST_MODIFY_TIMESTAMP;
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
  private static final int __GROUPID_ISSET_ID = 1;
  private static final int __EXPIREDTIMESTAMP_ISSET_ID = 2;
  private static final int __CREATETIMESTAMP_ISSET_ID = 3;
  private static final int __LASTMODIFYTIMESTAMP_ISSET_ID = 4;
  private byte __isset_bitfield = 0;
  private _Fields optionals[] = {_Fields.COMPANY_ID,_Fields.GROUP_ID,_Fields.EXPIRED_TIMESTAMP,_Fields.PRODUCT_TYPE,_Fields.CREATE_TIMESTAMP,_Fields.LAST_MODIFY_TIMESTAMP};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.COMPANY_ID, new org.apache.thrift.meta_data.FieldMetaData("companyId", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.GROUP_ID, new org.apache.thrift.meta_data.FieldMetaData("groupId", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.EXPIRED_TIMESTAMP, new org.apache.thrift.meta_data.FieldMetaData("expiredTimestamp", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.PRODUCT_TYPE, new org.apache.thrift.meta_data.FieldMetaData("productType", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, com.longsheng.xueqiao.payment.product.thriftapi.ProductType.class)));
    tmpMap.put(_Fields.CREATE_TIMESTAMP, new org.apache.thrift.meta_data.FieldMetaData("createTimestamp", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.LAST_MODIFY_TIMESTAMP, new org.apache.thrift.meta_data.FieldMetaData("lastModifyTimestamp", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(CompanyGroupSpec.class, metaDataMap);
  }

  public CompanyGroupSpec() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public CompanyGroupSpec(CompanyGroupSpec other) {
    __isset_bitfield = other.__isset_bitfield;
    this.companyId = other.companyId;
    this.groupId = other.groupId;
    this.expiredTimestamp = other.expiredTimestamp;
    if (other.isSetProductType()) {
      this.productType = other.productType;
    }
    this.createTimestamp = other.createTimestamp;
    this.lastModifyTimestamp = other.lastModifyTimestamp;
  }

  public CompanyGroupSpec deepCopy() {
    return new CompanyGroupSpec(this);
  }

  @Override
  public void clear() {
    setCompanyIdIsSet(false);
    this.companyId = 0;
    setGroupIdIsSet(false);
    this.groupId = 0;
    setExpiredTimestampIsSet(false);
    this.expiredTimestamp = 0;
    this.productType = null;
    setCreateTimestampIsSet(false);
    this.createTimestamp = 0;
    setLastModifyTimestampIsSet(false);
    this.lastModifyTimestamp = 0;
  }

  public int getCompanyId() {
    return this.companyId;
  }

  public CompanyGroupSpec setCompanyId(int companyId) {
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

  public int getGroupId() {
    return this.groupId;
  }

  public CompanyGroupSpec setGroupId(int groupId) {
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

  public long getExpiredTimestamp() {
    return this.expiredTimestamp;
  }

  public CompanyGroupSpec setExpiredTimestamp(long expiredTimestamp) {
    this.expiredTimestamp = expiredTimestamp;
    setExpiredTimestampIsSet(true);
    return this;
  }

  public void unsetExpiredTimestamp() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __EXPIREDTIMESTAMP_ISSET_ID);
  }

  /** Returns true if field expiredTimestamp is set (has been assigned a value) and false otherwise */
  public boolean isSetExpiredTimestamp() {
    return EncodingUtils.testBit(__isset_bitfield, __EXPIREDTIMESTAMP_ISSET_ID);
  }

  public void setExpiredTimestampIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __EXPIREDTIMESTAMP_ISSET_ID, value);
  }

  /**
   * 
   * @see com.longsheng.xueqiao.payment.product.thriftapi.ProductType
   */
  public com.longsheng.xueqiao.payment.product.thriftapi.ProductType getProductType() {
    return this.productType;
  }

  /**
   * 
   * @see com.longsheng.xueqiao.payment.product.thriftapi.ProductType
   */
  public CompanyGroupSpec setProductType(com.longsheng.xueqiao.payment.product.thriftapi.ProductType productType) {
    this.productType = productType;
    return this;
  }

  public void unsetProductType() {
    this.productType = null;
  }

  /** Returns true if field productType is set (has been assigned a value) and false otherwise */
  public boolean isSetProductType() {
    return this.productType != null;
  }

  public void setProductTypeIsSet(boolean value) {
    if (!value) {
      this.productType = null;
    }
  }

  public long getCreateTimestamp() {
    return this.createTimestamp;
  }

  public CompanyGroupSpec setCreateTimestamp(long createTimestamp) {
    this.createTimestamp = createTimestamp;
    setCreateTimestampIsSet(true);
    return this;
  }

  public void unsetCreateTimestamp() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __CREATETIMESTAMP_ISSET_ID);
  }

  /** Returns true if field createTimestamp is set (has been assigned a value) and false otherwise */
  public boolean isSetCreateTimestamp() {
    return EncodingUtils.testBit(__isset_bitfield, __CREATETIMESTAMP_ISSET_ID);
  }

  public void setCreateTimestampIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __CREATETIMESTAMP_ISSET_ID, value);
  }

  public long getLastModifyTimestamp() {
    return this.lastModifyTimestamp;
  }

  public CompanyGroupSpec setLastModifyTimestamp(long lastModifyTimestamp) {
    this.lastModifyTimestamp = lastModifyTimestamp;
    setLastModifyTimestampIsSet(true);
    return this;
  }

  public void unsetLastModifyTimestamp() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __LASTMODIFYTIMESTAMP_ISSET_ID);
  }

  /** Returns true if field lastModifyTimestamp is set (has been assigned a value) and false otherwise */
  public boolean isSetLastModifyTimestamp() {
    return EncodingUtils.testBit(__isset_bitfield, __LASTMODIFYTIMESTAMP_ISSET_ID);
  }

  public void setLastModifyTimestampIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __LASTMODIFYTIMESTAMP_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case COMPANY_ID:
      if (value == null) {
        unsetCompanyId();
      } else {
        setCompanyId((Integer)value);
      }
      break;

    case GROUP_ID:
      if (value == null) {
        unsetGroupId();
      } else {
        setGroupId((Integer)value);
      }
      break;

    case EXPIRED_TIMESTAMP:
      if (value == null) {
        unsetExpiredTimestamp();
      } else {
        setExpiredTimestamp((Long)value);
      }
      break;

    case PRODUCT_TYPE:
      if (value == null) {
        unsetProductType();
      } else {
        setProductType((com.longsheng.xueqiao.payment.product.thriftapi.ProductType)value);
      }
      break;

    case CREATE_TIMESTAMP:
      if (value == null) {
        unsetCreateTimestamp();
      } else {
        setCreateTimestamp((Long)value);
      }
      break;

    case LAST_MODIFY_TIMESTAMP:
      if (value == null) {
        unsetLastModifyTimestamp();
      } else {
        setLastModifyTimestamp((Long)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case COMPANY_ID:
      return Integer.valueOf(getCompanyId());

    case GROUP_ID:
      return Integer.valueOf(getGroupId());

    case EXPIRED_TIMESTAMP:
      return Long.valueOf(getExpiredTimestamp());

    case PRODUCT_TYPE:
      return getProductType();

    case CREATE_TIMESTAMP:
      return Long.valueOf(getCreateTimestamp());

    case LAST_MODIFY_TIMESTAMP:
      return Long.valueOf(getLastModifyTimestamp());

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
    case GROUP_ID:
      return isSetGroupId();
    case EXPIRED_TIMESTAMP:
      return isSetExpiredTimestamp();
    case PRODUCT_TYPE:
      return isSetProductType();
    case CREATE_TIMESTAMP:
      return isSetCreateTimestamp();
    case LAST_MODIFY_TIMESTAMP:
      return isSetLastModifyTimestamp();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof CompanyGroupSpec)
      return this.equals((CompanyGroupSpec)that);
    return false;
  }

  public boolean equals(CompanyGroupSpec that) {
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

    boolean this_present_groupId = true && this.isSetGroupId();
    boolean that_present_groupId = true && that.isSetGroupId();
    if (this_present_groupId || that_present_groupId) {
      if (!(this_present_groupId && that_present_groupId))
        return false;
      if (this.groupId != that.groupId)
        return false;
    }

    boolean this_present_expiredTimestamp = true && this.isSetExpiredTimestamp();
    boolean that_present_expiredTimestamp = true && that.isSetExpiredTimestamp();
    if (this_present_expiredTimestamp || that_present_expiredTimestamp) {
      if (!(this_present_expiredTimestamp && that_present_expiredTimestamp))
        return false;
      if (this.expiredTimestamp != that.expiredTimestamp)
        return false;
    }

    boolean this_present_productType = true && this.isSetProductType();
    boolean that_present_productType = true && that.isSetProductType();
    if (this_present_productType || that_present_productType) {
      if (!(this_present_productType && that_present_productType))
        return false;
      if (!this.productType.equals(that.productType))
        return false;
    }

    boolean this_present_createTimestamp = true && this.isSetCreateTimestamp();
    boolean that_present_createTimestamp = true && that.isSetCreateTimestamp();
    if (this_present_createTimestamp || that_present_createTimestamp) {
      if (!(this_present_createTimestamp && that_present_createTimestamp))
        return false;
      if (this.createTimestamp != that.createTimestamp)
        return false;
    }

    boolean this_present_lastModifyTimestamp = true && this.isSetLastModifyTimestamp();
    boolean that_present_lastModifyTimestamp = true && that.isSetLastModifyTimestamp();
    if (this_present_lastModifyTimestamp || that_present_lastModifyTimestamp) {
      if (!(this_present_lastModifyTimestamp && that_present_lastModifyTimestamp))
        return false;
      if (this.lastModifyTimestamp != that.lastModifyTimestamp)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  @Override
  public int compareTo(CompanyGroupSpec other) {
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
    lastComparison = Boolean.valueOf(isSetExpiredTimestamp()).compareTo(other.isSetExpiredTimestamp());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetExpiredTimestamp()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.expiredTimestamp, other.expiredTimestamp);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetProductType()).compareTo(other.isSetProductType());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetProductType()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.productType, other.productType);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetCreateTimestamp()).compareTo(other.isSetCreateTimestamp());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCreateTimestamp()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.createTimestamp, other.createTimestamp);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetLastModifyTimestamp()).compareTo(other.isSetLastModifyTimestamp());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetLastModifyTimestamp()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.lastModifyTimestamp, other.lastModifyTimestamp);
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
    StringBuilder sb = new StringBuilder("CompanyGroupSpec(");
    boolean first = true;

    if (isSetCompanyId()) {
      sb.append("companyId:");
      sb.append(this.companyId);
      first = false;
    }
    if (isSetGroupId()) {
      if (!first) sb.append(", ");
      sb.append("groupId:");
      sb.append(this.groupId);
      first = false;
    }
    if (isSetExpiredTimestamp()) {
      if (!first) sb.append(", ");
      sb.append("expiredTimestamp:");
      sb.append(this.expiredTimestamp);
      first = false;
    }
    if (isSetProductType()) {
      if (!first) sb.append(", ");
      sb.append("productType:");
      if (this.productType == null) {
        sb.append("null");
      } else {
        sb.append(this.productType);
      }
      first = false;
    }
    if (isSetCreateTimestamp()) {
      if (!first) sb.append(", ");
      sb.append("createTimestamp:");
      sb.append(this.createTimestamp);
      first = false;
    }
    if (isSetLastModifyTimestamp()) {
      if (!first) sb.append(", ");
      sb.append("lastModifyTimestamp:");
      sb.append(this.lastModifyTimestamp);
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

  private static class CompanyGroupSpecStandardSchemeFactory implements SchemeFactory {
    public CompanyGroupSpecStandardScheme getScheme() {
      return new CompanyGroupSpecStandardScheme();
    }
  }

  private static class CompanyGroupSpecStandardScheme extends StandardScheme<CompanyGroupSpec> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, CompanyGroupSpec struct) throws org.apache.thrift.TException {
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
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.companyId = iprot.readI32();
              struct.setCompanyIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // GROUP_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.groupId = iprot.readI32();
              struct.setGroupIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // EXPIRED_TIMESTAMP
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.expiredTimestamp = iprot.readI64();
              struct.setExpiredTimestampIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // PRODUCT_TYPE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.productType = com.longsheng.xueqiao.payment.product.thriftapi.ProductType.findByValue(iprot.readI32());
              struct.setProductTypeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 9: // CREATE_TIMESTAMP
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.createTimestamp = iprot.readI64();
              struct.setCreateTimestampIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 10: // LAST_MODIFY_TIMESTAMP
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.lastModifyTimestamp = iprot.readI64();
              struct.setLastModifyTimestampIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, CompanyGroupSpec struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.isSetCompanyId()) {
        oprot.writeFieldBegin(COMPANY_ID_FIELD_DESC);
        oprot.writeI32(struct.companyId);
        oprot.writeFieldEnd();
      }
      if (struct.isSetGroupId()) {
        oprot.writeFieldBegin(GROUP_ID_FIELD_DESC);
        oprot.writeI32(struct.groupId);
        oprot.writeFieldEnd();
      }
      if (struct.isSetExpiredTimestamp()) {
        oprot.writeFieldBegin(EXPIRED_TIMESTAMP_FIELD_DESC);
        oprot.writeI64(struct.expiredTimestamp);
        oprot.writeFieldEnd();
      }
      if (struct.productType != null) {
        if (struct.isSetProductType()) {
          oprot.writeFieldBegin(PRODUCT_TYPE_FIELD_DESC);
          oprot.writeI32(struct.productType.getValue());
          oprot.writeFieldEnd();
        }
      }
      if (struct.isSetCreateTimestamp()) {
        oprot.writeFieldBegin(CREATE_TIMESTAMP_FIELD_DESC);
        oprot.writeI64(struct.createTimestamp);
        oprot.writeFieldEnd();
      }
      if (struct.isSetLastModifyTimestamp()) {
        oprot.writeFieldBegin(LAST_MODIFY_TIMESTAMP_FIELD_DESC);
        oprot.writeI64(struct.lastModifyTimestamp);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class CompanyGroupSpecTupleSchemeFactory implements SchemeFactory {
    public CompanyGroupSpecTupleScheme getScheme() {
      return new CompanyGroupSpecTupleScheme();
    }
  }

  private static class CompanyGroupSpecTupleScheme extends TupleScheme<CompanyGroupSpec> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, CompanyGroupSpec struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetCompanyId()) {
        optionals.set(0);
      }
      if (struct.isSetGroupId()) {
        optionals.set(1);
      }
      if (struct.isSetExpiredTimestamp()) {
        optionals.set(2);
      }
      if (struct.isSetProductType()) {
        optionals.set(3);
      }
      if (struct.isSetCreateTimestamp()) {
        optionals.set(4);
      }
      if (struct.isSetLastModifyTimestamp()) {
        optionals.set(5);
      }
      oprot.writeBitSet(optionals, 6);
      if (struct.isSetCompanyId()) {
        oprot.writeI32(struct.companyId);
      }
      if (struct.isSetGroupId()) {
        oprot.writeI32(struct.groupId);
      }
      if (struct.isSetExpiredTimestamp()) {
        oprot.writeI64(struct.expiredTimestamp);
      }
      if (struct.isSetProductType()) {
        oprot.writeI32(struct.productType.getValue());
      }
      if (struct.isSetCreateTimestamp()) {
        oprot.writeI64(struct.createTimestamp);
      }
      if (struct.isSetLastModifyTimestamp()) {
        oprot.writeI64(struct.lastModifyTimestamp);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, CompanyGroupSpec struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(6);
      if (incoming.get(0)) {
        struct.companyId = iprot.readI32();
        struct.setCompanyIdIsSet(true);
      }
      if (incoming.get(1)) {
        struct.groupId = iprot.readI32();
        struct.setGroupIdIsSet(true);
      }
      if (incoming.get(2)) {
        struct.expiredTimestamp = iprot.readI64();
        struct.setExpiredTimestampIsSet(true);
      }
      if (incoming.get(3)) {
        struct.productType = com.longsheng.xueqiao.payment.product.thriftapi.ProductType.findByValue(iprot.readI32());
        struct.setProductTypeIsSet(true);
      }
      if (incoming.get(4)) {
        struct.createTimestamp = iprot.readI64();
        struct.setCreateTimestampIsSet(true);
      }
      if (incoming.get(5)) {
        struct.lastModifyTimestamp = iprot.readI64();
        struct.setLastModifyTimestampIsSet(true);
      }
    }
  }

}

