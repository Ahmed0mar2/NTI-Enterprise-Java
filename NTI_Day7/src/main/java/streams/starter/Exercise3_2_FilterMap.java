package streams.starter;

import java.util.List;
import java.util.stream.Collectors;

// Exercise 3.2 — filter and map
//
// TODO: Produce a list of UPPERCASE versions of all words with more than 4 characters.

public class Exercise3_2_FilterMap {

    public static void main(String[] args) {
        List<String> words = List.of("apple", "kiwi", "banana", "fig", "grape");

        // TODO: filter + map + collect, then print the result
        List<String> result = words.stream().filter(w -> w.length() > 4).map(String::toUpperCase).toList();
        System.out.println(result);
    }
}
