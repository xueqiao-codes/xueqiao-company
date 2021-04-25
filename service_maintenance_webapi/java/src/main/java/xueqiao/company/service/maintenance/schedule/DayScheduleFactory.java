package xueqiao.company.service.maintenance.schedule;

import java.util.Calendar;

public class DayScheduleFactory {
    private static DayScheduleFactory ourInstance = new DayScheduleFactory();

    public static DayScheduleFactory getInstance() {
        return ourInstance;
    }

    private DayScheduleFactory() {
    }

    public DaySchedule getDaySchedule(Calendar calendar) {
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        DaySchedule daySchedule = null;
        switch (dayOfWeek) {
            case 1:
                // 星期日
                daySchedule = new SundaySchedule(calendar);
                break;
            case 2:
                // 星期一
            case 3:
                // 星期二
            case 4:
                // 星期三
            case 5:
                // 星期四
                daySchedule = new WorkingDaySchedule(calendar);
                break;
            case 6:
                // 星期五
                daySchedule = new FridaySchedule(calendar);
                break;
            case 7:
                // 星期六
                daySchedule = new SaturdaySchedule(calendar);
            default:

        }
        return daySchedule;
    }
}
