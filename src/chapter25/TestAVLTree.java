package chapter25;

public class TestAVLTree {
    public static void main(String[] args) {
        MyAVLTree<Integer> tree = new MyAVLTree<>(new Integer[]{25, 20, 5});
        System.out.print("After inserting 25, 20, 5: ");
        printTree(tree);

        tree.insert(34);
        tree.insert(50);
        System.out.print("\nAfter inserting 34, 50: ");
        printTree(tree);

        tree.insert(30);
        System.out.print("\nAfter inserting 30: ");
        printTree(tree);

        tree.insert(10);
        System.out.print("\nAfter inserting 10: ");
        printTree(tree);

        System.out.println("第 7 小的数值 : " + tree.find(7));

        tree.delete(34);
        tree.delete(30);
        tree.delete(50);
        System.out.print("\nAfter removing 34, 30, 50: ");
        printTree(tree);

        tree.delete(5);
        System.out.print("\nAfter removing 5 :");
        printTree(tree);

        System.out.print("\nTraverse the elements in the tree : ");
        for (int e : tree)
            System.out.print(e + "  ");

    }


    public static void printTree(MyBST tree) {
        System.out.print("\nInorder (中序): ");
        tree.inorder();
        System.out.print("\nPostorder (后序) : ");
        tree.postorder();
        System.out.print("\nPreorder (前序) : ");
        tree.preorder();

        System.out.print("\nThe number of nodes is " + tree.getSize());
        System.out.println();
    }
}
