package at.fhv.lab1.eventbus.events;

import java.util.Date;

public class RoomBookedEvent extends Event {
    private int bookingId;
    private int customerId;
    private int roomId;
    private Date startDate;
    private Date endDate;

    public RoomBookedEvent(int bookingId, int customerId, int roomId, Date startDate, Date endDate) {
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.roomId = roomId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "RoomBookedEvent{" +
                "type=" + getType() +
                ", bookingId=" + bookingId +
                ", customerId=" + customerId +
                ", roomId=" + roomId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", timestamp=" + getTimestamp() +
                '}';
    }
}
