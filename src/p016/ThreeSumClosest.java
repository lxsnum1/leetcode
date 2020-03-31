package p016;

import java.util.Arrays;

// 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，
// 使得它们的和与 target 最接近。返回这三个数的和

// 假定每组输入只存在唯一答案。 
//
// 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
//
// 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
// 
// Related Topics 数组 双指针

public class ThreeSumClosest {

    public static void main(String[] args) {
        Solution solution = new ThreeSumClosest().new Solution();
        int[] nums = { -1, 2, 1, -4 };
        int target = 1;
        int ans = solution.threeSumClosest(nums, target);
    }

    class Solution {
        public int threeSumClosest(int[] nums, int target) {
            if (nums == null || nums.length < 3) {
                throw new IllegalArgumentException("no solution");
            }
            Arrays.sort(nums);
            int ans = nums[0] + nums[1] + nums[2];
            for (int i = 0; i < nums.length; i++) {
                int left = i + 1;
                int right = nums.length - 1;

                if (i > 0 && nums[i] == nums[i - 1]) { // 去重
                    continue;
                }
                while (left < right) {
                    int sum = nums[i] + nums[left] + nums[right];
                    if (Math.abs(sum - target) < Math.abs(ans - target)) {
                        ans = sum;
                    }

                    if (sum < target) {
                        left++;
                    } else if (sum > target) {
                        right--;
                    } else {
                        return sum;
                    }
                }
            }
            return ans;
        }
    }
}
