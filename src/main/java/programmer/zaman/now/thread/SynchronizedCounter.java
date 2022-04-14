package programmer.zaman.now.thread;

public class SynchronizedCounter {

    private Long counter = 0L;

    public synchronized void increment(){

        synchronized (this){
            counter++;
        }

    }

    public Long getValue(){
        return counter;
    }

}
