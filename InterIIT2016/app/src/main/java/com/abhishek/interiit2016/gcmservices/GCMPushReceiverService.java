package com.abhishek.interiit2016.gcmservices;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.RemoteViews;

import com.abhishek.interiit2016.activities.HomeActivity;
import com.abhishek.interiit2016.R;
import com.google.android.gms.gcm.GcmListenerService;

import java.io.IOException;
import java.util.List;

/**
 * Created by GUNDA ABHISHEK on 02-06-2016.
 */
public class GCMPushReceiverService extends GcmListenerService {
    private int requestCode=0;

    @Override
    public void onMessageReceived(String from,Bundle data){
        String message = data.getString("message");
            sendNotification(message);
    }


    private void sendNotification(String  message) {

        Intent intent = new Intent(this,HomeActivity.class);
        PendingIntent pendingIntent =PendingIntent.getActivity(this,requestCode,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        Uri sound= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(),
                R.drawable.eight);

        Notification.Builder builder = new Notification.Builder(this);
        builder.setSmallIcon(R.drawable.eight);
        builder.setTicker("Registrations from townscript");
        builder.setContentTitle("1 New Registration");
        builder.setContentText(message);
        builder.setContentIntent(pendingIntent);
        builder.setLargeIcon(largeIcon);
        builder.setAutoCancel(true);
        builder.setSound(sound);

        ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).notify(requestCode, builder.build());


//
//        remoteViews.setImageViewResource(R.id.imagenotileft,R.drawable.tlogo72);
//        remoteViews.setImageViewResource(R.id.imagenotiright,R.drawable.tlogo72);
//
//        // Locate and set the Text into customnotificationtext.xml TextViews
//        remoteViews.setTextViewText(R.id.title, getString(R.string.customnotificationtitle));
//        remoteViews.setTextViewText(R.id.text, message);
    }
}
