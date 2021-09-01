package math;

/**
 * 168. Excel表列名称
 * 给你一个整数 columnNumber ，返回它在 Excel 表中相对应的列名称。
 * <p>
 * 例如：
 * <p>
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28
 * ...
 * <p>
 * 示例 1：
 * 输入：columnNumber = 1
 * 输出："A"
 * <p>
 * 示例 2：
 * 输入：columnNumber = 28
 * 输出："AB"
 * <p>
 * 示例 3：
 * 输入：columnNumber = 701
 * 输出："ZY"
 * <p>
 * 示例 4：
 * 输入：columnNumber = 2147483647
 * 输出："FXSHRXW"
 * <p>
 * 提示：
 * <p>
 * 1 <= columnNumber <= 2^31 - 1
 */
public class Problem_0168_ExcelSheetColumnTitle {


    public String convertToTitle(int num) {
        StringBuilder result = new StringBuilder();
        while (num != 0) {
            num = num - 1;
            char ch = (char) ('A' + num % 26);
            result.append(ch);
            num = num / 26;
        }
        return result.reverse().toString();
    }

}
