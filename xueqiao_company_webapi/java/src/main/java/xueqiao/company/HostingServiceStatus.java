/**
 * Autogenerated by Thrift Compiler (0.9.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package xueqiao.company;


import java.util.Map;
import java.util.HashMap;
import org.apache.thrift.TEnum;

public enum HostingServiceStatus implements org.apache.thrift.TEnum {
  WAITING(0),
  OPENING(1),
  UPGRADING(2),
  WORKING(3),
  EXPIRED(4),
  RELEASED(5);

  private final int value;

  private HostingServiceStatus(int value) {
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
  public static HostingServiceStatus findByValue(int value) { 
    switch (value) {
      case 0:
        return WAITING;
      case 1:
        return OPENING;
      case 2:
        return UPGRADING;
      case 3:
        return WORKING;
      case 4:
        return EXPIRED;
      case 5:
        return RELEASED;
      default:
        return null;
    }
  }
}
