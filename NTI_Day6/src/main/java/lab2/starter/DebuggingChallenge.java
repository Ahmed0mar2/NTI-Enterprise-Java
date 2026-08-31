package lab2.starter;

import java.util.function.BiFunction;

// Exercise 2.5 — Debugging Challenge (Trickiest One)
//
// TODO: Before running this, PREDICT what "result" will be, and whether this
//       even compiles. Then run it and check your prediction.
//       Write your reasoning as a comment below explaining which of the
//       4 method reference types String::concat is using.

public class DebuggingChallenge {

    public static void main(String[] args) {
        BiFunction<String, String, String> concat = String::concat; // class
        // (s,t) -> s.concat(t);
        String result = concat.apply("Hello, ", "World");
        System.out.println(result);

        // TODO: write your explanation here as a comment
        //how the method ref would decide which string goes first or which calls the function
        // and the other one is a parameter so i think it won't compile as you're sending both as a parameters

        //correct:
        // String::concat is an unbound instance method reference.
        // BiFunction<String, String, String> expects two Strings.
        // The first String becomes the object that calls concat(),
        // and the second String becomes concat()'s parameter.
        //
        // Therefore:
        // String::concat
        // is equivalent to:
        // (s, t) -> s.concat(t)
        //
        // concat.apply("Hello, ", "World")
        // becomes:
        // "Hello, ".concat("World")
        //
        // Result: "Hello, World"
    }
}
