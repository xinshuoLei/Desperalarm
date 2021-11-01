package com.example.desperalarm;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Vibrator;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

public class AlarmReceiver extends BroadcastReceiver {

    public Ringtone ringtone;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Time to wake up", Toast.LENGTH_LONG).show();
        Intent alarmIntent = new Intent(context, AlarmService.class);
        context.startService(alarmIntent);
    }


}