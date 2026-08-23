package final_project.com.hotel.management;

import final_project.com.hotel.models.Customer;
import final_project.com.hotel.models.Room;
import final_project.com.hotel.services.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hotel {
    private final HashMap<String, Room> rooms = new HashMap<>();
    private String hotelName;


    public void addRoom(Room room) {
        if (rooms.containsKey(room.getRoomNumber()))
            System.out.println("Room number already exists");
        else
            rooms.put(room.getRoomNumber(), room);
    }

    public Room getRoom(String roomNumber) {
        return rooms.get(roomNumber);
    }

    public void bookRoom(String roomNumber, Customer customer, Service... extras) {
        if (!rooms.containsKey(roomNumber))
            System.out.println("Room doesn't exist");
        else if (rooms.get(roomNumber).isBooked())
            System.out.println("Room is already booked");
        else {
            rooms.get(roomNumber).bookRoom(customer);
            for (Service extra : extras)
                rooms.get(roomNumber).addService(extra);
        }
    }

    public void displayBookingDetails(String roomNumber) {
        if (rooms.containsKey(roomNumber)) {
            rooms.get(roomNumber).getBookingDetails();
        } else
            System.out.println("Room doesn't exist");
    }

    public void displayAllRooms() {
        for (Map.Entry<String, Room> entry : rooms.entrySet()) {
            System.out.println("Room: " + entry.getValue().getRoomNumber() + "\n" +
                    "Base rate: " + entry.getValue().getBaseRate() + "\n" +
                    "Booked: " + entry.getValue().isBooked());
            entry.getValue().displayServices();
            System.out.println("Total cost: " + entry.getValue().getTotalCost());
        }
    }

    public List<Room> getAvailableRooms() {
        List<Room> availableRooms = new ArrayList<Room>();
        for (Map.Entry<String, Room> entry : rooms.entrySet()) {
            if (!entry.getValue().isBooked()) {
                availableRooms.add(entry.getValue());
            }
        }
        return availableRooms;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        if (hotelName == null || hotelName.isEmpty())
            System.out.println("Hotel name Cannot be empty");
        else
            this.hotelName = hotelName;
    }
}
