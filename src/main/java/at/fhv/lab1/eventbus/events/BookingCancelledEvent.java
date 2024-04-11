package at.fhv.lab1.eventbus.events;

import java.util.Date;

public class BookingCancelledEvent extends Event {
    private int bookingId;
    private Date startDate;
    private Date endDate;

    public BookingCancelledEvent(int bookingId, Date startDate, Date endDate) {
        this.bookingId = bookingId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
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
        return "BookingCancelledEvent{" +
                "type=" + getType() +
                ", bookingId=" + bookingId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", timestamp=" + getTimestamp() +
                '}';
    }
}
