#### 方法一：暴力枚举

**思路与算法**

枚举子串在 *s* 中的起始位置 *i*，*t* 中的起始位置 *j*。随后同时遍历两个字符串，统计对应位置不同的字符的数量：

- 如果数量为 *0*，继续遍历；
- 如果数量为 *1*，计入一次答案；
- 如果数量为 *2*，退出遍历。

**代码**

```C++ [sol1-C++]
class Solution {
public:
    int countSubstrings(string s, string t) {
        int m = s.size();
        int n = t.size();
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int diff = 0;
                for (int k = 0; i + k < m && j + k < n; ++k) {
                    diff += (s[i + k] != t[j + k]);
                    if (diff > 1) {
                        break;
                    }
                    if (diff == 1) {
                        ++ans;
                    }
                }
            }
        }
        return ans;
    }
};
```

```Python [sol1-Python3]
class Solution:
    def countSubstrings(self, s: str, t: str) -> int:
        m, n = len(s), len(t)
        ans = 0
        for i in range(m):
            for j in range(n):
                diff = 0
                k = 0
                while i + k < m and j + k < n:
                    diff += int(s[i + k] != t[j + k])
                    if diff > 1:
                        break
                    if diff == 1:
                        ans += 1
                    k += 1
        return ans
```

**复杂度分析**

- 时间复杂度：![O\big(mn\min(m,n)\big) ](./p__Obig_mn_min_m,_n_big__.png) ，其中 *m* 和 *n* 分别是字符串 *s* 和 *t* 的长度。

- 空间复杂度：*O(1)*。

#### 方法二：枚举优化

**思路与算法**

我们可以对方法一的枚举过程进行优化。为了方便「正向」思考，我们枚举子串在 *s* 中的**结束**位置 *i*，*t* 中的**结束**位置 *j*，记固定了结束位置 *(i, j)* 时，满足要求的子串数目为 *f(i, j)*。

假设我们已经知道了 *f(i, j)*，那么它可以帮我们快速得到哪些其它的 *f* 值呢？直观上来说，我们可以联想到 *f(i+1, j+1)*，这是因为：

- 如果 *s[i+1]=t[j+1]*，那么 *f(i+1,j+1)=f(i,j)*。因为每一个结束位置为 *(i,j)* 的子串，往后扩展一个位置，就是一个结束位置为 *(i+1,j+1)* 的子串，反之亦然。

但如果 ![s\[i+1\]\neqt\[j+1\] ](./p__s_i+1_neq_t_j+1__.png)  怎么办？仔细想一想，当 ![s\[i+1\]\neqt\[j+1\] ](./p__s_i+1_neq_t_j+1__.png)  时，**以 *(i+1,j+1)* 为结束位置的子串数目，等于从 *s[i]* 以及 *t[j]* 开始往左看，最长连续的完全相同的子串的长度再加上 *1***。这是因为 *s[i+1]* 和 *t[j+1]* 本身不相同，因此如果结束位置为 *(i+1,j+1)*，那么**前面的所有字符必须都完全相同**。特别地，我们也可以不取任何前面的字符，因此需要加上 *1*。

因此我们可以用 *g(i,j)* 表示**从 *s[i]* 以及 *t[j]* 开始往左看，最长连续的完全相同的子串的长度**，其实它就是 *s[0..i]* 和 *t[0..j]* 的最长公共后缀的长度。*g(i,j)* 的求解方法非常简单：

![g(i,j)=\begin{cases}g(i-1,j-1)+1,&s\[i\]=t\[j\]\\0,&s\[i\]\neqt\[j\]\end{cases} ](./p___g_i,j_=begin{cases}_g_i-1,j-1_+1,_&_s_i__=_t_j___0,_&_s_i__neq_t_j__end{cases}__.png) 

这样从 *f(i,j)* 转移到 *f(i+1,j+1)* 的时间复杂度为 *O(1)*。只要我们处理好边界条件，就可以在 *O(mn)* 的时间得到所有的 *f* 和 *g* 值，其中 *f* 值的和即为答案。

我们当然可以用二重循环加上两个二维数组 *f* 和 *g* 实现上面的算法，但是我们发现一个非常有趣的性质，即 *f* 和 *g* 都是类似 ![(i,j)\leftarrow(i-1,j-1) ](./p___i,j__leftarrow__i-1,j-1__.png)  这样的转移过程，那么我们其实根本不需要二维数组，甚至不需要数组：

- 我们每次从 *(i, 0)* 或者 *(0, j)* 这样开始计算，其上一个状态无论是 *(i-1,-1)* 还是 *(-1,j-1)* 都是答案为 *0* 的边界状态（因为结束位置在字符串之外）。这样从边界状态开始进行 ![(i,j)\leftarrow(i-1,j-1) ](./p___i,j__leftarrow__i-1,j-1__.png)  的转移即可。

这样空间复杂度就可以达到完美的 *O(1)*。

**代码**

```C++ [sol2-C++]
class Solution {
public:
    int countSubstrings(string s, string t) {
        int m = s.size();
        int n = t.size();
        int ans = 0;
        for (int delta = -m + 1; delta < n; ++delta) {
            // 我们枚举每一个边界条件 (i,0) 以及 (0,j)
            int i = 0, j = 0;
            if (delta > 0) {
                j = delta;
            }
            else {
                i = -delta;
            }
            // f(i,j) 和 g(i,j) 的初始值均为 0
            int fij = 0, gij = 0;
            for (; i < m && j < n; ++i, ++j) {
                if (s[i] == t[j]) {
                    // f(i,j) 不变，g(i,j) 加 1
                    ++gij;
                }
                else {
                    // f(i,j) 变为 g(i,j) 加 1，g(i,j) 置零
                    fij = gij + 1;
                    gij = 0;
                }
                ans += fij;
            }
        }
        return ans;
    }
};
```

```Python [sol2-Python3]
class Solution:
    def countSubstrings(self, s: str, t: str) -> int:
        m, n = len(s), len(t)
        ans = 0
        for delta in range(-m + 1, n):
            # 我们枚举每一个边界条件 (i,0) 以及 (0,j)
            i = j = 0
            if delta > 0:
                j = delta
            else:
                i = -delta
            # f(i,j) 和 g(i,j) 的初始值均为 0
            fij = gij = 0
            while i < m and j < n:
                if s[i] == t[j]:
                    # f(i,j) 不变，g(i,j) 加 1
                    gij += 1
                else:
                    # f(i,j) 变为 g(i,j) 加 1，g(i,j) 置零
                    fij = gij + 1
                    gij = 0
                ans += fij
                i += 1
                j += 1
        
        return ans
```

**复杂度分析**

- 时间复杂度：![O\big((m+n)\min(m,n)\big)=O(mn) ](./p__Obig__m+n__min_m,_n_big__=_O_mn__.png) ，其中 *m* 和 *n* 分别是字符串 *s* 和 *t* 的长度。

- 空间复杂度：*O(1)*。