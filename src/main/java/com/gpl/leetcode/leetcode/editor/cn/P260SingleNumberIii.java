//给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。 
//
// 示例 : 
//
// 输入: [1,2,1,3,2,5]
//输出: [3,5] 
//
// 注意： 
//
// 
// 结果输出的顺序并不重要，对于上面的例子， [5, 3] 也是正确答案。 
// 你的算法应该具有线性时间复杂度。你能否仅使用常数空间复杂度来实现？ 
// 
// Related Topics 位运算


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

//Java：只出现一次的数字 III
public class P260SingleNumberIii{
    public static void main(String[] args) {
        Solution solution = new P260SingleNumberIii().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

        //https://mp.weixin.qq.com/s/WOmDQcgq9IwL43WP11KItg
    public int[] singleNumber1(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }
        int[] ans = new int[2];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() == 1) {
                ans[index++] = entry.getKey();
            }
        }
        return ans;
    }

    public int[] singleNumber(int[] nums) {
        int bitmask = 0;
        for (int num : nums) {
            bitmask ^= num;
        }
        //此时bitmask只包含两个只出现一次的数 bitmask = x ^ y

        int diff = bitmask & (-bitmask);//bitmask最右边的1
        int x = 0;
        for (int num : nums) {
            if ((num & diff) != 0) {
                x ^= num;
            }
        }
        int[] ans = {x, x ^ bitmask};
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}