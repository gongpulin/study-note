//题中给出一个 n_rows 行 n_cols 列的二维矩阵，且所有值被初始化为 0。要求编写一个 flip 函数，均匀随机的将矩阵中的 0 变为 1，并返回
//该值的位置下标 [row_id,col_id]；同样编写一个 reset 函数，将所有的值都重新置为 0。尽量最少调用随机函数 Math.random()，并且
//优化时间和空间复杂度。 
//
// 注意: 
//
// 
// 1 <= n_rows, n_cols <= 10000 
// 0 <= row.id < n_rows 并且 0 <= col.id < n_cols 
// 当矩阵中没有值为 0 时，不可以调用 flip 函数 
// 调用 flip 和 reset 函数的次数加起来不会超过 1000 次 
// 
//
// 示例 1： 
//
// 输入: 
//["Solution","flip","flip","flip","flip"]
//[[2,3],[],[],[],[]]
//输出: [null,[0,1],[1,2],[1,0],[1,1]]
// 
//
// 示例 2： 
//
// 输入: 
//["Solution","flip","flip","reset","flip"]
//[[1,2],[],[],[],[]]
//输出: [null,[0,0],[0,1],null,[0,0]] 
//
// 输入语法解释： 
//
// 输入包含两个列表：被调用的子程序和他们的参数。Solution 的构造函数有两个参数，分别为 n_rows 和 n_cols。flip 和 reset 没
//有参数，参数总会以列表形式给出，哪怕该列表为空 
// Related Topics Random


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

//Java：随机翻转矩阵
public class P519RandomFlipMatrix{
    public static void main(String[] args) {
        Solution solution = new P519RandomFlipMatrix().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

//    public Solution(int n_rows, int n_cols) {
////
////    }
////
////    public int[] flip() {
////
////    }
////
////    public void reset() {
////
////    }

        Map<Integer, Integer> V = new HashMap<>();
        int nr, nc, rem;
        Random rand = new Random();

        public Solution(int n_rows, int n_cols) {
            nr = n_rows;
            nc = n_cols;
            rem = nr * nc;
        }

        public int[] flip() {
            int r = rand.nextInt(rem--);
            int x = V.getOrDefault(r, r);
            V.put(r, V.getOrDefault(rem, rem));
            return new int[]{x / nc, x % nc};
        }

        public void reset() {
            V.clear();
            rem = nr * nc;
        }

    }

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(n_rows, n_cols);
 * int[] param_1 = obj.flip();
 * obj.reset();
 */
//leetcode submit region end(Prohibit modification and deletion)

}