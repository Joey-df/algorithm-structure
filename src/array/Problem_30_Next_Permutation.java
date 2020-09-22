package array;

public class Problem_30_Next_Permutation {

    //从右往左，找第一个升序的元素下标i
    //从右往左，找以第一个比nums[i]大的元素下标j
    //交换i,j对应元素
    //将nums[i+1...]整体反转
    public static void nextPermutation(int[] nums) {
        if (nums==null || nums.length<2) {
            return;
        }
        int i=nums.length-2;
        for (; i>=0; i--) {
            if (nums[i] < nums[i+1]) break;
        }
        if (i > -1) { //防止nums是完全降序的情况[5,4,3,2,1]
            int j=nums.length-1;
            for (; j>i ; j--) {
                if (nums[j] > nums[i]) break;
            }
            swap(nums, i, j);
        }
        reverse(nums, i+1, nums.length-1);
    }

    private static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] + arr[j];
        arr[j] = arr[i] - arr[j];
        arr[i] = arr[i] - arr[j];
    }

    private static void reverse(int[] arr, int L, int R) {
        while(L < R) {
            swap(arr, L++, R--);
        }
    }


    public static void main(String[] args) {
        int[] arr = {6,8,7,5,4,3,2,1};
        nextPermutation(arr);
        for (int n:arr) {
            System.out.print(n + " ");
        }
    }
}
