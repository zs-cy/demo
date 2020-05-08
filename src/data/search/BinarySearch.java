package data.search;

/**
 * 二分查找
 */
public class BinarySearch {
    /**
     * 给定数组和要查找的数值，返回数值所在下标，查询不到返回-1
     *
     * @param a     数组
     * @param n     数组长度
     * @param value 查找的值
     * @return
     */
    public static int bsearch(int[] a, int n, int value) {
        if (n < 1) {
            return -1;
        }
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (a[mid] == value) {
                return mid;
            } else if (a[mid] < value) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 给定数组和要查找的数值，返回数值第一次出现时所在下标，查询不到返回-1
     *
     * @param a     数组
     * @param n     数组长度
     * @param value 查找的值
     * @return
     */
    public static int bsearchFrst(int[] a, int n, int value) {
        if (n < 1) {
            return -1;
        }
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (a[mid] == value) {
                if(mid==0||a[mid-1]!=value){
                    return mid;
                }else {
                    high=mid -1;
                }
            } else if (a[mid] < value) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }


    // 二分查找的递归实现
    public static int bseachRecursion(int[] a, int n, int value) {
        return bsearchInternally(a, 0, n - 1, value);
    }

    public static int bsearchInternally(int[] a, int low, int high, int value) {
        if (low > high) return -1;
        int mid = low + ((high - low) >> 1);
        if(a[mid]==value){
            return mid;
        }else if(a[mid]<value){
            return bsearchInternally(a,mid+1,high,value);
        }else{
            return bsearchInternally(a,low,mid-1,value);
        }
    }

    public static void main(String[] args) {
        int[] a = {0, 1, 2, 5, 5, 5, 6, 7, 8, 9};
        int index = bsearchFrst(a, 10, 5);
        System.out.println(index);
    }
}
