package com.longsheng.xueqiao.payment.purchase;

import com.antiy.error_code.ErrorCodeInner;
import com.longsheng.xueqiao.payment.dao.table.company.CompanyGroupSpecTable;
import com.longsheng.xueqiao.payment.dao.table.payment.OrderHandler;
import com.longsheng.xueqiao.payment.dao.table.payment.ProductHandler;
import com.longsheng.xueqiao.payment.product.thriftapi.*;
import com.longsheng.xueqiao.payment.thriftapi.*;
import org.apache.commons.lang.StringUtils;
import org.soldier.platform.dal_set.DalSetDataSource;
import org.soldier.platform.db_helper.DBStepHelper;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import org.soldier.platform.svr_platform.container.TServiceCntl;
import xueqiao.company.CompanyGroupSpec;

import java.sql.Connection;
import java.sql.SQLException;

public class OrderChecker {

    public CheckPrePayResp validOrderPrePay(TServiceCntl oCntl, String roleName, PayOrderInfo payOrderInfo) {

        CheckPrePayResp resp = new CheckPrePayResp();
        try {
//            Order order = new DBQueryHelper<Order>(new DalSetDataSource(roleName, oCntl.getDalSetServiceName(), true, 0)) {
            Order order = new DBStepHelper<Order>(new DalSetDataSource(roleName, oCntl.getDalSetServiceName(), true, 0)) {

                Order order;

                @Override
                public void onPrepareData() throws ErrorInfo, Exception {

                }

                @Override
                public void onUpdate() throws ErrorInfo, Exception {
                    validOrder(getConnection());
                    validProduct(getConnection());
                }

                @Override
                public Order getResult() {
                    return order;
                }

                private void validProduct(Connection connection) throws SQLException, ErrorInfo {
                    Product product = new ProductHandler(connection, roleName).selectForUpdate(order.getProductId());
                    if (product == null) {
                        throw new ErrorInfo(PaymentErrorCode.PRODUCT_NOT_FOUND.getValue(), "Product not found.");
                    }
                    if (ProductStatus.INVALID.equals(product.getStatus())) {
                        throw new ErrorInfo(PaymentErrorCode.PRODUCT_INVALID.getValue(), "Product invalid.");
                    }

                    /*
                    * 商品为个人版云服务时，不检查托管机组相关信息，
                    * 因为个人版云服务在购买后再与具体的托管机组绑定
                    * */
                    if (!ProductType.XCOIN.equals(product.getType())
                            && (product.getPurpose() != ProductPurpose.PERSONAL_NEW)
                            && (product.getPurpose() != ProductPurpose.PERSONAL_RECHARGE_TIME)) {
                        ReqCompanyGroupSpecOption option = new ReqCompanyGroupSpecOption();
                        option.addToCompanyIds(order.getCompanyId());
                        option.setGroupId(order.getCompanyGroupId());

                        CompanyGroupSpec companyGroupSpec = new CompanyGroupSpecTable(connection, roleName).query(order.getCompanyId(), order.getCompanyGroupId(), false);
                        if (companyGroupSpec == null || StringUtils.isBlank(companyGroupSpec.getSpecName())) {
                            throw new ErrorInfo(ErrorCodeInner.SERVER_INNER_ERROR.getErrorCode(), "companyGroupSpec is empty or spec name blank");
                        }
                        if (!companyGroupSpec.getSpecName().equals(product.getType().name())) {
                            throw new ErrorInfo(PaymentErrorCode.PRODUCT_TYPE_NOT_MATCH.getValue(), "Product type not match.");
                        }
                    }
                }

                private void validOrder(Connection connection) throws ErrorInfo, SQLException {
                    OrderHandler orderHandler = new OrderHandler(connection, roleName);
                    order = orderHandler.selectForUpdate(payOrderInfo.getOrderId());
                    if (order == null) {
                        throw new ErrorInfo(PaymentErrorCode.ORDER_NOT_FOUND.getValue(), "order not found");
                    }

                    switch (order.getStatus()) {
                        case CREATED:
                            break;
                        case PAY_SUCCESS:
                            throw new ErrorInfo(PaymentErrorCode.ORDER_HAS_PAID.getValue(), "Order has paid.");
                        case SYSTEM_PROCESSED:
                            throw new ErrorInfo(PaymentErrorCode.ORDER_HAS_SYSTEM_PROCESSED.getValue(), "Order has been processed by System.");
                        case MANUAL_PROCESSING:
                            throw new ErrorInfo(PaymentErrorCode.ORDER_MANUAL_PROCESSING.getValue(), "Order is manual processing.");
                        case SUCCESS:
                            throw new ErrorInfo(PaymentErrorCode.ORDER_HAS_FINISHED.getValue(), "Order has finished.");
                        case EXPIRED:
                            throw new ErrorInfo(PaymentErrorCode.ORDER_EXPIRED.getValue(), "Order expired.");
                        case REMOVED:
                            throw new ErrorInfo(PaymentErrorCode.ORDER_REMOVED.getValue(), "Order has removed.");
                        case CANCELLED:
                            throw new ErrorInfo(PaymentErrorCode.ORDER_CANCELLED.getValue(), "Order has cancelled.");
                        default:
                            throw new ErrorInfo(PaymentErrorCode.UNKNOWN_ORDER_STATUS.getValue(), "Unkonwn order status.");
                    }
                }
            }.execute().getResult();
            resp.setOrder(order);
            resp.setValid(true);
        } catch (ErrorInfo errorInfo) {
            resp.setErr(errorInfo);
            resp.setValid(false);
        }
        return resp;
    }
}
