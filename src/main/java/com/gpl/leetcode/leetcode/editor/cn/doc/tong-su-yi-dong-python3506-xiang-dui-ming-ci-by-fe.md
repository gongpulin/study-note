
## 思路

如果直接排序是不行的，因为会丢失老的索引。 因此我用了一个 pairs 数组，来维护一个 nums -> 索引的映射。之后对 paris 进行降序排列即可。由于我事先保存了老的索引信息，因此是没有问题的。


## 代码

```py
class Solution:
    def findRelativeRanks(self, nums: List[int]) -> List[str]:
        pairs = []
        for i in range(len(nums)):
            pairs.append([nums[i], i])
        pairs.sort(key=lambda a: a[0], reverse=True)
        for i in range(len(nums)):
            if i == 0:
                nums[pairs[i][1]] = "Gold Medal"
            if i == 1:
                nums[pairs[i][1]] = "Silver Medal"
            if i == 2:
                nums[pairs[i][1]] = "Bronze Medal"
            if i > 2:
                nums[pairs[i][1]] = str(i + 1)
        return nums
```

**复杂度分析**
- 时间复杂度：复杂度的话注意是排序引起的，因此为 *O(NlogN)*
- 空间复杂度：由于使用了 paris 数组， 因此空间复杂度为 *O(N)*

更多题解可以访问我的LeetCode题解仓库：https://github.com/azl397985856/leetcode  。 目前已经30K star啦。

关注公众号力扣加加，努力用清晰直白的语言还原解题思路，并且有大量图解，手把手教你识别套路，高效刷题。
