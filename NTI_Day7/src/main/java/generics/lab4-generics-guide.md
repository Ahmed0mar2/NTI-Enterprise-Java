---
tags: [java, teaching, lab, generics]
---

# Lab 4 — Generics

> [!info] How to use this lab
> Each exercise has a **task** and a hidden **solution**. Try each for 5–10 minutes before revealing. Runnable `.java` starter/solution files are included alongside this doc.

---

## Exercise 4.1 — Spot the Problem (No Coding Yet)

**Task:** The code below compiles and runs, but crashes. Explain *why* it crashes, and what specifically generics would have prevented.

```java
List rawList = new ArrayList();
rawList.add("Hello");
rawList.add(42);

for (Object o : rawList) {
    String s = (String) o; // crashes on the second element
    System.out.println(s.toUpperCase());
}
```

> [!success]- Solution
> The list is a **raw type** — it carries no type information, so the compiler can't verify that every element is a `String`. The cast `(String) o` compiles fine because the compiler trusts you, but at runtime the second element is actually an `Integer`, and the cast throws a `ClassCastException`.
>
> With generics (`List<String> rawList = new ArrayList<>()`), the line `rawList.add(42)` would be a **compile-time error** — the bug would never make it to runtime at all.

---

## Exercise 4.2 — Build a Generic `Box<T>`

**Task:** Create a generic class `Box<T>` with:
- A private field of type `T`
- A `set(T value)` method
- A `get()` method returning `T`
- An `isEmpty()` method returning `true` if the content is `null`

Then create a `Box<String>` and a `Box<Integer>`, and show that trying to `set()` the wrong type on either fails to compile (write the failing line as a comment).

```java
// TODO: define Box<T>

public class Exercise4_2_Box {
    public static void main(String[] args) {
        // TODO: create Box<String> and Box<Integer>, use set/get/isEmpty
    }
}
```

> [!success]- Solution
> ```java
> class Box<T> {
>     private T content;
>
>     public void set(T content) { this.content = content; }
>     public T get() { return content; }
>     public boolean isEmpty() { return content == null; }
> }
>
> public class Exercise4_2_Box {
>     public static void main(String[] args) {
>         Box<String> stringBox = new Box<>();
>         System.out.println(stringBox.isEmpty()); // true
>         stringBox.set("Hello");
>         System.out.println(stringBox.get());     // Hello
>         // stringBox.set(42); // ❌ compiler error
>
>         Box<Integer> intBox = new Box<>();
>         intBox.set(42);
>         System.out.println(intBox.get());        // 42
>         // intBox.set("oops"); // ❌ compiler error
>     }
> }
> ```

---

## Exercise 4.3 — Build a Generic `Pair<K, V>`

**Task:** Create a generic class `Pair<K, V>` with a constructor taking a key and a value, plus `getKey()` and `getValue()` methods. Then create a `List<Pair<String, Integer>>` representing 3 people's names and ages, and print each pair.

```java
// TODO: define Pair<K, V>

public class Exercise4_3_Pair {
    public static void main(String[] args) {
        // TODO: build a List<Pair<String, Integer>> and print each entry
    }
}
```

> [!success]- Solution
> ```java
> class Pair<K, V> {
>     private K key;
>     private V value;
>
>     public Pair(K key, V value) {
>         this.key = key;
>         this.value = value;
>     }
>
>     public K getKey() { return key; }
>     public V getValue() { return value; }
>
>     @Override
>     public String toString() {
>         return key + " -> " + value;
>     }
> }
>
> public class Exercise4_3_Pair {
>     public static void main(String[] args) {
>         List<Pair<String, Integer>> people = List.of(
>             new Pair<>("Ali", 30),
>             new Pair<>("Sara", 25),
>             new Pair<>("Omar", 40)
>         );
>
>         for (Pair<String, Integer> p : people) {
>             System.out.println(p);
>         }
>         // Ali -> 30
>         // Sara -> 25
>         // Omar -> 40
>     }
> }
> ```

---

## Exercise 4.4 — Write a Generic Method: `firstElement`

**Task:** Write a static generic method `<T> T firstElement(List<T> list)` that returns the first element of any list, and throws `NoSuchElementException` if the list is empty. Test it with a `List<String>` and a `List<Integer>`.

