package smarthomesimulator.model;

import org.springframework.lang.Nullable;
import smarthomesimulator.interfaces.Observable;

import java.util.*;

public class Room {
		private String roomName;
		private final List<Door> doors = new ArrayList<>();
	    private final List<Window> windows = new ArrayList<>();
		private final List<Light> lights = new ArrayList<>();
		private int blockedWindows;
		private int blockedDoors;
		private double temperature;
		private boolean overridden;
		private Zone zone;
	    
	    public int getOpenLights() {
	    	int count = 0;
	    	
	    	for(int i = 0; i < lights.size() ; i++){
	    		if(lights.get(i).isOn()) {
	    			count++;
	    	}
	    }
	    	return count;
	    }
	    
	    public int getOpenDoors() {
	    	
	    	int count = 0;
	    	
	    	for(int i = 0; i < doors.size() ; i++){
	    		if(doors.get(i).isOpen()) {
	    			count++;
	    	}
	    }
	    	return count;
	    }
	    
	    public int getOpenWindows() {
	    	
	    	int count = 0;
	    	
	    	for(int i = 0; i < windows.size() ; i++){
	    		if(windows.get(i).isOpen()) {
	    			count++;
	    	}
	    }
	    	return count;
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
	    	int count = 0;
	    	
	    	for(int i = 0; i < lights.size() ; i++){
	    		if(!lights.get(i).isOn()) {
	    			count++;
	    	}
	    }

	    	return count;
	    }
	    
	    public int getClosedDoors() {
	    	int count = 0;
	    	
	    	for(int i = 0; i < doors.size() ; i++){
	    		if(!doors.get(i).isOpen()) {
	    			count++;
	    	}
	    }
	    	return count;
	    }
	    
	    public int getClosedWindows() {
	    	int count = 0;
	    	
	    	for(int i = 0; i < windows.size() ; i++){
	    		if(!windows.get(i).isOpen()) {
	    			count++;
	    	}
	    }
	    	return count;
	    }
	    
	    public void setClosedLights(int lightsToClose) {
	    	for(int i = 0; i < lightsToClose ; i++){
	    		lights.get(i).turnOff();
	    	}
	    }
	    
	    public void setClosedDoors(int doorsToClose) {
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
	        overridden = false;
			temperature = 0;
			zone = null;
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
	    
	    public List<Window> getWindows() {
	    	return this.windows;
	    }
	    public List<Door> getDoors() {
	    	return this.doors;
	    }
	    public List<Light> getLights() {
	    	return this.lights;
	    }

		public int getBlockedDoors() {
			int count = 0;
	    	
	    	for(int i = 0; i < doors.size() ; i++){
	    		if(doors.get(i).isBlocked()) {
	    			count++;
	    	}
	    }
	    	this.blockedDoors = count;
	    	
	    	return this.blockedDoors;
		}

		public int getBlockedWindows() {
			int count = 0;
	    	
	    	for(int i = 0; i < windows.size() ; i++){
	    		if(windows.get(i).isBlocked()) {
	    			count++;
	    	}
	    }
	    	this.blockedWindows = count;
	    	
	    	return this.blockedWindows;
		}

		public void setBlockedDoors(int doorsToBlock) {
			for(int i = 0; i < doorsToBlock ; i++){
	    		doors.get(i).block();
			}
			
			
		}
		
		public void setUnblockedDoors(int doorsToUnblock) {
			for (int i = 0; i < doorsToUnblock; i++) {
				doors.get(i).unblock();
			}
		}

		public void setBlockedWindows(int windowsToBlock) {
			for(int i = 0; i < windowsToBlock ; i++){
	    		windows.get(i).block();
			}
			
		}

		public void setUnblockedWindows(int windowsToUnblock) {
			for (int i = 0; i < windowsToUnblock; i++) {
				windows.get(i).unblock();
			}
		}

	public double getTemperature(){
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public Zone getZone() {
		return zone;
	}

	public void setZone(@Nullable Zone zone) {
		this.zone = zone;
	}

	public boolean isOverridden() {
		return overridden;
	}

	public void setOverridden(boolean overridden) {
		this.overridden = overridden;
	}
}

