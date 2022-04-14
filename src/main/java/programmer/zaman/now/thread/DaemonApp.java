package programmer.zaman.now.thread;

public class DaemonApp {

    public static void main(String[] args) {

        var thread = new Thread(()-> {
            try {
                Thread.sleep(3_000);
                System.out.println("Run Thread");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread.setDaemon(true);
        thread.start();

    }

}
