//验证给定的字符串是否可以解释为十进制数字。 
//
// 例如: 
//
// "0" => true 
//" 0.1 " => true 
//"abc" => false 
//"1 a" => false 
//"2e10" => true 
//" -90e3 " => true 
//" 1e" => false 
//"e3" => false 
//" 6e-1" => true 
//" 99e2.5 " => false 
//"53.5e93" => true 
//" --6 " => false 
//"-+3" => false 
//"95a54e53" => false 
//
// 说明: 我们有意将问题陈述地比较模糊。在实现代码之前，你应当事先思考所有可能的情况。这里给出一份可能存在于有效十进制数字中的字符列表： 
//
// 
// 数字 0-9 
// 指数 - "e" 
// 正/负号 - "+"/"-" 
// 小数点 - "." 
// 
//
// 当然，在输入中，这些字符的上下文也很重要。 
//
// 更新于 2015-02-10: 
//C++函数的形式已经更新了。如果你仍然看见你的函数接收 const char * 类型的参数，请点击重载按钮重置你的代码。 
// Related Topics 数学 字符串


package com.gpl.leetcode.leetcode.editor.cn;
//Java：有效数字
public class P65ValidNumber{
    public static void main(String[] args) {
        Solution solution = new P65ValidNumber().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isNumber1(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        int len = s.length();
        if (len > 1 && s.charAt(0) == '0' && s.charAt(1) != '.') {
            return false;
        }
        int[] table = new int[3];//e + - .
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c == 'e') {
                if (table[0] == 1) {
                    return false;
                }
                table[0]++;
            } else if (c == '+' || c == '-') {
                if (table[1] == 1) {
                    return false;
                }
                table[1]++;
            } else if (c == '.') {
                if (table[2] == 1) {
                    return false;
                }
                table[2]++;
            } else if (c >= 'a' && c <= 'z') {
                return false;
            }
        }
        return true;
    }

    private int make(char c) {
        switch(c) {
            case ' ' : return 0;
            case '+' :
            case '-' : return 1;
            case '.' : return 3;
            case 'e' : return 4;
            default :
                if (c >= 48 && c <= 57) {
                    return 2;
                }
        }
        return -1;
    }
    public boolean isNumber(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        int len = s.length();
        int state = 0;
        int finals = 0b101101000;
        int[][] transfer = new int[][]{{0,1,6,2,-1},
                    {-1,-1, 6, 2,-1},
                    {-1,-1, 3,-1,-1},
                    { 8,-1, 3,-1, 4},
                    {-1, 7, 5,-1,-1},
                    { 8,-1, 5,-1,-1},
                    { 8,-1, 6, 3, 4},
                    {-1,-1, 5,-1,-1},
                    { 8,-1,-1,-1,-1}};
        for (int i = 0; i < len; i++) {
            int id = make(s.charAt(i));
            if (id < 0 ) {
                return false;
            }
            state = transfer[state][id];
            if (state < 0) {
                return false;
            }
        }
        return (finals & (1 << state)) > 0;
    }

//        public boolean isNumber(String s) {
//            int state = 0;
//            int finals = 0b101101000;
//            int[][] transfer = new int[][]{{ 0, 1, 6, 2,-1},
//                    {-1,-1, 6, 2,-1},
//                    {-1,-1, 3,-1,-1},
//                    { 8,-1, 3,-1, 4},
//                    {-1, 7, 5,-1,-1},
//                    { 8,-1, 5,-1,-1},
//                    { 8,-1, 6, 3, 4},
//                    {-1,-1, 5,-1,-1},
//                    { 8,-1,-1,-1,-1}};
//            char[] ss = s.toCharArray();
//            for(int i=0; i < ss.length; ++i) {
//                int id = make(ss[i]);
//                if (id < 0) return false;
//                state = transfer[state][id];
//                if (state < 0) return false;
//            }
//            return (finals & (1 << state)) > 0;
//        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}