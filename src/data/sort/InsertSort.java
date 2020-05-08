package data.sort;

import java.util.Arrays;

public class InsertSort {

    // 插入排序，a表示待排序的数组，n表示数组大小
    public static void inserSort(int[] a,int n){
        if(n <= 1) return ;
        for (int i = 1; i < n; i++) {
            int value = a[i];
            for(int j=i; j >= 0; j--){
                if( j > 0 && a[j-1] > value){
                    a[j] = a[j-1];
                }else{
                    a[j]=value;
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] a={5,8,7,3,3,9,1,4};
        inserSort(a,8);
        System.out.println(Arrays.toString(a));
    }
}
