package streams.starter;

import java.util.List;

// Exercise 3.9 — Mini Challenge: Sum of Squares of Numbers > 10
//
// TODO: filter numbers > 10, square them with mapToInt, then sum().

public class Exercise3_9_SumOfSquares {

    public static void main(String[] args) {
        List<Integer> numbers = List.of(5, 12, 8, 3, 20, 15, 7);

        // TODO: implement and print the result

        System.out.println(numbers.stream().filter(n -> n > 10).mapToInt(n -> n * n).sum());
        // Expected: 12*12 + 20*20 + 15*15 = 769
    }
}
