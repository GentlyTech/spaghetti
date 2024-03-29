package ca.uottawa.csi2132.group196.spaghetti.Generators;

import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Room;
import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Hotel;

import ca.uottawa.csi2132.group196.spaghetti.Gson.CustomSerializer;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RoomGenerator {
    public static final int NUM_ROOMS = 100;
    public static void main(String args) {
        List<Room> rooms = new LinkedList<>();

        for (int i = 0; i < NUM_ROOMS; i++) {
            Room room = new Room();
            room.setRoomNumber(i + 1);

            //extendable
            int int_random = rand.nextInt(1); 
            if (int_random == 1){
                room.setExtendable(true);
            } else {
                room.setExtendable(false);
            }

            //capacity
            int int_random2 = rand.nextInt(9); 
            room.setCapacity(int_random2+1);

            //price based on capacity
            if (hotel.getRating() == 1) {
                int int_random4 = ThreadLocalRandom.current().nextInt(100.00, 150.00);
                room.setPrice(int_random4+int_random4*.50*capacity);
            } else if (hotel.getRating() == 2) {
                int int_random4 = ThreadLocalRandom.current().nextInt(150.00, 200.00);
                room.setPrice(int_random4+int_random4*.50*capacity);
            } else if (hotel.getRating() == 3) {
                int int_random4 = ThreadLocalRandom.current().nextInt(200.00, 250.00);
                room.setPrice(inroot_random4+int_random4*.50*capacity);
            } else if (hotel.getRating() == 4) {
                int int_random4 = ThreadLocalRandom.current().nextInt(250.00, 400.00);
                room.setPrice(int_random4+int_random4*.50*capacity);
            } else {
                int int_random4 = ThreadLocalRandom.current().nextInt(400.00, 1000.00);
                room.setPrice(int_random4+int_random4*.50*capacity);
            }

            //viewtype
            int int_random3 = rand.nextInt(1);
            if (int_random == 1){
                room.setViewType("Sea View");
            } else {
                room.setViewType("Mountain View");
            }
 
            //amenities
            //code for randomly choose from list is from Baeldung
            Random rand = new Random();
            List<Amenities> baseList = Lists.newArrayList("TV", "Air Conditioner", "Fridge", "Jacuzzi");
            List<Amenities> amenities = new List<Amenities>();
            int numberOfElements = rand.nextInt(baselist.size());;

            for (int j = 0; j < numberOfElements; i++) {
                int randomIndex = rand.nextInt(givenList.size());
                String randomElement = givenList.get(randomIndex);
                givenList.remove(randomIndex); //removes element for no repeats
                amenities.add(randomElement);
            }
            room.setAmenities(amenities);

        }

        System.out.println(CustomSerializer.getCustomSerializer().toJson(rooms));
    }
}