package com.example.jorge.helloworld;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.TaskStackBuilder;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.StrictMode;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class NotificationActivity extends Activity {

    private static final int NOTE_ID = 3;
    public NotificationCompat.Builder note;
    public static final String ANDROID_CHANNEL_ID = "50";
    public static final String CHANNEL_NAME = "Channel name";
    NotificationManager manager;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_notification);


        NotificationChannel androidChannel = new NotificationChannel(ANDROID_CHANNEL_ID,
                CHANNEL_NAME, NotificationManager.IMPORTANCE_NONE);
        androidChannel.setLightColor(Color.GREEN);
        androidChannel.setDescription("description name");
        androidChannel.enableLights(true);
        androidChannel.setShowBadge(false);

        manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        manager.createNotificationChannel(androidChannel);

        note = new NotificationCompat.Builder(getBaseContext(),ANDROID_CHANNEL_ID);
        note.setSmallIcon(R.mipmap.ic_launcher);
        note.setContentTitle("My notification");
        note.setContentText("Hello World!");
        note.setOngoing(true);
        note.setTicker("mensage");


        final Button notiButton = (Button)    findViewById(R.id.notify);

        AdapterView.OnClickListener ocl5;
        ocl5 = new AdapterView.OnClickListener() {

            @Override
            public void onClick(View v) {
                handler.postDelayed(task, 10000);
                Toast.makeText(NotificationActivity.this, "Notification will post in 10 seconds", Toast.LENGTH_SHORT).show();

            }

        };

        notiButton.setOnClickListener(ocl5);

    }



    private Handler handler = new Handler();
    private Runnable task = new Runnable() {
        @Override
        public void run() {

            Intent launchIntent = new Intent(getBaseContext(), ResultNotificationActivity.class);
            launchIntent.setFlags(  Intent.FLAG_ACTIVITY_NEW_TASK  | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            PendingIntent contentIntent = PendingIntent.getActivity(getBaseContext(), 0, launchIntent,  PendingIntent.FLAG_UPDATE_CURRENT);

            note.setContentIntent(contentIntent); //start the content intent activity when the user clicks the notification text in the notification drawer.

            manager.notify(NOTE_ID, note.build());

        }
    };
}