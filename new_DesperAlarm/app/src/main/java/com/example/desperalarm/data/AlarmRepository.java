package com.example.desperalarm.data;

import android.app.Application;
import androidx.lifecycle.LiveData;
import java.util.List;

/**
 * repository for alarms
 */
public class AlarmRepository {
    private static AlarmDatabaseOps alarmDatabaseOps;
    private static LiveData<List<Alarm>> alarmsLiveData;

    public AlarmRepository(Application application) {
        // get database and the interface
        AlarmDatabase db = AlarmDatabase.getDatabase(application);
        alarmDatabaseOps = db.alarmDao();
        alarmsLiveData = alarmDatabaseOps.getAlarms();
    }

    /**
     * inserting an alarm
     * @param alarm to insert
     */
    public static void insert(Alarm alarm) {
        AlarmDatabase.databaseWriteExecutor.execute(() -> {
            alarmDatabaseOps.insert(alarm);
        });
    }

    /**
     * updating an alarm, including cancel it
     * @param alarm alarm to update
     */
    public void update(Alarm alarm) {
        AlarmDatabase.databaseWriteExecutor.execute(() -> {
            alarmDatabaseOps.update(alarm);
        });
    }

    /**
     * delete all alarms
     */
    public static void delete() {
        AlarmDatabase.databaseWriteExecutor.execute(() -> {
            alarmDatabaseOps.deleteAll();
        });
    }

    public static LiveData<List<Alarm>> getAlarmsLiveData() {
        return alarmsLiveData;
    }
}
