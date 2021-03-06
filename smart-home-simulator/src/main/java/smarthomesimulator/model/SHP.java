package smarthomesimulator.model;

import smarthomesimulator.interfaces.Observable;
import smarthomesimulator.interfaces.Observer;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class SHP implements Observable {

    private ArrayList<Observer> observers = new ArrayList<>();
    private boolean away;
    private String startTime;
    private String endTime;
    private int alertTime;
    private Room shpRoom;
    private boolean lightsSHP;

    public SHP() {
        this.away = false;
        this.endTime = getCurrentTime();
        this.startTime = getCurrentTime();
    }

    public boolean getStatus() {
        return this.away;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public Room getShpRoom() {
        return this.shpRoom;
    }

    public int getAlertTime(){ return this.alertTime;}

    public boolean getLightsSHP() {
        return this.lightsSHP;
    }

    public void setAway(boolean status) {
        this.away = status;
        notifyObserver(this);
    }

    public void setStartTime(String time) {
        this.startTime = time;
    }

    public void setEndTime(String time) {
        this.endTime = time;
    }

    public void setAlertTime(int time){ this.alertTime = time;}

    public void setLightsSHP(boolean status) {
        this.lightsSHP = status;
    }

    public void setShpRoom(Room room) {
        this.shpRoom = room;
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

    @Override
    public void attachObserver(Observer o){
        observers.add(o);
    }
    @Override
    public void detachObserver(Observer o){
        observers.remove(o);
    }
    @Override
    public void notifyObserver(Observable o){
        for (Observer observer : observers){
            observer.update(o);
        }
    }

    public boolean isAway() {
        return away;
    }
}
