package final_project.com.hotel.models;

public class DeluxeRoom extends Room {

    public DeluxeRoom(String roomNumber, double baseRate) {
        super(roomNumber, baseRate);
    }

    @Override
    public String getDescription() {
        return "Deluxe Room";
    }
}
