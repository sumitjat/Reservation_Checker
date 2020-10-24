package com.example.codingsolution;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Checker
{
    private String date,stime,endtime,name;

    public String getName(){return  name;}

    public void setName(String name){this.name=name;}

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public String getEndtime() {

        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }
}
