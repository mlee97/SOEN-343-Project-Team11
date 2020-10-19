package smarthomesimulator.model;

import java.util.*;

public class Room {
	 	private String roomName;
	    private ArrayList<Door> doors = new ArrayList<>();
	    private ArrayList<Window> windows = new ArrayList<>();
	    private int numOfLights;
	    public Room() {
	     
	    }

	    public Room(final String roomName, final int numOfDoors, final int numOfWindows, final int numOfLights ) {
	        this.roomName = roomName;
	        initDoors(numOfDoors);
	        initWindows(numOfWindows);
	        this.numOfLights = numOfLights;

	    }

	    public void initDoors(int numOfDoors) {
	    	for(int i = 0; i < numOfDoors ; i++){
	    		doors.add(i, new Door());
			}
		}

		public void initWindows(int numOfWindows) {
			for(int i = 0; i < numOfWindows ; i++){
				windows.add(i, new Window());
			}
		}

	    public void setRoomName(String roomName) { this.roomName=roomName; }
	    public void setLights(int lights) { this.numOfLights=lights; }
	    
	    public String getRoomName() { return this.roomName; }
	    public int getLights() { return this.numOfLights; }
	    
	}

