package com.example.desperalarm;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.os.Vibrator;


/**
 * The service class for
 */
public class AlarmService extends Service {

    private Ringtone ringtone;
    private Vibrator vibrator;

    @Override
    public void onCreate() {
        super.onCreate();
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri == null) {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        ringtone = RingtoneManager.getRingtone(this, alarmUri);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        vibrator.vibrate(4000);
        ringtone.play();
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        ringtone.stop();
        vibrator.cancel();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}


