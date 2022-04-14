package programmer.zaman.now.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Exchanger;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExchangerTest {

    @Test
    void test() throws InterruptedException {

        final var exchanger = new Exchanger<String>();
        final var executor = Executors.newFixedThreadPool(5);

        executor.execute(() -> {
            try {
                System.out.println("Thread 1 send : First");
                Thread.sleep(2_000);
                var value = exchanger.exchange("First");
                System.out.println("Thread 1 receive : " + value);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executor.execute(() -> {
            try {
                System.out.println("Thread 2 send : Second");
                var value = exchanger.exchange("Second");
                System.out.println("Thread 2 receive : " + value);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.DAYS);
    }
}