/**
 * Autogenerated by Thrift Compiler (0.9.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package org.soldier.platform.app.manager.thriftapi;

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

public class ServerAppSupport implements org.apache.thrift.TBase<ServerAppSupport, ServerAppSupport._Fields>, java.io.Serializable, Cloneable, Comparable<ServerAppSupport> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("ServerAppSupport");

  private static final org.apache.thrift.protocol.TField SERVER_VERSION_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("serverVersionId", org.apache.thrift.protocol.TType.I64, (short)1);
  private static final org.apache.thrift.protocol.TField SUPPORT_CLIENT_APP_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("supportClientAppId", org.apache.thrift.protocol.TType.I64, (short)2);
  private static final org.apache.thrift.protocol.TField MIN_SUPPORT_VERSION_FIELD_DESC = new org.apache.thrift.protocol.TField("minSupportVersion", org.apache.thrift.protocol.TType.STRUCT, (short)3);
  private static final org.apache.thrift.protocol.TField MAX_SUPPORT_VERSION_FIELD_DESC = new org.apache.thrift.protocol.TField("maxSupportVersion", org.apache.thrift.protocol.TType.STRUCT, (short)4);
  private static final org.apache.thrift.protocol.TField CREATE_TIMESTAMP_FIELD_DESC = new org.apache.thrift.protocol.TField("createTimestamp", org.apache.thrift.protocol.TType.I64, (short)5);
  private static final org.apache.thrift.protocol.TField LAST_MODIFY_TIMESTAMP_FIELD_DESC = new org.apache.thrift.protocol.TField("lastModifyTimestamp", org.apache.thrift.protocol.TType.I64, (short)6);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new ServerAppSupportStandardSchemeFactory());
    schemes.put(TupleScheme.class, new ServerAppSupportTupleSchemeFactory());
  }

  public long serverVersionId; // optional
  public long supportClientAppId; // optional
  public VersionNum minSupportVersion; // optional
  public VersionNum maxSupportVersion; // optional
  public long createTimestamp; // optional
  public long lastModifyTimestamp; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    SERVER_VERSION_ID((short)1, "serverVersionId"),
    SUPPORT_CLIENT_APP_ID((short)2, "supportClientAppId"),
    MIN_SUPPORT_VERSION((short)3, "minSupportVersion"),
    MAX_SUPPORT_VERSION((short)4, "maxSupportVersion"),
    CREATE_TIMESTAMP((short)5, "createTimestamp"),
    LAST_MODIFY_TIMESTAMP((short)6, "lastModifyTimestamp");

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
        case 1: // SERVER_VERSION_ID
          return SERVER_VERSION_ID;
        case 2: // SUPPORT_CLIENT_APP_ID
          return SUPPORT_CLIENT_APP_ID;
        case 3: // MIN_SUPPORT_VERSION
          return MIN_SUPPORT_VERSION;
        case 4: // MAX_SUPPORT_VERSION
          return MAX_SUPPORT_VERSION;
        case 5: // CREATE_TIMESTAMP
          return CREATE_TIMESTAMP;
        case 6: // LAST_MODIFY_TIMESTAMP
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
  private static final int __SERVERVERSIONID_ISSET_ID = 0;
  private static final int __SUPPORTCLIENTAPPID_ISSET_ID = 1;
  private static final int __CREATETIMESTAMP_ISSET_ID = 2;
  private static final int __LASTMODIFYTIMESTAMP_ISSET_ID = 3;
  private byte __isset_bitfield = 0;
  private _Fields optionals[] = {_Fields.SERVER_VERSION_ID,_Fields.SUPPORT_CLIENT_APP_ID,_Fields.MIN_SUPPORT_VERSION,_Fields.MAX_SUPPORT_VERSION,_Fields.CREATE_TIMESTAMP,_Fields.LAST_MODIFY_TIMESTAMP};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.SERVER_VERSION_ID, new org.apache.thrift.meta_data.FieldMetaData("serverVersionId", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.SUPPORT_CLIENT_APP_ID, new org.apache.thrift.meta_data.FieldMetaData("supportClientAppId", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.MIN_SUPPORT_VERSION, new org.apache.thrift.meta_data.FieldMetaData("minSupportVersion", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, VersionNum.class)));
    tmpMap.put(_Fields.MAX_SUPPORT_VERSION, new org.apache.thrift.meta_data.FieldMetaData("maxSupportVersion", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, VersionNum.class)));
    tmpMap.put(_Fields.CREATE_TIMESTAMP, new org.apache.thrift.meta_data.FieldMetaData("createTimestamp", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.LAST_MODIFY_TIMESTAMP, new org.apache.thrift.meta_data.FieldMetaData("lastModifyTimestamp", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(ServerAppSupport.class, metaDataMap);
  }

  public ServerAppSupport() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public ServerAppSupport(ServerAppSupport other) {
    __isset_bitfield = other.__isset_bitfield;
    this.serverVersionId = other.serverVersionId;
    this.supportClientAppId = other.supportClientAppId;
    if (other.isSetMinSupportVersion()) {
      this.minSupportVersion = new VersionNum(other.minSupportVersion);
    }
    if (other.isSetMaxSupportVersion()) {
      this.maxSupportVersion = new VersionNum(other.maxSupportVersion);
    }
    this.createTimestamp = other.createTimestamp;
    this.lastModifyTimestamp = other.lastModifyTimestamp;
  }

  public ServerAppSupport deepCopy() {
    return new ServerAppSupport(this);
  }

  @Override
  public void clear() {
    setServerVersionIdIsSet(false);
    this.serverVersionId = 0;
    setSupportClientAppIdIsSet(false);
    this.supportClientAppId = 0;
    this.minSupportVersion = null;
    this.maxSupportVersion = null;
    setCreateTimestampIsSet(false);
    this.createTimestamp = 0;
    setLastModifyTimestampIsSet(false);
    this.lastModifyTimestamp = 0;
  }

  public long getServerVersionId() {
    return this.serverVersionId;
  }

  public ServerAppSupport setServerVersionId(long serverVersionId) {
    this.serverVersionId = serverVersionId;
    setServerVersionIdIsSet(true);
    return this;
  }

  public void unsetServerVersionId() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __SERVERVERSIONID_ISSET_ID);
  }

  /** Returns true if field serverVersionId is set (has been assigned a value) and false otherwise */
  public boolean isSetServerVersionId() {
    return EncodingUtils.testBit(__isset_bitfield, __SERVERVERSIONID_ISSET_ID);
  }

  public void setServerVersionIdIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __SERVERVERSIONID_ISSET_ID, value);
  }

  public long getSupportClientAppId() {
    return this.supportClientAppId;
  }

  public ServerAppSupport setSupportClientAppId(long supportClientAppId) {
    this.supportClientAppId = supportClientAppId;
    setSupportClientAppIdIsSet(true);
    return this;
  }

  public void unsetSupportClientAppId() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __SUPPORTCLIENTAPPID_ISSET_ID);
  }

  /** Returns true if field supportClientAppId is set (has been assigned a value) and false otherwise */
  public boolean isSetSupportClientAppId() {
    return EncodingUtils.testBit(__isset_bitfield, __SUPPORTCLIENTAPPID_ISSET_ID);
  }

  public void setSupportClientAppIdIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __SUPPORTCLIENTAPPID_ISSET_ID, value);
  }

  public VersionNum getMinSupportVersion() {
    return this.minSupportVersion;
  }

  public ServerAppSupport setMinSupportVersion(VersionNum minSupportVersion) {
    this.minSupportVersion = minSupportVersion;
    return this;
  }

  public void unsetMinSupportVersion() {
    this.minSupportVersion = null;
  }

  /** Returns true if field minSupportVersion is set (has been assigned a value) and false otherwise */
  public boolean isSetMinSupportVersion() {
    return this.minSupportVersion != null;
  }

  public void setMinSupportVersionIsSet(boolean value) {
    if (!value) {
      this.minSupportVersion = null;
    }
  }

  public VersionNum getMaxSupportVersion() {
    return this.maxSupportVersion;
  }

  public ServerAppSupport setMaxSupportVersion(VersionNum maxSupportVersion) {
    this.maxSupportVersion = maxSupportVersion;
    return this;
  }

  public void unsetMaxSupportVersion() {
    this.maxSupportVersion = null;
  }

  /** Returns true if field maxSupportVersion is set (has been assigned a value) and false otherwise */
  public boolean isSetMaxSupportVersion() {
    return this.maxSupportVersion != null;
  }

  public void setMaxSupportVersionIsSet(boolean value) {
    if (!value) {
      this.maxSupportVersion = null;
    }
  }

  public long getCreateTimestamp() {
    return this.createTimestamp;
  }

  public ServerAppSupport setCreateTimestamp(long createTimestamp) {
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

  public ServerAppSupport setLastModifyTimestamp(long lastModifyTimestamp) {
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
    case SERVER_VERSION_ID:
      if (value == null) {
        unsetServerVersionId();
      } else {
        setServerVersionId((Long)value);
      }
      break;

    case SUPPORT_CLIENT_APP_ID:
      if (value == null) {
        unsetSupportClientAppId();
      } else {
        setSupportClientAppId((Long)value);
      }
      break;

    case MIN_SUPPORT_VERSION:
      if (value == null) {
        unsetMinSupportVersion();
      } else {
        setMinSupportVersion((VersionNum)value);
      }
      break;

    case MAX_SUPPORT_VERSION:
      if (value == null) {
        unsetMaxSupportVersion();
      } else {
        setMaxSupportVersion((VersionNum)value);
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
    case SERVER_VERSION_ID:
      return Long.valueOf(getServerVersionId());

    case SUPPORT_CLIENT_APP_ID:
      return Long.valueOf(getSupportClientAppId());

    case MIN_SUPPORT_VERSION:
      return getMinSupportVersion();

    case MAX_SUPPORT_VERSION:
      return getMaxSupportVersion();

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
    case SERVER_VERSION_ID:
      return isSetServerVersionId();
    case SUPPORT_CLIENT_APP_ID:
      return isSetSupportClientAppId();
    case MIN_SUPPORT_VERSION:
      return isSetMinSupportVersion();
    case MAX_SUPPORT_VERSION:
      return isSetMaxSupportVersion();
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
    if (that instanceof ServerAppSupport)
      return this.equals((ServerAppSupport)that);
    return false;
  }

  public boolean equals(ServerAppSupport that) {
    if (that == null)
      return false;

    boolean this_present_serverVersionId = true && this.isSetServerVersionId();
    boolean that_present_serverVersionId = true && that.isSetServerVersionId();
    if (this_present_serverVersionId || that_present_serverVersionId) {
      if (!(this_present_serverVersionId && that_present_serverVersionId))
        return false;
      if (this.serverVersionId != that.serverVersionId)
        return false;
    }

    boolean this_present_supportClientAppId = true && this.isSetSupportClientAppId();
    boolean that_present_supportClientAppId = true && that.isSetSupportClientAppId();
    if (this_present_supportClientAppId || that_present_supportClientAppId) {
      if (!(this_present_supportClientAppId && that_present_supportClientAppId))
        return false;
      if (this.supportClientAppId != that.supportClientAppId)
        return false;
    }

    boolean this_present_minSupportVersion = true && this.isSetMinSupportVersion();
    boolean that_present_minSupportVersion = true && that.isSetMinSupportVersion();
    if (this_present_minSupportVersion || that_present_minSupportVersion) {
      if (!(this_present_minSupportVersion && that_present_minSupportVersion))
        return false;
      if (!this.minSupportVersion.equals(that.minSupportVersion))
        return false;
    }

    boolean this_present_maxSupportVersion = true && this.isSetMaxSupportVersion();
    boolean that_present_maxSupportVersion = true && that.isSetMaxSupportVersion();
    if (this_present_maxSupportVersion || that_present_maxSupportVersion) {
      if (!(this_present_maxSupportVersion && that_present_maxSupportVersion))
        return false;
      if (!this.maxSupportVersion.equals(that.maxSupportVersion))
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
  public int compareTo(ServerAppSupport other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetServerVersionId()).compareTo(other.isSetServerVersionId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetServerVersionId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.serverVersionId, other.serverVersionId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetSupportClientAppId()).compareTo(other.isSetSupportClientAppId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSupportClientAppId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.supportClientAppId, other.supportClientAppId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetMinSupportVersion()).compareTo(other.isSetMinSupportVersion());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetMinSupportVersion()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.minSupportVersion, other.minSupportVersion);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetMaxSupportVersion()).compareTo(other.isSetMaxSupportVersion());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetMaxSupportVersion()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.maxSupportVersion, other.maxSupportVersion);
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
    StringBuilder sb = new StringBuilder("ServerAppSupport(");
    boolean first = true;

    if (isSetServerVersionId()) {
      sb.append("serverVersionId:");
      sb.append(this.serverVersionId);
      first = false;
    }
    if (isSetSupportClientAppId()) {
      if (!first) sb.append(", ");
      sb.append("supportClientAppId:");
      sb.append(this.supportClientAppId);
      first = false;
    }
    if (isSetMinSupportVersion()) {
      if (!first) sb.append(", ");
      sb.append("minSupportVersion:");
      if (this.minSupportVersion == null) {
        sb.append("null");
      } else {
        sb.append(this.minSupportVersion);
      }
      first = false;
    }
    if (isSetMaxSupportVersion()) {
      if (!first) sb.append(", ");
      sb.append("maxSupportVersion:");
      if (this.maxSupportVersion == null) {
        sb.append("null");
      } else {
        sb.append(this.maxSupportVersion);
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
    if (minSupportVersion != null) {
      minSupportVersion.validate();
    }
    if (maxSupportVersion != null) {
      maxSupportVersion.validate();
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

  private static class ServerAppSupportStandardSchemeFactory implements SchemeFactory {
    public ServerAppSupportStandardScheme getScheme() {
      return new ServerAppSupportStandardScheme();
    }
  }

  private static class ServerAppSupportStandardScheme extends StandardScheme<ServerAppSupport> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, ServerAppSupport struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // SERVER_VERSION_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.serverVersionId = iprot.readI64();
              struct.setServerVersionIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // SUPPORT_CLIENT_APP_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.supportClientAppId = iprot.readI64();
              struct.setSupportClientAppIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // MIN_SUPPORT_VERSION
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.minSupportVersion = new VersionNum();
              struct.minSupportVersion.read(iprot);
              struct.setMinSupportVersionIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // MAX_SUPPORT_VERSION
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.maxSupportVersion = new VersionNum();
              struct.maxSupportVersion.read(iprot);
              struct.setMaxSupportVersionIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // CREATE_TIMESTAMP
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.createTimestamp = iprot.readI64();
              struct.setCreateTimestampIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // LAST_MODIFY_TIMESTAMP
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, ServerAppSupport struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.isSetServerVersionId()) {
        oprot.writeFieldBegin(SERVER_VERSION_ID_FIELD_DESC);
        oprot.writeI64(struct.serverVersionId);
        oprot.writeFieldEnd();
      }
      if (struct.isSetSupportClientAppId()) {
        oprot.writeFieldBegin(SUPPORT_CLIENT_APP_ID_FIELD_DESC);
        oprot.writeI64(struct.supportClientAppId);
        oprot.writeFieldEnd();
      }
      if (struct.minSupportVersion != null) {
        if (struct.isSetMinSupportVersion()) {
          oprot.writeFieldBegin(MIN_SUPPORT_VERSION_FIELD_DESC);
          struct.minSupportVersion.write(oprot);
          oprot.writeFieldEnd();
        }
      }
      if (struct.maxSupportVersion != null) {
        if (struct.isSetMaxSupportVersion()) {
          oprot.writeFieldBegin(MAX_SUPPORT_VERSION_FIELD_DESC);
          struct.maxSupportVersion.write(oprot);
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

  private static class ServerAppSupportTupleSchemeFactory implements SchemeFactory {
    public ServerAppSupportTupleScheme getScheme() {
      return new ServerAppSupportTupleScheme();
    }
  }

  private static class ServerAppSupportTupleScheme extends TupleScheme<ServerAppSupport> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, ServerAppSupport struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetServerVersionId()) {
        optionals.set(0);
      }
      if (struct.isSetSupportClientAppId()) {
        optionals.set(1);
      }
      if (struct.isSetMinSupportVersion()) {
        optionals.set(2);
      }
      if (struct.isSetMaxSupportVersion()) {
        optionals.set(3);
      }
      if (struct.isSetCreateTimestamp()) {
        optionals.set(4);
      }
      if (struct.isSetLastModifyTimestamp()) {
        optionals.set(5);
      }
      oprot.writeBitSet(optionals, 6);
      if (struct.isSetServerVersionId()) {
        oprot.writeI64(struct.serverVersionId);
      }
      if (struct.isSetSupportClientAppId()) {
        oprot.writeI64(struct.supportClientAppId);
      }
      if (struct.isSetMinSupportVersion()) {
        struct.minSupportVersion.write(oprot);
      }
      if (struct.isSetMaxSupportVersion()) {
        struct.maxSupportVersion.write(oprot);
      }
      if (struct.isSetCreateTimestamp()) {
        oprot.writeI64(struct.createTimestamp);
      }
      if (struct.isSetLastModifyTimestamp()) {
        oprot.writeI64(struct.lastModifyTimestamp);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, ServerAppSupport struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(6);
      if (incoming.get(0)) {
        struct.serverVersionId = iprot.readI64();
        struct.setServerVersionIdIsSet(true);
      }
      if (incoming.get(1)) {
        struct.supportClientAppId = iprot.readI64();
        struct.setSupportClientAppIdIsSet(true);
      }
      if (incoming.get(2)) {
        struct.minSupportVersion = new VersionNum();
        struct.minSupportVersion.read(iprot);
        struct.setMinSupportVersionIsSet(true);
      }
      if (incoming.get(3)) {
        struct.maxSupportVersion = new VersionNum();
        struct.maxSupportVersion.read(iprot);
        struct.setMaxSupportVersionIsSet(true);
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
