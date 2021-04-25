package questions;

public class Problem_0459_RepeatedSubstringPattern {

	public static boolean repeatedSubstringPattern(String s) {
		if (s == null || s.length() < 2) {
			return false;
		}
		char[] str = s.toCharArray();
		int N = str.length;
		if (str.length == 2) {
			return str[0] == str[1];
		}
		int tmp = N - lastNextValue(str);
		if (tmp == N || N % tmp != 0) {
			return false;
		}
		for (int j = 0, i = tmp--; i < N; i++, j = (j < tmp ? (j + 1) : 0)) {
			if (str[j] != str[i]) {
				return false;
			}
		}
		return true;
	}

	public static int lastNextValue(char[] match) {
		int N = match.length;
		int[] next = new int[N + 1];
		next[0] = -1;
		next[1] = 0;
		int i = 2;
		int cn = 0;
		while (i <= N) {
			if (match[i - 1] == match[cn]) {
				next[i++] = ++cn;
			} else if (cn > 0) {
				cn = next[cn];
			} else {
				next[i++] = 0;
			}
		}
		return next[N];
	}

}
