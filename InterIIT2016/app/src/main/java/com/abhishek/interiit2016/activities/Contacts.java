package com.abhishek.interiit2016.activities;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import com.abhishek.interiit2016.R;
import com.abhishek.interiit2016.model.CardModel;
import com.abhishek.interiit2016.view.CardContainer;
import com.abhishek.interiit2016.view.SimpleCardStackAdapter;

import java.util.ArrayList;

public class Contacts extends AppCompatActivity {
    private Context context = this;
    private static final String TAG = "MainActivity";
    private ArrayList<String> testData;
    private CardContainer mCardContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_contacts);
        mCardContainer = (CardContainer) findViewById(R.id.layoutview);

        Resources r = getResources();

        SimpleCardStackAdapter adapter = new SimpleCardStackAdapter(this);

        adapter.add(new CardModel("Prateek Jain", "Head Show Management","http://interiit.com/images/contact/prateek.jpg","",""));
        adapter.add(new CardModel("Pushpendra", "Head Show Management","http://interiit.com/images/contact/pushp.jpg","",""));
        adapter.add(new CardModel("Bibek Kumar", "Head Media & Publicity","http://interiit.com/images/contact/bibek.jpg","",""));
        adapter.add(new CardModel("Tarun Rathore", "Head Media & Publicity","http://interiit.com/images/contact/tarun.jpg","",""));
        adapter.add(new CardModel("Lisha Sinha", "Head Design","http://interiit.com/images/contact/lisha.jpg","",""));
        adapter.add(new CardModel("Zishan Zainul", "Head Design","http://interiit.com/images/contact/zishan.jpg","",""));
        adapter.add(new CardModel("Deependra", "Head Design","http://interiit.com/images/contact/deep.jpg","",""));
        adapter.add(new CardModel("Rahul Wadhwani", "Head Marketing","http://interiit.com/images/contact/rahul.jpg","",""));
        adapter.add(new CardModel("Gunda Abhishek", "Head Web & Android","http://interiit.com/images/contact/a.jpg","",""));
        adapter.add(new CardModel("Subham Goyal", "Head Web & Android","http://interiit.com/images/contact/subham.jpg","",""));
        adapter.add(new CardModel("Kshitiz Yadav", "Head Public Relation","http://interiit.com/images/contact/kshitij.jpg","",""));
        adapter.add(new CardModel("Mayang Garg", "Head Hospitality","http://interiit.com/images/contact/mayank.jpg","",""));
        adapter.add(new CardModel("Nitin Sangwan", "Head Hospitality","http://interiit.com/images/contact/nitin.jpg","",""));
        adapter.add(new CardModel("Gaurav Singh", "Head Events","http://interiit.com/images/contact/gaurav.jpg","",""));
        adapter.add(new CardModel("Priya Yadav", "Head Events","http://interiit.com/images/contact/priya.jpg","",""));
        adapter.add(new CardModel("Samyak Jain", "Overall Coordinator","http://interiit.com/images/contact/samyak.jpg","",""));
        adapter.add(new CardModel("Nicku Nitish", "Overall Coordinator","http://interiit.com/images/contact/nicku.jpg","",""));
        CardModel cardModel = new CardModel("Robinson", "Organizing Head","http://interiit.com/images/contact/guria.jpg","","");
        cardModel.setOnClickListener(new CardModel.OnClickListener() {
            @Override
            public void OnClickListener() {
                Log.i("Swipeable Cards","I am pressing the card");
            }
        });

        cardModel.setOnCardDismissedListener(new CardModel.OnCardDismissedListener() {
            @Override
            public void onLike() {
                Log.i("Swipeable Cards","I like the card");
            }

            @Override
            public void onDislike() {
                Log.i("Swipeable Cards","I dislike the card");
            }
        });

        adapter.add(cardModel);

        mCardContainer.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contacts, menu);
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
