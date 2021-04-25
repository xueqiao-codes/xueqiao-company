package com.longsheng.trader.webmethod;

import com.antiy.error_code.ErrorCodeOuter;
import com.longsheng.trader.BaseResult;
import com.longsheng.trader.ListResult;
import com.longsheng.trader.controller.MXueqiaoUserCenterPayment;
import com.longsheng.trader.webmethod.base.WebRequest;
import com.longsheng.trader.webmethod.base.WebUser;
import com.longsheng.trader.webmethod.base.WebUserMethod;
import com.longsheng.xueqiao.payment.thriftapi.Order;
import com.longsheng.xueqiao.payment.thriftapi.OrderPage;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.svr_platform.comm.ErrorInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询特定group关联的有效订单列表
 * */
public class GetValidOrdersByGroupId extends WebUserMethod {

	@Override
	protected BaseResult doUserMethod(WebRequest request, WebUser user) throws Exception {
		int companyId = user.getUserId();
		int groupId = request.getInt("groupId", 0);
		if (groupId < 0) {
			throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "内部错误,组ID无效");
		}

		OrderPage orderPage = null;
		try {
			orderPage = MXueqiaoUserCenterPayment.getInstance().queryOrderByGroupId(companyId, groupId);
		} catch (Throwable throwable) {
			AppLog.e("GetValidOrdersByGroupId ---- queryOrderByGroupId ---- throwable", throwable);
		}

		AppLog.i("GetValidOrdersByGroupId ---- queryOrderByGroupId ---- orderPage : " + orderPage.toString());

		List<Order> validOrderList = new ArrayList<>();
		if (orderPage != null && orderPage.getPageSize() > 0) {
			for (Order order : orderPage.getPage()) {
				if (MXueqiaoUserCenterPayment.isOrderValid(order)) {
					validOrderList.add(order);
				}
			}
		}

		return new ListResult<Order>(validOrderList);
	}
}
