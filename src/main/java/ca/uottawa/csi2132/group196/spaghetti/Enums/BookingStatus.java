package ca.uottawa.csi2132.group196.spaghetti.Enums;

import java.util.Random;

// Do not rearrange values once database has been populated. Otherwise, the status of any existing bopkings will be messed up.
public enum BookingStatus {
    BOOKED("booked"),
    RENTED("rented"),
    COMPLETED("archived"),
    CANCELLED("cancelled");
    
    private final String value;
    private static final Random random = new Random();
    
    BookingStatus(String value) {
        this.value = value;        
    }
    
    public String getValue() {
        return value;
    }
    
    public static BookingStatus generateRandom() {
        return BookingStatus.values()[random.nextInt(BookingStatus.values().length)];
    }
}
