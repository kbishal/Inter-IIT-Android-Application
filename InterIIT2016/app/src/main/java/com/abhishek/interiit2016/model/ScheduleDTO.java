package com.abhishek.interiit2016.model;

/**
 * Created by GUNDA ABHISHEK on 24-06-2016.
 */
public class ScheduleDTO {
    private String team1;
    private String team2;
    private String  event_time;
    private String venue;
    private String event_date;
    private String match_details;
    private String match_summary;
    private String team1_score;
    private String team2_score;


    public ScheduleDTO(String team1,String team2,String event_time,String event_date,String venue,String match_details){
        this.team1=team1;
        this.team2=team2;
        this.event_date=event_date;
        this.event_time=event_time;
        this.venue=venue;
        this.match_details=match_details;
    }
    public ScheduleDTO(String team1,String team2,String event_time,String event_date,String venue,String match_details,String team1_score,String team2_score,String match_summary){
        this.team1=team1;
        this.team2=team2;
        this.event_date=event_date;
        this.event_time=event_time;
        this.venue=venue;
        this.match_details=match_details;
        this.team1_score=team1_score;
        this.team2_score=team2_score;
        this.match_summary=match_summary;
    }
    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getEvent_time() {
        return event_time;
    }

    public void setEvent_time(String event_time) {
        this.event_time = event_time;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getEvent_date() {
        return event_date;
    }

    public void setEvent_date(String event_date) {
        this.event_date = event_date;
    }

    public String getMatch_details() {
        return match_details;
    }

    public void setMatch_details(String match_details) {
        this.match_details = match_details;
    }



    public String getMatch_summary() {
        return match_summary;
    }

    public void setMatch_summary(String match_summary) {
        this.match_summary = match_summary;
    }
    public String getTeam1_score() {
        return team1_score;
    }

    public void setTeam1_score(String team1_score) {
        this.team1_score = team1_score;
    }

    public String getTeam2_score() {
        return team2_score;
    }

    public void setTeam2_score(String team2_score) {
        this.team2_score = team2_score;
    }

}
