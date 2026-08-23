package final_project.com.hotel;

import final_project.com.hotel.management.Hotel;
import final_project.com.hotel.models.*;
import final_project.com.hotel.services.DiningService;
import final_project.com.hotel.services.GymService;
import final_project.com.hotel.services.SpaService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Hotel nileHotel = new Hotel();
        nileHotel.setHotelName("Nile Hotel");

        // Rooms
        nileHotel.addRoom(new StandardRoom("201", 158.00));
        nileHotel.addRoom(new SuiteRoom("202", 400.00));
        nileHotel.addRoom(new DeluxeRoom("403", 280.00));
        nileHotel.addRoom(new StandardRoom("801", 160.00));

        // Customers
        Customer c1 = new Customer("c1", "c1@email.com", "01111111111");
        Customer c2 = new Customer("c2", "c2@email.com", "02222222222");
        Customer c3 = new Customer("c3", "c3@email.com", "03333333333");
        Customer c4 = new Customer("c4", "c4email.com", "04444444444"); // no @

        // Bookings
        nileHotel.bookRoom("403", c1, new DiningService(), new GymService(), new SpaService());
        nileHotel.bookRoom("201", c1, new DiningService());
        nileHotel.bookRoom("201", c1, new SpaService()); // duplicated
        nileHotel.bookRoom("202", c1, new SpaService());

        // Details
        nileHotel.displayBookingDetails("403");
        System.out.println("=============================");
        nileHotel.displayBookingDetails("201");
        System.out.println("=============================");
        System.out.println(nileHotel.getRoom("201").isBooked());
        System.out.println("=============================");
        nileHotel.displayAllRooms();
        System.out.println("=============================");
        List<Room> rooms = nileHotel.getAvailableRooms();
        System.out.println("======== Available rooms ======");
        for (Room room : rooms) {
            System.out.println("Room: " + room.getRoomNumber() + "\n" +
                    "Base rate: " + room.getBaseRate() + "\n" +
                    "Booked: " + room.isBooked());
            room.displayServices();
            System.out.println("Total cost: " + room.getTotalCost());
        }

    }
}
