package final_project.com.hotel.models;

public class SuiteRoom extends Room {

    public SuiteRoom(String roomNumber, double baseRate) {
        super(roomNumber, baseRate);
    }

    @Override
    public String getDescription() {
        return "Suite Room";
    }
}
