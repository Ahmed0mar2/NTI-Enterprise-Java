package assignment.coffe_shop_order_queue;

public class Customer implements Runnable {

    private OrderQueue queue;

    public Customer(OrderQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Customer " + Thread.currentThread().getName() + ": " + "got " + queue.takeOrder());
        }
    }
}
