package streams.starter;

import java.util.List;

// Exercise 3.6 — anyMatch / allMatch / noneMatch
//
// TODO 1: Is there any password longer than 7 characters?
// TODO 2: Do all passwords have at least 5 characters?
// TODO 3: Is there no password that equals exactly "admin"?

public class Exercise3_6_MatchOperations {

    public static void main(String[] args) {
        List<String> passwords = List.of("abc123", "password", "Xy9!zK2q", "12345");

        // TODO 1, 2, 3: implement and print all three checks
        System.out.println(passwords.stream().anyMatch(p -> p.length() > 7));
        System.out.println(passwords.stream().allMatch(p -> p.length() >= 5));
        System.out.println(passwords.stream().noneMatch(p -> p.equals("admin")));
    }
}
