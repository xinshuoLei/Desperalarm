package com.example.desperalarm.createalarm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.desperalarm.data.Alarm;
import com.example.desperalarm.data.AlarmRepository;

/**
 * Model class for creating an alarm
 * inserti the alarm into the database
 */
public class CreateAlarmViewModel extends AndroidViewModel {
    private AlarmRepository alarmRepository;

    public CreateAlarmViewModel(@NonNull Application application) {
        super(application);
        alarmRepository = new AlarmRepository(application);
    }

    /**
     * function that insert alarm created
     * @param alarm alarm to insert
     */
    public void insert(Alarm alarm) {
        alarmRepository.insert(alarm);
    }
}
