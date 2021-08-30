package string;

/**
 * 434. 字符串中的单词数
 * 统计字符串中的单词个数，这里的单词指的是连续的不是空格的字符。
 *
 * 请注意，你可以假定字符串里不包括任何不可打印的字符。
 *
 * 示例:
 *
 * 输入: "Hello, my name is John"
 * 输出: 5
 * 解释: 这里的单词是指连续的不是空格的字符，所以 "Hello," 算作 1 个单词。
 */
public class Problem_0434_NumberOfSegmentsInAString {

    public static int countSegments(String s) {
        if (s==null || s.length()==0) return 0;
        //\s:匹配任何空白字符，包括空格、制表符、换页符等等。等价于[ \f\n\r\t\v]。
        String[] arr = s.split("\\s+");
        int count = 0;
        for(String str : arr) {
            count += "".equals(str.trim()) ? 0 : 1;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(countSegments("Hello, my name is John"));
    }
}
