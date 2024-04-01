package ca.uottawa.csi2132.group196.spaghetti.Generators;

import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Amenity;
import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Hotel;
import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Room;

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

        // base price based on rating
        double basePrice;
        switch (hotel.getRating()) {
            case 1:
                basePrice = rand.nextInt(100, 150);
                break;
            case 2:
                basePrice = rand.nextInt(150, 200);
                break;
            default:
            case 3:
                basePrice = rand.nextInt(200, 250);
                break;
            case 4:
                basePrice = rand.nextInt(250, 400);
                break;
            case 5:
                basePrice = rand.nextInt(400, 1000);
                break;
        }

        for (int i = 0; i < numRooms; i++) {
            Room room = new Room();
            // primary keys
            room.setHotelId(hotel.getHotelId());
            room.setRoomNumber(i + 1);

            // extendable
            int extendable = rand.nextInt(2);
            room.setExtendable(extendable == 1);

            // capacity
            int capacity = rand.nextInt(1, 10);
            room.setCapacity(capacity);

            // also base price based on capacity now (each additional person increases price by 40%)
            room.setPrice(basePrice + basePrice * (capacity - 1) * 0.4);

            //viewtype
            int viewType = rand.nextInt(2);
            if (viewType == 1) {
                room.setViewType("Sea View");
            } else {
                room.setViewType("Mountain View");
            }

            if (amenities == null) {
                rooms.add(room);
                continue;
            }

            //amenities
            room.setAmenities(new AmenityGenerator(amenities).generateAmenities());

            rooms.add(room);
        }

        return rooms;
    }
}