---
tags: [java, teaching, lab, streams]
---

# Lab 3 — Streams API

> [!info] How to use this lab
> Each exercise has a **task** and a hidden **solution**. Try each for 5–10 minutes before revealing. Runnable `.java` starter/solution files are included alongside this doc — use whichever format fits your session better.

---

## Exercise 3.1 — Creating Streams

**Task:** Create and print the contents of:
1. A stream from a `List<String>` of your favorite 3 programming languages
2. A stream of the integers 1 through 10 using `IntStream.rangeClosed`
3. An infinite stream of even numbers starting at 0, limited to the first 6

```java
// TODO: create all three streams and print their contents with forEach
```

> [!success]- Solution
> ```java
> List<String> languages = List.of("Java", "Python", "Kotlin");
> languages.stream().forEach(System.out::println);
>
> IntStream.rangeClosed(1, 10).forEach(System.out::println);
>
> Stream.iterate(0, n -> n + 2).limit(6).forEach(System.out::println);
> // 0, 2, 4, 6, 8, 10
> ```

---

## Exercise 3.2 — `filter` and `map`

**Task:** Given `List<String> words = List.of("apple", "kiwi", "banana", "fig", "grape")`, produce a list of the **uppercase** versions of all words with **more than 4 characters**.

```java
// TODO: filter + map + collect
```

> [!success]- Solution
> ```java
> List<String> result = words.stream()
>     .filter(w -> w.length() > 4)
>     .map(String::toUpperCase)
>     .collect(Collectors.toList());
> // [APPLE, BANANA, GRAPE]
> ```

---

## Exercise 3.3 — `flatMap`

**Task:** Given a `List<List<String>> teams` where each inner list is a team's member names, produce a single flat `List<String>` of all members across all teams.

```java
List<List<String>> teams = List.of(
    List.of("Ali", "Sara"),
    List.of("Omar"),
    List.of("Lina", "Ahmed", "Nour")
);
// TODO: flatMap into one flat list
```

> [!success]- Solution
> ```java
> List<String> allMembers = teams.stream()
>     .flatMap(List::stream)
>     .collect(Collectors.toList());
> // [Ali, Sara, Omar, Lina, Ahmed, Nour]
> ```

---

## Exercise 3.4 — `sorted`, `distinct`, `limit`, `skip`

**Task:** Given `List<Integer> nums = List.of(5, 3, 8, 3, 9, 1, 5, 8, 2)`:
1. Remove duplicates
2. Sort ascending
3. Skip the first 2
4. Take the next 3

Do this in **one pipeline**.

```java
// TODO: chain distinct -> sorted -> skip -> limit
```

> [!success]- Solution
> ```java
> List<Integer> result = nums.stream()
>     .distinct()
>     .sorted()
>     .skip(2)
>     .limit(3)
>     .collect(Collectors.toList());
> // distinct+sorted: [1, 2, 3, 5, 8, 9] -> skip 2: [3, 5, 8, 9] -> limit 3: [3, 5, 8]
> ```

---

## Exercise 3.5 — `reduce`

**Task:** Given `List<Integer> prices = List.of(20, 15, 30, 10, 25)`:
1. Use `reduce` to compute the total sum.
2. Use `reduce` (no identity) to find the maximum price, printed via the `Optional` it returns.

```java
// TODO: implement both reduce operations
```

> [!success]- Solution
> ```java
> int total = prices.stream().reduce(0, Integer::sum);
> System.out.println(total); // 100
>
> Optional<Integer> max = prices.stream().reduce(Integer::max);
> max.ifPresent(System.out::println); // 30
> ```

---

## Exercise 3.6 — `anyMatch` / `allMatch` / `noneMatch`

**Task:** Given `List<String> passwords = List.of("abc123", "password", "Xy9!zK2q", "12345")`, check:
1. Is there any password longer than 7 characters?
2. Do all passwords have at least 5 characters?
3. Is there no password that equals exactly `"admin"`?

```java
// TODO: implement all three checks
```

> [!success]- Solution
> ```java
> boolean anyLong = passwords.stream().anyMatch(p -> p.length() > 7);   // true
> boolean allAtLeast5 = passwords.stream().allMatch(p -> p.length() >= 5); // true
> boolean noneIsAdmin = passwords.stream().noneMatch(p -> p.equals("admin")); // true
> ```

---

## Exercise 3.7 — `findFirst`, `min`, `max`

**Task:** Given a list of `Product` objects (name, price), find:
1. The first product cheaper than $50
2. The cheapest product overall
3. The most expensive product overall

