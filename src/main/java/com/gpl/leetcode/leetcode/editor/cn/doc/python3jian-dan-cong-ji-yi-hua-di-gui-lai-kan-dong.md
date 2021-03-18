### 解题思路
- 最直接的方式是DFS递归解决，只要出界，那就是有一种结果，*return 1*，否则就递归求解。但是直接递归，存在大量的重复计算，比如考虑移动*k*步和移动*k-1*步的时候，就可以有1步选择不同的位置出界（其实也可以选更多不同的步数）。如果要采用递归的方式，应当考虑采用记忆化的方法，python提供了 `@lru_cache(None)` 装饰器，可以实现备忘的功能，加上 `@lru_cache(None)` 的代码和执行结果如下。

- 还有一种思考方式是使用动态规划，既然写出了递归，那么可以根据递归来改写成动态规划。

如果 *N == 0*, 那么始终在界内，直接返回，没有任何方法可以出界。如果 *N > 0*,那么扩展网格，扩展为 *(m+2)*(n+2)* 的网格，网格的所有边线全部是出界位置，因此在所有边线上都是 *dp[k][x][y] == 1*, 

```python3
                                if i < 0 or i >= m or j < 0 or j >= n: 
                                    return 1
```

之后如果 k == 0，也就是不进行移动的情况下，方案数是不会改变的，跳过就可以。
最后
*dp[k][x][y] = (dp[k][x][y] + dp[k-1][x-1][y] + dp[k-1][x+1][y] + dp[k-1][x][y-1] + dp[k-1][x][y+1]) % 1000000007*
转移方程对应递归调用 

```python3
                        for di, dj in [(i-1,j),(i+1,j),(i,j-1),(i,j+1)]:
                            count=(count + self.findPaths(m, n, N-1, di, dj)) % 1000000007
```

这样就把递归深搜改写成了动态规划。
注意这时由于扩展了网格，最后返回结果的时候，需要返回 *[i+1][j+1]* 的位置，*N* 步则依旧不变。代码和执行结果如下。

### 代码

记忆化递归
```python3
class Solution:
    @lru_cache(None)  #通过修饰器实现记忆化
    def findPaths(self, m: int, n: int, N: int, i: int, j: int) -> int:
        count = 0
        if N < 0:
            return count
        if i < 0 or i >= m or j < 0 or j >= n:
            return 1
        for di, dj in [(i-1,j),(i+1,j),(i,j-1),(i,j+1)]:
            count = (count + self.findPaths(m, n, N-1, di, dj)) % 1000000007
        return count
```
 [11.png](https://pic.leetcode-cn.com/1600243153-ipCYgr-11.png)

动态规划
```python3
class Solution:
    def findPaths(self, m: int, n: int, N: int, i: int, j: int) -> int:
        dp = [[[0]*(n+2) for _ in range(m+2)] for _ in range(N+1)]
        if N == 0:
            return 0
        for k in range(N+1):
            for x in range(m+2):
                for y in range(n+2):
                    if x == 0 or y == 0 or x == m + 1 or y == n + 1: #相当于递归的基线条件
                        dp[k][x][y] = 1
                    else:
                        if k == 0:
                            continue
                        else:  # 相当于递归调用部分
                            dp[k][x][y] = (dp[k][x][y] + dp[k-1][x-1][y] + \
                                        dp[k-1][x+1][y] + dp[k-1][x][y-1] + \
                                        dp[k-1][x][y+1]) % 1000000007
        return dp[N][i+1][j+1]
```
 [14.png](https://pic.leetcode-cn.com/1600244158-cBuqTv-14.png)
