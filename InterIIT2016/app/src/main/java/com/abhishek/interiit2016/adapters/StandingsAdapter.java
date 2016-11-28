package com.abhishek.interiit2016.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abhishek.interiit2016.R;
import com.abhishek.interiit2016.model.ItemObject;
import com.abhishek.interiit2016.model.ScheduleDTO;
import com.abhishek.interiit2016.model.StandingsDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GUNDA ABHISHEK on 29-06-2016.
 */
public class StandingsAdapter extends RecyclerView.Adapter<StandingsAdapter.VersionViewHolder> {
    Boolean isHomeList = false;
    Context context;
    OnItemClickListener clickListener;
    private int flag;
    private List<StandingsDTO> standingsDTOList;
    private List<ItemObject> itemObjects;
    public StandingsAdapter(Context context,List<StandingsDTO> standingsDTOList){
        isHomeList=true;
        this.context=context;
        this.standingsDTOList=standingsDTOList;
    }
    public StandingsAdapter(List<ItemObject> itemObjects, int flag){
        isHomeList=false;
        this.flag=flag;
        this.itemObjects=itemObjects;
    }
    @Override
    public VersionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (isHomeList == true){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.standings_list_item, parent, false);
            VersionViewHolder viewHolder = new VersionViewHolder(view);
            return viewHolder;
        }
        else {
            if(flag == 1){
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.team_list_item, parent, false);
                VersionViewHolder viewHolder = new VersionViewHolder(view);
                return viewHolder;
            }
            else if (flag == 2){
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sports_list_item, parent, false);
                VersionViewHolder viewHolder = new VersionViewHolder(view);
                return viewHolder;
            }
            else {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sports_list_item, parent, false);
                VersionViewHolder viewHolder = new VersionViewHolder(view);
                return viewHolder;
            }
        }
    }

    @Override
    public void onBindViewHolder(VersionViewHolder versionViewHolder, int i) {
        if (isHomeList) {
            versionViewHolder.standings_points.setText(standingsDTOList.get(i).getpoints());
            versionViewHolder.position.setText(String.valueOf(i+1));
            versionViewHolder.college_name.setText(standingsDTOList.get(i).getCollege_name());
        } else {
            versionViewHolder.college_name.setText(itemObjects.get(i).getName());
            versionViewHolder.imageView.setImageResource(itemObjects.get(i).getPhoto());
        }
    }

    @Override
    public int getItemCount() {
        if (isHomeList)
        return standingsDTOList == null ? 0 : standingsDTOList.size();
        else
            return itemObjects ==  null ? 0 : itemObjects.size();
    }

    class VersionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView position,college_name,standings_points;
        ImageView imageView;
        public VersionViewHolder(View itemView) {
            super(itemView);
            position=(TextView)itemView.findViewById(R.id.position);
            college_name=(TextView)itemView.findViewById(R.id.college_name);
            standings_points=(TextView)itemView.findViewById(R.id.standings_points);
            imageView =(ImageView)itemView.findViewById(R.id.cllg_logo);
            if (!isHomeList) {
                itemView.setOnClickListener(this);
            } else {
            }

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
