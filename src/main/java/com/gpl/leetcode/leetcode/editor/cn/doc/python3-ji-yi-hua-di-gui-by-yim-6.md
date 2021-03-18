### 解题思路
1. 参数定义
    - `n`：需要转换为`1`的数
    - `@lru_cache(None)`：`Python`标准库，用于缓存
    - `ans`：`n`转换为`1`的最少次数

2. 递归思路
    - 出口：当`n==1`时，无需操作，返回`0`
    - 操作：
        - 当`n`为奇数时，`n`可以变为`n+1`和`n-1`，只需递归返回`本次加减一`的`1`次加上这两个值转换为`1`次数最少的那个即可
        - 当`n`为偶数时，`n`变为`n//2`，只需递归返回`本次转换`的`1`次加上`n//2`转换为`1`的次数即可
    - 返回值：n转换为1的最少次数ans
3. 复杂度分析
    时间复杂度：O(N)
    空间复杂度：O(1)

### 代码

##### 1. 标准库
```python3
class Solution:
    def integerReplacement(self, n: int) -> int:
        @lru_cache(None)
        def dfs(n):
            if n==1:
                return 0

            ans=0
            if n&1:
                ans+=1+min(dfs(n+1),dfs(n-1))
            else:
                ans+=1+dfs(n//2)
            return ans
        
        return dfs(n)
```

##### 2. 备忘录
```python3
class Solution:
    def integerReplacement(self, n: int) -> int:
        def dfs(n):
            if n in memo:
                return memo[n]

            ans=0
            if n&1:
                ans+=1+min(dfs(n+1),dfs(n-1))
            else:
                ans+=1+dfs(n//2)
            memo[n]=ans
            return ans

        memo={1:0}
        return dfs(n)
```