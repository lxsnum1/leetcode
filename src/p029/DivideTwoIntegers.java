package p029;

// 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。 

//
// 返回被除数 dividend 除以除数 divisor 得到的商。 
//
// 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2 
//
// 
//
// 示例 1: 
//
// 输入: dividend = 10, divisor = 3
// 输出: 3
// 解释: 10/3 = truncate(3.33333..) = truncate(3) = 3 
//
// 示例 2: 
//
// 输入: dividend = 7, divisor = -3
// 输出: -2
// 解释: 7/-3 = truncate(-2.33333..) = -2 
//
// 
//
// 提示： 
//
// 
// 被除数和除数均为 32 位有符号整数。 
// 除数不为 0。 
// 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−2^31, 2^31 − 1]。本题中，如果除法结果溢出，则返回 231 − 1。 
// 
// Related Topics 数学 二分查找

public class DivideTwoIntegers {

    public static void main(String[] args) {
        Solution solution = new DivideTwoIntegers().new Solution();
        solution.divide(2147483647, 3);
    }

    class Solution {
        public int divide(int dividend, int divisor) {

            if (divisor == 0) {
                return 0;
            }
            if (dividend == Integer.MIN_VALUE && divisor == -1) {
                return Integer.MAX_VALUE;
            }

            boolean negative = (dividend ^ divisor) < 0;
            int a = dividend < 0 ? dividend : -dividend;
            int b = divisor < 0 ? divisor : -divisor;
            return negative ? -divOfNegatives(a, b) : divOfNegatives(a, b);

        }

        /**
         * div of two negative integers: a and b
         * 
         * @param a <= 0
         * @param b < 0
         * @return return a positive result
         */
        private int divOfNegatives(int a, int b) {
            if (a > b) {
                return 0;
            }

            int count = 1;
            int exp = b;
            while (exp + exp > a && exp + exp < 0) { // to prevent exp underflow
                count = count + count;
                exp = exp + exp;
            }

            return count + divOfNegatives(a - exp, b);
        }
    }
}