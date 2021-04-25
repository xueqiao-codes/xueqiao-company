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
 * 资金账户
 */
public class AssetAccount implements org.apache.thrift.TBase<AssetAccount, AssetAccount._Fields>, java.io.Serializable, Cloneable, Comparable<AssetAccount> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("AssetAccount");

  private static final org.apache.thrift.protocol.TField ACCOUNT_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("accountName", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField PASSWORD_FIELD_DESC = new org.apache.thrift.protocol.TField("password", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField NICK_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("nickName", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField AUTHORIZATION_CODE_FIELD_DESC = new org.apache.thrift.protocol.TField("authorizationCode", org.apache.thrift.protocol.TType.STRING, (short)4);
  private static final org.apache.thrift.protocol.TField BROKER_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("brokerId", org.apache.thrift.protocol.TType.I64, (short)5);
  private static final org.apache.thrift.protocol.TField BROKER_ACCESS_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("brokerAccessId", org.apache.thrift.protocol.TType.I64, (short)6);
  private static final org.apache.thrift.protocol.TField EXTRA_INFO_FIELD_DESC = new org.apache.thrift.protocol.TField("extraInfo", org.apache.thrift.protocol.TType.MAP, (short)7);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new AssetAccountStandardSchemeFactory());
    schemes.put(TupleScheme.class, new AssetAccountTupleSchemeFactory());
  }

  public String accountName; // optional
  public String password; // optional
  public String nickName; // optional
  public String authorizationCode; // optional
  public long brokerId; // optional
  public long brokerAccessId; // optional
  public Map<String,String> extraInfo; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ACCOUNT_NAME((short)1, "accountName"),
    PASSWORD((short)2, "password"),
    NICK_NAME((short)3, "nickName"),
    AUTHORIZATION_CODE((short)4, "authorizationCode"),
    BROKER_ID((short)5, "brokerId"),
    BROKER_ACCESS_ID((short)6, "brokerAccessId"),
    EXTRA_INFO((short)7, "extraInfo");

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
        case 1: // ACCOUNT_NAME
          return ACCOUNT_NAME;
        case 2: // PASSWORD
          return PASSWORD;
        case 3: // NICK_NAME
          return NICK_NAME;
        case 4: // AUTHORIZATION_CODE
          return AUTHORIZATION_CODE;
        case 5: // BROKER_ID
          return BROKER_ID;
        case 6: // BROKER_ACCESS_ID
          return BROKER_ACCESS_ID;
        case 7: // EXTRA_INFO
          return EXTRA_INFO;
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
  private static final int __BROKERID_ISSET_ID = 0;
  private static final int __BROKERACCESSID_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  private _Fields optionals[] = {_Fields.ACCOUNT_NAME,_Fields.PASSWORD,_Fields.NICK_NAME,_Fields.AUTHORIZATION_CODE,_Fields.BROKER_ID,_Fields.BROKER_ACCESS_ID,_Fields.EXTRA_INFO};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ACCOUNT_NAME, new org.apache.thrift.meta_data.FieldMetaData("accountName", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.PASSWORD, new org.apache.thrift.meta_data.FieldMetaData("password", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.NICK_NAME, new org.apache.thrift.meta_data.FieldMetaData("nickName", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.AUTHORIZATION_CODE, new org.apache.thrift.meta_data.FieldMetaData("authorizationCode", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.BROKER_ID, new org.apache.thrift.meta_data.FieldMetaData("brokerId", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.BROKER_ACCESS_ID, new org.apache.thrift.meta_data.FieldMetaData("brokerAccessId", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.EXTRA_INFO, new org.apache.thrift.meta_data.FieldMetaData("extraInfo", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.MapMetaData(org.apache.thrift.protocol.TType.MAP, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING), 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING))));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(AssetAccount.class, metaDataMap);
  }

  public AssetAccount() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public AssetAccount(AssetAccount other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetAccountName()) {
      this.accountName = other.accountName;
    }
    if (other.isSetPassword()) {
      this.password = other.password;
    }
    if (other.isSetNickName()) {
      this.nickName = other.nickName;
    }
    if (other.isSetAuthorizationCode()) {
      this.authorizationCode = other.authorizationCode;
    }
    this.brokerId = other.brokerId;
    this.brokerAccessId = other.brokerAccessId;
    if (other.isSetExtraInfo()) {
      Map<String,String> __this__extraInfo = new HashMap<String,String>(other.extraInfo);
      this.extraInfo = __this__extraInfo;
    }
  }

  public AssetAccount deepCopy() {
    return new AssetAccount(this);
  }

  @Override
  public void clear() {
    this.accountName = null;
    this.password = null;
    this.nickName = null;
    this.authorizationCode = null;
    setBrokerIdIsSet(false);
    this.brokerId = 0;
    setBrokerAccessIdIsSet(false);
    this.brokerAccessId = 0;
    this.extraInfo = null;
  }

  public String getAccountName() {
    return this.accountName;
  }

  public AssetAccount setAccountName(String accountName) {
    this.accountName = accountName;
    return this;
  }

  public void unsetAccountName() {
    this.accountName = null;
  }

  /** Returns true if field accountName is set (has been assigned a value) and false otherwise */
  public boolean isSetAccountName() {
    return this.accountName != null;
  }

  public void setAccountNameIsSet(boolean value) {
    if (!value) {
      this.accountName = null;
    }
  }

  public String getPassword() {
    return this.password;
  }

  public AssetAccount setPassword(String password) {
    this.password = password;
    return this;
  }

  public void unsetPassword() {
    this.password = null;
  }

  /** Returns true if field password is set (has been assigned a value) and false otherwise */
  public boolean isSetPassword() {
    return this.password != null;
  }

  public void setPasswordIsSet(boolean value) {
    if (!value) {
      this.password = null;
    }
  }

  public String getNickName() {
    return this.nickName;
  }

  public AssetAccount setNickName(String nickName) {
    this.nickName = nickName;
    return this;
  }

  public void unsetNickName() {
    this.nickName = null;
  }

  /** Returns true if field nickName is set (has been assigned a value) and false otherwise */
  public boolean isSetNickName() {
    return this.nickName != null;
  }

  public void setNickNameIsSet(boolean value) {
    if (!value) {
      this.nickName = null;
    }
  }

  public String getAuthorizationCode() {
    return this.authorizationCode;
  }

  public AssetAccount setAuthorizationCode(String authorizationCode) {
    this.authorizationCode = authorizationCode;
    return this;
  }

  public void unsetAuthorizationCode() {
    this.authorizationCode = null;
  }

  /** Returns true if field authorizationCode is set (has been assigned a value) and false otherwise */
  public boolean isSetAuthorizationCode() {
    return this.authorizationCode != null;
  }

  public void setAuthorizationCodeIsSet(boolean value) {
    if (!value) {
      this.authorizationCode = null;
    }
  }

  public long getBrokerId() {
    return this.brokerId;
  }

  public AssetAccount setBrokerId(long brokerId) {
    this.brokerId = brokerId;
    setBrokerIdIsSet(true);
    return this;
  }

  public void unsetBrokerId() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __BROKERID_ISSET_ID);
  }

  /** Returns true if field brokerId is set (has been assigned a value) and false otherwise */
  public boolean isSetBrokerId() {
    return EncodingUtils.testBit(__isset_bitfield, __BROKERID_ISSET_ID);
  }

  public void setBrokerIdIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __BROKERID_ISSET_ID, value);
  }

  public long getBrokerAccessId() {
    return this.brokerAccessId;
  }

  public AssetAccount setBrokerAccessId(long brokerAccessId) {
    this.brokerAccessId = brokerAccessId;
    setBrokerAccessIdIsSet(true);
    return this;
  }

  public void unsetBrokerAccessId() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __BROKERACCESSID_ISSET_ID);
  }

  /** Returns true if field brokerAccessId is set (has been assigned a value) and false otherwise */
  public boolean isSetBrokerAccessId() {
    return EncodingUtils.testBit(__isset_bitfield, __BROKERACCESSID_ISSET_ID);
  }

  public void setBrokerAccessIdIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __BROKERACCESSID_ISSET_ID, value);
  }

  public int getExtraInfoSize() {
    return (this.extraInfo == null) ? 0 : this.extraInfo.size();
  }

  public void putToExtraInfo(String key, String val) {
    if (this.extraInfo == null) {
      this.extraInfo = new HashMap<String,String>();
    }
    this.extraInfo.put(key, val);
  }

  public Map<String,String> getExtraInfo() {
    return this.extraInfo;
  }

  public AssetAccount setExtraInfo(Map<String,String> extraInfo) {
    this.extraInfo = extraInfo;
    return this;
  }

  public void unsetExtraInfo() {
    this.extraInfo = null;
  }

  /** Returns true if field extraInfo is set (has been assigned a value) and false otherwise */
  public boolean isSetExtraInfo() {
    return this.extraInfo != null;
  }

  public void setExtraInfoIsSet(boolean value) {
    if (!value) {
      this.extraInfo = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case ACCOUNT_NAME:
      if (value == null) {
        unsetAccountName();
      } else {
        setAccountName((String)value);
      }
      break;

    case PASSWORD:
      if (value == null) {
        unsetPassword();
      } else {
        setPassword((String)value);
      }
      break;

    case NICK_NAME:
      if (value == null) {
        unsetNickName();
      } else {
        setNickName((String)value);
      }
      break;

    case AUTHORIZATION_CODE:
      if (value == null) {
        unsetAuthorizationCode();
      } else {
        setAuthorizationCode((String)value);
      }
      break;

    case BROKER_ID:
      if (value == null) {
        unsetBrokerId();
      } else {
        setBrokerId((Long)value);
      }
      break;

    case BROKER_ACCESS_ID:
      if (value == null) {
        unsetBrokerAccessId();
      } else {
        setBrokerAccessId((Long)value);
      }
      break;

    case EXTRA_INFO:
      if (value == null) {
        unsetExtraInfo();
      } else {
        setExtraInfo((Map<String,String>)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case ACCOUNT_NAME:
      return getAccountName();

    case PASSWORD:
      return getPassword();

    case NICK_NAME:
      return getNickName();

    case AUTHORIZATION_CODE:
      return getAuthorizationCode();

    case BROKER_ID:
      return Long.valueOf(getBrokerId());

    case BROKER_ACCESS_ID:
      return Long.valueOf(getBrokerAccessId());

    case EXTRA_INFO:
      return getExtraInfo();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case ACCOUNT_NAME:
      return isSetAccountName();
    case PASSWORD:
      return isSetPassword();
    case NICK_NAME:
      return isSetNickName();
    case AUTHORIZATION_CODE:
      return isSetAuthorizationCode();
    case BROKER_ID:
      return isSetBrokerId();
    case BROKER_ACCESS_ID:
      return isSetBrokerAccessId();
    case EXTRA_INFO:
      return isSetExtraInfo();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof AssetAccount)
      return this.equals((AssetAccount)that);
    return false;
  }

  public boolean equals(AssetAccount that) {
    if (that == null)
      return false;

    boolean this_present_accountName = true && this.isSetAccountName();
    boolean that_present_accountName = true && that.isSetAccountName();
    if (this_present_accountName || that_present_accountName) {
      if (!(this_present_accountName && that_present_accountName))
        return false;
      if (!this.accountName.equals(that.accountName))
        return false;
    }

    boolean this_present_password = true && this.isSetPassword();
    boolean that_present_password = true && that.isSetPassword();
    if (this_present_password || that_present_password) {
      if (!(this_present_password && that_present_password))
        return false;
      if (!this.password.equals(that.password))
        return false;
    }

    boolean this_present_nickName = true && this.isSetNickName();
    boolean that_present_nickName = true && that.isSetNickName();
    if (this_present_nickName || that_present_nickName) {
      if (!(this_present_nickName && that_present_nickName))
        return false;
      if (!this.nickName.equals(that.nickName))
        return false;
    }

    boolean this_present_authorizationCode = true && this.isSetAuthorizationCode();
    boolean that_present_authorizationCode = true && that.isSetAuthorizationCode();
    if (this_present_authorizationCode || that_present_authorizationCode) {
      if (!(this_present_authorizationCode && that_present_authorizationCode))
        return false;
      if (!this.authorizationCode.equals(that.authorizationCode))
        return false;
    }

    boolean this_present_brokerId = true && this.isSetBrokerId();
    boolean that_present_brokerId = true && that.isSetBrokerId();
    if (this_present_brokerId || that_present_brokerId) {
      if (!(this_present_brokerId && that_present_brokerId))
        return false;
      if (this.brokerId != that.brokerId)
        return false;
    }

    boolean this_present_brokerAccessId = true && this.isSetBrokerAccessId();
    boolean that_present_brokerAccessId = true && that.isSetBrokerAccessId();
    if (this_present_brokerAccessId || that_present_brokerAccessId) {
      if (!(this_present_brokerAccessId && that_present_brokerAccessId))
        return false;
      if (this.brokerAccessId != that.brokerAccessId)
        return false;
    }

    boolean this_present_extraInfo = true && this.isSetExtraInfo();
    boolean that_present_extraInfo = true && that.isSetExtraInfo();
    if (this_present_extraInfo || that_present_extraInfo) {
      if (!(this_present_extraInfo && that_present_extraInfo))
        return false;
      if (!this.extraInfo.equals(that.extraInfo))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  @Override
  public int compareTo(AssetAccount other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetAccountName()).compareTo(other.isSetAccountName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAccountName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.accountName, other.accountName);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetPassword()).compareTo(other.isSetPassword());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPassword()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.password, other.password);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetNickName()).compareTo(other.isSetNickName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetNickName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.nickName, other.nickName);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetAuthorizationCode()).compareTo(other.isSetAuthorizationCode());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAuthorizationCode()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.authorizationCode, other.authorizationCode);
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
    lastComparison = Boolean.valueOf(isSetBrokerAccessId()).compareTo(other.isSetBrokerAccessId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetBrokerAccessId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.brokerAccessId, other.brokerAccessId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetExtraInfo()).compareTo(other.isSetExtraInfo());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetExtraInfo()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.extraInfo, other.extraInfo);
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
    StringBuilder sb = new StringBuilder("AssetAccount(");
    boolean first = true;

    if (isSetAccountName()) {
      sb.append("accountName:");
      if (this.accountName == null) {
        sb.append("null");
      } else {
        sb.append(this.accountName);
      }
      first = false;
    }
    if (isSetPassword()) {
      if (!first) sb.append(", ");
      sb.append("password:");
      if (this.password == null) {
        sb.append("null");
      } else {
        sb.append(this.password);
      }
      first = false;
    }
    if (isSetNickName()) {
      if (!first) sb.append(", ");
      sb.append("nickName:");
      if (this.nickName == null) {
        sb.append("null");
      } else {
        sb.append(this.nickName);
      }
      first = false;
    }
    if (isSetAuthorizationCode()) {
      if (!first) sb.append(", ");
      sb.append("authorizationCode:");
      if (this.authorizationCode == null) {
        sb.append("null");
      } else {
        sb.append(this.authorizationCode);
      }
      first = false;
    }
    if (isSetBrokerId()) {
      if (!first) sb.append(", ");
      sb.append("brokerId:");
      sb.append(this.brokerId);
      first = false;
    }
    if (isSetBrokerAccessId()) {
      if (!first) sb.append(", ");
      sb.append("brokerAccessId:");
      sb.append(this.brokerAccessId);
      first = false;
    }
    if (isSetExtraInfo()) {
      if (!first) sb.append(", ");
      sb.append("extraInfo:");
      if (this.extraInfo == null) {
        sb.append("null");
      } else {
        sb.append(this.extraInfo);
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

  private static class AssetAccountStandardSchemeFactory implements SchemeFactory {
    public AssetAccountStandardScheme getScheme() {
      return new AssetAccountStandardScheme();
    }
  }

  private static class AssetAccountStandardScheme extends StandardScheme<AssetAccount> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, AssetAccount struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // ACCOUNT_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.accountName = iprot.readString();
              struct.setAccountNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // PASSWORD
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.password = iprot.readString();
              struct.setPasswordIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // NICK_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.nickName = iprot.readString();
              struct.setNickNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // AUTHORIZATION_CODE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.authorizationCode = iprot.readString();
              struct.setAuthorizationCodeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // BROKER_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.brokerId = iprot.readI64();
              struct.setBrokerIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // BROKER_ACCESS_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.brokerAccessId = iprot.readI64();
              struct.setBrokerAccessIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 7: // EXTRA_INFO
            if (schemeField.type == org.apache.thrift.protocol.TType.MAP) {
              {
                org.apache.thrift.protocol.TMap _map10 = iprot.readMapBegin();
                struct.extraInfo = new HashMap<String,String>(2*_map10.size);
                for (int _i11 = 0; _i11 < _map10.size; ++_i11)
                {
                  String _key12;
                  String _val13;
                  _key12 = iprot.readString();
                  _val13 = iprot.readString();
                  struct.extraInfo.put(_key12, _val13);
                }
                iprot.readMapEnd();
              }
              struct.setExtraInfoIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, AssetAccount struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.accountName != null) {
        if (struct.isSetAccountName()) {
          oprot.writeFieldBegin(ACCOUNT_NAME_FIELD_DESC);
          oprot.writeString(struct.accountName);
          oprot.writeFieldEnd();
        }
      }
      if (struct.password != null) {
        if (struct.isSetPassword()) {
          oprot.writeFieldBegin(PASSWORD_FIELD_DESC);
          oprot.writeString(struct.password);
          oprot.writeFieldEnd();
        }
      }
      if (struct.nickName != null) {
        if (struct.isSetNickName()) {
          oprot.writeFieldBegin(NICK_NAME_FIELD_DESC);
          oprot.writeString(struct.nickName);
          oprot.writeFieldEnd();
        }
      }
      if (struct.authorizationCode != null) {
        if (struct.isSetAuthorizationCode()) {
          oprot.writeFieldBegin(AUTHORIZATION_CODE_FIELD_DESC);
          oprot.writeString(struct.authorizationCode);
          oprot.writeFieldEnd();
        }
      }
      if (struct.isSetBrokerId()) {
        oprot.writeFieldBegin(BROKER_ID_FIELD_DESC);
        oprot.writeI64(struct.brokerId);
        oprot.writeFieldEnd();
      }
      if (struct.isSetBrokerAccessId()) {
        oprot.writeFieldBegin(BROKER_ACCESS_ID_FIELD_DESC);
        oprot.writeI64(struct.brokerAccessId);
        oprot.writeFieldEnd();
      }
      if (struct.extraInfo != null) {
        if (struct.isSetExtraInfo()) {
          oprot.writeFieldBegin(EXTRA_INFO_FIELD_DESC);
          {
            oprot.writeMapBegin(new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.STRING, org.apache.thrift.protocol.TType.STRING, struct.extraInfo.size()));
            for (Map.Entry<String, String> _iter14 : struct.extraInfo.entrySet())
            {
              oprot.writeString(_iter14.getKey());
              oprot.writeString(_iter14.getValue());
            }
            oprot.writeMapEnd();
          }
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class AssetAccountTupleSchemeFactory implements SchemeFactory {
    public AssetAccountTupleScheme getScheme() {
      return new AssetAccountTupleScheme();
    }
  }

  private static class AssetAccountTupleScheme extends TupleScheme<AssetAccount> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, AssetAccount struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetAccountName()) {
        optionals.set(0);
      }
      if (struct.isSetPassword()) {
        optionals.set(1);
      }
      if (struct.isSetNickName()) {
        optionals.set(2);
      }
      if (struct.isSetAuthorizationCode()) {
        optionals.set(3);
      }
      if (struct.isSetBrokerId()) {
        optionals.set(4);
      }
      if (struct.isSetBrokerAccessId()) {
        optionals.set(5);
      }
      if (struct.isSetExtraInfo()) {
        optionals.set(6);
      }
      oprot.writeBitSet(optionals, 7);
      if (struct.isSetAccountName()) {
        oprot.writeString(struct.accountName);
      }
      if (struct.isSetPassword()) {
        oprot.writeString(struct.password);
      }
      if (struct.isSetNickName()) {
        oprot.writeString(struct.nickName);
      }
      if (struct.isSetAuthorizationCode()) {
        oprot.writeString(struct.authorizationCode);
      }
      if (struct.isSetBrokerId()) {
        oprot.writeI64(struct.brokerId);
      }
      if (struct.isSetBrokerAccessId()) {
        oprot.writeI64(struct.brokerAccessId);
      }
      if (struct.isSetExtraInfo()) {
        {
          oprot.writeI32(struct.extraInfo.size());
          for (Map.Entry<String, String> _iter15 : struct.extraInfo.entrySet())
          {
            oprot.writeString(_iter15.getKey());
            oprot.writeString(_iter15.getValue());
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, AssetAccount struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(7);
      if (incoming.get(0)) {
        struct.accountName = iprot.readString();
        struct.setAccountNameIsSet(true);
      }
      if (incoming.get(1)) {
        struct.password = iprot.readString();
        struct.setPasswordIsSet(true);
      }
      if (incoming.get(2)) {
        struct.nickName = iprot.readString();
        struct.setNickNameIsSet(true);
      }
      if (incoming.get(3)) {
        struct.authorizationCode = iprot.readString();
        struct.setAuthorizationCodeIsSet(true);
      }
      if (incoming.get(4)) {
        struct.brokerId = iprot.readI64();
        struct.setBrokerIdIsSet(true);
      }
      if (incoming.get(5)) {
        struct.brokerAccessId = iprot.readI64();
        struct.setBrokerAccessIdIsSet(true);
      }
      if (incoming.get(6)) {
        {
          org.apache.thrift.protocol.TMap _map16 = new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.STRING, org.apache.thrift.protocol.TType.STRING, iprot.readI32());
          struct.extraInfo = new HashMap<String,String>(2*_map16.size);
          for (int _i17 = 0; _i17 < _map16.size; ++_i17)
          {
            String _key18;
            String _val19;
            _key18 = iprot.readString();
            _val19 = iprot.readString();
            struct.extraInfo.put(_key18, _val19);
          }
        }
        struct.setExtraInfoIsSet(true);
      }
    }
  }

}

