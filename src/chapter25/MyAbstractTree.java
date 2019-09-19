package chapter25;

public abstract class MyAbstractTree<E> implements MyTree<E> {

    @Override
    public void inorder() {

    }

    @Override
    public void postorder() {

    }

    @Override
    public void preorder() {

    }

    @Override
    public boolean isEmpty() {
        return getSize() == 0;
    }
}
