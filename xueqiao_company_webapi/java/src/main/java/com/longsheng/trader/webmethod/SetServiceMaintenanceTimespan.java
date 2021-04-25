package com.longsheng.trader.webmethod;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.longsheng.trader.BaseResult;
import com.longsheng.trader.ValueResult;
import com.longsheng.trader.controller.MXueqiaoServiceMaintenance;
import com.longsheng.trader.webmethod.base.WebRequest;
import com.longsheng.trader.webmethod.base.WebUser;
import com.longsheng.trader.webmethod.base.WebUserMethod;
import org.soldier.base.logger.AppLog;
import org.soldier.platform.svr_platform.comm.ErrorInfo;
import xueqiao.company.service.maintenance.MaintenanceTimeSpan;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetServiceMaintenanceTimespan extends WebUserMethod {
    @Override
    protected BaseResult doUserMethod(WebRequest request, WebUser user) throws Exception {
        String timeSpanJson = request.getParameter("timeSpanJson", "");
        int companyId = user.getUserId();
        List<Integer> timeSpans = null;
        try {
            timeSpans = new Gson().fromJson(timeSpanJson, new TypeToken<List<Integer>>() {
            }.getType());
        } catch (Exception e) {
            AppLog.e("SetServiceMaintenanceTimespan # timeSpans convert to bean error : " + timeSpanJson, e);
            throw new ErrorInfo(6003,"维护时间不正确");
        }

        if (timeSpans == null) {
            AppLog.e("SetServiceMaintenanceTimespan # timeSpans is null ---- timeSpanJson : " + timeSpanJson);
            throw new ErrorInfo(6004,"维护时间不正确");
        }

        Set<MaintenanceTimeSpan> timeSpanSet = new HashSet<>();
        for (int timeSpan : timeSpans) {
            timeSpanSet.add(MaintenanceTimeSpan.findByValue(timeSpan));
        }

        MXueqiaoServiceMaintenance.getInstance().setServiceMaintenanceTimespan(companyId, timeSpanSet);
        return new ValueResult<String>("success");
    }
}
