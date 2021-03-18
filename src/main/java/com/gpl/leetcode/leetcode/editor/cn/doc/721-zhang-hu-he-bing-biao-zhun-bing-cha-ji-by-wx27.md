### 解题思路
此处撰写解题思路

### 代码

```java
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
}
```