package thread;


import java.util.TimerTask;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyResource{
    private volatile boolean FLAG=true;
    private AtomicInteger atomicInteger=new AtomicInteger();

    BlockingQueue<String> blockingQueue=null;
    public MyResource(BlockingQueue<String> blockingQueue){
        this.blockingQueue=blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void myProd() throws Exception {
        String data=null;
        boolean retValue=false;
        while (FLAG){
            data=String.valueOf(atomicInteger.incrementAndGet());
            retValue=blockingQueue.offer(data,2, TimeUnit.SECONDS);
            if(retValue){
                System.out.println(Thread.currentThread().getName()+"\t 插入队列成功:"+data);
            }else{
                System.out.println(Thread.currentThread().getName()+"\t 插入队列失败:"+data);
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName()+"\t 停止");
    }

    public void myConsumer() throws Exception {
        String result=null;
        while (FLAG){
            result = blockingQueue.poll(2, TimeUnit.SECONDS);
            if(result==null||result.equalsIgnoreCase("")){
                FLAG=false;
                System.out.println(Thread.currentThread().getName()+"\t 超过两秒钟未生产，消费退出");
                return ;
            }
            System.out.println(Thread.currentThread().getName()+"\t 消费队列消费成功："+result);
        }
    }

    public void stop() {
        this.FLAG=false;
    }
}

public class ProdConsumer_BlockQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        MyResource myResource=new MyResource(new ArrayBlockingQueue<>(1));
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t 生产线程启动");
            try {
                myResource.myProd();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"Prod").start();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t 消费线程启动");
            try {
                myResource.myConsumer();
                System.out.println();
                System.out.println();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"Consumer").start();

        System.out.println();
        System.out.println();
        TimeUnit.SECONDS.sleep(5);
        myResource.stop();
    }
}
