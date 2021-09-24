package com.qinh;

import java.io.*;
import java.util.*;

/**
 * 赫夫曼编码：
 * 传输的 字符串
 * 1) i like like like java do you like a java
 *
 * 2)  d:1 y:1 u:1 j:2  v:2  o:2  l:4  k:4  e:4 i:5  a:5   :9  // 各个字符对应的个数
 *
 * 3)  按照上面字符出现的次数构建一颗赫夫曼树, 次数作为权值
 *
 * 步骤：
 * 构成赫夫曼树的步骤：
 * 1) 从小到大进行排序, 将每一个数据，每个数据都是一个节点 ， 每个节点可以看成是一颗最简单的二叉树
 * 2) 取出根节点权值最小的两颗二叉树
 * 3) 组成一颗新的二叉树, 该新的二叉树的根节点的权值是前面两颗二叉树根节点权值的和
 * 4) 再将这颗新的二叉树，以根节点的权值大小 再次排序， 不断重复  1-2-3-4 的步骤，直到数列中，所有的数据都被处理，就得到一颗赫夫曼树
 *
 * 4)  根据赫夫曼树，给各个字符,规定编码 (前缀编码)， 向左的路径为0 向右的路径为1 ， 编码如下:
 * o: 1000   u: 10010  d: 100110  y: 100111  i: 101
 * a : 110     k: 1110    e: 1111       j: 0000       v: 0001
 * l: 001          : 01
 *
 * 5) 按照上面的赫夫曼编码，我们的"i like like like java do you like a java"   字符串对应的编码为 (注意这里我们使用的无损压缩)
 * 1010100110111101111010011011110111101001101111011110100001100001110011001111000011001111000100100100110111101111011100100001100001110  通过赫夫曼编码处理  长度为  133
 *
 *
 * 6） 长度为 ： 133
 * 说明:
 * 原来长度是  359 , 压缩了  (359-133) / 359 = 62.9%
 * 此编码满足前缀编码, 即字符的编码都不能是其他字符编码的前缀。不会造成匹配的多义性
 * 赫夫曼编码是无损处理方案
 *
 * 赫夫曼编码压缩注意事项：
 * 1.如果文件本身就是压缩的文件，使用赫夫曼编码进行压缩的效果不是很明显，eg. ppt,视频等文件
 * 2.赫夫曼编码压缩是按字节来压缩的，所以可以压缩一切文件
 * 3.如果一个文件重复的内容不多，利用赫夫曼编码压缩的效果也不明显
 *
 * @author Qh
 * @version 1.0
 * @date 2021/9/23 9:31
 */
public class HuffmanCode {
    public static void main(String[] args){
        //String s = "i like like like java do you like a java";
        //String s = "hello world";
        //byte[] content = s.getBytes();
        //System.out.println(content.length);
        //List<Node> nodes = getNodes(content);
        ////System.out.println(nodes);
        //Node root = createHuffmanTree(nodes);
        //preOrder(root);
        //Map<Byte, String> huffmanCodes = getHuffmanCodes(root);
        ////{32=01, 97=100, 100=11000, 117=11001, 101=1110, 118=11011, 105=101, 121=11010, 106=0010, 107=1111, 108=000, 111=0011}
        //System.out.println(huffmanCodes);
        //
        //byte[] huffmanCodeBytes = zip(content, huffmanCodes);
        //System.out.println(Arrays.toString(huffmanCodeBytes));
        //byte[] huffmanCodeBytes = huffmanZip(content);
        //System.out.println(Arrays.toString(huffmanCodeBytes));
        //
        //byte[] sourceBytes = decode(huffmanCodes, huffmanCodeBytes);
        //System.out.println("原来的字符串: " + new String(sourceBytes));

        String srcFile = "E:\\1.hprof";
        String destFile = "E:\\temp\\1.hprof";
        zipFile(srcFile,destFile);

        String destFile2 = "E:\\temp\\src\\1.hprof";
        unZipFile(destFile, destFile2);
    }

    /**
     * 统计每个字符出现的次数
     * @param bytes 字节数组
     * @return node集合
     */
    private static List<Node> getNodes(byte[] bytes){
        List<Node> nodes = new ArrayList<>();
        //遍历bytes，统计每个字符出现的次数，用HashMap存放每个字符出现的次数
        Map<Byte,Integer> counts = new HashMap<>();
        for (byte b : bytes){
            Integer count = counts.get(b);
            //说明还没存放过数据，第一次存放
            if (count == null){
                counts.put(b, 1);
            }else {
                counts.put(b, ++count);
            }
        }
        //将每个键值对转为Node对象，并加入nodes集合
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            Byte key = entry.getKey();
            Integer value = entry.getValue();
            nodes.add(new Node(key, value));
        }

