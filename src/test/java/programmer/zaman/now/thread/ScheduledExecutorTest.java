package programmer.zaman.now.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorTest {

    @Test
    void delayJob() throws InterruptedException {
        var executor = Executors.newScheduledThreadPool(10);
        executor.schedule(() -> System.out.println("Delay Job"), 2, TimeUnit.SECONDS);

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.DAYS);
    }

    @Test
    void periodicJob() throws InterruptedException {
        var executor = Executors.newScheduledThreadPool(10);
        executor.scheduleAtFixedRate(() -> System.out.println("Periodic Job"), 2, 2, TimeUnit.SECONDS);

//        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.DAYS);
    }
}
