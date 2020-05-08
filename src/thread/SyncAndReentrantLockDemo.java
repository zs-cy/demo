package thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareResource{
    private int number=1;
    private Lock lock=new ReentrantLock();
    private Condition condition1=lock.newCondition();
    private Condition condition2=lock.newCondition();
    private Condition condition3=lock.newCondition();


    public void print5(){
        lock.lock();
        try {
            while (number!=1){
                condition1.await();
            }
            for (int i = 1; i <=5 ; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            number=2;
            condition2.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void print10(){
        lock.lock();
        try {
            while (number!=2){
                condition2.await();
            }
            for (int i = 1; i <=10 ; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            number=3;
            condition3.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void print15(){
        lock.lock();
        try {
            while (number!=3){
                condition3.await();
            }
            for (int i = 1; i <=15 ; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            number=1;
            condition1.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

}

public class SyncAndReentrantLockDemo {
    public static void main(String[] args) throws InterruptedException {
        ShareResource shareResource=new ShareResource();

        new Thread(()->{
            for (int i = 1; i <= 10; i++) {
                shareResource.print5();
            }
        },"AAA").start();
        new Thread(()->{
            for (int i = 1; i <= 10; i++) {
                shareResource.print10();
            }
        },"BBB").start();
        new Thread(()->{
            for (int i = 1; i <= 10; i++) {
                shareResource.print15();
            }
        },"CCC").start();
    }
}
