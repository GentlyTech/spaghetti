package ca.uottawa.csi2132.group196.spaghetti.DataClasses;

public class RoomQueryResult {
    private Room room;

    private Hotel hotel;

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
    
    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
