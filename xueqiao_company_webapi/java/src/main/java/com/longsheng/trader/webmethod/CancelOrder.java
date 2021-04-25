package com.longsheng.trader.webmethod;

import com.antiy.error_code.ErrorCodeOuter;
import com.longsheng.trader.BaseResult;
import com.longsheng.trader.ValueResult;
import com.longsheng.trader.controller.MXueqiaoUserCenterPayment;
import com.longsheng.trader.webmethod.base.WebRequest;
import com.longsheng.trader.webmethod.base.WebUser;
import com.longsheng.trader.webmethod.base.WebUserMethod;
import com.longsheng.xueqiao.payment.thriftapi.Order;
import com.longsheng.xueqiao.payment.thriftapi.OrderStatus;
import org.soldier.platform.svr_platform.comm.ErrorInfo;

public class CancelOrder extends WebUserMethod {

	@Override
	protected BaseResult doUserMethod(WebRequest request, WebUser user) throws Exception {

		int orderId = request.getInt("orderId", 0);
		if (orderId < 0) {
			throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "订单号无效");
		}

		Order order = null;
		try {
			order = MXueqiaoUserCenterPayment.getInstance().queryOrderById(orderId);
		} catch (Throwable t) {
			throw new ErrorInfo(ErrorCodeOuter.SERVER_BUSY.getErrorCode(), "内部错误");
		}
		if (order == null) {
			throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "订单号无效");
		}

		if (order.getStatus() == OrderStatus.CREATED) {
			try {
				MXueqiaoUserCenterPayment.getInstance().updateOrderStatus(orderId, OrderStatus.CANCELLED);
			} catch (Throwable t) {
				throw new ErrorInfo(ErrorCodeOuter.SERVER_BUSY.getErrorCode(), "内部错误");
			}
		} else {
			throw new ErrorInfo(ErrorCodeOuter.ILLEGAL_OPEARTION_ERROR.getErrorCode(), "非未支付订单，不能做取消操作");
		}

		return new ValueResult<String>("success");
	}
}
