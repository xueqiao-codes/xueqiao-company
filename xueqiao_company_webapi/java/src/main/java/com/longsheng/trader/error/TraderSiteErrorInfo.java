package com.longsheng.trader.error;

import org.soldier.platform.svr_platform.comm.ErrorInfo;

public class TraderSiteErrorInfo {
	public static ErrorInfo ERROR_SERVER_BUSY = new ErrorInfo(3000, "服务器繁忙");
	public static ErrorInfo ERROR_COMPANY_GROUP_NAME_EXIST = new ErrorInfo(3001, "托管机名称已存在");
	public static ErrorInfo ERROR_CREATE_COMPANY_GROUP_FAIL = new ErrorInfo(3002, "创建托管机失败");
	public static ErrorInfo ERROR_EMAIL_EMPTY = new ErrorInfo(3003, "邮箱为空");
	public static ErrorInfo ERROR_PARAMETERS_INVALID = new ErrorInfo(4000, "参数不正确");
	public static ErrorInfo ERROR_INNER_ERROR = new ErrorInfo(4001, "内部错误");
//	public static ErrorInfo ERROR_COMPANY_GROUP_WITH_VALID_ORDER_AND_REMOVE_FAIL = new ErrorInfo(3003, "托管机关联有效订单，删除失败");
}
