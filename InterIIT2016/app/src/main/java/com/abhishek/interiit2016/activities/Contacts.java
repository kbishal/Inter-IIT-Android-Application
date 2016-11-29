package com.abhishek.interiit2016.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import com.abhishek.interiit2016.R;
import com.abhishek.interiit2016.model.CardModel;
import com.abhishek.interiit2016.utils.Utils;
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
       if(!Utils.isInterNetUp(Contacts.this)){
            Utils.showCustomToast(Contacts.this, "Please check your internet connection or try again later", Toast.LENGTH_LONG);
        }
        SimpleCardStackAdapter adapter = new SimpleCardStackAdapter(this);

        //-------------------------------------------------------------------------------------------------------------------------
// SIDDHARTH SAMPLE FB URL AND CONCT  SHOWN IN MY LIST ITEM
        // DO NOT ADD +91 FOR NUMBER -----------------------------------------------------------------------------------------------

        adapter.add(new CardModel("Prateek Jain", "Head Show Management","http://interiit.com/images/contact/prateek.jpg","8175909969","https://www.facebook.com/Prateeknj?fref=ts"));
        adapter.add(new CardModel("Pushpendra", "Head Show Management","http://interiit.com/images/contact/pushp.jpg","8172815262","https://www.facebook.com/pushpendra.kuntal.501?fref=ts"));
        adapter.add(new CardModel("Bibek Kumar", "Head Media & Publicity","http://interiit.com/images/contact/bibek.jpg","8960806840","https://www.facebook.com/bibek14187?fref=ts"));
        adapter.add(new CardModel("Tarun Rathore", "Head Media & Publicity","http://interiit.com/images/contact/tarun.jpg","8960806967","https://www.facebook.com/tarun.rathore.31?fref=ts"));
        adapter.add(new CardModel("Lisha Sinha", "Head Design","http://interiit.com/images/contact/lisha.jpg","7755048158","https://www.facebook.com/lisha.sinha.92?fref=ts"));
        adapter.add(new CardModel("Zishan Zainul", "Head Design","http://interiit.com/images/contact/zishan.jpg","8960621328","https://www.facebook.com/zishan.zainul?fref=ts"));
        adapter.add(new CardModel("Deependra", "Head Design","http://interiit.com/images/contact/deep.jpg","9721373870","https://www.facebook.com/deependra.rajpoot.927?fref=ts"));
        adapter.add(new CardModel("Rahul Wadhwani", "Head Marketing","http://interiit.com/images/contact/rahul.jpg","7388455444","https://www.facebook.com/rahul.wadhwani.104?fref=ts"));
        adapter.add(new CardModel("Gunda Abhishek", "Head Web & Android","http://interiit.com/images/contact/a.jpg","7755057754","https://www.facebook.com/abhishek.gunda.5"));
        adapter.add(new CardModel("Subham Goyal", "Head Web & Android","http://interiit.com/images/contact/subham.jpg","9410338009","https://www.facebook.com/subham.iitk?fref=ts"));
        adapter.add(new CardModel("Kshitiz Yadav", "Head Public Relation","http://interiit.com/images/contact/kshitij.jpg","9999823334","https://www.facebook.com/kshitij.yadav.165?fref=ts"));
        adapter.add(new CardModel("Mayang Garg", "Head Hospitality","http://interiit.com/images/contact/mayank.jpg","7599462646","https://www.facebook.com/profile.php?id=100006543783075&fref=ts"));
        adapter.add(new CardModel("Nitin Sangwan", "Head Hospitality","http://interiit.com/images/contact/nitin.jpg","8175989891","https://www.facebook.com/nitin.sangwan.902?fref=ts"));
        adapter.add(new CardModel("Gaurav Singh", "Head Events","http://interiit.com/images/contact/gaurav.jpg","9695628655","https://www.facebook.com/ronaldogaurav?fref=ts"));
        adapter.add(new CardModel("Priya Yadav", "Head Events","http://interiit.com/images/contact/priya.jpg","8090623179","https://www.facebook.com/priya.yadav.5623?fref=ts"));
        adapter.add(new CardModel("Samyak Jain", "Overall Coordinator","http://interiit.com/images/contact/samyak.jpg","7275566126","https://www.facebook.com/samyakstar?fref=ts"));
        adapter.add(new CardModel("Nicku Nitish", "Overall Coordinator","http://interiit.com/images/contact/nicku.jpg","7275795961","https://www.facebook.com/nicku.nitish?fref=ts"));
        CardModel cardModel = new CardModel("Robinson", "Organizing Head","http://interiit.com/images/contact/guria.jpg","","https://www.facebook.com/robinson.guria?fref=ts");
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
