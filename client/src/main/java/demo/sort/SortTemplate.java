package demo.sort;

public class SortTemplate {
    public static void sort(Comparable[] a,SortType type){
        // 各类排序算法
        int len = a.length;
        switch (type){
            case SELECT:
                //select sort:选择最小（最大）的元素，放在起始位置，时间复杂度O(n^2),进行n*(n-1)/2次
             
                for (int i = 0; i < len-1; i++) {
                    int min=i;
                    for (int j = i+1; j < len; j++) {
                        if(less(a[j],a[min]))
                            min=j;
                    }
                    exch(a,i,min);
                }
                break;
            case INSERT:
                //插入排序，将一个元素放入向前遍历，插入到合适的位置
                for (int i = 0; i < len; i++) {
                    for (int i1 = i; i1 > 0&&less(a[i1],a[i1-1]); i1--) {
                        exch(a,i1,i1-1);
                    }
                }
                
                break;
            case SHELL:
                //希尔排序，
                int gap=1;
                while (gap<len/3){
                    gap=3*gap+1;
                }
                while (gap>=1){
                    for (int i = gap; i < len; i++) {
                        for (int j = i; j >=gap&&less(a[j],a[j-gap]); j-=gap) {
                            exch(a,j,j-gap);
                        }
                    }
                    gap/=3;
                }

                break;
            case QUICK:
                quickSort(a,0,len-1);
                break;

        }


    }

    private static boolean less(Comparable v, Comparable w) {
        // 对元素进行比较
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        // 将元素交换位置
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void show(Comparable[] a) {
        // 在单行中打印数组
        for(int i = 0; i < a.length; i++)
            System.out.println(a[i] + " ");

    }

    public static boolean isSorted(Comparable[] a) {
        // 测试数组元素是否有序
        for(int i = 1; i < a.length; i++)
            if(less(a[i], a[i - 1]))
                return false;
        return true;
    }

    public static int partition(Comparable[] a,int lo,int hi){
        int i = lo;
        int j = hi + 1; // 左右扫描指针
        // 切分元素
        Comparable v = a[lo];
        while (true) {

            // find item on lo to swap
            while (less(a[++i], v)) {
                if (i == hi) break;
            }

            // find item on hi to swap
            while (less(v, a[--j])) {
                if (j == lo) break;      // redundant since a[lo] acts as sentinel
            }

            // check if pointers cross
            if (i >= j) break;

            exch(a, i, j);
        }

        // put partitioning item v at a[j]
        exch(a, lo, j);

        // now, a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
        return j;
    }

    public static void quickSort(Comparable[] a,int lo,int hi){
        if(hi<=lo) return;
        int j=partition(a,lo,hi);
        quickSort(a,lo,j-1);
        quickSort(a,j+1,hi);
    }

    public static void main(String[] args) {
        // 从标准输入读取字符串，将它们排序并输出

        Integer[] a2=new Integer[]{12,43,22,32,3};
        sort(a2,SortType.QUICK);
        show(a2);
    }


}
