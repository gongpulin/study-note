//班上有 N 名学生。其中有些人是朋友，有些则不是。他们的友谊具有是传递性。如果已知 A 是 B 的朋友，B 是 C 的朋友，那么我们可以认为 A 也是 C 
//的朋友。所谓的朋友圈，是指所有朋友的集合。 
//
// 给定一个 N * N 的矩阵 M，表示班级中学生之间的朋友关系。如果M[i][j] = 1，表示已知第 i 个和 j 个学生互为朋友关系，否则为不知道。你
//必须输出所有学生中的已知的朋友圈总数。 
//
// 
//
// 示例 1： 
//
// 输入：
//[[1,1,0],
// [1,1,0],
// [0,0,1]]
//输出：2 
//解释：已知学生 0 和学生 1 互为朋友，他们在一个朋友圈。
//第2个学生自己在一个朋友圈。所以返回 2 。
// 
//
// 示例 2： 
//
// 输入：
//[[1,1,0],
// [1,1,1],
// [0,1,1]]
//输出：1
//解释：已知学生 0 和学生 1 互为朋友，学生 1 和学生 2 互为朋友，所以学生 0 和学生 2 也是朋友，所以他们三个在一个朋友圈，返回 1 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= N <= 200 
// M[i][i] == 1 
// M[i][j] == M[j][i] 
// 
// Related Topics 深度优先搜索 并查集


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

//Java：朋友圈
public class P547FriendCircles{
    public static void main(String[] args) {
        Solution solution = new P547FriendCircles().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

        class UnionFind {
            private int size;
            private int[] parent;
            private int groups;
            public UnionFind(int size) {
                this.size = size;
                this.groups = size;
                this.parent = new int[size];
                for (int i = 0; i < size; i++) {
                    parent[i] = i;
                }
            }
            public int find(int element) {
                while (element != parent[element]) {
                    element = parent[element];
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
                    return;
                }
                this.groups--;
                parent[firstUnion] = secondUnion;
            }
//            public int getCircleNum() {
//                Set<Integer> set = new HashSet();
//                for (int i = 0; i < size; i++) {
//                    set.add(find(parent[i]));
//                }
//                return set.size();
//            }
        }

        public int findCircleNum(int[][] M) {
            if (M == null || M.length == 0) {
                return 0;
            }
            int len = M.length;
            UnionFind unionFind = new UnionFind(len);
            for (int i = 1; i < len; i++) {
                for (int j = 0; j < i; j++) {
                    if (M[i][j] == 1) {
                        unionFind.unionElements(i, j);
                    }
                }
            }
            return unionFind.groups;
//            return unionFind.getCircleNum();
        }







        public int findCircleNum1(int[][] M) {
            if (M == null || M.length == 0) {
                return 0;
            }
            int len = M.length;
            int ans = 0;
            boolean[] used = new boolean[len];
            for (int i = 0; i < len; i++) {
                if (!used[i]) {
                    dfs(M, used, i);
                    ans++;
                }
            }
            return ans;
        }

        private void dfs(int[][] M, boolean[] used, int i) {
            for (int j = 0; j < M.length; j++) {
                if (!used[j] && M[i][j] == 1) {
                    used[j] = true;
                    dfs(M, used, j);
                }
            }
        }

}
//leetcode submit region end(Prohibit modification and deletion)

}