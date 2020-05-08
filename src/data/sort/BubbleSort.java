package data.sort;

/**
 * 冒泡排序
 */
public class BubbleSort {

    // 冒泡排序，a表示待排序的数组，n表示数组大小
    public static void bubbleSort(int[] a,int n){
        if(n<=1)return ;
        for (int i = 0; i < n; i++) {
            // 标志位用来判断内层循环是否发生交换
            boolean flag=false;
            for (int j = 0; j < n-i-1; j++) {
                if(a[j] > a[j+1]){ // 交换
                    int temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                    flag = true; // 表示有数据发生交换
                }
            }
            // 未发生交换跳出循环，排序完成
            if(!flag) break;
        }
    }

    public static void main(String[] args) {
        int[] a={5,8,7,3,3,9,1,4};
        bubbleSort(a,8);
        for (int i : a) {
            System.out.println(i);
        }
    }
}
