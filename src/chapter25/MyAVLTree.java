package chapter25;

import java.util.LinkedList;

public class MyAVLTree<E extends Comparable<E>> extends MyBST<E> {

    public MyAVLTree() {

    }

    public MyAVLTree(E[] objects) {
        super(objects);
    }

    @Override
    public boolean insert(E e) {

        if (super.insert(e)) // 插入成功后，进行结点平衡
            balancePath(e);
        else
            return false;
        return true;
    }

    //更新一个结点的高
    private void updateHeight(AVLTreeNode<E> node) {
        if (null == node.left && null == node.right)
            node.height = 0;
        else if (null == node.left)
            node.height = 1 + ((AVLTreeNode<E>) node.right).height;
        else if (null == node.right)
            node.height = 1 + ((AVLTreeNode<E>) node.left).height;
        else
            node.height = 1 + Math.max(((AVLTreeNode<E>) node.right).height, ((AVLTreeNode<E>) node.left).height);
    }

    //更新一个结点的 size 值
    private void updateSize(AVLTreeNode<E> node) {
        if (null == node.left && null == node.right)
            node.size = 1;
        else if (null == node.left)
            node.size = 1 + ((AVLTreeNode<E>) node.right).size;
        else if (null == node.right)
            node.size = 1 + ((AVLTreeNode<E>) node.left).size;
        else
            node.size = 1 + ((AVLTreeNode<E>) node.left).size + ((AVLTreeNode<E>) node.right).size;
    }

    //从当前结点（只用它的值来找到）到 rootNode 的 path，在这个 path 上的所有结点都需要重新平衡树
    private void balancePath(E e) {
        LinkedList<TreeNode<E>> path = path(e);
        while (path.size() != 0) {
            AVLTreeNode<E> A = (AVLTreeNode<E>) path.removeLast();// 第一个结点即当前结点的平衡（删除当前结点）
            updateHeight(A);
            updateSize(A);
            AVLTreeNode<E> parentOfA = (root == A) ? null : (AVLTreeNode<E>) path.getLast();//（获得当前结点的父结点）
            //对于当前节点的平衡因子
            switch (balanceFactor(A)) {//确定一个 AVL 的平衡字母是L还是R
                case -2: //确定为L
                    if (balanceFactor((AVLTreeNode<E>) A.left) <= 0) // 确定第二个字母为L
                        balanceLL(A, parentOfA);
                    else
                        balanceLR(A, parentOfA);
                    break;
                case +2: //确定为R
                    if (balanceFactor((AVLTreeNode<E>) A.right) >= 0) // 确定第二个字母为R
                        balanceRR(A, parentOfA);
                    else
                        balanceRL(A, parentOfA);
                    break;
            }
        }

    }

    //返回一个结点的平衡因子
    private int balanceFactor(AVLTreeNode<E> node) {
        if (null == node.right) // 右子树为空，说明这个结点的高度为零 或者  高度等于左子树高度加一
            return -node.height;
        else if (null == node.left) // 左子树为空，说明结点的高度为零 或者高度等于右子树高度加一
            return node.height;
        else // 说明左右子树都在，相减即可
            return ((AVLTreeNode<E>) node.right).height - ((AVLTreeNode<E>) node.left).height;
    }

    //LL旋转，满足重量都在左边，即 A 的 balanceFactor 非常轻（-2），而 A.left 为（-1 或者 0）, A.left.left.height 更大
    private void balanceLL(TreeNode<E> A, TreeNode<E> parentOfA) {
        TreeNode<E> B = A.left;
        if (A == root) // 看看 A 是不是根节点，是根节点用 B 代替
            root = B;
        else {  // A 不是根节点，那么 A 的父结点也是要重新指向 B 的
            if (parentOfA.left == A)
                parentOfA.left = B;
            else
                parentOfA.right = B;
        }
        // 此时 A 没有父结点了，B 的父结点为 A 的父结点，A 被“抛弃”了
        A.left = B.right;   // A 的左节点指向 B 的右节点
        B.right = A;    // B 的右节点指向 A
        updateHeight((AVLTreeNode<E>) A);
        updateHeight((AVLTreeNode<E>) B);
        updateSize((AVLTreeNode<E>) A);
        updateSize((AVLTreeNode<E>) B);
    }

    //RR旋转，满足重量都在右边，即 A 的 balanceFactor 是重的（+2），而 A.right 为（1 或 0）, A.right.right.height 更大
    private void balanceRR(TreeNode<E> A, TreeNode<E> parentOfA) {
        TreeNode<E> B = A.right;
        if (A == root)
            root = B;
        else {
            if (parentOfA.left == A)
                parentOfA.left = B;
            else
                parentOfA.right = B;
        }
        // 此时 A 也是一个没有父节点了，B 的父结点是 A 的父结点，A被“抛弃”了
        A.right = B.left;
        B.left = A;
        updateHeight((AVLTreeNode<E>) A);
        updateHeight((AVLTreeNode<E>) B);
        updateSize((AVLTreeNode<E>) A);
        updateSize((AVLTreeNode<E>) B);
    }

