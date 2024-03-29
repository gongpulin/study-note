### 解题思路:

这道题，把问题考虑清楚就不难了！

首先，我们调整两个矩形，让第一个矩形是靠最左边的；

其次，先考虑没有重叠的情况，有三种情况，如图所示：

 [1567149914100.png](https://pic.leetcode-cn.com/368b62ce1de4f78c2f733bf0fd956e71ef78c0bacf6b10d303dfd7a635a75784-1567149914100.png)


1. `rectangle1` 的下边都大于（等于）`rectangle2` 的上边，即 `B >= H`
2. `rectangle1` 的右边都小于（等于）`rectangle2`的左边，即 `C >= E`
3. `rectangle1` 的上边都小于（等于）`rectangle2`的下边，即 `F >= D`

最后， 要考虑重叠的情况，因为一定有重叠，所以可以找到上下左右边界

上边界，取两个矩形的上边界的最小值

下边界，取两个矩形的下边界的最大值

左边界，取两个矩形的左边界的最大值

右边界，取两个矩形的右边界的最小值

得到重叠面积，只需要两个矩形相加减去重叠面积即可！

------

有疑惑的地方，要留言哦~

### 代码:

```python
class Solution:
    def computeArea(self, A: int, B: int, C: int, D: int, E: int, F: int, G: int, H: int) -> int:
        # 调整两个矩形位置, 让第一个矩形靠最左边
        if A > E:
            return self.computeArea(E, F, G, H, A, B, C, D)
        # 没有重叠的情况
        if B >= H or D <= F or C <= E:
            return abs(A - C) * abs(B - D) + abs(E - G) * abs(F - H)
        # 重叠情况
        # 下边界
        down = max(A, E)
        # 上
        up = min(C, G)
        # 左
        left = max(B, F)
        # 右
        right = min(D, H)
        return abs(A - C) * abs(B - D) + abs(E - G) * abs(F - H) - abs(up - down) * abs(left - right)
```

