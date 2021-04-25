package xueqiao.company.service.maintenance.schedule;

import xueqiao.company.service.maintenance.MaintenanceTimeSpan;

import java.util.Calendar;
import java.util.Set;

public class FridaySchedule extends DaySchedule {
    public FridaySchedule(Calendar calendar) {
        super(calendar);
    }

    @Override
    protected void handleMaintenanceTimeSpan(Calendar calendar, Set<MaintenanceTimeSpan> maintenanceTimeSpans) {
        int hourOfDay = mCalendar.get(Calendar.HOUR_OF_DAY);
        if (hourOfDay < SCHEDULE_START_HOUR) {
            calendar.set(Calendar.HOUR_OF_DAY, SCHEDULE_START_HOUR);
            calendar.set(Calendar.MINUTE, DELAY_MIN);
        } else {
            calendar.add(Calendar.MINUTE, DELAY_MIN);
        }
    }
}
