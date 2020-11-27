package train_camp_03.class01;

/**
 * 附加题：给定有效括号串，求最大嵌套层数。
 * 如 (()(())) 返回3
 * (()()) 返回2
 * ()(()) 返回2
 */
public class MaxDeepOfValidParentheses {

    public static int process(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int count = 0;
        int ans = 0;
        char[] str = s.toCharArray();
        for (int i = 0; i < str.length; i++) {
            count += str[i] == '(' ? 1 : -1;
            ans = Math.max(ans, count);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(process("(()(()))"));
        System.out.println(process("(()())"));
        System.out.println(process("()(())"));
        System.out.println(process("((()))"));
    }
}
