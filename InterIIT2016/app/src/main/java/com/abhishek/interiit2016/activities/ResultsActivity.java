package com.abhishek.interiit2016.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.abhishek.interiit2016.R;
import com.abhishek.interiit2016.adapters.SimpleRecyclerAdapter;
import com.abhishek.interiit2016.model.APIResponse;
import com.abhishek.interiit2016.model.ScheduleDTO;
import com.abhishek.interiit2016.utils.APIConstants;
import com.abhishek.interiit2016.utils.DataService;
import com.abhishek.interiit2016.utils.GsonFactory;
import com.abhishek.interiit2016.utils.Utils;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ResultsActivity extends AppCompatActivity {
    private SimpleRecyclerAdapter simpleRecyclerAdapter;
    RecyclerView recyclerView;
    List<ScheduleDTO> resultsList;
    private SharedPreferences sharedPreferences;
    private String gender;
     Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
         recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
         sharedPreferences = getSharedPreferences(APIConstants.USER_SPORT_SELECTED, Context.MODE_PRIVATE);
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(ResultsActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        simpleRecyclerAdapter =new SimpleRecyclerAdapter(ResultsActivity.this,resultsList);
        recyclerView.setAdapter(simpleRecyclerAdapter);
        gender = sharedPreferences.getString("Gender","Men");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(sharedPreferences.getString("Sport","")+" - "+gender);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
        getsportresults(gender);

        FloatingActionButton floatingActionButton =(FloatingActionButton)findViewById(R.id.fab2);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(ResultsActivity.this)
                        .title("Select gender")
                        .items(R.array.gender)
                        .itemsCallbackSingleChoice(2, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("Gender", text.toString());
                                editor.commit();
                                setSupportActionBar(toolbar);
                                getSupportActionBar().setTitle(sharedPreferences.getString("Sport", "") + " - " + text.toString());
                                getsportresults(text.toString());
                                return true; // allow selection
                            }
                        })
                        .positiveText("Submit")
                        .show();
            }
        });



    }

    private void getsportresults(String gender) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://www.interiit.com/php")
                .build();
        DataService dataService =restAdapter.create(DataService.class);

        Callback callback = new Callback() {

            @Override
            public void success(Object o, Response response) {
                APIResponse schedule = (APIResponse)o;
                if (schedule.getResult().equals("failure")){
                    Utils.showCustomToast(ResultsActivity.this,"Results yet to be uploaded.. Come later", Toast.LENGTH_LONG);
                }
                else {
                    try {
                        JSONArray jsonArray = new JSONArray(schedule.getData());
                        Type type = new TypeToken<List<ScheduleDTO>>() {
                        }.getType();
                        resultsList = GsonFactory.getISOFormatInstance().fromJson(jsonArray.toString(), type);
                        simpleRecyclerAdapter = new SimpleRecyclerAdapter(ResultsActivity.this, resultsList);
                        recyclerView.setAdapter(simpleRecyclerAdapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                resultsList= new ArrayList<>();
            }
        };
        if (Utils.isInterNetUp(ResultsActivity.this)) {
            dataService.getresults(sharedPreferences.getString("Sport",""),gender,"all", callback);
        } else {
            Utils.showCustomToast(ResultsActivity.this, "Please check your internet connection or try again later", Toast.LENGTH_LONG);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
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
