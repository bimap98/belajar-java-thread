package programmer.zaman.now.thread;

public class UserService {

    private final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    private String user;

    public void setUser(String user){
//        this.user = user;
        threadLocal.set(user);
    }

    public void doAction(){
        var user = threadLocal.get();
        System.out.println(user + " do action");

    }

}
