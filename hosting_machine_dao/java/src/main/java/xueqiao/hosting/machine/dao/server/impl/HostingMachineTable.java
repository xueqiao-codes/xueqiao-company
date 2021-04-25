package xueqiao.hosting.machine.dao.server.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.soldier.base.logger.AppLog;
import org.soldier.base.sql.PreparedFields;
import org.soldier.base.sql.SqlQueryBuilder;
import org.soldier.base.sql.SqlQueryBuilder.ConditionType;
import org.soldier.base.sql.SqlQueryBuilder.OrderType;
import org.soldier.platform.dal_set.DalSetTableHelper;
import org.soldier.platform.id_maker.IdException;
import org.soldier.platform.id_maker.IdMaker;
import org.soldier.platform.page.IndexedPageOption;

import com.google.common.base.Preconditions;

import xueqiao.hosting.machine.*;

public class HostingMachineTable extends DalSetTableHelper<HostingMachine> {

    public HostingMachineTable(Connection conn, String roleName) {
        super(conn, roleName);
    }

    public long addHostingMachine(IdMaker machineIdMaker, HostingMachine newMachine)
            throws IdException, SQLException {
        long newMachineId = machineIdMaker.createId();

        PreparedFields fields = getPreparedFields(newMachine);
        fields.addLong("Fmachine_id", newMachineId);
        fields.addInt("Fcreate_timestamp", (int) (System.currentTimeMillis() / 1000));
        super.insert(fields);

        return newMachineId;
    }

    public int updateHostingMachine(HostingMachine updateMachine) throws SQLException {
        Preconditions.checkArgument(updateMachine.getMachineId() > 0);

        PreparedFields fields = getPreparedFields(updateMachine);
        AppLog.i("fields: "+ fields.getPreparedSql());
        return super.update(fields, "Fmachine_id=?", updateMachine.getMachineId());
    }

    private PreparedFields getPreparedFields(HostingMachine updateMachine) {
        PreparedFields fields = new PreparedFields();
        if (updateMachine.isSetMachineInnerIP()) {
            fields.addString("Fmachine_inner_ip", updateMachine.getMachineInnerIP().trim());
        }
        if (updateMachine.isSetMachineOuterIP()) {
            fields.addString("Fmachine_outer_ip", updateMachine.getMachineOuterIP().trim());
        }
        if (updateMachine.isSetMachineHostname()) {
            fields.addString("Fmachine_hostname", updateMachine.getMachineHostname());
        }
        if (updateMachine.isSetMachineRunningStatus()) {
            fields.addInt("Frunning_status", updateMachine.getMachineRunningStatus().getValue());
        }
        if (updateMachine.isSetMachineAssignStatus()) {
            fields.addInt("Fassign_status", updateMachine.getMachineAssignStatus().getValue());
        }
        if (updateMachine.isSetInstanceId()) {
            fields.addString("Finstance_id", updateMachine.getInstanceId().trim());
        }
        if (updateMachine.isSetRegionId()) {
            fields.addString("Fregion_id", updateMachine.getRegionId().trim());
        }

        if (updateMachine.isSetMachineSpec()) {
            fieldsFromSpec(fields, updateMachine.getMachineSpec());
        }
        fields.addInt("Flastmodify_timestamp", (int) (System.currentTimeMillis() / 1000));
        return fields;
    }

    public int deleteHostingMachine(long machineId) throws SQLException {
        return super.delete("Fmachine_id=?", machineId);
    }

    public List<HostingMachine> getUniqueKeyMachine(
            String hostname, String innerIP, String outerIP) throws SQLException {
        SqlQueryBuilder queryBuilder = super.prepareSqlQueryBuilder();
        boolean needSelect = false;
        if (StringUtils.isNotBlank(hostname)) {
            queryBuilder.addFieldCondition(ConditionType.OR, "Fmachine_hostname=?", hostname);
            needSelect = true;
        }
        if (StringUtils.isNotBlank(innerIP)) {
            queryBuilder.addFieldCondition(ConditionType.OR, "Fmachine_inner_ip=?", innerIP);
            needSelect = true;
        }
        if (StringUtils.isNotBlank(outerIP)) {
            queryBuilder.addFieldCondition(ConditionType.OR, "Fmachine_outer_ip=?", outerIP);
            needSelect = true;
        }
        if (!needSelect) {
            return new ArrayList<HostingMachine>();
        }
        return super.getItemList(queryBuilder);
    }

    public HostingMachine selectForUpdate(long machineId) throws SQLException {
        Preconditions.checkArgument(machineId > 0);

        SqlQueryBuilder queryBuilder = super.prepareSqlQueryBuilder();
        queryBuilder.addFieldCondition(ConditionType.AND, "Fmachine_id=?", machineId);

        return super.getItem(queryBuilder, true);
    }

