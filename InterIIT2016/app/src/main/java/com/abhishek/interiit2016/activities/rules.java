package com.abhishek.interiit2016.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.abhishek.interiit2016.R;
import com.abhishek.interiit2016.utils.APIConstants;


public class rules extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private String sport;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);
        sharedPreferences = getSharedPreferences(APIConstants.USER_SPORT_SELECTED, Context.MODE_PRIVATE);
        sport = sharedPreferences.getString("Sport", "");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(sport+" Rules");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
       // TextView textView2 = (TextView) findViewById(R.id.heading);
        TextView textView = (TextView) findViewById(R.id.rules);

        //textView2.setText(sport+" Rules");
        switch (sport) {
            case "Badminton":
                textView.setText(APIConstants.Badminton);
                break;
            case "Basketball":
                textView.setText(APIConstants.Basketball);
                break;
            case "Cricket":
                textView.setText(APIConstants.Cricket);
                break;
            case "Football":
                textView.setText(APIConstants.Football);
                break;
            case "Hockey":
                textView.setText(APIConstants.Hockey);
                break;
            case "Squash":
                textView.setText(APIConstants.Squash);
                break;
            case "Table Tennis":
                textView.setText(APIConstants.TableTennis);
                break;
            case "Tennis":
                textView.setText(APIConstants.Tennis);
                break;
            case "Volleyball":
                textView.setText(APIConstants.Volleyball);

                break;
            case "Weightlifting":
                textView.setText(APIConstants.Weightlifting);
                break;
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_points, menu);
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
