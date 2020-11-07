package smarthomesimulator.model;

import java.util.*;

public class Room {
	 	private String roomName;
	    private ArrayList<Door> doors = new ArrayList<>();
	    private ArrayList<Window> windows = new ArrayList<>();
	    private ArrayList<Light> lights = new ArrayList<>();

	    public Room(final String roomName, final int numOfDoors, final int numOfWindows, final int numOfLights ) {
	        this.roomName = roomName;
	        initDoors(numOfDoors);
	        initWindows(numOfWindows);
	        initLights(numOfLights);

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
		
		public void initLights(int numOfLights) {
			for(int i = 0; i < numOfLights ; i++){
				lights.add(i, new Light());
			}
		}

	    public void setRoomName(String roomName) { this.roomName=roomName; }
	    
	    public String getRoomName() { return this.roomName; }
	     
	    public boolean canEnter() {
	    	
	    	boolean canEnter = false;
	    	
	    	for(int i=0; i>=doors.size(); i++) {
	    		if(canEnter == true) {
	    			break;
	    		}
	    		if(doors.get(i).isOpen()) {
	    			canEnter = true;
	    		}
	    	}
	    	
	    	return canEnter;
			
	    }
	    
	    public boolean isWindy() {
	    	boolean windy = false;
	    	
	    	for(int i=0; i>=windows.size(); i++) {
	    		if(windy == true) {
	    			break;
	    		}
	    		if(windows.get(i).isOpen()) {
	    			windy = true;
	    		}
	    	}
	    	return windy;
	    }
	    
	    public boolean isBright() {
	    	
	    	boolean bright = false;
	    	
	    	for(int i=0; i>=lights.size(); i++) {
	    		if(bright == true) {
	    			break;
	    		}
	    		if(lights.get(i).OnOff()) {
	    			bright = true;
	    		}
	    	}
	    	
	    	return bright; 
	    }
	    
	    public boolean findDoors() {
	    	if (doors.size() == 0) {
	    		return false;
	    	}else
	    		return true;
	    }
	    
	    public boolean findLights() {
	    	if (lights.size() == 0) {
	    		return false;
	    	}else
	    		return true;
	    }
	    
	    public boolean findWindows() {
	    	if (windows.size() == 0) {
	    		return false;
	    	}else
	    		return true;
	    }
	    
	    public ArrayList<Window> getWindows() {
	    	return this.windows;
	    }
	    public ArrayList<Door> getDoors() {
	    	return this.doors;
	    }
	    public ArrayList<Light> getLights() {
	    	return this.lights;
	    }
	    
	    
	}

