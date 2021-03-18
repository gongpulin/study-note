#### 方法一：暴力法[超过时间限制]
每次调用 sumrange 时，我们都使用for循环将索引 *i* 到 *j* 之间的每个元素相加。 

```Java []
private int[] data;

public NumArray(int[] nums) {
    data = nums;
}

public int sumRange(int i, int j) {
    int sum = 0;
    for (int k = i; k <= j; k++) {
        sum += data[k];
    }
    return sum;
}
```
**复杂度分析**

* 时间复杂度：每次查询的时间 *O(n)*，每个 sumrange 查询需要 *O(n)* 时间。
* 空间复杂度：*O(1)*，请注意，`data` 是对 `nums` 的引用，不是它的副本。


####  方法二：缓存
假设 sumrange 被调用 1000次，其参数完全相同。我们怎么能加快速度？
我们可以用额外的空间换取速度。通过预先计算所有的范围和可能性并将其结果存储在哈希表中，我们可以将查询加速到常量时间。 

```Java []
private Map<Pair<Integer, Integer>, Integer> map = new HashMap<>();

public NumArray(int[] nums) {
    for (int i = 0; i < nums.length; i++) {
        int sum = 0;
        for (int j = i; j < nums.length; j++) {
            sum += nums[j];
            map.put(Pair.create(i, j), sum);
        }
    }
}

public int sumRange(int i, int j) {
    return map.get(Pair.create(i, j));
}
```

**复杂度分析**

* 时间复杂度：每次查询的时间 *O(1)*，*O(n^2)* 时间用来预计算。在构造函数中完成的预计算需要 *O(n^2)* 时间。每个 sumrange 查询的时间复杂性是 *O(1)* 因为哈希表的查找操作是常量时间。 
* 空间复杂度：*O(n^2)*，所需的额外空间为 *O(n^2)* 因为 *i* 和 *j* 都有 *n* 个候选空间。


###  方法三：缓存
- 上面的方法需要很大的空间，我们可以优化它吗？             
- 假设我们预先计算了从数字 *0* 到 *k* 的累积和。我们可以用这个信息得出 *sum(i，j)* 吗？                 
- 让我们将 *sum[k]* 定义为 ![nums\[0\cdotsk-1\] ](./p__nums_0cdots_k-1__.png)  的累积和（包括这两个值）：              

![sum\[k\]=\left\{\begin{array}{rl}\sum_{i=0}^{k-1}nums\[i\]&,k>0\\0&,k=0\end{array}\right. ](./p___sum_k__=_left{_begin{array}{rl}_sum_{i=0}^{k-1}nums_i__&_,_k___0__0_&,_k_=_0_end{array}_right.__.png) 

- 现在，我们可以计算 sumrange 如下：              

*       sumrange（i，j）=sum[j+1]-sum[i]      *    

```Java []
private int[] sum;

public NumArray(int[] nums) {
    sum = new int[nums.length + 1];
    for (int i = 0; i < nums.length; i++) {
        sum[i + 1] = sum[i] + nums[i];
    }
}

public int sumRange(int i, int j) {
    return sum[j + 1] - sum[i];
}
```
- 注意，在上面的代码中，我们插入了一个虚拟 0 作为 sum 数组中的第一个元素。这个技巧可以避免在 sumrange 函数中进行额外的条件检查。 

**复杂度分析**

* 时间复杂度：每次查询的时间 *O(1)*，*O(N)* 预计算时间。由于累积和被缓存，每个sumrange查询都可以用 *O(1)* 时间计算。 
* 空间复杂度：*O(n)*.