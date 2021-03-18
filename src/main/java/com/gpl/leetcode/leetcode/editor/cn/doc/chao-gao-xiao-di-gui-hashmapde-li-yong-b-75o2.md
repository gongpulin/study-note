方法一：(运行时间在5ms以内，可以超过85%以上的人，空间上80%)
```
    private int maxcount=1;//记录子树元素的最大重复个数
    public int[] findFrequentTreeSum(TreeNode root) {
        Map<Integer,Integer> hash=new HashMap<>();//记录子树元素和，以及出现的频数
        getSumAndCount(root,hash);
        int l=0;
        Set Keys=hash.keySet();
        List<Integer> list=new ArrayList<>();
        for(Object k:Keys){         //遍历hashmap
            if(hash.get(k)==maxcount) list.add((int)k);}
        int[] ans=new int[list.size()];
        while (l<list.size()) ans[l]=list.get(l++);
        return  ans;
    }
    private int getSumAndCount(TreeNode root, Map<Integer, Integer> map) {
        if(root==null) return 0;
        int l_sum=getSumAndCount(root.left,map);//求当前节点的左子树的"子树元素和"
        int r_sum=getSumAndCount(root.right,map);//求当前节点的右子树的"子树元素和"
        int ret=root.val+l_sum+r_sum;//该节点的子树元素和（也是返回值）
        if(map.containsKey(ret)){//如果hashmap中包含这个"子树元素和"的值
            int temp=map.get(ret)+1;
            map.put(ret,temp);//更新hashmap中的键值对
            if(maxcount<temp) maxcount=temp;//比较并更新“子树数元素和”的最大重复个数
        }
        else map.put(ret,1);//如果不存在，直接存入即可
        return ret;
    }
```
方法二：基本思路差不多，只不过在运行时间上要差一些（13ms）
```
public int[] findFrequentTreeSum(TreeNode root) {
        TreeMap<Integer,Integer> map=new TreeMap<>();
        List<Integer> nodesum=new ArrayList<>();
        getsum(root,map,nodesum);
        int len=map.size();
        int maxcount=1;
        for (int i = 0; i <len; i++) {
            if(maxcount<map.get(nodesum.get(i))) maxcount=map.get(nodesum.get(i));
        }
        for (int i = 0; i < len; i++) {
            if(maxcount!=map.get(nodesum.get(i))){
                map.remove(nodesum.get(i));
            }
        }
        int[] ans=new int[map.size()];
        int i=0;
        while (!map.isEmpty()){
            ans[i++]=map.firstKey();
            map.remove(map.firstKey());
        }
        return ans;
    }
    private int getsum(TreeNode root,Map<Integer,Integer> treemap,List<Integer> l) {
        if(root==null) return 0;
        int l_sum=getsum(root.left,treemap,l);
        int r_sum=getsum(root.right,treemap,l);
        int ret=root.val+l_sum+r_sum;
        if(treemap.containsKey(ret)) treemap.put(ret,treemap.get(ret)+1);
        else{
            treemap.put(ret,1);
            l.add(ret);
        }
        return ret;
    }
```
