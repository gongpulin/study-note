package com.gpl.leetcode.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gongpulin
 * date 2020-09-03
 */
public class BacktrackTest {
    public static void main(String[] args) {
        BacktrackTest b = new BacktrackTest();
        List<List<Integer>> ans = new ArrayList<>();
//        boolean[] used = new boolean[3];
        b.backtrack(ans, new ArrayList<>(), 3, 1);
    }
    private void backtrack(List<List<Integer>> ans, List<Integer> path, int n, int start) {
        System.out.println(path.toString());

        for (int i = start; i <= n; i++) {
            path.add(i);
            backtrack(ans, path, n, i+1);
            path.remove(path.size()-1);
        }
    }
}
