package weekend_project;

public class Animal {

    private String name;
    private int age;
    private String species;
    private double weight;
    private boolean isHealthy;

    public Animal(String name, int age, String species, double weight, boolean isHealthy){
        setName(name);
        setAge(age);
        setWeight(weight);
        setSpecies(species);
        setHealthy(isHealthy);

    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getSpecies() {
        return species;
    }

    public double getWeight() {
        return weight;
    }

    public boolean isHealthy() {
        return isHealthy;
    }
    public void setName(String name){
        if(name.isEmpty())
            System.out.println("Name must not be empty");
        else{
            this.name = name;
        }
    }
    public void setAge(int age){
        if(age > 0 && age <= 50)
            this.age = age;
        else
            System.out.println("Age must be greater than 0 and less than or equal to 50");
    }
    public void setWeight(double weight){
      if(weight > 0)
          this.weight = weight;
      else
          System.out.println("Weight must be greater than 0");
    }
    public void setSpecies(String species){
        if(!species.isEmpty())
            this.species = species;
        else
            System.out.println("Species must not be empty");
    }
    public void setHealthy(boolean isHealthy){
        this.isHealthy = isHealthy;
    }
    void eat(){
        System.out.println(name + " is eating");
    }
    void sleep(){
        System.out.println(name + " is sleeping");
    }
    void makeSound(){
        System.out.println(name + " makes a sound");
    }
    void move(){
        System.out.println(name + " is moving");
    }
    void displayInfo(){
        System.out.println("Name: " + name + "\n" +
                            "Age: " + age + "\n" +
                            "Weight: " + weight + "\n" +
                            "Species: " + species + "\n" +
                            "Healthy: " + isHealthy + "\n");
    }
}
