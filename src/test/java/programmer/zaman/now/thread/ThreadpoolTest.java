package programmer.zaman.now.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadpoolTest {

    @Test
    void create() {
        var minThread = 5;
        var maxThread = 20;
        var alive = 1;
        var time = TimeUnit.MINUTES;

        var queue = new ArrayBlockingQueue<Runnable>(100);

        var threadpool = new ThreadPoolExecutor(minThread, maxThread, alive, time, queue);

    }

    @Test
    void executeRunnable() throws InterruptedException {
        var minThread = 5;
        var maxThread = 20;
        var alive = 1;
        var time = TimeUnit.MINUTES;

        var queue = new ArrayBlockingQueue<Runnable>(100);

        var executor = new ThreadPoolExecutor(minThread, maxThread, alive, time, queue);

        Runnable runnable = () -> {
            try {
                Thread.sleep(2_000);
                System.out.println("From Thread : " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        executor.execute(runnable);
        Thread.sleep(5_000);

    }

    @Test
    void shutdown() throws InterruptedException {
        var minThread = 5;
        var maxThread = 20;
        var alive = 1;
        var time = TimeUnit.MINUTES;

        var queue = new ArrayBlockingQueue<Runnable>(100);

        var executor = new ThreadPoolExecutor(minThread, maxThread, alive, time, queue);

        for (int i = 0; i < 100; i++) {
            final var task = i;
            Runnable runnable = () -> {
                try {
                    Thread.sleep(1_000);
                    System.out.println("Task " + task + " From thread : " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            executor.execute(runnable);
        }
//        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.DAYS);

    }

    @Test
    void rejected() throws InterruptedException {
        var minThread = 5;
        var maxThread = 20;
        var alive = 1;
        var time = TimeUnit.MINUTES;
        var handler = new LogRejectedExecutionHandler();

        var queue = new ArrayBlockingQueue<Runnable>(10);

        var executor = new ThreadPoolExecutor(minThread, maxThread, alive, time, queue, handler);

        for (int i = 0; i < 100; i++) {
            final var task = i;
            Runnable runnable = () -> {
                try {
                    Thread.sleep(1_000);
                    System.out.println("Task " + task + " From thread : " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            executor.execute(runnable);
        }
//        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.DAYS);

    }

    public static class LogRejectedExecutionHandler implements RejectedExecutionHandler{

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println("Task :  " + r + " is rejected");
        }
    }
}
