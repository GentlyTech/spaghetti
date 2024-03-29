package ca.uottawa.csi2132.group196.spaghetti.Generators;

import ca.uottawa.csi2132.group196.spaghetti.DataClasses.Amenity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AmenityGenerator {
    private final List<Amenity> amenities;
    private final Random random = new Random();

    public AmenityGenerator(List<Amenity> amenities) {
        this.amenities = amenities;
    }

    public List<Amenity> generateAmenities(int numAmenities) {
        List<Amenity> chosenAmenities = new ArrayList<>(amenities);

        for (int j = 0; j < (amenities.size() - numAmenities); j++) {
            int index = random.nextInt(chosenAmenities.size());
            chosenAmenities.remove(index);
        }
        return chosenAmenities;
    }
}
