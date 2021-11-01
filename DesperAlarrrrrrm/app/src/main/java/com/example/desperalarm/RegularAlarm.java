package com.example.desperalarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class RegularAlarm extends AppCompatActivity {
    TimePicker alarmTimePicker;
    PendingIntent pendingIntent;
    AlarmManager alarmManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regular_alarm);

        alarmTimePicker = (TimePicker) findViewById(R.id.timePicker);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        Button stop = findViewById(R.id.stopButton);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii = new Intent(RegularAlarm.this, AlarmService.class);
                stopService(ii);
            }
        });

    }
    public void OnToggleClicked(View view) {
        long time;
        if (((ToggleButton) view).isChecked()) {
            Toast.makeText(RegularAlarm.this, "ALARM ON", Toast.LENGTH_SHORT).show();
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getCurrentHour());
            calendar.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute());
            Intent intent = new Intent(this, AlarmReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

            time=(calendar.getTimeInMillis()-(calendar.getTimeInMillis()%60000));
            if(System.currentTimeMillis()>time) {
                if (calendar.AM_PM == 0)
                    time = time + (1000*60*60*12);
                else
                    time = time + (1000*60*60*24);
            }
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time, 10000, pendingIntent);

        } else {
            Intent intent= new Intent(this, AlarmReceiver.class);
            PendingIntent pendingIntent=PendingIntent.getBroadcast(this,0,intent,0);
            alarmManager.cancel(pendingIntent);
            Toast.makeText(RegularAlarm.this, "ALARM OFF", Toast.LENGTH_SHORT).show();
        }


    }


}
