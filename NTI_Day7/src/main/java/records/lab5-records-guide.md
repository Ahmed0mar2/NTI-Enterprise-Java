---
tags: [java, teaching, lab, records]
---

# Lab 5 — Records

> [!info] How to use this lab
> Each exercise has a **task** and a hidden **solution**. Try each for 5–10 minutes before revealing. Runnable `.java` starter/solution files are included alongside this doc.

---

## Exercise 5.1 — Your First Record

**Task:** Create a record `Point(int x, int y)`. In `main`, create two points with the same coordinates and demonstrate that:
1. `equals()` returns `true` for them
2. `hashCode()` matches for both
3. `toString()` prints in the format `Point[x=..., y=...]`
4. They are correctly deduplicated when both added to a `HashSet`

```java
// TODO: define record Point

public class Exercise5_1_FirstRecord {
    public static void main(String[] args) {
        // TODO: create two Points with the same values and test equals/hashCode/toString/HashSet
    }
}
```

> [!success]- Solution
> ```java
> record Point(int x, int y) {}
>
> public class Exercise5_1_FirstRecord {
>     public static void main(String[] args) {
>         Point a = new Point(3, 4);
>         Point b = new Point(3, 4);
>
>         System.out.println(a.equals(b));               // true
>         System.out.println(a.hashCode() == b.hashCode()); // true
>         System.out.println(a);                          // Point[x=3, y=4]
>
>         Set<Point> points = new HashSet<>();
>         points.add(a);
>         points.add(b);
>         System.out.println(points.size());              // 1
>     }
> }
> ```

---

## Exercise 5.2 — Convert a Traditional Class to a Record

**Task:** Below is a traditional immutable class. Convert it to a one-line record with identical behavior, then verify `getName()`/`getAge()` calls need to change to `name()`/`age()`.

```java
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
    public int hashCode() { return Objects.hash(name, age); }

    @Override
    public String toString() { return "Person[name=" + name + ", age=" + age + "]"; }
}
```

> [!success]- Solution
> ```java
> record Person(String name, int age) {}
>
> public class Exercise5_2_PersonRecord {
>     public static void main(String[] args) {
>         Person p = new Person("Ali", 30);
>         System.out.println(p.name()); // Ali (not getName())
>         System.out.println(p.age());  // 30  (not getAge())
>         System.out.println(p);        // Person[name=Ali, age=30]
>     }
> }
> ```
> **Talking point:** 15+ lines collapse to 1, and the accessor naming changes from `getX()` to `x()` — a common trip-up for people used to POJOs/Lombok.

---

## Exercise 5.3 — Compact Constructor for Validation

**Task:** Create a record `Range(int min, int max)` that throws `IllegalArgumentException` if `min > max`. Test both a valid and an invalid construction (catch the exception and print a message for the invalid one).

```java
// TODO: define Range with a compact constructor validating min <= max

public class Exercise5_3_RangeValidation {
    public static void main(String[] args) {
        // TODO: create a valid Range, then attempt an invalid one and catch the exception
    }
}
```

> [!success]- Solution
> ```java
> record Range(int min, int max) {
>     public Range {
>         if (min > max) {
>             throw new IllegalArgumentException("min (" + min + ") > max (" + max + ")");
>         }
>     }
> }
>
> public class Exercise5_3_RangeValidation {
>     public static void main(String[] args) {
>         Range good = new Range(1, 10);
>         System.out.println(good); // Range[min=1, max=10]
>
>         try {
>             Range bad = new Range(10, 5);
>         } catch (IllegalArgumentException e) {
>             System.out.println("Caught: " + e.getMessage());
>         }
>     }
> }
> ```

---

## Exercise 5.4 — Compact Constructor for Normalization

**Task:** Create a record `Email(String address)` whose compact constructor trims whitespace and lowercases the address before storing it. Test with `"  John@Example.COM  "`.

```java
// TODO: define Email with a normalizing compact constructor

public class Exercise5_4_EmailNormalization {
    public static void main(String[] args) {
        // TODO: create an Email with messy input and print the normalized result
    }
}
```

