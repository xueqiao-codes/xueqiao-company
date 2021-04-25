//package com.longsheng.xueqiao.payment.dao.table.payment;
//
//import com.longsheng.xueqiao.payment.product.thriftapi.ProductType;
//import com.longsheng.xueqiao.payment.thriftapi.CompanyGroupSpec;
//import com.longsheng.xueqiao.payment.thriftapi.CompanyGroupSpecPage;
//import com.longsheng.xueqiao.payment.thriftapi.ReqCompanyGroupSpecOption;
//import org.soldier.base.sql.PreparedFields;
//import org.soldier.base.sql.SqlQueryBuilder;
//import org.soldier.platform.dal_set.DalSetTableHelper;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class GroupSpecHandler extends DalSetTableHelper<CompanyGroupSpec> {
//
//    private static final String TABLE_NAME = "t_group_spec";
//    private static final String COLUMN_FCOMPANY_ID = "Fcompany_id";
//    private static final String COLUMN_FCOMPANY_GROUP_ID = "Fcompany_group_id";
//    private static final String COLUMN_FEXPIRED_TIMESTAMP = "Fexpired_timestamp";
//    private static final String COLUMN_FPRODUCT_TYPE = "Fproduct_type";
//
//    private static final String COLUMN_FCREATE_TIMESTAMP = "Fcreate_timestamp";
//    private static final String COLUMN_FLAST_MODIFY_TIMESTAMP = "Flast_modify_timestamp";
//
//
//    public GroupSpecHandler(Connection conn, String roleName) {
//        super(conn, roleName);
//    }
//
//    @Override
//    protected String getTableNamePrefix() {
//        return TABLE_NAME;
//    }
//
//    @Override
//    public CompanyGroupSpec fromResultSet(ResultSet resultSet) throws Exception {
//        CompanyGroupSpec spec = new CompanyGroupSpec();
//        spec.setCompanyId(resultSet.getInt(COLUMN_FCOMPANY_ID));
//        spec.setGroupId(resultSet.getInt(COLUMN_FCOMPANY_GROUP_ID));
//        spec.setExpiredTimestamp(resultSet.getLong(COLUMN_FEXPIRED_TIMESTAMP));
//        spec.setProductType(ProductType.findByValue(resultSet.getInt(COLUMN_FPRODUCT_TYPE)));
//        spec.setCreateTimestamp(resultSet.getLong(COLUMN_FCREATE_TIMESTAMP));
//        spec.setLastModifyTimestamp(resultSet.getLong(COLUMN_FLAST_MODIFY_TIMESTAMP));
//        return spec;
//    }
//
//    public void add(CompanyGroupSpec companyGroupSpec) throws SQLException {
//        if (companyGroupSpec.getCompanyId() <= 0) {
//            throw new IllegalArgumentException("CompanyGroupSpec companyId must > 0.");
//        }
//        if (companyGroupSpec.getGroupId() <= 0) {
//            throw new IllegalArgumentException("CompanyGroupSpec groupId must > 0.");
//        }
//        PreparedFields fields = getPreparedFields(companyGroupSpec);
//        fields.addInt(COLUMN_FCOMPANY_ID, companyGroupSpec.getCompanyId());
//        fields.addInt(COLUMN_FCOMPANY_GROUP_ID,companyGroupSpec.getGroupId());
//
//        long timeNow = System.currentTimeMillis() / 1000;
//        fields.addLong(COLUMN_FCREATE_TIMESTAMP, timeNow);
//        fields.addLong(COLUMN_FLAST_MODIFY_TIMESTAMP, timeNow);
//        super.insert(fields);
//    }
//
//    public CompanyGroupSpecPage query(ReqCompanyGroupSpecOption option) throws SQLException {
//        SqlQueryBuilder queryBuilder = super.prepareSqlQueryBuilder();
//        boolean isNeedTotal = false;
//
//        if (option.isSetCompanyIds()) {
//            queryBuilder.addInFieldCondition(SqlQueryBuilder.ConditionType.AND, COLUMN_FCOMPANY_ID, option.getCompanyIds());
//        }
//        if (option.isSetGroupId()){
//            queryBuilder.addFieldCondition(SqlQueryBuilder.ConditionType.AND,COLUMN_FCOMPANY_GROUP_ID+"=?",option.getGroupId());
//        }
//        if (option.isSetPageOption()) {
//            if (option.getPageOption().isNeedTotalCount()) {
//                isNeedTotal = option.getPageOption().isNeedTotalCount();
//            }
//            if (option.getPageOption().isSetPageIndex() && option.getPageOption().isSetPageSize()) {
//                queryBuilder.setPage(option.getPageOption().getPageIndex(), option.getPageOption().getPageSize());
//            }
//        }
//        queryBuilder.setOrder(SqlQueryBuilder.OrderType.ASC, COLUMN_FCOMPANY_GROUP_ID);
//        CompanyGroupSpecPage page = new CompanyGroupSpecPage();
//        if (isNeedTotal) {
//            page.setTotal(super.getTotalNum(queryBuilder));
//        }
//        page.setPage(super.getItemList(queryBuilder));
//        return page;
//    }
//
//    public void update(CompanyGroupSpec companyGroupSpec) throws SQLException {
//        if (companyGroupSpec.getCompanyId() <= 0) {
//            throw new IllegalArgumentException("CompanyBalance companyId must > 0.");
//        }
//        PreparedFields fields = getPreparedFields(companyGroupSpec);
//        long timeNow = System.currentTimeMillis() / 1000;
//        fields.addLong(COLUMN_FLAST_MODIFY_TIMESTAMP, timeNow);
//        super.update(fields, COLUMN_FCOMPANY_ID + "=? AND " + COLUMN_FCOMPANY_GROUP_ID + " =? ", companyGroupSpec.getCompanyId(), companyGroupSpec.getGroupId());
//    }
//
//    private PreparedFields getPreparedFields(CompanyGroupSpec companyGroupSpec) {
//        PreparedFields fields = new PreparedFields();
//        if (companyGroupSpec.isSetExpiredTimestamp()) {
//            fields.addLong(COLUMN_FEXPIRED_TIMESTAMP, companyGroupSpec.getExpiredTimestamp());
//        }
//        if (companyGroupSpec.isSetProductType()) {
//            fields.addInt(COLUMN_FPRODUCT_TYPE, companyGroupSpec.getProductType().getValue());
//        }
//        return fields;
//    }
//}
