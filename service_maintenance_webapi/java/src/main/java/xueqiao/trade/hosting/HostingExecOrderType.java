/**
 * Autogenerated by Thrift Compiler (0.9.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package xueqiao.trade.hosting;


import java.util.Map;
import java.util.HashMap;
import org.apache.thrift.TEnum;

/**
 * 执行子订单模块
 */
public enum HostingExecOrderType implements org.apache.thrift.TEnum {
  ORDER_LIMIT_PRICE(1),
  ORDER_WITH_CONDITION(2);

  private final int value;

  private HostingExecOrderType(int value) {
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
  public static HostingExecOrderType findByValue(int value) { 
    switch (value) {
      case 1:
        return ORDER_LIMIT_PRICE;
      case 2:
        return ORDER_WITH_CONDITION;
      default:
        return null;
    }
  }
}
