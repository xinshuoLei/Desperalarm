package com.example.desperalarm.data;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.desperalarm.broadcastreceiver.AlarmBroadcastReceiver;
import com.example.desperalarm.createalarm.DayUtil;

import java.util.Calendar;

import static com.example.desperalarm.broadcastreceiver.AlarmBroadcastReceiver.FRIDAY;
import static com.example.desperalarm.broadcastreceiver.AlarmBroadcastReceiver.MONDAY;
import static com.example.desperalarm.broadcastreceiver.AlarmBroadcastReceiver.RECURRING;
import static com.example.desperalarm.broadcastreceiver.AlarmBroadcastReceiver.SATURDAY;
import static com.example.desperalarm.broadcastreceiver.AlarmBroadcastReceiver.SUNDAY;
import static com.example.desperalarm.broadcastreceiver.AlarmBroadcastReceiver.THURSDAY;
import static com.example.desperalarm.broadcastreceiver.AlarmBroadcastReceiver.TITLE;
import static com.example.desperalarm.broadcastreceiver.AlarmBroadcastReceiver.TUESDAY;
import static com.example.desperalarm.broadcastreceiver.AlarmBroadcastReceiver.WEDNESDAY;

/**
 * Entity/Class for information about a single alarm
 */
@Entity(tableName = "alarm_table")
public class Alarm {
    @PrimaryKey
    @NonNull
    /**
     * alarm id, also primary key for alarm in database
     */
    private int alarmId;
    /**
     * time of alarm
     */
    private int hour, minute;
    /**
     * information about alarm
     */
    private boolean started, recurring;
    /**
     * booleans for recurring days. had to use seven booleans since database cannot store array
     */
    private boolean monday, tuesday, wednesday, thursday, friday, saturday, sunday;
    private String title;

    private long created;

    public Alarm(int alarmId, int hour, int minute, String title, long created, boolean started,
                 boolean recurring, boolean monday, boolean tuesday, boolean wednesday,
                 boolean thursday, boolean friday, boolean saturday, boolean sunday) {
        this.alarmId = alarmId;
        this.hour = hour;
        this.minute = minute;
        this.started = started;
        this.recurring = recurring;
        this.title = title;
        this.created = created;

        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
        this.sunday = sunday;
    }

    /**
     * function for scheduling an alarm
     * @param context
     */
    public void schedule(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        // put information about alarm to the intent for AlarmBroadcastReceiver
        Intent intent = new Intent(context, AlarmBroadcastReceiver.class);
        intent.putExtra(RECURRING, recurring);
        intent.putExtra(MONDAY, monday);
        intent.putExtra(TUESDAY, tuesday);
        intent.putExtra(WEDNESDAY, wednesday);
        intent.putExtra(THURSDAY, thursday);
        intent.putExtra(FRIDAY, friday);
        intent.putExtra(SATURDAY, saturday);
        intent.putExtra(SUNDAY, sunday);
        intent.putExtra(TITLE, title);
        PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(context, alarmId, intent, 0);

        // get current time from calendar
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        // check if alarm time has already passed
        if (calendar.getTimeInMillis() <= System.currentTimeMillis()) {
            // increment day by 1 if it is true
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1);
        }

        setAlarmManager(context, alarmManager, alarmPendingIntent, calendar);

        this.started = true;
    }

    private void setAlarmManager(Context context, AlarmManager alarmManager, PendingIntent alarmPendingIntent,
                                 Calendar calendar) {
        if (!recurring) {
            // if alarm is not recurring, schedule using set exact
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                alarmManager.setExact(
                        AlarmManager.RTC_WAKEUP,
                        calendar.getTimeInMillis(),
                        alarmPendingIntent
                );
            }
            // display a message indicating alarm is set successfully
            String msg = "";
            try {
                msg = String.format("One Time Alarm %s scheduled for %s at %02d:%02d", title,
                        DayUtil.toDay(calendar.get(Calendar.DAY_OF_WEEK)), hour, minute);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
        } else {
            // if alarm is recurring, schedule using set repeat
            final long RUN_DAILY = 24 * 60 * 60 * 1000;
            alarmManager.setRepeating(
                    AlarmManager.RTC_WAKEUP,
                    calendar.getTimeInMillis(),
                    RUN_DAILY,
                    alarmPendingIntent
            );
            String msg = String.format("Recurring Alarm %s scheduled for %s at %02d:%02d",
                    title, getRecurringDaysText(), hour, minute);
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * function for canceling an alarm
     * @param context
     */
    public void cancelAlarm(Context context) {
        this.started = false;
        // cancel all pending intent for alarm manager
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmBroadcastReceiver.class);
        PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(context, alarmId, intent, 0);
        alarmManager.cancel(alarmPendingIntent);
        // display a message indicating successfully canceled
        String msg = String.format("Alarm cancelled for %02d:%02d", hour, minute);
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        Log.i("cancel", msg);
    }

    /**
     * format the text to display recurring days info
     * @return formatted text
     */
    public String getRecurringDaysText() {
        if (!recurring) {
            return null;
        }
        String[] weekDays = {"Mo ", "Tu ", "We ", "Th ", "Fr ", "Sa ", "Su "};
        boolean[] recurringDays = {monday, tuesday, wednesday, thursday, friday, saturday, sunday};
        String days = "";
        for (int i = 0; i < weekDays.length; i++) {
            if (recurringDays[i]) {
                days += weekDays[i];
            }
        }

        return days;
    }

    /**
     * below are getters and setters
     */

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public boolean isStarted() {
        return started;
    }

    public int getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(int alarmId) {
        this.alarmId = alarmId;
    }

    public boolean isRecurring() {
        return recurring;
    }

    public boolean isMonday() {
        return monday;
    }

    public boolean isTuesday() {
        return tuesday;
    }

    public boolean isWednesday() {
        return wednesday;
    }

    public boolean isThursday() {
        return thursday;
    }

    public boolean isFriday() {
        return friday;
    }

    public boolean isSaturday() {
        return saturday;
    }

    public boolean isSunday() {
        return sunday;
    }

    public String getTitle() {
        return title;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }
}