> [!success]- Solution
> ```java
> record Email(String address) {
>     public Email {
>         address = address.trim().toLowerCase();
>     }
> }
>
> public class Exercise5_4_EmailNormalization {
>     public static void main(String[] args) {
>         Email e = new Email("  John@Example.COM  ");
>         System.out.println(e.address()); // john@example.com
>     }
> }
> ```

---

## Exercise 5.5 — Adding Custom Methods

**Task:** Extend the `Range` record from Exercise 5.3 with two additional methods:
1. `length()` — returns `max - min`
2. `contains(int value)` — returns `true` if `value` is within `[min, max]` inclusive

Test both methods.

```java
// TODO: add length() and contains() to Range

public class Exercise5_5_RangeMethods {
    public static void main(String[] args) {
        // TODO: test length() and contains()
    }
}
```

> [!success]- Solution
> ```java
> record Range(int min, int max) {
>     public Range {
>         if (min > max) throw new IllegalArgumentException("min > max");
>     }
>
>     public int length() {
>         return max - min;
>     }
>
>     public boolean contains(int value) {
>         return value >= min && value <= max;
>     }
> }
>
> public class Exercise5_5_RangeMethods {
>     public static void main(String[] args) {
>         Range r = new Range(5, 15);
>         System.out.println(r.length());     // 10
>         System.out.println(r.contains(10)); // true
>         System.out.println(r.contains(20)); // false
>     }
> }
> ```

---

## Exercise 5.6 — Static Factory Methods and Constants

**Task:** Create a record `Point(int x, int y)` with:
1. A `public static final Point ORIGIN` constant at `(0, 0)`
2. A static factory method `of(int x, int y)`
3. An instance method `distanceFromOrigin()` returning the Euclidean distance

```java
// TODO: define Point with ORIGIN, of(), and distanceFromOrigin()

public class Exercise5_6_PointFactory {
    public static void main(String[] args) {
        // TODO: use ORIGIN, Point.of(...), and distanceFromOrigin()
    }
}
```

> [!success]- Solution
> ```java
> record Point(int x, int y) {
>     public static final Point ORIGIN = new Point(0, 0);
>
>     public static Point of(int x, int y) {
>         return new Point(x, y);
>     }
>
>     public double distanceFromOrigin() {
>         return Math.sqrt(x * x + y * y);
>     }
> }
>
> public class Exercise5_6_PointFactory {
>     public static void main(String[] args) {
>         System.out.println(Point.ORIGIN); // Point[x=0, y=0]
>
>         Point p = Point.of(3, 4);
>         System.out.println(p.distanceFromOrigin()); // 5.0
>     }
> }
> ```

---

## Exercise 5.7 — Records Implementing an Interface

**Task:** Create an interface `Shape` with a method `double area()`. Implement it with records `Circle(double radius)` and `Rectangle(double width, double height)`. Put both in a `List<Shape>` and print the total area using a stream.

```java
interface Shape {
    double area();
}

// TODO: define Circle and Rectangle records implementing Shape

public class Exercise5_7_ShapeRecords {
    public static void main(String[] args) {
        // TODO: build a List<Shape> and sum the areas using streams
    }
}
```

> [!success]- Solution
> ```java
> interface Shape {
>     double area();
> }
>
> record Circle(double radius) implements Shape {
>     @Override
>     public double area() {
>         return Math.PI * radius * radius;
>     }
> }
>
> record Rectangle(double width, double height) implements Shape {
>     @Override
>     public double area() {
>         return width * height;
>     }
> }
>
> public class Exercise5_7_ShapeRecords {
>     public static void main(String[] args) {
>         List<Shape> shapes = List.of(
>             new Circle(2),
>             new Rectangle(3, 4)
>         );
>
>         double totalArea = shapes.stream()
>             .mapToDouble(Shape::area)
>             .sum();
>
>         System.out.println(totalArea); // ~12.566 + 12.0 = ~24.566
>     }
> }
> ```
> **Talking point:** records can't extend a class, but implementing an interface works perfectly — this combination is the foundation for the sealed-types topic coming up next.

---

## Exercise 5.8 — Spot the Compile Error

**Task:** The record below doesn't compile. Find the mistake and explain why records don't allow it.

