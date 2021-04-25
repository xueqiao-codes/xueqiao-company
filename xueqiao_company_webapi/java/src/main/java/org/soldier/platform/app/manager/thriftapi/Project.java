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

public class Project implements org.apache.thrift.TBase<Project, Project._Fields>, java.io.Serializable, Cloneable, Comparable<Project> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("Project");

  private static final org.apache.thrift.protocol.TField PROJECT_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("projectId", org.apache.thrift.protocol.TType.I64, (short)1);
  private static final org.apache.thrift.protocol.TField PROJECT_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("projectName", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField NOTE_FIELD_DESC = new org.apache.thrift.protocol.TField("note", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField CREATE_TIMESTAMP_FIELD_DESC = new org.apache.thrift.protocol.TField("createTimestamp", org.apache.thrift.protocol.TType.I64, (short)4);
  private static final org.apache.thrift.protocol.TField LAST_MODIFY_TIMESTAMP_FIELD_DESC = new org.apache.thrift.protocol.TField("lastModifyTimestamp", org.apache.thrift.protocol.TType.I64, (short)5);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new ProjectStandardSchemeFactory());
    schemes.put(TupleScheme.class, new ProjectTupleSchemeFactory());
  }

  public long projectId; // optional
  public String projectName; // optional
  public String note; // optional
  public long createTimestamp; // optional
  public long lastModifyTimestamp; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    PROJECT_ID((short)1, "projectId"),
    PROJECT_NAME((short)2, "projectName"),
    NOTE((short)3, "note"),
    CREATE_TIMESTAMP((short)4, "createTimestamp"),
    LAST_MODIFY_TIMESTAMP((short)5, "lastModifyTimestamp");

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
        case 1: // PROJECT_ID
          return PROJECT_ID;
        case 2: // PROJECT_NAME
          return PROJECT_NAME;
        case 3: // NOTE
          return NOTE;
        case 4: // CREATE_TIMESTAMP
          return CREATE_TIMESTAMP;
        case 5: // LAST_MODIFY_TIMESTAMP
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
  private static final int __PROJECTID_ISSET_ID = 0;
  private static final int __CREATETIMESTAMP_ISSET_ID = 1;
  private static final int __LASTMODIFYTIMESTAMP_ISSET_ID = 2;
  private byte __isset_bitfield = 0;
  private _Fields optionals[] = {_Fields.PROJECT_ID,_Fields.PROJECT_NAME,_Fields.NOTE,_Fields.CREATE_TIMESTAMP,_Fields.LAST_MODIFY_TIMESTAMP};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.PROJECT_ID, new org.apache.thrift.meta_data.FieldMetaData("projectId", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.PROJECT_NAME, new org.apache.thrift.meta_data.FieldMetaData("projectName", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.NOTE, new org.apache.thrift.meta_data.FieldMetaData("note", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.CREATE_TIMESTAMP, new org.apache.thrift.meta_data.FieldMetaData("createTimestamp", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.LAST_MODIFY_TIMESTAMP, new org.apache.thrift.meta_data.FieldMetaData("lastModifyTimestamp", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(Project.class, metaDataMap);
  }

  public Project() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public Project(Project other) {
    __isset_bitfield = other.__isset_bitfield;
    this.projectId = other.projectId;
    if (other.isSetProjectName()) {
      this.projectName = other.projectName;
    }
    if (other.isSetNote()) {
      this.note = other.note;
    }
    this.createTimestamp = other.createTimestamp;
    this.lastModifyTimestamp = other.lastModifyTimestamp;
  }

  public Project deepCopy() {
    return new Project(this);
  }

  @Override
  public void clear() {
    setProjectIdIsSet(false);
    this.projectId = 0;
    this.projectName = null;
    this.note = null;
    setCreateTimestampIsSet(false);
    this.createTimestamp = 0;
    setLastModifyTimestampIsSet(false);
    this.lastModifyTimestamp = 0;
  }

  public long getProjectId() {
    return this.projectId;
  }

  public Project setProjectId(long projectId) {
    this.projectId = projectId;
    setProjectIdIsSet(true);
    return this;
  }

  public void unsetProjectId() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __PROJECTID_ISSET_ID);
  }

  /** Returns true if field projectId is set (has been assigned a value) and false otherwise */
  public boolean isSetProjectId() {
    return EncodingUtils.testBit(__isset_bitfield, __PROJECTID_ISSET_ID);
  }

  public void setProjectIdIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __PROJECTID_ISSET_ID, value);
  }

  public String getProjectName() {
    return this.projectName;
  }

  public Project setProjectName(String projectName) {
    this.projectName = projectName;
    return this;
  }

  public void unsetProjectName() {
    this.projectName = null;
  }

  /** Returns true if field projectName is set (has been assigned a value) and false otherwise */
  public boolean isSetProjectName() {
    return this.projectName != null;
  }

  public void setProjectNameIsSet(boolean value) {
    if (!value) {
      this.projectName = null;
    }
  }

  public String getNote() {
    return this.note;
  }

  public Project setNote(String note) {
    this.note = note;
    return this;
  }

  public void unsetNote() {
    this.note = null;
  }

  /** Returns true if field note is set (has been assigned a value) and false otherwise */
  public boolean isSetNote() {
    return this.note != null;
  }

  public void setNoteIsSet(boolean value) {
    if (!value) {
      this.note = null;
    }
  }

  public long getCreateTimestamp() {
    return this.createTimestamp;
  }

  public Project setCreateTimestamp(long createTimestamp) {
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

  public Project setLastModifyTimestamp(long lastModifyTimestamp) {
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
    case PROJECT_ID:
      if (value == null) {
        unsetProjectId();
      } else {
        setProjectId((Long)value);
      }
      break;

    case PROJECT_NAME:
      if (value == null) {
        unsetProjectName();
      } else {
        setProjectName((String)value);
      }
      break;

    case NOTE:
      if (value == null) {
        unsetNote();
      } else {
        setNote((String)value);
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
    case PROJECT_ID:
      return Long.valueOf(getProjectId());

    case PROJECT_NAME:
      return getProjectName();

    case NOTE:
      return getNote();

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
    case PROJECT_ID:
      return isSetProjectId();
    case PROJECT_NAME:
      return isSetProjectName();
    case NOTE:
      return isSetNote();
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
    if (that instanceof Project)
      return this.equals((Project)that);
    return false;
  }

  public boolean equals(Project that) {
    if (that == null)
      return false;

    boolean this_present_projectId = true && this.isSetProjectId();
    boolean that_present_projectId = true && that.isSetProjectId();
    if (this_present_projectId || that_present_projectId) {
      if (!(this_present_projectId && that_present_projectId))
        return false;
      if (this.projectId != that.projectId)
        return false;
    }

    boolean this_present_projectName = true && this.isSetProjectName();
    boolean that_present_projectName = true && that.isSetProjectName();
    if (this_present_projectName || that_present_projectName) {
      if (!(this_present_projectName && that_present_projectName))
        return false;
      if (!this.projectName.equals(that.projectName))
        return false;
    }

    boolean this_present_note = true && this.isSetNote();
    boolean that_present_note = true && that.isSetNote();
    if (this_present_note || that_present_note) {
      if (!(this_present_note && that_present_note))
        return false;
      if (!this.note.equals(that.note))
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
  public int compareTo(Project other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetProjectId()).compareTo(other.isSetProjectId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetProjectId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.projectId, other.projectId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetProjectName()).compareTo(other.isSetProjectName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetProjectName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.projectName, other.projectName);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetNote()).compareTo(other.isSetNote());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetNote()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.note, other.note);
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
    StringBuilder sb = new StringBuilder("Project(");
    boolean first = true;

    if (isSetProjectId()) {
      sb.append("projectId:");
      sb.append(this.projectId);
      first = false;
    }
    if (isSetProjectName()) {
      if (!first) sb.append(", ");
      sb.append("projectName:");
      if (this.projectName == null) {
        sb.append("null");
      } else {
        sb.append(this.projectName);
      }
      first = false;
    }
    if (isSetNote()) {
      if (!first) sb.append(", ");
      sb.append("note:");
      if (this.note == null) {
        sb.append("null");
      } else {
        sb.append(this.note);
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

  private static class ProjectStandardSchemeFactory implements SchemeFactory {
    public ProjectStandardScheme getScheme() {
      return new ProjectStandardScheme();
    }
  }

  private static class ProjectStandardScheme extends StandardScheme<Project> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, Project struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // PROJECT_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.projectId = iprot.readI64();
              struct.setProjectIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // PROJECT_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.projectName = iprot.readString();
              struct.setProjectNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // NOTE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.note = iprot.readString();
              struct.setNoteIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // CREATE_TIMESTAMP
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.createTimestamp = iprot.readI64();
              struct.setCreateTimestampIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // LAST_MODIFY_TIMESTAMP
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, Project struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.isSetProjectId()) {
        oprot.writeFieldBegin(PROJECT_ID_FIELD_DESC);
        oprot.writeI64(struct.projectId);
        oprot.writeFieldEnd();
      }
      if (struct.projectName != null) {
        if (struct.isSetProjectName()) {
          oprot.writeFieldBegin(PROJECT_NAME_FIELD_DESC);
          oprot.writeString(struct.projectName);
          oprot.writeFieldEnd();
        }
      }
      if (struct.note != null) {
        if (struct.isSetNote()) {
          oprot.writeFieldBegin(NOTE_FIELD_DESC);
          oprot.writeString(struct.note);
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

  private static class ProjectTupleSchemeFactory implements SchemeFactory {
    public ProjectTupleScheme getScheme() {
      return new ProjectTupleScheme();
    }
  }

  private static class ProjectTupleScheme extends TupleScheme<Project> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, Project struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetProjectId()) {
        optionals.set(0);
      }
      if (struct.isSetProjectName()) {
        optionals.set(1);
      }
      if (struct.isSetNote()) {
        optionals.set(2);
      }
      if (struct.isSetCreateTimestamp()) {
        optionals.set(3);
      }
      if (struct.isSetLastModifyTimestamp()) {
        optionals.set(4);
      }
      oprot.writeBitSet(optionals, 5);
      if (struct.isSetProjectId()) {
        oprot.writeI64(struct.projectId);
      }
      if (struct.isSetProjectName()) {
        oprot.writeString(struct.projectName);
      }
      if (struct.isSetNote()) {
        oprot.writeString(struct.note);
      }
      if (struct.isSetCreateTimestamp()) {
        oprot.writeI64(struct.createTimestamp);
      }
      if (struct.isSetLastModifyTimestamp()) {
        oprot.writeI64(struct.lastModifyTimestamp);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, Project struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(5);
      if (incoming.get(0)) {
        struct.projectId = iprot.readI64();
        struct.setProjectIdIsSet(true);
      }
      if (incoming.get(1)) {
        struct.projectName = iprot.readString();
        struct.setProjectNameIsSet(true);
      }
      if (incoming.get(2)) {
        struct.note = iprot.readString();
        struct.setNoteIsSet(true);
      }
      if (incoming.get(3)) {
        struct.createTimestamp = iprot.readI64();
        struct.setCreateTimestampIsSet(true);
      }
      if (incoming.get(4)) {
        struct.lastModifyTimestamp = iprot.readI64();
        struct.setLastModifyTimestampIsSet(true);
      }
    }
  }

}
