package com.abhishek.interiit2016.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.abhishek.interiit2016.R;
import com.abhishek.interiit2016.model.APIResponse;
import com.abhishek.interiit2016.model.ImageDTO;
import com.abhishek.interiit2016.utils.APIConstants;
import com.abhishek.interiit2016.utils.DataService;
import com.abhishek.interiit2016.utils.GsonFactory;
import com.abhishek.interiit2016.utils.Utils;
import com.afollestad.materialdialogs.MaterialDialog;
import com.etiennelawlor.imagegallery.library.activities.FullScreenImageGalleryActivity;
import com.etiennelawlor.imagegallery.library.activities.ImageGalleryActivity;
import com.etiennelawlor.imagegallery.library.adapters.FullScreenImageGalleryAdapter;
import com.etiennelawlor.imagegallery.library.adapters.ImageGalleryAdapter;
import com.etiennelawlor.imagegallery.library.enums.PaletteColorType;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import io.smooch.core.User;
import io.smooch.ui.ConversationActivity;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class NavDrawerActivity extends AppCompatActivity implements ImageGalleryAdapter.ImageThumbnailLoader, FullScreenImageGalleryAdapter.FullScreenImageLoader {

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FrameLayout mContentFrame;

    private static final String PREFERENCES_FILE = "mymaterialapp_settings";
    private static final String PREF_USER_LEARNED_DRAWER = "navigation_drawer_learned";
    private static final String STATE_SELECTED_POSITION = "selected_navigation_drawer_position";

    private boolean mUserLearnedDrawer;
    private boolean mFromSavedInstanceState;
    private int mCurrentSelectedPosition;
    private final Handler mDrawerActionHandler = new Handler();
    private static final long DRAWER_CLOSE_DELAY_MS = 250;
    private PaletteColorType paletteColorType;
    List<ImageDTO> imageDTOList;
    String username;
    //String[] images = new String[10];
    List<String> images = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_nav_drawer);
        final SharedPreferences sharedPreferences = getSharedPreferences(APIConstants.USER_SPORT_SELECTED, Context.MODE_PRIVATE);
        username = sharedPreferences.getString("username","");

        setUpToolbar();
