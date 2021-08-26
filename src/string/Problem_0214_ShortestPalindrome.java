package string;

/**
 * Given a string s, you are allowed to convert it to a palindrome by adding
 * characters in front of it. Find and return the shortest palindrome you can
 * find by performing this transformation.
 * 
 * Example 1:
 * 
 * Input: "aacecaaa" Output: "aaacecaaa" Example 2:
 * 
 * Input: "abcd" Output: "dcbabcd"
 * 
 */
public class Problem_0214_ShortestPalindrome {

	public static String shortestPalindrome(String s) {
		if (s == null || s.length() == 0) {
			return s;
		}
		char[] str = s.toCharArray();
		int N = str.length;
		int addLen = N - leftPalindromeMax(str);
		char[] ans = new char[N + addLen];
		for (int i = 0, j = N - 1; i < addLen; i++, j--) {
			ans[i] = str[j];
		}
		for (int i = addLen, j = 0; i < ans.length; i++, j++) {
			ans[i] = str[j];
		}
		return String.valueOf(ans);
	}

	public static int leftPalindromeMax(char[] str) {
		str = manachers(str);
		int[] pArr = new int[str.length];
		int C = -1;
		int R = -1;
		int ans = 0;
		for (int i = 0; i < str.length; i++) {
			pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;
			while (i + pArr[i] < str.length && i - pArr[i] > -1) {
				if (str[i + pArr[i]] == str[i - pArr[i]])
					pArr[i]++;
				else {
					break;
				}
			}
			if (i + pArr[i] > R) {
				R = i + pArr[i];
				C = i;
			}
			// 求必须包含第一个字符的最长回文长度
			if (i - pArr[i] == -1) {
				ans = Math.max(ans, pArr[i] - 1);
			}
		}
		return ans;
	}

	public static char[] manachers(char[] str) {
		int N = str.length;
		char[] res = new char[(N << 1) + 1];
		int index = 0;
		for (int i = 0; i < res.length; i++) {
			res[i] = (i & 1) == 0 ? '#' : str[index++];
		}
		return res;
	}

}
