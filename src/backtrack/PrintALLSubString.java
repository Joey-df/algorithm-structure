package backtrack;

/**
 * 打印一个字符串的全部子串
 */
public class PrintALLSubString {

    public static void printAllSubstr(String s) {
        if (s == null || s.length() == 0) {
            return;
        }

        int N = s.length();
        for (int i = 0; i < N; i++) { //开始的位置
            for (int j = i; j < N; j++) { //结尾的位置
                System.out.println(s.substring(i, j + 1));
            }
        }
    }

    public static void main(String[] args) {
        printAllSubstr("abc");
    }
}
