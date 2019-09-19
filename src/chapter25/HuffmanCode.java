package chapter25;

import chapter23.HeapSort;

import java.util.Scanner;

// 实现 哈夫曼树，并得到编码
public class HuffmanCode {

    private static int[] frequency;
    private static String[] huffmanCode;
    public static void main(String[] args) {
        try (
                Scanner input = new Scanner(System.in)
        ) {
            String text = input.nextLine();
            // 下一步就直接得到编码 （单个字母 和 单子字母出现的次数） 用一个0pint[] 就行 和 编号代码 String[256]
            printHuffmanCodeList(text);

        }
    }

    public static void getHuffmanCode(String text) {
        if (null == text || text.equals("")) return;
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            str.append(huffmanCode[text.charAt(i)]);
        }
        System.out.println(str);
    }

    public static void printHuffmanCodeList(String text) {
        int[] counts = frequency = getCharacterFrequency(text);
        Tree huffmanTree = getHuffmanTree(counts);
        String[] codes = huffmanCode = getCode(huffmanTree);
        System.out.printf("%-15s%-15s%-15s%-15s\n", "ASCII 代码", "字符", "频率", "哈夫曼代码");
        System.out.printf("%-15s%-15s%-15s%-15s\n", "ASCII Code", "Character", "Frequency", "Code");
        for (int i = 0; i < codes.length; i++) {
            if (counts[i] > 0) {
                System.out.printf("%-15d%-15s%-15d%-15s\n", i, (char) i, counts[i], codes[i]);
            }
        }

        System.out.println("\n---------------我是分割线-----------------\n");
        getHuffmanCode(text);
    }

    private static String[] getCode(Tree tree) {
        if (tree.root == null) return null;
        String[] codes = new String[2 << 7]; // 这个的存在是记录下来每棵树的 code ，省的到时候又遍历HuffmanTree
        assignCode(tree.root, codes);
        return codes;
    }

    // 对每个树中的节点分配 code
    private static void assignCode(Tree.Node root, String[] codes) {
        if (root.left != null) {
            root.left.code = root.code + "0";
            assignCode(root.left, codes);
            root.right.code = root.code + "1";
            assignCode(root.right, codes);
        } else
            codes[root.element] = root.code;
    }

    // 得到 字符 频率 ---
    private static int[] getCharacterFrequency(String text) {
        int[] counts = new int[2 << 7];
        for (int i = 0; i < text.length(); i++)
            counts[text.charAt(i)]++;

        return counts;
    }

    // 得到 HuffmanTree
    private static Tree getHuffmanTree(int[] counts) {
        HeapSort.Heap<Tree> heap = new HeapSort.Heap<>();

        for (int i = 0; i < counts.length; i++)
            if (counts[i] > 0)
                heap.add(new Tree((char) i, counts[i]));

        // 为什么要 size > 1 ，因为权重越重的树，越在下面，所有的树都会变成森林，变成一个HuffmanTree，
        // 所以当size == 1 时，此时就是HuffmanTree

        while (heap.getSize() > 1) {
            Tree t1 = heap.remove();
            Tree t2 = heap.remove();
            heap.add(new Tree(t1, t2));
        }

        return heap.remove();
    }


    public static class Tree implements Comparable<Tree> {

        // 一个数据域
        public Node root;

        // 当自己是根节点时，创建的一棵树
        public Tree(char element, int weight) {
            root = new Node(element, weight);
        }

        // 当自己不是根节点，创建的一颗两个权重相加的森林
        public Tree(Tree t1, Tree t2) {
            root = new Node();
            root.left = t1.root;
            root.right = t2.root;
            root.weight = t1.root.weight + t2.root.weight;
        }

        @Override
        public int compareTo(Tree o) {
            if (root.weight < o.root.weight) // 这里权重越轻的越靠近根节点
                return 1;
            else if (root.weight > o.root.weight)
                return -1;
            else
                return 0;
        }

        public class Node {
            public char element;
            public int weight; // 权重
            public String code = "";
            public Node left;
            public Node right;

            public Node() {
            }

            public Node(char element, int weight) {
                this.element = element;
                this.weight = weight;
            }
        }
    }
}
