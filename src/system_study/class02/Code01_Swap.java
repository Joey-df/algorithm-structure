package system_study.class02;

//异或运算的应用
//如何不用额外变量交换两个数
public class Code01_Swap {
    // 能正确应用该方法的前提：
    // 交换的两个数是独立的内存空间
    // 也就是说，i和j是一个位置的话，会出错
    public static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2};
        swap(arr, 0, 1);
        System.out.println(arr[0] + " " + arr[1]);
    }
}
