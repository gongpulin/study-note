### 解题思路
我们可以从右向左建立一个数组arr用于保存该数右边最大的数的索引，如果有这个数，那么保存索引，没有就是-1
最后我们从左到右遍历arr，如果arr不为-1，那么交换原字符中索引为arr[i]与i的值。
### 代码

```java
class Solution {
    public int maximumSwap(int num) {
        if(num<10)  return num;
        char [] charArr = String.valueOf(num).toCharArray();
        int [] maxArr = new int [charArr.length];
        int max = charArr.length-1;//记录当前最大值的索引
        for(int i=charArr.length-1;i>=0;i--){
            if(charArr[i]>charArr[max]){   //如果当前值比最大值大，那么我们更新最大值，并更新max，并将arr索引设为-1,表示这个数右边没有比他大的值
                max = i;
                maxArr[i] = -1;
            }else{
                maxArr[i] = max;    //否则就将最大值的索引赋值给当前值
            }
        }
        for(int i=0;i<maxArr.length;i++){
            if(maxArr[i]!=-1&&charArr[i]!=charArr[maxArr[i]]){//上述算法中，可能会出现某个最大值在索引中又出现了，这时该处索引不为-1，需要排除这一情况，比如98368
                swap(charArr,i,maxArr[i]);
                break;
            }
        }
        return Integer.parseInt(new String(charArr));
    }
    private void swap (char[]charArr,int i,int j){
        char temp = charArr[i];
        charArr[i] = charArr[j];
        charArr[j] = temp;
    }
}
```