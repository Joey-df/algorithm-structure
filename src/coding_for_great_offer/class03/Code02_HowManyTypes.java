package coding_for_great_offer.class03;

import java.util.HashSet;
import java.util.Set;

/**
 * 只由小写字母（a~z）组成的一批字符串，都放在字符类型的数组String[] arr中，
 * 如果其中某两个字符串，所含有的字符种类完全一样，就将两个字符串算作一类 比如：baacba和bac就算作一类
 * 虽然长度不一样，但是所含字符的种类完全一样（a、b、c） 返回arr中有多少类？
 */
public class Code02_HowManyTypes {

    //最优解
    //使用int表示一个字符串是否出现过
    //小写字母总共26个，使用int即可表示，int32位
    public static int types(String[] arr) {
        Set<Integer> set = new HashSet<>();
        for (String s : arr) {
            int num = 0;
            char[] str = s.toCharArray();
            for (char c : str) {
                num |= 1 << (c - 'a');
            }
            set.add(num);
        }
        return set.size();
    }

    public static void main(String[] args) {
        String[] arr = new String[]{"", "ababababc", "", "abc", "edc", "edcdc", "ert", "zzz", "xyz"};
        System.out.println(types(arr));
    }
}
