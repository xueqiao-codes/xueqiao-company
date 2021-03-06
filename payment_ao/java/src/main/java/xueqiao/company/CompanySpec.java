/**
 * Autogenerated by Thrift Compiler (0.9.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package xueqiao.company;

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
 * 公司配置信息
 */
public class CompanySpec implements org.apache.thrift.TBase<CompanySpec, CompanySpec._Fields>, java.io.Serializable, Cloneable, Comparable<CompanySpec> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("CompanySpec");

  private static final org.apache.thrift.protocol.TField COMPANY_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("companyId", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField SUBSCRIBE_COMMODITY_IDS_FIELD_DESC = new org.apache.thrift.protocol.TField("subscribeCommodityIds", org.apache.thrift.protocol.TType.SET, (short)2);
  private static final org.apache.thrift.protocol.TField CREATE_TIMESTAMP_FIELD_DESC = new org.apache.thrift.protocol.TField("createTimestamp", org.apache.thrift.protocol.TType.I64, (short)10);
  private static final org.apache.thrift.protocol.TField LASTMODIFY_TIMESTAMP_FIELD_DESC = new org.apache.thrift.protocol.TField("lastmodifyTimestamp", org.apache.thrift.protocol.TType.I64, (short)11);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new CompanySpecStandardSchemeFactory());
    schemes.put(TupleScheme.class, new CompanySpecTupleSchemeFactory());
  }

  public int companyId; // optional
  public Set<Integer> subscribeCommodityIds; // optional
  public long createTimestamp; // optional
  public long lastmodifyTimestamp; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    COMPANY_ID((short)1, "companyId"),
    SUBSCRIBE_COMMODITY_IDS((short)2, "subscribeCommodityIds"),
    CREATE_TIMESTAMP((short)10, "createTimestamp"),
    LASTMODIFY_TIMESTAMP((short)11, "lastmodifyTimestamp");

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
        case 2: // SUBSCRIBE_COMMODITY_IDS
          return SUBSCRIBE_COMMODITY_IDS;
        case 10: // CREATE_TIMESTAMP
          return CREATE_TIMESTAMP;
        case 11: // LASTMODIFY_TIMESTAMP
          return LASTMODIFY_TIMESTAMP;
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
  private static final int __CREATETIMESTAMP_ISSET_ID = 1;
  private static final int __LASTMODIFYTIMESTAMP_ISSET_ID = 2;
  private byte __isset_bitfield = 0;
  private _Fields optionals[] = {_Fields.COMPANY_ID,_Fields.SUBSCRIBE_COMMODITY_IDS,_Fields.CREATE_TIMESTAMP,_Fields.LASTMODIFY_TIMESTAMP};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.COMPANY_ID, new org.apache.thrift.meta_data.FieldMetaData("companyId", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.SUBSCRIBE_COMMODITY_IDS, new org.apache.thrift.meta_data.FieldMetaData("subscribeCommodityIds", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.SetMetaData(org.apache.thrift.protocol.TType.SET, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32))));
    tmpMap.put(_Fields.CREATE_TIMESTAMP, new org.apache.thrift.meta_data.FieldMetaData("createTimestamp", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.LASTMODIFY_TIMESTAMP, new org.apache.thrift.meta_data.FieldMetaData("lastmodifyTimestamp", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(CompanySpec.class, metaDataMap);
  }

  public CompanySpec() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public CompanySpec(CompanySpec other) {
    __isset_bitfield = other.__isset_bitfield;
    this.companyId = other.companyId;
    if (other.isSetSubscribeCommodityIds()) {
      Set<Integer> __this__subscribeCommodityIds = new HashSet<Integer>(other.subscribeCommodityIds);
      this.subscribeCommodityIds = __this__subscribeCommodityIds;
    }
    this.createTimestamp = other.createTimestamp;
    this.lastmodifyTimestamp = other.lastmodifyTimestamp;
  }

  public CompanySpec deepCopy() {
    return new CompanySpec(this);
  }

  @Override
  public void clear() {
    setCompanyIdIsSet(false);
    this.companyId = 0;
    this.subscribeCommodityIds = null;
    setCreateTimestampIsSet(false);
    this.createTimestamp = 0;
    setLastmodifyTimestampIsSet(false);
    this.lastmodifyTimestamp = 0;
  }

  public int getCompanyId() {
    return this.companyId;
  }

  public CompanySpec setCompanyId(int companyId) {
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

  public int getSubscribeCommodityIdsSize() {
    return (this.subscribeCommodityIds == null) ? 0 : this.subscribeCommodityIds.size();
  }

  public java.util.Iterator<Integer> getSubscribeCommodityIdsIterator() {
    return (this.subscribeCommodityIds == null) ? null : this.subscribeCommodityIds.iterator();
  }

  public void addToSubscribeCommodityIds(int elem) {
    if (this.subscribeCommodityIds == null) {
      this.subscribeCommodityIds = new HashSet<Integer>();
    }
    this.subscribeCommodityIds.add(elem);
  }

  public Set<Integer> getSubscribeCommodityIds() {
    return this.subscribeCommodityIds;
  }

  public CompanySpec setSubscribeCommodityIds(Set<Integer> subscribeCommodityIds) {
    this.subscribeCommodityIds = subscribeCommodityIds;
    return this;
  }

  public void unsetSubscribeCommodityIds() {
    this.subscribeCommodityIds = null;
  }

  /** Returns true if field subscribeCommodityIds is set (has been assigned a value) and false otherwise */
  public boolean isSetSubscribeCommodityIds() {
    return this.subscribeCommodityIds != null;
  }

  public void setSubscribeCommodityIdsIsSet(boolean value) {
    if (!value) {
      this.subscribeCommodityIds = null;
    }
  }

  public long getCreateTimestamp() {
    return this.createTimestamp;
  }

  public CompanySpec setCreateTimestamp(long createTimestamp) {
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

  public long getLastmodifyTimestamp() {
    return this.lastmodifyTimestamp;
  }

  public CompanySpec setLastmodifyTimestamp(long lastmodifyTimestamp) {
    this.lastmodifyTimestamp = lastmodifyTimestamp;
    setLastmodifyTimestampIsSet(true);
    return this;
  }

  public void unsetLastmodifyTimestamp() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __LASTMODIFYTIMESTAMP_ISSET_ID);
  }

  /** Returns true if field lastmodifyTimestamp is set (has been assigned a value) and false otherwise */
  public boolean isSetLastmodifyTimestamp() {
    return EncodingUtils.testBit(__isset_bitfield, __LASTMODIFYTIMESTAMP_ISSET_ID);
  }

  public void setLastmodifyTimestampIsSet(boolean value) {
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

    case SUBSCRIBE_COMMODITY_IDS:
      if (value == null) {
        unsetSubscribeCommodityIds();
      } else {
        setSubscribeCommodityIds((Set<Integer>)value);
      }
      break;

    case CREATE_TIMESTAMP:
      if (value == null) {
        unsetCreateTimestamp();
      } else {
        setCreateTimestamp((Long)value);
      }
      break;

    case LASTMODIFY_TIMESTAMP:
      if (value == null) {
        unsetLastmodifyTimestamp();
      } else {
        setLastmodifyTimestamp((Long)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case COMPANY_ID:
      return Integer.valueOf(getCompanyId());

    case SUBSCRIBE_COMMODITY_IDS:
      return getSubscribeCommodityIds();

    case CREATE_TIMESTAMP:
      return Long.valueOf(getCreateTimestamp());

    case LASTMODIFY_TIMESTAMP:
      return Long.valueOf(getLastmodifyTimestamp());

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
    case SUBSCRIBE_COMMODITY_IDS:
      return isSetSubscribeCommodityIds();
    case CREATE_TIMESTAMP:
      return isSetCreateTimestamp();
    case LASTMODIFY_TIMESTAMP:
      return isSetLastmodifyTimestamp();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof CompanySpec)
      return this.equals((CompanySpec)that);
    return false;
  }

  public boolean equals(CompanySpec that) {
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

    boolean this_present_subscribeCommodityIds = true && this.isSetSubscribeCommodityIds();
    boolean that_present_subscribeCommodityIds = true && that.isSetSubscribeCommodityIds();
    if (this_present_subscribeCommodityIds || that_present_subscribeCommodityIds) {
      if (!(this_present_subscribeCommodityIds && that_present_subscribeCommodityIds))
        return false;
      if (!this.subscribeCommodityIds.equals(that.subscribeCommodityIds))
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

    boolean this_present_lastmodifyTimestamp = true && this.isSetLastmodifyTimestamp();
    boolean that_present_lastmodifyTimestamp = true && that.isSetLastmodifyTimestamp();
    if (this_present_lastmodifyTimestamp || that_present_lastmodifyTimestamp) {
      if (!(this_present_lastmodifyTimestamp && that_present_lastmodifyTimestamp))
        return false;
      if (this.lastmodifyTimestamp != that.lastmodifyTimestamp)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  @Override
  public int compareTo(CompanySpec other) {
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
    lastComparison = Boolean.valueOf(isSetSubscribeCommodityIds()).compareTo(other.isSetSubscribeCommodityIds());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSubscribeCommodityIds()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.subscribeCommodityIds, other.subscribeCommodityIds);
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
    lastComparison = Boolean.valueOf(isSetLastmodifyTimestamp()).compareTo(other.isSetLastmodifyTimestamp());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetLastmodifyTimestamp()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.lastmodifyTimestamp, other.lastmodifyTimestamp);
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
    StringBuilder sb = new StringBuilder("CompanySpec(");
    boolean first = true;

    if (isSetCompanyId()) {
      sb.append("companyId:");
      sb.append(this.companyId);
      first = false;
    }
    if (isSetSubscribeCommodityIds()) {
      if (!first) sb.append(", ");
      sb.append("subscribeCommodityIds:");
      if (this.subscribeCommodityIds == null) {
        sb.append("null");
      } else {
        sb.append(this.subscribeCommodityIds);
      }
      first = false;
    }
    if (isSetCreateTimestamp()) {
      if (!first) sb.append(", ");
      sb.append("createTimestamp:");
      sb.append(this.createTimestamp);
      first = false;
    }
    if (isSetLastmodifyTimestamp()) {
      if (!first) sb.append(", ");
      sb.append("lastmodifyTimestamp:");
      sb.append(this.lastmodifyTimestamp);
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

  private static class CompanySpecStandardSchemeFactory implements SchemeFactory {
    public CompanySpecStandardScheme getScheme() {
      return new CompanySpecStandardScheme();
    }
  }

  private static class CompanySpecStandardScheme extends StandardScheme<CompanySpec> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, CompanySpec struct) throws org.apache.thrift.TException {
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
          case 2: // SUBSCRIBE_COMMODITY_IDS
            if (schemeField.type == org.apache.thrift.protocol.TType.SET) {
              {
                org.apache.thrift.protocol.TSet _set8 = iprot.readSetBegin();
                struct.subscribeCommodityIds = new HashSet<Integer>(2*_set8.size);
                for (int _i9 = 0; _i9 < _set8.size; ++_i9)
                {
                  int _elem10;
                  _elem10 = iprot.readI32();
                  struct.subscribeCommodityIds.add(_elem10);
                }
                iprot.readSetEnd();
              }
              struct.setSubscribeCommodityIdsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 10: // CREATE_TIMESTAMP
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.createTimestamp = iprot.readI64();
              struct.setCreateTimestampIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 11: // LASTMODIFY_TIMESTAMP
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.lastmodifyTimestamp = iprot.readI64();
              struct.setLastmodifyTimestampIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, CompanySpec struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.isSetCompanyId()) {
        oprot.writeFieldBegin(COMPANY_ID_FIELD_DESC);
        oprot.writeI32(struct.companyId);
        oprot.writeFieldEnd();
      }
      if (struct.subscribeCommodityIds != null) {
        if (struct.isSetSubscribeCommodityIds()) {
          oprot.writeFieldBegin(SUBSCRIBE_COMMODITY_IDS_FIELD_DESC);
          {
            oprot.writeSetBegin(new org.apache.thrift.protocol.TSet(org.apache.thrift.protocol.TType.I32, struct.subscribeCommodityIds.size()));
            for (int _iter11 : struct.subscribeCommodityIds)
            {
              oprot.writeI32(_iter11);
            }
            oprot.writeSetEnd();
          }
          oprot.writeFieldEnd();
        }
      }
      if (struct.isSetCreateTimestamp()) {
        oprot.writeFieldBegin(CREATE_TIMESTAMP_FIELD_DESC);
        oprot.writeI64(struct.createTimestamp);
        oprot.writeFieldEnd();
      }
      if (struct.isSetLastmodifyTimestamp()) {
        oprot.writeFieldBegin(LASTMODIFY_TIMESTAMP_FIELD_DESC);
        oprot.writeI64(struct.lastmodifyTimestamp);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class CompanySpecTupleSchemeFactory implements SchemeFactory {
    public CompanySpecTupleScheme getScheme() {
      return new CompanySpecTupleScheme();
    }
  }

  private static class CompanySpecTupleScheme extends TupleScheme<CompanySpec> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, CompanySpec struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetCompanyId()) {
        optionals.set(0);
      }
      if (struct.isSetSubscribeCommodityIds()) {
        optionals.set(1);
      }
      if (struct.isSetCreateTimestamp()) {
        optionals.set(2);
      }
      if (struct.isSetLastmodifyTimestamp()) {
        optionals.set(3);
      }
      oprot.writeBitSet(optionals, 4);
      if (struct.isSetCompanyId()) {
        oprot.writeI32(struct.companyId);
      }
      if (struct.isSetSubscribeCommodityIds()) {
        {
          oprot.writeI32(struct.subscribeCommodityIds.size());
          for (int _iter12 : struct.subscribeCommodityIds)
          {
            oprot.writeI32(_iter12);
          }
        }
      }
      if (struct.isSetCreateTimestamp()) {
        oprot.writeI64(struct.createTimestamp);
      }
      if (struct.isSetLastmodifyTimestamp()) {
        oprot.writeI64(struct.lastmodifyTimestamp);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, CompanySpec struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(4);
      if (incoming.get(0)) {
        struct.companyId = iprot.readI32();
        struct.setCompanyIdIsSet(true);
      }
      if (incoming.get(1)) {
        {
          org.apache.thrift.protocol.TSet _set13 = new org.apache.thrift.protocol.TSet(org.apache.thrift.protocol.TType.I32, iprot.readI32());
          struct.subscribeCommodityIds = new HashSet<Integer>(2*_set13.size);
          for (int _i14 = 0; _i14 < _set13.size; ++_i14)
          {
            int _elem15;
            _elem15 = iprot.readI32();
            struct.subscribeCommodityIds.add(_elem15);
          }
        }
        struct.setSubscribeCommodityIdsIsSet(true);
      }
      if (incoming.get(2)) {
        struct.createTimestamp = iprot.readI64();
        struct.setCreateTimestampIsSet(true);
      }
      if (incoming.get(3)) {
        struct.lastmodifyTimestamp = iprot.readI64();
        struct.setLastmodifyTimestampIsSet(true);
      }
    }
  }

}

