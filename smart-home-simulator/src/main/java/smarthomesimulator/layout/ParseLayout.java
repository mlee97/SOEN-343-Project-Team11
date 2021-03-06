package smarthomesimulator.layout;

import smarthomesimulator.model.Room;
import smarthomesimulator.model.Simulator;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseLayout {

    public static void parse(String fileName) throws IOException {

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

            matchRooms(matcherRoomName, matcherDoors, matcherWindows, matcherLights);
        }
        br.close();
    }

    private static void matchRooms(Matcher matcherRoomName, Matcher matcherDoors, Matcher matcherWindows, Matcher matcherLights) {
        if (matcherRoomName.find()) {
            int numOfDoors = 0;
            int numOfWindows = 0;
            int numOfLights = 0;

            String roomName = matcherRoomName.group(1);

            if (matcherDoors.find()) {
                numOfDoors = (Integer.parseInt(matcherDoors.group(1)));
            }

            if (matcherWindows.find()) {
                numOfWindows = (Integer.parseInt(matcherWindows.group(1)));
            }

            if (matcherLights.find()) {
                numOfLights = ((Integer.parseInt(matcherLights.group(1))));
            }

            Room room = new Room(roomName, numOfDoors, numOfWindows,numOfLights);
            Simulator.roomsOfHouse.add(room);
        }
    }


}
