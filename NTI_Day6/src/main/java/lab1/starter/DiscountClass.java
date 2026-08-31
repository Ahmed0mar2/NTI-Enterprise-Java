// Exercise 1.2 — Write Your Own Functional Interface
//
// TODO 1: Define a functional interface called Discount with one abstract method:
//         double apply(double price);
//
// TODO 2: Implement THREE discounts using ANONYMOUS CLASSES:
//         1. 10% off
//         2. A flat $5 off
//         3. No discount at all
//
// TODO 3: Apply each discount to a $100 item and print the result.


package lab1.starter;

public class DiscountClass {

    // TODO 1: define the Discount interface here

    public static void main(String[] args) {
        double price = 100;
        Discount tenPercent = new Discount() {
            @Override
            public double apply(double price) {
                return price - 0.10 * price;
            }
        };
        Discount fiveFlatDiscount = new Discount() {
            @Override
            public double apply(double price) {
                return price - 5;
            }
        };

        Discount noDiscount = new Discount() {
            @Override
            public double apply(double price) {
                return price;
            }
        };
        System.out.println("Applying 10% discount on " + price + " = " + tenPercent.apply(price));
        System.out.println("Applying 5$ flat discount on " + price + " = " + fiveFlatDiscount.apply(price));
        System.out.println("Applying no discount on " + price + " = " + noDiscount.apply(price));


        // TODO 2 & 3: create the three anonymous class implementations and print results
    }
}
