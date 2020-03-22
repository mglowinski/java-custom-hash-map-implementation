package com.mglowinski.custom;

public class CustomHashMap<K, V> {

    private static final int INITIAL_CAPACITY = 16;

    private Entry<K, V>[] buckets;

    public CustomHashMap() {
        this(INITIAL_CAPACITY);
    }

    public CustomHashMap(int capacity) {
        this.buckets = new Entry[capacity];
    }

    public void put(K key, V value) {
        int index = index(key);
        Entry<K, V> newEntry = new Entry<>(key, value, null);
        if (buckets[index] == null) {
            buckets[index] = newEntry;
        } else {
            Entry<K, V> previousNode = null;
            Entry<K, V> currentNode = buckets[index];
            while (currentNode != null) {
                if (currentNode.getKey().equals(key)) {
                    currentNode.setValue(value);
                    break;
                }
                previousNode = currentNode;
                currentNode = currentNode.getNext();
            }
            if (previousNode != null) {
                previousNode.setNext(newEntry);
            }
        }
    }

    public V get(K key) {
        int index = index(key);
        Entry<K, V> entry = buckets[index];
        while (entry != null) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
            entry = entry.getNext();
        }
        return null;
    }

    public void remove(K key) {
        int index = index(key);
        Entry<K, V> previousEntry = null;
        Entry<K, V> currentEntry = buckets[index];
        while (currentEntry != null) {
            if (currentEntry.getKey().equals(key)) {
                if (previousEntry == null) {
                    buckets[index] = currentEntry.getNext();
                } else {
                    previousEntry.setNext(currentEntry.getNext());
                }
                return;
            }
            previousEntry = currentEntry;
            currentEntry = currentEntry.getNext();
        }
    }

    public void display() {
        for (int i = 0; i < INITIAL_CAPACITY; i++) {
            if (buckets[i] != null) {
                Entry<K, V> currentNode = buckets[i];
                while (currentNode != null) {
                    System.out.println(String.format("Key is %s and value is %s", currentNode.getKey(), currentNode.getValue()));
                    currentNode = currentNode.getNext();
                }
            }
        }
    }

    private int index(K key) {
        if (key == null) {
            return 0;
        }
        return Math.abs(key.hashCode() % INITIAL_CAPACITY);
    }

}
