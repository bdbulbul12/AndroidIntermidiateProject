package com.example.bulbul.lictchatapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by bulbul on 2/13/2018.
 */

public class FirebaseMessgingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        String notificationTitle = remoteMessage.getNotification().getTitle();
        String notificationMessage = remoteMessage.getNotification().getBody();
        String click_action = remoteMessage.getNotification().getClickAction();

        String from_user_id = remoteMessage.getData().get("from_user_id");

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(notificationTitle)
                .setContentText(notificationMessage);

        Intent resultIntent = new Intent(click_action);
        resultIntent.putExtra("user_id",from_user_id);

        PendingIntent resultPendingIntent = PendingIntent.getActivity(
                this,
                0,
                resultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        mBuilder.setContentIntent(resultPendingIntent);


        int mNotificationId  = (int) System.currentTimeMillis();
        NotificationManager mNotifyMgr  =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mNotifyMgr.notify(mNotificationId,mBuilder.build());



    }
}