    public List<HostingMachine> selectAllForUpdate() throws SQLException {
        return getItemList(prepareSqlQueryBuilder(), true);
    }

    public HostingMachinePageResult getPageResult(
            QueryHostingMachineOption queryOption
            , IndexedPageOption pageOption) throws SQLException {
        SqlQueryBuilder queryBuilder = super.prepareSqlQueryBuilder();

        if (queryOption != null) {
            if (queryOption.isSetMachineId()) {
                queryBuilder.addFieldCondition(ConditionType.AND, "Fmachine_id=?", queryOption.getMachineId());
            }
            if (queryOption.isSetMachineHostnamePartical()) {
                queryBuilder.addFieldCondition(ConditionType.AND, "Fmachine_hostname like ?"
                        , "%" + queryOption.getMachineHostnamePartical() + "%");
            }
            if (queryOption.isSetMachineHostnameWhole()) {
                queryBuilder.addFieldCondition(ConditionType.AND, "Fmachine_hostname=?"
                        , queryOption.getMachineHostnameWhole());
            }
            if (queryOption.isSetMachineInnerIPPartical()) {
                queryBuilder.addFieldCondition(ConditionType.AND, "Fmachine_inner_ip like ?"
                        , "%" + queryOption.getMachineInnerIPPartical() + "%");
            }
            if (queryOption.isSetMachineInnerIPWhole()) {
                queryBuilder.addFieldCondition(ConditionType.AND
                        , "Fmachine_inner_ip=?", queryOption.getMachineInnerIPWhole());
            }
            if (queryOption.isSetMachineOuterIPPartical()) {
                queryBuilder.addFieldCondition(ConditionType.AND
                        , "Fmachine_outer_ip like ?"
                        , "%" + queryOption.getMachineOuterIPPartical() + "%");
            }
            if (queryOption.isSetMachineOuterIPWhole()) {
                queryBuilder.addFieldCondition(ConditionType.AND
                        , "Fmachine_outer_ip=?"
                        , queryOption.getMachineOuterIPWhole());
            }
            if (queryOption.isSetMachineAssignStatus()) {
                queryBuilder.addFieldCondition(ConditionType.AND, "Fassign_status=?", queryOption.getMachineAssignStatus().getValue());
            }
        }

        queryBuilder.setOrder(OrderType.ASC, "Fmachine_hostname");
        queryBuilder.setPage(pageOption.getPageIndex(), pageOption.getPageSize());

        HostingMachinePageResult pageResult = new HostingMachinePageResult();
        if (pageOption.isNeedTotalCount()) {
            pageResult.setTotalNum(super.getTotalNum(queryBuilder));
        }
        pageResult.setResultList(super.getItemList(queryBuilder));
        return pageResult;
    }

    private void fieldsFromSpec(PreparedFields fields, HostingMachineSpec spec) {
        if (spec.isSetMemoryGB()) {
            fields.addInt("Fspec_memory_gb", spec.getMemoryGB());
        }
        if (spec.isSetCpuCount()) {
            fields.addInt("Fspec_cpu_count", spec.getCpuCount());
        }
        if (spec.isSetOutIfBandMB()) {
            fields.addInt("Fspec_outif_bandmb", spec.getOutIfBandMB());
        }
    }

    @Override
    public HostingMachine fromResultSet(ResultSet rs) throws SQLException {
        HostingMachine machine = new HostingMachine();
        machine.setMachineId(rs.getLong("Fmachine_id"));
        machine.setMachineInnerIP(rs.getString("Fmachine_inner_ip"));
        machine.setMachineOuterIP(rs.getString("Fmachine_outer_ip"));
        machine.setMachineHostname(rs.getString("Fmachine_hostname"));
        machine.setMachineRunningStatus(HostingMachineRunningStatus.findByValue(rs.getInt("Frunning_status")));
        machine.setMachineAssignStatus(HostingMachineAssignStatus.findByValue(rs.getInt("Fassign_status")));

        machine.setInstanceId(rs.getString("Finstance_id"));
        machine.setRegionId(rs.getString("Fregion_id"));

        HostingMachineSpec spec = new HostingMachineSpec();
        spec.setMemoryGB(rs.getInt("Fspec_memory_gb"));
        spec.setCpuCount(rs.getInt("Fspec_cpu_count"));
        spec.setOutIfBandMB(rs.getInt("Fspec_outif_bandmb"));
        machine.setMachineSpec(spec);

        machine.setCreateTimestamp(rs.getInt("Fcreate_timestamp"));
        machine.setLastmodifyTimestamp(rs.getInt("Flastmodify_timestamp"));
        return machine;
    }

    @Override
    protected String getTableNamePrefix() {
        return "thosting_machine";
    }
}
