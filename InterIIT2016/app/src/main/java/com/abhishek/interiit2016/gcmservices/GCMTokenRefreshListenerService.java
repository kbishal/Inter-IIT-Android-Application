package com.abhishek.interiit2016.gcmservices;

import android.content.Intent;

import com.google.android.gms.iid.InstanceIDListenerService;

/**
 * Created by GUNDA ABHISHEK on 02-06-2016.
 */
public class GCMTokenRefreshListenerService extends InstanceIDListenerService {
    @Override
    public void onTokenRefresh(){
        Intent intent =new Intent(this,GCMTokenRefreshListenerService.class);
        startService(intent);

    }
}
