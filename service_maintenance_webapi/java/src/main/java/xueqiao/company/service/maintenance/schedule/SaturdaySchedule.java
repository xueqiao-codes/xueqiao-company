package xueqiao.company.service.maintenance.schedule;

import xueqiao.company.service.maintenance.MaintenanceTimeSpan;

import java.util.Calendar;
import java.util.Set;

public class SaturdaySchedule extends DaySchedule {
    public SaturdaySchedule(Calendar calendar) {
        super(calendar);
    }

    @Override
    protected void handleMaintenanceTimeSpan(Calendar calendar, Set<MaintenanceTimeSpan> maintenanceTimeSpans) {
        calendar.add(Calendar.MINUTE, DELAY_MIN);
    }
}
