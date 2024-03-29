#### 方法一：集合

**分析**

显然，最简单的方法就是枚举所有满足 `i <= j` 的 `(i, j)`，并计算出不同的 `result(i, j) = A[i] | A[i + 1] | ... | A[j]` 的数量。由于 `result(i, j + 1) = result(i, j) | A[j + 1]`，因此我们可以在 *O(N^2)* 的时间复杂度计算出所有的 `result(i, j)`，其中 *N* 是数组 `A` 的长度。

我们尝试优化一下这种最简单的枚举方法。可以发现，对于固定的 `j`，`result(j, j), result(j - 1, j), result(j - 2), j, ..., result(1, j)` 的值是单调不降的，因为将 `result(k, j)` 对 `A[k - 1]` 做按位或操作，得到的结果 `result(k - 1, j)` 一定不会变小。并且，根据按位或操作的性质，如果把 `result(k, j)` 和 `result(k - 1, j)` 都用二进制表示，那么后者将前者二进制表示中的若干个 `0` 变成了 `1`。

由于数组 `A` 中都是小于 `10^9` 的正整数，它们的二进制表示最多只有 `32` 位。因此从 `result(j, j)` 开始到 `result(1, j)` 结束，最多只会有 `32` 个 `0` 变成了 `1`，也就是说，`result(j, j), result(j - 1, j), result(j - 2), j, ..., result(1, j)` 中最多只有 `32` 个不同的数。这样我们就可以维护一个集合，存储所有以 `j` 为结尾的 `result` 值。当结尾从 `j` 枚举到 `j + 1` 时，我们将集合中的每个数对 `A[j + 1]` 做按位或操作，得到的新的 `result` 值也不会超过 `32` 个。

**算法**

我们用一个集合 `cur` 存储以 `j` 为结尾的 `result` 值，即所有满足 `i <= j` 的 `A[i] | ... | A[j]` 的值。集合的大小不会超过 `32`。

```Java [sol1]
class Solution {
    public int subarrayBitwiseORs(int[] A) {
        Set<Integer> ans = new HashSet();
        Set<Integer> cur = new HashSet();
        cur.add(0);
        for (int x: A) {
            Set<Integer> cur2 = new HashSet();
            for (int y: cur)
                cur2.add(x | y);
            cur2.add(x);
            cur = cur2;
            ans.addAll(cur);
        }

        return ans.size();
    }
}
```

```Python [sol1]
class Solution(object):
    def subarrayBitwiseORs(self, A):
        ans = set()
        cur = {0}
        for x in A:
            cur = {x | y for y in cur} | {x}
            ans |= cur
        return len(ans)
```

**复杂度分析**

* 时间复杂度：![O(N\logW) ](./p__O_N_log_W__.png) ，其中 *N* 是数组 `A` 的长度，*W* 是 `A` 中最大的数。

* 空间复杂度：![O(N\logW) ](./p__O_N_log_W__.png) 。在给出的代码中用集合 `ans` 存放了所有答案，会使用 ![O(N\logW) ](./p__O_N_log_W__.png)  的空间。但这题只要返回答案的数量，因此可以只用一个变量对集合 `cur` 的大小进行累加，这样空间复杂度可以降低为 ![O(\logW) ](./p__O_log_W__.png) 。