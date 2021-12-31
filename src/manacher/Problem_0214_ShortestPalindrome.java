package manacher;

/**
 * 214. 字符串前面至少添加几个字符使原串变成回文串
 * 给定一个字符串 s，你可以通过在字符串前面添加字符将其转换为回文串。找到并返回可以用这种方式转换的最短回文串。
 * 示例 1：
 * 输入：s = "aacecaaa"
 * 输出："aaacecaaa"
 * 示例 2：
 * 输入：s = "abcd"
 * 输出："dcbabcd"
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
