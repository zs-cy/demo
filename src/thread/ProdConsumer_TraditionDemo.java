package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData{//资源类
    private int number=0;
    private Lock lock=new ReentrantLock();
    private Condition condition=lock.newCondition();

    public void increment() throws Exception {
        try {
            lock.lock();
            // 判断
            while(number!=0){
                // 等待，不能生产
                condition.await();
            }
            //
            number++;
            System.out.println(Thread.currentThread().getName() + "\t"+number);
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }

    public void decrement() throws Exception{
        try {
            lock.lock();
            // 判断
            while(number==0){
                // 等待，不能消费
                condition.await();
            }
            //
            number--;
            System.out.println(Thread.currentThread().getName() + "\t"+number);
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }

}

public class ProdConsumer_TraditionDemo {
    public static void main(String[] args) throws Exception{
        ShareData shareData=new ShareData();
        for (int i = 1; i <=5 ; i++) {
            new Thread(()->{
                try {
                    shareData.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            },"AAA").start();
        }
        for (int i = 1; i <=5 ; i++) {
            new Thread(()->{
                try {
                    shareData.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            },"BBB").start();
        }

    }
}
