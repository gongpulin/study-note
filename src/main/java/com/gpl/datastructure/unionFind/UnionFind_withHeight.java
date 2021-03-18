package com.gpl.datastructure.unionFind;

/**
 * @author gongpulin
 * date 2020-12-22
 */

/**
 * 路径压缩就是处理并查集中的深的结点。实现方法很简单，就是在find函数里加上一句 parent[element] = parent[parent[element]];
 * 就好了，就是让当前结点指向自己父亲的父亲，减少深度，同时还没有改变根结点的weight(非根节点的weight改变了无所谓)。
 *
 * 注：只能在基于重量的并查集上改find函数，而不能在基于高度的并查集上采用这种路径压缩。因为路径压缩后根的重量不变，但高度会变，
 * 然而高度改变后又不方便重新计算。
 */
public class UnionFind_withHeight {
    private int[] parent;
    private int[] height;
    private int size;

    public UnionFind_withHeight(int size) {
        this.size = size;
        this.parent = new int[size];
        this.height = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            height[i] = 1;
        }
    }

    public int find(int element) {
        while (element != parent[element]) {
//            parent[element] = parent[parent[element]];  //这里不能使用路径压缩
            element = parent[element];
        }
        return element;
    }

    public boolean isConnected(int firstElement, int secondElement) {
        return find(firstElement) == find(secondElement);
    }

    public void unionElements(int firstElement, int secondElement) {
        int firstUnion = find(firstElement);
        int secondUnion = find(secondElement);
        if (firstUnion == secondUnion) {
            return;
        }
        if (height[firstUnion] < height[secondUnion]) {
            parent[firstUnion] = secondUnion;
        } else if (height[firstUnion] > height[secondUnion]) {
            parent[secondUnion] = firstUnion;
        } else {
            parent[firstUnion] = secondUnion;
            height[secondUnion]++;
        }
    }
}
