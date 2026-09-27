package streams.starter;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

// Exercise 3.3 — flatMap
//
// TODO: Flatten the list of teams into a single flat List<String> of all members.

public class Exercise3_3_FlatMap {

    public static void main(String[] args) {
        List<List<String>> teams = List.of(
                List.of("Ali", "Sara"),
                List.of("Omar"),
                List.of("Lina", "Ahmed", "Nour")
        );

        // TODO: flatMap into one flat list, then print it

        List<String> list = teams.stream().flatMap(Collection::stream).toList();
        System.out.println(list);
    }
}
