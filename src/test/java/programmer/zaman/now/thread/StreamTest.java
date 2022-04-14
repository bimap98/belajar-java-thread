package programmer.zaman.now.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTest {

    @Test
    void parallel() {
        Stream<Integer> stream = IntStream.range(0, 10).boxed();
        stream.parallel().forEach(value -> System.out.println(Thread.currentThread().getName() + ":" + value));

    }

    @Test
    void custom() throws ExecutionException, InterruptedException {
        ForkJoinPool pool = new ForkJoinPool(5);
        ForkJoinTask<?> task = pool.submit(() -> {
            Stream<Integer> stream = IntStream.range(0, 10).boxed();
            stream.parallel().forEach(value -> System.out.println(Thread.currentThread().getName() + ":" + value));
        });

        task.get();
    }
}
