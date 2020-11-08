package smarthomesimulator.model;

import java.util.*;

public class Room {
	 	private String roomName;
	    private ArrayList<Door> doors = new ArrayList<>();
	    private ArrayList<Window> windows = new ArrayList<>();
	    private ArrayList<Light> lights = new ArrayList<>();
	    private int closedDoors;
	    private int closedWindows;
	    private int closedLights;
	    private int openedDoors = 0;
	    private int openedWindows = 0;
	    private int openedLights = 0;
	    
	    
	    public int getOpenLights() {
	    	return this.openedLights;
	    }
	    
	    public int getOpenDoors() {
	    	return this.openedDoors;
	    }
	    
	    public int getOpenWindows() {
	    	return this.openedWindows;
	    }
	    
	    public void setOpenLights(int lightsToOpen) {
	    	for(int i = 0; i < lightsToOpen ; i++){
	    		lights.get(i).turnOn();
	    	}
	    }
	    
	    public void setOpenDoors(int doorsToOpen) {
	    	for(int i = 0; i < doorsToOpen ; i++){
	    		doors.get(i).open();
			}
	    }
	    
	    public void setOpenWindows(int windowsToOpen) {
	    	for(int i = 0; i < windowsToOpen ; i++){
	    		windows.get(i).open();
			}
	    }
	    
	    public int getClosedLights() {
	    	return this.closedLights;
	    }
	    
	    public int getClosedDoors() {
	    	return this.closedDoors;
	    }
	    
	    public int getClosedWindows() {
	    	return this.closedWindows;
	    }
	    
	    public void setClosedLights(int lightsToClose) {
	    	for(int i = 0; i < lightsToClose ; i++){
	    		lights.get(i).turnOff();
	    	}
	    }
	    
	    public void seClosedDoors(int doorsToClose) {
	    	for(int i = 0; i < doorsToClose ; i++){
	    		doors.get(i).close();
			}
	    }
	    
	    public void setClosedWindows(int windowsToClose) {
	    	for(int i = 0; i < windowsToClose ; i++){
	    		windows.get(i).close();
			}
	    }


	    public Room(final String roomName, final int numOfDoors, final int numOfWindows, final int numOfLights ) {
	        this.roomName = roomName;
	        initDoors(numOfDoors);
	        initWindows(numOfWindows);
	        initLights(numOfLights);

	    }


		public void initDoors(int numOfDoors) {
			this.closedDoors = numOfDoors;
			
	    	for(int i = 0; i < numOfDoors ; i++){
	    		doors.add(i, new Door());
			}
		}

		public void initWindows(int numOfWindows) {
			this.closedWindows = numOfWindows;
			
			for(int i = 0; i < numOfWindows ; i++){
				windows.add(i, new Window());
			}
		}
		
		public void initLights(int numOfLights) {
			this.closedLights = numOfLights;
			
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

