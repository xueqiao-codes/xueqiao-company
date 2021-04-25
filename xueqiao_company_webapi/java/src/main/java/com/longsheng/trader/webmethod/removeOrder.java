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

public class removeOrder extends WebUserMethod {

	@Override
	protected BaseResult doUserMethod(WebRequest request, WebUser user) throws Exception {

		int orderId = request.getInt("orderId", 0);
		if (orderId < 0) {
			throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "订单号无效");
		}
		// check order
		Order order = null;
		try {
			order = MXueqiaoUserCenterPayment.getInstance().queryOrderById(orderId);
		} catch (Throwable t) {
			throw new ErrorInfo(ErrorCodeOuter.SERVER_BUSY.getErrorCode(), "内部错误");
		}
		if (order == null) {
			throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "订单号无效");
		}
		/*
		* 取消 或 过期的订单才能删除
		* 一旦删除，不可恢复（这个接口是直接删除数据库中的数据）
		* */
		if (order.getStatus() == OrderStatus.CANCELLED
			|| order.getStatus() == OrderStatus.EXPIRED) {
			try {
//				MXueqiaoUserCenterPayment.getInstance().updateOrderStatus(orderId, OrderStatus.REMOVED);
				MXueqiaoUserCenterPayment.getInstance().removeInvalidOrder(orderId);
			} catch (Throwable t) {
				throw new ErrorInfo(ErrorCodeOuter.SERVER_BUSY.getErrorCode(), "内部错误");
			}
		} else {
			throw new ErrorInfo(ErrorCodeOuter.ILLEGAL_OPEARTION_ERROR.getErrorCode(), "订单生效中，不能删除");
		}

		return new ValueResult<String>("success");
	}
}
