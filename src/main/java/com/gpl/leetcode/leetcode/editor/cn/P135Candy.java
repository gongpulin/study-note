//老师想给孩子们分发糖果，有 N 个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。 
//
// 你需要按照以下要求，帮助老师给这些孩子分发糖果： 
//
// 
// 每个孩子至少分配到 1 个糖果。 
// 相邻的孩子中，评分高的孩子必须获得更多的糖果。 
// 
//
// 那么这样下来，老师至少需要准备多少颗糖果呢？ 
//
// 示例 1: 
//
// 输入: [1,0,2]
//输出: 5
//解释: 你可以分别给这三个孩子分发 2、1、2 颗糖果。
// 
//
// 示例 2: 
//
// 输入: [1,2,2]
//输出: 4
//解释: 你可以分别给这三个孩子分发 1、2、1 颗糖果。
//     第三个孩子只得到 1 颗糖果，这已满足上述两个条件。 
// Related Topics 贪心算法


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.Arrays;

//Java：分发糖果
public class P135Candy{
    public static void main(String[] args) {
        Solution solution = new P135Candy().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int candy(int[] ratings) {
        int len = ratings.length;
        int[] l2r = new int[len];
        int[] r2l = new int[len];
        Arrays.fill(l2r, 1);
        Arrays.fill(r2l, 1);
        for (int i = 1; i < len; i++) {
            if (ratings[i] > ratings[i - 1]) {
                l2r[i] = l2r[i - 1] + 1;
            }
        }
        for (int j = len - 2; j >= 0; j--) {
            if (ratings[j] > ratings[j + 1]) {
                r2l[j] = r2l[j + 1] + 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < len; i++) {
            ans += Math.max(l2r[i], r2l[i]);
        }
        return ans;
    }


//        public int candy(int[] ratings) {
//            int len = ratings.length;
//            int[] candies = new int[len];
//            Arrays.fill(candies, 1);
//            boolean flag = true;
//            while (flag) {
//                flag = false;
//                for (int i = 0; i < len; i++) {
//                    if (i != len - 1 && ratings[i] > ratings[i + 1] && candies[i] <= candies[i + 1]) {
//                        candies[i] = candies[i + 1] + 1;
//                        flag = true;
//                    }
//                    if (i != 0 && ratings[i] > ratings[i - 1] && candies[i] <= candies[i - 1]) {
//                        candies[i] = candies[i - 1] + 1;
//                        flag = true;
//                    }
//                }
//            }
//            int ans = 0;
//            for (int num : candies) {
//                ans += num;
//            }
//            return ans;
//        }
}
//leetcode submit region end(Prohibit modification and deletion)

}