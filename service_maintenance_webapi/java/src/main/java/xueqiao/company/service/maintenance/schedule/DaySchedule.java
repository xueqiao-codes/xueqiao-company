package xueqiao.company.service.maintenance.schedule;

import xueqiao.company.service.maintenance.MaintenanceTimeSpan;

import java.util.Calendar;
import java.util.Set;

public abstract class DaySchedule {

    public DaySchedule(Calendar calendar) {
        this.mCalendar = calendar;
    }

    protected Calendar mCalendar;

    protected static final int DELAY_MIN = 3;
    protected static final int SCHEDULE_START_HOUR = 17;
    protected static final int SUNDAY_SCHEDULE_END_HOUR = 11;

    public long getScheduleTimestamp(Set<MaintenanceTimeSpan> maintenanceTimeSpans) {
        handleMaintenanceTimeSpan(mCalendar, maintenanceTimeSpans);
        return mCalendar.getTime().getTime() / 1000;
    }

    protected abstract void handleMaintenanceTimeSpan(Calendar calendar, Set<MaintenanceTimeSpan> maintenanceTimeSpans);
}
