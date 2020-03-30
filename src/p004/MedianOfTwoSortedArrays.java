package p004;

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

    /*
     * 1.首先，让我们在任一位置 i 将 A(长度为m) 划分成两个部分： leftA | rightA A[0],A[1],... A[i-1] |
     * A[i],A[i+1],...A[m - 1]
     *
     * 由于A有m个元素，所以有m + 1中划分方式(i = 0 ~ m)
     *
     * 我们知道len(leftA) = i, len(rightA) = m - i; 注意：当i = 0时，leftA是空集，而当i =
     * m时，rightA为空集。
     *
     * 2.采用同样的方式，将B也划分为两部分： leftB | rightB B[0],B[1],... B[j-1] | B[j],B[j+1],...B[n
     * - 1] 我们知道len(leftA) = j, len(rightA) = n - j;
     *
     * 将leftA和leftB放入一个集合，将rightA和rightB放入一个集合。再把这两个集合分别命名为leftPart和rightPart。
     *
     * leftPart | rightPart A[0],A[1],... A[i-1] | A[i],A[i+1],...A[m - 1]
     * B[0],B[1],... B[j-1] | B[j],B[j+1],...B[n - 1]
     *
     * 如果我们可以确认： 1.len(leftPart) = len(rightPart); =====> 该条件在m+n为奇数时，该推理不成立
     * 2.max(leftPart) <= min(rightPart);
     *
     * median = (max(leftPart) + min(rightPart)) / 2; 目标结果
     *
     * 要确保这两个条件满足： 1.i + j = m - i + n - j(或m - i + n - j + 1) 如果n >= m。只需要使i = 0 ~
     * m，j = (m+n+1)/2-i =====> 该条件在m+n为奇数/偶数时，该推理都成立 2.B[j] >= A[i-1] 并且 A[i] >=
     * B[j-1]
     *
     * 注意: 1.临界条件：i=0,j=0,i=m,j=n。需要考虑 2.为什么n >= m ? 由于0 <= i <= m且j =
     * (m+n+1)/2-i,必须确保j不能为负数。
     *
     * 按照以下步骤进行二叉树搜索 1.设imin = 0,imax = m，然后开始在[imin,imax]中进行搜索 2.令i = (imin+imax) /
     * 2, j = (m+n+1)/2-i 3.现在我们有len(leftPart) = len(rightPart)。而我们只会遇到三种情况：
     *
     * ①.B[j] >= A[i-1] 并且 A[i] >= B[j-1] 满足条件 ②.B[j-1] > A[i]。此时应该把i增大。 即imin = i +
     * 1; ③.A[i-1] > B[j]。此时应该把i减小。 即imax = i - 1;
     *
     */

    /**
     * SoluionV2
     */
    class SolutionV2 {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int len1 = nums1.length;
            int len2 = nums2.length;
            if (len1 > len2) {
                return findMedianSortedArrays(nums2, nums1);
            }
            int imin = 0, imax = len1; // range of i
            int maxLeft, minRight;

            while (imin <= imax) {
                int i = (imin + imax) / 2;
                int j = (len1 + len2 + 1) / 2 - i; // +1 to include both odd and even total length

                if (i > imin && nums1[i - 1] > nums2[j]) { // i is too big
                    imax = i - 1;
                } else if (i < imax && nums2[j - 1] > nums1[i]) { // i is too small
                    imin = i + 1;
                } else { // perfect i, max(left) <= min(right)
                    if (i == 0) {
                        maxLeft = nums2[j - 1];
                    } else if (j == 0) {
                        maxLeft = nums1[i - 1];
                    } else {
                        maxLeft = Math.max(nums1[i - 1], nums2[j - 1]);
                    }
                    if ((len1 + len2) % 2 == 1) { // if total length is odd, then max(left) is the median
                        return maxLeft;
                    }

                    if (i == len1) {
                        minRight = nums2[j];
                    } else if (j == len2) {
                        minRight = nums1[i];
                    } else {
                        minRight = Math.min(nums1[i], nums2[j]);
                    }
                    return (maxLeft + minRight) * 0.5;
                }
            }

            throw new IllegalArgumentException("something is wrong");
        }
    }
}