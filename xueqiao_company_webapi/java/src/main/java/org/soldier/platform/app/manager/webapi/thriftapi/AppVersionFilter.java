/**
 * Autogenerated by Thrift Compiler (0.9.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package org.soldier.platform.app.manager.webapi.thriftapi;

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
 * 预留filter做为筛选的条件
 */
public class AppVersionFilter implements org.apache.thrift.TBase<AppVersionFilter, AppVersionFilter._Fields>, java.io.Serializable, Cloneable, Comparable<AppVersionFilter> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("AppVersionFilter");

  private static final org.apache.thrift.protocol.TField APP_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("appId", org.apache.thrift.protocol.TType.I64, (short)1);
  private static final org.apache.thrift.protocol.TField APP_KEY_FIELD_DESC = new org.apache.thrift.protocol.TField("appKey", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField VERSION_KEY_FIELD_DESC = new org.apache.thrift.protocol.TField("versionKey", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField VERSION_STATE_FIELD_DESC = new org.apache.thrift.protocol.TField("versionState", org.apache.thrift.protocol.TType.I32, (short)4);
  private static final org.apache.thrift.protocol.TField START_VERSION_NUM_FIELD_DESC = new org.apache.thrift.protocol.TField("startVersionNum", org.apache.thrift.protocol.TType.STRUCT, (short)5);
  private static final org.apache.thrift.protocol.TField END_VERSION_NUM_FIELD_DESC = new org.apache.thrift.protocol.TField("endVersionNum", org.apache.thrift.protocol.TType.STRUCT, (short)6);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new AppVersionFilterStandardSchemeFactory());
    schemes.put(TupleScheme.class, new AppVersionFilterTupleSchemeFactory());
  }

  public long appId; // optional
  public String appKey; // optional
  public String versionKey; // optional
  /**
   * 
   * @see org.soldier.platform.app.manager.thriftapi.VersionState
   */
  public org.soldier.platform.app.manager.thriftapi.VersionState versionState; // optional
  public org.soldier.platform.app.manager.thriftapi.VersionNum startVersionNum; // optional
  public org.soldier.platform.app.manager.thriftapi.VersionNum endVersionNum; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    APP_ID((short)1, "appId"),
    APP_KEY((short)2, "appKey"),
    VERSION_KEY((short)3, "versionKey"),
    /**
     * 
     * @see org.soldier.platform.app.manager.thriftapi.VersionState
     */
    VERSION_STATE((short)4, "versionState"),
    START_VERSION_NUM((short)5, "startVersionNum"),
    END_VERSION_NUM((short)6, "endVersionNum");

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
        case 1: // APP_ID
          return APP_ID;
        case 2: // APP_KEY
          return APP_KEY;
        case 3: // VERSION_KEY
          return VERSION_KEY;
        case 4: // VERSION_STATE
          return VERSION_STATE;
        case 5: // START_VERSION_NUM
          return START_VERSION_NUM;
        case 6: // END_VERSION_NUM
          return END_VERSION_NUM;
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
  private static final int __APPID_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  private _Fields optionals[] = {_Fields.APP_ID,_Fields.APP_KEY,_Fields.VERSION_KEY,_Fields.VERSION_STATE,_Fields.START_VERSION_NUM,_Fields.END_VERSION_NUM};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.APP_ID, new org.apache.thrift.meta_data.FieldMetaData("appId", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.APP_KEY, new org.apache.thrift.meta_data.FieldMetaData("appKey", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.VERSION_KEY, new org.apache.thrift.meta_data.FieldMetaData("versionKey", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.VERSION_STATE, new org.apache.thrift.meta_data.FieldMetaData("versionState", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, org.soldier.platform.app.manager.thriftapi.VersionState.class)));
    tmpMap.put(_Fields.START_VERSION_NUM, new org.apache.thrift.meta_data.FieldMetaData("startVersionNum", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, org.soldier.platform.app.manager.thriftapi.VersionNum.class)));
    tmpMap.put(_Fields.END_VERSION_NUM, new org.apache.thrift.meta_data.FieldMetaData("endVersionNum", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, org.soldier.platform.app.manager.thriftapi.VersionNum.class)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(AppVersionFilter.class, metaDataMap);
  }

  public AppVersionFilter() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public AppVersionFilter(AppVersionFilter other) {
    __isset_bitfield = other.__isset_bitfield;
    this.appId = other.appId;
    if (other.isSetAppKey()) {
      this.appKey = other.appKey;
    }
    if (other.isSetVersionKey()) {
      this.versionKey = other.versionKey;
    }
    if (other.isSetVersionState()) {
      this.versionState = other.versionState;
    }
    if (other.isSetStartVersionNum()) {
      this.startVersionNum = new org.soldier.platform.app.manager.thriftapi.VersionNum(other.startVersionNum);
    }
    if (other.isSetEndVersionNum()) {
      this.endVersionNum = new org.soldier.platform.app.manager.thriftapi.VersionNum(other.endVersionNum);
    }
  }

  public AppVersionFilter deepCopy() {
    return new AppVersionFilter(this);
  }

  @Override
  public void clear() {
    setAppIdIsSet(false);
    this.appId = 0;
    this.appKey = null;
    this.versionKey = null;
    this.versionState = null;
    this.startVersionNum = null;
    this.endVersionNum = null;
  }

  public long getAppId() {
    return this.appId;
  }

  public AppVersionFilter setAppId(long appId) {
    this.appId = appId;
    setAppIdIsSet(true);
    return this;
  }

  public void unsetAppId() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __APPID_ISSET_ID);
  }

  /** Returns true if field appId is set (has been assigned a value) and false otherwise */
  public boolean isSetAppId() {
    return EncodingUtils.testBit(__isset_bitfield, __APPID_ISSET_ID);
  }

  public void setAppIdIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __APPID_ISSET_ID, value);
  }

  public String getAppKey() {
    return this.appKey;
  }

  public AppVersionFilter setAppKey(String appKey) {
    this.appKey = appKey;
    return this;
  }

  public void unsetAppKey() {
    this.appKey = null;
  }

  /** Returns true if field appKey is set (has been assigned a value) and false otherwise */
  public boolean isSetAppKey() {
    return this.appKey != null;
  }

  public void setAppKeyIsSet(boolean value) {
    if (!value) {
      this.appKey = null;
    }
  }

  public String getVersionKey() {
    return this.versionKey;
  }

  public AppVersionFilter setVersionKey(String versionKey) {
    this.versionKey = versionKey;
    return this;
  }

  public void unsetVersionKey() {
    this.versionKey = null;
  }

  /** Returns true if field versionKey is set (has been assigned a value) and false otherwise */
  public boolean isSetVersionKey() {
    return this.versionKey != null;
  }

  public void setVersionKeyIsSet(boolean value) {
    if (!value) {
      this.versionKey = null;
    }
  }

  /**
   * 
   * @see org.soldier.platform.app.manager.thriftapi.VersionState
   */
  public org.soldier.platform.app.manager.thriftapi.VersionState getVersionState() {
    return this.versionState;
  }

  /**
   * 
   * @see org.soldier.platform.app.manager.thriftapi.VersionState
   */
  public AppVersionFilter setVersionState(org.soldier.platform.app.manager.thriftapi.VersionState versionState) {
    this.versionState = versionState;
    return this;
  }

  public void unsetVersionState() {
    this.versionState = null;
  }

  /** Returns true if field versionState is set (has been assigned a value) and false otherwise */
  public boolean isSetVersionState() {
    return this.versionState != null;
  }

  public void setVersionStateIsSet(boolean value) {
    if (!value) {
      this.versionState = null;
    }
  }

  public org.soldier.platform.app.manager.thriftapi.VersionNum getStartVersionNum() {
    return this.startVersionNum;
  }

  public AppVersionFilter setStartVersionNum(org.soldier.platform.app.manager.thriftapi.VersionNum startVersionNum) {
    this.startVersionNum = startVersionNum;
    return this;
  }

  public void unsetStartVersionNum() {
    this.startVersionNum = null;
  }

  /** Returns true if field startVersionNum is set (has been assigned a value) and false otherwise */
  public boolean isSetStartVersionNum() {
    return this.startVersionNum != null;
  }

  public void setStartVersionNumIsSet(boolean value) {
    if (!value) {
      this.startVersionNum = null;
    }
  }

  public org.soldier.platform.app.manager.thriftapi.VersionNum getEndVersionNum() {
    return this.endVersionNum;
  }

  public AppVersionFilter setEndVersionNum(org.soldier.platform.app.manager.thriftapi.VersionNum endVersionNum) {
    this.endVersionNum = endVersionNum;
    return this;
  }

  public void unsetEndVersionNum() {
    this.endVersionNum = null;
  }

  /** Returns true if field endVersionNum is set (has been assigned a value) and false otherwise */
  public boolean isSetEndVersionNum() {
    return this.endVersionNum != null;
  }

  public void setEndVersionNumIsSet(boolean value) {
    if (!value) {
      this.endVersionNum = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case APP_ID:
      if (value == null) {
        unsetAppId();
      } else {
        setAppId((Long)value);
      }
      break;

    case APP_KEY:
      if (value == null) {
        unsetAppKey();
      } else {
        setAppKey((String)value);
      }
      break;

    case VERSION_KEY:
      if (value == null) {
        unsetVersionKey();
      } else {
        setVersionKey((String)value);
      }
      break;

    case VERSION_STATE:
      if (value == null) {
        unsetVersionState();
      } else {
        setVersionState((org.soldier.platform.app.manager.thriftapi.VersionState)value);
      }
      break;

    case START_VERSION_NUM:
      if (value == null) {
        unsetStartVersionNum();
      } else {
        setStartVersionNum((org.soldier.platform.app.manager.thriftapi.VersionNum)value);
      }
      break;

    case END_VERSION_NUM:
      if (value == null) {
        unsetEndVersionNum();
      } else {
        setEndVersionNum((org.soldier.platform.app.manager.thriftapi.VersionNum)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case APP_ID:
      return Long.valueOf(getAppId());

    case APP_KEY:
      return getAppKey();

    case VERSION_KEY:
      return getVersionKey();

    case VERSION_STATE:
      return getVersionState();

    case START_VERSION_NUM:
      return getStartVersionNum();

    case END_VERSION_NUM:
      return getEndVersionNum();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case APP_ID:
      return isSetAppId();
    case APP_KEY:
      return isSetAppKey();
    case VERSION_KEY:
      return isSetVersionKey();
    case VERSION_STATE:
      return isSetVersionState();
    case START_VERSION_NUM:
      return isSetStartVersionNum();
    case END_VERSION_NUM:
      return isSetEndVersionNum();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof AppVersionFilter)
      return this.equals((AppVersionFilter)that);
    return false;
  }

  public boolean equals(AppVersionFilter that) {
    if (that == null)
      return false;

    boolean this_present_appId = true && this.isSetAppId();
    boolean that_present_appId = true && that.isSetAppId();
    if (this_present_appId || that_present_appId) {
      if (!(this_present_appId && that_present_appId))
        return false;
      if (this.appId != that.appId)
        return false;
    }

    boolean this_present_appKey = true && this.isSetAppKey();
    boolean that_present_appKey = true && that.isSetAppKey();
    if (this_present_appKey || that_present_appKey) {
      if (!(this_present_appKey && that_present_appKey))
        return false;
      if (!this.appKey.equals(that.appKey))
        return false;
    }

    boolean this_present_versionKey = true && this.isSetVersionKey();
    boolean that_present_versionKey = true && that.isSetVersionKey();
    if (this_present_versionKey || that_present_versionKey) {
      if (!(this_present_versionKey && that_present_versionKey))
        return false;
      if (!this.versionKey.equals(that.versionKey))
        return false;
    }

    boolean this_present_versionState = true && this.isSetVersionState();
    boolean that_present_versionState = true && that.isSetVersionState();
    if (this_present_versionState || that_present_versionState) {
      if (!(this_present_versionState && that_present_versionState))
        return false;
      if (!this.versionState.equals(that.versionState))
        return false;
    }

    boolean this_present_startVersionNum = true && this.isSetStartVersionNum();
    boolean that_present_startVersionNum = true && that.isSetStartVersionNum();
    if (this_present_startVersionNum || that_present_startVersionNum) {
      if (!(this_present_startVersionNum && that_present_startVersionNum))
        return false;
      if (!this.startVersionNum.equals(that.startVersionNum))
        return false;
    }

    boolean this_present_endVersionNum = true && this.isSetEndVersionNum();
    boolean that_present_endVersionNum = true && that.isSetEndVersionNum();
    if (this_present_endVersionNum || that_present_endVersionNum) {
      if (!(this_present_endVersionNum && that_present_endVersionNum))
        return false;
      if (!this.endVersionNum.equals(that.endVersionNum))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  @Override
  public int compareTo(AppVersionFilter other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetAppId()).compareTo(other.isSetAppId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAppId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.appId, other.appId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetAppKey()).compareTo(other.isSetAppKey());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAppKey()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.appKey, other.appKey);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetVersionKey()).compareTo(other.isSetVersionKey());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetVersionKey()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.versionKey, other.versionKey);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetVersionState()).compareTo(other.isSetVersionState());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetVersionState()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.versionState, other.versionState);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetStartVersionNum()).compareTo(other.isSetStartVersionNum());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetStartVersionNum()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.startVersionNum, other.startVersionNum);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetEndVersionNum()).compareTo(other.isSetEndVersionNum());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetEndVersionNum()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.endVersionNum, other.endVersionNum);
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
    StringBuilder sb = new StringBuilder("AppVersionFilter(");
    boolean first = true;

    if (isSetAppId()) {
      sb.append("appId:");
      sb.append(this.appId);
      first = false;
    }
    if (isSetAppKey()) {
      if (!first) sb.append(", ");
      sb.append("appKey:");
      if (this.appKey == null) {
        sb.append("null");
      } else {
        sb.append(this.appKey);
      }
      first = false;
    }
    if (isSetVersionKey()) {
      if (!first) sb.append(", ");
      sb.append("versionKey:");
      if (this.versionKey == null) {
        sb.append("null");
      } else {
        sb.append(this.versionKey);
      }
      first = false;
    }
    if (isSetVersionState()) {
      if (!first) sb.append(", ");
      sb.append("versionState:");
      if (this.versionState == null) {
        sb.append("null");
      } else {
        sb.append(this.versionState);
      }
      first = false;
    }
    if (isSetStartVersionNum()) {
      if (!first) sb.append(", ");
      sb.append("startVersionNum:");
      if (this.startVersionNum == null) {
        sb.append("null");
      } else {
        sb.append(this.startVersionNum);
      }
      first = false;
    }
    if (isSetEndVersionNum()) {
      if (!first) sb.append(", ");
      sb.append("endVersionNum:");
      if (this.endVersionNum == null) {
        sb.append("null");
      } else {
        sb.append(this.endVersionNum);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
    if (startVersionNum != null) {
      startVersionNum.validate();
    }
    if (endVersionNum != null) {
      endVersionNum.validate();
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

  private static class AppVersionFilterStandardSchemeFactory implements SchemeFactory {
    public AppVersionFilterStandardScheme getScheme() {
      return new AppVersionFilterStandardScheme();
    }
  }

  private static class AppVersionFilterStandardScheme extends StandardScheme<AppVersionFilter> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, AppVersionFilter struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // APP_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.appId = iprot.readI64();
              struct.setAppIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // APP_KEY
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.appKey = iprot.readString();
              struct.setAppKeyIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // VERSION_KEY
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.versionKey = iprot.readString();
              struct.setVersionKeyIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // VERSION_STATE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.versionState = org.soldier.platform.app.manager.thriftapi.VersionState.findByValue(iprot.readI32());
              struct.setVersionStateIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // START_VERSION_NUM
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.startVersionNum = new org.soldier.platform.app.manager.thriftapi.VersionNum();
              struct.startVersionNum.read(iprot);
              struct.setStartVersionNumIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // END_VERSION_NUM
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.endVersionNum = new org.soldier.platform.app.manager.thriftapi.VersionNum();
              struct.endVersionNum.read(iprot);
              struct.setEndVersionNumIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, AppVersionFilter struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.isSetAppId()) {
        oprot.writeFieldBegin(APP_ID_FIELD_DESC);
        oprot.writeI64(struct.appId);
        oprot.writeFieldEnd();
      }
      if (struct.appKey != null) {
        if (struct.isSetAppKey()) {
          oprot.writeFieldBegin(APP_KEY_FIELD_DESC);
          oprot.writeString(struct.appKey);
          oprot.writeFieldEnd();
        }
      }
      if (struct.versionKey != null) {
        if (struct.isSetVersionKey()) {
          oprot.writeFieldBegin(VERSION_KEY_FIELD_DESC);
          oprot.writeString(struct.versionKey);
          oprot.writeFieldEnd();
        }
      }
      if (struct.versionState != null) {
        if (struct.isSetVersionState()) {
          oprot.writeFieldBegin(VERSION_STATE_FIELD_DESC);
          oprot.writeI32(struct.versionState.getValue());
          oprot.writeFieldEnd();
        }
      }
      if (struct.startVersionNum != null) {
        if (struct.isSetStartVersionNum()) {
          oprot.writeFieldBegin(START_VERSION_NUM_FIELD_DESC);
          struct.startVersionNum.write(oprot);
          oprot.writeFieldEnd();
        }
      }
      if (struct.endVersionNum != null) {
        if (struct.isSetEndVersionNum()) {
          oprot.writeFieldBegin(END_VERSION_NUM_FIELD_DESC);
          struct.endVersionNum.write(oprot);
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class AppVersionFilterTupleSchemeFactory implements SchemeFactory {
    public AppVersionFilterTupleScheme getScheme() {
      return new AppVersionFilterTupleScheme();
    }
  }

  private static class AppVersionFilterTupleScheme extends TupleScheme<AppVersionFilter> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, AppVersionFilter struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetAppId()) {
        optionals.set(0);
      }
      if (struct.isSetAppKey()) {
        optionals.set(1);
      }
      if (struct.isSetVersionKey()) {
        optionals.set(2);
      }
      if (struct.isSetVersionState()) {
        optionals.set(3);
      }
      if (struct.isSetStartVersionNum()) {
        optionals.set(4);
      }
      if (struct.isSetEndVersionNum()) {
        optionals.set(5);
      }
      oprot.writeBitSet(optionals, 6);
      if (struct.isSetAppId()) {
        oprot.writeI64(struct.appId);
      }
      if (struct.isSetAppKey()) {
        oprot.writeString(struct.appKey);
      }
      if (struct.isSetVersionKey()) {
        oprot.writeString(struct.versionKey);
      }
      if (struct.isSetVersionState()) {
        oprot.writeI32(struct.versionState.getValue());
      }
      if (struct.isSetStartVersionNum()) {
        struct.startVersionNum.write(oprot);
      }
      if (struct.isSetEndVersionNum()) {
        struct.endVersionNum.write(oprot);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, AppVersionFilter struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(6);
      if (incoming.get(0)) {
        struct.appId = iprot.readI64();
        struct.setAppIdIsSet(true);
      }
      if (incoming.get(1)) {
        struct.appKey = iprot.readString();
        struct.setAppKeyIsSet(true);
      }
      if (incoming.get(2)) {
        struct.versionKey = iprot.readString();
        struct.setVersionKeyIsSet(true);
      }
      if (incoming.get(3)) {
        struct.versionState = org.soldier.platform.app.manager.thriftapi.VersionState.findByValue(iprot.readI32());
        struct.setVersionStateIsSet(true);
      }
      if (incoming.get(4)) {
        struct.startVersionNum = new org.soldier.platform.app.manager.thriftapi.VersionNum();
        struct.startVersionNum.read(iprot);
        struct.setStartVersionNumIsSet(true);
      }
      if (incoming.get(5)) {
        struct.endVersionNum = new org.soldier.platform.app.manager.thriftapi.VersionNum();
        struct.endVersionNum.read(iprot);
        struct.setEndVersionNumIsSet(true);
      }
    }
  }

}

