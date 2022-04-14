package programmer.zaman.now.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class PhaserTest {

    @Test
    void cyclicBarier() throws InterruptedException {
        final var phaser = new Phaser();
        final var executor = Executors.newFixedThreadPool(5);

        phaser.bulkRegister(5);
        for (int i = 0; i < 5; i++) {
            executor.execute(() -> {
                try {
                    System.out.println("Start task");
                    Thread.sleep(2_000);
                    System.out.println("End task");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    phaser.arrive();
                }
            });
        }

        executor.execute(() ->{
            phaser.awaitAdvance(0);
            System.out.println("All tasks done");
        });

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.DAYS);
    }

    @Test
    void countDownLatch() throws InterruptedException {
        var phaser = new Phaser();
        var executor = Executors.newFixedThreadPool(5);

        phaser.bulkRegister(5);
        for (int i = 0; i < 5; i++) {
            executor.execute(() -> {
                try {
                    System.out.println("Waiting");
                    Thread.sleep(2_000);
                    phaser.arriveAndAwaitAdvance();
                    System.out.println("Done Waiting");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.DAYS);

    }
}