        return nodes;
    }

    /**
     * 生成赫夫曼树
     * @param nodes node集合
     * @return 赫夫曼树根结点
     */
    private static Node createHuffmanTree(List<Node> nodes){
        while (nodes.size() > 1){
            //从小到大排序，并取出权值最小的两个Node组成一颗树
            Collections.sort(nodes);
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            //新创建的根结点，没有data，只有权值
            Node parentNode = new Node(null, leftNode.getWeight() + rightNode.getWeight());
            parentNode.setLeft(leftNode);
            parentNode.setRight(rightNode);
            //将parentNode加入nodes中，并移除权值最小的两个Node
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parentNode);
        }
        return nodes.get(0);
    }

    /**
     * 前序遍历
     * @param root 赫夫曼树的根结点
     */
    private static void preOrder(Node root){
        if (root != null){
            root.preOrder();
        }else {
            System.out.println("root结点为空.........");
        }
    }

    /**
     * 存放赫夫曼编码
     */
    private static Map<Byte,String> huffmanCodes = new HashMap<>();
    /**
     * 存储某个叶子结点的路径
     */
    private static StringBuilder stringBuilder = new StringBuilder();

    /**
     * 为了调用方便，我们重载getHuffmanCodes
     * @param root 赫夫曼树的根结点
     * @return 赫夫曼编码
     */
    private static Map<Byte, String> getHuffmanCodes(Node root) {
        if(root == null) {
            return null;
        }
        //处理root的左子树
        getHuffmanCodes(root.getLeft(), "0", stringBuilder);
        //处理root的右子树
        getHuffmanCodes(root.getRight(), "1", stringBuilder);
        return huffmanCodes;
    }

    /**
     * 功能：将传入的node结点的所有叶子结点的赫夫曼编码得到，并放入到huffmanCodes集合
     * @param node 传入结点
     * @param code 路径： 左子结点是 0, 右子结点 1
     * @param stringBuilder 用于拼接路径
     */
    private static void getHuffmanCodes(Node node,String code,StringBuilder stringBuilder){
        StringBuilder stringBuilder1 = new StringBuilder(stringBuilder);
        //将code加入到stringBuilder1中
        stringBuilder1.append(code);
        if (node != null){
            //判断当前结点是否是叶子结点
            //非叶子结点
            if (node.getData() == null){
                //向左递归处理
                getHuffmanCodes(node.getLeft(), "0", stringBuilder1);
                //向右递归处理
                getHuffmanCodes(node.getRight(), "1", stringBuilder1);
            }
            //说明是一个叶子结点，就将其存入赫夫曼编码中
            else {
                huffmanCodes.put(node.getData(), stringBuilder1.toString());
            }
        }
    }

    /**
     * 将字符串对应的byte[] 数组，通过生成的赫夫曼编码表，返回一个赫夫曼编码 压缩后的byte[]
     * 举例： String content = "i like like like java do you like a java"; =》 byte[] contentBytes = content.getBytes();
     * 返回的是 字符串 "1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100"
     * => 对应的 byte[] huffmanCodeBytes  ，即 8位对应一个 byte,放入到 huffmanCodeBytes
     * huffmanCodeBytes[0] =  10101000(补码) => byte  [推导  10101000=> 10101000 - 1 => 10100111(反码)=> 11011000= -88 ]
     * huffmanCodeBytes[1] = -88
     *
     * @param bytes 原始的字符串对应的 byte[]
     * @param huffmanCodes 生成的赫夫曼编码map
     * @return 返回赫夫曼编码处理后的 byte[]
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes){
        //先利用赫夫曼编码表，将原始的byte[]转为赫夫曼编码后的字符串
        StringBuilder stringBuilder = new StringBuilder();
        //遍历byte[]
        for (byte b : bytes){
            stringBuilder.append(huffmanCodes.get(b));
        }
        //1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100
        //System.out.println(stringBuilder);

        //统计返回  byte[] huffmanCodeBytes 长度
        int len;
        if (stringBuilder.length() % 8 == 0){
            len = stringBuilder.length() / 8;
        }else {
            len = stringBuilder.length() / 8 + 1;
        }
        //创建存储压缩后的byte[]
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;//记录是第几个byte
        //因为是每8位对应一个byte,所以步长 +8
        for (int i = 0; i < stringBuilder.length(); i +=8) {
            String strByte;
            //不够8位
            if(i+8 > stringBuilder.length()) {
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            //将strByte 转成一个byte,放入到 huffmanCodeBytes
            huffmanCodeBytes[index] = (byte)Integer.parseInt(strByte, 2);
            index++;
        }
        return huffmanCodeBytes;
    }

    /**
     * 使用一个方法，将前面的方法封装起来，便于我们的调用.
     * @param bytes 原始的字符串对应的字节数组
     * @return 经过赫夫曼编码处理后的字节数组(压缩后的数组)
     */
    private static byte[] huffmanZip(byte[] bytes) {
        List<Node> nodes = getNodes(bytes);
        //根据 nodes 创建的赫夫曼树
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        //对应的赫夫曼编码(根据 赫夫曼树)
        Map<Byte, String> huffmanCodes = getHuffmanCodes(huffmanTreeRoot);
        //根据生成的赫夫曼编码，压缩得到压缩后的赫夫曼编码字节数组
        return zip(bytes, huffmanCodes);
    }

    /**
     * 将一个byte 转成一个二进制的字符串
     * @param b 传入的 byte
     * @param flag 标志是否需要补高位如果是true ，表示需要补高位，如果是false表示不补, 如果是最后一个字节，无需补高位
     * @return 是该b 对应的二进制的字符串，（注意是按补码返回）
     */
    private static String byteToBitString(boolean flag, byte b) {
        //使用变量保存 b，将 b 转成 int
        int temp = b;
        //如果是正数我们还存在补高位
        if(flag) {
            //按位或 256  1 0000 0000  | 0000 0001 => 1 0000 0001
            temp |= 256;
        }
        //返回的是temp对应的二进制的补码
        String str = Integer.toBinaryString(temp);
        if(flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }

    /**
     * 完成对压缩数据的解码
     * @param huffmanCodes 赫夫曼编码表map
     * @param huffmanBytes 赫夫曼编码得到的字节数组
     * @return 就是原来的字符串对应的数组
     */
    private static byte[] decode(Map<Byte,String> huffmanCodes,byte[] huffmanBytes){
        //先得到huffmanBytes对应的二进制的字符串
        StringBuilder stringBuilder = new StringBuilder();
        //将byte数组转成二进制的字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            //判断是不是最后一个字节
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, b));
        }

        /*
        把字符串按照指定的赫夫曼编码进行解码
        需要把赫夫曼码表进行调换，因为反向查询 a->100 100->a
         */
        Map<String,Byte> map = new HashMap<>();
        for (Map.Entry<Byte,String> entry : huffmanCodes.entrySet()){
            map.put(entry.getValue(), entry.getKey());
        }

        //创建一个集合，存放byte
        List<Byte> list = new ArrayList<>();
        //i可以理解成就是索引，扫描stringBuilder
        for (int i = 0; i < stringBuilder.length();) {
            //检索的小的计数器
            int count = 1;
            boolean flag = true;
            Byte b = null;
            while (flag){
                //递增的取出key,i不动，让count移动，指定匹配到的一个字符
                String key = stringBuilder.substring(i, i + count);
                b = map.get(key);
                //没有匹配到
                if (b == null){
                    count++;
                }else {
                    flag = false;
                }
            }
            list.add(b);
            //i移动到count位置
            i += count;
        }
        //当for循环结束后，list中就存放了所有的字符 i like like like java do you like a java
        //把list中的数据放入到byte[]并返回

        byte[] b = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }


    /**
     * 将一个文件进行压缩
     * @param srcFile 需要压缩的文件
     * @param destFile 压缩后的文件存放的位置
     */
    private static void zipFile(String srcFile,String destFile){
        //创建输入输出流
        InputStream is = null;
        OutputStream os = null;
        //创建对象输出流
        ObjectOutputStream oos = null;
        try{
            //创建文件的输入流
            is = new FileInputStream(srcFile);
            //创建一个和源文件大小一样的byte[]
            byte[] bytes = new byte[is.available()];
            //将数据读取进byte[]
            is.read(bytes);
            //获取采用赫夫曼压缩后的字节数组
            byte[] hufffmanBytes = huffmanZip(bytes);
            //创建文件输出流
            os = new FileOutputStream(destFile);
            oos = new ObjectOutputStream(os);
            //把赫夫曼编码后的字节数组写入压缩文件
            oos.writeObject(hufffmanBytes);
            //这里我们以对象流的方式写入 赫夫曼编码，是为了以后我们恢复源文件时使用
            //注意一定要把赫夫曼编码 写入压缩文件
            oos.writeObject(huffmanCodes);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if (is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (os != null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (oos != null){
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 完成对压缩文件的解压
     * @param srcFile 准备解压的文件
     * @param destFile 将文件解压到哪个路径
     */
    private static void unZipFile(String srcFile,String destFile){
        //创建输入输出流
        InputStream is = null;
        OutputStream os = null;
        //创建对象的输入流
        ObjectInputStream ois = null;
        try {
            is = new FileInputStream(srcFile);
            ois = new ObjectInputStream(is);
            //读取到赫夫曼数组
            byte[] huffmanBytes = (byte[]) ois.readObject();
            //读取赫夫曼编码
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();
            //解码
            byte[] bytes = decode(huffmanCodes, huffmanBytes);
            //将原始的数组写出到destFile文件
            os = new FileOutputStream(destFile);
            os.write(bytes);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            if (is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (os != null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ois != null){
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }




}

/**
 * 创建Node，存放真实字符数据和权值等
 */
class Node implements Comparable<Node>{

    /** 存放的真实数据值 eg. 'a' -> 97*/
    private Byte data;
    /** 字符的权值，字符出现的次数 */
    private int weight;
    /** 左结点 */
    private Node left;
    /** 右结点 */
    private Node right;

    /**
     * 前序遍历
     */
    public void preOrder(){
        System.out.println(this);
        if (this.left != null){
            this.left.preOrder();
        }
        if (this.right != null){
            this.right.preOrder();
        }
    }

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    public Byte getData() {
        return data;
    }

    public void setData(Byte data) {
        this.data = data;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }
}
