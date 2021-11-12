package leetcode_top_interview_and_top100liked_questions;

/**
 * NC1 大数加法
 * 以字符串的形式读入两个数字，编写一个函数计算它们的和，以字符串形式返回。
 * <p>
 * 数据范围：len(s),len(t) < 100000; len(s),len(t)≤100000，字符串仅由'0'~‘9’构成
 * 要求： 空间复杂度 O(1)O(1)（仅在传入字符串上操作）,时间复杂度 O(n)O(n)
 * 示例1
 * 输入：
 * "1","99"
 * 返回值：
 * "100"
 * 说明：
 * 1+99=100
 * <p>
 * 示例2
 * 输入：
 * "114514",""
 * 返回值：
 * "114514"
 */
//https://www.nowcoder.com/practice/11ae12e8c6fe48f883cad618c2e81475?tpId=117&&tqId=37842&rp=1&ru=/activity/oj&qru=/ta/job-code-high/question-ranking
public class BigNumberAdd {

    public static String solve(String s, String t) {
        // write code here
        if (s.length() == 0 && t.length() == 0) return "";
        if (s.length() == 0 ^ t.length() == 0) {
            return s.length() == 0 ? t : s;
        }
        //都不为空
        char[] arr1 = s.toCharArray();
        char[] arr2 = t.toCharArray();
        reverse(arr1);
        reverse(arr2);

        int carry = 0;
        int p1 = 0;
        int p2 = 0;

        StringBuilder sb = new StringBuilder();

        while (p1 < arr1.length && p2 < arr2.length) {
            int sum = (arr1[p1++] - '0') + (arr2[p2++] - '0') + carry;
            sb.append(sum % 10);
            carry = sum / 10;
        }

        while (p1 < arr1.length) {
            int sum = (arr1[p1++] - '0') + carry;
            sb.append(sum % 10);
            carry = sum / 10;
        }
        while (p2 < arr2.length) {
            int sum = (arr2[p2++] - '0') + carry;
            sb.append(sum % 10);
            carry = sum / 10;
        }
        if (carry == 1) {
            sb.append(1);
        }
        return sb.reverse().toString();

    }

    public static void reverse(char[] str) {
        int l = 0;
        int r = str.length - 1;
        while (l < r) {
            char temp = str[l];
            str[l] = str[r];
            str[r] = temp;
            l++;
            r--;
        }
    }

    public static void main(String[] args) {
        System.out.println(solve("99", "9999"));
    }

}
