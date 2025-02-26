package ru.itmo.tpo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HashTableTest {
    private HashTable table;

    @BeforeEach
    void setUp() {
        table = new HashTable();
    }

    @Test
    void testPutAndGet() {
        table.put(1, 100);
        assertEquals(100, table.get(1));

        table.put(1, 200);
        assertEquals(200, table.get(1));

        assertNull(table.get(0));
    }

    @Test
    void testRemove() {
        table.put(1, 100);
        assertTrue(table.remove(1));
        assertNull(table.get(1));

        assertFalse(table.remove(0));
    }

    @Test
    void testRemoveMiddleElementInCollisionList() {
        int key1 = 1;
        int key2 = key1 + 16;
        int key3 = key1 + 32;
        table.put(key1, 100);
        table.put(key2, 200);
        table.put(key3, 300);
        assertTrue(table.remove(key2));
        assertNull(table.get(key2));
        assertEquals(100, table.get(key1));
        assertEquals(300, table.get(key3));
    }

    @Test
    void testContainsKey() {
        table.put(1, 100);
        assertTrue(table.containsKey(1));
        assertFalse(table.containsKey(2));
    }

    @Test
    void testSize() {
        assertEquals(0, table.size());

        table.put(1, 100);
        table.put(2, 200);
        assertEquals(2, table.size());

        table.remove(1);
        assertEquals(1, table.size());
    }

    @Test
    void testClear() {
        table.put(1, 100);
        table.put(2, 200);
        table.clear();
        assertEquals(0, table.size());
        assertNull(table.get(1));
        assertNull(table.get(2));
    }

    @Test
    void testCollisions() {
        int key1 = 1;
        int key2 = key1 + 16;
        table.put(key1, 100);
        table.put(key2, 200);
        assertEquals(100, table.get(key1));
        assertEquals(200, table.get(key2));
    }

    @Test
    void testLargeNumberOfElements() {
        for (var i = 0; i < 10_000; i++) {
            table.put(i, i * 10);
        }
        for (var i = 0; i < 10_000; i++) {
            assertEquals(i * 10, table.get(i));
        }
    }

    @Test
    void testNegativeKeys() {
        table.put(-1, 50);
        assertEquals(50, table.get(-1));
        assertTrue(table.containsKey(-1));
    }

    @Test
    void testEmptyTableOperations() {
        assertNull(table.get(0));
        assertFalse(table.remove(0));
        assertFalse(table.containsKey(0));
    }

    @Test
    void testOverrideWithSameValue() {
        table.put(1, 100);
        int initialSize = table.size();
        table.put(1, 100);
        assertEquals(initialSize, table.size());
    }
}
