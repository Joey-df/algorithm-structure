package design;

/**
 * Given an integer array nums, design an algorithm to randomly shuffle the array.
 * <p>
 * Implement the Solution class:
 * <p>
 * Solution(int[] nums) Initializes the object with the integer array nums.
 * int[] reset() Resets the array to its original configuration and returns it.
 * int[] shuffle() Returns a random shuffling of the array.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["Solution", "shuffle", "reset", "shuffle"]
 * [[[1, 2, 3]], [], [], []]
 * Output
 * [null, [3, 1, 2], [1, 2, 3], [1, 3, 2]]
 * <p>
 * Explanation
 * Solution solution = new Solution([1, 2, 3]);
 * solution.shuffle();    // Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must be equally likely to be returned. Example: return [3, 1, 2]
 * solution.reset();      // Resets the array back to its original configuration [1,2,3]. Return [1, 2, 3]
 * solution.shuffle();    // Returns the random shuffling of array [1,2,3]. Example: return [1, 3, 2]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 200
 * -106 <= nums[i] <= 106
 * All the elements of nums are unique.
 * At most 5 * 104 calls will be made to reset and shuffle.
 */
public class Problem_384_Shuffle_an_Array {

    public static class Solution {

        private int[] original;
        private int[] shuffle;

        public Solution(int[] nums) {
            this.original = nums;
            this.shuffle = new int[original.length];
            for (int i = 0; i < nums.length; i++) {
                shuffle[i] = nums[i];
            }
        }

        /**
         * Resets the array to its original configuration and return it.
         */
        public int[] reset() {
            return this.original;
        }

        /**
         * Returns a random shuffling of the array.
         */
        public int[] shuffle() {
            int N = shuffle.length;
            for (int i = N - 1; i >= 0; i--) {
                int rdIdx = (int) (Math.random() * (i + 1));//[0,i]
                int t = shuffle[rdIdx];
                shuffle[rdIdx] = shuffle[i];
                shuffle[i] = t;
            }
            return shuffle;
        }
    }

}
