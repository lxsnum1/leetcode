package p046;

import java.util.*;

//给定一个 没有重复 数字的序列，返回其所有可能的全排列。
//
// 示例:
//
// 输入: [1,2,3]
//输出:
//[
//  [1,2,3],
//  [1,3,2],
//  [2,1,3],
//  [2,3,1],
//  [3,1,2],
//  [3,2,1]
//]
// Related Topics 回溯算法
// 👍 912 👎 0

//Java：全排列
public class Permutations {
    public static void main(String[] args) {
        SolutionV2 solution = new Permutations().new SolutionV2();
        int[] nums = { 1, 2, 3 };
        List<List<Integer>> permute = solution.permute(nums);
        System.out.println(permute);

    }

    // leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> permute(int[] nums) {

            List<Integer> list = new ArrayList<>();
            List<List<Integer>> res = new LinkedList<>();
            int len = nums.length;
            for (int i = 0; i < len; i++) {
                list.add(nums[i]);
            }

            backtrack(list, 0, len, res);
            return res;
        }

        private void backtrack(List<Integer> list, int start, int length, List<List<Integer>> res) {
            if (start == length) {
                res.add(new ArrayList<>(list));
                return;
            }

            for (int i = start; i < length; i++) {
                // list中元素的顺序代表了树节点的状态
                Collections.swap(list, i, start);
                backtrack(list, start + 1, length, res);
                Collections.swap(list, i, start);
            }
        }
    }
    // leetcode submit region end(Prohibit modification and deletion)

    class SolutionV2 {
        public List<List<Integer>> permute(int[] nums) {
            int len = nums.length;
            List<List<Integer>> res = new LinkedList<>();
            Deque<Integer> stack = new ArrayDeque<>();
            boolean[] isVisited = new boolean[len];

            dfs(nums, isVisited, stack, res);

            return res;
        }

        private void dfs(int[] nums, boolean[] isVisited, Deque<Integer> stack, List<List<Integer>> res) {
            int len = nums.length;
            if (stack.size() == len) {
                res.add(new ArrayList<>(stack));
            }

            for (int i = 0; i < len; i++) {
                if (isVisited[i]) {
                    continue;
                }
                stack.addLast(nums[i]);
                isVisited[i] = true;
                dfs(nums, isVisited, stack, res);
                stack.removeLast();
                isVisited[i] = false;
            }
        }
    }

    class SolutionV3 {

        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> res = new LinkedList<>();
            res.add(new LinkedList<>());

            for (int i = 0; i < nums.length; i++) {
                res = addEverywhere(res, nums[i]);
            }
            return res;

        }

        private List<List<Integer>> addEverywhere(List<List<Integer>> res, int num) {
            List<List<Integer>> ret = new LinkedList<>();
            for (List<Integer> list : res) {
                for (int i = 0; i <= list.size(); i++) {
                    List<Integer> tmp = new LinkedList<>(list);
                    tmp.add(i, num);
                    ret.add(tmp);
                }
            }
            return ret;
        }

    }

}