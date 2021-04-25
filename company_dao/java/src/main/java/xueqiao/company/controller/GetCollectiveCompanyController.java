package xueqiao.company.controller;

import org.apache.commons.lang.StringUtils;
import org.apache.thrift.TException;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.db_helper.DBQueryHelper;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import org.soldier.platform.svr_platform.container.TServiceCntl;
import xueqiao.company.CompanyEntry;
import xueqiao.company.CompanyType;
import xueqiao.company.controller.base.AbstractController;
import xueqiao.company.dao.handler.CompanyHandler;
import xueqiao.company.dao.table.company.CompanyTable;
import xueqiao.company.util.security.MD5;

import java.sql.Connection;

/**
 * 这里确保只有一个个人版集体挂靠公司
 */
public class GetCollectiveCompanyController extends AbstractController {

    private static final String COMPANY_NAME = "雪橇科技";
    private static final String COMPANY_CODE = "雪橇云服务个人版";
    private static final String PASSWD = "xueqiao123456";

    public GetCollectiveCompanyController(TServiceCntl oCntl) {
        super(oCntl);
    }

    @Override
    public void checkParams() throws TException {
        // no params
    }

    public CompanyEntry doGet() throws ErrorInfo {
        /*
         * 如何有环境变量指定公司，则优先使用指定的公司
         * */
        String collectiveCompanyIdStr = System.getenv("collective_company_id");
        AppLog.i(" System.getenv ---- collectiveCompanyIdStr : " + collectiveCompanyIdStr);
        if (StringUtils.isNotBlank(collectiveCompanyIdStr)) {
            int collectiveCompanyId = 0;
            try {
                collectiveCompanyId = Integer.parseInt(collectiveCompanyIdStr);
            } catch (Throwable throwable) {
                AppLog.e("Integer.parseInt error, collectiveCompanyIdStr : " + collectiveCompanyIdStr);
            }
            if (collectiveCompanyId > 0) {
                CompanyEntry companyEntry = queryCompanyById(collectiveCompanyId);
                if (companyEntry != null) {
                    return companyEntry;
                }
            }
        }

        /*
         * 如何没有指定公司，或查找指定公司失败，则使用预定的规则查找或生成个人版集体挂靠公司
         * */
        return new DBQueryHelper<CompanyEntry>(getDataSource()) {

            @Override
            protected CompanyEntry onQuery(Connection connection) throws Exception {
                CompanyTable table = new CompanyTable(connection);
                CompanyEntry collectiveCompany = table.query(CompanyType.COLLECTIVE);
                if (collectiveCompany != null) {
                    return collectiveCompany;
                }
                CompanyEntry newCompanyEntry = new CompanyEntry();
                newCompanyEntry.setType(CompanyType.COLLECTIVE);
                newCompanyEntry.setCompanyName(COMPANY_NAME);
                newCompanyEntry.setCompanyCode(COMPANY_CODE);
                newCompanyEntry.setPassword(MD5.crypto(PASSWD));
                int companyId = CompanyHandler.doAddCompany(connection, newCompanyEntry);
                return table.query(companyId);
            }
        }.query();
    }

    private CompanyEntry queryCompanyById(int companyid) throws ErrorInfo {
        return new DBQueryHelper<CompanyEntry>(getDataSource()) {
            @Override
            protected CompanyEntry onQuery(Connection connection) throws Exception {
                return new CompanyTable(connection).query(companyid);
            }
        }.query();
    }
}