```java
class Product {
    String name;
    double price;
    Product(String name, double price) { this.name = name; this.price = price; }
}

List<Product> products = List.of(
    new Product("Mouse", 25.0),
    new Product("Keyboard", 60.0),
    new Product("Monitor", 200.0),
    new Product("Cable", 10.0)
);
// TODO: findFirst, min, max
```

> [!success]- Solution
> ```java
> Optional<Product> firstCheap = products.stream()
>     .filter(p -> p.price < 50)
>     .findFirst();
> firstCheap.ifPresent(p -> System.out.println(p.name)); // Mouse
>
> Optional<Product> cheapest = products.stream()
>     .min(Comparator.comparingDouble(p -> p.price));
> cheapest.ifPresent(p -> System.out.println(p.name)); // Cable
>
> Optional<Product> mostExpensive = products.stream()
>     .max(Comparator.comparingDouble(p -> p.price));
> mostExpensive.ifPresent(p -> System.out.println(p.name)); // Monitor
> ```

---

## Exercise 3.8 — `Collectors.groupingBy` and `joining`

**Task:** Given the same `products` list plus a `category` field, group product names by category, then produce a single comma-separated string of all product names sorted alphabetically.

```java
class Product {
    String name;
    double price;
    String category;
    Product(String name, double price, String category) {
        this.name = name; this.price = price; this.category = category;
    }
}

List<Product> products = List.of(
    new Product("Mouse", 25.0, "Accessories"),
    new Product("Keyboard", 60.0, "Accessories"),
    new Product("Monitor", 200.0, "Displays"),
    new Product("Cable", 10.0, "Accessories")
);
// TODO: groupingBy category -> Map<String, List<String>> of names
// TODO: joining -> single sorted comma-separated string of all names
```

> [!success]- Solution
> ```java
> Map<String, List<String>> byCategory = products.stream()
>     .collect(Collectors.groupingBy(
>         p -> p.category,
>         Collectors.mapping(p -> p.name, Collectors.toList())
>     ));
> // {Accessories=[Mouse, Keyboard, Cable], Displays=[Monitor]}
>
> String joined = products.stream()
>     .map(p -> p.name)
>     .sorted()
>     .collect(Collectors.joining(", "));
> // "Cable, Keyboard, Monitor, Mouse"
> ```

---

## Exercise 3.9 — Mini Challenge: Sum of Squares of Numbers > 10

**Task:** Given `List<Integer> numbers = List.of(5, 12, 8, 3, 20, 15, 7)`, compute the sum of squares of all numbers greater than 10, using `mapToInt` and `sum()`.

```java
// TODO: filter -> mapToInt -> sum
```

> [!success]- Solution
> ```java
> int result = numbers.stream()
>     .filter(n -> n > 10)
>     .mapToInt(n -> n * n)
>     .sum();
> // 12² + 20² + 15² = 144 + 400 + 225 = 769
> ```

---

## Exercise 3.10 — Capstone: Full Pipeline

**Task:** You have a list of `Order` objects (customerName, total, isPaid). Write a single pipeline that:
1. Keeps only paid orders
2. Sorts by total, descending
3. Extracts just the customer names
4. Removes duplicates
5. Collects into a `List<String>`

Then, separately, compute the **average total** of only the paid orders using `Collectors.averagingDouble`.

```java
class Order {
    String customerName;
    double total;
    boolean isPaid;
    Order(String customerName, double total, boolean isPaid) {
        this.customerName = customerName; this.total = total; this.isPaid = isPaid;
    }
}

List<Order> orders = List.of(
    new Order("Ali", 250.0, true),
    new Order("Sara", 80.0, false),
    new Order("Omar", 500.0, true),
    new Order("Ali", 120.0, true),
    new Order("Ahmed", 90.0, true)
);
// TODO: build the pipeline described above
// TODO: compute average total of paid orders
```

> [!success]- Solution
> ```java
> List<String> paidCustomers = orders.stream()
>     .filter(o -> o.isPaid)
>     .sorted(Comparator.comparingDouble((Order o) -> o.total).reversed())
>     .map(o -> o.customerName)
>     .distinct()
>     .collect(Collectors.toList());
> // [Omar, Ali, Ahmed]
>
> double avgPaidTotal = orders.stream()
>     .filter(o -> o.isPaid)
>     .collect(Collectors.averagingDouble(o -> o.total));
> // (250 + 500 + 120 + 90) / 4 = 240.0
> ```

---

## Wrap-up Discussion Questions

1. Why does calling `.stream()` twice on the same `Stream` object throw an exception, but calling `list.stream()` twice on the same `List` works fine?
2. In Exercise 3.10, would reordering `filter` and `sorted` change the final result? Would it change performance?
3. When would you reach for `IntStream`/`mapToInt` instead of `Stream<Integer>`, and why does it matter for large datasets?
