package lab2.starter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

// Exercise 2.2 — Convert Lambdas to Method References
//
// TODO: Rewrite each lambda below as a method reference. Uncomment each line
//       and replace the lambda with the equivalent method reference.

public class ConvertToMethodRefs {

    public static void main(String[] args) {
        String greeting = "  Hello  ";

        //Function<String, Integer> a = s -> Integer.valueOf(s);
        Function<String, Integer> a = Integer::valueOf; //class
        //Supplier<String> b = () -> greeting.trim();
        Supplier<String> b = greeting::trim;    // instance
        //Function<String, Integer> c = s -> s.length();
        Function<String, Integer> c = String::length;
        //Supplier<List<String>> d = () -> new ArrayList<>();
        Supplier<List<String>> d = ArrayList::new; //constructor
        //BinaryOperator<Integer> e = (x, y) -> Math.max(x, y);
        BinaryOperator<Integer> e = Math::max; //static
        //Consumer<String> f = s -> System.out.println(s);
        Consumer<String> f = System.out::println;
        // Function<String, Integer> g = s -> s.hashCode();
        Function<String, Integer> g = Object::hashCode;

        // TODO: replace each lambda above with its equivalent method reference,
        //       then run this main method to confirm the output is unchanged.

        System.out.println(a.apply("42"));
        System.out.println(b.get());
        System.out.println(c.apply("hello"));
        System.out.println(d.get());
        System.out.println(e.apply(3, 7));
        f.accept("printed via reference");
        System.out.println(g.apply("hello"));
    }
}
