package thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

// 资源类
class Mycahe{
    private volatile Map<String,Object> map=new HashMap<>();
    public ReentrantReadWriteLock rwLock=new ReentrantReadWriteLock();

    public void put(String key,Object value){
        rwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t 正在写入："+key);
            // 暂停一会儿线程
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"\t 写入完成");
        }finally {
            rwLock.writeLock().unlock();
        }
    }

     public void get(String key){
        rwLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t 正在读取");
            // 暂停一会儿线程
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t 读取完成："+result);
        }finally {
            rwLock.readLock().unlock();
        }

     }
}

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        Mycahe mycahe=new Mycahe();
        for (int i = 1; i <=5 ; i++) {
            final int tempInt=i;
            new Thread(()->{
                mycahe.put(tempInt+"",tempInt+"");
            },String.valueOf(i)).start();
        }

        for (int i = 1; i <=5 ; i++) {
            final int tempInt=i;
            new Thread(()->{
                mycahe.get(tempInt+"");
            },String.valueOf(i)).start();
        }
    }
}
