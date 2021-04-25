package com.longsheng.trader.webmethod;

import com.longsheng.trader.BaseResult;
import com.longsheng.trader.ValueResult;
import com.longsheng.trader.data.VerifyCodeRequestSupervisor;
import com.longsheng.trader.webmethod.base.WebMethod;
import com.longsheng.trader.webmethod.base.WebRequest;
import com.longsheng.xueqiao.goose.thriftapi.client.GooseAoStub;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.svr_platform.comm.ErrorInfo;

/**
 * Created by walter on 07/08/2017.
 */
public class GetTelVerifyCode extends WebMethod {

    @Override
    protected BaseResult doMethod(WebRequest request) throws Exception {
        String tel = request.getParameter("tel", "");

        if (StringUtils.isBlank(tel)) {
            throw new ErrorInfo(82000,"手机号码为空");
        }
//        boolean isValid = IpSendCodeMap.getInstance().validIP(request.getIpAddress());
        boolean isValid = VerifyCodeRequestSupervisor.getInstance().isValid(request.getIpAddress(), tel);

        AppLog.i("GetTelVerifyCode ---- tel : " + tel + ", isValid : " + isValid);

        if (isValid) {
            GooseAoStub stub = new GooseAoStub();

            int routeKey = RandomUtils.nextInt();
            int timeout = 3000;

            try {
                stub.sendVerifyCode(routeKey, timeout, tel);
            } catch (ErrorInfo errorInfo) {
                AppLog.e("GetTelVerifyCode ---- sendVerifyCode ---- errorInfo", errorInfo);
                if (errorInfo.getErrorCode() == 80202) {
                    throw new ErrorInfo(80202,"发送太频繁，请稍后再发送");
                } else if (errorInfo.getErrorCode() == 80204) {
                    throw new ErrorInfo(80204,"发送服务异常，请稍后再试");
                } else if (errorInfo.getErrorCode() == 299) {
                    // parameter empty
                    throw new ErrorInfo(80205,"发送服务异常，请稍后再试");
                } else {
                    throw new ErrorInfo(500,"发送服务异常，请稍后再试");
                }
            } catch (Throwable throwable) {
                 AppLog.e("GetTelVerifyCode ---- sendVerifyCode ---- throwable", throwable);
                throw new ErrorInfo(500,"发送服务异常");
            }
        }else {
            AppLog.d("ip : "+ request.getIpAddress() + "tel : " + tel + " 今日发送次数太多");
            throw new ErrorInfo(82001,"今日发送次数太多");
        }
        return new ValueResult<String>("send success");
    }
}
