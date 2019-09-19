package chapter25;

public interface MyTree<E> extends Iterable<E> {
    boolean search(E e);

    boolean insert(E e);

    boolean delete(E e);

    /**
     * 前序，中序，后序都属于深度优先遍历
     */
    //前序遍历
    void preorder();

    //中序遍历
    void inorder();

    //后序遍历
    void postorder();

    /**
     * 广度优先遍历，一层一层遍历，每一层又是从左到右遍历
     * @return
     */

    void breadthFirstTraversal();

    int getSize();

    int getHeight();

    boolean isEmpty();

    void clear();
}
