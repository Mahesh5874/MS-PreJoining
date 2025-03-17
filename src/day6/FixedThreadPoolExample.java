package day6;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FixedThreadPoolExample {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        // Submitting 5 tasks
        for (int i = 0; i < 5; i++) {
            int taskNumber = i;
            executor.submit(() -> {
                System.out.println("Task " + taskNumber + " executed by " + Thread.currentThread().getName());
            });
        }

        //Initiate graceful shutdown
        executor.shutdown();

        if (executor.awaitTermination(5, TimeUnit.SECONDS)) {
            System.out.println("All task completed");
        } else {
            System.out.println("Timeout: some tasks did not finish");
        }

    }
}
