package system_study.class02;

//(一)、一个数组中有一种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这种数
//(二)、一个数组中有两种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这两种数
//leetcode 136. Single Number
//leetcode 260. Single Number III

public class Code02_EvenTimesOddTimes {

    public static int question1(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            ans ^= arr[i];
        }
        return ans;
    }

    //怎么把一个int类型的数，提取出最右侧的1来
    //n & (-n)
    //n & (~n+1)
    public static int[] questions2(int[] arr) {
        int eor = 0;
        for (int n : arr) {
            eor ^= n;
        }
        // a 和 b是两种数
        // eor != 0
        // eor最右侧的1，提取出来
        // eor :     00110010110111000
        // rightOne :00000000000001000

        //假设这两个数是a、b，那么eor=a^b
        int a = 0;
        int rightOne = eor & (~eor + 1);
        for (int n : arr) {
            if ((rightOne ^ n) == 0) {
                a ^= n;
            }
        }
        return new int[]{a, a ^ eor};
    }
}
