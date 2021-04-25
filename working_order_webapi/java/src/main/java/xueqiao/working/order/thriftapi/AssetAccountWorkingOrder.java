/**
 * Autogenerated by Thrift Compiler (0.9.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package xueqiao.working.order.thriftapi;

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
 * 资金账户工单信息
 */
public class AssetAccountWorkingOrder implements org.apache.thrift.TBase<AssetAccountWorkingOrder, AssetAccountWorkingOrder._Fields>, java.io.Serializable, Cloneable, Comparable<AssetAccountWorkingOrder> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("AssetAccountWorkingOrder");

  private static final org.apache.thrift.protocol.TField WORKING_ORDER_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("workingOrderId", org.apache.thrift.protocol.TType.I64, (short)1);
  private static final org.apache.thrift.protocol.TField BASE_WORKING_ORDER_FIELD_DESC = new org.apache.thrift.protocol.TField("baseWorkingOrder", org.apache.thrift.protocol.TType.STRUCT, (short)2);
  private static final org.apache.thrift.protocol.TField ACCOUNT_FIELD_DESC = new org.apache.thrift.protocol.TField("account", org.apache.thrift.protocol.TType.STRUCT, (short)3);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new AssetAccountWorkingOrderStandardSchemeFactory());
    schemes.put(TupleScheme.class, new AssetAccountWorkingOrderTupleSchemeFactory());
  }

  public long workingOrderId; // optional
  public BaseWorkingOrder baseWorkingOrder; // optional
  public AssetAccount account; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    WORKING_ORDER_ID((short)1, "workingOrderId"),
    BASE_WORKING_ORDER((short)2, "baseWorkingOrder"),
    ACCOUNT((short)3, "account");

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
        case 1: // WORKING_ORDER_ID
          return WORKING_ORDER_ID;
        case 2: // BASE_WORKING_ORDER
          return BASE_WORKING_ORDER;
        case 3: // ACCOUNT
          return ACCOUNT;
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
  private static final int __WORKINGORDERID_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  private _Fields optionals[] = {_Fields.WORKING_ORDER_ID,_Fields.BASE_WORKING_ORDER,_Fields.ACCOUNT};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.WORKING_ORDER_ID, new org.apache.thrift.meta_data.FieldMetaData("workingOrderId", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.BASE_WORKING_ORDER, new org.apache.thrift.meta_data.FieldMetaData("baseWorkingOrder", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, BaseWorkingOrder.class)));
    tmpMap.put(_Fields.ACCOUNT, new org.apache.thrift.meta_data.FieldMetaData("account", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, AssetAccount.class)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(AssetAccountWorkingOrder.class, metaDataMap);
  }

  public AssetAccountWorkingOrder() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public AssetAccountWorkingOrder(AssetAccountWorkingOrder other) {
    __isset_bitfield = other.__isset_bitfield;
    this.workingOrderId = other.workingOrderId;
    if (other.isSetBaseWorkingOrder()) {
      this.baseWorkingOrder = new BaseWorkingOrder(other.baseWorkingOrder);
    }
    if (other.isSetAccount()) {
      this.account = new AssetAccount(other.account);
    }
  }

  public AssetAccountWorkingOrder deepCopy() {
    return new AssetAccountWorkingOrder(this);
  }

  @Override
  public void clear() {
    setWorkingOrderIdIsSet(false);
    this.workingOrderId = 0;
    this.baseWorkingOrder = null;
    this.account = null;
  }

  public long getWorkingOrderId() {
    return this.workingOrderId;
  }

  public AssetAccountWorkingOrder setWorkingOrderId(long workingOrderId) {
    this.workingOrderId = workingOrderId;
    setWorkingOrderIdIsSet(true);
    return this;
  }

  public void unsetWorkingOrderId() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __WORKINGORDERID_ISSET_ID);
  }

  /** Returns true if field workingOrderId is set (has been assigned a value) and false otherwise */
  public boolean isSetWorkingOrderId() {
    return EncodingUtils.testBit(__isset_bitfield, __WORKINGORDERID_ISSET_ID);
  }

  public void setWorkingOrderIdIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __WORKINGORDERID_ISSET_ID, value);
  }

  public BaseWorkingOrder getBaseWorkingOrder() {
    return this.baseWorkingOrder;
  }

  public AssetAccountWorkingOrder setBaseWorkingOrder(BaseWorkingOrder baseWorkingOrder) {
    this.baseWorkingOrder = baseWorkingOrder;
    return this;
  }

  public void unsetBaseWorkingOrder() {
    this.baseWorkingOrder = null;
  }

  /** Returns true if field baseWorkingOrder is set (has been assigned a value) and false otherwise */
  public boolean isSetBaseWorkingOrder() {
    return this.baseWorkingOrder != null;
  }

  public void setBaseWorkingOrderIsSet(boolean value) {
    if (!value) {
      this.baseWorkingOrder = null;
    }
  }

  public AssetAccount getAccount() {
    return this.account;
  }

  public AssetAccountWorkingOrder setAccount(AssetAccount account) {
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

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case WORKING_ORDER_ID:
      if (value == null) {
        unsetWorkingOrderId();
      } else {
        setWorkingOrderId((Long)value);
      }
      break;

    case BASE_WORKING_ORDER:
      if (value == null) {
        unsetBaseWorkingOrder();
      } else {
        setBaseWorkingOrder((BaseWorkingOrder)value);
      }
      break;

    case ACCOUNT:
      if (value == null) {
        unsetAccount();
      } else {
        setAccount((AssetAccount)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case WORKING_ORDER_ID:
      return Long.valueOf(getWorkingOrderId());

    case BASE_WORKING_ORDER:
      return getBaseWorkingOrder();

    case ACCOUNT:
      return getAccount();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case WORKING_ORDER_ID:
      return isSetWorkingOrderId();
    case BASE_WORKING_ORDER:
      return isSetBaseWorkingOrder();
    case ACCOUNT:
      return isSetAccount();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof AssetAccountWorkingOrder)
      return this.equals((AssetAccountWorkingOrder)that);
    return false;
  }

  public boolean equals(AssetAccountWorkingOrder that) {
    if (that == null)
      return false;

    boolean this_present_workingOrderId = true && this.isSetWorkingOrderId();
    boolean that_present_workingOrderId = true && that.isSetWorkingOrderId();
    if (this_present_workingOrderId || that_present_workingOrderId) {
      if (!(this_present_workingOrderId && that_present_workingOrderId))
        return false;
      if (this.workingOrderId != that.workingOrderId)
        return false;
    }

    boolean this_present_baseWorkingOrder = true && this.isSetBaseWorkingOrder();
    boolean that_present_baseWorkingOrder = true && that.isSetBaseWorkingOrder();
    if (this_present_baseWorkingOrder || that_present_baseWorkingOrder) {
      if (!(this_present_baseWorkingOrder && that_present_baseWorkingOrder))
        return false;
      if (!this.baseWorkingOrder.equals(that.baseWorkingOrder))
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

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  @Override
  public int compareTo(AssetAccountWorkingOrder other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetWorkingOrderId()).compareTo(other.isSetWorkingOrderId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetWorkingOrderId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.workingOrderId, other.workingOrderId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetBaseWorkingOrder()).compareTo(other.isSetBaseWorkingOrder());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetBaseWorkingOrder()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.baseWorkingOrder, other.baseWorkingOrder);
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
    StringBuilder sb = new StringBuilder("AssetAccountWorkingOrder(");
    boolean first = true;

    if (isSetWorkingOrderId()) {
      sb.append("workingOrderId:");
      sb.append(this.workingOrderId);
      first = false;
    }
    if (isSetBaseWorkingOrder()) {
      if (!first) sb.append(", ");
      sb.append("baseWorkingOrder:");
      if (this.baseWorkingOrder == null) {
        sb.append("null");
      } else {
        sb.append(this.baseWorkingOrder);
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
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
    if (baseWorkingOrder != null) {
      baseWorkingOrder.validate();
    }
    if (account != null) {
      account.validate();
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

  private static class AssetAccountWorkingOrderStandardSchemeFactory implements SchemeFactory {
    public AssetAccountWorkingOrderStandardScheme getScheme() {
      return new AssetAccountWorkingOrderStandardScheme();
    }
  }

  private static class AssetAccountWorkingOrderStandardScheme extends StandardScheme<AssetAccountWorkingOrder> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, AssetAccountWorkingOrder struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // WORKING_ORDER_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.workingOrderId = iprot.readI64();
              struct.setWorkingOrderIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // BASE_WORKING_ORDER
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.baseWorkingOrder = new BaseWorkingOrder();
              struct.baseWorkingOrder.read(iprot);
              struct.setBaseWorkingOrderIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // ACCOUNT
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.account = new AssetAccount();
              struct.account.read(iprot);
              struct.setAccountIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, AssetAccountWorkingOrder struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.isSetWorkingOrderId()) {
        oprot.writeFieldBegin(WORKING_ORDER_ID_FIELD_DESC);
        oprot.writeI64(struct.workingOrderId);
        oprot.writeFieldEnd();
      }
      if (struct.baseWorkingOrder != null) {
        if (struct.isSetBaseWorkingOrder()) {
          oprot.writeFieldBegin(BASE_WORKING_ORDER_FIELD_DESC);
          struct.baseWorkingOrder.write(oprot);
          oprot.writeFieldEnd();
        }
      }
      if (struct.account != null) {
        if (struct.isSetAccount()) {
          oprot.writeFieldBegin(ACCOUNT_FIELD_DESC);
          struct.account.write(oprot);
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class AssetAccountWorkingOrderTupleSchemeFactory implements SchemeFactory {
    public AssetAccountWorkingOrderTupleScheme getScheme() {
      return new AssetAccountWorkingOrderTupleScheme();
    }
  }

  private static class AssetAccountWorkingOrderTupleScheme extends TupleScheme<AssetAccountWorkingOrder> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, AssetAccountWorkingOrder struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetWorkingOrderId()) {
        optionals.set(0);
      }
      if (struct.isSetBaseWorkingOrder()) {
        optionals.set(1);
      }
      if (struct.isSetAccount()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetWorkingOrderId()) {
        oprot.writeI64(struct.workingOrderId);
      }
      if (struct.isSetBaseWorkingOrder()) {
        struct.baseWorkingOrder.write(oprot);
      }
      if (struct.isSetAccount()) {
        struct.account.write(oprot);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, AssetAccountWorkingOrder struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.workingOrderId = iprot.readI64();
        struct.setWorkingOrderIdIsSet(true);
      }
      if (incoming.get(1)) {
        struct.baseWorkingOrder = new BaseWorkingOrder();
        struct.baseWorkingOrder.read(iprot);
        struct.setBaseWorkingOrderIsSet(true);
      }
      if (incoming.get(2)) {
        struct.account = new AssetAccount();
        struct.account.read(iprot);
        struct.setAccountIsSet(true);
      }
    }
  }

}

