package com.abhishek.interiit2016.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.abhishek.interiit2016.R;
import com.abhishek.interiit2016.activities.ResultsActivity;
import com.abhishek.interiit2016.activities.rules;
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
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by GUNDA ABHISHEK on 24-06-2016.
 */
public class HomeFragment extends Fragment implements ImageGalleryAdapter.ImageThumbnailLoader, FullScreenImageGalleryAdapter.FullScreenImageLoader{
    private Drawable color;
    private PaletteColorType paletteColorType;
    List<ImageDTO> imageDTOList;
    //String[] images = new String[10];
    List<String> images = new ArrayList<String>();
    //String[] images = new String[10];
    SharedPreferences sharedPreferences;
    String gender,Sport;
    //private RelativeLayout results,fixtures,standings,teams,photos;
    public HomeFragment() {
    }

    @SuppressLint("ValidFragment")
    public HomeFragment(Drawable color) {
        this.color = color;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
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
       ButterKnife.bind(view);
        ImageGalleryActivity.setImageThumbnailLoader(this);
        FullScreenImageGalleryActivity.setFullScreenImageLoader(this);
        paletteColorType = PaletteColorType.VIBRANT;
        CardView results= (CardView) view.findViewById(R.id.results);
        CardView fixtures= (CardView) view.findViewById(R.id.fixtures);
        CardView photos= (CardView) view.findViewById(R.id.photos);
        sharedPreferences = this.getActivity().getSharedPreferences(APIConstants.USER_SPORT_SELECTED, Context.MODE_PRIVATE);
        gender = sharedPreferences.getString("Gender","Men");
        Sport = sharedPreferences.getString("Sport","");
        photos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getsportimages(gender,Sport);

            }
        });


        results.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),ResultsActivity.class);
                startActivity(intent);
            }
        });
        fixtures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), rules.class);
                startActivity(intent);
            }
        });
        FloatingActionButton floatingActionButton =(FloatingActionButton)view.findViewById(R.id.fab5);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(getActivity())
                        .title("Select gender")
                        .items(R.array.gender)
                        .itemsCallbackSingleChoice(2, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("Gender", text.toString());
                                editor.commit();
                                ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(sharedPreferences.getString("Sport","")+" - "+text.toString());

                                return true; // allow selection
                            }
                        })
                        .positiveText("Submit")
                        .show();
            }
        });
        return view;
    }

    private void getsportimages(final String gender, final String sport) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://www.interiit.com/php")
                .build();
        DataService dataService =restAdapter.create(DataService.class);

        retrofit.Callback callback = new retrofit.Callback() {

            @Override
            public void success(Object o, Response response) {
                APIResponse schedule = (APIResponse)o;
                if (schedule.getResult().equals("failure")){
                    Utils.showCustomToast(getActivity(),"Images yet to be uploaded, try again later",Toast.LENGTH_LONG);
                }else {
                    try {
                        JSONArray jsonArray =new JSONArray(schedule.getData());
                        Type type = new TypeToken<List<ImageDTO>>(){}.getType();
                        imageDTOList = GsonFactory.getISOFormatInstance().fromJson(jsonArray.toString(), type);
                        for (int i=0; i<imageDTOList.size(); i++) {
                            images.add( imageDTOList.get(i).getImageurl() );
                        }
                        Intent intent = new Intent(getActivity(), ImageGalleryActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putStringArrayList(ImageGalleryActivity.KEY_IMAGES, (ArrayList<String>) images);
                        bundle.putString(ImageGalleryActivity.KEY_TITLE, sport+"-"+gender);
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
        if (Utils.isInterNetUp(getActivity())){
            dataService.getimages(sport,gender, callback);
        }
        else {
            Utils.showCustomToast(getActivity(), "Please check your internet connection or try again later", Toast.LENGTH_LONG);
            Intent intent = new Intent(getActivity(), ImageGalleryActivity.class);
            Bundle bundle = new Bundle();
            bundle.putStringArrayList(ImageGalleryActivity.KEY_IMAGES, (ArrayList<String>) images);
            bundle.putString(ImageGalleryActivity.KEY_TITLE, sport+"-"+gender);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

    @Override
    public void loadFullScreenImage(final ImageView iv, String imageUrl, int width, final LinearLayout bgLinearLayout) {
        if (!TextUtils.isEmpty(imageUrl)) {
            Picasso.with(iv.getContext())
                    .load("http://interiit.com/images/"+Sport+"/"+imageUrl)
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
                    .load("http://interiit.com/images/"+Sport+"/"+imageUrl)
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
}
