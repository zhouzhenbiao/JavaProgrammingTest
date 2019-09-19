package chapter25;

import java.util.LinkedList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
//        MyBST<String> tree = new MyBST<>();
//        tree.insert("George");
//        tree.insert("Michael");
//        tree.insert("Tom");
//        tree.insert("Adam");
//        tree.insert("Jones");
//        tree.insert("Peter");
//        tree.insert("Daniel");
//
//
//        System.out.print("Preorder（前序） : ");
//        tree.preorder();
//        System.out.print("Inorder（中序） : ");
//        tree.inorder();
//        System.out.print("Postorder（后序） ： ");
//        tree.postorder();
//        System.out.print("breadthFirstTraversal（广度优先遍历） ： ");
//        tree.breadthFirstTraversal();
//
//        System.out.println("BST 树的高度 ： " + tree.getHeight());
//
//        System.out.println("\nIs Peter in the tree ? " + tree.search("Peter"));
//
//        System.out.println("\nA path from the root to Peter is: ");
//        List<MyBST.TreeNode<String>> path = tree.path("Peter");
//        for (int i = 0; i < path.size(); i++) {
//            System.out.print(12323);
//            System.out.print(path.get(i).element + "   ");
//        }
//
//        System.out.println("\ndelete Peter in the tree ? " + tree.delete("Peter"));
//        tree.inorder();
//
//        System.out.println("\n232321321213");
//        for (String s : tree) {
//            System.out.println(123213);
//            System.out.print(s.toLowerCase() + "   ");
//        }

//        Integer[] numbers = {2, 4, 3, 1, 8, 5, 6, 7};
//        MyBST<Integer> integerBST = new MyBST<>(numbers);
//        System.out.print("Inorder（中序） : ");
//        integerBST.inorder();
//
//        System.out.print("\nstackInorder(中序) : ");
//        integerBST.stackInorder();
//
//        System.out.println("path : ");
//        LinkedList<MyBST.TreeNode<Integer>> list = integerBST.path(8);
//        for (MyBST.TreeNode<Integer> temp : list) {
//            System.out.println(temp.element);
//        }

        B b = new B();
        b.print("hahaha");
    }

    public static void shuffle(int[] a) {
        for (int i = 0; i < a.length; i++) {
            int index = (int) (Math.random() * 7);
            int temp = a[i];
            a[i] = a[index];
            a[index] = temp;
        }
    }

    public static class A {
        public A() {

        }

        protected void print(String text) {
            System.out.println(text + " :  A ");
        }
    }

    public static class B extends A {
        public B() {

        }

        @Override
        protected void print(String text) {
            super.print(text);
            System.out.println(text + " :  B ");
        }


    }
}
