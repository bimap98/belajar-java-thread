package programmer.zaman.now.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CountDownLatchTest {

    @Test
    void test() throws InterruptedException {
        final var countDownLatch = new CountDownLatch(5);
        final var executors = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 5; i++) {
            executors.execute(() -> {
                try {
                    System.out.println("Start Task");
                    Thread.sleep(2_000);
                    System.out.println("Finish Task");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }

        executors.execute(() -> {
            try {
                countDownLatch.await();
                System.out.println("Finish All Task");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executors.shutdown();
        executors.awaitTermination(1, TimeUnit.DAYS);
    }
}
