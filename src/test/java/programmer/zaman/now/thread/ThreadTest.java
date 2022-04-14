package programmer.zaman.now.thread;

import org.junit.jupiter.api.Test;

public class ThreadTest {

    @Test
    void mainThread() {
        String thread = Thread.currentThread().getName();
        System.out.println(thread);
    }

    @Test
    void createThread() {
        Runnable runnable = () -> {
            System.out.println("Hello from Thread : " + Thread.currentThread().getName());
        };

        Thread thread = new Thread(runnable);
        thread.start();

        System.out.println("Program selesai");

    }

    @Test
    void threadSleep() throws InterruptedException {
        Runnable runnable = () -> {
            try {
                Thread.sleep(2_000);
                System.out.println("Hello from Thread : " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

        System.out.println("Program selesai");
        Thread.sleep(3_000);
    }

    @Test
    void threadJoin() throws InterruptedException {
        Runnable runnable = () -> {
            try {
                Thread.sleep(2_000);
                System.out.println("Hello from Thread : " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        System.out.println("Menunggu selesai");
        thread.join();
        System.out.println("Program selesai");
    }

    @Test
    void testInterrupt() throws InterruptedException {
        Runnable runnable = () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Runnable : " + i);
                try {
                    Thread.sleep(1_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5_000);
        thread.interrupt();
        System.out.println("Menunggu selesai");
        thread.join();
        System.out.println("Program selesai");
    }

    @Test
    void testInterruptCorrect() throws InterruptedException {
        Runnable runnable = () -> {
            for (int i = 0; i < 10; i++) {
                // manual check interrupted
                if (Thread.interrupted()){
                    return;
                }
                System.out.println("Runnable : " + i);
                try {
                    Thread.sleep(1_000);
                } catch (InterruptedException e) {
                    break;
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5_000);
        thread.interrupt();
        System.out.println("Menunggu selesai");
        thread.join();
        System.out.println("Program selesai");
    }

    @Test
    void threadName() {
        var thread = new Thread(() -> {
            System.out.println("Hello from thread " + Thread.currentThread().getName());
        });

        thread.setName("Bima");
        thread.start();

    }

    @Test
    void threadState() throws InterruptedException {
        var thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getState());
            System.out.println("Hello from thread " + Thread.currentThread().getName());
        });
        thread.setName("Bima");
        System.out.println(thread.getState());
        thread.start();
        thread.join();;
        System.out.println(thread.getState());
    }
}
