package final_project.com.hotel.services;

public class GymService implements Service {
    @Override
    public double getCost() {
        return 30;
    }

    @Override
    public String getName() {
        return "Gym Service";
    }
}
