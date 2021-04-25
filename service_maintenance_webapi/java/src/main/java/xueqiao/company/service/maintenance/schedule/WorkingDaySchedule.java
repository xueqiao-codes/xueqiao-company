package xueqiao.company.service.maintenance.schedule;

import xueqiao.company.service.maintenance.MaintenanceTimeSpan;

import java.util.Calendar;
import java.util.Set;

public class WorkingDaySchedule extends DaySchedule {
    public WorkingDaySchedule(Calendar calendar) {
        super(calendar);
    }

    @Override
    protected void handleMaintenanceTimeSpan(Calendar calendar, Set<MaintenanceTimeSpan> maintenanceTimeSpans) {

        int hourOfDay = mCalendar.get(Calendar.HOUR_OF_DAY);
        int addDays = 0;
        if (maintenanceTimeSpans.contains(MaintenanceTimeSpan.WORKING_DAY)) {
            if (hourOfDay >= SCHEDULE_START_HOUR) {
                addDays = 1;
            }
        } else {
            int dayOfWeek = mCalendar.get(Calendar.DAY_OF_WEEK);
            addDays = 6 - dayOfWeek;
        }

        calendar.add(Calendar.DATE, addDays);
        calendar.set(Calendar.HOUR_OF_DAY, SCHEDULE_START_HOUR);
        calendar.set(Calendar.MINUTE, DELAY_MIN);
    }
}
