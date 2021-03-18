//给定一个列表 accounts，每个元素 accounts[i] 是一个字符串列表，其中第一个元素 accounts[i][0] 是 名称 (name)，其
//余元素是 emails 表示该账户的邮箱地址。
//
// 现在，我们想合并这些账户。如果两个账户都有一些共同的邮箱地址，则两个账户必定属于同一个人。请注意，即使两个账户具有相同的名称，它们也可能属于不同的人，因为
//人们可能具有相同的名称。一个人最初可以拥有任意数量的账户，但其所有账户都具有相同的名称。
//
// 合并账户后，按以下格式返回账户：每个账户的第一个元素是名称，其余元素是按顺序排列的邮箱地址。账户本身可以以任意顺序返回。
//
//
//
// 示例 1：
//
// 
//输入：
//accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnn
//ybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Ma
//ry", "mary@mail.com"]]
//输出：
//[["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  
//["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
//解释：
//第一个和第三个 John 是同一个人，因为他们有共同的邮箱地址 "johnsmith@mail.com"。 
//第二个 John 和 Mary 是不同的人，因为他们的邮箱地址没有被其他帐户使用。
//可以以任何顺序返回这些列表，例如答案 [['Mary'，'mary@mail.com']，['John'，'johnnybravo@mail.com']，
//['John'，'john00@mail.com'，'john_newyork@mail.com'，'johnsmith@mail.com']] 也是正确的
//。
// 
//
// 
//
// 提示： 
//
// 
// accounts的长度将在[1，1000]的范围内。 
// accounts[i]的长度将在[1，10]的范围内。 
// accounts[i][j]的长度将在[1，30]的范围内。 
// 
// Related Topics 深度优先搜索 并查集


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.*;

//Java：账户合并
public class P721AccountsMerge{
    public static void main(String[] args) {
        Solution solution = new P721AccountsMerge().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        public List<List<String>> accountsMerge(List<List<String>> accounts) {
            HashMap<String, Integer> email = new HashMap<>();
            int n = accounts.size();
            UF uf = new UF(n);
            for (int i = 0; i < n; i++) {
                List<String> acc = accounts.get(i);
                for (int j = 1; j < acc.size(); j++) {
                    String e = acc.get(j);
                    if (email.containsKey(e)) {
                        int f = email.get(e);
                        uf.union(f, i);
                    }
                    email.put(e, i);
                }
            }
            HashMap<Integer, TreeSet<String>> map = new HashMap<>();
            for (int i = 0; i < n; i++) {
                int idx = uf.find(i);
                List<String> account = accounts.get(i);
                List<String> emails = account.subList(1,account.size());
                if (map.containsKey(idx)) {
                    map.get(idx).addAll(emails);
                } else {
                    map.put(idx, new TreeSet<>(emails));
                }
            }
            List<List<String>> ans = new ArrayList<>();
            for(int key : map.keySet()) {
                TreeSet<String> val = map.get(key);
                List<String> tmp = new ArrayList<>();
                String name = accounts.get(key).get(0);
                tmp.add(name);
                tmp.addAll(val);
                ans.add(tmp);
            }
            return ans;
        }




        class UF {
            private int count; // 连通分量
            private int[] parent;  // 父节点
            private int[] size;  // 树的节点数量
            private UF(int n) {
                this.count = n;
                parent = new int[n];
                size = new int[n];
                for (int i = 0; i < n; i++) {
                    parent[i] = i;
                    size[i] = 1;
                }
            }
            private void union(int p, int q) {
                int rootP = find(p);
                int rootQ = find(q);
                if (rootP == rootQ) return ;
                if (size[rootP] > size[rootQ]) {
                    parent[rootQ] = rootP;
                    size[rootP] += size[rootQ];
                } else {
                    parent[rootP] = rootQ;
                    size[rootQ] += size[rootP];
                }
                count--;
            }
            public boolean connected(int p, int q) {
                int rootP = find(p);
                int rootQ = find(q);
                return rootP == rootQ;
            }
            private int find(int x) {
                while (parent[x] != x) {
                    parent[x] = parent[parent[x]];
                    x = parent[x];
                }
                return x;
            }

            public int count() {
                return count;
            }
        }



















        class UnionFind {
            private Map<String, String> parents; //邮箱
            private Map<String, HashSet<Integer>> values;  //key : 邮箱，  value:account
            public UnionFind() {
                parents = new HashMap();
                values = new HashMap();
            }
            public void initUnionFind(List<List<String>> accounts) {
                for (int i = 0; i < accounts.size(); i++) {
                    List<String> account = accounts.get(i);
//                for (List<String> account : accounts) {
                    int len = account.size();
                    if (len < 2) {
                        continue;
                    }
                    String name = account.get(0);
                    String firstEmail = account.get(1);
                    parents.put(firstEmail, firstEmail);
                    values.putIfAbsent(firstEmail, new HashSet<>()).add(i);
//                    if (len == 2) {
//                        parents.put(firstEmail, firstEmail);
//                    }

                    for (int j = 2; j < len; j++) {
                        unionElements(firstEmail, account.get(j));
                    }
                }
            }
            public String find(String email) {
                while (email.equals(parents.get(email))) {
                    email = parents.get(email);
                }
                return email;
            }
            public boolean isConnected(String firstElement, String secondElement) {
                return find(firstElement).equals(secondElement);
            }
            public void unionElements(String firstElement, String secondElement) {
                String firstUnion = find(firstElement);
                String secondUnion = find(secondElement);
                if (firstUnion.equals(secondUnion)) {
                    return;
                }
                parents.put(firstUnion, secondUnion);
                values.get(secondUnion).addAll(values.get(firstUnion));
            }
        }
    public List<List<String>> accountsMerge1(List<List<String>> accounts) {
        UnionFind unionFind = new UnionFind();
        unionFind.initUnionFind(accounts);
        List<List<String>> ans = new ArrayList<>();
        for (Map.Entry<String, String> entry : unionFind.parents.entrySet()) {
            String rootemail = entry.getKey();
            if (rootemail.equals(unionFind.find(rootemail))) {
                List<String> item = new ArrayList();
                Set<Integer> indexs = unionFind.values.get(rootemail);
                String name = "";
                Set<String> emails = new HashSet();
                for (int index : indexs) {
                    List<String> account = accounts.get(index);
                    name = account.get(0);
                    for (int i = 1; i < account.size(); i++) {
                        emails.add(account.get(i));
                    }
                }
                item.add(name);
                for (String email : emails) {
                    item.add(email);
                }
                ans.add(item);
            }
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}