```java
public class Exercise4_4_FirstElement {
    // TODO: implement firstElement

    public static void main(String[] args) {
        // TODO: test with List<String> and List<Integer>
    }
}
```

> [!success]- Solution
> ```java
> import java.util.List;
> import java.util.NoSuchElementException;
>
> public class Exercise4_4_FirstElement {
>
>     public static <T> T firstElement(List<T> list) {
>         if (list.isEmpty()) throw new NoSuchElementException("List is empty");
>         return list.get(0);
>     }
>
>     public static void main(String[] args) {
>         String first = firstElement(List.of("a", "b", "c"));
>         System.out.println(first); // a
>
>         Integer firstNum = firstElement(List.of(1, 2, 3));
>         System.out.println(firstNum); // 1
>     }
> }
> ```

---

## Exercise 4.5 — Bounded Type Parameter: Generic `max`

**Task:** Write a static generic method `<T extends Comparable<T>> T max(T a, T b)` that returns the larger of two values. Test it with two `Integer`s and two `String`s.

```java
public class Exercise4_5_GenericMax {
    // TODO: implement max with a bounded type parameter

    public static void main(String[] args) {
        // TODO: test with Integers and Strings
    }
}
```

> [!success]- Solution
> ```java
> public class Exercise4_5_GenericMax {
>
>     public static <T extends Comparable<T>> T max(T a, T b) {
>         return a.compareTo(b) > 0 ? a : b;
>     }
>
>     public static void main(String[] args) {
>         System.out.println(max(3, 7));               // 7
>         System.out.println(max("apple", "banana"));   // banana
>     }
> }
> ```
> **Talking point:** without `extends Comparable<T>`, `a.compareTo(b)` wouldn't compile — `T` alone gives you nothing but `Object`'s methods.

---

## Exercise 4.6 — Bounded Type Parameter: Generic `min` for a List

**Task:** Write a static generic method `<T extends Comparable<T>> T min(List<T> list)` that returns the smallest element in a non-empty list. Test it with a `List<Integer>`.

```java
public class Exercise4_6_GenericMin {
    // TODO: implement min

    public static void main(String[] args) {
        // TODO: test with a List<Integer>
    }
}
```

> [!success]- Solution
> ```java
> import java.util.List;
>
> public class Exercise4_6_GenericMin {
>
>     public static <T extends Comparable<T>> T min(List<T> list) {
>         T smallest = list.get(0);
>         for (T item : list) {
>             if (item.compareTo(smallest) < 0) {
>                 smallest = item;
>             }
>         }
>         return smallest;
>     }
>
>     public static void main(String[] args) {
>         List<Integer> numbers = List.of(5, 2, 9, 1, 7);
>         System.out.println(min(numbers)); // 1
>     }
> }
> ```

---

## Exercise 4.7 — Wildcards: Fix the Compile Error

**Task:** The method below fails to compile when called with a `List<String>` or `List<Integer>`. Fix the signature using a wildcard so it accepts a list of *any* type, while still only reading from it.

```java
public class Exercise4_7_Wildcards {

    public static void printAll(List<Object> list) { // TODO: fix this signature
        for (Object o : list) {
            System.out.println(o);
        }
    }

    public static void main(String[] args) {
        printAll(List.of("Ali", "Sara")); // currently fails to compile
        printAll(List.of(1, 2, 3));       // currently fails to compile
    }
}
```

> [!success]- Solution
> ```java
> import java.util.List;
>
> public class Exercise4_7_Wildcards {
>
>     public static void printAll(List<?> list) {
>         for (Object o : list) {
>             System.out.println(o);
>         }
>     }
>
>     public static void main(String[] args) {
>         printAll(List.of("Ali", "Sara")); // works now
>         printAll(List.of(1, 2, 3));       // works now
>     }
> }
> ```
> **Talking point:** `List<Object>` and `List<String>` are unrelated types (generics are invariant) — `List<?>` is the fix because it means "a list of some unknown type," which matches anything.

---

## Exercise 4.8 — PECS in Practice

**Task:** Write a method `copyAll(List<? extends T> source, List<? super T> destination)` that copies every element from `source` into `destination`. Test it by copying a `List<Integer>` into a `List<Number>`.

```java
public class Exercise4_8_PECS {
    // TODO: implement copyAll using PECS-correct wildcards

    public static void main(String[] args) {
        // TODO: copy a List<Integer> into a List<Number>
    }
}
```

