package p081;

// 假设按照升序排序的数组在预先未知的某个点上进行了旋转。 
//
// ( 例如，数组 [0,0,1,2,2,5,6] 可能变为 [2,5,6,0,0,1,2] )。 
//
// 编写一个函数来判断给定的目标值是否存在于数组中。若存在返回 true，否则返回 false。 
//
// 示例 1: 
//
// 输入: nums = [2,5,6,0,0,1,2], target = 0
// 输出: true
// 
//
// 示例 2: 
//
// 输入: nums = [2,5,6,0,0,1,2], target = 3
// 输出: false 
//
// 进阶: 
//
// 
// 这是 搜索旋转排序数组 的延伸题目，本题中的 nums 可能包含重复元素。 
// 这会影响到程序的时间复杂度吗？会有怎样的影响，为什么？ 
// 
// Related Topics 数组 二分查找

public class SearchInRotatedSortedArrayIi {

    public static void main(String[] args) {
        Solution solution = new SearchInRotatedSortedArrayIi().new Solution();
        int[] nums = new int[] { 0, 1, 1, 2, 0, 0 };
        solution.search(nums, 2);
    }

    class Solution {
        public boolean search(int[] nums, int target) {
            int lo = 0, hi = nums.length - 1;
            while (lo <= hi) {
                int mid = (lo + hi) / 2;
                if (nums[mid] == target) {
                    return true;
                }

                // 去掉一个重复的干扰项，eg: nums= {1,0,1,1,1} or {1,1,1,0,1}
                if (nums[lo] == nums[mid]) {
                    lo++;
                    continue;
                }

                if (nums[mid] > nums[lo]) {// 左半部分不含旋转
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

            return false;
        }
    }
}