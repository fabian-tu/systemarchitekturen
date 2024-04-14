package at.fhv.lab1.eventbus.events;

import java.time.LocalDate;
import java.util.UUID;

public class RoomBookedEvent extends Event {
    private UUID bookingId;
    private UUID customerId;
    private UUID roomId;
    private LocalDate startDate;
    private LocalDate endDate;

    public RoomBookedEvent(UUID bookingId, UUID customerId, UUID roomId, LocalDate startDate, LocalDate endDate) {
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
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
