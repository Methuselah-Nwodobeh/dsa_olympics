package org.example.hashmaps;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/*
    This is an implementation of the actual hashmap in java. Although unlike it, this doesn't implement AbstractHashMap
    It works just like the java hashmap.
    Author: ChatGPT
 */
public class ActualJavaHashMap<K, V> {
    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    private List<LinkedList<Entry<K, V>>> buckets;
    private int size;

    public ActualJavaHashMap() {
        buckets = new ArrayList<>(INITIAL_CAPACITY);
        for (int i = 0; i < INITIAL_CAPACITY; i++) {
            buckets.add(new LinkedList<>());
        }
        size = 0;
    }

    public void put(K key, V value) {
        int index = getIndex(key);
        LinkedList<Entry<K, V>> bucket = buckets.get(index);

        for (Entry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                entry.setValue(value);
                return;
            }
        }

        bucket.add(new Entry<>(key, value));
        size++;

        if ((double) size / buckets.size() > LOAD_FACTOR) {
            resize();
        }
    }

    public V get(K key) {
        int index = getIndex(key);
        LinkedList<Entry<K, V>> bucket = buckets.get(index);

        for (Entry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }

        return null;
    }

    public boolean containsKey(K key) {
        int index = getIndex(key);
        LinkedList<Entry<K, V>> bucket = buckets.get(index);

        for (Entry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                return true;
            }
        }

        return false;
    }

    public V remove(K key) {
        int index = getIndex(key);
        LinkedList<Entry<K, V>> bucket = buckets.get(index);

        Iterator<Entry<K, V>> iterator = bucket.iterator();
        while (iterator.hasNext()) {
            Entry<K, V> entry = iterator.next();
            if (entry.getKey().equals(key)) {
                iterator.remove();
                size--;
                return entry.getValue();
            }
        }

        return null;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");

        for (LinkedList<Entry<K, V>> bucket : buckets) {
            for (Entry<K, V> entry : bucket) {
                sb.append(entry.getKey()).append(":").append(entry.getValue()).append(", ");
            }
        }

        if (size > 0) {
            sb.setLength(sb.length() - 2); // Remove the trailing comma and space
        }

        sb.append("}");
        return sb.toString();
    }

    private int getIndex(K key) {
        return Math.abs(key.hashCode() % buckets.size());
    }

    private void resize() {
        int newCapacity = buckets.size() * 2;
        List<LinkedList<Entry<K, V>>> newBuckets = new ArrayList<>(newCapacity);

        for (int i = 0; i < newCapacity; i++) {
            newBuckets.add(new LinkedList<>());
        }

        for (LinkedList<Entry<K, V>> bucket : buckets) {
            for (Entry<K, V> entry : bucket) {
                int newIndex = Math.abs(entry.getKey().hashCode() % newCapacity);
                newBuckets.get(newIndex).add(entry);
            }
        }

        buckets = newBuckets;
    }

    private static class Entry<K, V> {
        private final K key;
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
    }
}
