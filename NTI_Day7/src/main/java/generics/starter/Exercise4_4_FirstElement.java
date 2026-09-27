package generics.starter;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

// Exercise 4.4 — Write a Generic Method: firstElement
//
// TODO 1: Implement a static generic method <T> T firstElement(List<T> list)
//         that returns the first element, throwing NoSuchElementException if empty.
// TODO 2: Test it with a List<String> and a List<Integer>.

public class Exercise4_4_FirstElement {

    // TODO 1: implement firstElement
    static <T> T firstElement(List<T> list) {
        if (!list.isEmpty()) {
            return list.getFirst();
        } else {
            throw new NoSuchElementException();
        }
    }

    public static void main(String[] args) {
        // TODO 2: test with List<String> and List<Integer>
        List<String> strings = List.of("first", "second", "third");
        //List<Integer> integers = List.of(1, 2, 3);
        List<Integer> integers = new ArrayList<>();

        System.out.println(firstElement(strings));
        System.out.println(firstElement(integers));
    }
}
