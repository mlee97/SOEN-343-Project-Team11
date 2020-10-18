package smarthomesimulator.model;

import java.util.ArrayList;

/**
 *
 */
public class Simulator {

    private int date;
    private int time;
    
    public static ArrayList<Room> roomsOfHouse = new ArrayList<Room>();

    public Simulator() {
        super();
    }

    public void setDate(int date) {
        this.date = date;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getDate() {
        return date;
    }

    public int getTime() {
        return time;
    }
}
