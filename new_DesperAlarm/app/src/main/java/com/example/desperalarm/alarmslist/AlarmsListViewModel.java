package com.example.desperalarm.alarmslist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.desperalarm.data.Alarm;
import com.example.desperalarm.data.AlarmRepository;

import java.util.List;

/**
 * the model for AlarmListFragment
 */
public class AlarmsListViewModel extends AndroidViewModel {
    private AlarmRepository alarmRepository;
    private LiveData<List<Alarm>> alarmsLiveData;

    public AlarmsListViewModel(@NonNull Application application) {
        super(application);
        alarmRepository = new AlarmRepository(application);
        alarmsLiveData = alarmRepository.getAlarmsLiveData();
    }

    /**
     * update the alarm status
     * @param alarm alarm to update
     */
    public void update(Alarm alarm) {
        alarmRepository.update(alarm);
    }

    /**
     * get live data
     * @return alarm live data
     */
    public LiveData<List<Alarm>> getAlarmsLiveData() {
        return alarmsLiveData;
    }

    /**
     * clear the whole repo. used for debugging and developing
     */
    public void clearAlarmRepository() {
        alarmRepository.delete();
    }
}
