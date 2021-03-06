/**
 * Autogenerated by Thrift Compiler (0.9.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.longsheng.xueqiao.hawk.thriftapi;

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

public class CtpAccount implements org.apache.thrift.TBase<CtpAccount, CtpAccount._Fields>, java.io.Serializable, Cloneable, Comparable<CtpAccount> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("CtpAccount");

  private static final org.apache.thrift.protocol.TField ID_FIELD_DESC = new org.apache.thrift.protocol.TField("id", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField HOST_FIELD_DESC = new org.apache.thrift.protocol.TField("host", org.apache.thrift.protocol.TType.LIST, (short)2);
  private static final org.apache.thrift.protocol.TField BROKER_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("brokerId", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField ACCOUNT_FIELD_DESC = new org.apache.thrift.protocol.TField("account", org.apache.thrift.protocol.TType.STRING, (short)4);
  private static final org.apache.thrift.protocol.TField ACTIVE_FIELD_DESC = new org.apache.thrift.protocol.TField("active", org.apache.thrift.protocol.TType.BOOL, (short)6);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new CtpAccountStandardSchemeFactory());
    schemes.put(TupleScheme.class, new CtpAccountTupleSchemeFactory());
  }

  public int id; // optional
  public List<String> host; // optional
  public String brokerId; // optional
  public String account; // optional
  public boolean active; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ID((short)1, "id"),
    HOST((short)2, "host"),
    BROKER_ID((short)3, "brokerId"),
    ACCOUNT((short)4, "account"),
    ACTIVE((short)6, "active");

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
        case 1: // ID
          return ID;
        case 2: // HOST
          return HOST;
        case 3: // BROKER_ID
          return BROKER_ID;
        case 4: // ACCOUNT
          return ACCOUNT;
        case 6: // ACTIVE
          return ACTIVE;
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
  private static final int __ID_ISSET_ID = 0;
  private static final int __ACTIVE_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  private _Fields optionals[] = {_Fields.ID,_Fields.HOST,_Fields.BROKER_ID,_Fields.ACCOUNT,_Fields.ACTIVE};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ID, new org.apache.thrift.meta_data.FieldMetaData("id", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.HOST, new org.apache.thrift.meta_data.FieldMetaData("host", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING))));
    tmpMap.put(_Fields.BROKER_ID, new org.apache.thrift.meta_data.FieldMetaData("brokerId", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.ACCOUNT, new org.apache.thrift.meta_data.FieldMetaData("account", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.ACTIVE, new org.apache.thrift.meta_data.FieldMetaData("active", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(CtpAccount.class, metaDataMap);
  }

  public CtpAccount() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public CtpAccount(CtpAccount other) {
    __isset_bitfield = other.__isset_bitfield;
    this.id = other.id;
    if (other.isSetHost()) {
      List<String> __this__host = new ArrayList<String>(other.host);
      this.host = __this__host;
    }
    if (other.isSetBrokerId()) {
      this.brokerId = other.brokerId;
    }
    if (other.isSetAccount()) {
      this.account = other.account;
    }
    this.active = other.active;
  }

  public CtpAccount deepCopy() {
    return new CtpAccount(this);
  }

  @Override
  public void clear() {
    setIdIsSet(false);
    this.id = 0;
    this.host = null;
    this.brokerId = null;
    this.account = null;
    setActiveIsSet(false);
    this.active = false;
  }

  public int getId() {
    return this.id;
  }

  public CtpAccount setId(int id) {
    this.id = id;
    setIdIsSet(true);
    return this;
  }

  public void unsetId() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __ID_ISSET_ID);
  }

  /** Returns true if field id is set (has been assigned a value) and false otherwise */
  public boolean isSetId() {
    return EncodingUtils.testBit(__isset_bitfield, __ID_ISSET_ID);
  }

  public void setIdIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __ID_ISSET_ID, value);
  }

  public int getHostSize() {
    return (this.host == null) ? 0 : this.host.size();
  }

  public java.util.Iterator<String> getHostIterator() {
    return (this.host == null) ? null : this.host.iterator();
  }

  public void addToHost(String elem) {
    if (this.host == null) {
      this.host = new ArrayList<String>();
    }
    this.host.add(elem);
  }

  public List<String> getHost() {
    return this.host;
  }

  public CtpAccount setHost(List<String> host) {
    this.host = host;
    return this;
  }

  public void unsetHost() {
    this.host = null;
  }

  /** Returns true if field host is set (has been assigned a value) and false otherwise */
  public boolean isSetHost() {
    return this.host != null;
  }

  public void setHostIsSet(boolean value) {
    if (!value) {
      this.host = null;
    }
  }

  public String getBrokerId() {
    return this.brokerId;
  }

  public CtpAccount setBrokerId(String brokerId) {
    this.brokerId = brokerId;
    return this;
  }

  public void unsetBrokerId() {
    this.brokerId = null;
  }

  /** Returns true if field brokerId is set (has been assigned a value) and false otherwise */
  public boolean isSetBrokerId() {
    return this.brokerId != null;
  }

  public void setBrokerIdIsSet(boolean value) {
    if (!value) {
      this.brokerId = null;
    }
  }

  public String getAccount() {
    return this.account;
  }

  public CtpAccount setAccount(String account) {
    this.account = account;
    return this;
  }

  public void unsetAccount() {
    this.account = null;
  }

  /** Returns true if field account is set (has been assigned a value) and false otherwise */
  public boolean isSetAccount() {
    return this.account != null;
  }

  public void setAccountIsSet(boolean value) {
    if (!value) {
      this.account = null;
    }
  }

  public boolean isActive() {
    return this.active;
  }

  public CtpAccount setActive(boolean active) {
    this.active = active;
    setActiveIsSet(true);
    return this;
  }

  public void unsetActive() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __ACTIVE_ISSET_ID);
  }

  /** Returns true if field active is set (has been assigned a value) and false otherwise */
  public boolean isSetActive() {
    return EncodingUtils.testBit(__isset_bitfield, __ACTIVE_ISSET_ID);
  }

  public void setActiveIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __ACTIVE_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case ID:
      if (value == null) {
        unsetId();
      } else {
        setId((Integer)value);
      }
      break;

    case HOST:
      if (value == null) {
        unsetHost();
      } else {
        setHost((List<String>)value);
      }
      break;

    case BROKER_ID:
      if (value == null) {
        unsetBrokerId();
      } else {
        setBrokerId((String)value);
      }
      break;

    case ACCOUNT:
      if (value == null) {
        unsetAccount();
      } else {
        setAccount((String)value);
      }
      break;

    case ACTIVE:
      if (value == null) {
        unsetActive();
      } else {
        setActive((Boolean)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case ID:
      return Integer.valueOf(getId());

    case HOST:
      return getHost();

    case BROKER_ID:
      return getBrokerId();

    case ACCOUNT:
      return getAccount();

    case ACTIVE:
      return Boolean.valueOf(isActive());

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case ID:
      return isSetId();
    case HOST:
      return isSetHost();
    case BROKER_ID:
      return isSetBrokerId();
    case ACCOUNT:
      return isSetAccount();
    case ACTIVE:
      return isSetActive();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof CtpAccount)
      return this.equals((CtpAccount)that);
    return false;
  }

  public boolean equals(CtpAccount that) {
    if (that == null)
      return false;

    boolean this_present_id = true && this.isSetId();
    boolean that_present_id = true && that.isSetId();
    if (this_present_id || that_present_id) {
      if (!(this_present_id && that_present_id))
        return false;
      if (this.id != that.id)
        return false;
    }

    boolean this_present_host = true && this.isSetHost();
    boolean that_present_host = true && that.isSetHost();
    if (this_present_host || that_present_host) {
      if (!(this_present_host && that_present_host))
        return false;
      if (!this.host.equals(that.host))
        return false;
    }

    boolean this_present_brokerId = true && this.isSetBrokerId();
    boolean that_present_brokerId = true && that.isSetBrokerId();
    if (this_present_brokerId || that_present_brokerId) {
      if (!(this_present_brokerId && that_present_brokerId))
        return false;
      if (!this.brokerId.equals(that.brokerId))
        return false;
    }

    boolean this_present_account = true && this.isSetAccount();
    boolean that_present_account = true && that.isSetAccount();
    if (this_present_account || that_present_account) {
      if (!(this_present_account && that_present_account))
        return false;
      if (!this.account.equals(that.account))
        return false;
    }

    boolean this_present_active = true && this.isSetActive();
    boolean that_present_active = true && that.isSetActive();
    if (this_present_active || that_present_active) {
      if (!(this_present_active && that_present_active))
        return false;
      if (this.active != that.active)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  @Override
  public int compareTo(CtpAccount other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetId()).compareTo(other.isSetId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.id, other.id);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetHost()).compareTo(other.isSetHost());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetHost()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.host, other.host);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetBrokerId()).compareTo(other.isSetBrokerId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetBrokerId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.brokerId, other.brokerId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetAccount()).compareTo(other.isSetAccount());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAccount()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.account, other.account);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetActive()).compareTo(other.isSetActive());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetActive()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.active, other.active);
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
    StringBuilder sb = new StringBuilder("CtpAccount(");
    boolean first = true;

    if (isSetId()) {
      sb.append("id:");
      sb.append(this.id);
      first = false;
    }
    if (isSetHost()) {
      if (!first) sb.append(", ");
      sb.append("host:");
      if (this.host == null) {
        sb.append("null");
      } else {
        sb.append(this.host);
      }
      first = false;
    }
    if (isSetBrokerId()) {
      if (!first) sb.append(", ");
      sb.append("brokerId:");
      if (this.brokerId == null) {
        sb.append("null");
      } else {
        sb.append(this.brokerId);
      }
      first = false;
    }
    if (isSetAccount()) {
      if (!first) sb.append(", ");
      sb.append("account:");
      if (this.account == null) {
        sb.append("null");
      } else {
        sb.append(this.account);
      }
      first = false;
    }
    if (isSetActive()) {
      if (!first) sb.append(", ");
      sb.append("active:");
      sb.append(this.active);
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

  private static class CtpAccountStandardSchemeFactory implements SchemeFactory {
    public CtpAccountStandardScheme getScheme() {
      return new CtpAccountStandardScheme();
    }
  }

  private static class CtpAccountStandardScheme extends StandardScheme<CtpAccount> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, CtpAccount struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.id = iprot.readI32();
              struct.setIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // HOST
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list40 = iprot.readListBegin();
                struct.host = new ArrayList<String>(_list40.size);
                for (int _i41 = 0; _i41 < _list40.size; ++_i41)
                {
                  String _elem42;
                  _elem42 = iprot.readString();
                  struct.host.add(_elem42);
                }
                iprot.readListEnd();
              }
              struct.setHostIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // BROKER_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.brokerId = iprot.readString();
              struct.setBrokerIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // ACCOUNT
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.account = iprot.readString();
              struct.setAccountIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // ACTIVE
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.active = iprot.readBool();
              struct.setActiveIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, CtpAccount struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.isSetId()) {
        oprot.writeFieldBegin(ID_FIELD_DESC);
        oprot.writeI32(struct.id);
        oprot.writeFieldEnd();
      }
      if (struct.host != null) {
        if (struct.isSetHost()) {
          oprot.writeFieldBegin(HOST_FIELD_DESC);
          {
            oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, struct.host.size()));
            for (String _iter43 : struct.host)
            {
              oprot.writeString(_iter43);
            }
            oprot.writeListEnd();
          }
          oprot.writeFieldEnd();
        }
      }
      if (struct.brokerId != null) {
        if (struct.isSetBrokerId()) {
          oprot.writeFieldBegin(BROKER_ID_FIELD_DESC);
          oprot.writeString(struct.brokerId);
          oprot.writeFieldEnd();
        }
      }
      if (struct.account != null) {
        if (struct.isSetAccount()) {
          oprot.writeFieldBegin(ACCOUNT_FIELD_DESC);
          oprot.writeString(struct.account);
          oprot.writeFieldEnd();
        }
      }
      if (struct.isSetActive()) {
        oprot.writeFieldBegin(ACTIVE_FIELD_DESC);
        oprot.writeBool(struct.active);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class CtpAccountTupleSchemeFactory implements SchemeFactory {
    public CtpAccountTupleScheme getScheme() {
      return new CtpAccountTupleScheme();
    }
  }

  private static class CtpAccountTupleScheme extends TupleScheme<CtpAccount> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, CtpAccount struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetId()) {
        optionals.set(0);
      }
      if (struct.isSetHost()) {
        optionals.set(1);
      }
      if (struct.isSetBrokerId()) {
        optionals.set(2);
      }
      if (struct.isSetAccount()) {
        optionals.set(3);
      }
      if (struct.isSetActive()) {
        optionals.set(4);
      }
      oprot.writeBitSet(optionals, 5);
      if (struct.isSetId()) {
        oprot.writeI32(struct.id);
      }
      if (struct.isSetHost()) {
        {
          oprot.writeI32(struct.host.size());
          for (String _iter44 : struct.host)
          {
            oprot.writeString(_iter44);
          }
        }
      }
      if (struct.isSetBrokerId()) {
        oprot.writeString(struct.brokerId);
      }
      if (struct.isSetAccount()) {
        oprot.writeString(struct.account);
      }
      if (struct.isSetActive()) {
        oprot.writeBool(struct.active);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, CtpAccount struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(5);
      if (incoming.get(0)) {
        struct.id = iprot.readI32();
        struct.setIdIsSet(true);
      }
      if (incoming.get(1)) {
        {
          org.apache.thrift.protocol.TList _list45 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, iprot.readI32());
          struct.host = new ArrayList<String>(_list45.size);
          for (int _i46 = 0; _i46 < _list45.size; ++_i46)
          {
            String _elem47;
            _elem47 = iprot.readString();
            struct.host.add(_elem47);
          }
        }
        struct.setHostIsSet(true);
      }
      if (incoming.get(2)) {
        struct.brokerId = iprot.readString();
        struct.setBrokerIdIsSet(true);
      }
      if (incoming.get(3)) {
        struct.account = iprot.readString();
        struct.setAccountIsSet(true);
      }
      if (incoming.get(4)) {
        struct.active = iprot.readBool();
        struct.setActiveIsSet(true);
      }
    }
  }

}