> [!success]- Solution
> ```java
> import java.util.ArrayList;
> import java.util.List;
>
> public class Exercise4_8_PECS {
>
>     public static <T> void copyAll(List<? extends T> source, List<? super T> destination) {
>         for (T item : source) {
>             destination.add(item);
>         }
>     }
>
>     public static void main(String[] args) {
>         List<Integer> integers = List.of(1, 2, 3);
>         List<Number> numbers = new ArrayList<>();
>
>         copyAll(integers, numbers);
>         System.out.println(numbers); // [1, 2, 3]
>     }
> }
> ```
> **Talking point:** `source` is a **producer** of `T` (we only read from it) → `? extends T`. `destination` is a **consumer** of `T` (we only write to it) → `? super T`. This is literally how `Collections.copy` is declared in the JDK.

---

## Exercise 4.9 — Type Erasure: Predict the Output

**Task:** Without running it, predict the output of this code. Then run it and check your prediction.

```java
import java.util.ArrayList;
import java.util.List;

public class Exercise4_9_TypeErasure {
    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        List<Integer> integers = new ArrayList<>();

        System.out.println(strings.getClass() == integers.getClass());
        System.out.println(strings.getClass().getName());
    }
}
```

> [!success]- Solution
> ```
> true
> java.util.ArrayList
> ```
> Both lists are just `ArrayList` at runtime — the `<String>` and `<Integer>` type arguments are erased by the compiler and don't exist as separate runtime types. This is **type erasure**: generics are a compile-time-only feature.

---

## Exercise 4.10 — Capstone: A Type-Safe Generic Stack

**Task:** Build a generic `Stack<T>` class from scratch (backed by an `ArrayList<T>` internally) with:
- `push(T item)`
- `pop()` — removes and returns the top item, throws `NoSuchElementException` if empty
- `peek()` — returns the top item without removing it
- `isEmpty()`

Then write a generic method `<T extends Comparable<T>> T maxInStack(Stack<T> stack)` that empties the stack while finding the maximum element (you may assume this destructive approach is fine for the exercise). Test everything with a `Stack<Integer>`.

```java
class Stack<T> {
    // TODO: implement push, pop, peek, isEmpty using an internal ArrayList<T>
}

public class Exercise4_10_GenericStack {
    // TODO: implement maxInStack using a bounded type parameter

    public static void main(String[] args) {
        // TODO: push several integers, then find and print the max
    }
}
```

> [!success]- Solution
> ```java
> import java.util.ArrayList;
> import java.util.List;
> import java.util.NoSuchElementException;
>
> class Stack<T> {
>     private List<T> items = new ArrayList<>();
>
>     public void push(T item) {
>         items.add(item);
>     }
>
>     public T pop() {
>         if (isEmpty()) throw new NoSuchElementException("Stack is empty");
>         return items.remove(items.size() - 1);
>     }
>
>     public T peek() {
>         if (isEmpty()) throw new NoSuchElementException("Stack is empty");
>         return items.get(items.size() - 1);
>     }
>
>     public boolean isEmpty() {
>         return items.isEmpty();
>     }
> }
>
> public class Exercise4_10_GenericStack {
>
>     public static <T extends Comparable<T>> T maxInStack(Stack<T> stack) {
>         if (stack.isEmpty()) throw new NoSuchElementException("Stack is empty");
>         T max = stack.pop();
>         while (!stack.isEmpty()) {
>             T current = stack.pop();
>             if (current.compareTo(max) > 0) {
>                 max = current;
>             }
>         }
>         return max;
>     }
>
>     public static void main(String[] args) {
>         Stack<Integer> stack = new Stack<>();
>         stack.push(5);
>         stack.push(12);
>         stack.push(3);
>         stack.push(9);
>
>         System.out.println(maxInStack(stack)); // 12
>     }
> }
> ```

---

## Wrap-up Discussion Questions

1. Why does `List<String>` NOT count as a `List<Object>`, even though `String` is an `Object`? What would break if it did?
2. In Exercise 4.8, what would happen if you swapped the wildcards — used `? super T` for `source` and `? extends T` for `destination`? Would it still compile? Would it still make sense?
3. Since generics are erased at runtime (Exercise 4.9), why does the compiler still bother enforcing type safety at compile time? What would Java look like without generics at all — just raw types everywhere?
