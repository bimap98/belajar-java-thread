package programmer.zaman.now.thread;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.*;

public class CompletableFutureTest {

    private ExecutorService executorService = Executors.newFixedThreadPool(10);

    private Random random = new Random();

    public CompletableFuture<String> getValue(){
        CompletableFuture<String> future = new CompletableFuture<>();
        executorService.execute(()-> {
            try {
                Thread.sleep(2_000);
                future.complete("Bima Putra Lamanda");
            } catch (InterruptedException e) {
                future.completeExceptionally(e);
            }

        });
        
        return future;
    }

    @Test
    void create() throws ExecutionException, InterruptedException {
        Future<String> future = getValue();
        System.out.println(future.get());
    }

    private void execute(CompletableFuture<String> future, String value){
        executorService.execute(() -> {
            try {
                Thread.sleep(1_000 + random.nextInt(5_000));
                future.complete(value);
            } catch (InterruptedException e) {
                future.completeExceptionally(e);
            }

        });
    }

    public Future<String> getFastest(){
        CompletableFuture<String> future = new CompletableFuture<>();

        execute(future, "Thread 1");
        execute(future, "Thread 2");
        execute(future, "Thread 3");

        return future;

    }

    @Test
    void testFastest() throws ExecutionException, InterruptedException {
        System.out.println(getFastest().get());
    }

    @Test
    void completionStage() throws ExecutionException, InterruptedException {
        CompletableFuture<String[]> completableFuture = getValue()
                .thenApply(string -> string.toUpperCase())
                .thenApply(string -> string.split(" "));

        for (var string : completableFuture.get()){
            System.out.println(string);
        }
    }
}
