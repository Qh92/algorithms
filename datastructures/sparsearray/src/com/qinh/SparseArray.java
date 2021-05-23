package com.qinh;

import java.io.*;

/**
 * 稀疏数组
 *
 * @author Qh
 * @version 1.0
 * @date 2021-05-17-22:23
 */
public class SparseArray {

    public static void main(String[] args) {
        //创建一个原始的二维数组 11 * 11
        //0:表示没有棋子 , 1:表示黑子 , 2:表示篮子
        int[][] chessArr1 = new int[11][11];
        //第二行第三列有一个黑子，第三行第四列有一个篮子
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        //第五行第六列有一个篮子
        chessArr1[4][5] = 2;
        //输出原始的二维数组
        for (int[] o : chessArr1){
              for (int v : o){
                  System.out.printf("%d\t",v);
              }
            System.out.println();
        }

        //将二维数组转稀疏数组的思路
        //1.先遍历二维数组得到非0数据的个数
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0){
                    sum++;
                }
            }
        }
        //2.创建对应的稀疏数组
        int[][] sparseArr = new int[sum+1][3];
        //给稀疏数组赋值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        //遍历二维数组，将非0的值存放到稀疏数组中
        //count用于记录是第几个非0数据
        int count = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0){
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }
        //输出稀疏数组的形式
        System.out.println();
        System.out.println("得到的稀疏数组为--------");
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n",sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);
        }

        //将稀疏数组保存到磁盘上，比如map.data
        ObjectOutputStream writer = null;
        try {
            writer = new ObjectOutputStream(new FileOutputStream("E:\\items\\algorithms\\algorithms\\datastructures\\sparsearray\\map.data"));
            writer.writeObject(sparseArr);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            try {
                if (writer != null){
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println();

        //读取磁盘的文件 map.data
        try(ObjectInputStream reader = new ObjectInputStream(new FileInputStream("E:\\items\\algorithms\\algorithms\\datastructures\\sparsearray\\map.data"));) {
            Object object = reader.readObject();
            sparseArr = (int[][]) object;
        }catch (Exception e){
            System.out.println(e);
        }

        System.out.println("磁盘读取的稀疏数组");
        for (int[] o : sparseArr){
            for (int v : o){
                System.out.printf("%d\t",v);
            }
            System.out.println();
        }


        //将稀疏数组恢复成原始的二维数组
        /*
        1.先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组，比如上面的chessArr1 = int[11][11]
        2.再读取稀疏数组后几行的数据，并赋值给原始的二维数组即可
         */

        //1.先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
        int[][] chessArr2 = new int[sparseArr[0][0]][sparseArr[0][1]];

        //2.再读取稀疏数组后几行的数据，并赋值给原始的二维数组即可
        for (int i = 1; i < sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        //输出恢复后的二维数组
        System.out.println();
        System.out.println("恢复后的二维数组");
        for (int[] o : chessArr2){
            for (int v : o){
                System.out.printf("%d\t",v);
            }
            System.out.println();
        }




    }
}
