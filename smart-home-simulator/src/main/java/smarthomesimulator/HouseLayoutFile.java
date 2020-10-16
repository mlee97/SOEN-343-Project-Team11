package smarthomesimulator;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.*;
import java.util.ArrayList;

public class HouseLayoutFile {

	public static void LoadLayout (String fileName){
		if(fileName==null) {
			System.out.println("No file found");
		}
		else {
		ArrayList<Room> roomsOfHouse = new ArrayList<Room>();
		try {
			File file = new File(fileName); // creates a new file instance
			FileReader fr = new FileReader(file); // reads the file
			BufferedReader br = new BufferedReader(fr); // creates a buffering character input stream
			String line;
			
			while ((line = br.readLine()) != null) {
				Pattern patternRoomName = Pattern.compile("RoomName:(.*?),");
				Matcher matcherRoomName = patternRoomName.matcher(line);
				Pattern patternWindows = Pattern.compile("Windows:(.*?),");
				Matcher matcherWindows = patternWindows.matcher(line);
				Pattern patternDoors = Pattern.compile("Doors:(.*?),");
				Matcher matcherDoors = patternDoors.matcher(line);
				Pattern patternLights = Pattern.compile("Lights:(.*?);");
				Matcher matcherLights = patternLights.matcher(line);
				if (matcherRoomName.find()) {
					Room room = new Room(matcherRoomName.group(1),
							0,
							0,
							0
					);
					if(matcherWindows.find()) {
						room.setWindows((Integer.parseInt(matcherWindows.group(1))));
						
					}
					if(matcherDoors.find()) {
						room.setDoors((Integer.parseInt(matcherDoors.group(1))));
						
					}				
					if(matcherLights.find()) {
						room.setLights((Integer.parseInt(matcherLights.group(1))));
						
					}
					roomsOfHouse.add(room);
				
				}
				
			}
			System.out.println("Using For Loop\n ");
	          for (int i = 0; i < roomsOfHouse.size();i++) 
	          {
	              System.out.println(roomsOfHouse.get(i).getRoomName()+roomsOfHouse.get(i).getWindows()+roomsOfHouse.get(i).getDoors()+roomsOfHouse.get(i).getLights());
	              
	          }
			br.close();

		} catch (IOException e) {
			e.printStackTrace();

		}
		}
	}
}