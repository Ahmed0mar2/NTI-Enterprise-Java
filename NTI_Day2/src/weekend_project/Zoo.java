package weekend_project;

import java.util.ArrayList;

public class Zoo {
    private final ArrayList<Animal> animals;
    private final String zooName;

    public Zoo(String zooName) {
        this.zooName = zooName;
        animals = new ArrayList<>();
    }

    public String getZooName() {
        return zooName;
    }

    void addAnimal(Animal animal) {
        animals.add(animal);
        System.out.println(animal.getName() + " has been added to " + zooName);
    }

    void displayAllAnimals() {
        if (animals.isEmpty())
            System.out.println("Zoo " + zooName + " is empty");
        else {
            for (Animal animal : animals) {
                animal.displayInfo();
                System.out.println("=========================");
            }
        }
    }

    Animal findAnimalByName(String name) {
        for (Animal animal : animals) {
            if (animal.getName().equalsIgnoreCase(name))
                return animal;
        }
        return null;
    }

    void removeAnimal(String name) {
        Animal ref = findAnimalByName(name);
        if (ref != null) {
            animals.remove(ref);
            System.out.println("Animal Removed successfully");
        } else
            System.out.println("Animal not found");

    }

    void feedAllAnimals() {
        for (Animal animal : animals)
            animal.eat();
    }

    void makeAllAnimalsSounds() {
        for (Animal animal : animals)
            animal.makeSound();
    }

    void moveAllAnimals() {
        for (Animal animal : animals)
            animal.move();
    }

    int getTotalAnimals() {
        return animals.size();
    }

    double getAverageAge() {
        double sum = 0;
        if (animals.isEmpty())
            return 0;
        else {

            for (Animal animal : animals) {
                sum += animal.getAge();
            }
        }
        return sum / animals.size();
    }

    void displayStatistics() {
        int catCount = 0;
        int dogCount = 0;
        int birdCount = 0;
        int healthyAnimals = 0;
        for (Animal animal : animals) {
            switch (animal.getSpecies()) {
                case "Cat":
                    catCount++;
                    break;
                case "Dog":
                    dogCount++;
                    break;
                case "Bird":
                    birdCount++;
                    break;
            }
            healthyAnimals = animal.isHealthy() ? healthyAnimals + 1 : healthyAnimals;

        }
        System.out.println("Zoo name: " + zooName + "\n" +
                "Total animals count: " + getTotalAnimals() + "\n" +
                "Average age: " + getAverageAge() + "\n" +
                "Cats count: " + catCount + "\n" +
                "Dogs count: " + dogCount + "\n" +
                "Birds count: " + birdCount + "\n" +
                "Healthy animals: " + healthyAnimals + "\n");
    }

    void searchBySpecies(String species) {
        boolean found = false;
        for (Animal animal : animals) {
            if (animal.getSpecies().equalsIgnoreCase(species)) {
                found = true;
                animal.displayInfo();
            }
        }
        if (!found)
            System.out.println("No animals match the search parameter");
    }

    ArrayList<Animal> getHealthyAnimals() {
        ArrayList<Animal> healthyAnimals = new ArrayList<>();
        for (Animal animal : animals) {
            if (animal.isHealthy())
                healthyAnimals.add(animal);
        }
        return healthyAnimals;
    }

    ArrayList<Animal> getAnimalsByAgeRange(int minAge, int maxAge) {
        ArrayList<Animal> inRangeAnimals = new ArrayList<>();
        for (Animal animal : animals) {
            if (animal.getAge() >= minAge && animal.getAge() <= maxAge) {
                inRangeAnimals.add(animal);
            }
        }
        return inRangeAnimals;
    }

}
