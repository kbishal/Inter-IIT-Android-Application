package com.abhishek.interiit2016.model;

/**
 * Created by GUNDA ABHISHEK on 29-06-2016.
 */
public class StandingsDTO {
    private String position;
    private String college_name;
    private String points;

    public StandingsDTO(String position ,String college_name,String points){
        this.position=position;
        this.college_name=college_name;
        this.points=points;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCollege_name() {
        return college_name;
    }

    public void setCollege_name(String college_name) {
        this.college_name = college_name;
    }

    public String getpoints() {
        return points;
    }

    public void setpoints(String points) {
        this.points = points;
    }



}
