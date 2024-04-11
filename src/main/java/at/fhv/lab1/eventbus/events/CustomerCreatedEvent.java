package at.fhv.lab1.eventbus.events;

import java.util.Date;

public class CustomerCreatedEvent extends Event {
    private int id;
    private String name;
    private String address;
    private Date dateOfBirth;

    public CustomerCreatedEvent(int id, String name, String address, Date dateOfBirth) {
        this.name = name;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "type=" + getType() +
                ", id=" + id +
                ", name=" + name +
                ", address=" + address +
                ", dateOfBirth=" + dateOfBirth +
                ", timestamp=" + getTimestamp() +
                '}';
    }
}
