//在本问题中, 树指的是一个连通且无环的无向图。 
//
// 输入一个图，该图由一个有着N个节点 (节点值不重复1, 2, ..., N) 的树及一条附加的边构成。附加的边的两个顶点包含在1到N中间，这条附加的边不属
//于树中已存在的边。 
//
// 结果图是一个以边组成的二维数组。每一个边的元素是一对[u, v] ，满足 u < v，表示连接顶点u 和v的无向图的边。 
//
// 返回一条可以删去的边，使得结果图是一个有着N个节点的树。如果有多个答案，则返回二维数组中最后出现的边。答案边 [u, v] 应满足相同的格式 u < v。
// 
//
// 示例 1： 
//
// 输入: [[1,2], [1,3], [2,3]]
//输出: [2,3]
//解释: 给定的无向图为:
//  1
// / \
//2 - 3
// 
//
// 示例 2： 
//
// 输入: [[1,2], [2,3], [3,4], [1,4], [1,5]]
//输出: [1,4]
//解释: 给定的无向图为:
//5 - 1 - 2
//    |   |
//    4 - 3
// 
//
// 注意: 
//
// 
// 输入的二维数组大小在 3 到 1000。 
// 二维数组中的整数在1到N之间，其中N是输入数组的大小。 
// 
//
// 更新(2017-09-26): 
//我们已经重新检查了问题描述及测试用例，明确图是无向 图。对于有向图详见冗余连接II。对于造成任何不便，我们深感歉意。 
// Related Topics 树 并查集 图


package com.gpl.leetcode.leetcode.editor.cn;
//Java：冗余连接
public class P684RedundantConnection{
    public static void main(String[] args) {
        Solution solution = new P684RedundantConnection().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

        class UnionFind {
            private int size;
            private int[] parents;
            public UnionFind(int size) {
                this.size = size;
                parents = new int[size];
                for (int i = 0; i < size; i++) {
                    parents[i] = i;
                }
            }
            public int find(int element) {
                while (element != parents[element]) {
                    element = parents[element];
                }
                return element;
            }
            public boolean isConnected(int firstElement, int secondElement) {
                return find(firstElement) == find(secondElement);
            }
            public void unionElements(int firstElement, int secondElement) {
                int firstUnion = find(firstElement);
                int secondUnion = find(secondElement);
                if (firstUnion == secondUnion) {
                    ans[0] = firstElement;
                    ans[1] = secondElement;
                    return;
                }
                parents[firstUnion] = secondUnion;
            }
        }
        int[] ans = new int[2];
        public int[] findRedundantConnection(int[][] edges) {

            UnionFind unionFind = new UnionFind(edges.length + 1);
            for (int[] edge : edges) {
                unionFind.unionElements(edge[0], edge[1]);
            }
            return ans;
        }










        //缓存结果集
        int[] result = new int[2];

        public int[] findRedundantConnection1(int[][] edges) {
            int[] father = new int[edges.length + 1];
            for (int i = 0; i < father.length; i++) {
                father[i] = i;
            }
            for (int[] edge : edges) {
                union(father, edge[0], edge[1]);
            }
            return result;
        }

        //路径压缩
        public int findXFather(int[] father, int x) {
            while (father[x]!=x){
                father[x] = father[father[x]];
                x = father[x];
            }
            return x;
        }

        //合并两个能连接上的点，father合为最后确定的father
        public void union(int[] father, int x, int y) {
            int xFather = findXFather(father, x);
            int yFather = findXFather(father, y);
            if (xFather != yFather) {
                father[xFather]=yFather;
            } else {
                //在发现两个点的连接已经存在时，就更新缓存，题目要最后一个，遍历到最后一个就是结果
                result[0] = x;
                result[1] = y;
            }
        }
}
//leetcode submit region end(Prohibit modification and deletion)

}