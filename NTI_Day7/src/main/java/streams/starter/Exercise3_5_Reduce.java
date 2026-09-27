package streams.starter;

import java.util.List;
import java.util.Optional;

// Exercise 3.5 — reduce
//
// TODO 1: Use reduce (with identity 0) to compute the total sum of prices.
// TODO 2: Use reduce (no identity) to find the maximum price - result is an Optional.

public class Exercise3_5_Reduce {

    public static void main(String[] args) {
        List<Integer> prices = List.of(20, 15, 30, 10, 25);

        // TODO 1: total sum
        System.out.println(prices.stream().reduce(0, (sum, price) -> sum += price));
        // TODO 2: max price via reduce, print using ifPresent
        prices.stream().reduce(Integer::max).ifPresent(System.out::println);
    }
}
