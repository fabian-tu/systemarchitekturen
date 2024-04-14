package at.fhv.lab1.eventbus.events;

import java.util.Date;
import java.util.UUID;

public class CustomerCreatedEvent extends Event {
    private UUID customerId;
    private String name;
    private String address;
    private Date dateOfBirth;

    public CustomerCreatedEvent(UUID customerId, String name, String address, Date dateOfBirth) {
        this.customerId = customerId;
        this.name = name;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerIdId(UUID customerId) {
        this.customerId = customerId;
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
                ", customerId=" + customerId +
                ", name=" + name +
                ", address=" + address +
                ", dateOfBirth=" + dateOfBirth +
                ", timestamp=" + getTimestamp() +
                '}';
    }
}
