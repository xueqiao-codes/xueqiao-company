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

public class VersionInfo implements org.apache.thrift.TBase<VersionInfo, VersionInfo._Fields>, java.io.Serializable, Cloneable, Comparable<VersionInfo> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("VersionInfo");

  private static final org.apache.thrift.protocol.TField VERSION_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("versionId", org.apache.thrift.protocol.TType.I64, (short)1);
  private static final org.apache.thrift.protocol.TField VERSION_TAG_FIELD_DESC = new org.apache.thrift.protocol.TField("versionTag", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField VERSION_TIMESTAMP_FIELD_DESC = new org.apache.thrift.protocol.TField("versionTimestamp", org.apache.thrift.protocol.TType.I64, (short)3);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new VersionInfoStandardSchemeFactory());
    schemes.put(TupleScheme.class, new VersionInfoTupleSchemeFactory());
  }

  public long versionId; // optional
  public String versionTag; // optional
  public long versionTimestamp; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    VERSION_ID((short)1, "versionId"),
    VERSION_TAG((short)2, "versionTag"),
    VERSION_TIMESTAMP((short)3, "versionTimestamp");

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
        case 1: // VERSION_ID
          return VERSION_ID;
        case 2: // VERSION_TAG
          return VERSION_TAG;
        case 3: // VERSION_TIMESTAMP
          return VERSION_TIMESTAMP;
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
  private static final int __VERSIONID_ISSET_ID = 0;
  private static final int __VERSIONTIMESTAMP_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  private _Fields optionals[] = {_Fields.VERSION_ID,_Fields.VERSION_TAG,_Fields.VERSION_TIMESTAMP};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.VERSION_ID, new org.apache.thrift.meta_data.FieldMetaData("versionId", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.VERSION_TAG, new org.apache.thrift.meta_data.FieldMetaData("versionTag", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.VERSION_TIMESTAMP, new org.apache.thrift.meta_data.FieldMetaData("versionTimestamp", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(VersionInfo.class, metaDataMap);
  }

  public VersionInfo() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public VersionInfo(VersionInfo other) {
    __isset_bitfield = other.__isset_bitfield;
    this.versionId = other.versionId;
    if (other.isSetVersionTag()) {
      this.versionTag = other.versionTag;
    }
    this.versionTimestamp = other.versionTimestamp;
  }

  public VersionInfo deepCopy() {
    return new VersionInfo(this);
  }

  @Override
  public void clear() {
    setVersionIdIsSet(false);
    this.versionId = 0;
    this.versionTag = null;
    setVersionTimestampIsSet(false);
    this.versionTimestamp = 0;
  }

  public long getVersionId() {
    return this.versionId;
  }

  public VersionInfo setVersionId(long versionId) {
    this.versionId = versionId;
    setVersionIdIsSet(true);
    return this;
  }

  public void unsetVersionId() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __VERSIONID_ISSET_ID);
  }

  /** Returns true if field versionId is set (has been assigned a value) and false otherwise */
  public boolean isSetVersionId() {
    return EncodingUtils.testBit(__isset_bitfield, __VERSIONID_ISSET_ID);
  }

  public void setVersionIdIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __VERSIONID_ISSET_ID, value);
  }

  public String getVersionTag() {
    return this.versionTag;
  }

  public VersionInfo setVersionTag(String versionTag) {
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

  public long getVersionTimestamp() {
    return this.versionTimestamp;
  }

  public VersionInfo setVersionTimestamp(long versionTimestamp) {
    this.versionTimestamp = versionTimestamp;
    setVersionTimestampIsSet(true);
    return this;
  }

  public void unsetVersionTimestamp() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __VERSIONTIMESTAMP_ISSET_ID);
  }

  /** Returns true if field versionTimestamp is set (has been assigned a value) and false otherwise */
  public boolean isSetVersionTimestamp() {
    return EncodingUtils.testBit(__isset_bitfield, __VERSIONTIMESTAMP_ISSET_ID);
  }

  public void setVersionTimestampIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __VERSIONTIMESTAMP_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case VERSION_ID:
      if (value == null) {
        unsetVersionId();
      } else {
        setVersionId((Long)value);
      }
      break;

    case VERSION_TAG:
      if (value == null) {
        unsetVersionTag();
      } else {
        setVersionTag((String)value);
      }
      break;

    case VERSION_TIMESTAMP:
      if (value == null) {
        unsetVersionTimestamp();
      } else {
        setVersionTimestamp((Long)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case VERSION_ID:
      return Long.valueOf(getVersionId());

    case VERSION_TAG:
      return getVersionTag();

    case VERSION_TIMESTAMP:
      return Long.valueOf(getVersionTimestamp());

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case VERSION_ID:
      return isSetVersionId();
    case VERSION_TAG:
      return isSetVersionTag();
    case VERSION_TIMESTAMP:
      return isSetVersionTimestamp();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof VersionInfo)
      return this.equals((VersionInfo)that);
    return false;
  }

  public boolean equals(VersionInfo that) {
    if (that == null)
      return false;

    boolean this_present_versionId = true && this.isSetVersionId();
    boolean that_present_versionId = true && that.isSetVersionId();
    if (this_present_versionId || that_present_versionId) {
      if (!(this_present_versionId && that_present_versionId))
        return false;
      if (this.versionId != that.versionId)
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

    boolean this_present_versionTimestamp = true && this.isSetVersionTimestamp();
    boolean that_present_versionTimestamp = true && that.isSetVersionTimestamp();
    if (this_present_versionTimestamp || that_present_versionTimestamp) {
      if (!(this_present_versionTimestamp && that_present_versionTimestamp))
        return false;
      if (this.versionTimestamp != that.versionTimestamp)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  @Override
  public int compareTo(VersionInfo other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetVersionId()).compareTo(other.isSetVersionId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetVersionId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.versionId, other.versionId);
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
    lastComparison = Boolean.valueOf(isSetVersionTimestamp()).compareTo(other.isSetVersionTimestamp());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetVersionTimestamp()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.versionTimestamp, other.versionTimestamp);
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
    StringBuilder sb = new StringBuilder("VersionInfo(");
    boolean first = true;

    if (isSetVersionId()) {
      sb.append("versionId:");
      sb.append(this.versionId);
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
    if (isSetVersionTimestamp()) {
      if (!first) sb.append(", ");
      sb.append("versionTimestamp:");
      sb.append(this.versionTimestamp);
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

  private static class VersionInfoStandardSchemeFactory implements SchemeFactory {
    public VersionInfoStandardScheme getScheme() {
      return new VersionInfoStandardScheme();
    }
  }

  private static class VersionInfoStandardScheme extends StandardScheme<VersionInfo> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, VersionInfo struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // VERSION_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.versionId = iprot.readI64();
              struct.setVersionIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // VERSION_TAG
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.versionTag = iprot.readString();
              struct.setVersionTagIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // VERSION_TIMESTAMP
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.versionTimestamp = iprot.readI64();
              struct.setVersionTimestampIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, VersionInfo struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.isSetVersionId()) {
        oprot.writeFieldBegin(VERSION_ID_FIELD_DESC);
        oprot.writeI64(struct.versionId);
        oprot.writeFieldEnd();
      }
      if (struct.versionTag != null) {
        if (struct.isSetVersionTag()) {
          oprot.writeFieldBegin(VERSION_TAG_FIELD_DESC);
          oprot.writeString(struct.versionTag);
          oprot.writeFieldEnd();
        }
      }
      if (struct.isSetVersionTimestamp()) {
        oprot.writeFieldBegin(VERSION_TIMESTAMP_FIELD_DESC);
        oprot.writeI64(struct.versionTimestamp);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class VersionInfoTupleSchemeFactory implements SchemeFactory {
    public VersionInfoTupleScheme getScheme() {
      return new VersionInfoTupleScheme();
    }
  }

  private static class VersionInfoTupleScheme extends TupleScheme<VersionInfo> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, VersionInfo struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetVersionId()) {
        optionals.set(0);
      }
      if (struct.isSetVersionTag()) {
        optionals.set(1);
      }
      if (struct.isSetVersionTimestamp()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetVersionId()) {
        oprot.writeI64(struct.versionId);
      }
      if (struct.isSetVersionTag()) {
        oprot.writeString(struct.versionTag);
      }
      if (struct.isSetVersionTimestamp()) {
        oprot.writeI64(struct.versionTimestamp);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, VersionInfo struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.versionId = iprot.readI64();
        struct.setVersionIdIsSet(true);
      }
      if (incoming.get(1)) {
        struct.versionTag = iprot.readString();
        struct.setVersionTagIsSet(true);
      }
      if (incoming.get(2)) {
        struct.versionTimestamp = iprot.readI64();
        struct.setVersionTimestampIsSet(true);
      }
    }
  }

}

