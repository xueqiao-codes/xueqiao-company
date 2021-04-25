package com.longsheng.trader.webmethod;

import com.antiy.error_code.ErrorCodeOuter;
import com.longsheng.trader.BaseResult;
import com.longsheng.trader.ValueResult;
import com.longsheng.trader.controller.MXueqiaoCompany;
import com.longsheng.trader.controller.MXueqiaoUserCenterPayment;
import com.longsheng.trader.webmethod.base.WebRequest;
import com.longsheng.trader.webmethod.base.WebUser;
import com.longsheng.trader.webmethod.base.WebUserMethod;
import com.longsheng.xueqiao.payment.thriftapi.Order;
import com.longsheng.xueqiao.payment.thriftapi.OrderPage;
import com.longsheng.xueqiao.payment.thriftapi.OrderStatus;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import xueqiao.company.CompanyGroupSpec;
import xueqiao.company.HostingServiceStatus;

public class RemoveHostedService extends WebUserMethod {

	@Override
	protected BaseResult doUserMethod(WebRequest request, WebUser user) throws Exception {

		int groupId = request.getInt("groupId", 0);
		if (groupId < 0) {
			throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "内部错误,组ID无效");
		}
		int companyId = user.getUserId();

		// check hosted service status
		CompanyGroupSpec companyGroupSpec = null;
		try {
			companyGroupSpec = MXueqiaoCompany.getInstance().queryCompanyGroupSpecById(companyId, groupId);
		} catch (Throwable t) {
			throw new ErrorInfo(ErrorCodeOuter.SERVER_BUSY.getErrorCode(), "内部错误,查询托管服务失败");
		}
		if (companyGroupSpec == null) {
			throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "内部错误,查询不到托管服务");
		}

		/*
		* 对于待支付状态的托管机，查询对应的订单的状态
		* 不存在有效的订单才能删除
		*
		* 此规则对于已释放的托管机不适用，因为状态为SUCCESS的订单，对应的托管机可以是释放状态
		* */
		boolean hostingServiceWaitingAndRemovable = false;
		if (companyGroupSpec.getHostingServiceStatus() == HostingServiceStatus.WAITING) {
			hostingServiceWaitingAndRemovable = true;
			OrderPage orderPage = null;
			try {
				orderPage = MXueqiaoUserCenterPayment.getInstance().queryOrderByGroupId(companyId, groupId);
			} catch (Throwable throwable) {
				AppLog.e("RemoveHostedService ---- queryOrderByGroupId ---- throwable", throwable);
			}
			if (orderPage != null && orderPage.getPageSize() > 0) {
				for (Order order : orderPage.getPage()) {
					if (MXueqiaoUserCenterPayment.isOrderValid(order)) {
						hostingServiceWaitingAndRemovable = false;
						break;
					}
				}
			}
		}

		/*
		 * 对应订单失效 或 已释放的托管机才能删除
		 * 删除的是companyGroup
		 * */
		if (hostingServiceWaitingAndRemovable
			|| companyGroupSpec.getHostingServiceStatus() == HostingServiceStatus.RELEASED) {
			try {
				MXueqiaoCompany.getInstance().deleteCompanyGroup(companyId, groupId);
			} catch (Throwable t) {
				throw new ErrorInfo(ErrorCodeOuter.SERVER_BUSY.getErrorCode(), "内部错误");
			}
		} else {
			throw new ErrorInfo(ErrorCodeOuter.ILLEGAL_OPEARTION_ERROR.getErrorCode(), "托管服务生效中或有关联有效订单，不能删除");
		}

		return new ValueResult<String>("success");
	}

//	/**
//	 * 判断订单是否有效
//	 * @see OrderStatus
//	 * */
//	private boolean isOrderValid(Order order) {
//		if (order.getStatus() == OrderStatus.EXPIRED
//			|| order.getStatus() == OrderStatus.CANCELLED
//			|| order.getStatus() == OrderStatus.REMOVED) {
//			return false;
//		} else {
//			return true;
//		}
//	}
}