```java
public record Account(String owner, double balance) {
    private double interestRate; // <-- compile error here

    public double withInterest() {
        return balance * (1 + interestRate);
    }
}
```

> [!success]- Solution
> Records **cannot declare additional instance fields** beyond their components — only the declared components (`owner`, `balance`) may hold instance state. This is what "transparent carrier" means: every piece of instance data must be visible in the record header.
>
> Fix: either add `interestRate` as a record component, or make it `static` if it's meant to be a shared constant:
> ```java
> public record Account(String owner, double balance, double interestRate) {
>     public double withInterest() {
>         return balance * (1 + interestRate);
>     }
> }
> ```

---

## Exercise 5.9 — Records as Map Keys

**Task:** Create a record `Coordinate(int row, int col)`. Use it as a key in a `Map<Coordinate, String>` representing a small grid of labeled cells. Demonstrate that looking up a value with a *newly constructed* (but equal) `Coordinate` still finds the entry — proving records work correctly as map keys without any extra code.

```java
// TODO: define Coordinate

public class Exercise5_9_RecordAsMapKey {
    public static void main(String[] args) {
        // TODO: build a Map<Coordinate, String>, then look up using a new equal Coordinate instance
    }
}
```

> [!success]- Solution
> ```java
> record Coordinate(int row, int col) {}
>
> public class Exercise5_9_RecordAsMapKey {
>     public static void main(String[] args) {
>         Map<Coordinate, String> grid = new HashMap<>();
>         grid.put(new Coordinate(0, 0), "Start");
>         grid.put(new Coordinate(2, 3), "Treasure");
>
>         String result = grid.get(new Coordinate(2, 3)); // a DIFFERENT object, same values
>         System.out.println(result); // Treasure
>     }
> }
> ```
> **Talking point:** this only works because records generate correct `equals()`/`hashCode()` automatically — with a hand-written class missing those overrides, this lookup would return `null`.

---

## Exercise 5.10 — Capstone: DTO Conversion with Validation

**Task:** Build a `CreateUserRequest` record with `username` and `email` components. Add a compact constructor that:
1. Throws `IllegalArgumentException` if `username` is blank
2. Throws `IllegalArgumentException` if `email` doesn't contain `"@"`

Then write a method `processSignup(CreateUserRequest request)` that prints a welcome message using the record's accessors. Test with one valid and one invalid request (catch the exception).

```java
// TODO: define CreateUserRequest with a two-rule compact constructor

public class Exercise5_10_SignupCapstone {

    static void processSignup(CreateUserRequest request) {
        // TODO: print a welcome message using request's accessors
    }

    public static void main(String[] args) {
        // TODO: test with a valid request, then an invalid one (catch and print the error)
    }
}
```

> [!success]- Solution
> ```java
> record CreateUserRequest(String username, String email) {
>     public CreateUserRequest {
>         if (username == null || username.isBlank()) {
>             throw new IllegalArgumentException("username is required");
>         }
>         if (email == null || !email.contains("@")) {
>             throw new IllegalArgumentException("email must be valid");
>         }
>     }
> }
>
> public class Exercise5_10_SignupCapstone {
>
>     static void processSignup(CreateUserRequest request) {
>         System.out.println("Welcome, " + request.username() + "! Confirmation sent to " + request.email());
>     }
>
>     public static void main(String[] args) {
>         CreateUserRequest valid = new CreateUserRequest("ali92", "ali@example.com");
>         processSignup(valid);
>         // Welcome, ali92! Confirmation sent to ali@example.com
>
>         try {
>             CreateUserRequest invalid = new CreateUserRequest("", "not-an-email");
>             processSignup(invalid);
>         } catch (IllegalArgumentException e) {
>             System.out.println("Signup failed: " + e.getMessage());
>         }
>     }
> }
> ```

---

## Wrap-up Discussion Questions

1. Why does a record's constructor validation logic go in a *compact* constructor instead of a normal one? What would happen if you tried to write a normal constructor with a parameter list instead?
2. In Exercise 5.9, what would break if `Coordinate` were a regular class without overriding `equals()`/`hashCode()`?
3. Give a real example from your own Spring Boot project where converting a class to a record would remove real boilerplate — and one place where it would be the *wrong* choice (hint: think about JPA entities).
