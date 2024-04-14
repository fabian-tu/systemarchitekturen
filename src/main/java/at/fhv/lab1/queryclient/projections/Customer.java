package at.fhv.lab1.queryclient.projections;

import java.time.LocalDate;
import java.util.UUID;

public class Customer {
    private UUID customerId;
    private String name;
    private String address;
    private LocalDate dateOfBirth;

    public Customer(UUID customerId, String name, String address, LocalDate dateOfBirth) {
        this.customerId = customerId;
        this.name = name;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
