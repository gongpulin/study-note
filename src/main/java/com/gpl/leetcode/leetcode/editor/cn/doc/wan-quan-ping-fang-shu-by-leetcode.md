####  方法一：暴力枚举法 [超出时间限制]
这个问题要求我们找出由完全平方数组合成给定数字的最小个数。我们将问题重新表述成：

给定一个完全平方数列表和正整数 `n`，求出完全平方数组合成 `n` 的组合，要求组合中的解拥有完全平方数的最小个数。

注：可以重复使用列表中的完全平方数。

从上面对这个问题的叙述来看，它似乎是一个组合问题，对于这个问题，一个直观的解决方案是使用暴力枚举法，我们枚举所有可能的组合，并找到完全平方数的个数最小的一个。

我们可以用下面的公式来表述这个问题：

![\text{numSquares}(n)=\min\Big(\text{numSquares(n-k)+1}\Big)\qquad\forallk\in{\text{squarenumbers}} ](./p__text{numSquares}_n__=_min_Big_text{numSquares_n-k__+_1}Big__qquad_forall_k_in_{text{square_numbers}}_.png) 

从上面的公式中，我们可以将其转换为递归解决方案。这里有一个例子。

**算法：**

```python [solution1-Python]
class Solution(object):
    def numSquares(self, n):
        square_nums = [i**2 for i in range(1, int(math.sqrt(n))+1)]

        def minNumSquares(k):
            """ recursive solution """
            # bottom cases: find a square number
            if k in square_nums:
                return 1
            min_num = float('inf')

            # Find the minimal value among all possible solutions
            for square in square_nums:
                if k < square:
                    break
                new_num = minNumSquares(k-square) + 1
                min_num = min(min_num, new_num)
            return min_num

        return minNumSquares(n)
```

上面的解决方案可以适用于较小的正整数 `n`。然而，会发现对于中等大小的数字（例如 55），我们也会很快遇到超出时间限制的问题。

简单的说，可能会由于过度递归，产生堆栈溢出。


####  方法二：动态规划
使用暴力枚举法会超出时间限制的原因很简单，因为我们重复的计算了中间解。我们以前的公式仍然是有效的。我们只需要一个更好的方法实现这个公式。

![\text{numSquares}(n)=\min\Big(\text{numSquares(n-k)+1}\Big)\qquad\forallk\in{\text{squarenumbers}} ](./p__text{numSquares}_n__=_min_Big_text{numSquares_n-k__+_1}Big__qquad_forall_k_in_{text{square_numbers}}_.png) 

