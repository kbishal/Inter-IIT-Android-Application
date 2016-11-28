package com.abhishek.interiit2016.activities;

import android.app.Application;

import io.smooch.core.Smooch;

/**
 * Created by GUNDA ABHISHEK on 05-08-2016.
 */
public class interIIT extends Application {
    @Override
    public void onCreate(){
        super.onCreate();
        Smooch.init(this,"axvmje0pdkvikbjcnx11gov4y");
    }
}
