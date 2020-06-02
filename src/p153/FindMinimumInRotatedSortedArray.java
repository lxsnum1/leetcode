package p153;

//假设按照升序排序的数组在预先未知的某个点上进行了旋转。 

//
// ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。 
//
// 请找出其中最小的元素。 
//
// 你可以假设数组中不存在重复元素。 
//
// 示例 1: 
//
// 输入: [3,4,5,1,2]
// 输出: 1 
//
// 示例 2: 
//
// 输入: [4,5,6,7,0,1,2]
// 输出: 0 
// Related Topics 数组 二分查找

/**
 * important: 此揭发不适用于数组中存在重复元素
 * 
 * eg: nums[lo] = nums[mid] = nums[hi]
 */
public class FindMinimumInRotatedSortedArray {

    public static void main(String[] args) {
        Solution solution = new FindMinimumInRotatedSortedArray().new Solution();
    }

    class Solution {
        public int findMin(int[] nums) {
            int lo = 0, hi = nums.length - 1, mid = 0;

            while (nums[lo] > nums[hi]) {
                mid = lo + (hi - lo) / 2; // 地板除，mid更靠近left
                if (nums[mid] >= nums[lo]) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }
            return nums[lo];
        }
    }

    /**
     * SolutionV2
     */
    public class SolutionV2 {

        public int findMin(int[] nums) {
            int lo = 0, hi = nums.length - 1;
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                if (nums[mid] < nums[hi]) {
                    hi = mid;
                } else {
                    lo = mid + 1;
                }
            }
            return nums[lo];
        }
    }
}