package day6;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolExample {
    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);

        // Scheduled a task to run every 1 second, with an initial delay of 0 seconds
        executor.scheduleAtFixedRate(() -> {
            System.out.println("Scheduled task executed by " + Thread.currentThread().getName());
        }, 0, 1, TimeUnit.SECONDS);

        // Task will execute for 3 seconds
        Thread.sleep(3000);

        // shutdown the scheduler gracefully
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.SECONDS);
        System.out.println("Scheduler shutdown");
    }
}
