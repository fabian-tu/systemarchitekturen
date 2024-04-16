package at.fhv.lab1.queryclient.projections;

import java.util.List;
import java.util.UUID;

public class FreeRoom {
    private UUID roomId;
    private int roomNumber;
    private int beds;
    private float pricePerNight;
    private List<AvailabilityInterval> availabilityIntervals;


    public FreeRoom(UUID roomId, int roomNumber, int beds, float pricePerNight, List<AvailabilityInterval> availabilityIntervals) {
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.beds = beds;
        this.pricePerNight = pricePerNight;
        this.availabilityIntervals = availabilityIntervals;
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

    public List<AvailabilityInterval> getAvailabilityIntervals() {
        return availabilityIntervals;
    }

    public void setAvailabilityIntervals(List<AvailabilityInterval> availabilityIntervals) {
        this.availabilityIntervals = availabilityIntervals;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + roomId +
                ", roomNumber=" + roomNumber +
                ", beds=" + beds +
                ", pricePerNight=" + pricePerNight +
                ", availabilityIntervals=" + availabilityIntervals +
                '}';
    }
}
