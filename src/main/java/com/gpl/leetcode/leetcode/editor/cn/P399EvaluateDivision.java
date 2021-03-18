//给出方程式 A / B = k, 其中 A 和 B 均为用字符串表示的变量， k 是一个浮点型数字。根据已知方程式求解问题，并返回计算结果。如果结果不存在，
//则返回 -1.0。 
//
// 输入总是有效的。你可以假设除法运算中不会出现除数为 0 的情况，且不存在任何矛盾的结果。
//
//
//
// 示例 1：
//
// 输入：equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"
//],["b","a"],["a","e"],["a","a"],["x","x"]]
//输出：[6.00000,0.50000,-1.00000,1.00000,-1.00000]
//解释：
//给定：a / b = 2.0, b / c = 3.0
//问题：a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
//返回：[6.0, 0.5, -1.0, 1.0, -1.0 ]
// 
//
// 示例 2： 
//
// 输入：equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], que
//ries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
//输出：[3.75000,0.40000,5.00000,0.20000]
// 
//
// 示例 3： 
//
// 输入：equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["
//a","c"],["x","y"]]
//输出：[0.50000,2.00000,-1.00000,-1.00000]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= equations.length <= 20 
// equations[i].length == 2 
// 1 <= equations[i][0].length, equations[i][1].length <= 5 
// values.length == equations.length 
// 0.0 < values[i] <= 20.0 
// 1 <= queries.length <= 20 
// queries[i].length == 2 
// 1 <= queries[i][0].length, queries[i][1].length <= 5 
// equations[i][0], equations[i][1], queries[i][0], queries[i][1] 由小写英文字母与数字组成 
// 
// Related Topics 并查集 图


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Java：除法求值
public class P399EvaluateDivision{
    public static void main(String[] args) {
        Solution solution = new P399EvaluateDivision().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

        class UnionFind {
            private Map<String, String> parents;
            private Map<String, Double> values;
            public UnionFind() {
                parents = new HashMap();
                values = new HashMap();
            }
            public String find(String element) {
                String parentStr = parents.get(element);
                while (!parentStr.equals(parents.get(parentStr))) {
                    parentStr = parents.get(parentStr);
                }
                parents.put(element, parentStr);
                return parentStr;
            }
            public boolean isConnected(String firstElement, String secondElement) {
                return find(firstElement).equals(find(secondElement));
            }
            public void unionElements(String firstElement, String secondElement) {
                String firstUnion = find(firstElement);
                String secondUnion = find(secondElement);
                if (firstUnion.equals(secondUnion)) {
                    return;
                }
                if (values.get(firstUnion) < values.get(secondUnion)) {
                    parents.put(firstUnion, secondUnion);
                    values.put(secondUnion, values.get(secondUnion) + values.get(firstUnion));
                }
            }
        }
        public double[] calcEquation1(List<List<String>> equations, double[] values, List<List<String>> queries) {

        }















        private Map<String, String> parents = new HashMap<>();
        /**
         * key : 当前节点
         * value : 父节点/当前节点
         */
        private Map<String, Double> values = new HashMap<>();

        public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
            for (int i = 0; i < equations.size(); i++) {
                union(equations.get(i).get(0), equations.get(i).get(1), values[i]);
            }
            double[] result = new double[queries.size()];
            for (int i = 0; i < queries.size(); i++) {
                String e = queries.get(i).get(0);
                String q = queries.get(i).get(1);
                if (!(parents.containsKey(e) && parents.containsKey(q))) {
                    result[i] = -1;
                    continue;
                }
                if (e.equals(q)) {
                    result[i] = 1;
                    continue;
                }
                String r1 = root(e);
                String r2 = root(q);
                if (!r1.equals(r2)) {
                    // 如果两者不相等，说明两个节点是不连通的
                    result[i] = -1;
                    continue;
                }
                result[i] = pm(q)/pm(e);
            }
            return result;
        }

        private void union(String parent, String child, double value) {
            add(parent);
            add(child);
            String r1 = root(parent);
            String r2 = root(child);
            if (!r1.equals(r2)) {
                parents.put(r2, r1);
                values.put(r2, value * (pm(parent)/pm(child)));
            }
        }
        private void add(String x) {
            if (!parents.containsKey(x)) {
                parents.put(x, x);
                values.put(x, 1.0);
            }
        }



        /**
         * 找到x的根节点
         */
        private String root(String x) {
            while (!parents.get(x).equals(x)) {
                x = parents.get(x);
            }
            return x;
        }


        /**
         * 循环的pm函数
         */
        private double pm(String x) {
            double v = 1;
            while (!parents.get(x).equals(x)) {
                v*= values.get(x);
                x = parents.get(x);
            }
            return v;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}