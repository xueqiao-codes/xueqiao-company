/**
 * Autogenerated by Thrift Compiler (0.9.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.longsheng.xueqiao.payment.balance.alteration;


import java.util.Map;
import java.util.HashMap;
import org.apache.thrift.TEnum;

public enum OperationType implements org.apache.thrift.TEnum {
  ADD_XCOIN(0),
  REDUCE_XCOIN(1);

  private final int value;

  private OperationType(int value) {
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
  public static OperationType findByValue(int value) { 
    switch (value) {
      case 0:
        return ADD_XCOIN;
      case 1:
        return REDUCE_XCOIN;
      default:
        return null;
    }
  }
}
