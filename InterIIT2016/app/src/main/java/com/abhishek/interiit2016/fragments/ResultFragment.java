package com.abhishek.interiit2016.fragments;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
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
import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ViewTarget;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by GUNDA ABHISHEK on 24-06-2016.
 */
public class ResultFragment extends Fragment {
    Drawable color;
    SimpleRecyclerAdapter adapter;
    List<ScheduleDTO> scheduleDTOList;
    RecyclerView recyclerView;
    SharedPreferences sharedPreferences;
    String gender,day;
    public ResultFragment() {
    }

    @SuppressLint("ValidFragment")
    public ResultFragment(Drawable color) {
        this.color = color;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.dummy_fragment, container, false);

        final FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.dummyfrag_bg);
        frameLayout.setBackground(color);
        Calendar calendar =Calendar.getInstance();
        SimpleDateFormat sdf= new SimpleDateFormat("MMM d", Locale.getDefault());
        day = sdf.format(calendar.getTime());
        sharedPreferences = this.getActivity().getSharedPreferences(APIConstants.USER_SPORT_SELECTED, Context.MODE_PRIVATE);
        recyclerView = (RecyclerView) view.findViewById(R.id.dummyfrag_scrollableview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new SimpleRecyclerAdapter(scheduleDTOList);
        recyclerView.setAdapter(adapter);
        gender = sharedPreferences.getString("Gender","Male");
        getschedule(gender);
        FloatingActionButton floatingActionButton =(FloatingActionButton)view.findViewById(R.id.fab5);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(view.getContext())
                        .title("Select gender")
                        .items(R.array.gender)
                        .itemsCallbackSingleChoice(2, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("Gender",text.toString());
                                editor.commit();
                                ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(sharedPreferences.getString("Sport","")+" - "+text.toString());
                                getschedule(text.toString());
                                return true; // allow selection
                            }
                        })
                        .positiveText("Submit")
                        .show();
            }
        });
        return view;
    }

    private void getschedule(String gender) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://www.interiit.com/php")
                .build();
        DataService dataService =restAdapter.create(DataService.class);

        Callback callback = new Callback() {

            @Override
            public void success(Object o, Response response) {
                APIResponse schedule = (APIResponse)o;
                if (schedule.getResult().equals("failure")){
                    Utils.showCustomToast(getActivity(),"Looks like there are no matches today.. Come later", Toast.LENGTH_LONG);
                }else {
                    try {
                        JSONArray jsonArray =new JSONArray(schedule.getData());
                        Type type = new TypeToken<List<ScheduleDTO>>(){}.getType();
                        scheduleDTOList = GsonFactory.getISOFormatInstance().fromJson(jsonArray.toString(), type);
                        adapter = new SimpleRecyclerAdapter(scheduleDTOList);
                        recyclerView.setAdapter(adapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                scheduleDTOList= new ArrayList<>();
            }
        };
        if (Utils.isInterNetUp(getContext())){
            dataService.geteventschedule(sharedPreferences.getString("Sport",""),gender,day, callback);
        }
        else {
            Utils.showCustomToast(getActivity(),"Please check your internet connection or try again later",Toast.LENGTH_LONG);
        }
    }

}
