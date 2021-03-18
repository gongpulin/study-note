## 方法一、暴力解法【TLE】
暴力解法是最容易想到的，对于任一一个起点`i`，我们不断尝试延长区间长度`j`，检测区间`[i,j]`是否满足所有字符出现次数都是**大于等于** `k` 。
```python
class Solution:
    def longestSubstring(self, s: str, k: int) -> int:
        res = 0
        for i in range(len(s)):
            cnt = [0] * 26
            for j in range(i, len(s)):
                cnt[ord(s[j])-ord("a")] += 1
                for t in cnt:
                    if 0 < t < k: break
                else:
                    res = max(res, j - i + 1)
        return res
```
**复杂度分析**：
+ **时间复杂度**：*O(N^2)*，尝试枚举所有子串。
+ **空间复杂度**：*O(N)*，对于每个起点都要申请 `26` 字符大小数组。
## 方法二、前缀和+分治（后序遍历）
思考下上面暴力算法慢在哪里，肯定是慢在统计不可能出现的区域内。例如 `S = 'abbacabba', k = 2`
```c
a b b a c a b b a
i
  j
    j
      j
        j         无用功
          j       无用功
            j     无用功
              j   无用功
                j 无用功
```
如上我们要是能够**直接跳过不可能出现**的字符，可以剩下大部分计算。但是如何知道某个字符在**某个区间内**是不满的呢？

---

那为什么要用前缀和？
+ 当某个区间内永远不可能成为答案区间时，可以直接跳过。
+ 通过前缀和可以快速查询区间字符出现次数（因为数组不可变，前缀和比用 hash 表快）

由于答案让我们求满足条件的最长区间，我们开始枚举答案区间就是 `start = 0, end = len(s) - 1` 。当区间内出现某个位置 `pos` 字符不满足时，立刻分开。
1. 计算 `[start, pos-1]` 区间内足条件的最长区间。
2. 计算 `[pos+1, end]` 区间内足条件的最长区间。

由于我们需要满足条件的最长区间，因此需要对取两个返回值中的最大值。那子区间怎么计算呢？答案是**递归呀**！

 [395. 至少有K个重复字符的最长子串.png](https://pic.leetcode-cn.com/1606954471-dPAJnP-395.%20%E8%87%B3%E5%B0%91%E6%9C%89K%E4%B8%AA%E9%87%8D%E5%A4%8D%E5%AD%97%E7%AC%A6%E7%9A%84%E6%9C%80%E9%95%BF%E5%AD%90%E4%B8%B2.png)

**算法流程：**
1. 计算 `26` 字符在区间`[0,n-1]` 出现次数前缀和（注意前缀和长度为`n+1`，方便处理左边界）
2. 寻找区间`[start, end]` 内**第一个**不满足条件的字符所在位置`pos`
    + 左侧区间 `[start, pos-1]` 继续重复步骤 2
    + 右侧区间 `[pos+1, end]` 继续重复步骤 2
    + 返回左右区间内最大值
```c
int longestSubstring(char * s, int k){
    int n = strlen(s), prefix[26][n+1];
    memset(prefix, 0, sizeof(prefix));
    // 1. 预处理前缀和：方便计算区间内数字出现个数
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < 26; j++) {
            prefix[j][i+1] = prefix[j][i];
        }
        prefix[s[i]-'a'][i+1]++;
    }
    // 2. 统计区间 [start, end] 内满足 s[i] < k 进行划分区间递归
    int helper(int start, int end) {
        for (int i = start; i <= end; i++) {
            if (prefix[s[i]-'a'][end+1] - prefix[s[i]-'a'][start] < k) {
                return fmax(helper(start, i - 1), helper(i + 1, end));
            }
        }
        return fmax(end - start + 1, 0);  // 证明 [start,end] 都满足 或 不存在区间
    }
    return helper(0, n - 1);
}
```
如上图所示，其本质就是**自底向上**的**分治**算法（类似二叉树的后序遍历）。我们还可以使用栈进行模拟。
```python
class Solution:
    def longestSubstring(self, s: str, k: int) -> int:
        n = len(s)
        # 1. 计算前缀和
        prefix = [[0] * (n + 1) for _ in range(26)]
        for i in range(n):
            for j in range(26):
                prefix[j][i+1] = prefix[j][i]  # 把前一个抄过来先
            prefix[ord(s[i])-97][i+1] += 1  # 注意下标
        # 2. 用栈实现后序遍历（因为本题答案是全局，因此可以迭代实现）
        stack, res = [(0, n - 1)], 0
        while stack:  # 枚举区间不存在则结束
            start, end = stack.pop()
            for i in range(start, end + 1):
                if prefix[ord(s[i])-97][end+1] - prefix[ord(s[i])-97][start] < k:
                    stack.extend([(start, i - 1), (i + 1, end)])
                    break
            else:  # 如果没有 break 则证明 [start, end] 区间满足条件则更新
                res = max(res, end - start + 1)
        return res
```
**复杂度分析**：
+ **时间复杂度**：*O(N)*，构造前缀和数组需要 *O(N)*，分治算法划分区间不会出现重复计算，只需要 *O(N)*，因此总的时间复杂度为 *O(N)*。
+ **空间复杂度**：*O(N)*，对于每个起点都要申请 `26` 字符大小数组（比 hash 表快）。