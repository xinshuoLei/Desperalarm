package com.example.desperalarm.createalarm;

import android.os.Build;
import android.widget.TimePicker;

/**
 * Utility class for getting time from time picker
 * getCurrentHour and getCurrentMinute are deprecated
 * getMinute and getHour are only supported in api 23. so need version check
 * reference:
 * stackoverflow.com/questions/33415321/timepicker-getcurrentminute-getcurrenthour-method-is-deprecated-in-api-23
 */
public final class TimePickerUtil {
    /**
     * get selected hour from time picker
     * @param tp time picker
     * @return selected hour
     */
    public static int getTimePickerHour(TimePicker tp) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return tp.getHour();
        } else {
            return tp.getCurrentHour();
        }
    }

    /**
     * get selected minute from time picker
     * @param tp time picker
     * @return selected minute
     */
    public static int getTimePickerMinute(TimePicker tp) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return tp.getMinute();
        } else {
            return tp.getCurrentMinute();
        }
    }
}
