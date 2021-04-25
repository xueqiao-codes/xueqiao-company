/**
 * Autogenerated by Thrift Compiler (0.9.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.longsheng.xueqiao.aliyun.resource.thriftapi;


import java.util.Map;
import java.util.HashMap;
import org.apache.thrift.TEnum;

public enum ResourceType implements org.apache.thrift.TEnum {
  INSTANCE(0),
  DISK(1),
  IMAGE(2),
  SECURITYGROUP(3),
  SNAPSHOT(4);

  private final int value;

  private ResourceType(int value) {
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
  public static ResourceType findByValue(int value) { 
    switch (value) {
      case 0:
        return INSTANCE;
      case 1:
        return DISK;
      case 2:
        return IMAGE;
      case 3:
        return SECURITYGROUP;
      case 4:
        return SNAPSHOT;
      default:
        return null;
    }
  }
}
