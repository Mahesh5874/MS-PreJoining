package day7;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class CompFutureExample {
    private static final ExecutorService executor = Executors.newFixedThreadPool(5);

    public static void main(String[] args) {
        List<String> sources = Arrays.asList("Source1", "Source2", "Source3", "Source4", "Source5");
        String fromCurrency = "USD";
        String toCurrency = "INR";

        // Fetch all exchange rates asynchronously
        Map<String, CompletableFuture<Double>> futureRates = sources.stream()
                .collect(Collectors.toMap(src -> src, src -> fetchExchangeRate(src, fromCurrency, toCurrency)));

        // Get the fastest response
        CompletableFuture<Object> fastest = CompletableFuture.anyOf(futureRates.values().toArray(new CompletableFuture[0]));
        fastest.thenAccept(rate -> System.out.println("Fastest exchange rate received: " + rate));

        // Aggregate all responses
        CompletableFuture<Void> allDone = CompletableFuture.allOf(futureRates.values().toArray(new CompletableFuture[0]));

        allDone.thenRun(() -> {
            Map<String, Double> exchangeRates = futureRates.entrySet().stream()
                    .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().join()));
            System.out.println("Aggregated exchange rates: " + exchangeRates);
        }).join();

        executor.shutdown();

    }

    private static CompletableFuture<Double> fetchExchangeRate(String source, String from, String to) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                // Simulating network call delay
                Thread.sleep(new Random().nextInt(3000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 80 + new Random().nextDouble(); // Mock exchange rate
        }, executor);
    }
}