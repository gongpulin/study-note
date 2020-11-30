//在二维平面上计算出两个由直线构成的矩形重叠后形成的总面积。 
//
// 每个矩形由其左下顶点和右上顶点坐标表示，如图所示。 
//
// 
//
// 示例: 
//
// 输入: -3, 0, 3, 4, 0, -1, 9, 2
//输出: 45 
//
// 说明: 假设矩形面积不会超出 int 的范围。 
// Related Topics 数学


package com.gpl.leetcode.leetcode.editor.cn;
//Java：矩形面积
public class P223RectangleArea{
    public static void main(String[] args) {
        Solution solution = new P223RectangleArea().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        //-3, 0, 3, 4, 0, -1, 9, 2
        //A,  B, C, D, E,  F, G, H
        long area = (C - A) * (D - B) + (G - E) * (H - F);
        long x = Math.max(0, (long)Math.min(G, C) - Math.max(A, E));
        long y = Math.max(0, (long)Math.min(D, H) - Math.max(B, F));
        return (int)(area - x * y);

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}