package data;

/**
 * 基于数组实现的循环队列
 */
public class ArrayQueue {
    // 数组
    private String[] items;
    // 数组的长度
    private int n=0;
    // 队列的head
    private int head=0;
    // 队列的tail
    private int tail=0;

    public ArrayQueue(int n){
        this.items=new String[n];
        this.n=n;
    }

    /**
     * 入队
     * @param item
     * @return
     */
    public boolean enqueue(String item){
        // 判断当前队列是否满了
        if((tail+1)%n==head){
            return false;
        }
        items[tail]=item;
        tail=(tail+1)%n;
        return true;
    }

    /**
     * 出队
     * @return
     */
    public String dequeue(){
        // 判断当前队列是否为空
        if(head==tail){
            return null;
        }
        String temp=items[head];
        head=(head+1)%n;
        return temp;
    }

    public static void main(String[] args) {
        ArrayQueue arrayQueue=new ArrayQueue(3);
        System.out.println(arrayQueue.enqueue("1"));
        System.out.println(arrayQueue.enqueue("2"));
        System.out.println(arrayQueue.enqueue("3"));
        System.out.println(arrayQueue.enqueue("4"));

        System.out.println(arrayQueue.dequeue());
        System.out.println(arrayQueue.dequeue());
        System.out.println(arrayQueue.dequeue());
        System.out.println(arrayQueue.dequeue());

    }
}
