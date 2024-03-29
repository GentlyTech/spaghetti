package ca.uottawa.csi2132.group196.spaghetti.Generators;

import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Amenity;
import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Hotel;
import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Room;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RoomGenerator {
    private final Hotel hotel;
    private final Amenity amenities;

    public RoomGenerator(Hotel hotel) {
        this.hotel = hotel;
        this.amenities = null;
    }

    public RoomGenerator(Hotel hotel, Amenity amenities) {
        this.hotel = hotel;
        this.amenities = amenities;
    }

    public List<Room> generateRooms(int numRooms) {
        Random rand = new Random();
        List<Room> rooms = new LinkedList<>();

        for (int i = 0; i < numRooms; i++) {
            Room room = new Room();
            room.setRoomNumber(i + 1);

            //extendable
            int int_random = rand.nextInt(2);
            room.setExtendable(int_random == 1);

            //capacity
            int capacity = rand.nextInt(10);
            room.setCapacity(capacity);

            //price based on capacity
            if (hotel.getRating() == 1) {
                int int_random4 = ThreadLocalRandom.current().nextInt(100.00, 150.00);
                room.setPrice(int_random4 + int_random4 * .50 * capacity);
            } else if (hotel.getRating() == 2) {
                int int_random4 = ThreadLocalRandom.current().nextInt(150.00, 200.00);
                room.setPrice(int_random4 + int_random4 * .50 * capacity);
            } else if (hotel.getRating() == 3) {
                int int_random4 = ThreadLocalRandom.current().nextInt(200.00, 250.00);
                room.setPrice(inroot_random4 + int_random4 * .50 * capacity);
            } else if (hotel.getRating() == 4) {
                int int_random4 = ThreadLocalRandom.current().nextInt(250.00, 400.00);
                room.setPrice(int_random4 + int_random4 * .50 * capacity);
            } else {
                int int_random4 = ThreadLocalRandom.current().nextInt(400.00, 1000.00);
                room.setPrice(int_random4 + int_random4 * .50 * capacity);
            }

            //viewtype
            int int_random3 = rand.nextInt(2);
            if (int_random == 1) {
                room.setViewType("Sea View");
            } else {
                room.setViewType("Mountain View");
            }

            if (amenities == null) return rooms;

            //amenities
            room.setAmenities(null); // TODO pick amenities

            return rooms;
        }
    }
}