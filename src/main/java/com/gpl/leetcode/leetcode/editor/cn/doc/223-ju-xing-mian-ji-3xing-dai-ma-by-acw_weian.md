    根据容斥原理
    两个矩形重叠后形成的总面积 = 矩形面积S1 + 矩形面积S2 - 两个矩形相交的面积

    只要求出两个矩形相交的面积，本题就结束了。



求之前，先看看一维线段求交集长度的方法，如下图
 [223.png](https://pic.leetcode-cn.com/1604413072-GVylvR-223.png)




```
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        long X = Math.max(0, (long)Math.min(G, C) - Math.max(A, E));
        long Y = Math.max(0, (long)Math.min(D, H) - Math.max(B, F));
        return (int)((long)(C - A) * (D - B) - X * Y + (G - E) * (H - F));
    }
```
