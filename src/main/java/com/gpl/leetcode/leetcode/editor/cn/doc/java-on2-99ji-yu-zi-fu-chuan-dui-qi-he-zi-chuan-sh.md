**主要逻辑**

1. 外层 进行字符串对齐, 以字符串s为基准,字符串t进行offset的偏移，来**覆盖各种对齐的情况**。
2. 内层 在确定了字符串对齐的对齐方式之后，**计算s和t的交集的start和end**。(以s为基准)
3. 内层 同时遍历s和t的交集部分，对不一样的字符**标记为1放到diff[]数组**。
4. 内层 计算所有1之间的距离，**围绕某个1的目标子串数量 = (左侧0数量 + 1) * (右侧0数量 + 1)**。
5. 例如：010的目标子串数量为2 * 2 = 4;  10101的目标子串数量为  (1 * 2)+(2 * 2)+(2 * 1) = 8
 


**代码：**
```
    public int countSubstrings(String s, String t) {
        int res = 0;
        for (int offset = -t.length() + 1; offset <= s.length() - 1; offset++) {//遍历对其方式
            int start = Math.max(0, offset);
            int end = Math.min(s.length() - 1, offset + t.length() - 1);

            // 标记不一样字符的位置为1，其余位置为0
            int[] diff = new int[end - start + 2];
            for (int i = start; i <= end; i++) {
                if (s.charAt(i) != t.charAt(i - offset)) {
                    diff[i - start] = 1;
                }
            }
            diff[diff.length - 1] = 1;//追加一个辅助位

            // 计算1之间的距离,   001000->围绕中间1产生的子串数量等于 (2 + 1) * (3 + 1)
            int p = -1, lastRange = 0;
            for (int i = 0; i < diff.length; i++) {
                if (diff[i] == 1) {
                    int range = i - p;
                    res += (range * lastRange);
                    lastRange = range;
                    p = i;
                }
            }
        }
        return res;
    }
```
