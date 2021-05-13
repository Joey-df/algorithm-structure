package coding_for_great_offer.class02;

import java.util.*;

/**
 * 给定数组hard和money，长度都为N
 * hard[i]表示i号的难度， money[i]表示i号工作的收入
 * 给定数组ability，长度都为M，ability[j]表示j号人的能力
 * 每一号工作，都可以提供无数的岗位，难度和收入都一样
 * 但是人的能力必须>=这份工作的难度，才能上班
 * 返回一个长度为M的数组ans，ans[j]表示j号人能获得的最好收入
 */
public class Code01_ChooseWork {
    //思路：
    //1、将hard和money两个数组对应元素封装成对象，组成一个新的对象数组arr，表示每一份工作的 难度&收入
    //2、对上一步得到的arr排序：hard升序、money降序(排序主要用于添加有序表)
    //3、排序后的arr加到有序表中，规则：hard相同，只保留money最大的；hard上升只保留money也上升的
    //4、构造ans数组，遍历ability，在有序表中找<=ability离ability最近的obj.money
    //前提：hard、money、ability都非空，并且长度相等
    public static int[] chooseWork(int[] hard, int[] money, int[] ability) {
        Job[] jobs = genJobsArr(hard, money);
        Arrays.sort(jobs, new Comparator<Job>() {
            @Override
            public int compare(Job o1, Job o2) {
                return o1.hard != o2.hard ? o1.hard - o2.hard : o2.money - o1.money;
            }
        });
        TreeMap<Integer, Job> table = genTable(jobs);
        int N = ability.length;
        int[] ans = new int[N];
        for (int i = 0; i < N; i++) {
            if (table.floorKey(ability[i]) != null) {//没找到就是空
                Integer floorKey = table.floorKey(ability[i]);
                ans[i] = table.get(floorKey).money;
            }
        }
        return ans;
    }

    private static class Job {
        int hard;
        int money;

        public Job(int hard, int money) {
            this.hard = hard;
            this.money = money;
        }
    }

    private static Job[] genJobsArr(int[] hard, int[] money) {
        int N = hard.length;
        Job[] jobs = new Job[N];
        for (int i = 0; i < N; i++) {
            jobs[i] = new Job(hard[i], money[i]);
        }
        return jobs;
    }

    public static TreeMap<Integer, Job> genTable(Job[] jobs) {
        TreeMap<Integer, Job> table = new TreeMap<>();
        table.put(jobs[0].hard, jobs[0]);
        Job pre = jobs[0]; //上一份进入的工作
        for (int i = 1; i < jobs.length; i++) {
            //难度相同的只进入第一个
            //难度不同的，只要money上升的
            if (jobs[i].hard != pre.hard && jobs[i].money > pre.money) {
                table.put(jobs[i].hard, jobs[i]);
                pre = jobs[i];
            }
        }
        return table;
    }
}
