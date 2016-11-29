package com.abhishek.interiit2016.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
	private Context mContext;
	public SimpleCardStackAdapter(Context mContext) {
		super(mContext);
	}

	@Override
	public View getCardView(int position, final CardModel model, View convertView, ViewGroup parent) {
		if(convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(getContext());
			convertView = inflater.inflate(R.layout.std_card_inner, parent, false);
			assert convertView != null;
		}
		ImageView imageView = (ImageView) convertView.findViewById(R.id.image);
		ImageButton fb =(ImageButton)convertView.findViewById(R.id.image_2);
		ImageButton call = (ImageButton)convertView.findViewById(R.id.image_1);
		call.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_DIAL);
				intent.setData(Uri.parse("tel:" +model.getNumber()));
				v.getContext().startActivity(intent);
			}
		});
		fb.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Uri uri = Uri.parse(model.getFburl()); // missing 'http://' will cause crashed
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				v.getContext().startActivity(intent);
			}
		});
		Picasso.with(imageView.getContext()).load(model.getImageurl()).resize(658,648).centerCrop().into(imageView);
		//((ImageView) convertView.findViewById(R.id.image)).setImageDrawable(model.getCardImageDrawable());
		((TextView) convertView.findViewById(R.id.title)).setText(model.getTitle());
		((TextView) convertView.findViewById(R.id.description)).setText(model.getDescription());

		return convertView;
	}
}
