package smarthomesimulator.model;

import java.util.*;

public class Room {
	 	private String roomName;
	    private List<Door> doors = new ArrayList<>();
	    private List<Window> windows = new ArrayList<>();
	    private List<Light> lights = new ArrayList<>();
		private int blockedWindows;
		private int blockedDoors;
	    
	    
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
	    		if(lights.get(i).isOn()) {
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

		public void setBlockedWindows(int windowsToBlock) {
			for(int i = 0; i < windowsToBlock ; i++){
	    		windows.get(i).block();
			}
			
		}
		
		public String Status() {
			String status = "There are currently ";
			String blockStatus = this.getBlockedDoors()+" blocked doors and "+this.getBlockedWindows()+" blocked windows, ";
			String closeStatus = this.getClosedDoors()+" closed doors, "+this.getClosedWindows()
			+" closed windows, and "+this.getClosedLights()+" closed lights.";
			
			return status+blockStatus+closeStatus;
		}
}

