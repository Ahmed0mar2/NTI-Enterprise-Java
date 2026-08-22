package weekend_project;

public class Dog extends Animal {
    private String breed;
    public Dog(String name, int age, double weight, boolean isHealthy, String breed){
        super(name,age,"Dog",weight,isHealthy);
        setBreed(breed);
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        if(!breed.isEmpty())
            this.breed = breed;
        else
            System.out.println("Breed must not be empty");
    }

    @Override
    void makeSound() {
        System.out.println(getName() + " says: Woof! Woof!");
    }

    @Override
    void move() {
        System.out.println(getName() + " is running");
    }

    @Override
    void displayInfo() {
        super.displayInfo();
        System.out.println("Breed: " + breed + "\n" );
    }
    void bark(){
        System.out.println(getName() + " is barking loudly!");
    }
    void fetch(){
        System.out.println(getName() + " is fetching the ball");
    }
    void wagTail(){
        System.out.println(getName() + " is wagging tail happily!");
    }
}
