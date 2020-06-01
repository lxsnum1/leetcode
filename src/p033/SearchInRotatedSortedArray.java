package p033;

// 假设按照升序排序的数组在预先未知的某个点上进行了旋转。 

// ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。 

// 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。 

// 你可以假设数组中不存在重复的元素。 

// 你的算法时间复杂度必须是 O(log n) 级别。 

// 示例 1: 
// 输入: nums = [4,5,6,7,0,1,2], target = 0
// 输出: 4

// 示例 2: 
// 输入: nums = [4,5,6,7,0,1,2], target = 3
// 输出: -1 

// Related Topics 数组 二分查找

public class SearchInRotatedSortedArray {

    public static void main(String[] args) {
        Solution solution = new SearchInRotatedSortedArray().new Solution();
    }

    class Solution {
        public int search(int[] nums, int target) {
            int lo = 0, hi = nums.length - 1;
            while (lo <= hi) {
                int mid = (lo + hi) / 2;
                if (nums[mid] == target) {
                    return mid;
                }

                if (target >= nums[0]) { // 目标值在左半段时，
                    if (nums[mid] < nums[0]) { // 若 mid 在右半段，则将 mid 索引的值改成 inf
                        nums[mid] = Integer.MAX_VALUE;
                    }
                } else { // 目标值在右半段时，
                    if (nums[mid] >= nums[0]) { // 若 mid 在左半段，则将 mid 索引的值改成 -inf
                        nums[mid] = Integer.MIN_VALUE;
                    }
                }

                if (target < nums[mid]) {
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            }
            return -1;
        }
    }

    class SolutionV2 {
        public int search(int[] nums, int target) {
            int lo = 0, hi = nums.length - 1;

            while (lo <= hi) {
                int mid = (lo + hi) / 2;
                if (nums[mid] == target) {
                    return mid;
                }

                if (nums[mid] >= nums[0]) { // 左半部分不含旋转
                    if (target < nums[mid] && target >= nums[lo]) { // 目标在左半部分
                        hi = mid - 1;
                    } else {
                        lo = mid + 1;
                    }
                } else {// 右半部分不含旋转
                    if (target > nums[mid] && target <= nums[hi]) { // 目标在右半部分
                        lo = mid + 1;
                    } else {
                        hi = mid - 1;
                    }
                }
            }
            return -1;
        }
    }
}