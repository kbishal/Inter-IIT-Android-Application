package com.abhishek.interiit2016.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.abhishek.interiit2016.R;
import com.abhishek.interiit2016.adapters.StandingsAdapter;
import com.abhishek.interiit2016.model.APIResponse;
import com.abhishek.interiit2016.model.StandingsDTO;
import com.abhishek.interiit2016.utils.APIConstants;
import com.abhishek.interiit2016.utils.DataService;
import com.abhishek.interiit2016.utils.GsonFactory;
import com.abhishek.interiit2016.utils.Utils;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ViewTarget;
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

public class Points extends AppCompatActivity {
    private StandingsAdapter adapter;
    private RecyclerView recyclerView;
    private List<StandingsDTO> standingsDTOList;
    private SharedPreferences sharedPreferences;
    private String gender;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_points);
        recyclerView = (RecyclerView)findViewById(R.id.dummy_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Points.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new StandingsAdapter(this,standingsDTOList);
        recyclerView.setAdapter(adapter);
        sharedPreferences = getSharedPreferences(APIConstants.USER_SPORT_SELECTED, Context.MODE_PRIVATE);
        gender = sharedPreferences.getString("Gender","Male");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setTitle(sharedPreferences.getString("Sport", "") + " - " + gender);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
        getpoints(gender);
        FloatingActionButton floatingActionButton =(FloatingActionButton)findViewById(R.id.fab2);
        new ShowcaseView.Builder(this)
                .withNewStyleShowcase()
                .setTarget(new ViewTarget(R.id.fab2, this))
                .setContentTitle("title")
                .setContentText("title")
                .hideOnTouchOutside()
                .singleShot(42)
                .build();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(Points.this)
                        .title("Select gender")
                        .items(R.array.gender)
                        .itemsCallbackSingleChoice(2, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("Gender", text.toString());
                                editor.commit();
                                setSupportActionBar(toolbar);
                                //getSupportActionBar().setTitle(sharedPreferences.getString("Sport", "") + " - " + text.toString());

                                getpoints(text.toString());
                                return true; // allow selection
                            }
                        })
                        .positiveText("Submit")
                        .show();
            }
        });
    }

    private void getpoints(String gender) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://www.interiit.com/php")
                .build();
        DataService dataService =restAdapter.create(DataService.class);

        Callback callback = new Callback() {

            @Override
            public void success(Object o, Response response) {
                APIResponse ticketids = (APIResponse)o;
                try {
                    JSONArray jsonArray =new JSONArray(ticketids.getData());
                    Type type = new TypeToken<List<StandingsDTO>>(){}.getType();
                    standingsDTOList = GsonFactory.getISOFormatInstance().fromJson(jsonArray.toString(), type);
                    adapter = new StandingsAdapter(Points.this,standingsDTOList);
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                standingsDTOList= new ArrayList<>();
            }
        };
        if (Utils.isInterNetUp(Points.this)){
            dataService.getEvents(gender, callback);
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
