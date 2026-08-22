package weekend_project;

public class Bird extends Animal {
    private double wingSpan;
    private boolean canFly;

    public Bird(String name, int age, double weight, boolean isHealthy, double wingSpan, boolean canFly) {
        super(name, age, "Bird", weight, isHealthy);
        setWingSpan(wingSpan);
        setCanFly(canFly);
    }

    public double getWingSpan() {
        return wingSpan;
    }

    public boolean isCanFly() {
        return canFly;
    }

    public void setWingSpan(double wingSpan) {
        if (wingSpan > 0)
            this.wingSpan = wingSpan;
        else
            System.out.println("Wing span must be greater than zero");
    }

    public void setCanFly(boolean canFly) {
        this.canFly = canFly;
    }

    @Override
    void makeSound() {
        System.out.println(getName() + " says: Tweet! Tweet!");
    }

    @Override
    void move() {
        if (canFly)
            System.out.println(getName() + " is flying");
        else
            System.out.println(getName() + " is hopping");
    }

    @Override
    void displayInfo() {
        super.displayInfo();
        System.out.println("Wing span: " + wingSpan + "\n" +
                "Can fly: " + canFly + "\n");
    }

    void chirp() {
        System.out.println(getName() + " is chirping melodiously");
    }

    void fly() {
        if (canFly)
            System.out.println(getName() + " is soaring in the sky");
        else
            System.out.println(getName() + " cannot fly");
    }

    void buildNest() {
        System.out.println(getName() + " is building a nest");
    }
}
