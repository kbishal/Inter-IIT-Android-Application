package com.abhishek.interiit2016.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.abhishek.interiit2016.R;
import com.abhishek.interiit2016.adapters.GridListAdapter;
import com.abhishek.interiit2016.adapters.StandingsAdapter;
import com.abhishek.interiit2016.model.ItemObject;

import java.util.ArrayList;
import java.util.List;

public class Teams extends AppCompatActivity {
    StandingsAdapter adapter;
    List<ItemObject> itemObjects =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Teams.this);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        for (int i =0;i<10 ; i++){
            itemObjects.add(new ItemObject("IIT KANPUR",R.drawable.iitk));
        }
        adapter = new StandingsAdapter(itemObjects,1);
        recyclerView.setAdapter(adapter);
        adapter.SetOnItemClickListener(new StandingsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_teams, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatemen

        return super.onOptionsItemSelected(item);
    }
}
