package assignment.coffe_shop_order_queue;

public class Barista implements Runnable {

    private OrderQueue queue;

    public Barista(OrderQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            queue.addOrder("Order no. " + i);
            System.out.println(Thread.currentThread().getName() + " made: " + "Order no. " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
    }
}
