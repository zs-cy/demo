package thread;

import java.util.concurrent.*;

class MyThread implements Callable<Integer>{
    @Override
    public Integer call() throws Exception {
        System.out.println("Hi~ o(*￣▽￣*)ブ come in  callable");
        TimeUnit.SECONDS.sleep(10);
        return 1024;
    }
}

public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask=new FutureTask<>(new MyThread());
        Thread thread=new Thread(futureTask);
        thread.start();
        try {
            System.out.println("result:"+futureTask.get(3,TimeUnit.SECONDS));
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}


