package com.example.desperalarm.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.os.Vibrator;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.desperalarm.R;
import com.example.desperalarm.activities.RingActivity;

import static com.example.desperalarm.application.App.CHANNEL_ID;
import static com.example.desperalarm.broadcastreceiver.AlarmBroadcastReceiver.DESPERATE;
import static com.example.desperalarm.broadcastreceiver.AlarmBroadcastReceiver.TITLE;


/**
 * The Service class for activating the alarm
 */
public class AlarmService extends Service {
    /**
     * vibrator used for the alarm
     */
    private Vibrator vibrator;
    /**
     * Ringtone used for the alarm
     */
    private Ringtone ringtone;

    @Override
    public void onCreate() {
        super.onCreate();
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        // get the default ringtone
        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri == null) {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        ringtone = RingtoneManager.getRingtone(this, alarmUri);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // create an intent for the ringtone activity, which is shown when user click notification
        Intent notificationIntent = new Intent(this, RingActivity.class);

        notificationIntent.putExtra(DESPERATE, intent.getBooleanExtra(DESPERATE, false));
        notificationIntent.putExtra(TITLE, intent.getStringExtra(TITLE));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // set up notification
        String alarmTitle = String.format("%s Alarm", intent.getStringExtra(TITLE));
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle(alarmTitle)
                .setContentText("Time to wake up")
                .setSmallIcon(R.drawable.ic_alarm_black_24dp)
                .setContentIntent(pendingIntent)
                .build();

        // activate ringtone and vibrator
        ringtone.play();
        long[] pattern = { 0, 100, 1000 };
        vibrator.vibrate(pattern, 0);

        startForeground(1, notification);

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ringtone.stop();
        vibrator.cancel();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
