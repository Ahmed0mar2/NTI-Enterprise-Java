package generics.starter;

import java.util.*;

// Exercise 4.6 — Bounded Type Parameter: Generic min for a List
//
// TODO 1: Implement a static generic method <T extends Comparable<T>> T min(List<T> list)
//         that returns the smallest element in a non-empty list.
// TODO 2: Test it with a List<Integer>.

public class Exercise4_6_GenericMin {

    // TODO 1: implement min
    static <T extends Comparable<T>> T min(List<T> list) {
        if (!list.isEmpty()) {
            return Collections.min(list);
        } else {
            throw new NoSuchElementException();
        }

    }

    public static void main(String[] args) {
        // TODO 2: test with a List<Integer>
        List<Integer> integers = List.of(64, 34, 232, 22, 5235);
        System.out.println(min(integers));
    }
}
