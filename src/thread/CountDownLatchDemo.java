package thread;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) {
        CountDownLatch countDownLatch=new CountDownLatch(10);
        for (int i = 1; i <= 10; i++) {
            new Thread(()->{
                System.out.println("Thread:"+Thread.currentThread().getName());
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread:"+Thread.currentThread().getName());
    }
}
