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
 * 工单基本信息
 */
public class BaseWorkingOrder implements org.apache.thrift.TBase<BaseWorkingOrder, BaseWorkingOrder._Fields>, java.io.Serializable, Cloneable, Comparable<BaseWorkingOrder> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("BaseWorkingOrder");

  private static final org.apache.thrift.protocol.TField ORDER_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("orderId", org.apache.thrift.protocol.TType.I64, (short)1);
  private static final org.apache.thrift.protocol.TField COMPANY_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("companyId", org.apache.thrift.protocol.TType.I64, (short)2);
  private static final org.apache.thrift.protocol.TField COMPANY_USER_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("companyUserId", org.apache.thrift.protocol.TType.I64, (short)3);
  private static final org.apache.thrift.protocol.TField WORKING_ORDER_TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("workingOrderType", org.apache.thrift.protocol.TType.I32, (short)4);
  private static final org.apache.thrift.protocol.TField STATE_FIELD_DESC = new org.apache.thrift.protocol.TField("state", org.apache.thrift.protocol.TType.I32, (short)5);
  private static final org.apache.thrift.protocol.TField CREATE_TIMESTAMP_FIELD_DESC = new org.apache.thrift.protocol.TField("createTimestamp", org.apache.thrift.protocol.TType.I64, (short)6);
  private static final org.apache.thrift.protocol.TField LAST_MODIFY_TIMESTAMP_FIELD_DESC = new org.apache.thrift.protocol.TField("lastModifyTimestamp", org.apache.thrift.protocol.TType.I64, (short)7);
  private static final org.apache.thrift.protocol.TField OPERATE_USER_FIELD_DESC = new org.apache.thrift.protocol.TField("operateUser", org.apache.thrift.protocol.TType.STRING, (short)8);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new BaseWorkingOrderStandardSchemeFactory());
    schemes.put(TupleScheme.class, new BaseWorkingOrderTupleSchemeFactory());
  }

  public long orderId; // optional
  public long companyId; // optional
  public long companyUserId; // optional
  /**
   * 
   * @see WorkingOrderType
   */
  public WorkingOrderType workingOrderType; // optional
  /**
   * 
   * @see WorkingOrderState
   */
  public WorkingOrderState state; // optional
  public long createTimestamp; // optional
  public long lastModifyTimestamp; // optional
  public String operateUser; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ORDER_ID((short)1, "orderId"),
    COMPANY_ID((short)2, "companyId"),
    COMPANY_USER_ID((short)3, "companyUserId"),
    /**
     * 
     * @see WorkingOrderType
     */
    WORKING_ORDER_TYPE((short)4, "workingOrderType"),
    /**
     * 
     * @see WorkingOrderState
     */
    STATE((short)5, "state"),
    CREATE_TIMESTAMP((short)6, "createTimestamp"),
    LAST_MODIFY_TIMESTAMP((short)7, "lastModifyTimestamp"),
    OPERATE_USER((short)8, "operateUser");

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
        case 1: // ORDER_ID
          return ORDER_ID;
        case 2: // COMPANY_ID
          return COMPANY_ID;
        case 3: // COMPANY_USER_ID
          return COMPANY_USER_ID;
        case 4: // WORKING_ORDER_TYPE
          return WORKING_ORDER_TYPE;
        case 5: // STATE
          return STATE;
        case 6: // CREATE_TIMESTAMP
          return CREATE_TIMESTAMP;
        case 7: // LAST_MODIFY_TIMESTAMP
          return LAST_MODIFY_TIMESTAMP;
        case 8: // OPERATE_USER
          return OPERATE_USER;
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
  private static final int __ORDERID_ISSET_ID = 0;
  private static final int __COMPANYID_ISSET_ID = 1;
  private static final int __COMPANYUSERID_ISSET_ID = 2;
  private static final int __CREATETIMESTAMP_ISSET_ID = 3;
  private static final int __LASTMODIFYTIMESTAMP_ISSET_ID = 4;
  private byte __isset_bitfield = 0;
  private _Fields optionals[] = {_Fields.ORDER_ID,_Fields.COMPANY_ID,_Fields.COMPANY_USER_ID,_Fields.WORKING_ORDER_TYPE,_Fields.STATE,_Fields.CREATE_TIMESTAMP,_Fields.LAST_MODIFY_TIMESTAMP,_Fields.OPERATE_USER};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ORDER_ID, new org.apache.thrift.meta_data.FieldMetaData("orderId", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.COMPANY_ID, new org.apache.thrift.meta_data.FieldMetaData("companyId", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.COMPANY_USER_ID, new org.apache.thrift.meta_data.FieldMetaData("companyUserId", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.WORKING_ORDER_TYPE, new org.apache.thrift.meta_data.FieldMetaData("workingOrderType", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, WorkingOrderType.class)));
    tmpMap.put(_Fields.STATE, new org.apache.thrift.meta_data.FieldMetaData("state", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, WorkingOrderState.class)));
    tmpMap.put(_Fields.CREATE_TIMESTAMP, new org.apache.thrift.meta_data.FieldMetaData("createTimestamp", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.LAST_MODIFY_TIMESTAMP, new org.apache.thrift.meta_data.FieldMetaData("lastModifyTimestamp", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.OPERATE_USER, new org.apache.thrift.meta_data.FieldMetaData("operateUser", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(BaseWorkingOrder.class, metaDataMap);
  }

  public BaseWorkingOrder() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public BaseWorkingOrder(BaseWorkingOrder other) {
    __isset_bitfield = other.__isset_bitfield;
    this.orderId = other.orderId;
    this.companyId = other.companyId;
    this.companyUserId = other.companyUserId;
    if (other.isSetWorkingOrderType()) {
      this.workingOrderType = other.workingOrderType;
    }
    if (other.isSetState()) {
      this.state = other.state;
    }
    this.createTimestamp = other.createTimestamp;
    this.lastModifyTimestamp = other.lastModifyTimestamp;
    if (other.isSetOperateUser()) {
      this.operateUser = other.operateUser;
    }
  }

  public BaseWorkingOrder deepCopy() {
    return new BaseWorkingOrder(this);
  }

  @Override
  public void clear() {
    setOrderIdIsSet(false);
    this.orderId = 0;
    setCompanyIdIsSet(false);
    this.companyId = 0;
    setCompanyUserIdIsSet(false);
    this.companyUserId = 0;
    this.workingOrderType = null;
    this.state = null;
    setCreateTimestampIsSet(false);
    this.createTimestamp = 0;
    setLastModifyTimestampIsSet(false);
    this.lastModifyTimestamp = 0;
    this.operateUser = null;
  }

  public long getOrderId() {
    return this.orderId;
  }

  public BaseWorkingOrder setOrderId(long orderId) {
    this.orderId = orderId;
    setOrderIdIsSet(true);
    return this;
  }

  public void unsetOrderId() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __ORDERID_ISSET_ID);
  }

  /** Returns true if field orderId is set (has been assigned a value) and false otherwise */
  public boolean isSetOrderId() {
    return EncodingUtils.testBit(__isset_bitfield, __ORDERID_ISSET_ID);
  }

  public void setOrderIdIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __ORDERID_ISSET_ID, value);
  }

  public long getCompanyId() {
    return this.companyId;
  }

  public BaseWorkingOrder setCompanyId(long companyId) {
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

  public long getCompanyUserId() {
    return this.companyUserId;
  }

  public BaseWorkingOrder setCompanyUserId(long companyUserId) {
    this.companyUserId = companyUserId;
    setCompanyUserIdIsSet(true);
    return this;
  }

  public void unsetCompanyUserId() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __COMPANYUSERID_ISSET_ID);
  }

  /** Returns true if field companyUserId is set (has been assigned a value) and false otherwise */
  public boolean isSetCompanyUserId() {
    return EncodingUtils.testBit(__isset_bitfield, __COMPANYUSERID_ISSET_ID);
  }

  public void setCompanyUserIdIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __COMPANYUSERID_ISSET_ID, value);
  }

  /**
   * 
   * @see WorkingOrderType
   */
  public WorkingOrderType getWorkingOrderType() {
    return this.workingOrderType;
  }

  /**
   * 
   * @see WorkingOrderType
   */
  public BaseWorkingOrder setWorkingOrderType(WorkingOrderType workingOrderType) {
    this.workingOrderType = workingOrderType;
    return this;
  }

  public void unsetWorkingOrderType() {
    this.workingOrderType = null;
  }

  /** Returns true if field workingOrderType is set (has been assigned a value) and false otherwise */
  public boolean isSetWorkingOrderType() {
    return this.workingOrderType != null;
  }

  public void setWorkingOrderTypeIsSet(boolean value) {
    if (!value) {
      this.workingOrderType = null;
    }
  }

  /**
   * 
   * @see WorkingOrderState
   */
  public WorkingOrderState getState() {
    return this.state;
  }

  /**
   * 
   * @see WorkingOrderState
   */
  public BaseWorkingOrder setState(WorkingOrderState state) {
    this.state = state;
    return this;
  }

  public void unsetState() {
    this.state = null;
  }

  /** Returns true if field state is set (has been assigned a value) and false otherwise */
  public boolean isSetState() {
    return this.state != null;
  }

  public void setStateIsSet(boolean value) {
    if (!value) {
      this.state = null;
    }
  }

  public long getCreateTimestamp() {
    return this.createTimestamp;
  }

  public BaseWorkingOrder setCreateTimestamp(long createTimestamp) {
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

  public BaseWorkingOrder setLastModifyTimestamp(long lastModifyTimestamp) {
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

  public String getOperateUser() {
    return this.operateUser;
  }

  public BaseWorkingOrder setOperateUser(String operateUser) {
    this.operateUser = operateUser;
    return this;
  }

  public void unsetOperateUser() {
    this.operateUser = null;
  }

  /** Returns true if field operateUser is set (has been assigned a value) and false otherwise */
  public boolean isSetOperateUser() {
    return this.operateUser != null;
  }

  public void setOperateUserIsSet(boolean value) {
    if (!value) {
      this.operateUser = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case ORDER_ID:
      if (value == null) {
        unsetOrderId();
      } else {
        setOrderId((Long)value);
      }
      break;

    case COMPANY_ID:
      if (value == null) {
        unsetCompanyId();
      } else {
        setCompanyId((Long)value);
      }
      break;

    case COMPANY_USER_ID:
      if (value == null) {
        unsetCompanyUserId();
      } else {
        setCompanyUserId((Long)value);
      }
      break;

    case WORKING_ORDER_TYPE:
      if (value == null) {
        unsetWorkingOrderType();
      } else {
        setWorkingOrderType((WorkingOrderType)value);
      }
      break;

    case STATE:
      if (value == null) {
        unsetState();
      } else {
        setState((WorkingOrderState)value);
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

    case OPERATE_USER:
      if (value == null) {
        unsetOperateUser();
      } else {
        setOperateUser((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case ORDER_ID:
      return Long.valueOf(getOrderId());

    case COMPANY_ID:
      return Long.valueOf(getCompanyId());

    case COMPANY_USER_ID:
      return Long.valueOf(getCompanyUserId());

    case WORKING_ORDER_TYPE:
      return getWorkingOrderType();

    case STATE:
      return getState();

    case CREATE_TIMESTAMP:
      return Long.valueOf(getCreateTimestamp());

    case LAST_MODIFY_TIMESTAMP:
      return Long.valueOf(getLastModifyTimestamp());

    case OPERATE_USER:
      return getOperateUser();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case ORDER_ID:
      return isSetOrderId();
    case COMPANY_ID:
      return isSetCompanyId();
    case COMPANY_USER_ID:
      return isSetCompanyUserId();
    case WORKING_ORDER_TYPE:
      return isSetWorkingOrderType();
    case STATE:
      return isSetState();
    case CREATE_TIMESTAMP:
      return isSetCreateTimestamp();
    case LAST_MODIFY_TIMESTAMP:
      return isSetLastModifyTimestamp();
    case OPERATE_USER:
      return isSetOperateUser();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof BaseWorkingOrder)
      return this.equals((BaseWorkingOrder)that);
    return false;
  }

  public boolean equals(BaseWorkingOrder that) {
    if (that == null)
      return false;

    boolean this_present_orderId = true && this.isSetOrderId();
    boolean that_present_orderId = true && that.isSetOrderId();
    if (this_present_orderId || that_present_orderId) {
      if (!(this_present_orderId && that_present_orderId))
        return false;
      if (this.orderId != that.orderId)
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

    boolean this_present_companyUserId = true && this.isSetCompanyUserId();
    boolean that_present_companyUserId = true && that.isSetCompanyUserId();
    if (this_present_companyUserId || that_present_companyUserId) {
      if (!(this_present_companyUserId && that_present_companyUserId))
        return false;
      if (this.companyUserId != that.companyUserId)
        return false;
    }

    boolean this_present_workingOrderType = true && this.isSetWorkingOrderType();
    boolean that_present_workingOrderType = true && that.isSetWorkingOrderType();
    if (this_present_workingOrderType || that_present_workingOrderType) {
      if (!(this_present_workingOrderType && that_present_workingOrderType))
        return false;
      if (!this.workingOrderType.equals(that.workingOrderType))
        return false;
    }

    boolean this_present_state = true && this.isSetState();
    boolean that_present_state = true && that.isSetState();
    if (this_present_state || that_present_state) {
      if (!(this_present_state && that_present_state))
        return false;
      if (!this.state.equals(that.state))
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

    boolean this_present_operateUser = true && this.isSetOperateUser();
    boolean that_present_operateUser = true && that.isSetOperateUser();
    if (this_present_operateUser || that_present_operateUser) {
      if (!(this_present_operateUser && that_present_operateUser))
        return false;
      if (!this.operateUser.equals(that.operateUser))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  @Override
  public int compareTo(BaseWorkingOrder other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetOrderId()).compareTo(other.isSetOrderId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetOrderId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.orderId, other.orderId);
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
    lastComparison = Boolean.valueOf(isSetCompanyUserId()).compareTo(other.isSetCompanyUserId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCompanyUserId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.companyUserId, other.companyUserId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetWorkingOrderType()).compareTo(other.isSetWorkingOrderType());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetWorkingOrderType()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.workingOrderType, other.workingOrderType);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetState()).compareTo(other.isSetState());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetState()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.state, other.state);
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
    lastComparison = Boolean.valueOf(isSetOperateUser()).compareTo(other.isSetOperateUser());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetOperateUser()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.operateUser, other.operateUser);
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
    StringBuilder sb = new StringBuilder("BaseWorkingOrder(");
    boolean first = true;

    if (isSetOrderId()) {
      sb.append("orderId:");
      sb.append(this.orderId);
      first = false;
    }
    if (isSetCompanyId()) {
      if (!first) sb.append(", ");
      sb.append("companyId:");
      sb.append(this.companyId);
      first = false;
    }
    if (isSetCompanyUserId()) {
      if (!first) sb.append(", ");
      sb.append("companyUserId:");
      sb.append(this.companyUserId);
      first = false;
    }
    if (isSetWorkingOrderType()) {
      if (!first) sb.append(", ");
      sb.append("workingOrderType:");
      if (this.workingOrderType == null) {
        sb.append("null");
      } else {
        sb.append(this.workingOrderType);
      }
      first = false;
    }
    if (isSetState()) {
      if (!first) sb.append(", ");
      sb.append("state:");
      if (this.state == null) {
        sb.append("null");
      } else {
        sb.append(this.state);
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
    if (isSetOperateUser()) {
      if (!first) sb.append(", ");
      sb.append("operateUser:");
      if (this.operateUser == null) {
        sb.append("null");
      } else {
        sb.append(this.operateUser);
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

  private static class BaseWorkingOrderStandardSchemeFactory implements SchemeFactory {
    public BaseWorkingOrderStandardScheme getScheme() {
      return new BaseWorkingOrderStandardScheme();
    }
  }

  private static class BaseWorkingOrderStandardScheme extends StandardScheme<BaseWorkingOrder> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, BaseWorkingOrder struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // ORDER_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.orderId = iprot.readI64();
              struct.setOrderIdIsSet(true);
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
          case 3: // COMPANY_USER_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.companyUserId = iprot.readI64();
              struct.setCompanyUserIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // WORKING_ORDER_TYPE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.workingOrderType = WorkingOrderType.findByValue(iprot.readI32());
              struct.setWorkingOrderTypeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // STATE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.state = WorkingOrderState.findByValue(iprot.readI32());
              struct.setStateIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // CREATE_TIMESTAMP
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.createTimestamp = iprot.readI64();
              struct.setCreateTimestampIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 7: // LAST_MODIFY_TIMESTAMP
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.lastModifyTimestamp = iprot.readI64();
              struct.setLastModifyTimestampIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 8: // OPERATE_USER
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.operateUser = iprot.readString();
              struct.setOperateUserIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, BaseWorkingOrder struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.isSetOrderId()) {
        oprot.writeFieldBegin(ORDER_ID_FIELD_DESC);
        oprot.writeI64(struct.orderId);
        oprot.writeFieldEnd();
      }
      if (struct.isSetCompanyId()) {
        oprot.writeFieldBegin(COMPANY_ID_FIELD_DESC);
        oprot.writeI64(struct.companyId);
        oprot.writeFieldEnd();
      }
      if (struct.isSetCompanyUserId()) {
        oprot.writeFieldBegin(COMPANY_USER_ID_FIELD_DESC);
        oprot.writeI64(struct.companyUserId);
        oprot.writeFieldEnd();
      }
      if (struct.workingOrderType != null) {
        if (struct.isSetWorkingOrderType()) {
          oprot.writeFieldBegin(WORKING_ORDER_TYPE_FIELD_DESC);
          oprot.writeI32(struct.workingOrderType.getValue());
          oprot.writeFieldEnd();
        }
      }
      if (struct.state != null) {
        if (struct.isSetState()) {
          oprot.writeFieldBegin(STATE_FIELD_DESC);
          oprot.writeI32(struct.state.getValue());
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
      if (struct.operateUser != null) {
        if (struct.isSetOperateUser()) {
          oprot.writeFieldBegin(OPERATE_USER_FIELD_DESC);
          oprot.writeString(struct.operateUser);
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class BaseWorkingOrderTupleSchemeFactory implements SchemeFactory {
    public BaseWorkingOrderTupleScheme getScheme() {
      return new BaseWorkingOrderTupleScheme();
    }
  }

  private static class BaseWorkingOrderTupleScheme extends TupleScheme<BaseWorkingOrder> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, BaseWorkingOrder struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetOrderId()) {
        optionals.set(0);
      }
      if (struct.isSetCompanyId()) {
        optionals.set(1);
      }
      if (struct.isSetCompanyUserId()) {
        optionals.set(2);
      }
      if (struct.isSetWorkingOrderType()) {
        optionals.set(3);
      }
      if (struct.isSetState()) {
        optionals.set(4);
      }
      if (struct.isSetCreateTimestamp()) {
        optionals.set(5);
      }
      if (struct.isSetLastModifyTimestamp()) {
        optionals.set(6);
      }
      if (struct.isSetOperateUser()) {
        optionals.set(7);
      }
      oprot.writeBitSet(optionals, 8);
      if (struct.isSetOrderId()) {
        oprot.writeI64(struct.orderId);
      }
      if (struct.isSetCompanyId()) {
        oprot.writeI64(struct.companyId);
      }
      if (struct.isSetCompanyUserId()) {
        oprot.writeI64(struct.companyUserId);
      }
      if (struct.isSetWorkingOrderType()) {
        oprot.writeI32(struct.workingOrderType.getValue());
      }
      if (struct.isSetState()) {
        oprot.writeI32(struct.state.getValue());
      }
      if (struct.isSetCreateTimestamp()) {
        oprot.writeI64(struct.createTimestamp);
      }
      if (struct.isSetLastModifyTimestamp()) {
        oprot.writeI64(struct.lastModifyTimestamp);
      }
      if (struct.isSetOperateUser()) {
        oprot.writeString(struct.operateUser);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, BaseWorkingOrder struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(8);
      if (incoming.get(0)) {
        struct.orderId = iprot.readI64();
        struct.setOrderIdIsSet(true);
      }
      if (incoming.get(1)) {
        struct.companyId = iprot.readI64();
        struct.setCompanyIdIsSet(true);
      }
      if (incoming.get(2)) {
        struct.companyUserId = iprot.readI64();
        struct.setCompanyUserIdIsSet(true);
      }
      if (incoming.get(3)) {
        struct.workingOrderType = WorkingOrderType.findByValue(iprot.readI32());
        struct.setWorkingOrderTypeIsSet(true);
      }
      if (incoming.get(4)) {
        struct.state = WorkingOrderState.findByValue(iprot.readI32());
        struct.setStateIsSet(true);
      }
      if (incoming.get(5)) {
        struct.createTimestamp = iprot.readI64();
        struct.setCreateTimestampIsSet(true);
      }
      if (incoming.get(6)) {
        struct.lastModifyTimestamp = iprot.readI64();
        struct.setLastModifyTimestampIsSet(true);
      }
      if (incoming.get(7)) {
        struct.operateUser = iprot.readString();
        struct.setOperateUserIsSet(true);
      }
    }
  }

}

