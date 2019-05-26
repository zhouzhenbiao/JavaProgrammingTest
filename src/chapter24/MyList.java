package chapter24;

public interface MyList<E> extends Iterable<E> {

    //增
    public void add(E e);

    public void add(int index, E e);

    //删
    public boolean remove(E e);

    public E remove(int index);

    //查
    public boolean contains(E e);

    public int indexOf(E e);

    public int lastIndexOf(E e);

    public boolean isEmpty();

    public int size();

    //gai
    public E set(int index, E e);

    //获取
    public E get(int index);

    //清空
    public void clear();


}
