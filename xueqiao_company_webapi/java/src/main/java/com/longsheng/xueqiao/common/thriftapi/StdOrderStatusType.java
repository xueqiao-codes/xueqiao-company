/**
 * Autogenerated by Thrift Compiler (0.9.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.longsheng.xueqiao.common.thriftapi;


import java.util.Map;
import java.util.HashMap;
import org.apache.thrift.TEnum;

public enum StdOrderStatusType implements org.apache.thrift.TEnum {
  REQUEST_CANCELING(-2),
  REQUEST_INSERTING(-1),
  SENDING(0),
  ADDING(1),
  WORKING(2),
  PART_FILLED(3),
  FILLED(4),
  CANCELED(5),
  CANCELING(6),
  REJECTED(7),
  EXPIRED(8),
  SLED_ERROR(9);

  private final int value;

  private StdOrderStatusType(int value) {
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
  public static StdOrderStatusType findByValue(int value) { 
    switch (value) {
      case -2:
        return REQUEST_CANCELING;
      case -1:
        return REQUEST_INSERTING;
      case 0:
        return SENDING;
      case 1:
        return ADDING;
      case 2:
        return WORKING;
      case 3:
        return PART_FILLED;
      case 4:
        return FILLED;
      case 5:
        return CANCELED;
      case 6:
        return CANCELING;
      case 7:
        return REJECTED;
      case 8:
        return EXPIRED;
      case 9:
        return SLED_ERROR;
      default:
        return null;
    }
  }
}
