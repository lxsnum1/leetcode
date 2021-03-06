package p070;

//假设你正在爬楼梯。需要 n 阶你才能到达楼顶。 

//
// 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？ 
//
// 注意：给定 n 是一个正整数。 
//
// 示例 1： 
//
// 输入： 2
// 输出： 2
// 解释： 有两种方法可以爬到楼顶。
// 1.  1 阶 + 1 阶
// 2.  2 阶
//
// 示例 2： 
//
// 输入： 3
// 输出： 3
// 解释： 有三种方法可以爬到楼顶。
// 1.  1 阶 + 1 阶 + 1 阶
// 2.  1 阶 + 2 阶
// 3.  2 阶 + 1 阶
// 
// Related Topics 动态规划

public class ClimbingStairs {

    public static void main(String[] args) {
        Solution solution = new ClimbingStairs().new Solution();
    }

    // leetcode submit region begin(Prohibit modification and deletion)

    /**
     * recursive solution
     */
    class Solution {
        public int climbStairs(int n) {
            if (n < 3) {
                return n;
            }

            return climbStairs(n - 1) + climbStairs(n - 2);
        }
    }
    // leetcode submit region end(Prohibit modification and deletion)

    /**
     * SolutionV2
     * <p>
     * dynamic programming
     */
    public class SolutionV2 {

        public int climbStairs(int n) {
            if (n < 3) {
                return n;
            }
            int[] steps = new int[n + 1];
            steps[0] = 0;
            steps[1] = 1;
            steps[2] = 2;

            for (int i = 3; i <= n; i++) {
                steps[i] = steps[i - 1] + steps[i - 2];
            }
            return steps[n];
        }
    }

    /**
     * SolutionV3
     * <p>
     * dynamic programming, space optimized
     */
    public class SolutionV3 {

        public int climbStairs(int n) {
            if (n < 3) {
                return n;
            }

            int x = 1;
            int y = 2;
            int z = 3;

            for (int i = 3; i <= n; i++) {
                z = y + x;
                x = y;
                y = z;
            }
            return z;
        }
    }
}