package com.abhishek.interiit2016.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.abhishek.interiit2016.R;
import com.abhishek.interiit2016.model.CardModel;
import com.squareup.picasso.Picasso;

public final class SimpleCardStackAdapter extends CardStackAdapter {

	public SimpleCardStackAdapter(Context mContext) {
		super(mContext);
	}

	@Override
	public View getCardView(int position, CardModel model, View convertView, ViewGroup parent) {
		if(convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(getContext());
			convertView = inflater.inflate(R.layout.std_card_inner, parent, false);
			assert convertView != null;
		}
		ImageView imageView = (ImageView) convertView.findViewById(R.id.image);
		ImageButton fb =(ImageButton)convertView.findViewById(R.id.image_2);
		ImageButton call = (ImageButton)convertView.findViewById(R.id.image_1);
		Picasso.with(imageView.getContext()).load(model.getImageurl()).into(imageView);
		//((ImageView) convertView.findViewById(R.id.image)).setImageDrawable(model.getCardImageDrawable());
		((TextView) convertView.findViewById(R.id.title)).setText(model.getTitle());
		((TextView) convertView.findViewById(R.id.description)).setText(model.getDescription());

		return convertView;
	}
}
