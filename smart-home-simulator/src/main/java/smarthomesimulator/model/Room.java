package smarthomesimulator.model;

public class Room {
	 	private String roomName;
	    private int doors;
	    private int windows;
	    private int lights;
	    public Room() {
	     
	    }

	    public Room(final String roomName, final int doors, final int windows, final int lights ) {
	        this.roomName = roomName;
	        this.doors = doors;
	        this.windows = windows;
	        this.lights = lights;

	    }

	    public void setRoomName(String roomName) { this.roomName=roomName; }
	    public void setDoors(int doors) { this.doors=doors; }
	    public void setWindows(int windows) { this.windows=windows; }
	    public void setLights(int lights) { this.lights=lights; }
	    
	    public String getRoomName() { return this.roomName; }
	    public int getDoors() { return this.doors; }
	    public int getWindows() { return this.windows; }
	    public int getLights() { return this.lights; }
	    
	}

