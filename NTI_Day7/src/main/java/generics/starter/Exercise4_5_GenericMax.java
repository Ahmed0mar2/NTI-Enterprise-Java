package generics.starter;
// Exercise 4.5 — Bounded Type Parameter: Generic max
//
// TODO 1: Implement a static generic method <T extends Comparable<T>> T max(T a, T b)
//         that returns the larger of the two values.
// TODO 2: Test it with two Integers and two Strings.

public class Exercise4_5_GenericMax {

    // TODO 1: implement max with a bounded type parameter
    static <T extends Comparable<T>> T max(T a, T b) {
        return a.compareTo(b) < 0 ? b : a;
    }

    public static void main(String[] args) {
        // TODO 2: test with Integers and Strings
        System.out.println(max("amany", "mahmoud"));
        System.out.println(max(1, 10));
    }
}
