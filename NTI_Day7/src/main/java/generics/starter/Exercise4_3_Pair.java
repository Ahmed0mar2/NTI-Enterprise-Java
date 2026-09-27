package generics.starter;

import java.util.ArrayList;
import java.util.List;

// Exercise 4.3 — Build a Generic Pair<K, V>
//
// TODO 1: Define a generic class Pair<K, V> with a constructor(K key, V value),
//         getKey(), getValue(), and a toString() like "key -> value".
//
// TODO 2: Build a List<Pair<String, Integer>> for 3 people's (name, age), and print each.

class Pair<K, V> {
    // TODO 1: implement
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return key + " -> " + value;
    }
}

public class Exercise4_3_Pair {
    public static void main(String[] args) {
        // TODO 2: build and print the list of pairs
        List<Pair<String, Integer>> people = List.of(new Pair<>("Alex", 22), new Pair<>("John", 30), new Pair<>("Miles", 25));
        people.forEach(System.out::println);

    }
}
