package xueqiao.company.service.maintenance.schedule;

import xueqiao.company.service.maintenance.MaintenanceTimeSpan;

import java.util.Calendar;
import java.util.Set;

public class SundaySchedule extends DaySchedule {
    public SundaySchedule(Calendar calendar) {
        super(calendar);
    }

    @Override
    protected void handleMaintenanceTimeSpan(Calendar calendar, Set<MaintenanceTimeSpan> maintenanceTimeSpans) {
        int hourOfDay = mCalendar.get(Calendar.HOUR_OF_DAY);
        if (hourOfDay > SUNDAY_SCHEDULE_END_HOUR) {
            int addDays;
            if (maintenanceTimeSpans.contains(MaintenanceTimeSpan.WORKING_DAY)) {
                if (hourOfDay < SCHEDULE_START_HOUR) {
                    addDays = 0;
                } else {
                    addDays = 1;
                }
            } else {
                addDays = 5;
            }
            calendar.add(Calendar.DATE, addDays);
            calendar.set(Calendar.MINUTE, DELAY_MIN);
            calendar.set(Calendar.HOUR_OF_DAY, SCHEDULE_START_HOUR);
        } else {
            calendar.add(Calendar.MINUTE, DELAY_MIN);
        }
    }
}
