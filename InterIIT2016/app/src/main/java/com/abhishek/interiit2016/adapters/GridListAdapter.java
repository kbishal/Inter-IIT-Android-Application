package com.abhishek.interiit2016.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abhishek.interiit2016.R;
import com.abhishek.interiit2016.model.ItemObject;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by GUNDA ABHISHEK on 23-06-2016.
 */
public class GridListAdapter extends RecyclerView.Adapter<GridListAdapter.VersionViewHolder> {
        private List<ItemObject> itemList;
        private OnItemClickListener clickListener;
        private Context context;
        public GridListAdapter(Context context, List<ItemObject> itemList) {
                this.itemList = itemList;
                this.context = context;
        }

    @Override
    public VersionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.team_list_item, parent, false);
        VersionViewHolder viewHolder = new VersionViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(VersionViewHolder holder, int position) {
        holder.EventName.setText(itemList.get(position).getName());
        Picasso.with(holder.EventPhoto.getContext()).load(itemList.get(position).getPhoto()).into(holder.EventPhoto);

        //holder.EventPhoto.setImageResource(itemList.get(position).getPhoto());
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
    class VersionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView EventName;
        public ImageView EventPhoto;
        public VersionViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            EventName =(TextView) itemView.findViewById(R.id.college_name);
            EventPhoto=(ImageView)itemView.findViewById(R.id.cllg_logo);

        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(v, getPosition());
        }
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }
}
