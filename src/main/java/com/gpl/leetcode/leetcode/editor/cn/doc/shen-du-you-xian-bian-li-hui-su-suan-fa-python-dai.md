思路分析：

+ 这一类搜索问题是在一个隐式的树上进行的搜索问题，即「树形问题」。解决这一类问题， **先画出递归树是十分重要的，可以帮助打开思路** ，然后看着图形把代码写出来；
+ 这个问题所求的解，是这棵树的叶子结点上的值。因此，可以使用深度优先遍历，收集 **所有** 叶子结点的值，**深度优先遍历用于搜索也叫回溯算法**；
+ 回溯算法因为有回头的过程，因此其显著特征是 **状态重置**。回溯算法的入门问题是「力扣」第 46 题：[全排列](https://leetcode-cn.com/problems/permutations/)）。

（温馨提示：下面的幻灯片中，有几页上有较多的文字，可能需要您停留一下，可以点击右下角的后退 “|◀” 或者前进 “▶|” 按钮控制幻灯片的播放。）

  [784-1.png](https://pic.leetcode-cn.com/a8944d75c527c6184f142ab303309c6613064d5b533886966a41a9c4211ae218-784-1.png)  [784-2.png](https://pic.leetcode-cn.com/0441ae7f48648d4249d63d798d2fed65c41bfd9fa5e3f63ea1903d8ed6e0ea8d-784-2.png)  [784-3.png](https://pic.leetcode-cn.com/80a21ccfa616fcc3b26451415b3c21b4f5983ddfcb5fca1bee9bee2c988a17bd-784-3.png)  [784-4.png](https://pic.leetcode-cn.com/e263a2a27f4f49ae979b5a6833d288eed22a001f24a6e08157de5835c9f739c0-784-4.png)  [784-5.png](https://pic.leetcode-cn.com/928e97c14e6ba0f61ad71db11c8e5df24f9af693f456883e2cef4a1760a8f94f-784-5.png)  [784-6.png](https://pic.leetcode-cn.com/f8c544dd2255f360ec055e01c03347ded5fb1f8c06a785eb6d45b8dde243331e-784-6.png) 

思路介绍完了，下面要介绍一个技巧，即解决字母大小写转换的问题。

**技巧**：使用异或运算转换字母大小写

这一步比较有技巧，我也是参考了别人的思路，用自己话写了出来。

我们先看一看 [ASCII 表](https://baike.baidu.com/item/ASCII/309296?fr=aladdin)，A 到 Z，Z 完了以后没有直接到 a，中间隔了 6 个字符。

 [image.png](https://pic.leetcode-cn.com/b5bda457ce665cfaae1e51cd9c7fb26167e2931748d9a069607e39c65191756b-image.png)

（中间省略）

 [image.png](https://pic.leetcode-cn.com/6d0acbb942713f7302632c6064a84e2debab6299c97726c66ac5a0497ece9140-image.png)

我们发现大写字符与其对应的小写字符的 ASCII 的差为 32，32 这个值如果敏感的话，它是 *2^5* ，在编程语言中，可以表示为 `1 << 5`。而

变换大小写这件事等价于：

+ 如果字符是小写字符，减去 32 得到大写字符；  
+ 如果字符是大写字符，加上 32 得到小写字符。

而这两者合并起来，就是给这个字符做一次不进位的加法，即异或上 `1 << 5`。

**参考代码**：

```Java []
import java.util.ArrayList;
import java.util.List;

public class Solution {

    public List<String> letterCasePermutation(String S) {
        List<String> res = new ArrayList<>();
        char[] charArray = S.toCharArray();
        dfs(charArray, 0, res);
        return res;
    }

    private void dfs(char[] charArray, int index, List<String> res) {
        if (index == charArray.length) {
            res.add(new String(charArray));
            return;
        }

        dfs(charArray, index + 1, res);
        if (Character.isLetter(charArray[index])) {
            charArray[index] ^= 1 << 5;
            dfs(charArray, index + 1, res);
        }
    }
}
```