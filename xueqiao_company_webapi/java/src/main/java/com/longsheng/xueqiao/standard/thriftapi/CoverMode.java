/**
 * Autogenerated by Thrift Compiler (0.9.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.longsheng.xueqiao.standard.thriftapi;


import java.util.Map;
import java.util.HashMap;
import org.apache.thrift.TEnum;

public enum CoverMode implements org.apache.thrift.TEnum {
  NONE(0),
  UNFINISHED(1),
  OPENCOVER(2),
  COVERTODAY(3);

  private final int value;

  private CoverMode(int value) {
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
  public static CoverMode findByValue(int value) { 
    switch (value) {
      case 0:
        return NONE;
      case 1:
        return UNFINISHED;
      case 2:
        return OPENCOVER;
      case 3:
        return COVERTODAY;
      default:
        return null;
    }
  }
}
