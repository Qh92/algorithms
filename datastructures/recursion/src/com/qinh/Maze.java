package com.qinh;

/**
 * 迷宫问题
 *
 * 如何找出最短路径?
 *
 * @author Qh
 * @version 1.0
 * @date 2021-10-02-11:03
 */
public class Maze {
    public static void main(String[] args) {
        //先创建一个二维数组，模拟迷宫
        int[][] map = new int[8][7];
        //使用1表示墙
        //先把上下全部置为1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //左右全部置为1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //设置挡板，1表示
        map[3][1] = 1;
        map[3][2] = 1;

        //输出地图
        System.out.println("地图的情况");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        //使用递归回溯给小球找路
        //setWay(map, 1, 1);
        setWay2(map, 1, 1);
        System.out.println("再输出新的地图的情况");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

    }

    /**
     * 使用递归回溯来给小球找路
     * 说明：如果小球能到map[6][5]位置，则说明通路找到
     * 当map[i][j]为0表示该点没有走过，1表示墙不能走，2表示通路可以走，3表示该位置已经走过，但是走不通
     * 在走迷宫时，需要确定一个策略（方法），先走下面->再走右面->再走上->最后走左，如果该点走不通，再回溯
     * @param map 表示地图
     * @param i 从哪个位置开始找的坐标
     * @param j 坐标
     * @return 如果找到通路，就返回true,否则返回false
     */
    public static boolean setWay(int[][] map,int i,int j){
        //通路已经找到
        if (map[6][5] == 2){
            return true;
        }else {
            //如果当前这个点还没有走过
            if (map[i][j] == 0){
                //按照策略走 先走下面->再走右面->再走上->最后走左
                //假定该点是可以走通的
                map[i][j] = 2;
                //向下走
                if (setWay(map, i+1, j)){
                    return true;
                }
                //向右走
                else if (setWay(map, i, j+1)){
                    return true;
                }
                //向上走
                else if (setWay(map, i-1, j)){
                    return true;
                }
                //向左走
                else if (setWay(map, i, j-1)){
                    return true;
                }
                //说明该点是走不通的，是死路
                map[i][j] = 3;
                return false;
            }
            //map[i][j] != 0,可能是1，2，3
            else {
                return false;
            }
        }
    }


    /**
     * 修改找路的策略 改成 上->右->下->左
     * @param map
     * @param i
     * @param j
     * @return
     */
    public static boolean setWay2(int[][] map,int i,int j){
        //通路已经找到
        if (map[6][5] == 2){
            return true;
        }else {
            //如果当前这个点还没有走过
            if (map[i][j] == 0){
                //按照策略走 先走下面->再走右面->再走上->最后走左
                //假定该点是可以走通的
                map[i][j] = 2;
                if (setWay2(map, i-1, j)){
                    return true;
                }
                else if (setWay2(map, i, j+1)){
                    return true;
                }
                else if (setWay2(map, i+1, j)){
                    return true;
                }
                else if (setWay2(map, i, j-1)){
                    return true;
                }
                //说明该点是走不通的，是死路
                map[i][j] = 3;
                return false;
            }
            //map[i][j] != 0,可能是1，2，3
            else {
                return false;
            }
        }
    }
}
