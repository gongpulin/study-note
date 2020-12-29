package com.gpl.datastructure.unionFind;

/**
 * @author gongpulin
 * date 2020-12-22
 */
public class UnionFind_withWeight {
    private int[] parent;
    private int[] weight;
    private int size;

    public UnionFind_withWeight(int size) {
        this.size = size;
        this.parent = new int[size];
        this.weight = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            weight[i] = 1;
        }
    }

    public int find(int element) {
        while (element != parent[element]) {
            parent[element] = parent[parent[element]];
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
        if (weight[firstUnion] > weight[secondUnion]) {
            parent[secondUnion] = firstUnion;
            weight[firstUnion] += weight[secondUnion];
        } else {
            parent[firstUnion] = secondUnion;
            weight[secondUnion] += weight[firstUnion];
        }
    }
}
