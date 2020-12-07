package train_camp_03.class06;

/**
 * 子数组的最大异或和
 * 题目描述:
 * 数组异或和的定义：把数组中所有的数异或起来得到的值。
 * 给定一个整型数组arr，其中可能有正、负、零，求其中子数组的最大异或和。
 * 例如：
 * arr={3}，数组只有一个数，所以只有一个子数组，就是这个数组本身，最大异或和为3。
 * arr={3, -28, -29, 2}，子数组有很多，但{-28, -29}这个子数组的异或和为7，是所有子数组中最大的。
 * <p>
 * http://chenxii.cn/2020/02/23/D-DataStructureAndAlgorithm/E-ZuoInterview/84-maxXorSubarray/
 */
public class Code01_MaxEOR {
    // O(N^2)的解法
    public static int maxEOR(int[] nums) {
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {//枚举每一个子数组的开头
            int eor = 0;
            for (int j = i; j < nums.length; j++) { //枚举每一个以i位置开始的结尾j
                eor ^= nums[j];
                ans = Math.max(ans, eor);
            }
        }
        return ans;
    }

    // O(N^2)
    public static int maxXorSubarray1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // 准备一个前缀异或和数组eor
        // eor[i] = arr[0...i]的异或结果
        int[] eor = new int[arr.length];
        eor[0] = arr[0];
        // 生成eor数组，eor[i]代表arr[0..i]的异或和
        for (int i = 1; i < arr.length; i++) {
            eor[i] = eor[i - 1] ^ arr[i];
        }
        int max = Integer.MIN_VALUE;
        //枚举每一个以j位置结尾的子数组
        for (int j = 0; j < arr.length; j++) {
            for (int i = 0; i <= j; i++) { // 依次尝试arr[0..j]、arr[1..j]..arr[i..j]..arr[j..j]
                max = Math.max(max, i == 0 ? eor[j] : eor[j] ^ eor[i - 1]);
            }
        }
        return max;
    }

    // O(N)的解法 使用前缀树左优化
    private static class Node {
        Node[] next;

        public Node() {
            this.next = new Node[2];
        }
    }

    private static class NumTrie {
        Node root = new Node();

        // num加入前缀树
        public void add(int num) {
            Node cur = root;
            for (int move = 31; move >= 0; move--) {
                int path = (num >> move) & 1; //判断move位是1还是0
                if (cur.next[path] == null) {
                    cur.next[path] = new Node();
                }
                cur = cur.next[path];
            }
        }

        //求以num结尾的所有子数组的最大异或和
        //num之前的数据(以每个数结尾的异或和)已经加入前缀树了
        public int maxXor(int num) {
            int res = 0;
            Node cur = root;
            for (int i = 31; i >= 0; i--) {
                int path = (num >> i) & 1;
                //期待走的路
                //i==31代表符号位，符号位期待与path一致，即0期望0，1期望1，得到的结果都是正数
                //非符号位的话，期望和path相反，异或后的结果最大化
                int best = (i == 31) ? path : path ^ 1;
                //实际走的路
                best = cur.next[best] != null ? best : best ^ 1;
                res |= (best ^ path) << i;
                cur = cur.next[best];
            }
            return res;
        }
    }

    //O(N)最优解
    public static int maxEORUseTrie(int[] nums) {
        NumTrie tree = new NumTrie();
        int ans = Integer.MIN_VALUE;
        int eor = 0;
        tree.add(0); //计算以nums[0]结尾的子数组时需要
        for (int i = 0; i < nums.length; i++) {
            eor ^= nums[i]; //0...i的异或和
            int curMax = tree.maxXor(eor);
            ans = Math.max(ans, curMax);
            tree.add(eor);
        }
        return ans;
    }

    public static int[] generateArr(int len, int maxVal) {
        int l = (int) (Math.random() * (len + 1));
        l = Math.max(l, 3);
        int[] ans = new int[l];
        for (int i = 0; i < l; i++) {
            ans[i] = (int) (Math.random() * (maxVal + 1)) - (int) (Math.random() * (maxVal + 1));
        }
        return ans;
    }

    public static void printArr(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int testTimes = 100000;
        int len = 100;
        int maxVal = 100;
        System.out.println("test begin..");
        for (int i = 0; i < testTimes; i++) {
            int[] nums = generateArr(len, maxVal);
            if (i < 10) {
                printArr(nums);
            }
            int ans1 = maxEOR(nums);
            int ans2 = maxXorSubarray1(nums);
            int ans3 = maxEORUseTrie(nums);
            if (ans1 != ans2 || ans2 != ans3) {
                System.out.println("fuck..");
                break;
            }
        }
        System.out.println("test end..");
    }
}
