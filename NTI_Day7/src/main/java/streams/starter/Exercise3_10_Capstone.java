package streams.starter;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// Exercise 3.10 — Capstone: Full Pipeline
//
// TODO 1: Build a pipeline that: keeps only paid orders, sorts by total descending,
//         extracts customer names, removes duplicates, collects into a List<String>.
// TODO 2: Separately, compute the average total of only the paid orders
//         using Collectors.averagingDouble.

public class Exercise3_10_Capstone {

    static class Order {
        String customerName;
        double total;
        boolean isPaid;

        Order(String customerName, double total, boolean isPaid) {
            this.customerName = customerName;
            this.total = total;
            this.isPaid = isPaid;
        }
    }

    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("Ali", 250.0, true),
                new Order("Sara", 80.0, false),
                new Order("Omar", 500.0, true),
                new Order("Ali", 120.0, true),
                new Order("Ahmed", 90.0, true)
        );

        // TODO 1: build the pipeline described above, print the result
        List<Order> pipe = orders.stream().
                filter(o -> o.isPaid).
                distinct().
                sorted(Comparator.comparing((Order o) -> o.total)
                        .reversed())
                .toList();
        // TODO 2: compute and print the average total of paid orders
        System.out.println(orders.stream()
                .filter(o -> o.isPaid)
                .collect(Collectors.averagingDouble(o -> o.total)));
    }
}
