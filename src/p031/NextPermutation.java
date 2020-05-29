package p031;

// 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。 
//
// 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。 
//
// 必须原地修改，只允许使用额外常数空间。 
//
// 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。 
// 1,2,3 → 1,3,2 
// 3,2,1 → 1,2,3 
// 1,1,5 → 1,5,1 
// Related Topics 数组

public class NextPermutation {

    public static void main(String[] args) {
        Solution solution = new NextPermutation().new Solution();
        int[] nums = new int[] { 1, 2, 3 };
        solution.nextPermutation(nums);
    }

    class Solution {
        public void nextPermutation(int[] nums) {
            if (nums.length < 2) {
                return;
            }

            int i = nums.length - 1;
            while (i > 0 && nums[i - 1] >= nums[i]) {
                i--;
            }
            if (i > 0) {
                int j = nums.length - 1;
                while (j > 0 && nums[j] <= nums[i - 1]) {
                    j--;
                }

                int tmp = nums[i - 1];
                nums[i - 1] = nums[j];
                nums[j] = tmp;
            }

            for (int left = i, right = nums.length - 1; left < right; left++, right--) {
                int tmp2 = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp2;
            }
        }
    }
}