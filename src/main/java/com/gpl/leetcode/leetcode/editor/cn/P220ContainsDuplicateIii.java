//在整数数组 nums 中，是否存在两个下标 i 和 j，使得 nums [i] 和 nums [j] 的差的绝对值小于等于 t ，且满足 i 和 j 的差的
//绝对值也小于等于 ķ 。 
//
// 如果存在则返回 true，不存在返回 false。 
//
// 
//
// 示例 1: 
//
// 输入: nums = [1,2,3,1], k = 3, t = 0
//输出: true 
//
// 示例 2: 
//
// 输入: nums = [1,0,1,1], k = 1, t = 2
//输出: true 
//
// 示例 3: 
//
// 输入: nums = [1,5,9,1,5,9], k = 2, t = 3
//输出: false 
// Related Topics 排序 Ordered Map


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

//Java：存在重复元素 III
public class P220ContainsDuplicateIii{
    public static void main(String[] args) {
        Solution solution = new P220ContainsDuplicateIii().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

        // Get the ID of the bucket from element value x and bucket width w
        // In Java, `-3 / 5 = 0` and but we need `-3 / 5 = -1`.
        private long getID(long x, long w) {
            return x < 0 ? (x + 1) / w - 1 : x / w;
        }

        public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
            if (t < 0) {
                return false;
            }
            Map<Long, Long> d = new HashMap<>();
            long w = (long)t + 1;
            for (int i = 0; i < nums.length; ++i) {
                long m = getID(nums[i], w);
                // check if bucket m is empty, each bucket may contain at most one element
                if (d.containsKey(m)) {
                    return true;
                }

                // check the nei***or buckets for almost duplicate
                if (d.containsKey(m - 1) && Math.abs(nums[i] - d.get(m - 1)) < w) {
                    return true;
                }

                if (d.containsKey(m + 1) && Math.abs(nums[i] - d.get(m + 1)) < w) {
                    return true;
                }

                // now bucket m is empty and no almost duplicate in nei***or buckets
                d.put(m, (long)nums[i]);
                if (i >= k) {
                    d.remove(getID(nums[i - k], w));
                }
            }
            return false;
        }


        public boolean containsNearbyAlmostDuplicate1(int[] nums, int k, int t) {
            TreeSet<Integer> set = new TreeSet<>();
            for (int i = 0; i < nums.length; ++i) {
                // Find the successor of current element
                Integer s = set.ceiling(nums[i]);
                if (s != null && (long)s <= (long)nums[i] + (long)t) {
                    return true;
                }

                // Find the predecessor of current element
                Integer g = set.floor(nums[i]);
                if (g != null && (long)nums[i] <= (long)g + (long)t) {
                    return true;
                }

                set.add(nums[i]);
                if (set.size() > k) {
                    set.remove(nums[i - k]);
                }
            }
            return false;
        }

        //Time Limit Exceeded
        public boolean containsNearbyAlmostDuplicate2(int[] nums, int k, int t) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int len = nums.length;
//        k = Math.min(k, len-1);
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j <= i + k && j <= len - 1; j++) { //注意这里的下标
                long abs = Math.abs((long)nums[i] - (long)nums[j]);
                if (abs <= t) {
                    return true;
                }
            }
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}