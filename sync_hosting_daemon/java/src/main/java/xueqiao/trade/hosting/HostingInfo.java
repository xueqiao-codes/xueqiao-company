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

public class HostingInfo implements org.apache.thrift.TBase<HostingInfo, HostingInfo._Fields>, java.io.Serializable, Cloneable, Comparable<HostingInfo> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("HostingInfo");

  private static final org.apache.thrift.protocol.TField STATUS_FIELD_DESC = new org.apache.thrift.protocol.TField("status", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField TABLE_VERSION_FIELD_DESC = new org.apache.thrift.protocol.TField("tableVersion", org.apache.thrift.protocol.TType.I32, (short)2);
  private static final org.apache.thrift.protocol.TField SUB_USER_TOTAL_COUNT_FIELD_DESC = new org.apache.thrift.protocol.TField("subUserTotalCount", org.apache.thrift.protocol.TType.I32, (short)3);
  private static final org.apache.thrift.protocol.TField COMPOSE_TOTAL_COUNT_FIELD_DESC = new org.apache.thrift.protocol.TField("composeTotalCount", org.apache.thrift.protocol.TType.I32, (short)4);
  private static final org.apache.thrift.protocol.TField ONLINE_USER_TOTAL_COUNT_FIELD_DESC = new org.apache.thrift.protocol.TField("onlineUserTotalCount", org.apache.thrift.protocol.TType.I32, (short)5);
  private static final org.apache.thrift.protocol.TField MACHINE_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("machineId", org.apache.thrift.protocol.TType.I64, (short)6);
  private static final org.apache.thrift.protocol.TField RUNNING_MODE_FIELD_DESC = new org.apache.thrift.protocol.TField("runningMode", org.apache.thrift.protocol.TType.I32, (short)7);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new HostingInfoStandardSchemeFactory());
    schemes.put(TupleScheme.class, new HostingInfoTupleSchemeFactory());
  }

  /**
   * 
   * @see HostingStatus
   */
  public HostingStatus status; // optional
  public int tableVersion; // optional
  public int subUserTotalCount; // optional
  public int composeTotalCount; // optional
  public int onlineUserTotalCount; // optional
  public long machineId; // optional
  /**
   * 
   * @see HostingRunningMode
   */
  public HostingRunningMode runningMode; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    /**
     * 
     * @see HostingStatus
     */
    STATUS((short)1, "status"),
    TABLE_VERSION((short)2, "tableVersion"),
    SUB_USER_TOTAL_COUNT((short)3, "subUserTotalCount"),
    COMPOSE_TOTAL_COUNT((short)4, "composeTotalCount"),
    ONLINE_USER_TOTAL_COUNT((short)5, "onlineUserTotalCount"),
    MACHINE_ID((short)6, "machineId"),
    /**
     * 
     * @see HostingRunningMode
     */
    RUNNING_MODE((short)7, "runningMode");

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
        case 1: // STATUS
          return STATUS;
        case 2: // TABLE_VERSION
          return TABLE_VERSION;
        case 3: // SUB_USER_TOTAL_COUNT
          return SUB_USER_TOTAL_COUNT;
        case 4: // COMPOSE_TOTAL_COUNT
          return COMPOSE_TOTAL_COUNT;
        case 5: // ONLINE_USER_TOTAL_COUNT
          return ONLINE_USER_TOTAL_COUNT;
        case 6: // MACHINE_ID
          return MACHINE_ID;
        case 7: // RUNNING_MODE
          return RUNNING_MODE;
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
  private static final int __TABLEVERSION_ISSET_ID = 0;
  private static final int __SUBUSERTOTALCOUNT_ISSET_ID = 1;
  private static final int __COMPOSETOTALCOUNT_ISSET_ID = 2;
  private static final int __ONLINEUSERTOTALCOUNT_ISSET_ID = 3;
  private static final int __MACHINEID_ISSET_ID = 4;
  private byte __isset_bitfield = 0;
  private _Fields optionals[] = {_Fields.STATUS,_Fields.TABLE_VERSION,_Fields.SUB_USER_TOTAL_COUNT,_Fields.COMPOSE_TOTAL_COUNT,_Fields.ONLINE_USER_TOTAL_COUNT,_Fields.MACHINE_ID,_Fields.RUNNING_MODE};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.STATUS, new org.apache.thrift.meta_data.FieldMetaData("status", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, HostingStatus.class)));
    tmpMap.put(_Fields.TABLE_VERSION, new org.apache.thrift.meta_data.FieldMetaData("tableVersion", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.SUB_USER_TOTAL_COUNT, new org.apache.thrift.meta_data.FieldMetaData("subUserTotalCount", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.COMPOSE_TOTAL_COUNT, new org.apache.thrift.meta_data.FieldMetaData("composeTotalCount", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.ONLINE_USER_TOTAL_COUNT, new org.apache.thrift.meta_data.FieldMetaData("onlineUserTotalCount", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.MACHINE_ID, new org.apache.thrift.meta_data.FieldMetaData("machineId", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.RUNNING_MODE, new org.apache.thrift.meta_data.FieldMetaData("runningMode", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, HostingRunningMode.class)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(HostingInfo.class, metaDataMap);
  }

  public HostingInfo() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public HostingInfo(HostingInfo other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetStatus()) {
      this.status = other.status;
    }
    this.tableVersion = other.tableVersion;
    this.subUserTotalCount = other.subUserTotalCount;
    this.composeTotalCount = other.composeTotalCount;
    this.onlineUserTotalCount = other.onlineUserTotalCount;
    this.machineId = other.machineId;
    if (other.isSetRunningMode()) {
      this.runningMode = other.runningMode;
    }
  }

  public HostingInfo deepCopy() {
    return new HostingInfo(this);
  }

  @Override
  public void clear() {
    this.status = null;
    setTableVersionIsSet(false);
    this.tableVersion = 0;
    setSubUserTotalCountIsSet(false);
    this.subUserTotalCount = 0;
    setComposeTotalCountIsSet(false);
    this.composeTotalCount = 0;
    setOnlineUserTotalCountIsSet(false);
    this.onlineUserTotalCount = 0;
    setMachineIdIsSet(false);
    this.machineId = 0;
    this.runningMode = null;
  }

  /**
   * 
   * @see HostingStatus
   */
  public HostingStatus getStatus() {
    return this.status;
  }

  /**
   * 
   * @see HostingStatus
   */
  public HostingInfo setStatus(HostingStatus status) {
    this.status = status;
    return this;
  }

  public void unsetStatus() {
    this.status = null;
  }

  /** Returns true if field status is set (has been assigned a value) and false otherwise */
  public boolean isSetStatus() {
    return this.status != null;
  }

  public void setStatusIsSet(boolean value) {
    if (!value) {
      this.status = null;
    }
  }

  public int getTableVersion() {
    return this.tableVersion;
  }

  public HostingInfo setTableVersion(int tableVersion) {
    this.tableVersion = tableVersion;
    setTableVersionIsSet(true);
    return this;
  }

  public void unsetTableVersion() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __TABLEVERSION_ISSET_ID);
  }

  /** Returns true if field tableVersion is set (has been assigned a value) and false otherwise */
  public boolean isSetTableVersion() {
    return EncodingUtils.testBit(__isset_bitfield, __TABLEVERSION_ISSET_ID);
  }

  public void setTableVersionIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __TABLEVERSION_ISSET_ID, value);
  }

  public int getSubUserTotalCount() {
    return this.subUserTotalCount;
  }

  public HostingInfo setSubUserTotalCount(int subUserTotalCount) {
    this.subUserTotalCount = subUserTotalCount;
    setSubUserTotalCountIsSet(true);
    return this;
  }

  public void unsetSubUserTotalCount() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __SUBUSERTOTALCOUNT_ISSET_ID);
  }

  /** Returns true if field subUserTotalCount is set (has been assigned a value) and false otherwise */
  public boolean isSetSubUserTotalCount() {
    return EncodingUtils.testBit(__isset_bitfield, __SUBUSERTOTALCOUNT_ISSET_ID);
  }

  public void setSubUserTotalCountIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __SUBUSERTOTALCOUNT_ISSET_ID, value);
  }

  public int getComposeTotalCount() {
    return this.composeTotalCount;
  }

  public HostingInfo setComposeTotalCount(int composeTotalCount) {
    this.composeTotalCount = composeTotalCount;
    setComposeTotalCountIsSet(true);
    return this;
  }

  public void unsetComposeTotalCount() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __COMPOSETOTALCOUNT_ISSET_ID);
  }

  /** Returns true if field composeTotalCount is set (has been assigned a value) and false otherwise */
  public boolean isSetComposeTotalCount() {
    return EncodingUtils.testBit(__isset_bitfield, __COMPOSETOTALCOUNT_ISSET_ID);
  }

  public void setComposeTotalCountIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __COMPOSETOTALCOUNT_ISSET_ID, value);
  }

  public int getOnlineUserTotalCount() {
    return this.onlineUserTotalCount;
  }

  public HostingInfo setOnlineUserTotalCount(int onlineUserTotalCount) {
    this.onlineUserTotalCount = onlineUserTotalCount;
    setOnlineUserTotalCountIsSet(true);
    return this;
  }

  public void unsetOnlineUserTotalCount() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __ONLINEUSERTOTALCOUNT_ISSET_ID);
  }

  /** Returns true if field onlineUserTotalCount is set (has been assigned a value) and false otherwise */
  public boolean isSetOnlineUserTotalCount() {
    return EncodingUtils.testBit(__isset_bitfield, __ONLINEUSERTOTALCOUNT_ISSET_ID);
  }

  public void setOnlineUserTotalCountIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __ONLINEUSERTOTALCOUNT_ISSET_ID, value);
  }

  public long getMachineId() {
    return this.machineId;
  }

  public HostingInfo setMachineId(long machineId) {
    this.machineId = machineId;
    setMachineIdIsSet(true);
    return this;
  }

  public void unsetMachineId() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __MACHINEID_ISSET_ID);
  }

  /** Returns true if field machineId is set (has been assigned a value) and false otherwise */
  public boolean isSetMachineId() {
    return EncodingUtils.testBit(__isset_bitfield, __MACHINEID_ISSET_ID);
  }

  public void setMachineIdIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __MACHINEID_ISSET_ID, value);
  }

  /**
   * 
   * @see HostingRunningMode
   */
  public HostingRunningMode getRunningMode() {
    return this.runningMode;
  }

  /**
   * 
   * @see HostingRunningMode
   */
  public HostingInfo setRunningMode(HostingRunningMode runningMode) {
    this.runningMode = runningMode;
    return this;
  }

  public void unsetRunningMode() {
    this.runningMode = null;
  }

  /** Returns true if field runningMode is set (has been assigned a value) and false otherwise */
  public boolean isSetRunningMode() {
    return this.runningMode != null;
  }

  public void setRunningModeIsSet(boolean value) {
    if (!value) {
      this.runningMode = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case STATUS:
      if (value == null) {
        unsetStatus();
      } else {
        setStatus((HostingStatus)value);
      }
      break;

    case TABLE_VERSION:
      if (value == null) {
        unsetTableVersion();
      } else {
        setTableVersion((Integer)value);
      }
      break;

    case SUB_USER_TOTAL_COUNT:
      if (value == null) {
        unsetSubUserTotalCount();
      } else {
        setSubUserTotalCount((Integer)value);
      }
      break;

    case COMPOSE_TOTAL_COUNT:
      if (value == null) {
        unsetComposeTotalCount();
      } else {
        setComposeTotalCount((Integer)value);
      }
      break;

    case ONLINE_USER_TOTAL_COUNT:
      if (value == null) {
        unsetOnlineUserTotalCount();
      } else {
        setOnlineUserTotalCount((Integer)value);
      }
      break;

    case MACHINE_ID:
      if (value == null) {
        unsetMachineId();
      } else {
        setMachineId((Long)value);
      }
      break;

    case RUNNING_MODE:
      if (value == null) {
        unsetRunningMode();
      } else {
        setRunningMode((HostingRunningMode)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case STATUS:
      return getStatus();

    case TABLE_VERSION:
      return Integer.valueOf(getTableVersion());

    case SUB_USER_TOTAL_COUNT:
      return Integer.valueOf(getSubUserTotalCount());

    case COMPOSE_TOTAL_COUNT:
      return Integer.valueOf(getComposeTotalCount());

    case ONLINE_USER_TOTAL_COUNT:
      return Integer.valueOf(getOnlineUserTotalCount());

    case MACHINE_ID:
      return Long.valueOf(getMachineId());

    case RUNNING_MODE:
      return getRunningMode();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case STATUS:
      return isSetStatus();
    case TABLE_VERSION:
      return isSetTableVersion();
    case SUB_USER_TOTAL_COUNT:
      return isSetSubUserTotalCount();
    case COMPOSE_TOTAL_COUNT:
      return isSetComposeTotalCount();
    case ONLINE_USER_TOTAL_COUNT:
      return isSetOnlineUserTotalCount();
    case MACHINE_ID:
      return isSetMachineId();
    case RUNNING_MODE:
      return isSetRunningMode();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof HostingInfo)
      return this.equals((HostingInfo)that);
    return false;
  }

  public boolean equals(HostingInfo that) {
    if (that == null)
      return false;

    boolean this_present_status = true && this.isSetStatus();
    boolean that_present_status = true && that.isSetStatus();
    if (this_present_status || that_present_status) {
      if (!(this_present_status && that_present_status))
        return false;
      if (!this.status.equals(that.status))
        return false;
    }

    boolean this_present_tableVersion = true && this.isSetTableVersion();
    boolean that_present_tableVersion = true && that.isSetTableVersion();
    if (this_present_tableVersion || that_present_tableVersion) {
      if (!(this_present_tableVersion && that_present_tableVersion))
        return false;
      if (this.tableVersion != that.tableVersion)
        return false;
    }

    boolean this_present_subUserTotalCount = true && this.isSetSubUserTotalCount();
    boolean that_present_subUserTotalCount = true && that.isSetSubUserTotalCount();
    if (this_present_subUserTotalCount || that_present_subUserTotalCount) {
      if (!(this_present_subUserTotalCount && that_present_subUserTotalCount))
        return false;
      if (this.subUserTotalCount != that.subUserTotalCount)
        return false;
    }

    boolean this_present_composeTotalCount = true && this.isSetComposeTotalCount();
    boolean that_present_composeTotalCount = true && that.isSetComposeTotalCount();
    if (this_present_composeTotalCount || that_present_composeTotalCount) {
      if (!(this_present_composeTotalCount && that_present_composeTotalCount))
        return false;
      if (this.composeTotalCount != that.composeTotalCount)
        return false;
    }

    boolean this_present_onlineUserTotalCount = true && this.isSetOnlineUserTotalCount();
    boolean that_present_onlineUserTotalCount = true && that.isSetOnlineUserTotalCount();
    if (this_present_onlineUserTotalCount || that_present_onlineUserTotalCount) {
      if (!(this_present_onlineUserTotalCount && that_present_onlineUserTotalCount))
        return false;
      if (this.onlineUserTotalCount != that.onlineUserTotalCount)
        return false;
    }

    boolean this_present_machineId = true && this.isSetMachineId();
    boolean that_present_machineId = true && that.isSetMachineId();
    if (this_present_machineId || that_present_machineId) {
      if (!(this_present_machineId && that_present_machineId))
        return false;
      if (this.machineId != that.machineId)
        return false;
    }

    boolean this_present_runningMode = true && this.isSetRunningMode();
    boolean that_present_runningMode = true && that.isSetRunningMode();
    if (this_present_runningMode || that_present_runningMode) {
      if (!(this_present_runningMode && that_present_runningMode))
        return false;
      if (!this.runningMode.equals(that.runningMode))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  @Override
  public int compareTo(HostingInfo other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetStatus()).compareTo(other.isSetStatus());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetStatus()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.status, other.status);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetTableVersion()).compareTo(other.isSetTableVersion());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTableVersion()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.tableVersion, other.tableVersion);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetSubUserTotalCount()).compareTo(other.isSetSubUserTotalCount());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSubUserTotalCount()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.subUserTotalCount, other.subUserTotalCount);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetComposeTotalCount()).compareTo(other.isSetComposeTotalCount());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetComposeTotalCount()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.composeTotalCount, other.composeTotalCount);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetOnlineUserTotalCount()).compareTo(other.isSetOnlineUserTotalCount());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetOnlineUserTotalCount()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.onlineUserTotalCount, other.onlineUserTotalCount);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetMachineId()).compareTo(other.isSetMachineId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetMachineId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.machineId, other.machineId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetRunningMode()).compareTo(other.isSetRunningMode());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRunningMode()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.runningMode, other.runningMode);
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
    StringBuilder sb = new StringBuilder("HostingInfo(");
    boolean first = true;

    if (isSetStatus()) {
      sb.append("status:");
      if (this.status == null) {
        sb.append("null");
      } else {
        sb.append(this.status);
      }
      first = false;
    }
    if (isSetTableVersion()) {
      if (!first) sb.append(", ");
      sb.append("tableVersion:");
      sb.append(this.tableVersion);
      first = false;
    }
    if (isSetSubUserTotalCount()) {
      if (!first) sb.append(", ");
      sb.append("subUserTotalCount:");
      sb.append(this.subUserTotalCount);
      first = false;
    }
    if (isSetComposeTotalCount()) {
      if (!first) sb.append(", ");
      sb.append("composeTotalCount:");
      sb.append(this.composeTotalCount);
      first = false;
    }
    if (isSetOnlineUserTotalCount()) {
      if (!first) sb.append(", ");
      sb.append("onlineUserTotalCount:");
      sb.append(this.onlineUserTotalCount);
      first = false;
    }
    if (isSetMachineId()) {
      if (!first) sb.append(", ");
      sb.append("machineId:");
      sb.append(this.machineId);
      first = false;
    }
    if (isSetRunningMode()) {
      if (!first) sb.append(", ");
      sb.append("runningMode:");
      if (this.runningMode == null) {
        sb.append("null");
      } else {
        sb.append(this.runningMode);
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

  private static class HostingInfoStandardSchemeFactory implements SchemeFactory {
    public HostingInfoStandardScheme getScheme() {
      return new HostingInfoStandardScheme();
    }
  }

  private static class HostingInfoStandardScheme extends StandardScheme<HostingInfo> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, HostingInfo struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // STATUS
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.status = HostingStatus.findByValue(iprot.readI32());
              struct.setStatusIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // TABLE_VERSION
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.tableVersion = iprot.readI32();
              struct.setTableVersionIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // SUB_USER_TOTAL_COUNT
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.subUserTotalCount = iprot.readI32();
              struct.setSubUserTotalCountIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // COMPOSE_TOTAL_COUNT
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.composeTotalCount = iprot.readI32();
              struct.setComposeTotalCountIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // ONLINE_USER_TOTAL_COUNT
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.onlineUserTotalCount = iprot.readI32();
              struct.setOnlineUserTotalCountIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // MACHINE_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.machineId = iprot.readI64();
              struct.setMachineIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 7: // RUNNING_MODE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.runningMode = HostingRunningMode.findByValue(iprot.readI32());
              struct.setRunningModeIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, HostingInfo struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.status != null) {
        if (struct.isSetStatus()) {
          oprot.writeFieldBegin(STATUS_FIELD_DESC);
          oprot.writeI32(struct.status.getValue());
          oprot.writeFieldEnd();
        }
      }
      if (struct.isSetTableVersion()) {
        oprot.writeFieldBegin(TABLE_VERSION_FIELD_DESC);
        oprot.writeI32(struct.tableVersion);
        oprot.writeFieldEnd();
      }
      if (struct.isSetSubUserTotalCount()) {
        oprot.writeFieldBegin(SUB_USER_TOTAL_COUNT_FIELD_DESC);
        oprot.writeI32(struct.subUserTotalCount);
        oprot.writeFieldEnd();
      }
      if (struct.isSetComposeTotalCount()) {
        oprot.writeFieldBegin(COMPOSE_TOTAL_COUNT_FIELD_DESC);
        oprot.writeI32(struct.composeTotalCount);
        oprot.writeFieldEnd();
      }
      if (struct.isSetOnlineUserTotalCount()) {
        oprot.writeFieldBegin(ONLINE_USER_TOTAL_COUNT_FIELD_DESC);
        oprot.writeI32(struct.onlineUserTotalCount);
        oprot.writeFieldEnd();
      }
      if (struct.isSetMachineId()) {
        oprot.writeFieldBegin(MACHINE_ID_FIELD_DESC);
        oprot.writeI64(struct.machineId);
        oprot.writeFieldEnd();
      }
      if (struct.runningMode != null) {
        if (struct.isSetRunningMode()) {
          oprot.writeFieldBegin(RUNNING_MODE_FIELD_DESC);
          oprot.writeI32(struct.runningMode.getValue());
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class HostingInfoTupleSchemeFactory implements SchemeFactory {
    public HostingInfoTupleScheme getScheme() {
      return new HostingInfoTupleScheme();
    }
  }

  private static class HostingInfoTupleScheme extends TupleScheme<HostingInfo> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, HostingInfo struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetStatus()) {
        optionals.set(0);
      }
      if (struct.isSetTableVersion()) {
        optionals.set(1);
      }
      if (struct.isSetSubUserTotalCount()) {
        optionals.set(2);
      }
      if (struct.isSetComposeTotalCount()) {
        optionals.set(3);
      }
      if (struct.isSetOnlineUserTotalCount()) {
        optionals.set(4);
      }
      if (struct.isSetMachineId()) {
        optionals.set(5);
      }
      if (struct.isSetRunningMode()) {
        optionals.set(6);
      }
      oprot.writeBitSet(optionals, 7);
      if (struct.isSetStatus()) {
        oprot.writeI32(struct.status.getValue());
      }
      if (struct.isSetTableVersion()) {
        oprot.writeI32(struct.tableVersion);
      }
      if (struct.isSetSubUserTotalCount()) {
        oprot.writeI32(struct.subUserTotalCount);
      }
      if (struct.isSetComposeTotalCount()) {
        oprot.writeI32(struct.composeTotalCount);
      }
      if (struct.isSetOnlineUserTotalCount()) {
        oprot.writeI32(struct.onlineUserTotalCount);
      }
      if (struct.isSetMachineId()) {
        oprot.writeI64(struct.machineId);
      }
      if (struct.isSetRunningMode()) {
        oprot.writeI32(struct.runningMode.getValue());
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, HostingInfo struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(7);
      if (incoming.get(0)) {
        struct.status = HostingStatus.findByValue(iprot.readI32());
        struct.setStatusIsSet(true);
      }
      if (incoming.get(1)) {
        struct.tableVersion = iprot.readI32();
        struct.setTableVersionIsSet(true);
      }
      if (incoming.get(2)) {
        struct.subUserTotalCount = iprot.readI32();
        struct.setSubUserTotalCountIsSet(true);
      }
      if (incoming.get(3)) {
        struct.composeTotalCount = iprot.readI32();
        struct.setComposeTotalCountIsSet(true);
      }
      if (incoming.get(4)) {
        struct.onlineUserTotalCount = iprot.readI32();
        struct.setOnlineUserTotalCountIsSet(true);
      }
      if (incoming.get(5)) {
        struct.machineId = iprot.readI64();
        struct.setMachineIdIsSet(true);
      }
      if (incoming.get(6)) {
        struct.runningMode = HostingRunningMode.findByValue(iprot.readI32());
        struct.setRunningModeIsSet(true);
      }
    }
  }

}

