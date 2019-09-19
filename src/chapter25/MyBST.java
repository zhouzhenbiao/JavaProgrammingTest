package chapter25;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class MyBST<E extends Comparable<E>> extends MyAbstractTree<E> {

    protected int size = 0;
    protected TreeNode<E> root;

    public MyBST() {
    }

    public MyBST(E[] objects) {
        for (int i = 0; i < objects.length; i++)
            insert(objects[i]);
    }

    @Override
    public boolean search(E e) {
        TreeNode<E> current = this.root;
        while (current != null) {
            if (e.compareTo(current.element) > 0)
                current = current.right;
            else if (e.compareTo(current.element) < 0)
                current = current.left;
            else
                return true;
        }

        return false;
    }

    @Override
    public boolean insert(E e) {
        if (root == null)
            root = createNewNode(e);
        else {
            TreeNode<E> parent = null;
            TreeNode<E> current = root;

            while (current != null) {
                if (e.compareTo(current.element) < 0) {
                    parent = current;
                    current = current.left;
                } else if (e.compareTo(current.element) > 0) {
                    parent = current;
                    current = current.right;
                } else
                    return false;
            }

            if (e.compareTo(parent.element) < 0)
                parent.left = createNewNode(e);
            else
                parent.right = createNewNode(e);

        }
        size++;
        return true;
    }

    protected TreeNode<E> createNewNode(E e) {
        return new TreeNode<>(e);
    }

    @Override
    public boolean delete(E e) {
        if (null == e) return false;
        TreeNode<E> parent = null;
        TreeNode<E> current = this.root;

        //加入e 不在树上，那么current == null 就为null， 如果在树上，current.element.equals(e)
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                parent = current;
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                parent = current;
                current = current.right;
            } else
                break;
        }

        //case 1: e 元素不在树上，即 current == null
        if (current == null)
            return false;

        //case 2：e 元素在树上，即 current.element.equals(e) == boolean，考虑怎么调整树
        if (current.left == null) { // current 没有左子树，只有右子树
            if (parent == null) //parent == null 即 current == root，即只有右子树且当前为根节点
                root = current.right;
            else {//这个是正常情况，在某一（不为零的）深度上的某节点且只存在右节点
                if (e.compareTo(parent.element) < 0) // 我们还不知道current 是 parent 的 左节点还是右节点
                    parent.left = current.right;
                else
                    parent.right = current.right;
            }
        } else {
            //当左子树不为空时，不考虑右子树的事情，只考虑找到当前结点current的左子树的最大值
            //将这个最大值赋值给当前结点，就保持了当前树的结构，这个最大值的节点父节点的右孩子要么是左孩子
            // RightMost : 最右
            TreeNode<E> parentOfRightMost = current;
            TreeNode<E> rightMost = current.left;

            while (rightMost.right != null) {
                parentOfRightMost = rightMost;
                rightMost = rightMost.right;
            }

            //接下来要做的就是这个最大值结点的指针问题
            current.element = rightMost.element;

            if (parentOfRightMost.left == rightMost) // 这个不就代表 current.left 的左孩子就是最大值
                parentOfRightMost.left = rightMost.left; // 父节点的左孩子是左子树的最大值
            else
                parentOfRightMost.right = rightMost.left;// 父结点的右子树的最大值

        }

        size--;
        return true;
    }

    public LinkedList<TreeNode<E>> path(E e) {
        LinkedList<TreeNode<E>> list = new LinkedList<>();
        TreeNode<E> current = this.root;

        while (current != null) {
            list.addLast(current);
            if (e.compareTo(current.element) < 0)
                current = current.left;
            else if (e.compareTo(current.element) > 0)
                current = current.right;
            else
                break;
        }

        return list;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int getHeight() {
        return getHeight(root);
    }

    private int getHeight(TreeNode<E> root) {
        if (null == root) return 0;
        int left = getHeight(root.left) + 1;
        int right = getHeight(root.right) + 1;
        return Math.max(left, right);
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    //前序遍历
    @Override
    public void preorder() {
        preorder(root);
    }

    private void preorder(TreeNode<E> root) {
        if (null == root) return;
        System.out.print(root.element + "  ");
        preorder(root.left);
        preorder(root.right);
    }

    //中序
    @Override
    public void inorder() {
        inorder(root);
    }

    private void inorder(TreeNode<E> root) {
        if (null == root) return;
        inorder(root.left);
        System.out.print(root.element + "  ");
        inorder(root.right);
    }

    //后序遍历
    @Override
    public void postorder() {
        postorder(root);
    }

    private void postorder(TreeNode<E> root) {
        if (null == root) return;
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.element + "  ");
    }

    // 广度优先遍历，先进先出，链式队列
    @Override
    public void breadthFirstTraversal() {
        breadthFirstTraversal(root);
    }

    private void breadthFirstTraversal(TreeNode<E> root) {
        if (null == root) return;
        LinkedList<TreeNode<E>> linkedList = new LinkedList<>(); // 当作一个队列使用，先进先出
        LinkedList<TreeNode<E>> list = new LinkedList<>();  // 当作一个存储 list 使用
        linkedList.addLast(root);
        while (linkedList.size() != 0) {
            TreeNode<E> current = linkedList.remove();
            list.addLast(current);
            if (null != current.left)
                linkedList.addLast(current.left);
            if (null != current.right)
                linkedList.addLast(current.right);
        }
        Iterator<TreeNode<E>> iterator = list.iterator();
        while (iterator.hasNext())
            System.out.print(iterator.next().element + "   ");
    }

    // 不使用 递归的中序遍历
    public void stackInorder() {
        stackInorder(root);
    }

    private void stackInorder(TreeNode<E> root) {
        if (null == root) return;
        LinkedList<TreeNode<E>> nodesStack = new LinkedList<>(); // push pop peek search empty
        LinkedList<TreeNode<E>> tempStack = new LinkedList<>();
        TreeNode<E> current = root;
        while (nodesStack.size() != size) { //当 nodesStack 中放满了 元素，即完成了循环
            if (null != current.right) { // 一直找到 BST 的最大值
                tempStack.push(current);   // 就把这个路上先进去的节点放入栈中
                current = current.right;
            } else {
                // 找到了 BST 的最大值
                nodesStack.push(current); // 把最大值压入 nodesStack 栈中
                if (null == current.left && !tempStack.isEmpty()) { // 判断最大值的左节点有没有节点，没有就把最大值父结点加进去
                    current = tempStack.peek();
                    nodesStack.push(tempStack.pop());  // 最大值的父结点也进去
                    while (current.left == null) {  //最大值的左节点为空，
                        current = tempStack.peek();
                        nodesStack.push(tempStack.pop());   //tempStack 的元素 压入 nodes 中
                    }
                    current = current.left; // 如果不为空，current 指向它的左节点
                } else
                    current = current.left;
            }

        }

        for (TreeNode<E> node : nodesStack)
            System.out.print(node.element + "   ");
    }

    @Override
    public Iterator<E> iterator() {
        return new InorderIterator();
    }

    //使用中序遍历，中序遍历的特点就是正序排好序的
    private class InorderIterator implements Iterator<E> {

        private java.util.ArrayList<E> list = new java.util.ArrayList<>();

        //使用了构造函数后，list 里面装的都是中序遍历的结果，然后是ArrayList 面向索引位遍历器遍历
        private int currentIndex; // 当前索引位置

        private int lastReturn = -1; // 上一个所以位置

        private int expectedSizeCount = MyBST.this.size; // 确保使用了遍历器就不能用原来的方法删除。

        public InorderIterator() {
            this.inorder();
        }

        private void inorder() {
            this.inorder(MyBST.this.root);
        }

        private void inorder(TreeNode<E> root) {
            if (root == null) return;
            this.inorder(root.left);
            list.add(root.element);
            this.inorder(root.right);
        }

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public E next() {
            checkForComodification();
            lastReturn = currentIndex;
            if (lastReturn >= size) {
                throw new NoSuchElementException();
            }
            currentIndex++;

            return list.get(lastReturn);
        }

        @Override
        public void remove() {
            checkForComodification();
            if (lastReturn < 0)
                throw new IllegalStateException();

            currentIndex = lastReturn;
            MyBST.this.delete(list.get(lastReturn));
            lastReturn = -1;
            list.clear();
            this.inorder();
        }

        private void checkForComodification() {
            if (expectedSizeCount != size)
                throw new ConcurrentModificationException();
        }
    }

    public static class TreeNode<E extends Comparable<E>> {


        protected E element;
        protected TreeNode<E> left;
        protected TreeNode<E> right;

        public TreeNode(E e) {
            element = e;
        }
    }
}
