package xueqiao.company.util.security;

import com.antiy.error_code.ErrorCodeInner;
import org.soldier.platform.svr_platform.comm.ErrorInfo;

public class ParamChecker {

    public static void check(boolean expression, String description) throws ErrorInfo {
        if (!expression) {
            throw new ErrorInfo(ErrorCodeInner.PARAM_ERROR.getErrorCode(), description);
        }
    }
}
