package com.longsheng.xueqiao.payment.dao.table.payment;

import com.antiy.error_code.ErrorCodeOuter;
import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.longsheng.xueqiao.payment.product.thriftapi.*;
import org.soldier.base.sql.PreparedFields;
import org.soldier.base.sql.SqlQueryBuilder;
import org.soldier.platform.dal_set.DalSetDataSource;
import org.soldier.platform.dal_set.DalSetTableHelper;
import org.soldier.platform.db_helper.DBQueryHelper;
import org.soldier.platform.db_helper.DBTransactionHelper;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import org.soldier.platform.svr_platform.container.TServiceCntl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class ProductHandler extends DalSetTableHelper<Product> {

    private static final String TABLE_NAME = "t_sled_product";
    private static final String COLUMN_FSLED_PRODUCT_ID = "Fsled_product_id";
    private static final String COLUMN_FPRODUCT_TYPE = "Fproduct_type";
    private static final String COLUMN_FPRODUCT_NAME = "Fproduct_name";
    private static final String COLUMN_FDESCRIPTION = "Fdescription";
    private static final String COLUMN_FINVENTORY_QUANTITY = "Finventory_quantity";
    private static final String COLUMN_FCURRENCY_TYPE = "Fcurrency_type";
    private static final String COLUMN_FPRICE = "Fprice";
    private static final String COLUMN_FIMAGE_CODES = "Fimage_codes";
    private static final String COLUMN_FPRODUCT_STATUS = "Fproduct_status";
    private static final String COLUMN_FEXTEND_PROPERTIES = "Fextend_properties";
    private static final String COLUMN_FNEED_OPERATOR = "Fneed_operator";
    private static final String COLUMN_FOPERATION = "Foperation";
    private static final String COLUMN_FXCOIN_INCREMENT = "Fxcoin_increment";
    private static final String COLUMN_FTIME_INCREMENT = "Ftime_increment";
    private static final String COLUMN_FPURPOSE = "Fpurpose";
    private static final String COLUMN_FPURPOSE_TYPE = "Fpurpose_type";

    private static final String COLUMN_FCREATE_TIMESTAMP = "Fcreate_timestamp";
    private static final String COLUMN_FLAST_MODIFY_TIMESTAMP = "Flast_modify_timestamp";

    public ProductHandler(Connection conn, String roleName) {
        super(conn, roleName);
    }

    public void addProduct(Product product) throws SQLException {
        PreparedFields fields = getPreparedFields(product);

        long timeNow = System.currentTimeMillis() / 1000;
        fields.addLong(COLUMN_FCREATE_TIMESTAMP, timeNow);
        fields.addLong(COLUMN_FLAST_MODIFY_TIMESTAMP, timeNow);
        super.insert(fields);
    }

    public void updateProduct(Product product) throws SQLException {
        if (!product.isSetProductId()) {
            throw new IllegalArgumentException("Product productId must set.");
        }
        PreparedFields fields = getPreparedFields(product);
        long timeNow = System.currentTimeMillis() / 1000;
        fields.addLong(COLUMN_FLAST_MODIFY_TIMESTAMP, timeNow);
        super.update(fields, COLUMN_FSLED_PRODUCT_ID + "=?", product.getProductId());
    }

    public List<Product> selectAllForUpdate() throws SQLException {
        return super.getItemList(prepareSqlQueryBuilder(), true);
    }

    public Product selectForUpdate(int productId) throws SQLException {
        Preconditions.checkArgument(productId > 0);
        SqlQueryBuilder queryBuilder = super.prepareSqlQueryBuilder();
        queryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, COLUMN_FSLED_PRODUCT_ID + "=?", productId);
        return super.getItem(queryBuilder, true);
    }

    public ProductPage queryProduct(ReqProductOption option) throws SQLException {
        SqlQueryBuilder queryBuilder = super.prepareSqlQueryBuilder();
        boolean isNeedTotal = false;
        if (option.isSetType()) {
            queryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, COLUMN_FPRODUCT_TYPE + "=?", option.getType().getValue());
        }
        if (option.isSetCurrencyType()) {
            queryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, COLUMN_FCURRENCY_TYPE + "=?", option.getCurrencyType().getValue());
        }
        if (option.isSetProductIds() && option.getProductIdsSize() > 0) {
            queryBuilder.addInFieldCondition(SqlQueryBuilder.ConditionType.AND, COLUMN_FSLED_PRODUCT_ID, option.getProductIds());
        }
        if (option.isSetStatus()) {
            queryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, COLUMN_FPRODUCT_STATUS + "=?", option.getStatus().getValue());
        }
        if (option.isSetPurpose()) {
            queryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND, COLUMN_FPURPOSE + "=?", option.getPurpose().getValue());
        }
        if (option.isSetPageOption()) {

            if (option.getPageOption().isNeedTotalCount()) {
                isNeedTotal = option.getPageOption().isNeedTotalCount();
            }
            if (option.getPageOption().isSetPageIndex() && option.getPageOption().isSetPageSize()) {
                queryBuilder.setPage(option.getPageOption().getPageIndex(), option.getPageOption().getPageSize());
            }
        }
        queryBuilder.setOrder(SqlQueryBuilder.OrderType.ASC, COLUMN_FSLED_PRODUCT_ID);
        ProductPage page = new ProductPage();
        if (isNeedTotal) {
            page.setTotal(super.getTotalNum(queryBuilder));
        }
        page.setPage(super.getItemList(queryBuilder));
        return page;
    }

    private PreparedFields getPreparedFields(Product product) {
        PreparedFields fields = new PreparedFields();
        if (product.isSetType()) {
            fields.addInt(COLUMN_FPRODUCT_TYPE, product.getType().getValue());
        }
        if (product.isSetName()) {
            fields.addString(COLUMN_FPRODUCT_NAME, product.getName());
        }
        if (product.isSetDescription()) {
            fields.addString(COLUMN_FDESCRIPTION, product.getDescription());
        }
        if (product.isSetInventoryQuantity()) {
            fields.addInt(COLUMN_FINVENTORY_QUANTITY, product.getInventoryQuantity());
        }
        if (product.isSetPrice()) {
            fields.addDouble(COLUMN_FPRICE, product.getPrice());
        }
        if (product.isSetCurrencyType()) {
            fields.addInt(COLUMN_FCURRENCY_TYPE, product.getCurrencyType().getValue());
        }
        if (product.isSetImageCodes()) {
            fields.addString(COLUMN_FIMAGE_CODES, product.getImageCodes());
        }
        if (product.isSetStatus()) {
            fields.addInt(COLUMN_FPRODUCT_STATUS, product.getStatus().getValue());
        }
        if (product.isSetExtendProperties()) {
            fields.addString(COLUMN_FEXTEND_PROPERTIES, new Gson().toJson(product.getExtendProperties()));
        }
        if (product.isSetNeedOperator()) {
            fields.addInt(COLUMN_FNEED_OPERATOR, product.isNeedOperator() ? 1 : 0);
        }
        if (product.isSetOperation()) {
            fields.addInt(COLUMN_FOPERATION, product.getOperation().getValue());
        }
        if (product.isSetXCoinIncrement()) {
            fields.addDouble(COLUMN_FXCOIN_INCREMENT, product.getXCoinIncrement());
        }
        if (product.isSetTimeIncrement()) {
            fields.addLong(COLUMN_FTIME_INCREMENT, product.getTimeIncrement());
        }
        if (product.isSetPurpose()) {
            fields.addInt(COLUMN_FPURPOSE, product.getPurpose().getValue());
        }
        if (product.isSetPurposeType()) {
            fields.addInt(COLUMN_FPURPOSE_TYPE, product.getPurposeType().getValue());
        }
        return fields;
    }

    @Override
    protected String getTableNamePrefix() {
        return TABLE_NAME;
    }

    @Override
    public Product fromResultSet(ResultSet resultSet) throws Exception {
        Product product = new Product();
        product.setProductId(resultSet.getInt(COLUMN_FSLED_PRODUCT_ID));
        product.setType(ProductType.findByValue(resultSet.getInt(COLUMN_FPRODUCT_TYPE)));
        product.setName(resultSet.getString(COLUMN_FPRODUCT_NAME));
        product.setDescription(resultSet.getString(COLUMN_FDESCRIPTION));
        product.setInventoryQuantity(resultSet.getInt(COLUMN_FINVENTORY_QUANTITY));
        product.setPrice(resultSet.getDouble(COLUMN_FPRICE));
        product.setCurrencyType(CurrencyType.findByValue(resultSet.getInt(COLUMN_FCURRENCY_TYPE)));
        product.setImageCodes(resultSet.getString(COLUMN_FIMAGE_CODES));
        product.setStatus(ProductStatus.findByValue(resultSet.getInt(COLUMN_FPRODUCT_STATUS)));
        product.setExtendProperties(new Gson().fromJson(resultSet.getString(COLUMN_FEXTEND_PROPERTIES), new TypeToken<HashMap<String, String>>() {
        }.getType()));
        product.setNeedOperator(resultSet.getInt(COLUMN_FNEED_OPERATOR) == 1 ? true : false);
        product.setOperation(Operation.findByValue(resultSet.getInt(COLUMN_FOPERATION)));
        product.setXCoinIncrement(resultSet.getDouble(COLUMN_FXCOIN_INCREMENT));
        product.setTimeIncrement(resultSet.getLong(COLUMN_FTIME_INCREMENT));
        product.setPurpose(ProductPurpose.findByValue(resultSet.getInt(COLUMN_FPURPOSE)));
        product.setPurposeType(ProductType.findByValue(resultSet.getInt(COLUMN_FPURPOSE_TYPE)));
        product.setCreateTimestamp(resultSet.getLong(COLUMN_FCREATE_TIMESTAMP));
        product.setLastModifyTimestamp(resultSet.getLong(COLUMN_FLAST_MODIFY_TIMESTAMP));

        return product;
    }

    public static ProductPage reqPage(TServiceCntl oCntl, String roleName, ReqProductOption option) throws ErrorInfo {
        return new DBQueryHelper<ProductPage>(new DalSetDataSource(roleName, oCntl.getDalSetServiceName(), true, 0)) {
            @Override
            protected ProductPage onQuery(Connection connection) throws Exception {
                return new ProductHandler(connection, roleName).queryProduct(option);
            }
        }.query();
    }

    public static void addProduct(TServiceCntl oCntl, String roleName, Product product) throws ErrorInfo {

        new DBTransactionHelper<Product>(new DalSetDataSource(roleName, oCntl.getDalSetServiceName(), false, 0)) {
            @Override
            public void onPrepareData() throws ErrorInfo, Exception {
                if (!product.isSetName() || "".equals(product.getName().trim())) {
                    throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "product name not set.");
                }
                if (!product.isSetCurrencyType()) {
                    throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "product currency type not set.");
                }
                if (!product.isSetPrice()) {
                    throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "product price not set.");
                }
            }

            @Override
            public void onUpdate() throws ErrorInfo, Exception {
                new ProductHandler(getConnection(), roleName).addProduct(product);
            }

            @Override
            public Product getResult() {
                return null;
            }
        }.execute();
    }

    public static void updateProduct(TServiceCntl oCntl, String roleName, Product product) throws ErrorInfo {
        new DBTransactionHelper<Product>(new DalSetDataSource(roleName, oCntl.getDalSetServiceName(), false, 0)) {
            @Override
            public void onPrepareData() throws ErrorInfo, Exception {
                if (!product.isSetProductId() || product.getProductId() <= 0) {
                    throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "product name not set.");
                }
            }

            @Override
            public void onUpdate() throws ErrorInfo, Exception {
                new ProductHandler(getConnection(), roleName).updateProduct(product);
            }

            @Override
            public Product getResult() {
                return null;
            }
        }.execute();
    }
}
