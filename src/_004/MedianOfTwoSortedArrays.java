//给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。 
//
// 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。 
//
// 你可以假设 nums1 和 nums2 不会同时为空。 
//
// 示例 1: 
//
// nums1 = [1, 3]
//nums2 = [2]
//
//则中位数是 2.0
// 
//
// 示例 2: 
//
// nums1 = [1, 2]
//nums2 = [3, 4]
//
//则中位数是 (2 + 3)/2 = 2.5
// 
// Related Topics 数组 二分查找 分治算法

public class MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        Solution solution = new MedianOfTwoSortedArrays().new Solution();
    }

    // leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int len1 = nums1.length;
            int len2 = nums2.length;
            int mid1 = (len1 + len2 + 1) / 2;
            int mid2 = (len1 + len2 + 2) / 2;
            return (getKthSmallest(nums1, 0, len1 - 1, nums2, 0, len2 - 1, mid1)
                    + getKthSmallest(nums1, 0, len1 - 1, nums2, 0, len2 - 1, mid2)) * 0.5;

        }

        private int getKthSmallest(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
            int len1 = end1 - start1 + 1;
            int len2 = end2 - start2 + 1;
            if (len1 == 0) {
                return nums2[start2 + k - 1];
            }
            if (len2 == 0) {
                return nums1[start1 + k - 1];
            }
            if (k == 1) {
                return Math.min(nums1[start1], nums2[start2]);
            }

            int i = start1 + Math.min(len1, k / 2) - 1;
            int j = start2 + Math.min(len2, k / 2) - 1;
            if (nums1[i] < nums2[j]) {
                return getKthSmallest(nums1, i + 1, end1, nums2, start2, end2, k - (i + 1 - start1));
            } else {
                return getKthSmallest(nums1, start1, end1, nums2, j + 1, end2, k - (j + 1 - start2));
            }
        }
    }
    // leetcode submit region end(Prohibit modification and deletion)

}