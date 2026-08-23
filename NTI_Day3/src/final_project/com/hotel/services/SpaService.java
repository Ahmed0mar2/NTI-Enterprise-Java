package final_project.com.hotel.services;

public class SpaService implements Service {
    @Override
    public double getCost() {
        return 100;
    }

    @Override
    public String getName() {
        return "Spa Service";
    }
}
