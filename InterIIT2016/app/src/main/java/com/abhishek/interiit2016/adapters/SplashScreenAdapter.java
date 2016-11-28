package com.abhishek.interiit2016.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.abhishek.interiit2016.R;
import com.abhishek.interiit2016.utils.Utils;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by sheelu on 1/13/2016.
 */
public class SplashScreenAdapter extends PagerAdapter {
    private Context context;
    private SwipeUpdateListener listener;
    private int MAX_SWIPE_ELEMENTS=3;

    //Interface for update bubbles
    public interface SwipeUpdateListener {
        void updateBubble(int position);
    }

    public SplashScreenAdapter(Context context, SwipeUpdateListener listener){
        this.context=context;
        this.listener=listener;
    }

    @Override
    public int getCount() {
        return MAX_SWIPE_ELEMENTS;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int imagePosition) {
        LayoutInflater inflater = (LayoutInflater) container.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_splash_screen_swipe_layout, null);
        RelativeLayout fl=(RelativeLayout)view.findViewById(R.id.swipe_bg_ll);
        if (imagePosition == 0) {
            try {
                // get input stream
                InputStream ims = context.getAssets().open("contest.jpg");
                // load image as Drawable
                Drawable d = Drawable.createFromStream(ims, null);
                // set image to ImageView
                Utils.setImageFromAssetsForRelativeLayout(fl,d);
            }
            catch(IOException ex) {
            }
        } else if (imagePosition == 1) {
            try {
                // get input stream
                InputStream ims =context.getAssets().open("trek.jpg");
                // load image as Drawable
                Drawable d = Drawable.createFromStream(ims, null);
                // set image to ImageView
                Utils.setImageFromAssetsForRelativeLayout(fl, d);

            }
            catch(IOException ex) {
            }
        } else if (imagePosition == 2) {
            try {
                // get input stream
                InputStream ims = context.getAssets().open("marathon.jpg");
                // load image as Drawable
                Drawable d = Drawable.createFromStream(ims, null);
                // set image to ImageView
                Utils.setImageFromAssetsForRelativeLayout(fl, d);
            }
            catch(IOException ex) {
            }
        }
        container.addView(view);

        //update circles
        if(imagePosition>=1)
            listener.updateBubble(imagePosition-1);
        if(imagePosition==0)
            listener.updateBubble(imagePosition+1);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if(position==0)
            listener.updateBubble(position+2);
        if(position==2)
            listener.updateBubble(position-2);
        container.removeView((RelativeLayout) object);

    }
}