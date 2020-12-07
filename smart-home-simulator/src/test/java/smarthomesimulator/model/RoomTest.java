package smarthomesimulator.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RoomTest {

    Room room = new Room("RoomName", 2, 3, 4);

    @Test
    void openCloseLightsTest() {
        room.setOpenLights(1);
        Assertions.assertEquals(room.getOpenLights(), 1);
        Assertions.assertEquals(room.getClosedLights(), 3);

        room.setClosedLights(4);
        Assertions.assertEquals(room.getOpenLights(), 0);
        Assertions.assertEquals(room.getClosedLights(), 4);
    }

    @Test
    void openCloseDoorsTest() {
        room.setOpenDoors(2);
        Assertions.assertEquals(room.getOpenDoors(), 2);
        Assertions.assertEquals(room.getClosedDoors(), 0);

        room.setClosedDoors(2);
        Assertions.assertEquals(room.getOpenDoors(), 0);
        Assertions.assertEquals(room.getClosedDoors(), 2);
    }

    @Test
    void openCloseWindowsTest() {
        room.setOpenWindows(1);
        Assertions.assertEquals(room.getOpenWindows(), 1);
        Assertions.assertEquals(room.getClosedWindows(), 2);

        room.setClosedWindows(3);
        Assertions.assertEquals(room.getOpenWindows(), 0);
        Assertions.assertEquals(room.getClosedWindows(), 3);
    }

    @Test
    void blockingDoorsTest() {
        room.setBlockedDoors(1);
        Assertions.assertEquals(room.getBlockedDoors(), 1);
    }

    @Test
    void blockingWindowsTest() {
        room.setBlockedWindows(1);
        Assertions.assertEquals(room.getBlockedWindows(), 1);
    }
}