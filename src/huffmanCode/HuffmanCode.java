package huffmanCode;

import java.io.*;
import java.util.*;

public class HuffmanCode {
    public static void main(String[] args) {
//        String str = "i like like like java do you like a java";
//        //将字符串逐个放入字节数组中
//        byte[] strBytes = str.getBytes();
//        byte[] huffmanCodes = huffmanZip(strBytes);
//        System.out.println("哈夫曼编码表：" + huffmanCode);
//        System.out.println("压缩后哈夫曼编码：" + Arrays.toString(huffmanCodes));
//        byte[] decode = decode(huffmanCode, huffmanCodes);
//        System.out.println(new String(decode));

//        //测试文件压缩
//        String srcFile = "C:\\Users\\ASUS\\Desktop\\src.bmp";
//        String dstFile = "C:\\Users\\ASUS\\Desktop\\dst.zip";
//        zipFile(srcFile, dstFile);
//        System.out.println("压缩文件成功！");

        //测试文件解压
        String srcFile = "C:\\Users\\ASUS\\Desktop\\dst.zip";
        String dstFile = "C:\\Users\\ASUS\\Desktop\\src2.bmp";
        unZipFile(srcFile, dstFile);
        System.out.println("解压文件成功！");
    }

    //对文件进行解压
    //srcFile为待解压文件，dstFile为解压后文件
    public static void unZipFile(String zipFile, String dstFile) {
        //创建输入流
        InputStream is = null;
        //定义对象输入流
        ObjectInputStream ois = null;
        //创建文件输出流
        FileOutputStream os = null;
        try {
            //创建文件输入流
            is = new FileInputStream(zipFile);
            //创建一个和is关联的对象流
            ois = new ObjectInputStream(is);
            //创建一个byte[]接收压缩后的字节
            byte[] bytes = (byte[]) ois.readObject();
            //创建一个Map<Byte,String>接收哈夫曼编码表
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();
            //解码
            byte[] decode = decode(huffmanCodes, bytes);
            //将解压后的字节数组写入文件
            os = new FileOutputStream(dstFile);
            //将解压的字节数组
            os.write(decode);
        } catch (Exception e) {
            e.getMessage();
        } finally {
            try {
                os.close();
                ois.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //用哈夫曼编码对文件进行压缩
    //srcFile为传入的待压缩文件，dstFile为压缩好要传出的文件
    public static void zipFile(String srcFile, String dstFile) {
        //创建输出流
        OutputStream os = null;
        //定义对象输出流
        ObjectOutputStream oos = null;
        //创建文件输入流
        FileInputStream is = null;
        try {
            //将文件输入
            is = new FileInputStream(srcFile);
            //创建一个byte数组，用于接受文件内容
            byte[] bytes = new byte[is.available()];
            is.read(bytes);
            //对byte[]进行压缩
            byte[] huffmanBytes = huffmanZip(bytes);
            //存放压缩文件
            os = new FileOutputStream(dstFile);
            //创建和文件输出流相关的ObjectOutputStream
            oos = new ObjectOutputStream(os);
            //将用哈夫曼编码压缩后的字节数组写入压缩文件
            oos.writeObject(huffmanBytes);
            //将哈夫曼编码表写入文件，以便以后解压文件使用
            oos.writeObject(huffmanCode);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                oos.close();
                os.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    //哈夫曼编码最后一个字节的位数
    static int lastCount;

    //对压缩数据的解码
    //Map<Byte, String> huffmanCode 哈夫曼编码表
    //byte[] huffmanCodes 通过哈夫曼编码压缩后的字节数组
    private static byte[] decode(Map<Byte, String> huffmanCode, byte[] huffmanCodes) {
        //将字节数组转回哈夫曼编码，并存入stringBuilder
        StringBuilder stringBuilder = new StringBuilder();
        boolean flag = false;
        for (int i = 0; i < huffmanCodes.length; i++) {
            if (i == huffmanCodes.length - 1) {
                flag = true;
            }
            stringBuilder.append(byteToBitString(huffmanCodes[i], flag));
        }
        //将Map<Byte, String> 的K V 逆转，即变成Map<String, Byte>
        Map<String, Byte> temp = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCode.entrySet()) {
            temp.put(entry.getValue(), entry.getKey());
        }
        //遍历stringBuilder，对应已经逆转的Map，将哈夫曼编码转换为字符串
        //创建一个list，用于存放字符
        List<Byte> string = new ArrayList<>();
        //遍历
        for (int i = 0; i < stringBuilder.length(); ) {
            //创建一个count，使i作为起始点，count是i向后遍历的长度
            int count = 1;
            //用于判断是否找到对应字符
            boolean search = true;
            Byte b = null;
            while (search) {
                //遍历Map，进行查找
                String key;
                if (i + count < stringBuilder.length()) {
                    key = stringBuilder.substring(i, i + count);
                } else {
                    key = stringBuilder.substring(i);
                }
                b = temp.get(key);
                if (b != null) {
                    search = false;
                } else {
                    count++;
                }
            }
            //将找到的字符加入list
            string.add(b);
            i += count;
        }
        //将list的字符串放入byte[]中
        byte[] bytes = new byte[string.size()];
        for (int i = 0; i < string.size(); i++) {
            bytes[i] = string.get(i);
        }

        return bytes;
    }

    //将字节byte转化为二进制数
    //b为待转换的字符，flag是判断是否为最后一个字节
    //最后返回字节的补码
    private static String byteToBitString(byte b, boolean flag) {
        //将字节b转成int类型
        int temp = b;
        //然后和256(即1 0000 0000)进行一个或运算，进行补位
        temp |= 256;
        String str = Integer.toBinaryString(temp);//temp对应的二进制补码
        if (flag) {//是最后一位数
            return str.substring(str.length() - lastCount);
        } else {//若不是
            return str.substring(str.length() - 8);
        }
    }


    //封装
    //输入原始字符串，返回哈夫曼编码处理后的字节数组
    public static byte[] huffmanZip(byte[] strBytes) {
        //获得对应的哈夫曼树根节点
        Node root = createHuffmanTree(getNodes(strBytes));
        //获得对应哈夫曼编码表，并存入Map<Byte, String> huffmanCode中
        getCode(root);
        //压缩对应哈夫曼编码，并返回
        return zip(strBytes, huffmanCode);
    }

    //将存放字符串字节的byte[]和哈夫曼编码表 Map<Byte,String>结合压缩成一个byte[]
    public static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCode) {
        //通过bytes和哈夫曼编码表 huffmanCode 获得哈夫曼编码，并存入stringBuilder
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(huffmanCode.get(b));
        }

        //System.out.println(stringBuilder);

        //将哈夫曼编码压缩进新的byte[len]，每8为为一组（补码）
        //len为byte[]的长度
        int len = stringBuilder.length() % 8 == 0 ? stringBuilder.length() / 8 : stringBuilder.length() / 8 + 1;
        //创建byte[]，并将哈夫曼编码按8个为一位放入byte[]中
        byte[] huffmanCodes = new byte[len];
        //huffmanCode的数组下标
        int index = 0;
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            //若编码不足8为，则代表已经到最后一组，则直接全部打入即可
            if (i + 8 > stringBuilder.length()) {
                lastCount = stringBuilder.length() - i;
                huffmanCodes[index] = (byte) Integer.parseInt(stringBuilder.substring(i), 2);
            } else {
                huffmanCodes[index] = (byte) Integer.parseInt(stringBuilder.substring(i, i + 8), 2);
            }
            index++;
        }
        return huffmanCodes;
    }

    //将字节串转换为ArrayList类型，以便创建哈夫曼树
    public static List<Node> getNodes(byte[] bytes) {
        //创建一个ArrayList
        List<Node> nodes = new ArrayList<>();
        //将字符类型和出现的次数加入map中
        Map<Byte, Integer> byteCount = new HashMap<>();
        for (byte b : bytes) {
            Integer count = byteCount.get(b);
            if (count == null) {
                byteCount.put(b, 1);
            } else {
                byteCount.put(b, count + 1);
            }
        }
        //遍历Map，将Map中的值放入nodes中
        for (Map.Entry<Byte, Integer> entry : byteCount.entrySet()) {
            byte data = entry.getKey();
            int weight = entry.getValue();
            Node node = new Node(data, weight);
            nodes.add(node);
        }
        return nodes;
    }

    //创建哈夫曼树
    public static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {//当等于1时，代表表内只剩哈夫曼树的根节点
            //先对list进行排序
            Collections.sort(nodes);
            //取出list里权值最小的节点
            Node leftNode = nodes.get(0);
            //取出list里权值第二小的节点
            Node rightNode = nodes.get(1);
            //新的节点
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            //删除已经处理好的节点
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //将新节点加入list中
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    //创建一个Map<Byte,String>用于存放字符对应的哈夫曼编码,形成哈夫曼编码表
    static Map<Byte, String> huffmanCode = new HashMap<>();
    //创建一个StringBuilder用于存放拼接的路径，走左子树为0，走右子树为1
    static StringBuilder stringBuilder = new StringBuilder();

    //重载方法getCode
    public static void getCode(Node root) {
        if (root == null) {
            System.out.println("哈夫曼树为空！");
        } else {
            getCodes(root, "", stringBuilder);
        }
    }

    //将所有节点对应的哈夫曼编码拿到，并存入Map
    //node为当前节点，code为待拼接的编码，stringBuilder为已拼接的字符串
    public static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder1 = new StringBuilder(stringBuilder);
        //将字符串拼接进去
        stringBuilder1.append(code);
        if (node != null) {
            //若node不为叶子节点
            if (node.data == null) {
                //向左递归处理
                getCodes(node.left, "0", stringBuilder1);
                //向右递归处理
                getCodes(node.right, "1", stringBuilder1);
            } else {//若为叶子节点
                huffmanCode.put(node.data, stringBuilder1.toString());
            }
        }
    }

    //前序遍历
    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder(root);
        } else {
            System.out.println("哈夫曼树为空！");
        }
    }

}

class Node implements Comparable<Node> {
    Byte data;//字符的内容
    int weight;//字符出现的次数
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    //前序遍历
    public void preOrder(Node node) {
        //输出自身
        System.out.println(node);
        //向左子树遍历
        if (node.left != null) {
            preOrder(node.left);
        }
        //向右子树遍历
        if (node.right != null) {
            preOrder(node.right);
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        //从小到大排序
        return this.weight - o.weight;
    }
}
