
import java.util.List;

// Exercise 2.3 — Build a Mini "Pipeline" (No Streams Yet)
//
// TODO 1: Create a Function<String, Double> called "parser" using a METHOD REFERENCE
//         that converts a String to a Double (hint: Double::valueOf)
// TODO 2: Create a Consumer<Double> called "printer" using a METHOD REFERENCE
//         that prints a value (hint: System.out::println)
// TODO 3: Loop over rawPrices, use parser to convert each, then printer to print it.


void main() {

    List<String> rawPrices = List.of("19.99", "5.50", "100.00", "3.25");

    // TODO 1 & 2: define parser and printer as method references
    Function<String, Double> parser = Double::valueOf;
    Consumer<Double> printer = System.out::println;
    for (String rawPrice : rawPrices) {
        printer.accept(parser.apply(rawPrice));
    }
    // TODO 3: loop over rawPrices and apply parser then printer
}