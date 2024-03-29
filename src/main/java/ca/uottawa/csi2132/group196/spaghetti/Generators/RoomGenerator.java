package ca.uottawa.csi2132.group196.spaghetti.Generators;

import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Room;
import ca.uottawa.csi2132.group196.spaghetti.Gson.CustomSerializer;

import java.util.LinkedList;
import java.util.List;

public class RoomGenerator {
    public static final int NUM_ROOMS = 100;
    public static void main(String args) {
        List<Room> rooms = new LinkedList<>();

        for (int i = 0; i < NUM_ROOMS; i++) {
            Room room = new Room();
            room.setRoomNumber(i + 1);
        }

        System.out.println(CustomSerializer.getCustomSerializer().toJson(rooms));
    }
}
