/**
 * Autogenerated by Thrift Compiler (0.9.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.longsheng.xueqiao.common.thriftapi;

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

public class ContractBase implements org.apache.thrift.TBase<ContractBase, ContractBase._Fields>, java.io.Serializable, Cloneable, Comparable<ContractBase> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("ContractBase");

  private static final org.apache.thrift.protocol.TField CONTRACT_FIELD_DESC = new org.apache.thrift.protocol.TField("contract", org.apache.thrift.protocol.TType.STRUCT, (short)1);
  private static final org.apache.thrift.protocol.TField CONTRACT_ENG_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("contractEngName", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField CONTRACT_CN_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("contractCnName", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField CONTRACT_TC_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("contractTcName", org.apache.thrift.protocol.TType.STRING, (short)4);
  private static final org.apache.thrift.protocol.TField EXPRIED_DATE_FIELD_DESC = new org.apache.thrift.protocol.TField("expriedDate", org.apache.thrift.protocol.TType.I32, (short)5);
  private static final org.apache.thrift.protocol.TField LAST_TRADE_DATE_FIELD_DESC = new org.apache.thrift.protocol.TField("lastTradeDate", org.apache.thrift.protocol.TType.I32, (short)6);
  private static final org.apache.thrift.protocol.TField FIRST_NOTICE_DATE_FIELD_DESC = new org.apache.thrift.protocol.TField("firstNoticeDate", org.apache.thrift.protocol.TType.I32, (short)7);
  private static final org.apache.thrift.protocol.TField SLED_CONTRACT_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("sledContractId", org.apache.thrift.protocol.TType.I32, (short)8);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new ContractBaseStandardSchemeFactory());
    schemes.put(TupleScheme.class, new ContractBaseTupleSchemeFactory());
  }

  public Contract contract; // optional
  public String contractEngName; // optional
  public String contractCnName; // optional
  public String contractTcName; // optional
  public int expriedDate; // optional
  public int lastTradeDate; // optional
  public int firstNoticeDate; // optional
  public int sledContractId; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    CONTRACT((short)1, "contract"),
    CONTRACT_ENG_NAME((short)2, "contractEngName"),
    CONTRACT_CN_NAME((short)3, "contractCnName"),
    CONTRACT_TC_NAME((short)4, "contractTcName"),
    EXPRIED_DATE((short)5, "expriedDate"),
    LAST_TRADE_DATE((short)6, "lastTradeDate"),
    FIRST_NOTICE_DATE((short)7, "firstNoticeDate"),
    SLED_CONTRACT_ID((short)8, "sledContractId");

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
        case 1: // CONTRACT
          return CONTRACT;
        case 2: // CONTRACT_ENG_NAME
          return CONTRACT_ENG_NAME;
        case 3: // CONTRACT_CN_NAME
          return CONTRACT_CN_NAME;
        case 4: // CONTRACT_TC_NAME
          return CONTRACT_TC_NAME;
        case 5: // EXPRIED_DATE
          return EXPRIED_DATE;
        case 6: // LAST_TRADE_DATE
          return LAST_TRADE_DATE;
        case 7: // FIRST_NOTICE_DATE
          return FIRST_NOTICE_DATE;
        case 8: // SLED_CONTRACT_ID
          return SLED_CONTRACT_ID;
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
  private static final int __EXPRIEDDATE_ISSET_ID = 0;
  private static final int __LASTTRADEDATE_ISSET_ID = 1;
  private static final int __FIRSTNOTICEDATE_ISSET_ID = 2;
  private static final int __SLEDCONTRACTID_ISSET_ID = 3;
  private byte __isset_bitfield = 0;
  private _Fields optionals[] = {_Fields.CONTRACT,_Fields.CONTRACT_ENG_NAME,_Fields.CONTRACT_CN_NAME,_Fields.CONTRACT_TC_NAME,_Fields.EXPRIED_DATE,_Fields.LAST_TRADE_DATE,_Fields.FIRST_NOTICE_DATE,_Fields.SLED_CONTRACT_ID};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.CONTRACT, new org.apache.thrift.meta_data.FieldMetaData("contract", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, Contract.class)));
    tmpMap.put(_Fields.CONTRACT_ENG_NAME, new org.apache.thrift.meta_data.FieldMetaData("contractEngName", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.CONTRACT_CN_NAME, new org.apache.thrift.meta_data.FieldMetaData("contractCnName", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.CONTRACT_TC_NAME, new org.apache.thrift.meta_data.FieldMetaData("contractTcName", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.EXPRIED_DATE, new org.apache.thrift.meta_data.FieldMetaData("expriedDate", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.LAST_TRADE_DATE, new org.apache.thrift.meta_data.FieldMetaData("lastTradeDate", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.FIRST_NOTICE_DATE, new org.apache.thrift.meta_data.FieldMetaData("firstNoticeDate", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.SLED_CONTRACT_ID, new org.apache.thrift.meta_data.FieldMetaData("sledContractId", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(ContractBase.class, metaDataMap);
  }

  public ContractBase() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public ContractBase(ContractBase other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetContract()) {
      this.contract = new Contract(other.contract);
    }
    if (other.isSetContractEngName()) {
      this.contractEngName = other.contractEngName;
    }
    if (other.isSetContractCnName()) {
      this.contractCnName = other.contractCnName;
    }
    if (other.isSetContractTcName()) {
      this.contractTcName = other.contractTcName;
    }
    this.expriedDate = other.expriedDate;
    this.lastTradeDate = other.lastTradeDate;
    this.firstNoticeDate = other.firstNoticeDate;
    this.sledContractId = other.sledContractId;
  }

  public ContractBase deepCopy() {
    return new ContractBase(this);
  }

  @Override
  public void clear() {
    this.contract = null;
    this.contractEngName = null;
    this.contractCnName = null;
    this.contractTcName = null;
    setExpriedDateIsSet(false);
    this.expriedDate = 0;
    setLastTradeDateIsSet(false);
    this.lastTradeDate = 0;
    setFirstNoticeDateIsSet(false);
    this.firstNoticeDate = 0;
    setSledContractIdIsSet(false);
    this.sledContractId = 0;
  }

  public Contract getContract() {
    return this.contract;
  }

  public ContractBase setContract(Contract contract) {
    this.contract = contract;
    return this;
  }

  public void unsetContract() {
    this.contract = null;
  }

  /** Returns true if field contract is set (has been assigned a value) and false otherwise */
  public boolean isSetContract() {
    return this.contract != null;
  }

  public void setContractIsSet(boolean value) {
    if (!value) {
      this.contract = null;
    }
  }

  public String getContractEngName() {
    return this.contractEngName;
  }

  public ContractBase setContractEngName(String contractEngName) {
    this.contractEngName = contractEngName;
    return this;
  }

  public void unsetContractEngName() {
    this.contractEngName = null;
  }

  /** Returns true if field contractEngName is set (has been assigned a value) and false otherwise */
  public boolean isSetContractEngName() {
    return this.contractEngName != null;
  }

  public void setContractEngNameIsSet(boolean value) {
    if (!value) {
      this.contractEngName = null;
    }
  }

  public String getContractCnName() {
    return this.contractCnName;
  }

  public ContractBase setContractCnName(String contractCnName) {
    this.contractCnName = contractCnName;
    return this;
  }

  public void unsetContractCnName() {
    this.contractCnName = null;
  }

  /** Returns true if field contractCnName is set (has been assigned a value) and false otherwise */
  public boolean isSetContractCnName() {
    return this.contractCnName != null;
  }

  public void setContractCnNameIsSet(boolean value) {
    if (!value) {
      this.contractCnName = null;
    }
  }

  public String getContractTcName() {
    return this.contractTcName;
  }

  public ContractBase setContractTcName(String contractTcName) {
    this.contractTcName = contractTcName;
    return this;
  }

  public void unsetContractTcName() {
    this.contractTcName = null;
  }

  /** Returns true if field contractTcName is set (has been assigned a value) and false otherwise */
  public boolean isSetContractTcName() {
    return this.contractTcName != null;
  }

  public void setContractTcNameIsSet(boolean value) {
    if (!value) {
      this.contractTcName = null;
    }
  }

  public int getExpriedDate() {
    return this.expriedDate;
  }

  public ContractBase setExpriedDate(int expriedDate) {
    this.expriedDate = expriedDate;
    setExpriedDateIsSet(true);
    return this;
  }

  public void unsetExpriedDate() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __EXPRIEDDATE_ISSET_ID);
  }

  /** Returns true if field expriedDate is set (has been assigned a value) and false otherwise */
  public boolean isSetExpriedDate() {
    return EncodingUtils.testBit(__isset_bitfield, __EXPRIEDDATE_ISSET_ID);
  }

  public void setExpriedDateIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __EXPRIEDDATE_ISSET_ID, value);
  }

  public int getLastTradeDate() {
    return this.lastTradeDate;
  }

  public ContractBase setLastTradeDate(int lastTradeDate) {
    this.lastTradeDate = lastTradeDate;
    setLastTradeDateIsSet(true);
    return this;
  }

  public void unsetLastTradeDate() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __LASTTRADEDATE_ISSET_ID);
  }

  /** Returns true if field lastTradeDate is set (has been assigned a value) and false otherwise */
  public boolean isSetLastTradeDate() {
    return EncodingUtils.testBit(__isset_bitfield, __LASTTRADEDATE_ISSET_ID);
  }

  public void setLastTradeDateIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __LASTTRADEDATE_ISSET_ID, value);
  }

  public int getFirstNoticeDate() {
    return this.firstNoticeDate;
  }

  public ContractBase setFirstNoticeDate(int firstNoticeDate) {
    this.firstNoticeDate = firstNoticeDate;
    setFirstNoticeDateIsSet(true);
    return this;
  }

  public void unsetFirstNoticeDate() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __FIRSTNOTICEDATE_ISSET_ID);
  }

  /** Returns true if field firstNoticeDate is set (has been assigned a value) and false otherwise */
  public boolean isSetFirstNoticeDate() {
    return EncodingUtils.testBit(__isset_bitfield, __FIRSTNOTICEDATE_ISSET_ID);
  }

  public void setFirstNoticeDateIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __FIRSTNOTICEDATE_ISSET_ID, value);
  }

  public int getSledContractId() {
    return this.sledContractId;
  }

  public ContractBase setSledContractId(int sledContractId) {
    this.sledContractId = sledContractId;
    setSledContractIdIsSet(true);
    return this;
  }

  public void unsetSledContractId() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __SLEDCONTRACTID_ISSET_ID);
  }

  /** Returns true if field sledContractId is set (has been assigned a value) and false otherwise */
  public boolean isSetSledContractId() {
    return EncodingUtils.testBit(__isset_bitfield, __SLEDCONTRACTID_ISSET_ID);
  }

  public void setSledContractIdIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __SLEDCONTRACTID_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case CONTRACT:
      if (value == null) {
        unsetContract();
      } else {
        setContract((Contract)value);
      }
      break;

    case CONTRACT_ENG_NAME:
      if (value == null) {
        unsetContractEngName();
      } else {
        setContractEngName((String)value);
      }
      break;

    case CONTRACT_CN_NAME:
      if (value == null) {
        unsetContractCnName();
      } else {
        setContractCnName((String)value);
      }
      break;

    case CONTRACT_TC_NAME:
      if (value == null) {
        unsetContractTcName();
      } else {
        setContractTcName((String)value);
      }
      break;

    case EXPRIED_DATE:
      if (value == null) {
        unsetExpriedDate();
      } else {
        setExpriedDate((Integer)value);
      }
      break;

    case LAST_TRADE_DATE:
      if (value == null) {
        unsetLastTradeDate();
      } else {
        setLastTradeDate((Integer)value);
      }
      break;

    case FIRST_NOTICE_DATE:
      if (value == null) {
        unsetFirstNoticeDate();
      } else {
        setFirstNoticeDate((Integer)value);
      }
      break;

    case SLED_CONTRACT_ID:
      if (value == null) {
        unsetSledContractId();
      } else {
        setSledContractId((Integer)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case CONTRACT:
      return getContract();

    case CONTRACT_ENG_NAME:
      return getContractEngName();

    case CONTRACT_CN_NAME:
      return getContractCnName();

    case CONTRACT_TC_NAME:
      return getContractTcName();

    case EXPRIED_DATE:
      return Integer.valueOf(getExpriedDate());

    case LAST_TRADE_DATE:
      return Integer.valueOf(getLastTradeDate());

    case FIRST_NOTICE_DATE:
      return Integer.valueOf(getFirstNoticeDate());

    case SLED_CONTRACT_ID:
      return Integer.valueOf(getSledContractId());

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case CONTRACT:
      return isSetContract();
    case CONTRACT_ENG_NAME:
      return isSetContractEngName();
    case CONTRACT_CN_NAME:
      return isSetContractCnName();
    case CONTRACT_TC_NAME:
      return isSetContractTcName();
    case EXPRIED_DATE:
      return isSetExpriedDate();
    case LAST_TRADE_DATE:
      return isSetLastTradeDate();
    case FIRST_NOTICE_DATE:
      return isSetFirstNoticeDate();
    case SLED_CONTRACT_ID:
      return isSetSledContractId();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof ContractBase)
      return this.equals((ContractBase)that);
    return false;
  }

  public boolean equals(ContractBase that) {
    if (that == null)
      return false;

    boolean this_present_contract = true && this.isSetContract();
    boolean that_present_contract = true && that.isSetContract();
    if (this_present_contract || that_present_contract) {
      if (!(this_present_contract && that_present_contract))
        return false;
      if (!this.contract.equals(that.contract))
        return false;
    }

    boolean this_present_contractEngName = true && this.isSetContractEngName();
    boolean that_present_contractEngName = true && that.isSetContractEngName();
    if (this_present_contractEngName || that_present_contractEngName) {
      if (!(this_present_contractEngName && that_present_contractEngName))
        return false;
      if (!this.contractEngName.equals(that.contractEngName))
        return false;
    }

    boolean this_present_contractCnName = true && this.isSetContractCnName();
    boolean that_present_contractCnName = true && that.isSetContractCnName();
    if (this_present_contractCnName || that_present_contractCnName) {
      if (!(this_present_contractCnName && that_present_contractCnName))
        return false;
      if (!this.contractCnName.equals(that.contractCnName))
        return false;
    }

    boolean this_present_contractTcName = true && this.isSetContractTcName();
    boolean that_present_contractTcName = true && that.isSetContractTcName();
    if (this_present_contractTcName || that_present_contractTcName) {
      if (!(this_present_contractTcName && that_present_contractTcName))
        return false;
      if (!this.contractTcName.equals(that.contractTcName))
        return false;
    }

    boolean this_present_expriedDate = true && this.isSetExpriedDate();
    boolean that_present_expriedDate = true && that.isSetExpriedDate();
    if (this_present_expriedDate || that_present_expriedDate) {
      if (!(this_present_expriedDate && that_present_expriedDate))
        return false;
      if (this.expriedDate != that.expriedDate)
        return false;
    }

    boolean this_present_lastTradeDate = true && this.isSetLastTradeDate();
    boolean that_present_lastTradeDate = true && that.isSetLastTradeDate();
    if (this_present_lastTradeDate || that_present_lastTradeDate) {
      if (!(this_present_lastTradeDate && that_present_lastTradeDate))
        return false;
      if (this.lastTradeDate != that.lastTradeDate)
        return false;
    }

    boolean this_present_firstNoticeDate = true && this.isSetFirstNoticeDate();
    boolean that_present_firstNoticeDate = true && that.isSetFirstNoticeDate();
    if (this_present_firstNoticeDate || that_present_firstNoticeDate) {
      if (!(this_present_firstNoticeDate && that_present_firstNoticeDate))
        return false;
      if (this.firstNoticeDate != that.firstNoticeDate)
        return false;
    }

    boolean this_present_sledContractId = true && this.isSetSledContractId();
    boolean that_present_sledContractId = true && that.isSetSledContractId();
    if (this_present_sledContractId || that_present_sledContractId) {
      if (!(this_present_sledContractId && that_present_sledContractId))
        return false;
      if (this.sledContractId != that.sledContractId)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  @Override
  public int compareTo(ContractBase other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetContract()).compareTo(other.isSetContract());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetContract()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.contract, other.contract);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetContractEngName()).compareTo(other.isSetContractEngName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetContractEngName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.contractEngName, other.contractEngName);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetContractCnName()).compareTo(other.isSetContractCnName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetContractCnName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.contractCnName, other.contractCnName);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetContractTcName()).compareTo(other.isSetContractTcName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetContractTcName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.contractTcName, other.contractTcName);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetExpriedDate()).compareTo(other.isSetExpriedDate());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetExpriedDate()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.expriedDate, other.expriedDate);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetLastTradeDate()).compareTo(other.isSetLastTradeDate());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetLastTradeDate()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.lastTradeDate, other.lastTradeDate);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetFirstNoticeDate()).compareTo(other.isSetFirstNoticeDate());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetFirstNoticeDate()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.firstNoticeDate, other.firstNoticeDate);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetSledContractId()).compareTo(other.isSetSledContractId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSledContractId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.sledContractId, other.sledContractId);
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
    StringBuilder sb = new StringBuilder("ContractBase(");
    boolean first = true;

    if (isSetContract()) {
      sb.append("contract:");
      if (this.contract == null) {
        sb.append("null");
      } else {
        sb.append(this.contract);
      }
      first = false;
    }
    if (isSetContractEngName()) {
      if (!first) sb.append(", ");
      sb.append("contractEngName:");
      if (this.contractEngName == null) {
        sb.append("null");
      } else {
        sb.append(this.contractEngName);
      }
      first = false;
    }
    if (isSetContractCnName()) {
      if (!first) sb.append(", ");
      sb.append("contractCnName:");
      if (this.contractCnName == null) {
        sb.append("null");
      } else {
        sb.append(this.contractCnName);
      }
      first = false;
    }
    if (isSetContractTcName()) {
      if (!first) sb.append(", ");
      sb.append("contractTcName:");
      if (this.contractTcName == null) {
        sb.append("null");
      } else {
        sb.append(this.contractTcName);
      }
      first = false;
    }
    if (isSetExpriedDate()) {
      if (!first) sb.append(", ");
      sb.append("expriedDate:");
      sb.append(this.expriedDate);
      first = false;
    }
    if (isSetLastTradeDate()) {
      if (!first) sb.append(", ");
      sb.append("lastTradeDate:");
      sb.append(this.lastTradeDate);
      first = false;
    }
    if (isSetFirstNoticeDate()) {
      if (!first) sb.append(", ");
      sb.append("firstNoticeDate:");
      sb.append(this.firstNoticeDate);
      first = false;
    }
    if (isSetSledContractId()) {
      if (!first) sb.append(", ");
      sb.append("sledContractId:");
      sb.append(this.sledContractId);
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
    if (contract != null) {
      contract.validate();
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

  private static class ContractBaseStandardSchemeFactory implements SchemeFactory {
    public ContractBaseStandardScheme getScheme() {
      return new ContractBaseStandardScheme();
    }
  }

  private static class ContractBaseStandardScheme extends StandardScheme<ContractBase> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, ContractBase struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // CONTRACT
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.contract = new Contract();
              struct.contract.read(iprot);
              struct.setContractIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // CONTRACT_ENG_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.contractEngName = iprot.readString();
              struct.setContractEngNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // CONTRACT_CN_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.contractCnName = iprot.readString();
              struct.setContractCnNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // CONTRACT_TC_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.contractTcName = iprot.readString();
              struct.setContractTcNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // EXPRIED_DATE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.expriedDate = iprot.readI32();
              struct.setExpriedDateIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // LAST_TRADE_DATE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.lastTradeDate = iprot.readI32();
              struct.setLastTradeDateIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 7: // FIRST_NOTICE_DATE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.firstNoticeDate = iprot.readI32();
              struct.setFirstNoticeDateIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 8: // SLED_CONTRACT_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.sledContractId = iprot.readI32();
              struct.setSledContractIdIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, ContractBase struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.contract != null) {
        if (struct.isSetContract()) {
          oprot.writeFieldBegin(CONTRACT_FIELD_DESC);
          struct.contract.write(oprot);
          oprot.writeFieldEnd();
        }
      }
      if (struct.contractEngName != null) {
        if (struct.isSetContractEngName()) {
          oprot.writeFieldBegin(CONTRACT_ENG_NAME_FIELD_DESC);
          oprot.writeString(struct.contractEngName);
          oprot.writeFieldEnd();
        }
      }
      if (struct.contractCnName != null) {
        if (struct.isSetContractCnName()) {
          oprot.writeFieldBegin(CONTRACT_CN_NAME_FIELD_DESC);
          oprot.writeString(struct.contractCnName);
          oprot.writeFieldEnd();
        }
      }
      if (struct.contractTcName != null) {
        if (struct.isSetContractTcName()) {
          oprot.writeFieldBegin(CONTRACT_TC_NAME_FIELD_DESC);
          oprot.writeString(struct.contractTcName);
          oprot.writeFieldEnd();
        }
      }
      if (struct.isSetExpriedDate()) {
        oprot.writeFieldBegin(EXPRIED_DATE_FIELD_DESC);
        oprot.writeI32(struct.expriedDate);
        oprot.writeFieldEnd();
      }
      if (struct.isSetLastTradeDate()) {
        oprot.writeFieldBegin(LAST_TRADE_DATE_FIELD_DESC);
        oprot.writeI32(struct.lastTradeDate);
        oprot.writeFieldEnd();
      }
      if (struct.isSetFirstNoticeDate()) {
        oprot.writeFieldBegin(FIRST_NOTICE_DATE_FIELD_DESC);
        oprot.writeI32(struct.firstNoticeDate);
        oprot.writeFieldEnd();
      }
      if (struct.isSetSledContractId()) {
        oprot.writeFieldBegin(SLED_CONTRACT_ID_FIELD_DESC);
        oprot.writeI32(struct.sledContractId);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class ContractBaseTupleSchemeFactory implements SchemeFactory {
    public ContractBaseTupleScheme getScheme() {
      return new ContractBaseTupleScheme();
    }
  }

  private static class ContractBaseTupleScheme extends TupleScheme<ContractBase> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, ContractBase struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetContract()) {
        optionals.set(0);
      }
      if (struct.isSetContractEngName()) {
        optionals.set(1);
      }
      if (struct.isSetContractCnName()) {
        optionals.set(2);
      }
      if (struct.isSetContractTcName()) {
        optionals.set(3);
      }
      if (struct.isSetExpriedDate()) {
        optionals.set(4);
      }
      if (struct.isSetLastTradeDate()) {
        optionals.set(5);
      }
      if (struct.isSetFirstNoticeDate()) {
        optionals.set(6);
      }
      if (struct.isSetSledContractId()) {
        optionals.set(7);
      }
      oprot.writeBitSet(optionals, 8);
      if (struct.isSetContract()) {
        struct.contract.write(oprot);
      }
      if (struct.isSetContractEngName()) {
        oprot.writeString(struct.contractEngName);
      }
      if (struct.isSetContractCnName()) {
        oprot.writeString(struct.contractCnName);
      }
      if (struct.isSetContractTcName()) {
        oprot.writeString(struct.contractTcName);
      }
      if (struct.isSetExpriedDate()) {
        oprot.writeI32(struct.expriedDate);
      }
      if (struct.isSetLastTradeDate()) {
        oprot.writeI32(struct.lastTradeDate);
      }
      if (struct.isSetFirstNoticeDate()) {
        oprot.writeI32(struct.firstNoticeDate);
      }
      if (struct.isSetSledContractId()) {
        oprot.writeI32(struct.sledContractId);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, ContractBase struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(8);
      if (incoming.get(0)) {
        struct.contract = new Contract();
        struct.contract.read(iprot);
        struct.setContractIsSet(true);
      }
      if (incoming.get(1)) {
        struct.contractEngName = iprot.readString();
        struct.setContractEngNameIsSet(true);
      }
      if (incoming.get(2)) {
        struct.contractCnName = iprot.readString();
        struct.setContractCnNameIsSet(true);
      }
      if (incoming.get(3)) {
        struct.contractTcName = iprot.readString();
        struct.setContractTcNameIsSet(true);
      }
      if (incoming.get(4)) {
        struct.expriedDate = iprot.readI32();
        struct.setExpriedDateIsSet(true);
      }
      if (incoming.get(5)) {
        struct.lastTradeDate = iprot.readI32();
        struct.setLastTradeDateIsSet(true);
      }
      if (incoming.get(6)) {
        struct.firstNoticeDate = iprot.readI32();
        struct.setFirstNoticeDateIsSet(true);
      }
      if (incoming.get(7)) {
        struct.sledContractId = iprot.readI32();
        struct.setSledContractIdIsSet(true);
      }
    }
  }

}

