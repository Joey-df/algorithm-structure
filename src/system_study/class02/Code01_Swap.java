package system_study.class02;

//如何不用额外变量交换两个数
public class Code01_Swap {

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
