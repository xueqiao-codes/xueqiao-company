/**
 * Autogenerated by Thrift Compiler (0.9.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package xueqiao.hosting.taskqueue;


import java.util.Map;
import java.util.HashMap;
import org.apache.thrift.TEnum;

public enum TaskType implements org.apache.thrift.TEnum {
  INIT_HOSTING(1),
  OPERATE_COMPANY_USER(2);

  private final int value;

  private TaskType(int value) {
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
  public static TaskType findByValue(int value) { 
    switch (value) {
      case 1:
        return INIT_HOSTING;
      case 2:
        return OPERATE_COMPANY_USER;
      default:
        return null;
    }
  }
}