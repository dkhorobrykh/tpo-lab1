package ru.itmo.tpo;

import java.util.LinkedList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class HashTable {
    @AllArgsConstructor
    @Getter
    @Setter
    private static class Entry {
        int key;
        int value;
    }

    private static final int DEFAULT_CAPACITY = 16;
    private final LinkedList<Entry>[] table;
    private int size;

    @SuppressWarnings("unchecked")
    public HashTable(int capacity) {
        table = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            table[i] = new LinkedList<>();
        }
        size = 0;
    }

    public HashTable() {
        this(DEFAULT_CAPACITY);
    }

    private int hash(int key) {
        return Math.abs(key) % table.length;
    }

    public void put(int key, int value) {
        int index = hash(key);
        for (Entry entry : table[index]) {
            if (entry.key == key) {
                entry.value = value;
                return;
            }
        }
        table[index].add(new Entry(key, value));
        size++;
    }

    public Integer get(int key) {
        int index = hash(key);
        for (var entry : table[index]) {
            if (entry.key == key) {
                return entry.value;
            }
        }
        return null;
    }

    public boolean remove(int key) {
        int index = hash(key);
        var iterator = table[index].iterator();
        while (iterator.hasNext()) {
            if (iterator.next().key == key) {
                iterator.remove();
                size--;
                return true;
            }
        }
        return false;
    }

    public boolean containsKey(int key) {
        return get(key) != null;
    }

    public int size() {
        return size;
    }

    public void clear() {
        for (var list : table) {
            list.clear();
        }
        size = 0;
    }
}
