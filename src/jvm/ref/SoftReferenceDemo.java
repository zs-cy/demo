package jvm.ref;

import java.lang.ref.SoftReference;

public class SoftReferenceDemo {
    public static void main(String[] args) {
        Object obj1=new Object();
        SoftReference<Object> softReference=new SoftReference<>(obj1);
        System.out.println(obj1);
        System.out.println(softReference.get());
        obj1=null;
        System.gc();
        System.out.println(obj1);
        System.out.println(softReference.get());
    }
}
