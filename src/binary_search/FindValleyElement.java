package binary_search;

//在无序数组中找局部最小值
//限制：相邻的数均不相等
public class FindValleyElement {
    private static int findValleyElement(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        if (arr.length == 1) {
            return 0;
        }
        if (arr.length == 2) {
            return (arr[0] > arr[1]) ? 1 : 0;
        }
        //arr.length>=3
        int N = arr.length;
        if (arr[0] < arr[1]) {
            return 0;
        }
        if (arr[N - 1] < arr[N - 2]) {
            return N - 1;
        }
        int L = 1, R = N - 2;
        while (L < R) { //L==R退出
            int mid = L + ((R - L) >> 2);
            if (arr[mid] < arr[mid + 1] && arr[mid] < arr[mid - 1]) {
                return mid;
            } else if (arr[mid] > arr[mid - 1]) {
                R = mid - 1; //左边找
            } else if (arr[mid] > arr[mid + 1]) {
                L = mid + 1;//右边找
            }
        }
        return L;
    }

    public static void main(String[] args) {
        int[] nums = {10, 9, 8, 5, 0, 12, 4, 21};
        int index = findValleyElement(nums);
        System.out.println(nums[index]);
    }
}
