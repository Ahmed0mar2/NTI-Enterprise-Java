package generics.starter;
// Exercise 4.2 — Build a Generic Box<T>
//
// TODO 1: Define a generic class Box<T> with:
//         - a private field of type T
//         - set(T value)
//         - get() returning T
//         - isEmpty() returning true if the content is null
//
// TODO 2: In main, create a Box<String> and a Box<Integer>, and exercise all the methods.

class Box<T> {
    // TODO 1: implement
    private T side;

    public void setSide(T side) {
        this.side = side;
    }

    public T getSide() {
        return side;
    }

    public boolean isEmpty() {
        return side == null;
    }
}

public class Exercise4_2_Box {
    public static void main(String[] args) {
        // TODO 2: create Box<String> and Box<Integer>, use set/get/isEmpty
        Box<String> stringBox = new Box<>();
        Box<Integer> IntegerBox = new Box<>();

        stringBox.setSide("example");
        IntegerBox.setSide(1);

        System.out.println(stringBox.getSide());
        System.out.println(IntegerBox.getSide());
        stringBox = new Box<>();
        System.out.println(stringBox.isEmpty());
    }
}
