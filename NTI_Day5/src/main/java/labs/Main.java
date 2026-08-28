package labs;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        MyThread task = new MyThread();

        Thread worker1 = new Thread(task, "Worker-1");
        Thread worker2 = new Thread(task, "Worker-2");

        worker1.start();
        worker2.start();
        worker1.join();
        worker2.join();

        System.out.println("Both threads are done");
    }
}
