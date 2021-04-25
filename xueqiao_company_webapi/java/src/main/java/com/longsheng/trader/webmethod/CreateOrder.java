package com.longsheng.trader.webmethod;

import com.longsheng.trader.BaseResult;
import com.longsheng.trader.ValueResult;
import com.longsheng.trader.bean.order.OrderConfirmInfo;
import com.longsheng.trader.handler.CreateHostedServiceOrderSubmitter;
import com.longsheng.trader.handler.RechargeTimeOrderSubmitter;
import com.longsheng.trader.handler.UpgradeSpecOrderSubmitter;
import com.longsheng.trader.webmethod.base.WebRequest;
import com.longsheng.trader.webmethod.base.WebUser;
import com.longsheng.trader.webmethod.base.WebUserMethod;
import com.longsheng.xueqiao.payment.product.thriftapi.ProductPurpose;
import com.longsheng.xueqiao.payment.thriftapi.Order;
import org.soldier.base.logger.AppLog;

/**
 * 创建订单
 * 1 创建companyGroup记录 （仅限于新建托管机的订单）
 * 2 创建order记录
 * 本接口里不涉及到支付
 */
public class CreateOrder extends WebUserMethod {
	@Override
	protected BaseResult doUserMethod(WebRequest request, WebUser user) throws Exception {

		int companyId = user.getUserId();

		String payType = request.getParameter("payType", "");
		String productPurpose = request.getParameter("productPurpose", "");
		String groupName = request.getParameter("groupName", "");
		String tradeType = request.getParameter("tradeType", "");
		double totalAmount = request.getDouble("totalAmount", 0.0);
		int groupId = request.getInt("groupId", 0);
		int productId = request.getInt("productId", 0);
		int quantity = request.getInt("quantity", 0);
		int serviceDays = request.getInt("serviceDays", 0);

		OrderConfirmInfo orderConfirmInfo = new OrderConfirmInfo();
		orderConfirmInfo.setCompanyId(companyId);
		orderConfirmInfo.setGroupId(groupId);
		orderConfirmInfo.setProductId(productId);
		orderConfirmInfo.setPayType(payType);
		orderConfirmInfo.setProductPurpose(productPurpose);
		orderConfirmInfo.setGroupName(groupName);
		orderConfirmInfo.setTradeType(tradeType);
		orderConfirmInfo.setTotalAmount(totalAmount);
		orderConfirmInfo.setQuantity(quantity);
		orderConfirmInfo.setServiceDays(serviceDays);

		AppLog.i("SubmitOrder ---- orderConfirmInfo : " + orderConfirmInfo.toString());

		// submit order
		Order retOrder = null;
		if (ProductPurpose.NEW.name().equals(productPurpose)) {
			/*
			* 创建 company group 记录
			* 创建新建托管机 order 记录
			* */
			retOrder = new CreateHostedServiceOrderSubmitter().submit(orderConfirmInfo);
		} else if (ProductPurpose.UPGRADE.name().equals(productPurpose)) {
			/*
			* 创建升级托管机 order 记录
			* */
			retOrder = new UpgradeSpecOrderSubmitter().submit(orderConfirmInfo);
		} else if (ProductPurpose.RECHARGE_TIME.name().equals(productPurpose)) {
			/*
			* 创建续费 order 记录
			* */
			retOrder = new RechargeTimeOrderSubmitter().submit(orderConfirmInfo);
		}
		/*
		* 返回订单ID
		* */
		int ret = retOrder.getOrderId();
		return new ValueResult<Integer>(ret);
	}
}
