package at.fhv.lab1.queryclient.queries;

import java.time.LocalDate;

public class GetFreeRoomsQuery {
    private LocalDate startDate;
    private LocalDate endDate;
    private int numberOfPersons;

    public GetFreeRoomsQuery(LocalDate startDate, LocalDate endDate, int numberOfPersons) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.numberOfPersons = numberOfPersons;
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

    public int getNumberOfPersons() {
        return numberOfPersons;
    }

    public void setNumberOfPersons(int numberOfPersons) {
        this.numberOfPersons = numberOfPersons;
    }

    @Override
    public String toString() {
        return "GetFreeRoomsQuery{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", numberOfPersons=" + numberOfPersons +
                '}';
    }
}
