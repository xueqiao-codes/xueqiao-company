package xueqiao.company.dao.table.company;

import org.soldier.base.sql.PreparedFields;
import org.soldier.platform.dal_set.DalSetTableHelper;
import xueqiao.company.CompanyGroup;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

// 用于用户删除组时作为备份
public class DeletedGroupTable extends DalSetTableHelper<CompanyGroup> {

    private static final String TABLE_NAME = "tdeleted_group";
    private static final String COLUMN_FGROUP_ID = "Fgroup_id";
    private static final String COLUMN_FCOMPANY_ID = "Fcompany_id";
    private static final String COLUMN_FGROUP_CODE = "Fgroup_code";
    private static final String COLUMN_FGROUP_NAME = "Fgroup_name";
    private static final String COLUMN_FAES_KEY = "Faes_key";

    private static final String COLUMN_FCREATE_TIMESTAMP = "Fcreate_timestamp";
    private static final String COLUMN_FLAST_MODIFY_TIMESTAMP = "Flastmodify_timestamp";

    public DeletedGroupTable(Connection conn, String roleName) {
        super(conn, roleName);
    }


    @Override
    protected String getTableNamePrefix() {
        return TABLE_NAME;
    }

    @Override
    public CompanyGroup fromResultSet(ResultSet resultSet) throws Exception {
        CompanyGroup companyGroup = new CompanyGroup();
        companyGroup.setGroupId(resultSet.getInt(COLUMN_FGROUP_ID));
        companyGroup.setCompanyId(resultSet.getInt(COLUMN_FCOMPANY_ID));
        companyGroup.setGroupCode(resultSet.getString(COLUMN_FGROUP_CODE));
        companyGroup.setGroupName(resultSet.getString(COLUMN_FGROUP_NAME));
        companyGroup.setHostingAES16Key(resultSet.getString(COLUMN_FAES_KEY));
        companyGroup.setCreateTimestamp(resultSet.getInt(COLUMN_FCREATE_TIMESTAMP));
        companyGroup.setLastmodifyTimestamp(resultSet.getInt(COLUMN_FLAST_MODIFY_TIMESTAMP));
        return companyGroup;
    }

    public void add(CompanyGroup newGroup)
            throws SQLException {
        PreparedFields fields = new PreparedFields();
        fields.addInt("Fcompany_id", newGroup.getCompanyId());
        fields.addInt("Fgroup_id", newGroup.getGroupId());
        fields.addString("Fgroup_name", newGroup.getGroupName().trim());
        if (newGroup.isSetGroupCode()) {
            fields.addString("Fgroup_code", newGroup.getGroupCode().trim());
        }
        fields.addString("Faes_key", newGroup.getHostingAES16Key());

        fields.addInt("Fcreate_timestamp", (int) (System.currentTimeMillis() / 1000));
        fields.addInt("Flastmodify_timestamp", (int) (System.currentTimeMillis() / 1000));
        super.insert(fields);
    }
}
