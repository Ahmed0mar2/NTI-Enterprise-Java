// Exercise 1.3 — Convert Anonymous Classes to Lambdas
//
// TODO: Rewrite the three Discount implementations from Exercise 1.2 as LAMBDAS instead
//       of anonymous classes. Same behavior, less code.
package lab1.starter;

public class DiscountLambdas {


    public static void main(String[] args) {
        double price = 200;

        // TODO: tenPercentOff as a lambda
        Discount tenPercentOff = p -> p - 0.10 * p;
        // TODO: fiveFlatOff as a lambda
        Discount fiveFlatOff = p -> p - 5;
        // TODO: noDiscount as a lambda
        Discount noDiscount = p -> p;
        // TODO: print all three results
        System.out.println("Applying 10% discount on " + price + " = " + tenPercentOff.apply(price));
        System.out.println("Applying 5$ flat discount on " + price + " = " + fiveFlatOff.apply(price));
        System.out.println("Applying no discount on " + price + " = " + noDiscount.apply(price));
    }
}
