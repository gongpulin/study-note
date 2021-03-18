### 解题思路
1.以枚举方式，分别找出两个数组的最大子序列。例如k=5:则有 （0,5）;（1,4）,（2,3）,（3,2）,（4,1）,（5,0）几种情况
2.分别将两个最大子序列进行合并。
2.将合并后的序列调用函数进行一一比较。
3.返回最大序列结果。

### 代码

```java
class Solution {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
         int []b = new int[k];
        List<Integer>Merge_s;
        List<Integer>Merge_Max = Merge(StackTrans(nums1,0),StackTrans(nums2,k));
        for (int i = 1;i < k+1;i ++){
            Merge_s = Merge(StackTrans(nums1,i),StackTrans(nums2,k-i));
            Merge_Max = CompareStack(Merge_Max,Merge_s);
        }
        for(int i = 0;i < k;i ++){
            b[i] = Merge_Max.get(i);
        }
        return b;
    }
    public List<Integer> CompareStack(List<Integer> list1,List<Integer> list2){
        if(list1.size() > list2.size())return list1;
        else if(list1.size() < list2.size())return list2;
        else{
            for(int i = 0;i < list1.size();i ++){
                if(list1.get(i) > list2.get(i)){
                    return list1;
                }else if(list1.get(i) < list2.get(i)){
                    return list2;
                }
            }
            return list1;
        }
    }
    public int list_cmp(List<Integer> list1,int index1,List<Integer> list2, int index2){
        int size1 = list1.size();
        int size2 = list2.size();
        while (index1 < size1 && index2 < size2){
            int tmp = list1.get(index1) - list2.get(index2);
            if (tmp != 0){
                return tmp;
            }
            else {
                index1 ++;
                index2 ++;
            }
        }
        return (size1 - index1) - (size2 - index2);
    }
    //合并两序列
    public List Merge(Stack<Integer> stack1,Stack<Integer> stack2){
        List<Integer> list1 = (List<Integer>) stack1.clone();
        List<Integer> list2 = (List<Integer>) stack2.clone();
        int a[] = new int[stack1.size() + stack2.size()];
        int index1 = 0,index2 = 0;
        for(int i = 0;i < stack1.size() + stack2.size();i ++){
            if(list_cmp(list1,index1,list2,index2) > 0){
                a[i] = list1.get(index1);
                index1 ++;
            }else{
                a[i] = list2.get(index2);
                index2 ++;
            }
        }
        List<Integer>list = new ArrayList<>();
        for(int i:a){
            list.add(i);
        }
        return list;
    }
    public Stack<Integer> StackTrans(int []nums,int length) {
        Stack<Integer> stack = new Stack<Integer>();
        if (length == 0) return stack;
        else {
            int pop_number = nums.length - length;
            for (int i = 0; i < nums.length; i++) {
                if (stack.size() <= length) {
                    if (stack.empty()) {
                        stack.push(nums[i]);
                    } else {
                        while (!stack.empty() && nums[i] > stack.peek() && pop_number > 0 && nums.length - i + stack.size() > length) {
                            stack.pop();
                            pop_number--;
                        }
                        if (stack.size() < length) {
                            stack.push(nums[i]);
                        }
                    }
                }
            }
            return stack;
        }
    }
}
```