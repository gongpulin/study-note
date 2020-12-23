package com.gpl.datastructure.unionFind;

/**
 * @author gongpulin
 * date 2020-12-22
 */
public class UnionFind_easyunion {
    private int[] parent;
    private int size;
    public UnionFind_easyunion() {
        this.size = size;
        this.parent = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }
    }

    public int find(int element) {
        while (element != parent[element]) {
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
        parent[firstUnion] = secondUnion;
    }



}
