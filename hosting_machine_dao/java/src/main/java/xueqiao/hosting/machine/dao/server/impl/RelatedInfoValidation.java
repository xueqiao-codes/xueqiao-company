package xueqiao.hosting.machine.dao.server.impl;

import org.soldier.platform.svr_platform.comm.ErrorInfo;

import com.antiy.error_code.ErrorCodeInner;

public class RelatedInfoValidation {
	
	public static void checkCompanyId(int companyId) throws ErrorInfo {
		if (companyId <= 0) {
			throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode()
					, "companyId should not <= 0");
		}
	}
	
	public static void checkCompanyGroupId(int companyGroupId) throws ErrorInfo {
		if (companyGroupId <= 0) {
			throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode()
					, "companyGroupId should not <= 0");
		}
	}
	
	public static void checkRelatedId(long relatedId) throws ErrorInfo {
		if (relatedId <= 0) {
			throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode()
					, "relatedId should not <= 0");
		}
		
	}
	
	public static void checkActiveTimestamp(int startTimestamp, int endTimestamp) throws ErrorInfo {
		if (startTimestamp <= 0) {
			throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode()
					, "startTimestamp should not <= 0");
		}
		if (endTimestamp <= 0) {
			throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode()
					, "endTimestamp should not <= 0");
		}
		
		if (endTimestamp <= startTimestamp) {
			throw new ErrorInfo(
					ErrorCodeInner.PARAM_ERROR.getErrorCode()
					, "endTimestamp should not <= startTimestamp");
		}
	}
	
	
}
