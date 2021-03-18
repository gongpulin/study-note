### 解题思路

#### 广度优先搜索（BFS）：

按层级遍历，从右到左，记录每层最后一个节点的val值，将最后一层最后一个节点val值返回。

#### 深度优先搜索（DFS）：

层级索引与`List`索引相对应，递归从左至右遍历，将每层最左端的节点放入`List`，并返回`List`最后一个节点的val值。

### 代码

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 * 广度优先搜索（BFS）
 */
class Solution {
    public int findBottomLeftValue(TreeNode root) {
        int lastNodeVal = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (i == size - 1) {
                    lastNodeVal = node.val;
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
            }
        }
        return lastNodeVal;
    }
}
/**
 * 深度优先搜索（DFS）
 */
class Solution {
    public int findBottomLeftValue(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        dfs(root, list, 0);
        return list.get(list.size()-1);
    }

    private void dfs(TreeNode node, List<Integer> list, int level) {
        if (node == null) return;

        if (list.size() == level) {
            list.add(node.val);
        }

        dfs(node.left, list, level+1);
        dfs(node.right, list, level+1);
    }
}
```