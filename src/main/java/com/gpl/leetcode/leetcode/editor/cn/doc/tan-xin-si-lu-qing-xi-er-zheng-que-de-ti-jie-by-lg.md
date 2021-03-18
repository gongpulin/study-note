# 思路
本题大家都很容易想到用动态规划来求解，求解的过程类似最长上升子序列，不过是需要判断两个序列。大家在实现动态规划的平方复杂度时，就会考虑如何优化到线性复杂度。

假设 `up[i]` 表示 `nums[0:i]` 中最后两个数字递增的最长摆动序列长度，`down[i]` 表示 `nums[0:i]` 中最后两个数字递减的最长摆动序列长度，只有一个数字时默认为 `1`。

接下来我们进行分类讨论：
1. `nums[i+1] > nums[i]`
    - 假设 `down[i]` 表示的最长摆动序列的最远末尾元素下标正好为 `i`，遇到新的上升元素后，`up[i+1] = down[i] + 1` ，这是因为 `up` 一定从 `down` 中产生（初始除外），并且 `down[i]` 此时最大。
    - 假设 `down[i]` 表示的最长摆动序列的最远末尾元素下标小于 `i`，设为 `j`，那么 `nums[j:i]` 一定是递增的，因为若完全递减，最远元素下标等于 `i`，若波动，那么 `down[i] > down[j]`。由于 `nums[j:i]` 递增，`down[j:i]` 一直等于 `down[j]` ，依然满足 `up[i+1] = down[i] + 1` 。
2. `nums[i+1] < nums[i]`，类似第一种情况
3. `nums[i+1] == nums[i]`，新的元素不能用于任何序列，保持不变

# 演示
`nums=[1,7,4,9,2,5]` 时，演示如下：
 [image.png](https://pic.leetcode-cn.com/dd09644d01ea873cfb14a3d538c7b6b49680f5d840e22f3eef6a5e07aec78db0-image.png)
怎么样，是不是清晰易懂呀～

注意到 `down` 和 `up` 只和前一个状态有关，所以我们可以优化存储，分别用一个变量即可。

# 代码
```Java []
public int wiggleMaxLength(int[] nums) {
    int down = 1, up = 1;
    for (int i = 1; i < nums.length; i++) {
        if (nums[i] > nums[i - 1])
            up = down + 1;
        else if (nums[i] < nums[i - 1])
            down = up + 1;
    }
    return nums.length == 0 ? 0 : Math.max(down, up);
}
```
