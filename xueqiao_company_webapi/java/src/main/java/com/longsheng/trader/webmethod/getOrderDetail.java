package com.longsheng.trader.webmethod;

import com.antiy.error_code.ErrorCodeOuter;
import com.longsheng.trader.BaseResult;
import com.longsheng.trader.ValueResult;
import com.longsheng.trader.bean.order.LocalOrderDetail;
import com.longsheng.trader.controller.MXueqiaoUserCenterPayment;
import com.longsheng.trader.utils.StringUtil;
import com.longsheng.trader.utils.TimeFormator;
import com.longsheng.trader.webmethod.base.WebRequest;
import com.longsheng.trader.webmethod.base.WebUser;
import com.longsheng.trader.webmethod.base.WebUserMethod;
import com.longsheng.xueqiao.payment.thriftapi.Order;
import com.longsheng.xueqiao.payment.thriftapi.OrderStatus;
import com.longsheng.xueqiao.payment.thriftapi.PayType;
import org.soldier.platform.svr_platform.comm.ErrorInfo;

import java.util.Map;

public class getOrderDetail extends WebUserMethod {

	@Override
	protected BaseResult doUserMethod(WebRequest request, WebUser user) throws Exception {

		int orderId = request.getInt("orderId", 0);
		if (orderId < 0) {
			throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "订单号无效");
		}
//		int companyId = user.getUserId();

		// query order
		Order order = null;
		try {
			order = MXueqiaoUserCenterPayment.getInstance().queryOrderById(orderId);
		} catch (Throwable t) {
			throw new ErrorInfo(ErrorCodeOuter.SERVER_BUSY.getErrorCode(), "内部错误");
		}
		if (order == null) {
			throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "订单号无效");
		}

		Map<String, String> extraInfoMap = StringUtil.string2Map(order.getExtraInfo());
		Map<String, String> tradeInfoMap = StringUtil.string2Map(order.getTradeInfo());

		LocalOrderDetail orderDetail = new LocalOrderDetail();
		orderDetail.setOrderId(order.getOrderId());
		orderDetail.setCompanyId(order.getCompanyId());
		orderDetail.setCompanyGroupId(order.getCompanyGroupId());
		orderDetail.setCompanyGroupName(order.getCompanyGroupName());
		orderDetail.setProductName(order.getProductName());
		orderDetail.setProductDescription(order.getProductDescription());
		orderDetail.setProductQuantity(order.getProductQuantity());
		orderDetail.setPrice(String.valueOf(order.getPrice()));
		orderDetail.setCurrencyType(order.getCurrencyType().name());
		orderDetail.setTotalAmount(String.valueOf(order.getTotalAmount()));
		orderDetail.setPayType(String.valueOf(order.getPayType()));
		orderDetail.setThirdPartyOrderNo(order.getThirdPartyOrderNo());
		orderDetail.setStatus(order.getStatus().name());
		orderDetail.setCreateTimestamp(TimeFormator.timeStampToDate(order.getCreateTimestamp()));

		int orderStatus = order.getStatus().getValue();
		if (order.getPayType() == PayType.INNER && orderStatus >= OrderStatus.PAY_SUCCESS.getValue() && orderStatus <= OrderStatus.SUCCESS.getValue()) {
			orderDetail.setPayTimestamp(orderDetail.getCreateTimestamp());
		} else {
			orderDetail.setPayTimestamp(tradeInfoMap.get("gmt_payment"));
		}

		orderDetail.setProductType(extraInfoMap.get("ProductType"));
		orderDetail.setProductPropose(extraInfoMap.get("ProductPurpose"));
		orderDetail.setServiceDays(extraInfoMap.get("ServiceDays"));

		return new ValueResult<LocalOrderDetail>(orderDetail);
	}
}
