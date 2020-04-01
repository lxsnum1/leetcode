package p018;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d，
// 使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。 
//
// 注意： 
//
// 答案中不可以包含重复的四元组。 
//
// 示例： 
//
// 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
//
// 满足要求的四元组集合为：
//[
//  [-1,  0, 0, 1],
//  [-2, -1, 1, 2],
//  [-2,  0, 0, 2]
//]
// 
// Related Topics 数组 哈希表 双指针

public class FourSum {

    public static void main(String[] args) {
        Solution solution = new FourSum().new Solution();
    }

    class Solution {
        public List<List<Integer>> fourSum(int[] nums, int target) {
            List<List<Integer>> result = new ArrayList<>();
            if (nums == null || nums.length < 4) {
                return result;
            }

            Arrays.sort(nums);
            int len = nums.length;
            for (int i = 0; i < len - 3; i++) {
                if (i > 0 && nums[i] == nums[i - 1]) { // 去重
                    continue;
                }
                // 仅确定i后的剪枝
                if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
                    break; // 当前最小值比target大，增大i后更大， 因此终止外层循环
                }
                if (nums[i] + nums[len - 3] + nums[len - 2] + nums[len - 1] < target) {
                    continue;// 当前最大值比target小，当前i不合适，应增大i
                }

                for (int j = i + 1; j < len - 2; j++) {
                    if (j > i + 1 && nums[j] == nums[j - 1]) {
                        continue;
                    }
                    // 确定i, j 后的剪枝
                    if (nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) {
                        break; // 当前最小值比target大，增大j后更大， 因此终止内层循环
                    }
                    if (nums[i] + nums[j] + nums[len - 1] + nums[len - 2] < target) {
                        continue; // 当前最大值比target小，当前j合适，应增大j
                    }

                    int left = j + 1, right = len - 1;
                    while (left < right) {
                        int sum = nums[i] + nums[j] + nums[left] + nums[right];
                        if (sum < target) {
                            left++;
                        } else if (sum > target) {
                            right--;
                        } else {
                            result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                            while (left < right && nums[left] == nums[left + 1]) { // 去重
                                left = left + 1;
                            }
                            while (left < right && nums[right] == nums[right - 1]) {
                                right = right - 1;
                            }
                            left++;
                            right--;
                        }
                    }
                }
            }
            return result;
        }
    }
}