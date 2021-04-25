package com.longsheng.trader.webmethod;

import com.antiy.error_code.ErrorCodeInner;
import com.longsheng.trader.BaseResult;
import com.longsheng.trader.ValueResult;
import com.longsheng.trader.bean.order.OrderConfirmInfo;
import com.longsheng.trader.controller.MXueqiaoCompany;
import com.longsheng.trader.handler.PersonalUserOrderSubmitter;
import com.longsheng.trader.webmethod.base.WebPersonalUserMethod;
import com.longsheng.trader.webmethod.base.WebRequest;
import com.longsheng.trader.webmethod.base.WebUser;
import com.longsheng.xueqiao.payment.thriftapi.Order;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import xueqiao.company.CompanyEntry;
import xueqiao.company.CompanyUser;

public class CreatePersonalUserOrder extends WebPersonalUserMethod {
    @Override
    protected BaseResult doUserMethod(WebRequest request, WebUser user) throws Exception {
        int userId = user.getUserId();
        int productId = request.getInt("productId", 0);
        int isRechargeTime = request.getInt("isRechargeTime", 0);

        /*
         * 获取个人版用户集体挂靠公司
         * */
        CompanyEntry collectiveCompany = MXueqiaoCompany.getInstance().queryCollectiveCompany();
        if (collectiveCompany == null) {
            throw new ErrorInfo(ErrorCodeInner.SERVER_INNER_ERROR.getErrorCode(), "云服务个人版服务异常");
        }

        /*
        * 获取用户信息
        * */
        CompanyUser companyUser = MXueqiaoCompany.getInstance().queryCompanyUserById(userId);
        if (companyUser == null) {
            throw new ErrorInfo(ErrorCodeInner.SERVER_INNER_ERROR.getErrorCode(), "服务繁忙，确认用户信息失败");
        }

        OrderConfirmInfo orderConfirmInfo = new OrderConfirmInfo();
        orderConfirmInfo.setCompanyId(collectiveCompany.getCompanyId());
        orderConfirmInfo.setProductId(productId);

        Order retOrder = new PersonalUserOrderSubmitter(companyUser, isRechargeTime).submit(orderConfirmInfo);
        /*
         * 返回订单ID
         * */
        int ret = retOrder.getOrderId();
        return new ValueResult<Integer>(ret);
    }
}
