package weekend_project;

public class Cat extends Animal{

    private String furColor;
    private boolean isIndoor;

    public Cat(String name, int age, double weight, boolean isHealthy, String furColor, boolean isIndoor){
        super(name,age,"Cat",weight,isHealthy);
        setFurColor(furColor);
        setIndoor(isIndoor);
    }

    public String getFurColor() {
        return furColor;
    }

    public boolean isIndoor() {
        return isIndoor;
    }

    public void setFurColor(String furColor) {
        if(!furColor.isEmpty())
            this.furColor = furColor;
        else
            System.out.println("Fur color must not be empty");
    }

    public void setIndoor(boolean indoor) {
        isIndoor = indoor;
    }

    @Override
    void move() {
        System.out.println(getName() + " is sneaking quietly");
    }

    @Override
    void makeSound() {
        System.out.println(getName() + " says: Meow!");
    }

    @Override
    void displayInfo() {
        super.displayInfo();
        System.out.println("Fur color: " + furColor + "\n" +
                            "indoor: " + isIndoor + "\n");
    }
    void meow(){
        System.out.println(getName() + " is meowing softly");
    }
    void purr(){
        System.out.println(getName() + " is purring contentedly");
    }
    void scratch(){
        System.out.println(getName() + " is scratching the post");
    }
}
