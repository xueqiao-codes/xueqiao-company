/**
 * Autogenerated by Thrift Compiler (0.9.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.longsheng.xueqiao.payment.product.thriftapi;


import java.util.Map;
import java.util.HashMap;
import org.apache.thrift.TEnum;

/**
 * 商品对应的操作
 */
public enum Operation implements org.apache.thrift.TEnum {
  NONE(0),
  ALLOCATE_SPEC(1),
  UPGRADE_SPEC(2);

  private final int value;

  private Operation(int value) {
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
  public static Operation findByValue(int value) { 
    switch (value) {
      case 0:
        return NONE;
      case 1:
        return ALLOCATE_SPEC;
      case 2:
        return UPGRADE_SPEC;
      default:
        return null;
    }
  }
}
