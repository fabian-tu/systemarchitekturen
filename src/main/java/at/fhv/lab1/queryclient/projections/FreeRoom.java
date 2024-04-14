package at.fhv.lab1.queryclient.projections;

import java.util.Date;
import java.util.UUID;

public class FreeRoom {
    private UUID roomId;
    private int roomNumber;
    private int beds;
    private float pricePerNight;
    private Date availableFrom;
    private Date availableTo;

    public FreeRoom(UUID roomId, int roomNumber, int beds, float pricePerNight, Date availableFrom, Date availableTo) {
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.beds = beds;
        this.pricePerNight = pricePerNight;
        this.availableFrom = availableFrom;
        this.availableTo = availableTo;
    }

    public UUID getRoomId() {
        return roomId;
    }

    public void setRoomId(UUID roomId) {
        this.roomId = roomId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getBeds() {
        return beds;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }

    public float getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(float pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public Date getAvailableFrom() {
        return availableFrom;
    }

    public void setAvailableFrom(Date availableFrom) {
        this.availableFrom = availableFrom;
    }

    public Date getAvailableTo() {
        return availableTo;
    }

    public void setAvailableTo(Date availableTo) {
        this.availableTo = availableTo;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + roomId +
                ", roomNumber=" + roomNumber +
                ", beds=" + beds +
                ", pricePerNight=" + pricePerNight +
                ", availableFrom=" + availableFrom +
                ", availableTo=" + availableTo +
                '}';
    }
}
