### 解题思路
并查集：
547:[朋友圈](https://leetcode-cn.com/problems/friend-circles/)
684:冗余连接（无向图）
685:[冗余连接（有向图）](https://leetcode-cn.com/problems/redundant-connection-ii/)
737:[句子相似性2](https://leetcode-cn.com/problems/sentence-similarity-ii/)
990:[等式方程可满足性](https://leetcode-cn.com/problems/satisfiability-of-equality-equations/)
1319:[连通网络操作次数](https://leetcode-cn.com/problems/number-of-operations-to-make-network-connected/)
952: [按公因数计算最大组件大小](https://leetcode-cn.com/problems/largest-component-size-by-common-factor/)
### 代码

```java
class Solution {
    //缓存结果集
    int[] result = new int[2];

    public int[] findRedundantConnection(int[][] edges) {
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
```
 [image.png](https://pic.leetcode-cn.com/1600234886-Oortxq-image.png)
