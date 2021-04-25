package xueqiao.company.service.maintenance.handler;

import org.apache.thrift.TException;
import org.soldier.platform.app.manager.dao.thriftapi.ReqAppOption;
import org.soldier.platform.app.manager.dao.thriftapi.ReqAppVersionOption;
import org.soldier.platform.app.manager.dao.thriftapi.ReqProjectOption;
import org.soldier.platform.app.manager.dao.thriftapi.ReqServerAppSupportOption;
import org.soldier.platform.app.manager.dao.thriftapi.client.AppManagerDaoStub;
import org.soldier.platform.app.manager.thriftapi.*;
import org.soldier.platform.page.IndexedPageOption;

import java.util.List;

public class AppManager implements IAppManager {
    @Override
    public List<Project> reqProject(ReqProjectOption option) throws TException {
        return new AppManagerDaoStub().reqProject(option);
    }

    @Override
    public long addProject(Project project) throws TException {
        return new AppManagerDaoStub().addProject(project);
    }

    @Override
    public void updateProject(Project project) throws TException {
        new AppManagerDaoStub().updateProject(project);
    }

    @Override
    public void removeProject(long projectId) throws TException {
        new AppManagerDaoStub().removeProject(projectId);
    }

    @Override
    public List<App> reqApp(ReqAppOption option) throws TException {
        return new AppManagerDaoStub().reqApp(option);
    }

    @Override
    public void addApp(App app) throws TException {
        new AppManagerDaoStub().addApp(app);
    }

    @Override
    public void updateApp(App app) throws TException {
        new AppManagerDaoStub().updateApp(app);
    }

    @Override
    public void removeApp(long appId) throws TException {
        new AppManagerDaoStub().removeApp(appId);
    }

    @Override
    public AppVersionPage reqAppVersion(ReqAppVersionOption option, IndexedPageOption pageOption) throws TException {
        return new AppManagerDaoStub().reqAppVersion(option, pageOption);
    }

    @Override
    public void addAppVersion(AppVersion appVersion) throws TException {
        new AppManagerDaoStub().addAppVersion(appVersion);
    }

    @Override
    public void updateAppVersion(AppVersion appVersion) throws TException {
        new AppManagerDaoStub().updateAppVersion(appVersion);
    }

    @Override
    public List<ServerAppSupport> reqServerAppSupport(ReqServerAppSupportOption option) throws TException {
        return new AppManagerDaoStub().reqServerAppSupport(option);
    }

    @Override
    public void addServerAppSupport(ServerAppSupport serverAppSupport) throws TException {
        new AppManagerDaoStub().addServerAppSupport(serverAppSupport);
    }

    @Override
    public void updateServerAppSupport(ServerAppSupport serverAppSupport) throws TException {
        new AppManagerDaoStub().updateServerAppSupport(serverAppSupport);
    }
}
