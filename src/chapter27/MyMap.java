package chapter27;


public interface MyMap<K, V> {

    void clear();

    boolean containsKey(K key);

    boolean containsValue(V value);

    java.util.Set<Entry<K, V>> entrySet();

    V get(K key);

    boolean isEmpty();

    java.util.Set<K> keySet();

    V put(K key, V value);

    void remove(K key);

    int size();

    java.util.Set<V> valueSet();


    //Entry 条目， 一个条目包括一个 Key  --  Value
    class Entry<K, V> {

        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }
}
