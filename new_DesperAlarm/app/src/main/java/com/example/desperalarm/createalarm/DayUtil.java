package com.example.desperalarm.createalarm;

import java.util.Calendar;

/**
 * The util class for days in calendar
 */
public final class DayUtil {
    /**
     * return String of the current day
     * @param day constant in calendar
     * @return transformed string
     * @throws Exception
     */
    public static final String toDay(int day) throws Exception {
        switch (day) {
            case Calendar.SUNDAY:
                return "Sunday";
            case Calendar.MONDAY:
                return "Monday";
            case Calendar.TUESDAY:
                return "Tuesday";
            case Calendar.WEDNESDAY:
                return "Wednesday";
            case Calendar.THURSDAY:
                return "Thursday";
            case Calendar.FRIDAY:
                return "Friday";
            case Calendar.SATURDAY:
                return "Saturday";
        }
        throw new Exception("Could not locate day");
    }
}
