#### 方法一： 模拟法

**思路**

就像你每次玩 FizzBuzz 那样，你只需要判断这个数是能被 `3` 整除？ 还是能被 `5` 整除？ 或者是都能被整除。

**算法**

1. 初始化一个空的答案列表。
2. 遍历 *1 ... N*。
3. 对于每个数，判断它能不能同时被 3 和 5 整除，如果可以就把 FizzBuzz 加入答案列表。
4. 如果不行，判断它能不能被 3 整除，如果可以，把 Fizz 加入答案列表。
5. 如果还是不行，判断它能不能被 5 整除，如果可以，把 Buzz 加入答案列表。
6. 如果以上都不行，把这个数加入答案列表。


```Java []
class Solution {
  public List<String> fizzBuzz(int n) {

    // ans list
    List<String> ans = new ArrayList<String>();

    for (int num = 1; num <= n; num++) {

      boolean divisibleBy3 = (num % 3 == 0);
      boolean divisibleBy5 = (num % 5 == 0);

      if (divisibleBy3 && divisibleBy5) {
        // Divides by both 3 and 5, add FizzBuzz
        ans.add("FizzBuzz");
      } else if (divisibleBy3) {
        // Divides by 3, add Fizz
        ans.add("Fizz");
      } else if (divisibleBy5) {
        // Divides by 5, add Buzz
        ans.add("Buzz");
      } else {
        // Not divisible by 3 or 5, add the number
        ans.add(Integer.toString(num));
      }
    }

    return ans;
  }
}
```

```Python []
class Solution:
    def fizzBuzz(self, n):
        """
        :type n: int
        :rtype: List[str]
        """
        # ans list
        ans = []

        for num in range(1,n+1):

            divisible_by_3 = (num % 3 == 0)
            divisible_by_5 = (num % 5 == 0)

            if divisible_by_3 and divisible_by_5:
                # Divides by both 3 and 5, add FizzBuzz
                ans.append("FizzBuzz")
            elif divisible_by_3:
                # Divides by 3, add Fizz
                ans.append("Fizz")
            elif divisible_by_5:
                # Divides by 5, add Buzz
                ans.append("Buzz")
            else:
                # Not divisible by 3 or 5, add the number
                ans.append(str(num))

        return ans
```

**复杂度分析**

* 时间复杂度： *O(N)*
* 空间复杂度： *O(1)*


#### 方法二： 字符串连接

**思路**

这个方法不会降低渐进复杂度，但是当 `FizzBuzz` 的规则变得更复杂的时候，这将会是个更优雅的解法。比方说，玩个 `FizzBuzzJazz` 的游戏。规则如下：
<pre>
3 ---> "Fizz" , 5 ---> "Buzz", 7 ---> "Jazz"
</pre>

如果你还是用之前的方法来解决这个问题的话，那将会有非常多的条件需要判断哦~

1. 能不能被 3 整除
2. 能不能被 5 整除
3. 能不能被 7 整除
4. 能不能同时被 3 和 5 整除
5. 能不能同时被 5 和 7 整除
6. 能不能同时被 3 和 7 整除
7. 能不能同时被 3，5，7 整除
8. 不能被 3，5，7 其中任何一个数整除

如果 `FizzBuzz` 照着这种方式变地更复杂的话，那么你要写的判断可能会让你抓狂。

**算法**

我们放弃使用之前的联合判断，取而代之依次判断是否能被给定的数整数。这道题中，就是依次判断能不能被 3 整除，能不能被 5 整除。如果能被 3 整除，就把对应的 `Fizz` 连接到答案字符串，如果能被 5 整除，就把 `Buzz` 连接到答案字符串。

举个例子，现在需要判断 15，步骤将会是下面这样的：


> 条件 1： 15 % 3 == 0, num_ans_str = "Fizz"  
> 条件 2： 15 % 5 == 0, num_ans_str += "Buzz"  
> => num_ans_str = "FizzBuzz"


对于 `FizzBuzz` 来说，只需要判断两个条件就可以了，而不需要像方法一中那样判断三个条件。

同样的，对于 `FizzBuzzJazz`，现在只需要判断三个条件就可以了。

