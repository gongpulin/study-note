//给定一个机票的字符串二维数组 [from, to]，子数组中的两个成员分别表示飞机出发和降落的机场地点，对该行程进行重新规划排序。所有这些机票都属于一个从 
//JFK（肯尼迪国际机场）出发的先生，所以该行程必须从 JFK 开始。 
//
// 
//
// 提示： 
//
// 
// 如果存在多种有效的行程，请你按字符自然排序返回最小的行程组合。例如，行程 ["JFK", "LGA"] 与 ["JFK", "LGB"] 相比就更小，排序
//更靠前 
// 所有的机场都用三个大写字母表示（机场代码）。 
// 假定所有机票至少存在一种合理的行程。 
// 所有的机票必须都用一次 且 只能用一次。 
// 
//
// 
//
// 示例 1： 
//
// 输入：[["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
//输出：["JFK", "MUC", "LHR", "SFO", "SJC"]
// 
//
// 示例 2： 
//
// 输入：[["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
//输出：["JFK","ATL","JFK","SFO","ATL","SFO"]
//解释：另一种有效的行程是 ["JFK","SFO","ATL","JFK","ATL","SFO"]。但是它自然排序更大更靠后。 
// Related Topics 深度优先搜索 图


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.*;

//Java：重新安排行程
public class P332ReconstructItinerary{
    public static void main(String[] args) {
        Solution solution = new P332ReconstructItinerary().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<String> findItinerary(List<List<String>> tickets) {
        List<String> ans = new ArrayList();
        if (tickets == null || tickets.size() == 0) {
            return ans;
        }
        Map<String, PriorityQueue<String>> graph = new HashMap();
        for (List<String> ticket : tickets) {
//            PriorityQueue<String> nbr = graph.computeIfAbsent(ticket.get(0), k -> new PriorityQueue<>());
            graph.putIfAbsent(ticket.get(0), new PriorityQueue());
            PriorityQueue<String> nbr = graph.get(ticket.get(0));
            nbr.add(ticket.get(1));
        }
        visit(graph, "JFK", ans);
        return ans;
    }
    private void visit(Map<String, PriorityQueue<String>> graph, String src, List<String> ans) {
        PriorityQueue<String> nbr = graph.get(src);
        while (nbr != null && nbr.size() > 0) {
            String dst = nbr.poll();
            visit(graph, dst, ans);
        }
        ans.add(0, src);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}