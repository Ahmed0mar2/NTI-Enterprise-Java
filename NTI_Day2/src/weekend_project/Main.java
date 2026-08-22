package weekend_project;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter zoo name: ");
        Zoo zoo = new Zoo(scanner.nextLine());
        while (true) {
            System.out.println("""
                    ===== Zoo MANAGEMENT SYSTEM =====
                    1. Add New Animal
                    2. Display All Animals
                    3. Search Animal by name
                    4. Remove Animal
                    5. Feed All Animals
                    6. Make All Animals Sound
                    7. Move All Animals
                    8. Display Statistics
                    9. Search by Species
                    10. Show Healthy Animals
                    11. Animal Actions Menu
                    0. Exit
                    ====================================
                    Enter your choice:""");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 0)
                break;
            switch (choice) {

                case 1: // Add animal to zoo

                    System.out.println("""
                            Enter your choice
                            1. Cat
                            2. Dog
                            3. Bird
                            """);
                    int animal = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.println("Enter age: ");
                    int age = scanner.nextInt();
                    System.out.println("Enter weight in kg: ");
                    double weight = scanner.nextDouble();
                    System.out.println("Is healthy: ");
                    boolean isHealthy = scanner.nextBoolean();
                    scanner.nextLine();
                    switch (animal) {

                        case 1: // Add a cat to the zoo
                            System.out.println("Enter fur color: ");
                            String furColor = scanner.nextLine();
                            System.out.println("Indoor: ");
                            boolean isIndoor = scanner.nextBoolean();
                            Animal cat = new Cat(name, age, weight, isHealthy, furColor, isIndoor);
                            zoo.addAnimal(cat);
                            break;

                        case 2: // Add a dog to the zoo
                            System.out.println("Enter breed: ");
                            Animal dog = new Dog(name, age, weight, isHealthy, scanner.nextLine());
                            zoo.addAnimal(dog);
                            break;

                        case 3: // Add bird to the zoo
                            System.out.println("Enter wing span: ");
                            double wingSpan = scanner.nextDouble();
                            System.out.println("Can fly: ");
                            Animal bird = new Bird(name, age, weight, isHealthy, wingSpan, scanner.nextBoolean());
                            zoo.addAnimal(bird);
                            break;

                        default:
                            System.out.println("Invalid option");
                            break;
                    }
                    break;


                case 2: // Display all animals info
                    zoo.displayAllAnimals();
                    break;


                case 3: // Find animal by name
                    System.out.println("Enter name: ");
                    Animal result = zoo.findAnimalByName(scanner.nextLine());
                    if (result != null)
                        result.displayInfo();
                    else
                        System.out.println("not found");
                    break;


                case 4: // Remove animal by name
                    System.out.println("Enter name: ");
                    zoo.removeAnimal(scanner.nextLine());
                    break;


                case 5: // Feed all animals
                    zoo.feedAllAnimals();
                    break;


                case 6: // Make all animals in the zoo sounds
                    zoo.makeAllAnimalsSounds();
                    break;


                case 7: // Move all animals in the zoo
                    zoo.moveAllAnimals();
                    break;


                case 8: // Display zoo's statistics
                    zoo.displayStatistics();
                    break;


                case 9: // Search by species
                    System.out.println("Please write the species you want to search with from these species (Dog, Cat, Bird): ");
                    zoo.searchBySpecies(scanner.nextLine());
                    break;


                case 10: // Show healthy animals in the zoo
                    ArrayList<Animal> healthyAnimals = zoo.getHealthyAnimals();
                    if (healthyAnimals.isEmpty())
                        System.out.println("No healthy animals found");
                    else {
                        for (Animal a : healthyAnimals)
                            a.displayInfo();
                    }
                    break;


                case 11: // Animal action menu
                    System.out.println("Please enter the animal name: ");
                    Animal found = zoo.findAnimalByName(scanner.nextLine());
                    if (found != null) {
                        if (found instanceof Cat c) {
                            System.out.println("""
                                    Please enter action
                                    1. Meow
                                    2. Purr
                                    3. Scratch
                                    """);
                            int catAction = scanner.nextInt();
                            scanner.nextLine();
                            switch (catAction) {

                                case 1: // Meow
                                    c.meow();
                                    break;

                                case 2: // Purr
                                    c.purr();
                                    break;

                                case 3: // Scratch
                                    c.scratch();
                                    break;

                                default:
                                    System.out.println("Invalid option");
                                    break;
                            }
                        } else if (found instanceof Dog d) {
                            System.out.println("""
                                    Please enter action
                                    1. Bark
                                    2. Fetch
                                    3. Wag tail
                                    """);
                            int dogAction = scanner.nextInt();
                            scanner.nextLine();
                            switch (dogAction) {

                                case 1: // Bark
                                    d.bark();
                                    break;

                                case 2: // Fetch
                                    d.fetch();
                                    break;

                                case 3: // Wag tail
                                    d.wagTail();
                                    break;

                                default:
                                    System.out.println("Invalid option");
                                    break;
                            }
                        } else if (found instanceof Bird b) {
                            System.out.println("""
                                    Please enter action
                                    1. Chirp
                                    2. Fly
                                    3. Build nest
                                    """);
                            int birdAction = scanner.nextInt();
                            scanner.nextLine();
                            switch (birdAction) {

                                case 1: // Chirp
                                    b.chirp();
                                    break;

                                case 2: // Fly
                                    b.fly();
                                    break;

                                case 3: // Build nest
                                    b.buildNest();
                                    break;

                                default:
                                    System.out.println("Invalid option");
                                    break;
                            }
                        }
                    }
                    break;
                default:
                    System.out.println("Invalid option");
                    break;

            }
        }
    }
}