你可能注意到从公式看来，这个问题和[斐波那契数问题](https://leetcode.com/problems/fibonacci-number/)类似。和斐波那契数一样，我们由几种更有效的方法来计算解，而不是简单的递归。

解决递归中堆栈溢出的问题的一个思路就是使用动态规划（DP）技术，该技术建立在重用中间解的结果来计算终解的思想之上。

要计算 ![\text{numSquares}(n) ](./p__text{numSquares}_n__.png)  的值，首先要计算 *n* 之前的所有值，即 ![\text{numSquares}(n-k)\qquad\forall{k}\in{\text{squarenumbers}} ](./p__text{numSquares}_n-k__qquad_forall{k}_in{text{square_numbers}}_.png) 。如果我们已经在某个地方保留了数字 *n-k* 的解，那么就不需要使用递归计算。

**算法：**

基于上述所说，我么可以在以下步骤实现 DP 解决方案。
- 几乎所有的动态规划解决方案，首先会创建一个一维或多维数组 DP 来保存中间子解的值，以及通常数组最后一个值代表最终解。注意，我们创建了一个虚构的元素 `dp[0]=0` 来简化逻辑，这有助于在在余数 (`n-k`）恰好是一个完全平方数的情况下。
- 我们还需要预计算小于给定数字 `n` 的完全平方数列表（即 `square_nums`）。
- 在主要步骤中，我们从数字 `1` 循环到 `n`，计算每个数字 `i` 的解（即 `numSquares(i)`）。每次迭代中，我们将 `numSquares(i)` 的结果保存在 `dp[i]` 中。
- 在循环结束时，我们返回数组中的最后一个元素作为解决方案的结果。
- 在下图中，我们演示了如何计算与 `dp[4]` 和 `dp[5]` 相对应的 `numSquares(4)` 和 `numSquares(5)` 的结果。

 [在这里插入图片描述](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9waWMubGVldGNvZGUtY24uY29tL0ZpZ3VyZXMvMjc5LzI3OV9kcC5wbmc?x-oss-process=image/format,png)
下面是示例实现，其中 Python 解决方案花费了约 3500 ms，这比当时 50% 的提交要快。

注意：以下 Python 解决方案仅适用于 Python2。出于某种未知的原因，Python3 运行相同的代码需要更长的时间。

```python [solution2-Python]
class Solution(object):
    def numSquares(self, n):
        """
        :type n: int
        :rtype: int
        """
        square_nums = [i**2 for i in range(0, int(math.sqrt(n))+1)]
        
        dp = [float('inf')] * (n+1)
        # bottom case
        dp[0] = 0
        
        for i in range(1, n+1):
            for square in square_nums:
                if i < square:
                    break
                dp[i] = min(dp[i], dp[i-square] + 1)
        
        return dp[-1]
```

```java [solution2-Java]
class Solution {

  public int numSquares(int n) {
    int dp[] = new int[n + 1];
    Arrays.fill(dp, Integer.MAX_VALUE);
    // bottom case
    dp[0] = 0;

    // pre-calculate the square numbers.
    int max_square_index = (int) Math.sqrt(n) + 1;
    int square_nums[] = new int[max_square_index];
    for (int i = 1; i < max_square_index; ++i) {
      square_nums[i] = i * i;
    }

    for (int i = 1; i <= n; ++i) {
      for (int s = 1; s < max_square_index; ++s) {
        if (i < square_nums[s])
          break;
        dp[i] = Math.min(dp[i], dp[i - square_nums[s]] + 1);
      }
    }
    return dp[n];
  }
}
```

**复杂度分析**

* 时间复杂度：![\mathcal{O}(n\cdot\sqrt{n}) ](./p__mathcal{O}_ncdotsqrt{n}__.png) ，在主步骤中，我们有一个嵌套循环，其中外部循环是 *n* 次迭代，而内部循环最多需要 ![\sqrt{n} ](./p__sqrt{n}_.png)  迭代。
* 空间复杂度：![\mathcal{O}(n) ](./p__mathcal{O}_n__.png) ，使用了一个一维数组 dp。


####  方法三：贪心枚举
递归解决方法为我们理解问题提供了简洁直观的方法。我们仍然可以用递归解决这个问题。为了改进上述暴力枚举解决方案，我们可以在递归中加入贪心。我们可以将枚举重新格式化如下：

从一个数字到多个数字的组合开始，一旦我们找到一个可以组合成给定数字 `n` 的组合，那么我们可以说我们找到了最小的组合，因为我们贪心的从小到大的枚举组合。

为了更好的解释，我们首先定义一个名为 `is_divided_by(n, count)` 的函数，该函数返回一个布尔值，表示数字 `n` 是否可以被一个数字 `count` 组合，而不是像前面函数 `numSquares(n)` 返回组合的确切大小。
 
 ![\text{numSquares}(n)=\argmin_{\text{count}\in\[1,2,...n\]}\Big(\text{is\_divided\_by}(n,\text{count})\Big) ](./p__text{numSquares}_n__=_argmin_{text{count}_in__1,_2,_...n_}_Big_text{is_divided_by}_n,text{count}__Big__.png) 

与递归函数 `numSquare(n)` 不同，`is_divided_by(n, count)` 的递归过程可以归结为底部情况（即 `count==1`）更快。

下面是一个关于函数 `is_divided_by(n, count)` 的例子，它对 输入 `n=5` 和 `count=2` 进行了分解。

 [在这里插入图片描述](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9waWMubGVldGNvZGUtY24uY29tL0ZpZ3VyZXMvMjc5LzI3OV9ncmVlZHkucG5n?x-oss-process=image/format,png)
通过这种重新构造的技巧，我们可以显著降低堆栈溢出的风险。

**算法：**

- 首先，我们准备一个小于给定数字 `n` 的完全平方数列表（称为 `square_nums`）。
- 在主循环中，将组合的大小（称为 `count`）从 `1` 迭代到 `n`，我们检查数字 `n` 是否可以除以组合的和，即 `is_divided_by(n, count)`。
- 函数 `is_divided_by(n, count)` 可以用递归的形式实现，汝上面所说。
- 在最下面的例子中，我们有 `count==1`，我们只需检查数字 `n` 是否本身是一个完全平方数。可以在 `square_nums` 中检查，即 ![n\in\text{square\_nums} ](./p__n_in_text{square_nums}_.png) 。如果 `square_nums` 使用的是集合数据结构，我们可以获得比 `n == int(sqrt(n)) ^ 2` 更快的运行时间。

关于算法的正确性，通常情况下，我们可以用反证法来证明贪心算法。这也不例外。假设我们发现 `count=m` 可以除以 `n`，并且假设在以后的迭代中存在另一个 `count=p` 也可以除以 `n`，并且这个数的组合小于找到的数，即 `p<m`。如果给定迭代的顺序，`count = p` 会在 `count=m` 之前被发现，因此，该算法总是能够找到组合的最小大小。

下面是一些示例实现。Python 解决方案需要大约 70ms，这比当时大约 90% 的提交要快。

```python [solution3-Python]
class Solution:
    def numSquares(self, n):
        
        def is_divided_by(n, count):
            """
                return: true if "n" can be decomposed into "count" number of perfect square numbers.
                e.g. n=12, count=3:  true.
                     n=12, count=2:  false
            """
            if count == 1:
                return n in square_nums
            
            for k in square_nums:
                if is_divided_by(n - k, count - 1):
                    return True
            return False

        square_nums = set([i * i for i in range(1, int(n**0.5)+1)])
    
        for count in range(1, n+1):
            if is_divided_by(n, count):
                return count
```

```java [solution3-Java]
class Solution {
  Set<Integer> square_nums = new HashSet<Integer>();

  protected boolean is_divided_by(int n, int count) {
    if (count == 1) {
      return square_nums.contains(n);
    }

    for (Integer square : square_nums) {
      if (is_divided_by(n - square, count - 1)) {
        return true;
      }
    }
    return false;
  }

  public int numSquares(int n) {
    this.square_nums.clear();

    for (int i = 1; i * i <= n; ++i) {
      this.square_nums.add(i * i);
    }

    int count = 1;
    for (; count <= n; ++count) {
      if (is_divided_by(n, count))
        return count;
    }
    return count;
  }
}
```

**复杂度分析**

* 时间复杂度：![\mathcal{O}(\frac{\sqrt{n}^{h+1}-1}{\sqrt{n}-1})=\mathcal{O}(n^{\frac{h}{2}}) ](./p__mathcal{O}__frac{sqrt{n}^{h+1}_-_1}{sqrt{n}_-_1}___=_mathcal{O}_n^{frac{h}{2}}__.png) ，其中 `h` 是可能发生的最大递归次数。你可能会注意到，上面的公式实际上类似于计算完整 N 元数种结点数的公式。事实上，算法种的递归调用轨迹形成一个 N 元树，其中 *N* 是 `square_nums` 种的完全平方数个数。即，在最坏的情况下，我们可能要遍历整棵树才能找到最终解。
* 空间复杂度：![\mathcal{O}(\sqrt{n}) ](./p__mathcal{O}_sqrt{n}__.png) ，我们存储了一个列表 `square_nums`，我们还需要额外的空间用于递归调用堆栈。但正如我们所了解的那样，调用轨迹的大小不会超过 `4`。


####  方法四：贪心 + BFS（广度优先搜索）
正如上述贪心算法的复杂性分析种提到的，调用堆栈的轨迹形成一颗 N 元树，其中每个结点代表 `is_divided_by(n, count)` 函数的调用。基于上述想法，我们可以把原来的问题重新表述如下：

给定一个 N 元树，其中每个节点表示数字 n 的余数减去一个完全平方数的组合，我们的任务是在树中找到一个节点，该节点满足两个条件：

(1) 节点的值（即余数）也是一个完全平方数。
(2) 在满足条件（1）的所有节点中，节点和根之间的距离应该最小。

下面是这棵树的样子。

 [在这里插入图片描述](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9waWMubGVldGNvZGUtY24uY29tL0ZpZ3VyZXMvMjc5LzI3OV9ncmVlZHlfdHJlZS5wbmc?x-oss-process=image/format,png)

在前面的方法3中，由于我们执行调用的贪心策略，我们实际上是从上到下逐层构造 N 元树。我们以 BFS（广度优先搜索）的方式遍历它。在 N 元树的每一级，我们都在枚举相同大小的组合。

遍历的顺序是 BFS，而不是 DFS（深度优先搜索），这是因为在用尽固定数量的完全平方数分解数字 n 的所有可能性之前，我们不会探索任何需要更多元素的潜在组合。

**算法：**

- 首先，我们准备小于给定数字 `n` 的完全平方数列表（即 `square_nums`）。
- 然后创建 `queue` 遍历，该变量将保存所有剩余项在每个级别的枚举。
- 在主循环中，我们迭代 `queue` 变量。在每次迭代中，我们检查余数是否是一个完全平方数。如果余数不是一个完全平方数，就用其中一个完全平方数减去它，得到一个新余数，然后将新余数添加到 `next_queue` 中，以进行下一级的迭代。一旦遇到一个完全平方数的余数，我们就会跳出循环，这也意味着我们找到了解。

注意：在典型的 BFS 算法中，`queue` 变量通常是数组或列表类型。但是，这里我们使用 `set` 类型，以消除同一级别中的剩余项的冗余。事实证明，这个小技巧甚至可以增加 5 倍的运行加速。

在下图中，我们以 `numSquares(7)` 为例说明队列的布局。

 [在这里插入图片描述](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9waWMubGVldGNvZGUtY24uY29tL0ZpZ3VyZXMvMjc5LzI3OV9ncmVlZHlfYmZzLnBuZw?x-oss-process=image/format,png)

```python [solution4-Python]
class Solution:
    def numSquares(self, n):

        # list of square numbers that are less than `n`
        square_nums = [i * i for i in range(1, int(n**0.5)+1)]
    
        level = 0
        queue = {n}
        while queue:
            level += 1
            #! Important: use set() instead of list() to eliminate the redundancy,
            # which would even provide a 5-times speedup, 200ms vs. 1000ms.
            next_queue = set()
            # construct the queue for the next level
            for remainder in queue:
                for square_num in square_nums:    
                    if remainder == square_num:
                        return level  # find the node!
                    elif remainder < square_num:
                        break
                    else:
                        next_queue.add(remainder - square_num)
            queue = next_queue
        return level
```

```java [solution4-Java]
class Solution {
  public int numSquares(int n) {

    ArrayList<Integer> square_nums = new ArrayList<Integer>();
    for (int i = 1; i * i <= n; ++i) {
      square_nums.add(i * i);
    }

    Set<Integer> queue = new HashSet<Integer>();
    queue.add(n);

    int level = 0;
    while (queue.size() > 0) {
      level += 1;
      Set<Integer> next_queue = new HashSet<Integer>();

      for (Integer remainder : queue) {
        for (Integer square : square_nums) {
          if (remainder.equals(square)) {
            return level;
          } else if (remainder < square) {
            break;
          } else {
            next_queue.add(remainder - square);
          }
        }
      }
      queue = next_queue;
    }
    return level;
  }
}
```

**复杂度分析**

* 时间复杂度：![\mathcal{O}(\frac{\sqrt{n}^{h+1}-1}{\sqrt{n}-1})=\mathcal{O}(n^{\frac{h}{2}}) ](./p__mathcal{O}__frac{sqrt{n}^{h+1}_-_1}{sqrt{n}_-_1}___=_mathcal{O}_n^{frac{h}{2}}__.png) ，其中 `h` 是 N 元树的高度。在前面的方法三我们可以看到详细解释。
* 空间复杂度：![\mathcal{O}\Big((\sqrt{n})^h\Big) ](./p__mathcal{O}Big__sqrt{n}_^hBig__.png) ，这也是在 `h` 级可以出现的最大节点数。可以看到，虽然我们保留了一个完全平方数列表，但是空间的主要消耗是队列变量，它跟踪给定 N 元树级别上要访问的剩余节点。


####  方法五：数学运算
随着时间的推移，已经提出并证明的数学定理可以解决这个问题。在这一节中，我们将把这个问题分成几个例子。

1770 年，[Joseph Louis Lagrange](https://en.wikipedia.org/wiki/Lagrange%27s_four-square_theorem)证明了一个定理，称为四平方和定理，也称为 Bachet 猜想，它指出每个自然数都可以表示为四个整数平方和：

*p=a_{0}^{2}+a_{1}^{2}+a_{2}^{2}+a_{3}^{2}*

其中 *a_{0},a_{1},a_{2},a_{3}* 表示整数。

例如，3，31 可以被表示为四平方和如下：
![3=1^{2}+1^{2}+1^{2}+0^{2}\qquad31=5^{2}+2^{2}+1^{2}+1^{2} ](./p__3=1^{2}+1^{2}+1^{2}+0^{2}_qquad_31=5^{2}+2^{2}+1^{2}+1^{2}_.png) 

情况 1：拉格朗日四平方定理设置了问题结果的上界，即如果数 `n` 不能分解为较少的完全平方数，则至少可以分解为 4个完全平方数之和，即 ![\text{numSquares}(n)\le4 ](./p__text{numSquares}_n__le_4_.png) 。

正如我们在上面的例子中可能注意到的，数字 0 也被认为是一个完全平方数，因此我们可以认为数字 3 可以分解为 3 个或  4 个完全平方数。

然而，拉格朗日四平方定理并没有直接告诉我们用最小平方数来分解自然数。

后来，在 1797 年，[Adrien Marie Legendre](https://en.wikipedia.org/wiki/Adrien-Marie_Legendre)用他的三平方定理完成了四平方定理，证明了正整数可以表示为三个平方和的一个特殊条件：

![n\ne4^{k}(8m+7)\iffn=a_{0}^{2}+a_{1}^{2}+a_{2}^{2} ](./p__n_ne_4^{k}_8m+7__iff_n_=_a_{0}^{2}+a_{1}^{2}+a_{2}^{2}_.png) 

其中 *k* 和 *m* 是整数。

情况 2：与四平方定理不同，Adrien-Marie-Legendre 的三平方定理给了我们一个充分必要的条件来检验这个数是否只能分解成 4 个平方。

从三平方定理看我们在第 2 种情况下得出的结论可能很难。让我们详细说明一下推论过程。

首先，三平方定理告诉我们，如果 `n` 的形式是 *n = 4^{k}(8m+7)*，那么 `n` 不能分解为 3 个平方的和。此外，我们还可以断言 `n` 不能分解为两个平方和，数本身也不是完全平方数。因为假设数 `n` 可以分解为 *n = a_{0}^{2}+a_{1}^{2}*，然后通过在表达式中添加平方数 0，即 *n = a_{0}^{2}+a_{1}^{2} + 0^2*，我们得到了数 `n` 可以分解为 3 个平方的结论，这与三平方定理相矛盾。因此，结合四平方定理，我们可以断言，如果这个数不满足三平方定理的条件，它只能分解成四个平方和。

如果这个数满足三平方定理的条件，则可以分解成三个完全平方数。但我们不知道的是，如果这个数可以分解成更少的完全平方数，即一个或两个完全平方数。

所以在我们把这个数视为底部情况（三平方定理）之前，还有两种情况需要检查，即：

情况 3.1：如果数字本身是一个完全平方数，这很容易检查，例如 `n == int(sqrt(n)) ^ 2`。

情况 3.2：如果这个数可以分解成两个完全平方数和。不幸的是，没有任何数学定理可以帮助我们检查这个情况。我们需要使用枚举方法。

**算法：**

可以按照上面的例子来实现解决方案。
- 首先，我们检查数字 `n` 的形式是否为 *n = 4^{k}(8m+7)*，如果是，则直接返回 4。
- 否则，我们进一步检查这个数本身是否是一个完全平方数，或者这个数是否可以分解为两个完全平方数和。
- 在底部的情况下，这个数可以分解为 3 个平方和，但我们也可以根据四平方定理，通过加零，把它分解为 4 个平方。但是我们被要求找出最小的平方数。

```python [solution5-Python]
class Solution:
    def isSquare(self, n: int) -> bool:
        sq = int(math.sqrt(n))
        return sq*sq == n

    def numSquares(self, n: int) -> int:
        # four-square and three-square theorems
        while (n & 3) == 0:
            n >>= 2      # reducing the 4^k factor from number
        if (n & 7) == 7: # mod 8
            return 4

        if self.isSquare(n):
            return 1
        # check if the number can be decomposed into sum of two squares
        for i in range(1, int(n**(0.5)) + 1):
            if self.isSquare(n - i*i):
                return 2
        # bottom case from the three-square theorem
        return 3
```

```java [solution5-Java]
class Solution {

  protected boolean isSquare(int n) {
    int sq = (int) Math.sqrt(n);
    return n == sq * sq;
  }

  public int numSquares(int n) {
    // four-square and three-square theorems.
    while (n % 4 == 0)
      n /= 4;
    if (n % 8 == 7)
      return 4;

    if (this.isSquare(n))
      return 1;
    // enumeration to check if the number can be decomposed into sum of two squares.
    for (int i = 1; i * i <= n; ++i) {
      if (this.isSquare(n - i * i))
        return 2;
    }
    // bottom case of three-square theorem.
    return 3;
  }
}
```

**复杂度分析**

* 时间复杂度：![\mathcal{O}(\sqrt{n}) ](./p__mathcal{O}_sqrt{n}__.png) ，在主循环中，我们检查数字是否可以分解为两个平方和，这需要 ![\mathcal{O}(\sqrt{n}) ](./p__mathcal{O}_sqrt{n}__.png)  个迭代。在其他情况下，我们会在常数时间内进行检查。
* 空间复杂度：![\mathcal{O}(1) ](./p__mathcal{O}_1__.png) ，该算法消耗一个常量空间。