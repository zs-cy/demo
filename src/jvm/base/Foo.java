package jvm.base;

public class Foo {
    static boolean boolValue;
    public static void main(String[] args) {
        boolValue = true;
        if (boolValue) System.out.println("Hello,java!");
        if(boolValue == true) System.out.println("hello,jvm!");
    }
}
