package ca.uottawa.csi2132.group196.spaghetti.DataClasses;

public class HotelChain {

    private String chainName;
    private int hotelCount;

    public HotelChain() {

    }

    public HotelChain(String chainName, int hotelCount) {
        this.chainName = chainName;
        this.hotelCount = hotelCount;
    }

    public String getChainName() {
        return chainName;
    }

    public int getHotelCount() {
        return hotelCount;
    }
}