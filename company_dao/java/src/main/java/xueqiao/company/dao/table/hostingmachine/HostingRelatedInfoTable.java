package xueqiao.company.dao.table.hostingmachine;

import com.google.common.base.Preconditions;
import org.soldier.base.sql.PreparedFields;
import org.soldier.base.sql.SqlQueryBuilder;
import org.soldier.base.sql.SqlQueryBuilder.ConditionType;
import org.soldier.base.sql.SqlQueryBuilder.OrderType;
import org.soldier.platform.dal_set.DalSetTableHelper;
import org.soldier.platform.id_maker.IdException;
import org.soldier.platform.id_maker.IdMaker;
import org.soldier.platform.page.IndexedPageOption;
import xueqiao.company.dao.server.impl.ConfigurationProperty;
import xueqiao.hosting.machine.HostingRelatedInfo;
import xueqiao.hosting.machine.HostingRelatedInfoPageResult;
import xueqiao.hosting.machine.QueryHostingRelatedInfoOption;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HostingRelatedInfoTable extends DalSetTableHelper<HostingRelatedInfo> {
	
	public HostingRelatedInfoTable(Connection conn, String roleName) {
		super(conn, roleName);
	}
	
	public long addRelatedInfo(HostingRelatedInfo relatedInfo) throws IdException, SQLException {
		long relatedId = ConfigurationProperty.instance().getRelatedIdMaker().createIdIntSafe();
		PreparedFields fields = new PreparedFields();
		fields.addLong("Frelated_id", relatedId);
		fields.addInt("Fcompany_id", relatedInfo.getCompanyId());
		fields.addInt("Fcompany_group_id", relatedInfo.getCompanyGroupId());
		fields.addLong("Fmachine_id", relatedInfo.getMachineId());
		if (relatedInfo.isSetMachineInnerIP()) {
			fields.addString("Fmachine_inner_ip", relatedInfo.getMachineInnerIP());
		}
		if (relatedInfo.isSetMachineOuterIP()) {
			fields.addString("Fmachine_outer_ip", relatedInfo.getMachineOuterIP());
		}
		if (relatedInfo.isSetActiveStartTimestamp()) {
			fields.addInt("Factive_start_timestamp", relatedInfo.getActiveStartTimestamp());
		}
		if (relatedInfo.isSetActivedEndTimestamp()) {
			fields.addInt("Factive_end_timestamp", relatedInfo.getActivedEndTimestamp());
		}
		fields.addInt("Fcreate_timestamp", (int)(System.currentTimeMillis()/1000));
		fields.addInt("Flastmodify_timestamp", (int)(System.currentTimeMillis()/1000));
		
		super.insert(fields);
		
		return relatedId;
	}
	
	public int updateRelatedInfo(HostingRelatedInfo relatedInfo) throws SQLException {
		Preconditions.checkArgument(relatedInfo.getRelatedId() > 0 || relatedInfo.getMachineId() > 0);
		
		PreparedFields fields = new PreparedFields();
		if (relatedInfo.isSetActiveStartTimestamp()) {
			fields.addInt("Factive_start_timestamp", relatedInfo.getActiveStartTimestamp());
		}
		if (relatedInfo.isSetActivedEndTimestamp()) {
			fields.addInt("Factive_end_timestamp", relatedInfo.getActivedEndTimestamp());
		}
		if (relatedInfo.getRelatedId() > 0) {
			if (relatedInfo.isSetMachineId()) {
				fields.addLong("Fmachine_id", relatedInfo.getMachineId());
			}
		}
		if (relatedInfo.isSetMachineInnerIP()) {
			fields.addString("Fmachine_inner_ip", relatedInfo.getMachineInnerIP());
		}
		if (relatedInfo.isSetMachineOuterIP()) {
			fields.addString("Fmachine_outer_ip", relatedInfo.getMachineOuterIP());
		}
		if (fields.getSize() <= 0) {
			return 0;
		}
		
		if (relatedInfo.getRelatedId() > 0) {
			return super.update(fields, "Frelated_id=?", relatedInfo.getRelatedId());
		} else {
			return super.update(fields, "Fmachine_id=?", relatedInfo.getMachineId());
		}
	}
	
	public int deleteRelatedInfo(long relatedId) throws SQLException {
		Preconditions.checkArgument(relatedId > 0);
		return super.delete("Frelated_id=?", relatedId);
	}
	
	public HostingRelatedInfo getRelatedInfo(long relatedId) throws SQLException {
		Preconditions.checkArgument(relatedId > 0);
		SqlQueryBuilder queryBuilder = super.prepareSqlQueryBuilder();
		queryBuilder.addFieldCondition(ConditionType.AND, "Frelated_id=?", relatedId);
		return super.getItem(queryBuilder);
	}

	public HostingRelatedInfo getRelatedInfoByMachineId(long machineId) throws SQLException {
		Preconditions.checkArgument(machineId > 0);
		SqlQueryBuilder queryBuilder = super.prepareSqlQueryBuilder();
		queryBuilder.addFieldCondition(ConditionType.AND, "Fmachine_id=?", machineId);
		return super.getItem(queryBuilder);
	}
	
	public HostingRelatedInfoPageResult queryRelatedInfoPage(QueryHostingRelatedInfoOption option, IndexedPageOption pageOption) throws SQLException {
		SqlQueryBuilder queryBuilder = super.prepareSqlQueryBuilder();
		
		if (option != null) {
			if (option.isSetRelatedId()) {
				queryBuilder.addFieldCondition(ConditionType.AND
						, "Frelated_id=?", option.getRelatedId());
			}
			if (option.isSetCompanyId()) {
				queryBuilder.addFieldCondition(ConditionType.AND
						, "Fcompany_id=?", option.getCompanyId());
			}
			if (option.isSetCompanyGroupId()) {
				queryBuilder.addFieldCondition(ConditionType.AND
						, "Fcompany_group_id=?", option.getCompanyGroupId());
			}
			if (option.isSetMachineId()) {
				queryBuilder.addFieldCondition(ConditionType.AND
						, "Fmachine_id=?", option.getMachineId());
			}
		}
		queryBuilder.setOrder(OrderType.ASC, "Factive_end_timestamp");
		if (pageOption != null) {
			queryBuilder.setPage(pageOption.getPageIndex(), pageOption.getPageSize());
		}

		HostingRelatedInfoPageResult pageResult = new HostingRelatedInfoPageResult();
		if (pageOption != null && pageOption.isNeedTotalCount()) {
			pageResult.setTotalNum(super.getTotalNum(queryBuilder));
		}
		pageResult.setResultList(super.getItemList(queryBuilder));
		return pageResult;
	}

	@Override
	public HostingRelatedInfo fromResultSet(ResultSet rs) throws Exception {
		HostingRelatedInfo relatedInfo = new HostingRelatedInfo();
		relatedInfo.setRelatedId(rs.getLong("Frelated_id"));
		relatedInfo.setCompanyId(rs.getInt("Fcompany_id"));
		relatedInfo.setMachineId(rs.getLong("Fmachine_id"));
		relatedInfo.setCompanyGroupId(rs.getInt("Fcompany_group_id"));
		relatedInfo.setActiveStartTimestamp(rs.getInt("Factive_start_timestamp"));
		relatedInfo.setActivedEndTimestamp(rs.getInt("Factive_end_timestamp"));
		relatedInfo.setMachineInnerIP(rs.getString("Fmachine_inner_ip"));
		relatedInfo.setMachineOuterIP(rs.getString("Fmachine_outer_ip"));
		relatedInfo.setCreateTimestamp(rs.getInt("Fcreate_timestamp"));
		relatedInfo.setLastmodifyTimestamp(rs.getInt("Flastmodify_timestamp"));
		return relatedInfo;
	}

	@Override
	protected String getTableNamePrefix() {
		return "thosting_related_info";
	}

}
