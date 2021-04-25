package xueqiao.company.service.maintenance.handler;

import org.apache.thrift.TException;
import org.soldier.platform.app.manager.dao.thriftapi.ReqAppOption;
import org.soldier.platform.app.manager.dao.thriftapi.ReqAppVersionOption;
import org.soldier.platform.app.manager.dao.thriftapi.ReqProjectOption;
import org.soldier.platform.app.manager.dao.thriftapi.ReqServerAppSupportOption;
import org.soldier.platform.app.manager.thriftapi.*;
import org.soldier.platform.page.IndexedPageOption;

import java.util.List;

public interface IAppManager {

    List<Project> reqProject(ReqProjectOption option) throws TException;

    long addProject(Project project) throws TException;

    void updateProject(Project project) throws TException;

    void removeProject(long projectId) throws TException;


    List<App> reqApp(ReqAppOption option) throws TException;

    void addApp(App app) throws TException;

    void updateApp(App app) throws TException;

    void removeApp(long appId) throws TException;


    AppVersionPage reqAppVersion(ReqAppVersionOption option, IndexedPageOption pageOption) throws TException;

    void addAppVersion(AppVersion appVersion) throws TException;

    void updateAppVersion(AppVersion appVersion) throws TException;


    List<ServerAppSupport> reqServerAppSupport(ReqServerAppSupportOption option) throws TException;

    void addServerAppSupport(ServerAppSupport serverAppSupport) throws TException;

    void updateServerAppSupport(ServerAppSupport serverAppSupport) throws TException;
}
