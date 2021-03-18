### 解题思路
此处撰写解题思路

### 代码

```java
class Solution {
    public int[] advantageCount(int[] A, int[] B) {
        //找到大于B的A的最小值，如果不存在，则返回A的最小值
        Arrays.sort(A);
        List<Integer> input = new ArrayList<>();
        for(Integer tmp : A){
            input.add(tmp);
        }
        int[] res = new int[A.length];
        for(int i=0; i<B.length; ++i){
            int index = findMinMax(input, B[i]);
            res[i] = input.get(index);
            input.remove(index);
        }
        return res;
    }

    private static int findMinMax(List<Integer> input, int target){
        //二分法查找大于target的最小值
        int left = 0;
        int right = input.size()-1;
        if(input.get(left) > target){
            return left;
        }
        if(input.get(right) <= target){
            return left;
        }
        while(left < right){
            int mid = left + (right-left) / 2;
            if(input.get(mid) <= target){
                left = mid + 1;
            }else {
                right = mid;
            }
        }
        return right;
    }
}
```