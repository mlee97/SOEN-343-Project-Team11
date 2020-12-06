package smarthomesimulator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class Simulator {

    @JsonProperty()
    private String date;
    @JsonProperty()
    private String time;
    @JsonProperty()
    private double tempOut;
    private double defaultTempIn;
    private double tempIn;
    private String fileName;
    private static int roomNumber = 0;
    private boolean awayMode;
    @JsonProperty()
    private ConsoleOutput cOut;

    @JsonProperty()
    private double defaultSummerTemp;
    @JsonProperty()
    private double defaultWinterTemp;

    public static ArrayList<Room> roomsOfHouse = new ArrayList<>();
    public static ArrayList<Profile> profilesOfHouse = new ArrayList<>();
    public static List<Zone> zonesOfHouse = new ArrayList<>();

    public Simulator() {
        super();
        this.date = this.getCurrentDate();
        this.time = this.getCurrentTime();
        this.tempOut = this.getTempOut();
        this.defaultTempIn = this.getDefaultTempIn();
        this.defaultSummerTemp = this.getDefaultSummerTemp();
        this.defaultWinterTemp = this.getDefaultWinterTemp();
        this.fileName=this.getFileName();
        this.cOut = new ConsoleOutput();
        awayMode = isAwayMode();
    }

    public static int getRoomNumber() {
        return roomNumber;
    }

    public static List<Zone> getZonesOfHouse(){
        return zonesOfHouse;
    }

    public static ArrayList<Room> getRoomsOfHouse() {
        return roomsOfHouse;
    }

    public static ArrayList<Profile> getProfilesOfHouse() {
        return profilesOfHouse;
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

    public double getDefaultTempIn() { return defaultTempIn; }

    public void setDefaultTempIn(double defaultTempIn) { this.defaultTempIn = defaultTempIn; }

    public double getTempIn(){ return this.tempIn;}

    public void setTempIn(double temp) { this.tempIn = temp; }

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
    public boolean isAwayMode() {
        return awayMode;
    }

    public void setAwayMode(boolean awayMode) {
        this.awayMode = awayMode;
    }

    public void addProfile(Profile profile) {
        profilesOfHouse.add(profile);
    }

    public double getDefaultSummerTemp() {
        return defaultSummerTemp;
    }

    public void setDefaultSummerTemp(double defaultSummerTemp) {
        this.defaultSummerTemp = defaultSummerTemp;
    }

    public double getDefaultWinterTemp() {
        return defaultWinterTemp;
    }

    public void setDefaultWinterTemp(double defaultWinterTemp) {
        this.defaultWinterTemp = defaultWinterTemp;
    }

    public ConsoleOutput getcOut(){
        return this.cOut;
    }

}
