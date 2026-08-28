package assignment.coffe_shop_order_queue;

public class Main {
    public static void main(String[] args) {

        OrderQueue queue = new OrderQueue(3);

        Thread barista1 = new Thread(new Barista(queue), "Barista-1");
        Thread barista2 = new Thread(new Barista(queue), "Barista-2");

        Thread customer1 = new Thread(new Customer(queue), "Customer-1");
        Thread customer2 = new Thread(new Customer(queue), "Customer-2");

        barista1.start();
        barista2.start();
        customer1.start();
        customer2.start();

        try {
            barista1.join();
            barista2.join();
            customer1.join();
            customer2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Shop closed");
    }
}
