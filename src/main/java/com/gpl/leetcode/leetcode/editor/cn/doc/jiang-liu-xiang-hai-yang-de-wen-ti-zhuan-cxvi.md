### 解题思路
此处撰写解题思路
思路：
        可以转换为海浪问题（从大海向大陆流向），问题就变简单了。
        太平洋的海浪 和 大西洋的海浪 可以交汇的节点，
        这些节点就既可以流向太平洋， 也可以流向大西洋

太平洋 ~   ~   ~   ~   ~
             ~  1   2   2   3  (5) *
             ~  3   2   3  (4) (4) *
             ~  2   4  (5)  3   1  *
             ~ (6) (7)  1   4   5  *
             ~ (5)  1   1   2   4  *
                *   *   *   *   * 大西洋
     
 陆地，数字大小代表高度，海浪是从海洋倒灌到大陆高地。
      1 2 2 3 5
      3 2 3 4 4
      2 4 5 3 1
      6 7 1 4 5
      5 1 1 2 4
     
P代表太平洋
      P P P P P
      P . . . .
      P . . . .
      P . . . .
      P . . . .
     
A代表大西洋
      . . . . A
      . . . . A
      . . . . A
      . . . . A
      A A A A A
     
太平洋的海浪，可以倒灌的区域。
      P P P P P
      P P P P P
      P P P . .
      P P . . .
      P . . . .
     
大西洋的海浪，可以倒灌的区域。
      . . . . A
      . . . A A
      . . A A A
      A A A A A
      A A A A A
     
到此，问题答案已经明晰了，
### 代码

```java
class Solution {
    private int d[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private List<List<Integer>> res;
    private int r,c;
    private boolean[][] pVisited,aVisited;
    private final static int DIRECTION =4;

    
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {

        res = new ArrayList<>();
        if (matrix==null || matrix.length ==0 || matrix[0].length==0){
            return  res;
        }
        r=matrix.length;
        c=matrix[0].length;
        pVisited = new boolean[r][c];
        aVisited = new boolean[r][c];

        for (int i = 0; i <r ; i++) {
            for (int j = 0; j <c ; j++) {

                if (!pVisited[i][j] && inPacificCoast(i,j) ){
                    //从太平洋海边，随便一个地点海浪倒灌入陆地
                    pour(matrix,i,j,matrix[i][j],pVisited);

                }
                if (!aVisited[i][j] && inAtlanticCoast(i,j)){
                    //大西洋海边随便一个点海浪倒灌入陆地
                    pour(matrix,i,j,matrix[i][j],aVisited);
                }
            }
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j <c ; j++) {
                if (pVisited[i][j] && aVisited[i][j]){
                     res.add(Arrays.asList(i,j));
                }
            }
        }

        return res;
    }

    private void pour(int[][] matrix, int x, int y, int h, boolean[][] v) {

        v[x][y] =true;
        for (int i = 0; i < DIRECTION; i++) {
            int nextX = x+d[i][0];
            int nextY = y+d[i][1];
            if (inMatrix(nextX, nextY) && !v[nextX][nextY] && matrix[nextX][nextY] >= h) {
                pour(matrix, nextX, nextY, matrix[nextX][nextY], v);
            }
        }
    }

    private boolean inMatrix(int x, int y) {
        return x>=0 && x<r && y>=0 && y<c;
    }


    private boolean inAtlanticCoast(int x, int y) {
        return (y==c-1 )||( x==r-1 );
    }

    private boolean inPacificCoast(int x, int y) {
        return (x==0 )||(y==0 );
    }


}
```