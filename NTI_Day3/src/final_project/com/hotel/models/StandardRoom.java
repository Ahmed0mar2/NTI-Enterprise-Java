package final_project.com.hotel.models;

public class StandardRoom extends Room {


    public StandardRoom(String roomNumber, double baseRate) {
        super(roomNumber, baseRate);
    }

    @Override
    public String getDescription() {
        return "Standard Room";
    }
}
