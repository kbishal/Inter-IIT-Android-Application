package com.abhishek.interiit2016.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.abhishek.interiit2016.activities.MainActivity;
import com.abhishek.interiit2016.R;
import com.abhishek.interiit2016.adapters.SplashScreenAdapter;

import java.util.Timer;
import java.util.TimerTask;



public class SplashScreen extends AppCompatActivity {

    private ViewPager viewPager;
    private TextView concertTV;
    private ImageView firstCircleIV, secondCircleIV, thirdCircleIV;
    boolean isSwipeFromFirstElement=true;
    private boolean isSwipeImageManually=false;
   // private SwipeBackLayout mSwipeBackLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
//        mSwipeBackLayout = getSwipeBackLayout();
//        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);

        Button loginBtn = (Button) findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);

            }
        });
        viewPager=(ViewPager)findViewById(R.id.swipe_images_view_pager);
        RelativeLayout splashLayout = (RelativeLayout) findViewById(R.id.splashLayout);
        concertTV = (TextView) findViewById(R.id.concertTV);
        firstCircleIV = (ImageView) findViewById(R.id.FirstCircle);
        secondCircleIV = (ImageView) findViewById(R.id.SecondCircle);
        thirdCircleIV = (ImageView) findViewById(R.id.ThirdCircle);
        SplashScreenAdapter splashScreenAdapter=new SplashScreenAdapter(SplashScreen.this, new SplashScreenAdapter.SwipeUpdateListener() {
            @Override
            public void updateBubble(int position) {
                onSplashScreenSwipe(position);
            }
        });
        viewPager.setAdapter(splashScreenAdapter);
       Timer timer = new Timer();
       SplashScreenTimer splashscreentimer = new SplashScreenTimer();
        timer.schedule(splashscreentimer, 3000, 3000);
    }

    private void onSplashScreenSwipe(int imagePosition){
        firstCircleIV.setImageResource(R.drawable.checkbox_blank_circle_outline);
        secondCircleIV.setImageResource(R.drawable.checkbox_blank_circle_outline);
        thirdCircleIV.setImageResource(R.drawable.checkbox_blank_circle_outline);
        isSwipeImageManually=true;
        if (imagePosition == 0) {
            String txt="First page text";
            isSwipeFromFirstElement=true;
            concertTV.setText(txt);
            firstCircleIV.setImageResource(R.drawable.checkbox_blank_circle);
        } else if (imagePosition == 1) {
            String txt="Second Page Text";
            concertTV.setText(txt);
            secondCircleIV.setImageResource(R.drawable.checkbox_blank_circle);
        } else if (imagePosition == 2) {
            isSwipeFromFirstElement=false;
            String txt="Third Page text";
            concertTV.setText(txt);
            thirdCircleIV.setImageResource(R.drawable.checkbox_blank_circle);
        }
    }

    private class SplashScreenTimer extends TimerTask {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    int currentItem = viewPager.getCurrentItem();
                    if (!isSwipeImageManually) {
                        if (currentItem == 2 && isSwipeFromFirstElement) {
                            isSwipeFromFirstElement = false;

                        }
                        if (currentItem == 0 && !isSwipeFromFirstElement) {
                            isSwipeFromFirstElement = true;
                        }
                        if (isSwipeFromFirstElement)
                            viewPager.setCurrentItem(currentItem + 1);
                        else
                            viewPager.setCurrentItem(currentItem - 1);
                    }else{
                        isSwipeImageManually=false;
                    }
                }
            });
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

