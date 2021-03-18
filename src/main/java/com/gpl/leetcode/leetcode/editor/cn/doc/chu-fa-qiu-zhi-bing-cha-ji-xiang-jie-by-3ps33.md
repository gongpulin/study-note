### 解题思路
本题核心思路是建立所给数组字符串的连通关系，并包含权重，故有2个思路.
1. 带权图的 dfs bfs解法
2. 带权 并查集

本解法使用 带权并查集的思路
1. 由于此处包括权重，使用路径减半和秩优化没有必要，该算的还得算
2. 故此处只使用路径压缩，将所有点指向根节点进行合并
3. 由于所给是string 不是int 无法利用传统并查集的数组index，此处使用2个哈希表
一个 unordered_map<string, string> parents 表示根节点信息
如 parents[a] = b 表示 a/b  a和b有通路
另一个 unordered_map<string, double> weights 表示权重信息
如 weights[a] = 2 表示 在 a->b 的情况下 a/b =2 
4. 由于不适用rank秩优化， 故并查集没有并，只使用查 find的过程，不过相比于
普通的find， 需要多一个权重更新的过程。比如 a->b->c的过程 
在find(a) 找c的过程中，要把本来weights[a] = a/b 更新为 weights[a] = a/c
但是在更新a 前要更新b 所以要注意顺序 先find(parent[a]) 再更新weights[a]
因为 weights[a] = weights[a] * weights[parents[a]]  
即 a->c 的权重 是 a->b 的权重 * b->c的权重 ，要保证 b->c的权重是最新的，得先 find(b)
即 find(parents[a])

详细过程见代码
想更多了解并查集的操作可以参考此题 [200.岛屿数量 并查集解法](https://leetcode-cn.com/problems/number-of-islands/solution/dao-yu-shu-liang-dfs-by-wang-xiao-shuai-ve/)，也是很好的一个应用场景

### 代码

```cpp
class Solution {
public:
    // 并查集解法
    // 哈希表1 建立 字符到其母结点的映射
    unordered_map<string, string> parents;
    // 哈希表2 建立 字符到其母结点的权值
    unordered_map<string, double> weights;

    // 查  
    // 在查的过程合并到根结点的权重
    // 以 a->b->c 为例
    // 此处使用路径压缩，所有结点指向根结点
    // 注意 此处问题：
    // string temp = find(parents[a]); 要在权重更新
    // weights[a] = weights[a] * weights[parents[a]];前运行
    // 要保证如 a->b  b->c 在后续手动连接 b->c时， b的权重还未更新
    // 要先 find(parents[a]) 也就是 find(b) 更新b的权重
    // 再更新前面的 a 的权重  否则会出错！ 
    string find(string a){
        // a->b  != a
        if(parents[a]!=a){
            // 顺序不能错  不能写成  
            // weights[a] = weights[a] * weights[parents[a]];
            // parents[a] = find(parents[a]); 会少一次b权重更新的过程 结果会出错
            // 因为 a的权重依赖于b的权重
            string temp = find(parents[a]);
            // a->c 的权重 =  a->b 的权重 *  b->c 的权重
            weights[a] = weights[a] * weights[parents[a]];
            // a->c 连接
            parents[a] = temp;
        }
        // 返回a的母结点
        return parents[a]; 
    }
    // main函数
    vector<double> calcEquation(vector<vector<string>>& equations, vector<double>& values, vector<vector<string>>& queries) 
    {
        // 最终结果数组
        vector<double> res;
        // 1. 并查集初始化 
        int n = equations.size();
        for(int i=0; i<n; i++)
        {
            string a = equations[i][0];
            string b = equations[i][1];
            // 初始化每个字符串的母结点是自己，初始化权重为1.0
            parents[a] = a;
            parents[b] = b;
            weights[a] = 1.0;
            weights[b] = 1.0;
        }
        // 2. 更新并查集权重
        for(int i=0; i<n; i++)
        {
            string a = equations[i][0];
            string b = equations[i][1];
            // 找到 a 的根结点  a->root_a  并更新指向根结点的权重
            string root_a = find(a);   

            // 建立 root_a -> b 的连接 并更新权重
            parents[root_a] = b;   
            // a->root_a->b   
            // values[i]: a->b权重（题目给的）
            // weights[a]: a->root_a 权重(已知)
            // weights[root_a]: root_a->b 权重（待求）
            weights[root_a] = values[i] / weights[a];
        }
        // 3. 计算最终结果
        int m = queries.size();
        for(int i=0; i<m; i++)
        {
            string a = queries[i][0];
            string b = queries[i][1];
            // 如果 
            // (1) 母结点哈希表不包含查询字符中的任意一个
            // (2) 两个字符最终的根节点不相同
            //（如 a->c  b->d ab不在一个集合中）（但是 a->c->d  b->d  ab就可以）
            // 此两种情况 都是 -1.0
            if(!parents.count(a) || !parents.count(b) || find(a)!=find(b))
            {
                res.push_back(-1.0);
            }else{
                // 以 a->c->d  b->d  为例 a-b的距离就是  a-d / b-d  
                // weights[a]:a-d    weights[b]: b-d
                res.push_back(weights[a]/weights[b]);
            }
        }
        return res;
    }
};
```