//        images[0]="https://images.unsplash.com/photo-1437422061949-f6efbde0a471?q=80&amp;fm=jpg&amp;s=e23055c9ba7686b8fe583fb8318a1f88";
//        images[1]="https://images.unsplash.com/photo-1434139240289-56c519f77cb0?q=80&amp;fm=jpg&amp;s=13f8a0d1c2f96b5f311dedeb17cddb60";
//        images[2]="https://images.unsplash.com/photo-1429152937938-07b5f2828cdd?q=80&amp;fm=jpg&amp;s=a4f424db0ae5a398297df5ae5e0520d6";
//        images[3]="https://images.unsplash.com/photo-1430866880825-336a7d7814eb?q=80&amp;fm=jpg&amp;s=450de8563ac041f48b1563b499f56895";
//        images[4]="https://images.unsplash.com/photo-1429547584745-d8bec594c82e?q=80&amp;fm=jpg&amp;s=e9a7d9973088122a3e453cb2af541201";
//        images[5]="https://images.unsplash.com/photo-1429277158984-614d155e0017?q=80&amp;fm=jpg&amp;s=138f154e17a304b296c953323862633b";
//        images[6]="https://images.unsplash.com/photo-1429042007245-890c9e2603af?q=80&amp;fm=jpg&amp;s=8b76d20174cf46bffe32ea18f05551d3";
//        images[7]="https://images.unsplash.com/photo-1429091967365-492aaa5accfe?q=80&amp;fm=jpg&amp;s=b7430cfe5508430aea39fcf3b0645878";
//        images[8]="https://images.unsplash.com/photo-1430132594682-16e1185b17c5?q=80&amp;fm=jpg&amp;s=a70abbfff85382d11b03b9bbc71649c3";
//        images[9]="https://images.unsplash.com/photo-1415871989540-61fe9268d3c8?q=80&amp;fm=jpg&amp;s=061a03a7abe860a6c165cc3994feaba2";
        ButterKnife.bind(NavDrawerActivity.this);
        ImageGalleryActivity.setImageThumbnailLoader(NavDrawerActivity.this);
        FullScreenImageGalleryActivity.setFullScreenImageLoader(NavDrawerActivity.this);
        paletteColorType = PaletteColorType.VIBRANT;
        mDrawerLayout = (DrawerLayout) findViewById(R.id.nav_drawer);

        mUserLearnedDrawer = Boolean.valueOf(readSharedSetting(this, PREF_USER_LEARNED_DRAWER, "false"));

        if (savedInstanceState != null) {
            mCurrentSelectedPosition = savedInstanceState.getInt(STATE_SELECTED_POSITION);
            mFromSavedInstanceState = true;
        }

        setUpNavDrawer();

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mContentFrame = (FrameLayout) findViewById(R.id.nav_contentframe);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                Intent intent;
                menuItem.setChecked(true);
                switch (menuItem.getItemId()) {
                    case R.id.navigation_item_1:
                        intent =new Intent(NavDrawerActivity.this,HomeActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.navigation_item_2:
                        intent =new Intent(NavDrawerActivity.this,Points.class);
                        startActivity(intent);
                        return true;
                    case R.id.navigation_item_3:
                        getimagesgallery();

                        return true;
                    case R.id.navigation_item_4:
                        intent =new Intent(NavDrawerActivity.this,Contacts.class);
                        startActivity(intent);
                        return true;
                    case R.id.navigation_item_5:
                        if (username.equals("")){
                            new MaterialDialog.Builder(NavDrawerActivity.this)
                                    .title("Please provide your Username")
                                    .content("Maximum of 8 characters is allowed")
                                    .inputType(InputType.TYPE_CLASS_TEXT)
                                    .input("username", "", new MaterialDialog.InputCallback() {
                                        @Override
                                        public void onInput(MaterialDialog dialog, CharSequence input) {
                                            SharedPreferences.Editor editor = sharedPreferences.edit();
                                            editor.putString("username",input.toString());
                                            editor.commit();
                                            Utils.showCustomToast(NavDrawerActivity.this,"Submitted Successfully", Toast.LENGTH_SHORT);
                                            User.getCurrentUser().setFirstName(input.toString());
                                            User.getCurrentUser().setLastName("");
                                            //User.getCurrentUser().setEmail("abhigun@iitk.ac.in");
                                            ConversationActivity.show(NavDrawerActivity.this);
                                            // Do something
                                        }
                                    }).show();
                        }
                        else {
                            User.getCurrentUser().setFirstName(username);
                            User.getCurrentUser().setLastName("");
                            //User.getCurrentUser().setEmail("abhigun@iitk.ac.in");
                            ConversationActivity.show(NavDrawerActivity.this);
                        }

                        return true;

                    default:
                        return true;
                }
            }
        });

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_SELECTED_POSITION, mCurrentSelectedPosition);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mCurrentSelectedPosition = savedInstanceState.getInt(STATE_SELECTED_POSITION, 0);
        Menu menu = mNavigationView.getMenu();
        menu.getItem(mCurrentSelectedPosition).setChecked(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setUpToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }
    }

    private void setUpNavDrawer() {
        if (mToolbar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            mToolbar.setNavigationIcon(R.drawable.ic_drawer);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDrawerLayout.openDrawer(GravityCompat.START);
                }
            });
        }



    }

    public static void saveSharedSetting(Context ctx, String settingName, String settingValue) {
        SharedPreferences sharedPref = ctx.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(settingName, settingValue);
        editor.apply();
    }

    public static String readSharedSetting(Context ctx, String settingName, String defaultValue) {
        SharedPreferences sharedPref = ctx.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
        return sharedPref.getString(settingName, defaultValue);
    }

    @Override
    public void loadFullScreenImage(final ImageView iv, String imageUrl, int width, final LinearLayout bgLinearLayout) {
        if (!TextUtils.isEmpty(imageUrl)) {
            Picasso.with(iv.getContext())
                    .load("http://interiit.com/images/"+imageUrl)
                    .resize(width, 0)
                    .into(iv, new Callback() {
                        @Override
                        public void onSuccess() {
                            Bitmap bitmap = ((BitmapDrawable) iv.getDrawable()).getBitmap();
                            Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                                public void onGenerated(Palette palette) {
                                    applyPalette(palette, bgLinearLayout);
                                }
                            });
                        }

                        @Override
                        public void onError() {

                        }
                    });
        } else {
            iv.setImageDrawable(null);
        }
    }

    @Override
    public void loadImageThumbnail(ImageView iv, String imageUrl, int dimension) {
        if (!TextUtils.isEmpty(imageUrl)) {
            Picasso.with(iv.getContext())
                    .load("http://interiit.com/images/"+imageUrl)
                    .resize(dimension, dimension)
                    .centerCrop()
                    .into(iv);
        } else {
            iv.setImageDrawable(null);
        }

    }
    private void applyPalette(Palette palette, LinearLayout bgLinearLayout){
        int bgColor = getBackgroundColor(palette);
        if (bgColor != -1)
            bgLinearLayout.setBackgroundColor(bgColor);
    }

    private int getBackgroundColor(Palette palette) {
        int bgColor = -1;

        int vibrantColor = palette.getVibrantColor(0x000000);
        int lightVibrantColor = palette.getLightVibrantColor(0x000000);
        int darkVibrantColor = palette.getDarkVibrantColor(0x000000);

        int mutedColor = palette.getMutedColor(0x000000);
        int lightMutedColor = palette.getLightMutedColor(0x000000);
        int darkMutedColor = palette.getDarkMutedColor(0x000000);

        if (paletteColorType != null) {
            switch (paletteColorType) {
                case VIBRANT:
                    if (vibrantColor != 0) { // primary option
                        bgColor = vibrantColor;
                    } else if (lightVibrantColor != 0) { // fallback options
                        bgColor = lightVibrantColor;
                    } else if (darkVibrantColor != 0) {
                        bgColor = darkVibrantColor;
                    } else if (mutedColor != 0) {
                        bgColor = mutedColor;
                    } else if (lightMutedColor != 0) {
                        bgColor = lightMutedColor;
                    } else if (darkMutedColor != 0) {
                        bgColor = darkMutedColor;
                    }
                    break;
                case LIGHT_VIBRANT:
                    if (lightVibrantColor != 0) { // primary option
                        bgColor = lightVibrantColor;
                    } else if (vibrantColor != 0) { // fallback options
                        bgColor = vibrantColor;
                    } else if (darkVibrantColor != 0) {
                        bgColor = darkVibrantColor;
                    } else if (mutedColor != 0) {
                        bgColor = mutedColor;
                    } else if (lightMutedColor != 0) {
                        bgColor = lightMutedColor;
                    } else if (darkMutedColor != 0) {
                        bgColor = darkMutedColor;
                    }
                    break;
                case DARK_VIBRANT:
                    if (darkVibrantColor != 0) { // primary option
                        bgColor = darkVibrantColor;
                    } else if (vibrantColor != 0) { // fallback options
                        bgColor = vibrantColor;
                    } else if (lightVibrantColor != 0) {
                        bgColor = lightVibrantColor;
                    } else if (mutedColor != 0) {
                        bgColor = mutedColor;
                    } else if (lightMutedColor != 0) {
                        bgColor = lightMutedColor;
                    } else if (darkMutedColor != 0) {
                        bgColor = darkMutedColor;
                    }
                    break;
                case MUTED:
                    if (mutedColor != 0) { // primary option
                        bgColor = mutedColor;
                    } else if (lightMutedColor != 0) { // fallback options
                        bgColor = lightMutedColor;
                    } else if (darkMutedColor != 0) {
                        bgColor = darkMutedColor;
                    } else if (vibrantColor != 0) {
                        bgColor = vibrantColor;
                    } else if (lightVibrantColor != 0) {
                        bgColor = lightVibrantColor;
                    } else if (darkVibrantColor != 0) {
                        bgColor = darkVibrantColor;
                    }
                    break;
                case LIGHT_MUTED:
                    if (lightMutedColor != 0) { // primary option
                        bgColor = lightMutedColor;
                    } else if (mutedColor != 0) { // fallback options
                        bgColor = mutedColor;
                    } else if (darkMutedColor != 0) {
                        bgColor = darkMutedColor;
                    } else if (vibrantColor != 0) {
                        bgColor = vibrantColor;
                    } else if (lightVibrantColor != 0) {
                        bgColor = lightVibrantColor;
                    } else if (darkVibrantColor != 0) {
                        bgColor = darkVibrantColor;
                    }
                    break;
                case DARK_MUTED:
                    if (darkMutedColor != 0) { // primary option
                        bgColor = darkMutedColor;
                    } else if (mutedColor != 0) { // fallback options
                        bgColor = mutedColor;
                    } else if (lightMutedColor != 0) {
                        bgColor = lightMutedColor;
                    } else if (vibrantColor != 0) {
                        bgColor = vibrantColor;
                    } else if (lightVibrantColor != 0) {
                        bgColor = lightVibrantColor;
                    } else if (darkVibrantColor != 0) {
                        bgColor = darkVibrantColor;
                    }
                    break;
                default:
                    break;
            }
        }

        return bgColor;
    }
    private void getimagesgallery() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://www.interiit.com/php")
                .build();
        DataService dataService =restAdapter.create(DataService.class);

        retrofit.Callback callback = new retrofit.Callback() {

            @Override
            public void success(Object o, Response response) {
                APIResponse schedule = (APIResponse)o;
                if (schedule.getResult().equals("failure")){

                }else {
                    try {
                        JSONArray jsonArray =new JSONArray(schedule.getData());
                        Type type = new TypeToken<List<ImageDTO>>(){}.getType();
                        imageDTOList = GsonFactory.getISOFormatInstance().fromJson(jsonArray.toString(), type);
                        for (int i=0; i<imageDTOList.size(); i++) {
                            images.add( imageDTOList.get(i).getImageurl() );
                        }
                        Intent intent = new Intent(NavDrawerActivity.this, ImageGalleryActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putStringArrayList(ImageGalleryActivity.KEY_IMAGES, (ArrayList<String>) images);
                        bundle.putString(ImageGalleryActivity.KEY_TITLE, "Gallery");
                        intent.putExtras(bundle);
                        startActivity(intent);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void failure(RetrofitError retrofitError) {
            }
        };
        if (Utils.isInterNetUp(NavDrawerActivity.this)){
            dataService.getgalleryimages("Gallery", callback);
        }
        else {
            Utils.showCustomToast(NavDrawerActivity.this, "Please check your internet connection or try again later", Toast.LENGTH_LONG);
            Intent intent = new Intent(NavDrawerActivity.this, ImageGalleryActivity.class);
            Bundle bundle = new Bundle();
            bundle.putStringArrayList(ImageGalleryActivity.KEY_IMAGES, (ArrayList<String>) images);
            bundle.putString(ImageGalleryActivity.KEY_TITLE, "Gallery");
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}
