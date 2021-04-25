/**
 * Autogenerated by Thrift Compiler (0.9.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.longsheng.xueqiao.payment.balance.alteration;

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

public class ReqBalanceAlterationOption implements org.apache.thrift.TBase<ReqBalanceAlterationOption, ReqBalanceAlterationOption._Fields>, java.io.Serializable, Cloneable, Comparable<ReqBalanceAlterationOption> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("ReqBalanceAlterationOption");

  private static final org.apache.thrift.protocol.TField ALTERATION_IDS_FIELD_DESC = new org.apache.thrift.protocol.TField("alterationIds", org.apache.thrift.protocol.TType.SET, (short)1);
  private static final org.apache.thrift.protocol.TField COMPANY_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("companyId", org.apache.thrift.protocol.TType.I64, (short)2);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new ReqBalanceAlterationOptionStandardSchemeFactory());
    schemes.put(TupleScheme.class, new ReqBalanceAlterationOptionTupleSchemeFactory());
  }

  public Set<Long> alterationIds; // optional
  public long companyId; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ALTERATION_IDS((short)1, "alterationIds"),
    COMPANY_ID((short)2, "companyId");

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
        case 1: // ALTERATION_IDS
          return ALTERATION_IDS;
        case 2: // COMPANY_ID
          return COMPANY_ID;
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
  private _Fields optionals[] = {_Fields.ALTERATION_IDS,_Fields.COMPANY_ID};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ALTERATION_IDS, new org.apache.thrift.meta_data.FieldMetaData("alterationIds", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.SetMetaData(org.apache.thrift.protocol.TType.SET, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64))));
    tmpMap.put(_Fields.COMPANY_ID, new org.apache.thrift.meta_data.FieldMetaData("companyId", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(ReqBalanceAlterationOption.class, metaDataMap);
  }

  public ReqBalanceAlterationOption() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public ReqBalanceAlterationOption(ReqBalanceAlterationOption other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetAlterationIds()) {
      Set<Long> __this__alterationIds = new HashSet<Long>(other.alterationIds);
      this.alterationIds = __this__alterationIds;
    }
    this.companyId = other.companyId;
  }

  public ReqBalanceAlterationOption deepCopy() {
    return new ReqBalanceAlterationOption(this);
  }

  @Override
  public void clear() {
    this.alterationIds = null;
    setCompanyIdIsSet(false);
    this.companyId = 0;
  }

  public int getAlterationIdsSize() {
    return (this.alterationIds == null) ? 0 : this.alterationIds.size();
  }

  public java.util.Iterator<Long> getAlterationIdsIterator() {
    return (this.alterationIds == null) ? null : this.alterationIds.iterator();
  }

  public void addToAlterationIds(long elem) {
    if (this.alterationIds == null) {
      this.alterationIds = new HashSet<Long>();
    }
    this.alterationIds.add(elem);
  }

  public Set<Long> getAlterationIds() {
    return this.alterationIds;
  }

  public ReqBalanceAlterationOption setAlterationIds(Set<Long> alterationIds) {
    this.alterationIds = alterationIds;
    return this;
  }

  public void unsetAlterationIds() {
    this.alterationIds = null;
  }

  /** Returns true if field alterationIds is set (has been assigned a value) and false otherwise */
  public boolean isSetAlterationIds() {
    return this.alterationIds != null;
  }

  public void setAlterationIdsIsSet(boolean value) {
    if (!value) {
      this.alterationIds = null;
    }
  }

  public long getCompanyId() {
    return this.companyId;
  }

  public ReqBalanceAlterationOption setCompanyId(long companyId) {
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

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case ALTERATION_IDS:
      if (value == null) {
        unsetAlterationIds();
      } else {
        setAlterationIds((Set<Long>)value);
      }
      break;

    case COMPANY_ID:
      if (value == null) {
        unsetCompanyId();
      } else {
        setCompanyId((Long)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case ALTERATION_IDS:
      return getAlterationIds();

    case COMPANY_ID:
      return Long.valueOf(getCompanyId());

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case ALTERATION_IDS:
      return isSetAlterationIds();
    case COMPANY_ID:
      return isSetCompanyId();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof ReqBalanceAlterationOption)
      return this.equals((ReqBalanceAlterationOption)that);
    return false;
  }

  public boolean equals(ReqBalanceAlterationOption that) {
    if (that == null)
      return false;

    boolean this_present_alterationIds = true && this.isSetAlterationIds();
    boolean that_present_alterationIds = true && that.isSetAlterationIds();
    if (this_present_alterationIds || that_present_alterationIds) {
      if (!(this_present_alterationIds && that_present_alterationIds))
        return false;
      if (!this.alterationIds.equals(that.alterationIds))
        return false;
    }

    boolean this_present_companyId = true && this.isSetCompanyId();
    boolean that_present_companyId = true && that.isSetCompanyId();
    if (this_present_companyId || that_present_companyId) {
      if (!(this_present_companyId && that_present_companyId))
        return false;
      if (this.companyId != that.companyId)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  @Override
  public int compareTo(ReqBalanceAlterationOption other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetAlterationIds()).compareTo(other.isSetAlterationIds());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAlterationIds()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.alterationIds, other.alterationIds);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
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
    StringBuilder sb = new StringBuilder("ReqBalanceAlterationOption(");
    boolean first = true;

    if (isSetAlterationIds()) {
      sb.append("alterationIds:");
      if (this.alterationIds == null) {
        sb.append("null");
      } else {
        sb.append(this.alterationIds);
      }
      first = false;
    }
    if (isSetCompanyId()) {
      if (!first) sb.append(", ");
      sb.append("companyId:");
      sb.append(this.companyId);
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

  private static class ReqBalanceAlterationOptionStandardSchemeFactory implements SchemeFactory {
    public ReqBalanceAlterationOptionStandardScheme getScheme() {
      return new ReqBalanceAlterationOptionStandardScheme();
    }
  }

  private static class ReqBalanceAlterationOptionStandardScheme extends StandardScheme<ReqBalanceAlterationOption> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, ReqBalanceAlterationOption struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // ALTERATION_IDS
            if (schemeField.type == org.apache.thrift.protocol.TType.SET) {
              {
                org.apache.thrift.protocol.TSet _set8 = iprot.readSetBegin();
                struct.alterationIds = new HashSet<Long>(2*_set8.size);
                for (int _i9 = 0; _i9 < _set8.size; ++_i9)
                {
                  long _elem10;
                  _elem10 = iprot.readI64();
                  struct.alterationIds.add(_elem10);
                }
                iprot.readSetEnd();
              }
              struct.setAlterationIdsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // COMPANY_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.companyId = iprot.readI64();
              struct.setCompanyIdIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, ReqBalanceAlterationOption struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.alterationIds != null) {
        if (struct.isSetAlterationIds()) {
          oprot.writeFieldBegin(ALTERATION_IDS_FIELD_DESC);
          {
            oprot.writeSetBegin(new org.apache.thrift.protocol.TSet(org.apache.thrift.protocol.TType.I64, struct.alterationIds.size()));
            for (long _iter11 : struct.alterationIds)
            {
              oprot.writeI64(_iter11);
            }
            oprot.writeSetEnd();
          }
          oprot.writeFieldEnd();
        }
      }
      if (struct.isSetCompanyId()) {
        oprot.writeFieldBegin(COMPANY_ID_FIELD_DESC);
        oprot.writeI64(struct.companyId);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class ReqBalanceAlterationOptionTupleSchemeFactory implements SchemeFactory {
    public ReqBalanceAlterationOptionTupleScheme getScheme() {
      return new ReqBalanceAlterationOptionTupleScheme();
    }
  }

  private static class ReqBalanceAlterationOptionTupleScheme extends TupleScheme<ReqBalanceAlterationOption> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, ReqBalanceAlterationOption struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetAlterationIds()) {
        optionals.set(0);
      }
      if (struct.isSetCompanyId()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetAlterationIds()) {
        {
          oprot.writeI32(struct.alterationIds.size());
          for (long _iter12 : struct.alterationIds)
          {
            oprot.writeI64(_iter12);
          }
        }
      }
      if (struct.isSetCompanyId()) {
        oprot.writeI64(struct.companyId);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, ReqBalanceAlterationOption struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        {
          org.apache.thrift.protocol.TSet _set13 = new org.apache.thrift.protocol.TSet(org.apache.thrift.protocol.TType.I64, iprot.readI32());
          struct.alterationIds = new HashSet<Long>(2*_set13.size);
          for (int _i14 = 0; _i14 < _set13.size; ++_i14)
          {
            long _elem15;
            _elem15 = iprot.readI64();
            struct.alterationIds.add(_elem15);
          }
        }
        struct.setAlterationIdsIsSet(true);
      }
      if (incoming.get(1)) {
        struct.companyId = iprot.readI64();
        struct.setCompanyIdIsSet(true);
      }
    }
  }

}
