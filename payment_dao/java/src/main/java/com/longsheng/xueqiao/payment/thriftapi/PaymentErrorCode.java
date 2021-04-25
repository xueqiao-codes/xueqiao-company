/**
 * Autogenerated by Thrift Compiler (0.9.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.longsheng.xueqiao.payment.thriftapi;


import java.util.Map;
import java.util.HashMap;
import org.apache.thrift.TEnum;

public enum PaymentErrorCode implements org.apache.thrift.TEnum {
  ORDER_HAS_FINISHED(1001),
  ORDER_HAS_PAID(1002),
  FINISH_ORDER_FAIL(1003),
  PRODUCT_NOT_FOUND(1004),
  TOTAL_AMOUNT_NOT_MATCH(1005),
  PAY_TYPE_NOT_FOUND(1006),
  PRODUCT_PAY_TYPE_ERROR(1007),
  TRADE_INFO_NOT_MATCH(1008),
  XCOIN_BALANCE_NOT_ENOUGH(1009),
  ORDER_EXPIRED(1010),
  PRODUCT_INVALID(1011),
  ORDER_NOT_FOUND(1012),
  PRODUCT_INVENTORY_NOT_ENOUGH(1013),
  UNKNOWN_ORDER_STATUS(1014),
  PRODUCT_TYPE_NOT_MATCH(1015),
  COMPANY_GROUP_NAME_EXIST(1020),
  THRID_PARTY_TRADE_INFO_EXIST(1021),
  ORDER_NOT_PAID(1030),
  ORDER_MANUAL_PROCESSING(1031),
  ORDER_REMOVED(1302),
  ORDER_CANCELLED(1303),
  ORDER_HAS_SYSTEM_PROCESSED(1304);

  private final int value;

  private PaymentErrorCode(int value) {
    this.value = value;
  }

  /**
   * Get the integer value of this enum value, as defined in the Thrift IDL.
   */
  public int getValue() {
    return value;
  }

  /**
   * Find a the enum type by its integer value, as defined in the Thrift IDL.
   * @return null if the value is not found.
   */
  public static PaymentErrorCode findByValue(int value) { 
    switch (value) {
      case 1001:
        return ORDER_HAS_FINISHED;
      case 1002:
        return ORDER_HAS_PAID;
      case 1003:
        return FINISH_ORDER_FAIL;
      case 1004:
        return PRODUCT_NOT_FOUND;
      case 1005:
        return TOTAL_AMOUNT_NOT_MATCH;
      case 1006:
        return PAY_TYPE_NOT_FOUND;
      case 1007:
        return PRODUCT_PAY_TYPE_ERROR;
      case 1008:
        return TRADE_INFO_NOT_MATCH;
      case 1009:
        return XCOIN_BALANCE_NOT_ENOUGH;
      case 1010:
        return ORDER_EXPIRED;
      case 1011:
        return PRODUCT_INVALID;
      case 1012:
        return ORDER_NOT_FOUND;
      case 1013:
        return PRODUCT_INVENTORY_NOT_ENOUGH;
      case 1014:
        return UNKNOWN_ORDER_STATUS;
      case 1015:
        return PRODUCT_TYPE_NOT_MATCH;
      case 1020:
        return COMPANY_GROUP_NAME_EXIST;
      case 1021:
        return THRID_PARTY_TRADE_INFO_EXIST;
      case 1030:
        return ORDER_NOT_PAID;
      case 1031:
        return ORDER_MANUAL_PROCESSING;
      case 1302:
        return ORDER_REMOVED;
      case 1303:
        return ORDER_CANCELLED;
      case 1304:
        return ORDER_HAS_SYSTEM_PROCESSED;
      default:
        return null;
    }
  }
}
