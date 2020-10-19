package smarthomesimulator.model;

import java.util.ArrayList;

/**
 *
 */

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

public class Simulator {

    private String date;
    private String time;

    public static ArrayList<Room> roomsOfHouse = new ArrayList<Room>();


    public Simulator() {
        super();
        this.date = this.getCurrentDate();
        this.time = this.getCurrentTime();
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getCurrentDate(){

        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int year  = localDate.getYear();
        int month = localDate.getMonthValue();
        int day   = localDate.getDayOfMonth();
        String today = year + "-" + month + "-" + day;

        return today;
    }

    public String getCurrentTime(){
        LocalTime time = LocalTime.now();
        int hour  = time.getHour();
        int minute = time.getMinute();
        String newMin = "";
        String curTime = "";
        if(String.valueOf(minute).length() < 2){
            newMin = "0" + minute;
            curTime = hour + ":" + newMin;
        }
        else {
            curTime = hour + ":" + minute;
        }
        return curTime;
    }
}
