package com.gpl.datastructure.unionFind;

/**
 * @author gongpulin
 * date 2020-12-22
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://blog.csdn.net/Jocker_coding/article/details/85985474
 */
public class UnionFind_map {
    class Node {
        int val;
        public Node(int val) {
            this.val = val;
        }
    }
    private Map<Node, Node> parents;
    private Map<Node, Integer> values;
    public UnionFind_map() {
        parents = new HashMap();
        values = new HashMap();
    }
    public void initUnionFind(List<Node> nodes) {
        parents.clear();
        values.clear();
        for (Node node : nodes) {
            parents.put(node, node);
            values.put(node, 1);
        }
    }
    public Node find(Node element) {
        Node node = element;
        while (node != parents.get(node)) {
            node = parents.get(node);
        }
        parents.put(element, node);
        return node;
    }
    public void unionElements(Node firstNode, Node secondNode) {
        Node firstUnion = find(firstNode);
        Node secondUnion = find(secondNode);
        if (firstUnion == secondUnion) {
            return;
        }
        int total = values.get(firstUnion) + values.get(secondUnion);
        if (values.get(firstUnion) < values.get(secondUnion)) {
            parents.put(firstUnion, secondUnion);
            values.put(secondUnion, total);
        } else {
            parents.put(secondUnion, firstUnion);
            values.put(firstUnion, total);
        }
    }
}
