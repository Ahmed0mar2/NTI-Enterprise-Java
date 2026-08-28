package assignment.coffe_shop_order_queue;

import java.util.LinkedList;

public class OrderQueue {
    private final LinkedList<String> orders = new LinkedList<>();
    private final int capacity;
    private final Object lock = new Object();

    public OrderQueue(int capacity) {
        this.capacity = capacity;
    }


    public void addOrder(String order) {
        synchronized (lock) {
            while (orders.size() == capacity) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            }
            orders.offer(order);
            lock.notifyAll();

        }
    }

    public String takeOrder() {
        synchronized (lock) {
            while (orders.isEmpty()) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            }

            String order = orders.poll();
            lock.notifyAll();
            return order;
        }
    }
}
