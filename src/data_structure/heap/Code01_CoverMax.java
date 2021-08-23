package data_structure.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 最大线段重合问题（用堆的实现）
 * <p>
 * 给定很多线段，每个线段都有两个数组[start, end]，
 * 表示线段开始位置和结束位置，左右都是闭区间
 * 规定：
 * 1）线段的开始和结束位置一定都是整数值
 * 2）线段重合区域的长度必须>=1
 * 返回线段最多重合区域中，包含了几条线段
 * <p>
 * https://github.com/algorithmzuo/algorithmbasic2020/blob/master/src/class04_07/Code01_CoverMax.java
 */
public class Code01_CoverMax {

    //分析：
    //1、先对所有线段进行排序(按起始位置从小到大)
    //2、准备一个小根堆，存在线段的结束位置
    //3、遍历每一个线段，判断堆顶是否<=当前线段的start，一直poll到堆顶>当前start位置，当前线段end入堆，抓答案
    //4、更新全部max
    public static class Line {
        int start;
        int end;

        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static int process(int[][] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Line[] lines = new Line[nums.length];
        for (int i = 0; i < nums.length; i++) {
            lines[i] = new Line(nums[i][0], nums[i][1]);
        }
        Arrays.sort(lines, new Comparator<Line>() {
            @Override
            public int compare(Line o1, Line o2) {
                return o1.start - o2.start;
            }
        });
        int ans = 0;
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int i = 0; i < lines.length; i++) {
            while (!heap.isEmpty() && heap.peek() <= lines[i].start) {
                heap.poll();
            }
            heap.add(lines[i].end);
            ans = Math.max(ans, heap.size());
        }
        return ans;
    }

    //on class
    public static int maxCover1(int[][] lines) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < lines.length; i++) {
            min = Math.min(min, lines[i][0]);
            max = Math.max(max, lines[i][1]);
        }
        int cover = 0;
        for (double p = min + 0.5; p < max; p += 1) {
            int cur = 0;
            for (int i = 0; i < lines.length; i++) {
                if (lines[i][0] < p && lines[i][1] > p) {
                    cur++;
                }
            }
            cover = Math.max(cover, cur);
        }
        return cover;
    }

    //生成长度最大为maLen，start-end在[l,r]范围上的线段们
    public static int[][] generateLines(int l, int r, int maxLen) {
        int len = (int) (Math.random() * (maxLen + 1));
        len = Math.max(len, 3);
        int[][] lines = new int[len][2];
        for (int i = 0; i < len; i++) {
            int s = l + (int) (Math.random() * (r - l + 1));
            int e = l + (int) (Math.random() * (r - l + 1));
            if (s == e) {
                e = s + 2;
            }
            lines[i][0] = Math.min(s,e);
            lines[i][1] = Math.max(s,e);
        }
        return lines;
    }

    public static void main(String[] args) {
        int testTimes = 100000;
        int maxLen = 1000;
        int l = 10;
        int r = 100;
        System.out.println("test start");
        for (int i = 0; i < testTimes; i++) {
            int[][] lines = generateLines(l, r, maxLen);
            int ans1 = process(lines);
            int ans2 = maxCover1(lines);
            if (ans1 != ans2) {
                System.out.println("fuck");
                break;
            }
        }
        System.out.println("test end");
    }
}


