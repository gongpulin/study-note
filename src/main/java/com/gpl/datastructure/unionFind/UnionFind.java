package com.gpl.datastructure.unionFind;

/**
 * @author gongpulin
 * date 2020-12-21
 */

/**
 * 数组实现并查集，元素内数字代表集合号
 * https://labuladong.gitbook.io/algo/shu-ju-jie-gou-xi-lie/2.4-shou-ba-shou-she-ji-shu-ju-jie-gou/unionfind-suan-fa-xiang-jie
 */
public class UnionFind {
    /**
     * 数组表示并查集所有元素
     */
    private int[] id;

    /**
     * 并查集元素的个数
     */
    private int size;

    public UnionFind(int size) {
        this.size = size;
        id = new int[size];
        for (int i = 0; i < size; i++) {
            id[i] = i;
        }
    }

    public int find(int element) {
        return id[element];
    }

    public boolean isConnected(int e1, int e2) {
        return find(e1) == find(e2);
    }

    public void unionElements(int e1, int e2) {
        int firstUnion = find(e1);
        int secondUnion = find(e2);
        if (firstUnion != secondUnion) {
            for (int i = 0; i < size; i++) {
                if (id[i] == firstUnion) {
                    id[i] = secondUnion;
                }
            }
        }
    }


}
