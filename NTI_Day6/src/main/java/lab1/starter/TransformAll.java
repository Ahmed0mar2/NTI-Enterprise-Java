package lab1.starter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;

// Exercise 1.6 — Passing Behavior Into a Method
//
// TODO 1: Implement transformAll(input, transformer):
//         it should apply "transformer" to every element and return a NEW list.

//
// TODO 2: Call transformAll twice on the "words" list:
//         - once to uppercase every word
//         - once to reverse every word (hint: new StringBuilder(w).reverse().toString())

public class TransformAll {

    static List<String> transformAll(List<String> input, Function<String, String> transformer) {
        // TODO 1: implement this method
        List<String> result = new ArrayList<>();
        for (String s : input) {
            result.add(transformer.apply(s));
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> words = List.of("java", "lambda", "stream");
        System.out.println(transformAll(words, String::toUpperCase));
        // TODO 2: call transformAll twice and print both results
        System.out.println(transformAll(words, (s) -> new StringBuilder(s).reverse().toString()));
    }
}
