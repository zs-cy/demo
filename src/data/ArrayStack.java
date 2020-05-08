package data;

/**
 * 基于数组实现的顺序栈
 */
public class ArrayStack {
    // 数组
    private String[] items;
    // 栈中元素的个数
    private int count;
    // 栈的大小
    private int n;

    /**
     * 初始化数组，申请一个大小为n的数组空间
     * @param n
     */
    public ArrayStack(int n){
        this.items=new String[n];
        this.n=n;
        this.count=0;
    }

    /**
     * 入栈操作
     * @param item
     * @return
     */
    public boolean push(String item){
        // 判断栈是否满了，满了直接返回fasle
        if(n==count) return false;
        items[count]=item;
        count++;
        return true;
    }

    public String pop(){
        // 判断栈是否为空，为空直接返回null
        if(count==0)return null;
        String temp=items[--count];
        return temp;
    }

    public static void main(String[] args) {
        ArrayStack arrayStack=new ArrayStack(3);
        System.out.println(arrayStack.push("1"));
        System.out.println(arrayStack.push("2"));
        System.out.println(arrayStack.push("3"));
        System.out.println(arrayStack.push("4"));

        System.out.println(arrayStack.pop());
        System.out.println(arrayStack.pop());
        System.out.println(arrayStack.pop());
    }
}
