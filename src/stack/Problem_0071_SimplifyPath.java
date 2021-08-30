package stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 71. 简化路径
 * 给你一个字符串 path ，表示指向某一文件或目录的 Unix 风格 绝对路径 （以 '/' 开头），请你将其转化为更加简洁的规范路径。
 * 在 Unix 风格的文件系统中，一个点（.）表示当前目录本身；此外，两个点 （..） 表示将目录切换到上一级（指向父目录）；
 * 两者都可以是复杂相对路径的组成部分。任意多个连续的斜杠（即，'//'）都被视为单个斜杠 '/' 。
 * 对于此问题，任何其他格式的点（例如，'...'）均被视为文件/目录名称。
 * <p>
 * 请注意，返回的 规范路径 必须遵循下述格式：
 * <p>
 * 始终以斜杠 '/' 开头。
 * 两个目录名之间必须只有一个斜杠 '/' 。
 * 最后一个目录名（如果存在）不能 以 '/' 结尾。
 * 此外，路径仅包含从根目录到目标文件或目录的路径上的目录（即，不含 '.' 或 '..'）。
 * 返回简化后得到的 规范路径 。
 * <p>
 * 示例 1：
 * 输入：path = "/home/"
 * 输出："/home"
 * 解释：注意，最后一个目录名后面没有斜杠。
 * <p>
 * 示例 2：
 * 输入：path = "/../"
 * 输出："/"
 * 解释：从根目录向上一级是不可行的，因为根目录是你可以到达的最高级。
 * <p>
 * 示例 3：
 * 输入：path = "/home//foo/"
 * 输出："/home/foo"
 * 解释：在规范路径中，多个连续斜杠需要用一个斜杠替换。
 * <p>
 * 示例 4：
 * 输入：path = "/a/./b/../../c/"
 * 输出："/c"
 * <p>
 * 提示：
 * 1 <= path.length <= 3000
 * path 由英文字母，数字，'.'，'/' 或 '_' 组成。
 * path 是一个有效的 Unix 风格绝对路径。
 */
public class Problem_0071_SimplifyPath {

    //解题思路
    //分割字符串之后根据每种情况进行判定
    //.和""就不用管，直接跳过
    //..就代表着返回上一级，即弹出队尾元素（要注意判空）
    //其他情况直接压入队列就行。
    //
    //如果对linux比较熟悉的话这道题应该是秒杀的，Linux的目录层级就是用栈实现的
    //只不过为了拼接方便这里使用了双端队列
    public static String simplifyPath(String path) {
        if (path == null || path.length() == 0) return path;
        String[] arr = path.split("/");
        //使用双端队列，实现尾进头出
        Deque<String> q = new LinkedList<>();
        for (String s : arr) {
            if ("".equals(s) || ".".equals(s)) continue;
            else if ("..".equals(s)) {
                if (!q.isEmpty()) q.pollLast();
            } else {
                q.offerLast(s);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("/");
        while (!q.isEmpty()) {
            int size = q.size();
            sb.append(q.pollFirst());
            if (size > 1) sb.append("/");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        //String path = "/a/./b/../../c/";
        String path = "/home//foo/";
        System.out.println(simplifyPath(path));
    }
}
