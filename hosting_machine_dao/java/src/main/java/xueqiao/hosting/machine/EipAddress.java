/**
 * Autogenerated by Thrift Compiler (0.9.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package xueqiao.hosting.machine;

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

public class EipAddress implements org.apache.thrift.TBase<EipAddress, EipAddress._Fields>, java.io.Serializable, Cloneable, Comparable<EipAddress> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("EipAddress");

  private static final org.apache.thrift.protocol.TField ALLOCATION_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("allocationId", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField IP_ADDRESS_FIELD_DESC = new org.apache.thrift.protocol.TField("ipAddress", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField BANDWIDTH_FIELD_DESC = new org.apache.thrift.protocol.TField("bandwidth", org.apache.thrift.protocol.TType.I32, (short)3);
  private static final org.apache.thrift.protocol.TField INTERNET_CHARGE_TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("internetChargeType", org.apache.thrift.protocol.TType.STRING, (short)4);
  private static final org.apache.thrift.protocol.TField IS_SUPPORT_UNASSOCIATE_FIELD_DESC = new org.apache.thrift.protocol.TField("isSupportUnassociate", org.apache.thrift.protocol.TType.BOOL, (short)5);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new EipAddressStandardSchemeFactory());
    schemes.put(TupleScheme.class, new EipAddressTupleSchemeFactory());
  }

  public String allocationId; // optional
  public String ipAddress; // optional
  public int bandwidth; // optional
  public String internetChargeType; // optional
  public boolean isSupportUnassociate; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ALLOCATION_ID((short)1, "allocationId"),
    IP_ADDRESS((short)2, "ipAddress"),
    BANDWIDTH((short)3, "bandwidth"),
    INTERNET_CHARGE_TYPE((short)4, "internetChargeType"),
    IS_SUPPORT_UNASSOCIATE((short)5, "isSupportUnassociate");

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
        case 1: // ALLOCATION_ID
          return ALLOCATION_ID;
        case 2: // IP_ADDRESS
          return IP_ADDRESS;
        case 3: // BANDWIDTH
          return BANDWIDTH;
        case 4: // INTERNET_CHARGE_TYPE
          return INTERNET_CHARGE_TYPE;
        case 5: // IS_SUPPORT_UNASSOCIATE
          return IS_SUPPORT_UNASSOCIATE;
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
  private static final int __BANDWIDTH_ISSET_ID = 0;
  private static final int __ISSUPPORTUNASSOCIATE_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  private _Fields optionals[] = {_Fields.ALLOCATION_ID,_Fields.IP_ADDRESS,_Fields.BANDWIDTH,_Fields.INTERNET_CHARGE_TYPE,_Fields.IS_SUPPORT_UNASSOCIATE};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ALLOCATION_ID, new org.apache.thrift.meta_data.FieldMetaData("allocationId", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.IP_ADDRESS, new org.apache.thrift.meta_data.FieldMetaData("ipAddress", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.BANDWIDTH, new org.apache.thrift.meta_data.FieldMetaData("bandwidth", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.INTERNET_CHARGE_TYPE, new org.apache.thrift.meta_data.FieldMetaData("internetChargeType", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.IS_SUPPORT_UNASSOCIATE, new org.apache.thrift.meta_data.FieldMetaData("isSupportUnassociate", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(EipAddress.class, metaDataMap);
  }

  public EipAddress() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public EipAddress(EipAddress other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetAllocationId()) {
      this.allocationId = other.allocationId;
    }
    if (other.isSetIpAddress()) {
      this.ipAddress = other.ipAddress;
    }
    this.bandwidth = other.bandwidth;
    if (other.isSetInternetChargeType()) {
      this.internetChargeType = other.internetChargeType;
    }
    this.isSupportUnassociate = other.isSupportUnassociate;
  }

  public EipAddress deepCopy() {
    return new EipAddress(this);
  }

  @Override
  public void clear() {
    this.allocationId = null;
    this.ipAddress = null;
    setBandwidthIsSet(false);
    this.bandwidth = 0;
    this.internetChargeType = null;
    setIsSupportUnassociateIsSet(false);
    this.isSupportUnassociate = false;
  }

  public String getAllocationId() {
    return this.allocationId;
  }

  public EipAddress setAllocationId(String allocationId) {
    this.allocationId = allocationId;
    return this;
  }

  public void unsetAllocationId() {
    this.allocationId = null;
  }

  /** Returns true if field allocationId is set (has been assigned a value) and false otherwise */
  public boolean isSetAllocationId() {
    return this.allocationId != null;
  }

  public void setAllocationIdIsSet(boolean value) {
    if (!value) {
      this.allocationId = null;
    }
  }

  public String getIpAddress() {
    return this.ipAddress;
  }

  public EipAddress setIpAddress(String ipAddress) {
    this.ipAddress = ipAddress;
    return this;
  }

  public void unsetIpAddress() {
    this.ipAddress = null;
  }

  /** Returns true if field ipAddress is set (has been assigned a value) and false otherwise */
  public boolean isSetIpAddress() {
    return this.ipAddress != null;
  }

  public void setIpAddressIsSet(boolean value) {
    if (!value) {
      this.ipAddress = null;
    }
  }

  public int getBandwidth() {
    return this.bandwidth;
  }

  public EipAddress setBandwidth(int bandwidth) {
    this.bandwidth = bandwidth;
    setBandwidthIsSet(true);
    return this;
  }

  public void unsetBandwidth() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __BANDWIDTH_ISSET_ID);
  }

  /** Returns true if field bandwidth is set (has been assigned a value) and false otherwise */
  public boolean isSetBandwidth() {
    return EncodingUtils.testBit(__isset_bitfield, __BANDWIDTH_ISSET_ID);
  }

  public void setBandwidthIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __BANDWIDTH_ISSET_ID, value);
  }

  public String getInternetChargeType() {
    return this.internetChargeType;
  }

  public EipAddress setInternetChargeType(String internetChargeType) {
    this.internetChargeType = internetChargeType;
    return this;
  }

  public void unsetInternetChargeType() {
    this.internetChargeType = null;
  }

  /** Returns true if field internetChargeType is set (has been assigned a value) and false otherwise */
  public boolean isSetInternetChargeType() {
    return this.internetChargeType != null;
  }

  public void setInternetChargeTypeIsSet(boolean value) {
    if (!value) {
      this.internetChargeType = null;
    }
  }

  public boolean isIsSupportUnassociate() {
    return this.isSupportUnassociate;
  }

  public EipAddress setIsSupportUnassociate(boolean isSupportUnassociate) {
    this.isSupportUnassociate = isSupportUnassociate;
    setIsSupportUnassociateIsSet(true);
    return this;
  }

  public void unsetIsSupportUnassociate() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __ISSUPPORTUNASSOCIATE_ISSET_ID);
  }

  /** Returns true if field isSupportUnassociate is set (has been assigned a value) and false otherwise */
  public boolean isSetIsSupportUnassociate() {
    return EncodingUtils.testBit(__isset_bitfield, __ISSUPPORTUNASSOCIATE_ISSET_ID);
  }

  public void setIsSupportUnassociateIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __ISSUPPORTUNASSOCIATE_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case ALLOCATION_ID:
      if (value == null) {
        unsetAllocationId();
      } else {
        setAllocationId((String)value);
      }
      break;

    case IP_ADDRESS:
      if (value == null) {
        unsetIpAddress();
      } else {
        setIpAddress((String)value);
      }
      break;

    case BANDWIDTH:
      if (value == null) {
        unsetBandwidth();
      } else {
        setBandwidth((Integer)value);
      }
      break;

    case INTERNET_CHARGE_TYPE:
      if (value == null) {
        unsetInternetChargeType();
      } else {
        setInternetChargeType((String)value);
      }
      break;

    case IS_SUPPORT_UNASSOCIATE:
      if (value == null) {
        unsetIsSupportUnassociate();
      } else {
        setIsSupportUnassociate((Boolean)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case ALLOCATION_ID:
      return getAllocationId();

    case IP_ADDRESS:
      return getIpAddress();

    case BANDWIDTH:
      return Integer.valueOf(getBandwidth());

    case INTERNET_CHARGE_TYPE:
      return getInternetChargeType();

    case IS_SUPPORT_UNASSOCIATE:
      return Boolean.valueOf(isIsSupportUnassociate());

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case ALLOCATION_ID:
      return isSetAllocationId();
    case IP_ADDRESS:
      return isSetIpAddress();
    case BANDWIDTH:
      return isSetBandwidth();
    case INTERNET_CHARGE_TYPE:
      return isSetInternetChargeType();
    case IS_SUPPORT_UNASSOCIATE:
      return isSetIsSupportUnassociate();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof EipAddress)
      return this.equals((EipAddress)that);
    return false;
  }

  public boolean equals(EipAddress that) {
    if (that == null)
      return false;

    boolean this_present_allocationId = true && this.isSetAllocationId();
    boolean that_present_allocationId = true && that.isSetAllocationId();
    if (this_present_allocationId || that_present_allocationId) {
      if (!(this_present_allocationId && that_present_allocationId))
        return false;
      if (!this.allocationId.equals(that.allocationId))
        return false;
    }

    boolean this_present_ipAddress = true && this.isSetIpAddress();
    boolean that_present_ipAddress = true && that.isSetIpAddress();
    if (this_present_ipAddress || that_present_ipAddress) {
      if (!(this_present_ipAddress && that_present_ipAddress))
        return false;
      if (!this.ipAddress.equals(that.ipAddress))
        return false;
    }

    boolean this_present_bandwidth = true && this.isSetBandwidth();
    boolean that_present_bandwidth = true && that.isSetBandwidth();
    if (this_present_bandwidth || that_present_bandwidth) {
      if (!(this_present_bandwidth && that_present_bandwidth))
        return false;
      if (this.bandwidth != that.bandwidth)
        return false;
    }

    boolean this_present_internetChargeType = true && this.isSetInternetChargeType();
    boolean that_present_internetChargeType = true && that.isSetInternetChargeType();
    if (this_present_internetChargeType || that_present_internetChargeType) {
      if (!(this_present_internetChargeType && that_present_internetChargeType))
        return false;
      if (!this.internetChargeType.equals(that.internetChargeType))
        return false;
    }

    boolean this_present_isSupportUnassociate = true && this.isSetIsSupportUnassociate();
    boolean that_present_isSupportUnassociate = true && that.isSetIsSupportUnassociate();
    if (this_present_isSupportUnassociate || that_present_isSupportUnassociate) {
      if (!(this_present_isSupportUnassociate && that_present_isSupportUnassociate))
        return false;
      if (this.isSupportUnassociate != that.isSupportUnassociate)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  @Override
  public int compareTo(EipAddress other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetAllocationId()).compareTo(other.isSetAllocationId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAllocationId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.allocationId, other.allocationId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetIpAddress()).compareTo(other.isSetIpAddress());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetIpAddress()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.ipAddress, other.ipAddress);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetBandwidth()).compareTo(other.isSetBandwidth());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetBandwidth()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.bandwidth, other.bandwidth);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetInternetChargeType()).compareTo(other.isSetInternetChargeType());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetInternetChargeType()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.internetChargeType, other.internetChargeType);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetIsSupportUnassociate()).compareTo(other.isSetIsSupportUnassociate());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetIsSupportUnassociate()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.isSupportUnassociate, other.isSupportUnassociate);
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
    StringBuilder sb = new StringBuilder("EipAddress(");
    boolean first = true;

    if (isSetAllocationId()) {
      sb.append("allocationId:");
      if (this.allocationId == null) {
        sb.append("null");
      } else {
        sb.append(this.allocationId);
      }
      first = false;
    }
    if (isSetIpAddress()) {
      if (!first) sb.append(", ");
      sb.append("ipAddress:");
      if (this.ipAddress == null) {
        sb.append("null");
      } else {
        sb.append(this.ipAddress);
      }
      first = false;
    }
    if (isSetBandwidth()) {
      if (!first) sb.append(", ");
      sb.append("bandwidth:");
      sb.append(this.bandwidth);
      first = false;
    }
    if (isSetInternetChargeType()) {
      if (!first) sb.append(", ");
      sb.append("internetChargeType:");
      if (this.internetChargeType == null) {
        sb.append("null");
      } else {
        sb.append(this.internetChargeType);
      }
      first = false;
    }
    if (isSetIsSupportUnassociate()) {
      if (!first) sb.append(", ");
      sb.append("isSupportUnassociate:");
      sb.append(this.isSupportUnassociate);
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

  private static class EipAddressStandardSchemeFactory implements SchemeFactory {
    public EipAddressStandardScheme getScheme() {
      return new EipAddressStandardScheme();
    }
  }

  private static class EipAddressStandardScheme extends StandardScheme<EipAddress> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, EipAddress struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // ALLOCATION_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.allocationId = iprot.readString();
              struct.setAllocationIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // IP_ADDRESS
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.ipAddress = iprot.readString();
              struct.setIpAddressIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // BANDWIDTH
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.bandwidth = iprot.readI32();
              struct.setBandwidthIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // INTERNET_CHARGE_TYPE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.internetChargeType = iprot.readString();
              struct.setInternetChargeTypeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // IS_SUPPORT_UNASSOCIATE
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.isSupportUnassociate = iprot.readBool();
              struct.setIsSupportUnassociateIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, EipAddress struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.allocationId != null) {
        if (struct.isSetAllocationId()) {
          oprot.writeFieldBegin(ALLOCATION_ID_FIELD_DESC);
          oprot.writeString(struct.allocationId);
          oprot.writeFieldEnd();
        }
      }
      if (struct.ipAddress != null) {
        if (struct.isSetIpAddress()) {
          oprot.writeFieldBegin(IP_ADDRESS_FIELD_DESC);
          oprot.writeString(struct.ipAddress);
          oprot.writeFieldEnd();
        }
      }
      if (struct.isSetBandwidth()) {
        oprot.writeFieldBegin(BANDWIDTH_FIELD_DESC);
        oprot.writeI32(struct.bandwidth);
        oprot.writeFieldEnd();
      }
      if (struct.internetChargeType != null) {
        if (struct.isSetInternetChargeType()) {
          oprot.writeFieldBegin(INTERNET_CHARGE_TYPE_FIELD_DESC);
          oprot.writeString(struct.internetChargeType);
          oprot.writeFieldEnd();
        }
      }
      if (struct.isSetIsSupportUnassociate()) {
        oprot.writeFieldBegin(IS_SUPPORT_UNASSOCIATE_FIELD_DESC);
        oprot.writeBool(struct.isSupportUnassociate);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class EipAddressTupleSchemeFactory implements SchemeFactory {
    public EipAddressTupleScheme getScheme() {
      return new EipAddressTupleScheme();
    }
  }

  private static class EipAddressTupleScheme extends TupleScheme<EipAddress> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, EipAddress struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetAllocationId()) {
        optionals.set(0);
      }
      if (struct.isSetIpAddress()) {
        optionals.set(1);
      }
      if (struct.isSetBandwidth()) {
        optionals.set(2);
      }
      if (struct.isSetInternetChargeType()) {
        optionals.set(3);
      }
      if (struct.isSetIsSupportUnassociate()) {
        optionals.set(4);
      }
      oprot.writeBitSet(optionals, 5);
      if (struct.isSetAllocationId()) {
        oprot.writeString(struct.allocationId);
      }
      if (struct.isSetIpAddress()) {
        oprot.writeString(struct.ipAddress);
      }
      if (struct.isSetBandwidth()) {
        oprot.writeI32(struct.bandwidth);
      }
      if (struct.isSetInternetChargeType()) {
        oprot.writeString(struct.internetChargeType);
      }
      if (struct.isSetIsSupportUnassociate()) {
        oprot.writeBool(struct.isSupportUnassociate);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, EipAddress struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(5);
      if (incoming.get(0)) {
        struct.allocationId = iprot.readString();
        struct.setAllocationIdIsSet(true);
      }
      if (incoming.get(1)) {
        struct.ipAddress = iprot.readString();
        struct.setIpAddressIsSet(true);
      }
      if (incoming.get(2)) {
        struct.bandwidth = iprot.readI32();
        struct.setBandwidthIsSet(true);
      }
      if (incoming.get(3)) {
        struct.internetChargeType = iprot.readString();
        struct.setInternetChargeTypeIsSet(true);
      }
      if (incoming.get(4)) {
        struct.isSupportUnassociate = iprot.readBool();
        struct.setIsSupportUnassociateIsSet(true);
      }
    }
  }

}

