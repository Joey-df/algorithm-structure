package system_study.class05;

//快速排序
/**
 * Partition过程
 * 给定一个数组arr，和一个整数num。请把小于等于num的数放在数组的左边，大于num的数放在数组的右边。
 * 要求额外空间复杂度O(1)，时间复杂度O(N)
 *
 *
 * 荷兰国旗问题
 * 给定一个数组arr，和一个整数num。请把小于num的数放在数组的左边，等于num的数放在中间，大于num的数放在数组的右边。
 * 要求额外空间复杂度O(1)，时间复杂度O(N)
 *
 * 快速排序3.0(随机快排+荷兰国旗技巧优化)
 *
 * 在arr[L..R]范围上，进行快速排序的过程：
 * 1）在这个范围上，随机选一个数记为num，
 * 1）用num对该范围做partition，< num的数在左部分，== num的数中间，>num的数在右部分。假设== num的数所在范围是[a,b]
 * 2）对arr[L..a-1]进行快速排序(递归)
 * 3）对arr[b+1..R]进行快速排序(递归)
 * 因为每一次partition都会搞定一批数的位置且不会再变动，所以排序能完成
 */
public class Code02_PartitionAndQuickSort {

}
