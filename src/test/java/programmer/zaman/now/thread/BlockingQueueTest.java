package programmer.zaman.now.thread;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.concurrent.*;

public class BlockingQueueTest {

    @Test
    void arrayBlockingQueue() throws InterruptedException {
        final var queue = new ArrayBlockingQueue<String>(5);
        final var executor = Executors.newFixedThreadPool(20);

        for (int i = 0; i < 7; i++) {
            executor.execute(() -> {
                try {
                    queue.put("Data");
                    System.out.println("Finish put data");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        executor.execute(() -> {
            while (true){
                try {
                    var value = queue.take();
                    Thread.sleep(1_000);
                    System.out.println("Receive " + value);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        executor.awaitTermination(1, TimeUnit.DAYS);

    }

    @Test
    void linkedBlockingQueue() throws InterruptedException {
        final var queue = new LinkedBlockingQueue<>();
        final var executor = Executors.newFixedThreadPool(20);

        for (int i = 0; i < 10; i++) {
            executor.execute(() -> {
                try {
                    queue.put("Data");
                    System.out.println("Finish put data");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        executor.execute(() -> {
            while (true){
                try {
                    var value = queue.take();
                    Thread.sleep(1_000);
                    System.out.println("Receive " + value);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        executor.awaitTermination(1, TimeUnit.DAYS);

    }

    @Test
    void priorityBlockingQueue() throws InterruptedException {
        final var queue = new PriorityBlockingQueue<String>(10, Comparator.reverseOrder());
        final var executor = Executors.newFixedThreadPool(20);

        for (int i = 0; i < 10; i++) {
            final var index = i;
            executor.execute(() -> {
                queue.put("Data " + index);
                System.out.println("Finish put data " + index);
            });
        }

        executor.execute(() -> {
            while (true){
                try {
                    var value = queue.take();
                    Thread.sleep(1_000);
                    System.out.println("Receive " + value);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        executor.awaitTermination(1, TimeUnit.DAYS);

    }

    @Test
    void delayedQueue() throws InterruptedException {
        final var queue = new DelayQueue<ScheduledFuture<String>>();
        final var executor = Executors.newScheduledThreadPool(10);

        for (int i = 0; i < 5; i++) {
            final var index = i;
            queue.put(executor.schedule(() -> "Delayed " + index, i, TimeUnit.SECONDS));
        }

        executor.execute(() -> {
            while (true){
                try {
                    var value = queue.take();
                    Thread.sleep(1_000);
                    System.out.println("Receive " + value.get());
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });

        executor.awaitTermination(1, TimeUnit.DAYS);

    }

    @Test
    void synchronousQueue() throws InterruptedException {
        final var queue = new SynchronousQueue<String>();
        final var executor = Executors.newFixedThreadPool(20);

        for (int i = 0; i < 10; i++) {
            final var index = i;
            executor.execute(() -> {
                try {
                    queue.put("Data " + index);
                    System.out.println("Finish put data " + index);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        executor.execute(() -> {
            while (true){
                try {
                    var value = queue.take();
                    Thread.sleep(1_000);
                    System.out.println("Receive " + value);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        executor.awaitTermination(1, TimeUnit.DAYS);

    }

    @Test
    void blockingDeque() throws InterruptedException {
        final var queue = new LinkedBlockingDeque<>();
        final var executor = Executors.newFixedThreadPool(20);

        for (int i = 0; i < 10; i++) {
            final var index = i;
            executor.execute(() -> {
                try {
                    queue.putLast("Data " + index);
                    System.out.println("Finish put data " + index);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        executor.execute(() -> {
            while (true){
                try {
                    var value = queue.takeLast();
                    Thread.sleep(1_000);
                    System.out.println("Receive " + value);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        executor.awaitTermination(1, TimeUnit.DAYS);

    }

    @Test
    void linkedTransferQueue() throws InterruptedException {
        final var queue = new LinkedTransferQueue<>();
        final var executor = Executors.newFixedThreadPool(20);

        for (int i = 0; i < 10; i++) {
            final var index = i;
            executor.execute(() -> {
                try {
                    queue.transfer("Data " + index);
                    System.out.println("Finish put data " + index);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        executor.execute(() -> {
            while (true){
                try {
                    var value = queue.take();
                    Thread.sleep(1_000);
                    System.out.println("Receive " + value);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        executor.awaitTermination(1, TimeUnit.DAYS);

    }
}
