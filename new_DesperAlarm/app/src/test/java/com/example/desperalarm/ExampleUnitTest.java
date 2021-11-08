package com.example.desperalarm;

import org.junit.Test;

import static org.junit.Assert.*;

import androidx.lifecycle.LiveData;

import com.example.desperalarm.activities.Question;
import com.example.desperalarm.data.Alarm;
import com.example.desperalarm.data.AlarmRepository;

import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    /**
     * first check if the question & answer we present to users is correct!
     */
    public void check_Answer() {
        int[] input = new int[]{22, 35};
        assertTrue(Question.checkAnswer(input, 1, 57));
    }
    @Test
    public void check_DB_insert() {
        int alarmId = 123;
        int hour = 4;
        int minute = 30;
        String title = "what for?";
        long created = 133782;
        AlarmRepository.insert(new Alarm(alarmId, hour, minute, title, created, false,
        false, true, false, false,
        false, false, false, false, true)); //try insert one random alarm in the DB
        LiveData<List<Alarm>> data = AlarmRepository.getAlarmsLiveData();
        System.out.println(data);
    }
    @Test
    public void check_DB_delete() {
        int alarmId = 123;
        int hour = 4;
        int minute = 30;
        String title = "what for?";
        long created = 133782;
        AlarmRepository.insert(new Alarm(alarmId, hour, minute, title, created, false,
                false, true, false, false,
                false, false, false, false, true)); //try insert one random alarm in the DB
        AlarmRepository.delete();
        LiveData<List<Alarm>> data = AlarmRepository.getAlarmsLiveData();
        System.out.println(data);
    }
}