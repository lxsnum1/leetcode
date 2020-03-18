package p007;

//给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。 

//
// 示例 1: 
//
// 输入: 123
//输出: 321
// 
//
// 示例 2: 
//
// 输入: -123
//输出: -321
// 
//
// 示例 3: 
//
// 输入: 120
//输出: 21
// 
//
// 注意: 
//
// 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231, 231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。 
// Related Topics 数学

public class ReverseInteger {
    public static void main(String[] args) {
        Solution solution = new ReverseInteger().new Solution();
    }

    // leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Solution
     * 
     * int type number range : -2^31=-2147483648 ~ 2^31-1=2147483647, so, when res=
     * Integer.MAX_VALUE / 10, the msb of the input number must be 0, 1, 2.
     */
    class Solution {

        public int reverse(int x) {
            int res = 0;
            while (x != 0) {
                if (res > Integer.MAX_VALUE / 10 || res < Integer.MIN_VALUE / 10) {
                    return 0;
                }
                res = res * 10 + x % 10;
                x = x / 10;
            }
            return res;
        }
    }
    // leetcode submit region end(Prohibit modification and deletion)

    public class SolutionV2 {

        public int reverse(int x) {
            int res = 0;
            while (x != 0) {
                if (res * 10 / 10 != res) {
                    return 0;
                }
                res = res * 10 + x % 10;
                x = x / 10;
            }
            return res;
        }
    }
}