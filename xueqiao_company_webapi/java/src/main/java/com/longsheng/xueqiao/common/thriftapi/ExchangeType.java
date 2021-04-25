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

public enum ExchangeType implements org.apache.thrift.TEnum {
  ALL(0),
  SHFE(1),
  DCE(2),
  CZCE(3),
  CFFEX(4),
  COMEX(5),
  NYMEX(6),
  CME(7),
  SGX(8),
  HKEX(9),
  INE(10);

  private final int value;

  private ExchangeType(int value) {
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
  public static ExchangeType findByValue(int value) { 
    switch (value) {
      case 0:
        return ALL;
      case 1:
        return SHFE;
      case 2:
        return DCE;
      case 3:
        return CZCE;
      case 4:
        return CFFEX;
      case 5:
        return COMEX;
      case 6:
        return NYMEX;
      case 7:
        return CME;
      case 8:
        return SGX;
      case 9:
        return HKEX;
      case 10:
        return INE;
      default:
        return null;
    }
  }
}
