package ca.uottawa.csi2132.group196.spaghetti.Generators;

import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Amenity;
import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Hotel;
import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Room;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class RoomGenerator {
    private final Hotel hotel;
    private final List<Amenity> amenities;

    public RoomGenerator(Hotel hotel) {
        this.hotel = hotel;

        if (hotel != null)
            this.amenities = hotel.getAmenities();
        else
            this.amenities = null;
    }

    public RoomGenerator(Hotel hotel, List<Amenity> amenities) {
        this.hotel = hotel;
        this.amenities = amenities;
    }

    public List<Room> generateRooms(int numRooms) {
        Random rand = new Random();
        List<Room> rooms = new LinkedList<>();

        for (int i = 0; i < numRooms; i++) {
            Room room = new Room();
            // primary keys
            room.setHotelId(hotel.getHotelId());
            room.setRoomNumber(i + 1);

            // extendable
            int int_random = rand.nextInt(2);
            room.setExtendable(int_random == 1);

            // capacity
            int capacity = rand.nextInt(10);
            room.setCapacity(capacity);

            // price based on capacity
            double price;
            switch (hotel.getRating()) {
                case 1:
                    price = rand.nextInt(100, 150);
                    break;
                case 2:
                    price = rand.nextInt(150, 200);
                    break;
                default:
                case 3:
                    price = rand.nextInt(200, 250);
                    break;
                case 4:
                    price = rand.nextInt(250, 400);
                    break;
                case 5:
                    price = rand.nextInt(400, 1000);
                    break;
            }

            room.setPrice(price);

            //viewtype
            int int_random3 = rand.nextInt(2);
            if (int_random == 1) {
                room.setViewType("Sea View");
            } else {
                room.setViewType("Mountain View");
            }

            if (amenities == null) {
                rooms.add(room);
                continue;
            }

            //amenities
            int numAmenities = rand.nextInt(amenities.size());
            room.setAmenities(new AmenityGenerator(amenities).generateAmenities(numAmenities));

            rooms.add(room);
        }

        return rooms;
    }
}