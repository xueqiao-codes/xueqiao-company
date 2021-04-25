package xueqiao.company.service.maintenance.handler;

import com.antiy.error_code.ErrorCodeOuter;
import org.apache.commons.lang.StringUtils;
import org.apache.thrift.TException;
import org.soldier.platform.app.manager.dao.thriftapi.ReqAppOption;
import org.soldier.platform.app.manager.dao.thriftapi.ReqAppVersionOption;
import org.soldier.platform.app.manager.thriftapi.*;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import xueqiao.company.service.maintenance.ServiceMaintenance;
import xueqiao.company.service.maintenance.config.ConfigurationProperty;
import xueqiao.company.service.maintenance.dao.thriftapi.ReqServiceMaintenanceOption;
import xueqiao.company.service.maintenance.dao.thriftapi.ServiceMaintenancePage;
import xueqiao.company.service.maintenance.webapi.thriftapi.VersionInfo;

import java.util.*;

public class VersionHandler {

    public List<VersionInfo> reqCompanyExistVersionTags() throws TException {

        IServiceMaintenanceApi serviceMaintenanceApi = ServiceMaintenanceApiFactory.getInstance().getDefault();
        ReqServiceMaintenanceOption option = new ReqServiceMaintenanceOption();
        ServiceMaintenancePage page = serviceMaintenanceApi.reqServiceMaintenance(option, null);
        List<VersionInfo> versionInfos = new ArrayList<>();
        Map<Long, String> versionTags = new HashMap<>();
        if (page.getPageSize() > 0) {
            for (ServiceMaintenance maintenance : page.getPage()) {
                String tag = versionTags.get(maintenance.getVersionId());
                if (tag == null) {
                    VersionInfo versionInfo = new VersionInfo();
                    versionInfo.setVersionId(maintenance.getVersionId());
                    versionInfo.setVersionTag(maintenance.getVersionTag());
                    versionInfos.add(versionInfo);
                    versionTags.put(maintenance.getVersionId(), maintenance.getVersionTag());
                }
            }
        }
        return versionInfos;
    }

    public List<VersionInfo> reqCompanyUpgradeVersion(Set<Long> companyIds) throws TException {

        IServiceMaintenanceApi serviceMaintenanceApi = ServiceMaintenanceApiFactory.getInstance().getDefault();
        ReqServiceMaintenanceOption option = new ReqServiceMaintenanceOption();
        option.setCompanyIds(companyIds);
        ServiceMaintenancePage page = serviceMaintenanceApi.reqServiceMaintenance(option, null);
        int maxTagNum = 0;
        ServiceMaintenance targetMaintenance = null;
        if (page.getPageSize() > 0) {
            for (ServiceMaintenance serviceMaintenance : page.getPage()) {
                String versionTag = serviceMaintenance.getVersionTag();
                int targetTagNum = getTagNum(versionTag);
                if (targetTagNum >= maxTagNum) {
                    maxTagNum = targetTagNum;
                    targetMaintenance = serviceMaintenance;
                }
            }
        }
        if (targetMaintenance == null) {
            throw new ErrorInfo(ErrorCodeOuter.PARAM_ERROR.getErrorCode(), "Company service maintenance not found.");
        }

        return getVersionInfos(true, targetMaintenance);
    }

    public List<VersionInfo> reqCompanyVersion(long companyId, boolean isUpgrade) throws TException {
        ServiceMaintenance targetMaintenance = getServiceMaintenance(companyId);
        return getVersionInfos(isUpgrade, targetMaintenance);
    }

    public List<VersionInfo> reqServerVersions() throws TException {
        List<VersionInfo> versionInfos = new ArrayList<>();
        String appKey = ConfigurationProperty.instance().getAppKey();
        IAppManager iAppManager = AppManagerFactory.getInstance().getDefault();
        ReqAppOption reqAppOption = new ReqAppOption();
        reqAppOption.setAppKey(appKey);

        List<App> apps = iAppManager.reqApp(reqAppOption);
        if (apps.size() == 0) {
            return versionInfos;
        }
        long appId = apps.get(0).getAppId();

        ReqAppVersionOption reqVersionOption = new ReqAppVersionOption();
        reqVersionOption.setAppId(appId);
        reqVersionOption.setVersionState(VersionState.ENABLE);
        AppVersionPage allVersions = iAppManager.reqAppVersion(reqVersionOption, null);
        for (AppVersion appVersion : allVersions.getPage()) {
            VersionInfo versionInfo = new VersionInfo();
            versionInfo.setVersionId(appVersion.getVersionId());
            versionInfo.setVersionTag(appVersion.getVersionNumTag());
            versionInfo.setVersionTimestamp(appVersion.getCreateTimestamp());
            versionInfos.add(versionInfo);
        }

        return versionInfos;
    }

    private List<VersionInfo> getVersionInfos(boolean isUpgrade, ServiceMaintenance targetMaintenance) throws TException {
        List<VersionInfo> versionInfos = new ArrayList<>();
        if (targetMaintenance == null) {
            return versionInfos;
        }

        String appKey = ConfigurationProperty.instance().getAppKey();
        IAppManager iAppManager = AppManagerFactory.getInstance().getDefault();
        ReqAppOption reqAppOption = new ReqAppOption();
        reqAppOption.setAppKey(appKey);

        List<App> apps = iAppManager.reqApp(reqAppOption);
        if (apps.size() == 0) {
            return versionInfos;
        }
        long appId = apps.get(0).getAppId();

        String versionTag = targetMaintenance.getVersionTag();
        int targetTagNum = getTagNum(versionTag);
        ReqAppVersionOption reqVersionOption = new ReqAppVersionOption();
        reqVersionOption.setAppId(appId);
        AppVersionPage allVersions = iAppManager.reqAppVersion(reqVersionOption, null);
        for (AppVersion appVersion : allVersions.getPage()) {
            String tag = appVersion.getVersionNumTag();
            int tagNum = getTagNum(tag);
            if (isUpgrade) {
                if (tagNum > targetTagNum) {
                    VersionInfo versionInfo = new VersionInfo();
                    versionInfo.setVersionId(appVersion.getVersionId());
                    versionInfo.setVersionTag(appVersion.getVersionNumTag());
                    versionInfo.setVersionTimestamp(appVersion.getCreateTimestamp());
                    versionInfos.add(versionInfo);
                }
            } else {
                if (tagNum < targetTagNum) {
                    VersionInfo versionInfo = new VersionInfo();
                    versionInfo.setVersionId(appVersion.getVersionId());
                    versionInfo.setVersionTag(appVersion.getVersionNumTag());
                    versionInfo.setVersionTimestamp(appVersion.getCreateTimestamp());
                    versionInfos.add(versionInfo);
                }
            }
        }

        return versionInfos;
    }

    private ServiceMaintenance getServiceMaintenance(long companyId) throws TException {
        IServiceMaintenanceApi serviceMaintenanceApi = ServiceMaintenanceApiFactory.getInstance().getDefault();
        ReqServiceMaintenanceOption option = new ReqServiceMaintenanceOption();
        option.addToCompanyIds(companyId);
        ServiceMaintenancePage page = serviceMaintenanceApi.reqServiceMaintenance(option, null);
        ServiceMaintenance targetMaintenance = null;
        if (page.getPageSize() > 0) {
            targetMaintenance = page.getPage().get(0);
        }
        return targetMaintenance;
    }

    private int getTagNum(String versionTag) {
        if (StringUtils.isEmpty(versionTag)) {
            return 0;
        }
        return Integer.valueOf(versionTag.toUpperCase().replace("V", ""));
    }
}
