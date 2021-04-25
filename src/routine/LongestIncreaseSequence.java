package routine;

//每天一遍 最长递增子序列 O(N*logN)
public class LongestIncreaseSequence {

    public static int getLis(int[] arr) {
        if (arr==null||arr.length==0) {
            return 0;
        }
        int N = arr.length;
        int[] dp = new int[N];//dp[i]的含义：以i位置结尾的递增子序列长度
        int[] ends = new int[N];//ends[i]的含义：长度为i+1的递增子序列的最小结尾是啥？
        ends[0] = arr[0];
        dp[0]=1;
        int ans=dp[0];
        int right=0;//ends的有效区为[0,right] 有效区必有序
        for (int i = 1; i < arr.length; i++) {
            int l=0;
            int r= right;
            while (l<=r) {
                int mid=(l+r)/2;
                if (ends[mid]>=arr[i]) {
                    r=mid-1;
                } else {
                    l=mid+1;
                }
            }
            right=Math.max(right,l);
            ends[l]=arr[i];
            dp[i] = l+1;
            ans=Math.max(ans,dp[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(getLis(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
    }
}
