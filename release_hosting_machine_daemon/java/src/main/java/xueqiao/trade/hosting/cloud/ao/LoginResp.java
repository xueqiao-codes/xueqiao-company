/**
 * Autogenerated by Thrift Compiler (0.9.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package xueqiao.trade.hosting.cloud.ao;

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

public class LoginResp implements org.apache.thrift.TBase<LoginResp, LoginResp._Fields>, java.io.Serializable, Cloneable, Comparable<LoginResp> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("LoginResp");

  private static final org.apache.thrift.protocol.TField SESSION_FIELD_DESC = new org.apache.thrift.protocol.TField("session", org.apache.thrift.protocol.TType.STRUCT, (short)1);
  private static final org.apache.thrift.protocol.TField HOSTING_PROXY_PORT_FIELD_DESC = new org.apache.thrift.protocol.TField("hostingProxyPort", org.apache.thrift.protocol.TType.I32, (short)3);
  private static final org.apache.thrift.protocol.TField HOSTING_TIMENS_FIELD_DESC = new org.apache.thrift.protocol.TField("hostingTimens", org.apache.thrift.protocol.TType.I64, (short)4);
  private static final org.apache.thrift.protocol.TField RUNNING_MODE_FIELD_DESC = new org.apache.thrift.protocol.TField("runningMode", org.apache.thrift.protocol.TType.I32, (short)5);
  private static final org.apache.thrift.protocol.TField LOGIN_USER_INFO_FIELD_DESC = new org.apache.thrift.protocol.TField("loginUserInfo", org.apache.thrift.protocol.TType.STRUCT, (short)6);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new LoginRespStandardSchemeFactory());
    schemes.put(TupleScheme.class, new LoginRespTupleSchemeFactory());
  }

  public xueqiao.trade.hosting.HostingSession session; // optional
  public int hostingProxyPort; // optional
  public long hostingTimens; // optional
  /**
   * 
   * @see xueqiao.trade.hosting.HostingRunningMode
   */
  public xueqiao.trade.hosting.HostingRunningMode runningMode; // optional
  public xueqiao.trade.hosting.HostingUser loginUserInfo; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    SESSION((short)1, "session"),
    HOSTING_PROXY_PORT((short)3, "hostingProxyPort"),
    HOSTING_TIMENS((short)4, "hostingTimens"),
    /**
     * 
     * @see xueqiao.trade.hosting.HostingRunningMode
     */
    RUNNING_MODE((short)5, "runningMode"),
    LOGIN_USER_INFO((short)6, "loginUserInfo");

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
        case 1: // SESSION
          return SESSION;
        case 3: // HOSTING_PROXY_PORT
          return HOSTING_PROXY_PORT;
        case 4: // HOSTING_TIMENS
          return HOSTING_TIMENS;
        case 5: // RUNNING_MODE
          return RUNNING_MODE;
        case 6: // LOGIN_USER_INFO
          return LOGIN_USER_INFO;
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
  private static final int __HOSTINGPROXYPORT_ISSET_ID = 0;
  private static final int __HOSTINGTIMENS_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  private _Fields optionals[] = {_Fields.SESSION,_Fields.HOSTING_PROXY_PORT,_Fields.HOSTING_TIMENS,_Fields.RUNNING_MODE,_Fields.LOGIN_USER_INFO};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.SESSION, new org.apache.thrift.meta_data.FieldMetaData("session", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, xueqiao.trade.hosting.HostingSession.class)));
    tmpMap.put(_Fields.HOSTING_PROXY_PORT, new org.apache.thrift.meta_data.FieldMetaData("hostingProxyPort", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.HOSTING_TIMENS, new org.apache.thrift.meta_data.FieldMetaData("hostingTimens", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.RUNNING_MODE, new org.apache.thrift.meta_data.FieldMetaData("runningMode", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, xueqiao.trade.hosting.HostingRunningMode.class)));
    tmpMap.put(_Fields.LOGIN_USER_INFO, new org.apache.thrift.meta_data.FieldMetaData("loginUserInfo", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, xueqiao.trade.hosting.HostingUser.class)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(LoginResp.class, metaDataMap);
  }

  public LoginResp() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public LoginResp(LoginResp other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetSession()) {
      this.session = new xueqiao.trade.hosting.HostingSession(other.session);
    }
    this.hostingProxyPort = other.hostingProxyPort;
    this.hostingTimens = other.hostingTimens;
    if (other.isSetRunningMode()) {
      this.runningMode = other.runningMode;
    }
    if (other.isSetLoginUserInfo()) {
      this.loginUserInfo = new xueqiao.trade.hosting.HostingUser(other.loginUserInfo);
    }
  }

  public LoginResp deepCopy() {
    return new LoginResp(this);
  }

  @Override
  public void clear() {
    this.session = null;
    setHostingProxyPortIsSet(false);
    this.hostingProxyPort = 0;
    setHostingTimensIsSet(false);
    this.hostingTimens = 0;
    this.runningMode = null;
    this.loginUserInfo = null;
  }

  public xueqiao.trade.hosting.HostingSession getSession() {
    return this.session;
  }

  public LoginResp setSession(xueqiao.trade.hosting.HostingSession session) {
    this.session = session;
    return this;
  }

  public void unsetSession() {
    this.session = null;
  }

  /** Returns true if field session is set (has been assigned a value) and false otherwise */
  public boolean isSetSession() {
    return this.session != null;
  }

  public void setSessionIsSet(boolean value) {
    if (!value) {
      this.session = null;
    }
  }

  public int getHostingProxyPort() {
    return this.hostingProxyPort;
  }

  public LoginResp setHostingProxyPort(int hostingProxyPort) {
    this.hostingProxyPort = hostingProxyPort;
    setHostingProxyPortIsSet(true);
    return this;
  }

  public void unsetHostingProxyPort() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __HOSTINGPROXYPORT_ISSET_ID);
  }

  /** Returns true if field hostingProxyPort is set (has been assigned a value) and false otherwise */
  public boolean isSetHostingProxyPort() {
    return EncodingUtils.testBit(__isset_bitfield, __HOSTINGPROXYPORT_ISSET_ID);
  }

  public void setHostingProxyPortIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __HOSTINGPROXYPORT_ISSET_ID, value);
  }

  public long getHostingTimens() {
    return this.hostingTimens;
  }

  public LoginResp setHostingTimens(long hostingTimens) {
    this.hostingTimens = hostingTimens;
    setHostingTimensIsSet(true);
    return this;
  }

  public void unsetHostingTimens() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __HOSTINGTIMENS_ISSET_ID);
  }

  /** Returns true if field hostingTimens is set (has been assigned a value) and false otherwise */
  public boolean isSetHostingTimens() {
    return EncodingUtils.testBit(__isset_bitfield, __HOSTINGTIMENS_ISSET_ID);
  }

  public void setHostingTimensIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __HOSTINGTIMENS_ISSET_ID, value);
  }

  /**
   * 
   * @see xueqiao.trade.hosting.HostingRunningMode
   */
  public xueqiao.trade.hosting.HostingRunningMode getRunningMode() {
    return this.runningMode;
  }

  /**
   * 
   * @see xueqiao.trade.hosting.HostingRunningMode
   */
  public LoginResp setRunningMode(xueqiao.trade.hosting.HostingRunningMode runningMode) {
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

  public xueqiao.trade.hosting.HostingUser getLoginUserInfo() {
    return this.loginUserInfo;
  }

  public LoginResp setLoginUserInfo(xueqiao.trade.hosting.HostingUser loginUserInfo) {
    this.loginUserInfo = loginUserInfo;
    return this;
  }

  public void unsetLoginUserInfo() {
    this.loginUserInfo = null;
  }

  /** Returns true if field loginUserInfo is set (has been assigned a value) and false otherwise */
  public boolean isSetLoginUserInfo() {
    return this.loginUserInfo != null;
  }

  public void setLoginUserInfoIsSet(boolean value) {
    if (!value) {
      this.loginUserInfo = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case SESSION:
      if (value == null) {
        unsetSession();
      } else {
        setSession((xueqiao.trade.hosting.HostingSession)value);
      }
      break;

    case HOSTING_PROXY_PORT:
      if (value == null) {
        unsetHostingProxyPort();
      } else {
        setHostingProxyPort((Integer)value);
      }
      break;

    case HOSTING_TIMENS:
      if (value == null) {
        unsetHostingTimens();
      } else {
        setHostingTimens((Long)value);
      }
      break;

    case RUNNING_MODE:
      if (value == null) {
        unsetRunningMode();
      } else {
        setRunningMode((xueqiao.trade.hosting.HostingRunningMode)value);
      }
      break;

    case LOGIN_USER_INFO:
      if (value == null) {
        unsetLoginUserInfo();
      } else {
        setLoginUserInfo((xueqiao.trade.hosting.HostingUser)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case SESSION:
      return getSession();

    case HOSTING_PROXY_PORT:
      return Integer.valueOf(getHostingProxyPort());

    case HOSTING_TIMENS:
      return Long.valueOf(getHostingTimens());

    case RUNNING_MODE:
      return getRunningMode();

    case LOGIN_USER_INFO:
      return getLoginUserInfo();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case SESSION:
      return isSetSession();
    case HOSTING_PROXY_PORT:
      return isSetHostingProxyPort();
    case HOSTING_TIMENS:
      return isSetHostingTimens();
    case RUNNING_MODE:
      return isSetRunningMode();
    case LOGIN_USER_INFO:
      return isSetLoginUserInfo();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof LoginResp)
      return this.equals((LoginResp)that);
    return false;
  }

  public boolean equals(LoginResp that) {
    if (that == null)
      return false;

    boolean this_present_session = true && this.isSetSession();
    boolean that_present_session = true && that.isSetSession();
    if (this_present_session || that_present_session) {
      if (!(this_present_session && that_present_session))
        return false;
      if (!this.session.equals(that.session))
        return false;
    }

    boolean this_present_hostingProxyPort = true && this.isSetHostingProxyPort();
    boolean that_present_hostingProxyPort = true && that.isSetHostingProxyPort();
    if (this_present_hostingProxyPort || that_present_hostingProxyPort) {
      if (!(this_present_hostingProxyPort && that_present_hostingProxyPort))
        return false;
      if (this.hostingProxyPort != that.hostingProxyPort)
        return false;
    }

    boolean this_present_hostingTimens = true && this.isSetHostingTimens();
    boolean that_present_hostingTimens = true && that.isSetHostingTimens();
    if (this_present_hostingTimens || that_present_hostingTimens) {
      if (!(this_present_hostingTimens && that_present_hostingTimens))
        return false;
      if (this.hostingTimens != that.hostingTimens)
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

    boolean this_present_loginUserInfo = true && this.isSetLoginUserInfo();
    boolean that_present_loginUserInfo = true && that.isSetLoginUserInfo();
    if (this_present_loginUserInfo || that_present_loginUserInfo) {
      if (!(this_present_loginUserInfo && that_present_loginUserInfo))
        return false;
      if (!this.loginUserInfo.equals(that.loginUserInfo))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  @Override
  public int compareTo(LoginResp other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetSession()).compareTo(other.isSetSession());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSession()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.session, other.session);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetHostingProxyPort()).compareTo(other.isSetHostingProxyPort());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetHostingProxyPort()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.hostingProxyPort, other.hostingProxyPort);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetHostingTimens()).compareTo(other.isSetHostingTimens());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetHostingTimens()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.hostingTimens, other.hostingTimens);
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
    lastComparison = Boolean.valueOf(isSetLoginUserInfo()).compareTo(other.isSetLoginUserInfo());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetLoginUserInfo()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.loginUserInfo, other.loginUserInfo);
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
    StringBuilder sb = new StringBuilder("LoginResp(");
    boolean first = true;

    if (isSetSession()) {
      sb.append("session:");
      if (this.session == null) {
        sb.append("null");
      } else {
        sb.append(this.session);
      }
      first = false;
    }
    if (isSetHostingProxyPort()) {
      if (!first) sb.append(", ");
      sb.append("hostingProxyPort:");
      sb.append(this.hostingProxyPort);
      first = false;
    }
    if (isSetHostingTimens()) {
      if (!first) sb.append(", ");
      sb.append("hostingTimens:");
      sb.append(this.hostingTimens);
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
    if (isSetLoginUserInfo()) {
      if (!first) sb.append(", ");
      sb.append("loginUserInfo:");
      if (this.loginUserInfo == null) {
        sb.append("null");
      } else {
        sb.append(this.loginUserInfo);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
    if (session != null) {
      session.validate();
    }
    if (loginUserInfo != null) {
      loginUserInfo.validate();
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

  private static class LoginRespStandardSchemeFactory implements SchemeFactory {
    public LoginRespStandardScheme getScheme() {
      return new LoginRespStandardScheme();
    }
  }

  private static class LoginRespStandardScheme extends StandardScheme<LoginResp> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, LoginResp struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // SESSION
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.session = new xueqiao.trade.hosting.HostingSession();
              struct.session.read(iprot);
              struct.setSessionIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // HOSTING_PROXY_PORT
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.hostingProxyPort = iprot.readI32();
              struct.setHostingProxyPortIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // HOSTING_TIMENS
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.hostingTimens = iprot.readI64();
              struct.setHostingTimensIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // RUNNING_MODE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.runningMode = xueqiao.trade.hosting.HostingRunningMode.findByValue(iprot.readI32());
              struct.setRunningModeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // LOGIN_USER_INFO
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.loginUserInfo = new xueqiao.trade.hosting.HostingUser();
              struct.loginUserInfo.read(iprot);
              struct.setLoginUserInfoIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, LoginResp struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.session != null) {
        if (struct.isSetSession()) {
          oprot.writeFieldBegin(SESSION_FIELD_DESC);
          struct.session.write(oprot);
          oprot.writeFieldEnd();
        }
      }
      if (struct.isSetHostingProxyPort()) {
        oprot.writeFieldBegin(HOSTING_PROXY_PORT_FIELD_DESC);
        oprot.writeI32(struct.hostingProxyPort);
        oprot.writeFieldEnd();
      }
      if (struct.isSetHostingTimens()) {
        oprot.writeFieldBegin(HOSTING_TIMENS_FIELD_DESC);
        oprot.writeI64(struct.hostingTimens);
        oprot.writeFieldEnd();
      }
      if (struct.runningMode != null) {
        if (struct.isSetRunningMode()) {
          oprot.writeFieldBegin(RUNNING_MODE_FIELD_DESC);
          oprot.writeI32(struct.runningMode.getValue());
          oprot.writeFieldEnd();
        }
      }
      if (struct.loginUserInfo != null) {
        if (struct.isSetLoginUserInfo()) {
          oprot.writeFieldBegin(LOGIN_USER_INFO_FIELD_DESC);
          struct.loginUserInfo.write(oprot);
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class LoginRespTupleSchemeFactory implements SchemeFactory {
    public LoginRespTupleScheme getScheme() {
      return new LoginRespTupleScheme();
    }
  }

  private static class LoginRespTupleScheme extends TupleScheme<LoginResp> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, LoginResp struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetSession()) {
        optionals.set(0);
      }
      if (struct.isSetHostingProxyPort()) {
        optionals.set(1);
      }
      if (struct.isSetHostingTimens()) {
        optionals.set(2);
      }
      if (struct.isSetRunningMode()) {
        optionals.set(3);
      }
      if (struct.isSetLoginUserInfo()) {
        optionals.set(4);
      }
      oprot.writeBitSet(optionals, 5);
      if (struct.isSetSession()) {
        struct.session.write(oprot);
      }
      if (struct.isSetHostingProxyPort()) {
        oprot.writeI32(struct.hostingProxyPort);
      }
      if (struct.isSetHostingTimens()) {
        oprot.writeI64(struct.hostingTimens);
      }
      if (struct.isSetRunningMode()) {
        oprot.writeI32(struct.runningMode.getValue());
      }
      if (struct.isSetLoginUserInfo()) {
        struct.loginUserInfo.write(oprot);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, LoginResp struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(5);
      if (incoming.get(0)) {
        struct.session = new xueqiao.trade.hosting.HostingSession();
        struct.session.read(iprot);
        struct.setSessionIsSet(true);
      }
      if (incoming.get(1)) {
        struct.hostingProxyPort = iprot.readI32();
        struct.setHostingProxyPortIsSet(true);
      }
      if (incoming.get(2)) {
        struct.hostingTimens = iprot.readI64();
        struct.setHostingTimensIsSet(true);
      }
      if (incoming.get(3)) {
        struct.runningMode = xueqiao.trade.hosting.HostingRunningMode.findByValue(iprot.readI32());
        struct.setRunningModeIsSet(true);
      }
      if (incoming.get(4)) {
        struct.loginUserInfo = new xueqiao.trade.hosting.HostingUser();
        struct.loginUserInfo.read(iprot);
        struct.setLoginUserInfoIsSet(true);
      }
    }
  }

}
