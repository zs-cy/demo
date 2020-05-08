package thread;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyThreadPoolDemo {
    public static void main(String[] args) {

        System.out.println(Runtime.getRuntime().availableProcessors());

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,5,
                1L, TimeUnit.SECONDS,new LinkedBlockingDeque<>(3),
                new ThreadPoolExecutor.CallerRunsPolicy());
        try {
            for (int i = 1; i <=9 ; i++) {
                threadPoolExecutor.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t 办理业务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPoolExecutor.shutdown();
        }

    }
}
