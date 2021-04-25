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

public class QueryGroupUserExOption implements org.apache.thrift.TBase<QueryGroupUserExOption, QueryGroupUserExOption._Fields>, java.io.Serializable, Cloneable, Comparable<QueryGroupUserExOption> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("QueryGroupUserExOption");

  private static final org.apache.thrift.protocol.TField COMPANY_IDS_FIELD_DESC = new org.apache.thrift.protocol.TField("companyIds", org.apache.thrift.protocol.TType.SET, (short)1);
  private static final org.apache.thrift.protocol.TField GROUP_IDS_FIELD_DESC = new org.apache.thrift.protocol.TField("groupIds", org.apache.thrift.protocol.TType.SET, (short)2);
  private static final org.apache.thrift.protocol.TField ROLES_FIELD_DESC = new org.apache.thrift.protocol.TField("roles", org.apache.thrift.protocol.TType.SET, (short)3);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new QueryGroupUserExOptionStandardSchemeFactory());
    schemes.put(TupleScheme.class, new QueryGroupUserExOptionTupleSchemeFactory());
  }

  public Set<Integer> companyIds; // optional
  public Set<Integer> groupIds; // optional
  public Set<OperatorRole> roles; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    COMPANY_IDS((short)1, "companyIds"),
    GROUP_IDS((short)2, "groupIds"),
    ROLES((short)3, "roles");

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
        case 1: // COMPANY_IDS
          return COMPANY_IDS;
        case 2: // GROUP_IDS
          return GROUP_IDS;
        case 3: // ROLES
          return ROLES;
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
  private _Fields optionals[] = {_Fields.COMPANY_IDS,_Fields.GROUP_IDS,_Fields.ROLES};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.COMPANY_IDS, new org.apache.thrift.meta_data.FieldMetaData("companyIds", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.SetMetaData(org.apache.thrift.protocol.TType.SET, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32))));
    tmpMap.put(_Fields.GROUP_IDS, new org.apache.thrift.meta_data.FieldMetaData("groupIds", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.SetMetaData(org.apache.thrift.protocol.TType.SET, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32))));
    tmpMap.put(_Fields.ROLES, new org.apache.thrift.meta_data.FieldMetaData("roles", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.SetMetaData(org.apache.thrift.protocol.TType.SET, 
            new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, OperatorRole.class))));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(QueryGroupUserExOption.class, metaDataMap);
  }

  public QueryGroupUserExOption() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public QueryGroupUserExOption(QueryGroupUserExOption other) {
    if (other.isSetCompanyIds()) {
      Set<Integer> __this__companyIds = new HashSet<Integer>(other.companyIds);
      this.companyIds = __this__companyIds;
    }
    if (other.isSetGroupIds()) {
      Set<Integer> __this__groupIds = new HashSet<Integer>(other.groupIds);
      this.groupIds = __this__groupIds;
    }
    if (other.isSetRoles()) {
      Set<OperatorRole> __this__roles = new HashSet<OperatorRole>(other.roles.size());
      for (OperatorRole other_element : other.roles) {
        __this__roles.add(other_element);
      }
      this.roles = __this__roles;
    }
  }

  public QueryGroupUserExOption deepCopy() {
    return new QueryGroupUserExOption(this);
  }

  @Override
  public void clear() {
    this.companyIds = null;
    this.groupIds = null;
    this.roles = null;
  }

  public int getCompanyIdsSize() {
    return (this.companyIds == null) ? 0 : this.companyIds.size();
  }

  public java.util.Iterator<Integer> getCompanyIdsIterator() {
    return (this.companyIds == null) ? null : this.companyIds.iterator();
  }

  public void addToCompanyIds(int elem) {
    if (this.companyIds == null) {
      this.companyIds = new HashSet<Integer>();
    }
    this.companyIds.add(elem);
  }

  public Set<Integer> getCompanyIds() {
    return this.companyIds;
  }

  public QueryGroupUserExOption setCompanyIds(Set<Integer> companyIds) {
    this.companyIds = companyIds;
    return this;
  }

  public void unsetCompanyIds() {
    this.companyIds = null;
  }

  /** Returns true if field companyIds is set (has been assigned a value) and false otherwise */
  public boolean isSetCompanyIds() {
    return this.companyIds != null;
  }

  public void setCompanyIdsIsSet(boolean value) {
    if (!value) {
      this.companyIds = null;
    }
  }

  public int getGroupIdsSize() {
    return (this.groupIds == null) ? 0 : this.groupIds.size();
  }

  public java.util.Iterator<Integer> getGroupIdsIterator() {
    return (this.groupIds == null) ? null : this.groupIds.iterator();
  }

  public void addToGroupIds(int elem) {
    if (this.groupIds == null) {
      this.groupIds = new HashSet<Integer>();
    }
    this.groupIds.add(elem);
  }

  public Set<Integer> getGroupIds() {
    return this.groupIds;
  }

  public QueryGroupUserExOption setGroupIds(Set<Integer> groupIds) {
    this.groupIds = groupIds;
    return this;
  }

  public void unsetGroupIds() {
    this.groupIds = null;
  }

  /** Returns true if field groupIds is set (has been assigned a value) and false otherwise */
  public boolean isSetGroupIds() {
    return this.groupIds != null;
  }

  public void setGroupIdsIsSet(boolean value) {
    if (!value) {
      this.groupIds = null;
    }
  }

  public int getRolesSize() {
    return (this.roles == null) ? 0 : this.roles.size();
  }

  public java.util.Iterator<OperatorRole> getRolesIterator() {
    return (this.roles == null) ? null : this.roles.iterator();
  }

  public void addToRoles(OperatorRole elem) {
    if (this.roles == null) {
      this.roles = new HashSet<OperatorRole>();
    }
    this.roles.add(elem);
  }

  public Set<OperatorRole> getRoles() {
    return this.roles;
  }

  public QueryGroupUserExOption setRoles(Set<OperatorRole> roles) {
    this.roles = roles;
    return this;
  }

  public void unsetRoles() {
    this.roles = null;
  }

  /** Returns true if field roles is set (has been assigned a value) and false otherwise */
  public boolean isSetRoles() {
    return this.roles != null;
  }

  public void setRolesIsSet(boolean value) {
    if (!value) {
      this.roles = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case COMPANY_IDS:
      if (value == null) {
        unsetCompanyIds();
      } else {
        setCompanyIds((Set<Integer>)value);
      }
      break;

    case GROUP_IDS:
      if (value == null) {
        unsetGroupIds();
      } else {
        setGroupIds((Set<Integer>)value);
      }
      break;

    case ROLES:
      if (value == null) {
        unsetRoles();
      } else {
        setRoles((Set<OperatorRole>)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case COMPANY_IDS:
      return getCompanyIds();

    case GROUP_IDS:
      return getGroupIds();

    case ROLES:
      return getRoles();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case COMPANY_IDS:
      return isSetCompanyIds();
    case GROUP_IDS:
      return isSetGroupIds();
    case ROLES:
      return isSetRoles();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof QueryGroupUserExOption)
      return this.equals((QueryGroupUserExOption)that);
    return false;
  }

  public boolean equals(QueryGroupUserExOption that) {
    if (that == null)
      return false;

    boolean this_present_companyIds = true && this.isSetCompanyIds();
    boolean that_present_companyIds = true && that.isSetCompanyIds();
    if (this_present_companyIds || that_present_companyIds) {
      if (!(this_present_companyIds && that_present_companyIds))
        return false;
      if (!this.companyIds.equals(that.companyIds))
        return false;
    }

    boolean this_present_groupIds = true && this.isSetGroupIds();
    boolean that_present_groupIds = true && that.isSetGroupIds();
    if (this_present_groupIds || that_present_groupIds) {
      if (!(this_present_groupIds && that_present_groupIds))
        return false;
      if (!this.groupIds.equals(that.groupIds))
        return false;
    }

    boolean this_present_roles = true && this.isSetRoles();
    boolean that_present_roles = true && that.isSetRoles();
    if (this_present_roles || that_present_roles) {
      if (!(this_present_roles && that_present_roles))
        return false;
      if (!this.roles.equals(that.roles))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  @Override
  public int compareTo(QueryGroupUserExOption other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetCompanyIds()).compareTo(other.isSetCompanyIds());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCompanyIds()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.companyIds, other.companyIds);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetGroupIds()).compareTo(other.isSetGroupIds());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetGroupIds()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.groupIds, other.groupIds);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetRoles()).compareTo(other.isSetRoles());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRoles()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.roles, other.roles);
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
    StringBuilder sb = new StringBuilder("QueryGroupUserExOption(");
    boolean first = true;

    if (isSetCompanyIds()) {
      sb.append("companyIds:");
      if (this.companyIds == null) {
        sb.append("null");
      } else {
        sb.append(this.companyIds);
      }
      first = false;
    }
    if (isSetGroupIds()) {
      if (!first) sb.append(", ");
      sb.append("groupIds:");
      if (this.groupIds == null) {
        sb.append("null");
      } else {
        sb.append(this.groupIds);
      }
      first = false;
    }
    if (isSetRoles()) {
      if (!first) sb.append(", ");
      sb.append("roles:");
      if (this.roles == null) {
        sb.append("null");
      } else {
        sb.append(this.roles);
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
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class QueryGroupUserExOptionStandardSchemeFactory implements SchemeFactory {
    public QueryGroupUserExOptionStandardScheme getScheme() {
      return new QueryGroupUserExOptionStandardScheme();
    }
  }

  private static class QueryGroupUserExOptionStandardScheme extends StandardScheme<QueryGroupUserExOption> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, QueryGroupUserExOption struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // COMPANY_IDS
            if (schemeField.type == org.apache.thrift.protocol.TType.SET) {
              {
                org.apache.thrift.protocol.TSet _set40 = iprot.readSetBegin();
                struct.companyIds = new HashSet<Integer>(2*_set40.size);
                for (int _i41 = 0; _i41 < _set40.size; ++_i41)
                {
                  int _elem42;
                  _elem42 = iprot.readI32();
                  struct.companyIds.add(_elem42);
                }
                iprot.readSetEnd();
              }
              struct.setCompanyIdsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // GROUP_IDS
            if (schemeField.type == org.apache.thrift.protocol.TType.SET) {
              {
                org.apache.thrift.protocol.TSet _set43 = iprot.readSetBegin();
                struct.groupIds = new HashSet<Integer>(2*_set43.size);
                for (int _i44 = 0; _i44 < _set43.size; ++_i44)
                {
                  int _elem45;
                  _elem45 = iprot.readI32();
                  struct.groupIds.add(_elem45);
                }
                iprot.readSetEnd();
              }
              struct.setGroupIdsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // ROLES
            if (schemeField.type == org.apache.thrift.protocol.TType.SET) {
              {
                org.apache.thrift.protocol.TSet _set46 = iprot.readSetBegin();
                struct.roles = new HashSet<OperatorRole>(2*_set46.size);
                for (int _i47 = 0; _i47 < _set46.size; ++_i47)
                {
                  OperatorRole _elem48;
                  _elem48 = OperatorRole.findByValue(iprot.readI32());
                  struct.roles.add(_elem48);
                }
                iprot.readSetEnd();
              }
              struct.setRolesIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, QueryGroupUserExOption struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.companyIds != null) {
        if (struct.isSetCompanyIds()) {
          oprot.writeFieldBegin(COMPANY_IDS_FIELD_DESC);
          {
            oprot.writeSetBegin(new org.apache.thrift.protocol.TSet(org.apache.thrift.protocol.TType.I32, struct.companyIds.size()));
            for (int _iter49 : struct.companyIds)
            {
              oprot.writeI32(_iter49);
            }
            oprot.writeSetEnd();
          }
          oprot.writeFieldEnd();
        }
      }
      if (struct.groupIds != null) {
        if (struct.isSetGroupIds()) {
          oprot.writeFieldBegin(GROUP_IDS_FIELD_DESC);
          {
            oprot.writeSetBegin(new org.apache.thrift.protocol.TSet(org.apache.thrift.protocol.TType.I32, struct.groupIds.size()));
            for (int _iter50 : struct.groupIds)
            {
              oprot.writeI32(_iter50);
            }
            oprot.writeSetEnd();
          }
          oprot.writeFieldEnd();
        }
      }
      if (struct.roles != null) {
        if (struct.isSetRoles()) {
          oprot.writeFieldBegin(ROLES_FIELD_DESC);
          {
            oprot.writeSetBegin(new org.apache.thrift.protocol.TSet(org.apache.thrift.protocol.TType.I32, struct.roles.size()));
            for (OperatorRole _iter51 : struct.roles)
            {
              oprot.writeI32(_iter51.getValue());
            }
            oprot.writeSetEnd();
          }
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class QueryGroupUserExOptionTupleSchemeFactory implements SchemeFactory {
    public QueryGroupUserExOptionTupleScheme getScheme() {
      return new QueryGroupUserExOptionTupleScheme();
    }
  }

  private static class QueryGroupUserExOptionTupleScheme extends TupleScheme<QueryGroupUserExOption> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, QueryGroupUserExOption struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetCompanyIds()) {
        optionals.set(0);
      }
      if (struct.isSetGroupIds()) {
        optionals.set(1);
      }
      if (struct.isSetRoles()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetCompanyIds()) {
        {
          oprot.writeI32(struct.companyIds.size());
          for (int _iter52 : struct.companyIds)
          {
            oprot.writeI32(_iter52);
          }
        }
      }
      if (struct.isSetGroupIds()) {
        {
          oprot.writeI32(struct.groupIds.size());
          for (int _iter53 : struct.groupIds)
          {
            oprot.writeI32(_iter53);
          }
        }
      }
      if (struct.isSetRoles()) {
        {
          oprot.writeI32(struct.roles.size());
          for (OperatorRole _iter54 : struct.roles)
          {
            oprot.writeI32(_iter54.getValue());
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, QueryGroupUserExOption struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        {
          org.apache.thrift.protocol.TSet _set55 = new org.apache.thrift.protocol.TSet(org.apache.thrift.protocol.TType.I32, iprot.readI32());
          struct.companyIds = new HashSet<Integer>(2*_set55.size);
          for (int _i56 = 0; _i56 < _set55.size; ++_i56)
          {
            int _elem57;
            _elem57 = iprot.readI32();
            struct.companyIds.add(_elem57);
          }
        }
        struct.setCompanyIdsIsSet(true);
      }
      if (incoming.get(1)) {
        {
          org.apache.thrift.protocol.TSet _set58 = new org.apache.thrift.protocol.TSet(org.apache.thrift.protocol.TType.I32, iprot.readI32());
          struct.groupIds = new HashSet<Integer>(2*_set58.size);
          for (int _i59 = 0; _i59 < _set58.size; ++_i59)
          {
            int _elem60;
            _elem60 = iprot.readI32();
            struct.groupIds.add(_elem60);
          }
        }
        struct.setGroupIdsIsSet(true);
      }
      if (incoming.get(2)) {
        {
          org.apache.thrift.protocol.TSet _set61 = new org.apache.thrift.protocol.TSet(org.apache.thrift.protocol.TType.I32, iprot.readI32());
          struct.roles = new HashSet<OperatorRole>(2*_set61.size);
          for (int _i62 = 0; _i62 < _set61.size; ++_i62)
          {
            OperatorRole _elem63;
            _elem63 = OperatorRole.findByValue(iprot.readI32());
            struct.roles.add(_elem63);
          }
        }
        struct.setRolesIsSet(true);
      }
    }
  }

}

