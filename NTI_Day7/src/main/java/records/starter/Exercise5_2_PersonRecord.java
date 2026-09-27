// Exercise 5.2 — Convert a Traditional Class to a Record
//
// Below is a traditional immutable Person class (commented out).
// TODO 1: Define an equivalent record Person(String name, int age) in ONE line.
// TODO 2: In main, use it and call name()/age() instead of getName()/getAge().

/*
public final class Person {
    private final String name;
    private final int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() { return name; }
    public int getAge() { return age; }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Person)) return false;
        Person p = (Person) o;
        return age == p.age && name.equals(p.name);
    }

    @Override
    public int hashCode() { return java.util.Objects.hash(name, age); }

    @Override
    public String toString() { return "Person[name=" + name + ", age=" + age + "]"; }
}
*/

// TODO 1: define record Person here

public class Exercise5_2_PersonRecord {
    public static void main(String[] args) {
        // TODO 2: create a Person and print name(), age(), and the record itself
    }
}