```Java [solution-Java]
class Solution {
  public List<String> fizzBuzz(int n) {
    // ans list
    List<String> ans = new ArrayList<String>();

    for (int num = 1; num <= n; num++) {

      boolean divisibleBy3 = (num % 3 == 0);
      boolean divisibleBy5 = (num % 5 == 0);

      String numAnsStr = "";

      if (divisibleBy3) {
        // Divides by 3, add Fizz
        numAnsStr += "Fizz";
      }

      if (divisibleBy5) {
        // Divides by 5, add Buzz
        numAnsStr += "Buzz";
      }

      if (numAnsStr.equals("")) {
        // Not divisible by 3 or 5, add the number
        numAnsStr += Integer.toString(num);
      }

      // Append the current answer str to the ans list
      ans.add(numAnsStr);
    }

    return ans;
  }
}
```

```Python [solution-Python]
class Solution:
    def fizzBuzz(self, n):
        """
        :type n: int
        :rtype: List[str]
        """
        # ans list
        ans = []

        for num in range(1,n+1):

            divisible_by_3 = (num % 3 == 0)
            divisible_by_5 = (num % 5 == 0)

            num_ans_str = ""

            if divisible_by_3:
                # Divides by 3
                num_ans_str += "Fizz"
            if divisible_by_5:
                # Divides by 5
                num_ans_str += "Buzz"
            if not num_ans_str:
                # Not divisible by 3 or 5
                num_ans_str = str(num)

            # Append the current answer str to the ans list
            ans.append(num_ans_str)  

        return ans
```

**复杂度分析**

* 时间复杂度： *O(N)*
* 空间复杂度： *O(1)*
<br/>
<br/>

#### 方法三 用散列表

**思路**

这个方法是对方法二的优化。当数字和答案的映射是定好的，那么方法二用起来也还可以。但是如果你遇到一个变态的面试官，他跟你说他需要更自由的映射关系呢？

每个映射一个判断显然是不可行的，这样写出来的代码一定是丑陋不堪且难以维护的。

如果老板有这样一个需求，明天你把映射关系换掉或者删除一个映射关系吧。对于这种要求，我们只能一个个去修改判断条件的代码。

但我们实际上有个更优雅的做法，那就是把映射关系放在 `散列表` 里面。

**算法**

1. 把所有的映射关系放在散列表 `fizzBuzzHash` 中，这个散列表形如 ``{ 3: 'Fizz', 5: 'Buzz' }``。
2. 遍历 *1 ... N*。
3. 对于每个数字，遍历 `fizzBuzzHash` 中的键，检查是否能被它整除。
4. 如果这个数能被键整除，就把当前键映射的值加到到答案字符串后面去。对于散列表的每个键值对，都这样操作。
5. 最后将答案字符串加入答案列表。

> 通过这样的方式你可以对散列表`添加/删除`映射关系，同时还不需要修改太多代码。

而对于 `FizzBuzzJazz` 这个问题，散列表就可以是这样的，``{ 3: 'Fizz', 5: 'Buzz', 7: 'Jazz' }``。

```Java []
class Solution {
  public List<String> fizzBuzz(int n) {

    // ans list
    List<String> ans = new ArrayList<String>();

    // Hash map to store all fizzbuzz mappings.
    HashMap<Integer, String> fizzBizzDict =
        new HashMap<Integer, String>() {
          {
            put(3, "Fizz");
            put(5, "Buzz");
          }
        };

    for (int num = 1; num <= n; num++) {

      String numAnsStr = "";

      for (Integer key : fizzBizzDict.keySet()) {

        // If the num is divisible by key,
        // then add the corresponding string mapping to current numAnsStr
        if (num % key == 0) {
          numAnsStr += fizzBizzDict.get(key);
        }
      }

      if (numAnsStr.equals("")) {
        // Not divisible by 3 or 5, add the number
        numAnsStr += Integer.toString(num);
      }

      // Append the current answer str to the ans list
      ans.add(numAnsStr);
    }

    return ans;
  }
}
```

```Python []
class Solution:
    def fizzBuzz(self, n):
        """
        :type n: int
        :rtype: List[str]
        """
        # ans list
        ans = []

        # Dictionary to store all fizzbuzz mappings
        fizz_buzz_dict = {3 : "Fizz", 5 : "Buzz"}

        for num in range(1,n+1):

            num_ans_str = ""

            for key in fizz_buzz_dict.keys():

                # If the num is divisible by key,
                # then add the corresponding string mapping to current num_ans_str
                if num % key == 0:
                    num_ans_str += fizz_buzz_dict[key]

            if not num_ans_str:
                num_ans_str = str(num)

            # Append the current answer str to the ans list
            ans.append(num_ans_str)  

        return ans
```

**复杂度分析**

* 时间复杂度： *O(N)*
* 空间复杂度： *O(1)*