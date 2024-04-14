package at.fhv.lab1.eventbus.events;

import java.util.Date;
import java.util.UUID;

public class RoomBookedEvent extends Event {
    private UUID bookingId;
    private UUID customerId;
    private UUID roomId;
    private Date startDate;
    private Date endDate;

    public RoomBookedEvent(UUID bookingId, UUID customerId, UUID roomId, Date startDate, Date endDate) {
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.roomId = roomId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public UUID getBookingId() {
        return bookingId;
    }

    public void setBookingId(UUID bookingId) {
        this.bookingId = bookingId;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public UUID getRoomId() {
        return roomId;
    }

    public void setRoomId(UUID roomId) {
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
