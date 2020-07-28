package com.gpl.leetcode.leetcode.editor.cn;
/**
 * 题目描述
 * 百元百鸡问题。每只母鸡3元，每只公鸡4元，每只小鸡0.5元，如果花100元钱买100只鸡，请问有哪些可能？试编程输出所有的组合。
 * 【注：每种鸡的数量都可以为零】
 */

/**
 * @author gongpulin
 * date 2020-07-27
 */
public class Pbaiyuanmaibaiji {
    public static void main(String[] args) {
        Pbaiyuanmaibaiji.s1();
    }
    public static void s2() {
        // x + y + z = 100
        //6x + 8y + z = 200
        //3*x + 4*y + z/2 = 100
        //由该方程组消元z可得到 5x+7y=100，令y=0，x最大=20，则可用一个循环解决该问题
        for (int x = 0; x <= 20; x++) {
            int y = (100 - 5 * x) / 7;
            int z = 100 - x - y;
            if ( (3 * x + 4 * y + z / 2) == 100) {
                System.out.println("x="+x+",y="+y+",z="+z);
            }
        }

        /**
         * x=6,y=10,z=84
         * x=13,y=5,z=82
         * x=20,y=0,z=80
         */

    }

    public static void s1() {
        int x, y ,z;
        for (x = 0; x < 34; x++) {
            for (y = 0; y <= 25; y++) {
                z = 100 - x - y;
                if ( (z % 2 == 0) && (3 * x + 4 * y + z / 2) == 100) {
                    System.out.println("x="+x+",y="+y+",z="+z);
                }
            }
        }
        /**
         * x=2,y=13,z=85
         * x=6,y=10,z=84
         * x=9,y=8,z=83
         * x=13,y=5,z=82
         * x=16,y=3,z=81
         * x=20,y=0,z=80
         */
    }
}
