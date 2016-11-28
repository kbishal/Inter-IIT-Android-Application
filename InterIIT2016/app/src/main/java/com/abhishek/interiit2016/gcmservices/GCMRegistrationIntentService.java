package com.abhishek.interiit2016.gcmservices;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.abhishek.interiit2016.R;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by GUNDA ABHISHEK on 02-06-2016.
 */
public class GCMRegistrationIntentService extends IntentService {
    public static final String REGISTRATION_SUCCESS="RegistrationSuccess";
    public static final String REGISTRATION_ERROR="RegistrationError";
    public GCMRegistrationIntentService() {
        super("");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        registerGCM();
    }

    private void registerGCM() {
        Intent registrationComplete=null;
        String token =null;

        try {
            InstanceID instanceID = InstanceID.getInstance(getApplicationContext());
            token=instanceID.getToken(getString(R.string.gcm_defaultSenderId), GoogleCloudMessaging.INSTANCE_ID_SCOPE,null);
            Log.v("GCMRegistrationIntentService", "token:" + token);
            registrationComplete =new Intent(REGISTRATION_SUCCESS);
            registrationComplete.putExtra("token", token);
        }
        catch (IOException e){
            Log.v("GCMRegistrationIntentService", e.getMessage(), e.fillInStackTrace());
            registrationComplete=new Intent(REGISTRATION_ERROR);
        }
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
    }


}
