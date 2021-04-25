package com.longsheng.trader.common.base;

import com.antiy.error_code.ErrorCodeInner;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.svr_platform.comm.ErrorInfo;

/**
 * 前置参数检查
 */
public class Precondition {

    public static void check(boolean condition, String errorMsg) throws ErrorInfo {
        if (!condition) {
            AppLog.e("errorMsg");
            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), errorMsg);
        }
    }
}
