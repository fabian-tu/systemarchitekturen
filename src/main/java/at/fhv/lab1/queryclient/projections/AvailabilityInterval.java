package at.fhv.lab1.queryclient.projections;

import java.time.LocalDate;

public class AvailabilityInterval {
    private LocalDate availableFrom;
    private LocalDate availableTo;

    public AvailabilityInterval(LocalDate availableFrom, LocalDate availableTo) {
        this.availableFrom = availableFrom;
        this.availableTo = availableTo;
    }

    public LocalDate getAvailableFrom() {
        return availableFrom;
    }

    public void setAvailableFrom(LocalDate availableFrom) {
        this.availableFrom = availableFrom;
    }

    public LocalDate getAvailableTo() {
        return availableTo;
    }

    public void setAvailableTo(LocalDate availableTo) {
        this.availableTo = availableTo;
    }

    @Override
    public String toString() {
        return "AvailabilityInterval{" +
                "availableFrom=" + availableFrom +
                ", availableTo=" + availableTo +
                '}';
    }
}
