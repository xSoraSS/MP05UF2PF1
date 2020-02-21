package ex1;

import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {

    HashTable ht = new HashTable();

    @org.junit.jupiter.api.Test
    void size() {
    }

    @org.junit.jupiter.api.Test
    void realSize() {
    }

    @org.junit.jupiter.api.Test
    void put() {
        Assertions.assertEquals("", ht.toString());

        ht.put("1", "Test");
        Assertions.assertEquals("\n bucket[1] = [1, Test]", ht.toString());

        ht.put("2", "Uno");
        Assertions.assertEquals("\n bucket[1] = [1, Test]\n bucket[2] = [2, Uno]", ht.toString());

        ht.put("2", "Dos");
        Assertions.assertEquals("\n bucket[1] = [1, Test]\n bucket[2] = [2, Uno] -> [2, Dos]", ht.toString());

        ht.put("2", "Tres");
        Assertions.assertEquals("\n bucket[1] = [1, Test]\n bucket[2] = [2, Tres]", ht.toString());

        System.out.println(ht.get("1") + " " +ht.get("2"));


    }

    @org.junit.jupiter.api.Test
    void get() {
    }

    @org.junit.jupiter.api.Test
    void drop() {
    }
}