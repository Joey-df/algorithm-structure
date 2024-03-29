package bit_manipulation;

/**
 * 260. Single Number III
 * Given an integer array nums, in which exactly two elements appear only once and all the other elements appear exactly twice.
 * Find the two elements that appear only once. You can return the answer in any order.
 *
 * You must write an algorithm that runs in linear runtime complexity and uses only constant extra space.
 */
//数组中有两种元素出现了奇数次，其他元素都出现了偶数次，请找出出现奇数次的两种元素返回
public class Problem_0260_SingleNumberIII {

    //1，1，2，2，3，4
    //3^4
    //3^4^1^1^3 = 4
    public static int[] singleNumber(int[] arr) {
        int eor = 0; //a^b的结果
        for (int num : arr) eor ^= num;
        int rightOne = eor & -eor;
        int a=0;
        for (int n: arr) {
//            if ((rightOne & n) == 0) {
//                a ^= n;
//            }
            a ^= (rightOne&n)==0 ? n : 0;
        }
        System.out.println(a+"  "+ (eor^a));
        return new int[]{a, eor^a};
    }


    public static void main(String[] args) {
        singleNumber(new int[]{1,1,2,2,5,3,3,4});
    }
}
