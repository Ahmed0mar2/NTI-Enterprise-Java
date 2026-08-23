package final_project.com.hotel.models;

import final_project.com.hotel.services.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Room {
    private String roomNumber;
    private double baseRate;
    private final List<Service> services;
    private boolean isBooked;
    private Customer customer;

    public Room(String roomNumber, double baseRate) {
        if (roomNumber == null || roomNumber.isEmpty())
            System.out.println("Room number cannot be empty");
        else
            this.roomNumber = roomNumber;
        if (baseRate > 0)
            this.baseRate = baseRate;
        else
            System.out.println("Base rate must be greater than zero");
        this.services = new ArrayList<>();
        this.isBooked = false;
        this.customer = null;
    }

    public abstract String getDescription();

    public void addService(Service service) {
        if (isBooked) {
            services.add(service);
            System.out.println("Service: " + service.getName() + " added successfully!");
        } else
            System.out.println("Cannot add service room is not booked");
    }

    public void addMultipleServices(Service... service) {
        if (!isBooked)
            services.addAll(Arrays.asList(service));
        else
            System.out.println("Room must be booked to add services");
    }

    public double getTotalCost() {
        double totalServicesCost = 0;
        for (Service service : services) {
            totalServicesCost += service.getCost();
        }
        return baseRate + totalServicesCost;
    }

    public void bookRoom(Customer customer) {
        if (!isBooked) {
            this.customer = customer;
            isBooked = true;
        } else
            System.out.println("Room is already booked");
    }

    public void getBookingDetails() {
        System.out.println("----------- Booking Details ----------");
        System.out.println("Booking for " + getDescription() + " - Room " + roomNumber);
        System.out.println("Customer: " + customer.toString());
        displayServices();
        System.out.println("Total Cost: " + getTotalCost());
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public double getBaseRate() {
        return baseRate;
    }

    public List<Service> getServices() {
        return new ArrayList<>(services);
    }

    public boolean isBooked() {
        return isBooked;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void displayServices() {
        double total = 0;
        System.out.println("Services:");
        for (Service service : services) {
            System.out.println("\t - " + service.getName() + ": $" + service.getCost());
            total += service.getCost();
        }
        System.out.println("Total services cost: $" + total);
    }
}
