package leetcode_top_interview_and_top100liked_questions;


/**
 * Given two binary strings a and b, return their sum as a binary string.
 * <p>
 * Example 1:
 * <p>
 * Input: a = "11", b = "1"
 * Output: "100"
 * Example 2:
 * <p>
 * Input: a = "1010", b = "1011"
 * Output: "10101"
 */
//和leetcode2同类型的题
public class Problem_0067_AddBinary {

    public static String addBinary(String a, String b) {
        assert (a != null && a.length() > 0 && b != null && b.length() > 0);
        char[] str1 = a.toCharArray();
        char[] str2 = b.toCharArray();
        int N = str1.length;
        int M = str2.length;
        int i = N - 1;
        int j = M - 1;
        int carry = 0;
        int sum = 0; //每一步的sum
        StringBuilder sb = new StringBuilder();
        while (i >= 0 || j >= 0) {
            sum = carry;
            sum += (i >= 0) ? (str1[i--] - '0') : 0;
            sum += (j >= 0) ? (str2[j--] - '0') : 0;
            sb.append(sum % 2);
            carry = sum / 2;
        }
        if (carry == 1) {
            sb.append(1);
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(addBinary("11","1"));
    }
}
