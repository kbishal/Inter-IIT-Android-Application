package com.abhishek.interiit2016.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.abhishek.interiit2016.R;
import com.abhishek.interiit2016.adapters.GridListAdapter;
import com.abhishek.interiit2016.model.ItemObject;
import com.abhishek.interiit2016.utils.APIConstants;
import com.abhishek.interiit2016.utils.Utils;
import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;
import java.util.List;

import io.smooch.core.User;
import io.smooch.ui.ConversationActivity;

//import com.google.android.gms.common.;

//import io.smooch.core.User;
//import io.smooch.ui.ConversationActivity;

public class MainActivity extends NavDrawerActivity {
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_main, mContentFrame);
        final SharedPreferences sharedPreferences = getSharedPreferences(APIConstants.USER_SPORT_SELECTED, Context.MODE_PRIVATE);
        String Sport = sharedPreferences.getString("Sport","");
        String Gender = sharedPreferences.getString("Gender","Male");
         username = sharedPreferences.getString("username","");
//        int resultCode= GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());
//
//        if (ConnectionResult.SUCCESS!=resultCode){
//            //checking the type of error
//            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)){
//                GooglePlayServicesUtil.showErrorNotification(resultCode, getApplicationContext());
//            }
//            else
//            {
//                // Empty state - Can't show notifications, because Can't access Google Play Services
//            }
//        }
//        else {

        // ---------------------------GCM Registration comment have to check it ------------------------------------------------------
//            Intent intent =new Intent(this,GCMRegistrationIntentService.class);
//            startService(intent);

        FloatingActionButton floatingActionButton =(FloatingActionButton)findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.equals("")){
                    new MaterialDialog.Builder(MainActivity.this)
                            .title("Please provide your Username")
                            .content("Maximum of 8 characters is allowed")
                            .inputType(InputType.TYPE_CLASS_TEXT)
                            .input("username", "", new MaterialDialog.InputCallback() {
                                @Override
                                public void onInput(MaterialDialog dialog, CharSequence input) {
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("username",input.toString());
                                    editor.commit();
                                    Utils.showCustomToast(MainActivity.this,"Submitted Successfully", Toast.LENGTH_SHORT);
                                    User.getCurrentUser().setFirstName(input.toString());
                                    User.getCurrentUser().setLastName("");
                                    //User.getCurrentUser().setEmail("abhigun@iitk.ac.in");
                                    ConversationActivity.show(MainActivity.this);
                                    // Do something
                                }
                            }).show();
                }
                else {
                    User.getCurrentUser().setFirstName(username);
                    User.getCurrentUser().setLastName("");
                    //User.getCurrentUser().setEmail("abhigun@iitk.ac.in");
                    ConversationActivity.show(MainActivity.this);
                }


            }
        });

//        PandaLoadingView mview= new PandaLoadingView();
//        mview.show(getSupportFragmentManager(), "");

        final List<ItemObject> rowListItem = getAllItemList();
        GridLayoutManager gridLayout = new GridLayoutManager(MainActivity.this, 3);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(gridLayout);
        GridListAdapter adapter = new GridListAdapter(MainActivity.this,rowListItem);
        recyclerView.setAdapter(adapter);
        adapter.SetOnItemClickListener(new GridListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent =new Intent(MainActivity.this,HomeActivity.class);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Sport",rowListItem.get(position).getName());
                editor.commit();
                //intent.putExtra("eventName",rowListItem.get(position).getName());
                startActivity(intent);

            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    private List<ItemObject> getAllItemList(){

        List<ItemObject> allItems = new ArrayList<>();

        allItems.add(new ItemObject("Badminton", R.drawable.baddy1));
        allItems.add(new ItemObject("Basketball", R.drawable.basketball1));
        allItems.add(new ItemObject("Cricket", R.drawable.cricket1));
        allItems.add(new ItemObject("Football", R.drawable.football1));
        allItems.add(new ItemObject("Hockey", R.drawable.hockey));
        allItems.add(new ItemObject("Squash", R.drawable.squash1));
        allItems.add(new ItemObject("Table Tennis", R.drawable.tt1));
        allItems.add(new ItemObject("Tennis", R.drawable.tennis1));
        allItems.add(new ItemObject("Volleyball", R.drawable.volley1));
        allItems.add(new ItemObject("Weightlifting", R.drawable.weight));
       /* allItems.add(new ItemObject("Germany", R.drawable.four));
        allItems.add(new ItemObject("Sweden", R.drawable.five));
        allItems.add(new ItemObject("United Kingdom", R.drawable.four));
        allItems.add(new ItemObject("Germany", R.drawable.five));
        allItems.add(new ItemObject("Sweden", R.drawable.eight));
*/
        return allItems;
    }
}
