## perface

如果你觉得我的题解尚可，希望多多点赞。

## 思路

思路比较简单，对于B中每一个数，我们都只需要在A中找到恰好比他大的数即可。

这样我们就只需把A B分别排序，然后按上面说的一个个数记录就行。

但由于要保持原来数组B的顺序，那我们只需要排序B的时候，记录每一个数原来的位置，一个个映射回去就好

## code

```java
class Solution {
    public int[] advantageCount(int[] A, int[] B) {
        // 用来记录排序后的数对应的之前的位置
        Pair<Integer, Integer>[] BPair = new Pair[B.length];
        for(int i = 0; i < B.length; i++) {
            // 记录位置
            BPair[i] = new Pair(i, B[i]);
        }
        // 排序的A数组
        int[] Ac = A.clone();
        Arrays.sort(Ac);
        // 排序，用java的函数式写法排序方法
        Arrays.sort(BPair, (a,b) -> a.getValue() - b.getValue());

        int[] res = new int[A.length];

        // 双指针，指向两者起点
        int i = 0, j = 0;
        while(i < A.length) {
            // 如果i指向的刚好大于j指向的，此时取出j对应原来的位置BPair[j].getKey()，把结果记录
            if(Ac[i] > BPair[j].getValue()){
                res[BPair[j].getKey()] = Ac[i];
                i++;
                j++;
            } else {
                // 如果i指向的小于等于j指向的，那就把i记录在BPair后面，
                // 后面已经记录的位置通过(A.length-1) - (i-j)来确定
                res[BPair[(A.length-1) - (i-j)].getKey()] = Ac[i];
                i++;
            }
        }
        return res;
    }
}
```