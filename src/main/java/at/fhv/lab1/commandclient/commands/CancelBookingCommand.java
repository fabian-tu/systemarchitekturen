package at.fhv.lab1.commandclient.commands;

public class CancelBookingCommand {
    private int bookingId;

    public CancelBookingCommand(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    @Override
    public String toString() {
        return "CancelBookingCommand{" +
                "bookingId=" + bookingId +
                '}';
    }
}
