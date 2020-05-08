package thread;

import java.util.concurrent.TimeUnit;

class HoldLockThread implements Runnable{
    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA){
            try {
                System.out.println(Thread.currentThread().getName()+"\t 持有lockA:"+lockA+"\t 尝试获得lockB:"+lockB);
                TimeUnit.SECONDS.sleep(1);
                synchronized (lockB){
                    System.out.println(Thread.currentThread().getName()+"\t 持有lockA:"+lockA+"" +"\t 持有lockB:"+lockB);
                };
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class DealLockDemo {
    public static void main(String[] args) {
        Thread thread1=new Thread(new HoldLockThread("A","B"));
        Thread thread2=new Thread(new HoldLockThread("B","A"));
        thread1.start();
        thread2.start();
    }
}
