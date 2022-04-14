package programmer.zaman.now.thread;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FutureTest {

    @Test
    void create() throws ExecutionException, InterruptedException {

        var executor = Executors.newSingleThreadExecutor();
        var future = executor.submit(() -> {
            Thread.sleep(5_000);
            return "hi";
        });

        System.out.println("Ini Feature");

        while (!future.isDone()){
            System.out.println("Waiting");
            Thread.sleep(1_000);
        }

        System.out.println(future.get());

    }

    @Test
    void cancel() throws ExecutionException, InterruptedException {

        var executor = Executors.newSingleThreadExecutor();
        var future = executor.submit(() -> {
            Thread.sleep(5_000);
            return "hi";
        });

        System.out.println("Ini Feature");

        Thread.sleep(2_000);
        future.cancel(true);

        System.out.println(future.get());

    }

    @Test
    void invokeAll() throws InterruptedException, ExecutionException {
        var executor = Executors.newFixedThreadPool(10);
        List<Callable<String>> callables = IntStream.range(1, 11).mapToObj(value -> (Callable<String>) () -> {
            Thread.sleep(value * 500L);
            return String.valueOf(value);
        }).collect(Collectors.toList());

        List<Future<String>> futures = executor.invokeAll(callables);
        for (Future<String> stringFuture : futures){
            System.out.println(stringFuture.get());
        }

    }

    @Test
    void invokeAny() throws InterruptedException, ExecutionException {
        var executor = Executors.newFixedThreadPool(10);
        List<Callable<String>> callables = IntStream.range(1, 11).mapToObj(value -> (Callable<String>) () -> {
            Thread.sleep(value * 500L);
            return String.valueOf(value);
        }).collect(Collectors.toList());

        String any = executor.invokeAny(callables);
        System.out.println(any);


    }
}
