package smarthomesimulator.model;

import java.util.ArrayList;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class Simulator {

    private String date;
    private String time;
    private double tempOut;
    private int defaultTempIn;
    private String fileName;
    private static int roomNumber = 0;
    
    public static ArrayList<Room> roomsOfHouse = new ArrayList<>();
    public static ArrayList<Profile> profilesOfHouse = new ArrayList<>();

    public Simulator() {
        super();
        this.date = this.getCurrentDate();
        this.time = this.getCurrentTime();
        this.tempOut = this.getTempOut();
        this.defaultTempIn = this.getDefaultTempIn();
        this.fileName=this.getFileName();
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

    public double getTempOut() { return tempOut; }

    public void setTempOut(double tempOut) { this.tempOut = tempOut; }

    public int getDefaultTempIn() { return defaultTempIn; }

    public void setDefaultTempIn(int defaultTempIn) { this.defaultTempIn = defaultTempIn; }

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
    
    public void setFileName(String fileName) {
    	this.fileName=fileName;
    }
    public String getFileName() {
    	return fileName;
    }

    public static Room getRoom(String name) {
    	
    	
    	for(int i=0; i<roomsOfHouse.size();i++) {
    		if(name.equals(roomsOfHouse.get(i).getRoomName())) {
    			 roomNumber = i;
    			 break;
    		}
    	}
    	
    	return roomsOfHouse.get(roomNumber);
    }
    public void addProfile(Profile profile) {
        profilesOfHouse.add(profile);
    }

}
