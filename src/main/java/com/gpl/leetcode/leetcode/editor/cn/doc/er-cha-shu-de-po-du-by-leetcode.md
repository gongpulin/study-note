#### 方法：递归

**算法**

从问题的描述中，可以清楚地了解到，我们需要在给定树的每个结点处找到其坡度，并将所有的坡度相加以获得最终结果。要找出任意结点的坡度，我们需要求出该结点的左子树上所有结点和以及其右子树上全部结点和的差值。 

因此，为了找出解决方案，我们使用递归函数 `traverse`，在任何结点调用该函数，都会返回当前结点下面（包括其自身）的结点和。借助于任何结点的左右子结点的这一和值，我们可以直接获得该结点所对应的坡度。

下面的动画描述了值的传递和坡度计算的方式：


  [1200](https://pic.leetcode-cn.com/Figures/563_BinarySlide1.PNG)  [1200](https://pic.leetcode-cn.com/Figures/563_BinarySlide2.PNG)  [1200](https://pic.leetcode-cn.com/Figures/563_BinarySlide3.PNG)  [1200](https://pic.leetcode-cn.com/Figures/563_BinarySlide4.PNG)  [1200](https://pic.leetcode-cn.com/Figures/563_BinarySlide5.PNG)  [1200](https://pic.leetcode-cn.com/Figures/563_BinarySlide6.PNG)  [1200](https://pic.leetcode-cn.com/Figures/563_BinarySlide7.PNG)  [1200](https://pic.leetcode-cn.com/Figures/563_BinarySlide8.PNG)  [1200](https://pic.leetcode-cn.com/Figures/563_BinarySlide9.PNG)  [1200](https://pic.leetcode-cn.com/Figures/563_BinarySlide10.PNG)  [1200](https://pic.leetcode-cn.com/Figures/563_BinarySlide11.PNG)  [1200](https://pic.leetcode-cn.com/Figures/563_BinarySlide12.PNG)  [1200](https://pic.leetcode-cn.com/Figures/563_BinarySlide13.PNG)  [1200](https://pic.leetcode-cn.com/Figures/563_BinarySlide14.PNG) 


```java [xB2Wwq9f-Java]
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class Solution {
    int tilt=0;
    public int findTilt(TreeNode root) {
        traverse(root);
        return tilt;
    }
    public int traverse(TreeNode root)
    {
        if(root==null )
            return 0;
        int left=traverse(root.left);
        int right=traverse(root.right);
        tilt+=Math.abs(left-right);
        return left+right+root.val;
    }
}
```


**复杂度分析** 

* 时间复杂度：*O(n)*，其中 *n* 是结点的数目。每个结点访问一次。
* 空间复杂度：*O(n)*，在最糟糕的情形下，当树倾斜时，树的深度可以达到 *n*。平均情况下，树的深度为 *logn*。