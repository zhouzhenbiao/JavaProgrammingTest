package chapter27;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class MyHashMap<K, V> implements MyMap<K, V> {
    // 默认的 hash-table 的 size ，必须是 2^n次幂
    private static int DEFAULT_INITIAL_CAPACITY = 4;

    // 默认的 hash-table size 1 << 30 即 2^0 * 2* 30 = 2^30
    private static int MAXIMUM_CAPACITY = 1 << 30;

    // current hash-table capacity, 2^n 次幂
    private int capacity;

    // 默认的装填因子 load factor
    private static float DEFAULT_MAX_LOAD_FACTOR = 0.75f;

    // Specify(需求) a load factor used in the hash-table
    private float loadFactorThreshold; //装填 因子 阈值

    private int size;

    private LinkedList<MyMap.Entry<K, V>>[] table;

    // 创建一个默认的 hash-table
    public MyHashMap() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);
    }

    // 指定 initialCapacity （初始容量）
    public MyHashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_MAX_LOAD_FACTOR);
    }

    // 指定 initialCapacity （初始容量） 和 loadFactorThreshold 装填因子阈值
    public MyHashMap(int initialCapacity, float loadFactorThreshold) {
        // 必须保证 initialCapacity 是 2^n 次幂
        if (initialCapacity > DEFAULT_INITIAL_CAPACITY)
            initialCapacity = DEFAULT_INITIAL_CAPACITY;
        else
            initialCapacity = trimToPowerOf2(initialCapacity);
        this.capacity = initialCapacity;
        this.loadFactorThreshold = loadFactorThreshold;
        table = new LinkedList[this.capacity];
    }

    @Override
    public void clear() {
        size = 0;
        removeEntries();
    }

    @Override
    public boolean containsKey(K key) {
        if (null != get(key))
            return true;
        else
            return false;
    }

    @Override
    public boolean containsValue(V value) {
        for (int bucketIndex = 0; bucketIndex < capacity; bucketIndex++) {
            if (null != table[bucketIndex]) {
                LinkedList<Entry<K, V>> bucket = table[bucketIndex];
                for (Entry<K, V> entry : bucket)
                    if (entry.getValue().equals(value))
                        return true;
            }

        }

        return false;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        HashSet<Entry<K, V>> hashSet = new HashSet<>();

        for (int bucketIndex = 0; bucketIndex < capacity; bucketIndex++) {
            if (null != table[bucketIndex]) {
                LinkedList<Entry<K, V>> bucket = table[bucketIndex];
                for (Entry<K, V> entry : bucket)
                    hashSet.add(entry);
            }
        }

        return hashSet;
    }

    @Override
    public V get(K key) {
        int bucketIndex = hash(key.hashCode()); // bucket 桶
        if (null != table[bucketIndex]) {
            LinkedList<Entry<K, V>> bucket = table[bucketIndex];
            for (Entry<K, V> entry : bucket)
                if (entry.getKey().equals(key))
                    return entry.getValue();
        }

        return null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Set<K> keySet() {
        HashSet<K> hashSet = new HashSet<>();

        for (int bucketIndex = 0; bucketIndex < capacity; bucketIndex++) {
            if (null != table[bucketIndex]) {
                LinkedList<Entry<K, V>> bucket = table[bucketIndex];
                for (Entry<K, V> entry : bucket)
                    hashSet.add(entry.getKey());
            }
        }

        return hashSet;
    }

    @Override
    public V put(K key, V value) {
        if (null != get(key)) {
            int bucketIndex = hash(key.hashCode());

            LinkedList<Entry<K, V>> bucket = table[bucketIndex];
            for (Entry<K, V> entry : bucket)
                if (entry.getKey().equals(key)) {
                    V oldValue = entry.getValue();
                    entry.setValue(value);

                    return oldValue;
                }
        }

        if (size >= capacity * loadFactorThreshold) {
            if (capacity == MAXIMUM_CAPACITY)
                throw new RuntimeException("Exceeding maximum capacity");
            rehash();
        }

        int bucketIndex = hash(key.hashCode());

        if (null == table[bucketIndex])
            table[bucketIndex] = new LinkedList<>();

        table[bucketIndex].add(new Entry<>(key, value));

        size++;

        return value;
    }

    @Override
    public void remove(K key) {
        int bucketIndex = hash(key.hashCode());

        if (null != table[bucketIndex] && table[bucketIndex].size() > 0) {
            LinkedList<Entry<K, V>> bucket = table[bucketIndex];
            for (Entry<K, V> entry : bucket)
                if (entry.getKey().equals(key)) {
                    bucket.remove(entry);
                    size--;
                    break;
                }
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Set<V> valueSet() {
        HashSet<V> hashSet = new HashSet<>();

        for (int bucketIndex = 0; bucketIndex < capacity; bucketIndex++) {
            if (null != table[bucketIndex] && table[bucketIndex].size() > 0) {
                LinkedList<Entry<K, V>> bucket = table[bucketIndex];
                for (Entry<K, V> entry : bucket)
                    hashSet.add(entry.getValue());
            }
        }
        return hashSet;
    }

    /**
     * Hash Function
     */

    // 进来一个 对象的 hashCode, 返回一个 压缩散列码
    private int hash(int hashCode) {
        return supplementalHash(hashCode) & (capacity - 1);
    }

    // 补充的 hash 函数，得到一个分布均匀的 散列码
    private static int supplementalHash(int h) {
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    //裁切到 2^n 次幂  例如，initialCapacity = 7, return 2*3;
    private int trimToPowerOf2(int initialCapacity) {
        int capacity = 1;
        while (capacity < initialCapacity)
            capacity = capacity << 1; // capacity * 2^1，每次都乘以 2
        return capacity;
    }

    // 通过遍历删除所有元素
    private void removeEntries() {
        for (int bucketIndex = 0; bucketIndex < capacity; bucketIndex++)
            if (null != table[bucketIndex])
                table[bucketIndex].clear();
    }

    // 当 loadFactor 比较大的时候，通过 rehash 到一个更大的散列表
    private void rehash() {
        Set<Entry<K, V>> entrySet = entrySet();
        capacity = capacity << 1; // 容量 * 2^1;
        table = new LinkedList[capacity];
        size = 0;

        for (Entry<K, V> entry : entrySet)
            put(entry.getKey(), entry.getValue());
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");
        for (int bucketIndex = 0; bucketIndex < capacity; bucketIndex++) {
            if (null != table[bucketIndex] && table[bucketIndex].size() > 0) {
                LinkedList<Entry<K, V>> bucket = table[bucketIndex];
                for (Entry<K, V> entry : bucket)
                    stringBuilder.append(entry);
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