    //LR旋转，满足 A 的左边更重。即 A 的 balanceFactor 是重的（-2），A.left 却是 (1 或 0) --> A.left.right.height 更大
    private void balanceLR(TreeNode<E> A, TreeNode<E> parentOfA) {
        TreeNode<E> B = A.left;
        TreeNode<E> C = B.right;
        if (A == root)
            root = C;
        else {
            if (parentOfA.left == A)
                parentOfA.left = C;
            else
                parentOfA.right = C;
        }

        A.left = C.right;
        B.right = C.left;
        C.left = B;
        C.right = A;
        updateHeight((AVLTreeNode<E>) A);
        updateHeight((AVLTreeNode<E>) B);
        updateHeight((AVLTreeNode<E>) C);

        updateSize((AVLTreeNode<E>) A);
        updateSize((AVLTreeNode<E>) B);
        updateSize((AVLTreeNode<E>) C);
    }

    // RL 旋转，右边更重，即 A 的 balanceFactor 是中的（+2），A.right 却是（-1 或者 0） --> A.right.left.height 更大
    private void balanceRL(TreeNode<E> A, TreeNode<E> parentOfA) {
        TreeNode<E> B = A.right;
        TreeNode<E> C = B.left;
        if (A == root)
            root = C;
        else {
            if (parentOfA.left == A)
                parentOfA.left = C;
            else
                parentOfA.right = C;
        }

        A.right = C.left;
        B.left = C.right;
        C.left = A;
        C.right = B;
        updateHeight((AVLTreeNode<E>) A);
        updateHeight((AVLTreeNode<E>) B);
        updateHeight((AVLTreeNode<E>) C);

        updateSize((AVLTreeNode<E>) A);
        updateSize((AVLTreeNode<E>) B);
        updateSize((AVLTreeNode<E>) C);
    }

    @Override
    public boolean delete(E e) {
        if (null == e) return false;
        AVLTreeNode<E> current = (AVLTreeNode<E>) this.root;
        AVLTreeNode<E> parent = null;

        while (null != current) {   // 这么一遍历，current 即是当前元素
            if (e.compareTo(current.element) < 0) { // 树的左边
                parent = current;
                current = (AVLTreeNode<E>) current.left;
            } else if (e.compareTo(current.element) > 0) {
                parent = current;
                current = (AVLTreeNode<E>) current.right;
            } else
                break;
        }

        if (null == current)    // 此时说明 AVLTree 中没有此元素，
            return false;

        // 开始两种情况，第一种，左子树为空
        if (null == current.left) {
            if (root == current)    // 当前结点为 root
                root = current.right;
            else {
                if (parent.left == current)
                    parent.left = current.right;
                else
                    parent.right = current.right;
            }
        } else {    // 左子树不为空，即找到左子树的最大值
            AVLTreeNode<E> mostRight = (AVLTreeNode<E>) current.left;
            AVLTreeNode<E> parentOfMostRight = current;
            while (null != mostRight.right) {
                parentOfMostRight = mostRight;
                mostRight = (AVLTreeNode<E>) mostRight.right;
            }
            current.element = mostRight.element;

            if (parentOfMostRight.left == mostRight)
                parentOfMostRight.left = mostRight.left;
            else
                parentOfMostRight.right = mostRight.left;

            balancePath(parentOfMostRight.element);
        }

        size--;
        return true;
    }

    //找到第 k 小的元素
    public E find(int k) {
        if (k <= 0 || k > size || null == root) return null;
        return find(k, (AVLTreeNode<E>) root);
    }

    private E find(int k, AVLTreeNode<E> root) {
        E findK = null;
        AVLTreeNode<E> current = root;
        boolean flag = false; // 代表未找到
        while (!flag) {
            AVLTreeNode<E> A = (AVLTreeNode<E>) current.left; //当前结点的左右孩子
            AVLTreeNode<E> B = (AVLTreeNode<E>) current.right;

            if (null == A && k == 1) { // 只有根节点根节点，
                flag = true;
                findK = current.element;
            } else if (null == A && k == 2) {
                flag = true;
                findK = B.element;
            } else if (k == A.size + 1) {
                flag = true;
                findK = current.element;
            } else if (k <= A.size) {   // k 的值 为 AVLTree 的前 A.size 名
                current = A;
                A = (AVLTreeNode<E>) current.left;
                B = (AVLTreeNode<E>) current.right;
            } else {
                k = k - A.size - 1; // 代表第 k 小的元素大于 current.left.size + 1,去掉了左子树和 current，迈进了右子树
                                    // 所以 K 的大小得到了改变
                current = B;
                A = (AVLTreeNode<E>) current.left;
                B = (AVLTreeNode<E>) current.right;
            }
        }

        return findK;
    }

    @Override
    protected AVLTreeNode<E> createNewNode(E e) {
        return new AVLTreeNode<>(e);
    }

    public static class AVLTreeNode<E extends Comparable<E>> extends MyBST.TreeNode<E> {

        protected int height; // 这个高是指当前节点到某一叶子结点的最大值或者自己为叶子结点 = 0
        protected int size; // 这个size 是指这个结点拥有多少个子结点 + 1（这个1 是本身）

        public AVLTreeNode(E e) {
            super(e);
        }
    }
}
