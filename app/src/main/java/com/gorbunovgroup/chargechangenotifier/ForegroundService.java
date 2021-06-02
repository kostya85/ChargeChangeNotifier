package com.gorbunovgroup.chargechangenotifier;

import android.app.Service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.os.Vibrator;

import androidx.core.app.NotificationCompat;
import androidx.annotation.Nullable;

public class ForegroundService extends Service {
    public static JThread thread;
    public static final String CHANNEL_ID = "ForegroundServiceChannel";
    @Override
    public void onCreate() {
        super.onCreate();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        MainActivity.status = MainActivity.IsCharge();
        String input = intent.getStringExtra("inputExtra");
        createNotificationChannel();
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, 0);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Charge Change Notifier")
                .setContentText("The Charge Change Notifier app is working!")
                .setSmallIcon(R.drawable.app_working_notif)
                .setContentIntent(pendingIntent)
                .build();
        startForeground(1, notification);

        long[] pattern = { 0, 300, 400, 200 };
        Vibrator vibrator = (Vibrator) MainActivity.mContext.getSystemService(Context.VIBRATOR_SERVICE);

        if (vibrator.hasVibrator()) {
            vibrator.vibrate(pattern,-1);
        }
        //do heavy work on a background thread


            thread = new JThread("Kostya");

            thread.start();

        //stopSelf();
        return START_NOT_STICKY;
    }


    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Foreground Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("Foreground destroyed!");
        if(thread.isAlive())thread.interrupt();
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }





}