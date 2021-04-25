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

public enum CompanyErrorCode implements org.apache.thrift.TEnum {
  COMPANY_NAME_EXIST(1000),
  GROUP_NAME_EXIST(1001),
  USER_NAME_EXIST(1002),
  COMPANY_NOT_FOUND(1003),
  GROUP_NOT_FOUND(1004),
  USER_NOT_FOUND(1005),
  GROUP_SPEC_NOT_FOUND(1007),
  GROUP_SERVICE_INVALID(1008),
  PASSWORD_ERROR(1009);

  private final int value;

  private CompanyErrorCode(int value) {
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
  public static CompanyErrorCode findByValue(int value) { 
    switch (value) {
      case 1000:
        return COMPANY_NAME_EXIST;
      case 1001:
        return GROUP_NAME_EXIST;
      case 1002:
        return USER_NAME_EXIST;
      case 1003:
        return COMPANY_NOT_FOUND;
      case 1004:
        return GROUP_NOT_FOUND;
      case 1005:
        return USER_NOT_FOUND;
      case 1007:
        return GROUP_SPEC_NOT_FOUND;
      case 1008:
        return GROUP_SERVICE_INVALID;
      case 1009:
        return PASSWORD_ERROR;
      default:
        return null;
    }
  }
}