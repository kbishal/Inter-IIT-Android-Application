package com.abhishek.interiit2016.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abhishek.interiit2016.R;
import com.abhishek.interiit2016.model.ScheduleDTO;
import com.abhishek.interiit2016.utils.Utils;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suleiman on 14-04-2015.
 */
public class SimpleRecyclerAdapter extends RecyclerView.Adapter<SimpleRecyclerAdapter.VersionViewHolder> {

    private List<ScheduleDTO> scheduleDTOList,resultDTOList;
    Boolean isHomeList = false;
    Context context;
    OnItemClickListener clickListener;
    private int previousposition=0;


    public SimpleRecyclerAdapter(List<ScheduleDTO> scheduleDTOList) {
        isHomeList = true;
        this.scheduleDTOList=scheduleDTOList;
    }


    public SimpleRecyclerAdapter(Context context,List<ScheduleDTO> resultDTOList) {
        isHomeList = false;
        this.context = context;
        this.resultDTOList = resultDTOList;

    }

    @Override
    public VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (isHomeList){
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.schedule_list_item, viewGroup, false);
            VersionViewHolder viewHolder = new VersionViewHolder(view);
            return viewHolder;
        }
        else {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.results_list_item, viewGroup, false);
            VersionViewHolder viewHolder = new VersionViewHolder(view);
            return viewHolder;
        }
    }

    @Override
    public void onBindViewHolder(VersionViewHolder versionViewHolder, int i) {
        if (isHomeList) {
            versionViewHolder.team1.setText(scheduleDTOList.get(i).getTeam1());
            versionViewHolder.team2.setText(scheduleDTOList.get(i).getTeam2());
            versionViewHolder.venue.setText(scheduleDTOList.get(i).getVenue());
            versionViewHolder.details.setText(scheduleDTOList.get(i).getMatch_details());
            versionViewHolder.time.setText(scheduleDTOList.get(i).getEvent_time());
            versionViewHolder.day.setText(scheduleDTOList.get(i).getEvent_date());
        } else {
                versionViewHolder.team1.setText(resultDTOList.get(i).getTeam1());
                versionViewHolder.team2.setText(resultDTOList.get(i).getTeam2());
                versionViewHolder.venue.setText(resultDTOList.get(i).getVenue());
                versionViewHolder.details.setText(resultDTOList.get(i).getMatch_details());
                versionViewHolder.time.setText(resultDTOList.get(i).getEvent_time());
                versionViewHolder.day.setText(resultDTOList.get(i).getEvent_date());
                versionViewHolder.score1.setText(resultDTOList.get(i).getTeam1_score());
                versionViewHolder.score2.setText(resultDTOList.get(i).getTeam2_score());
                versionViewHolder.summary.setText(resultDTOList.get(i).getMatch_summary());

        }
        if (i>previousposition){
            Utils.animatelist(versionViewHolder,true);
        }
        else {
            //Utils.animatelist(versionViewHolder,false);
        }
    }

    @Override
    public int getItemCount() {
        if (isHomeList){
            return scheduleDTOList == null ? 0 : scheduleDTOList.size();
        }

        else
            return resultDTOList == null ? 0 : resultDTOList.size();
    }


    class VersionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cardItemLayout;
        TextView team1,team2,venue,time,details,summary,score1,score2,day;
        public VersionViewHolder(View itemView) {
            super(itemView);

            cardItemLayout = (CardView) itemView.findViewById(R.id.cardlist_item);
            team1 = (TextView) itemView.findViewById(R.id.team1);
            team2 = (TextView) itemView.findViewById(R.id.team2);
            day =(TextView)itemView.findViewById(R.id.listitem_name);
            venue =(TextView) itemView.findViewById(R.id.venue);
            time =(TextView) itemView.findViewById(R.id.event_time);
            details =(TextView) itemView.findViewById(R.id.match_details);
            summary =(TextView)itemView.findViewById(R.id.match_summary);
            score1=(TextView)itemView.findViewById(R.id.score1);
            score2=(TextView)itemView.findViewById(R.id.score2);

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
