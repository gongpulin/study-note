#### 解题思路
题目要求在常数时间内获得栈中的最小值，因此不能在 `getMin()` 的时候再去计算最小值，最好应该在 `push` 或者 `pop` 的时候就已经计算好了当前栈中的最小值。

前排的众多题解中，基本都讲了「辅助栈」的概念，这是一种常见的思路，但是有没有更容易懂的方法呢？

可以用一个栈，这个栈同时保存的是每个数字 `x` 进栈的时候的**值 与 插入该值后的栈内最小值**。即每次新元素 `x` 入栈的时候保存一个元组：**（当前值 x，栈内最小值）**。

这个元组是一个整体，同时进栈和出栈。即栈顶同时有值和栈内最小值，`top()`函数是获取栈顶的**当前值**，即栈顶元组的第一个值； `getMin()` 函数是获取**栈内最小值**，即栈顶元组的第二个值；`pop()` 函数时删除栈顶的元组。

每次新元素入栈时，要求新的栈内最小值：比较当前新插入元素 x 和 当前栈内最小值（即栈顶元组的第二个值）的大小。

1. 新元素入栈：当栈为空，保存元组 `(x, x)`；当栈不空，保存元组 `(x, min(此前栈内最小值， x)))`
2. 出栈：删除栈顶的元组。

#### 代码
```Python []
class MinStack(object):

    def __init__(self):
        """
        initialize your data structure here.
        """
        self.stack = []
        

    def push(self, x):
        """
        :type x: int
        :rtype: void
        """
        if not self.stack:
            self.stack.append((x, x))
        else:
            self.stack.append((x, min(x, self.stack[-1][1])))
        

    def pop(self):
        """
        :rtype: void
        """
        self.stack.pop()
        

    def top(self):
        """
        :rtype: int
        """
        return self.stack[-1][0]
        

    def getMin(self):
        """
        :rtype: int
        """
        return self.stack[-1][1]


# Your MinStack object will be instantiated and called as such:
# obj = MinStack()
# obj.push(x)
# obj.pop()
# param_3 = obj.top()
# param_4 = obj.getMin()
```

```C++ []
class MinStack {
public:
    /** initialize your data structure here. */
    MinStack() {
    }
    
    void push(int x) {
        if (st.size() == 0) {
            st.push({x, x});
        } else {
            st.push({x, min(x, st.top().second)});
        }
    }
    
    void pop() {
        st.pop();
    }
    
    int top() {
        return st.top().first;
    }
    
    int getMin() {
        return st.top().second;
    }
private:
    stack<pair<int, int>> st;
};

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack* obj = new MinStack();
 * obj->push(x);
 * obj->pop();
 * int param_3 = obj->top();
 * int param_4 = obj->getMin();
 */
```

