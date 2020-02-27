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

        ht.put("13", "Trece");
        Assertions.assertEquals("\n bucket[1] = [1, Test]\n bucket[2] = [2, Uno] -> [13, Trece]", ht.toString());

        ht.put("13", "Reemplaza");
        Assertions.assertEquals("\n bucket[1] = [1, Test]\n bucket[2] = [2, Uno] -> [13, Reemplaza]", ht.toString());

        System.out.println(ht.get("1") + " " +ht.get("2"));


    }

    @org.junit.jupiter.api.Test
    void get() {

        ht.put("1", "Get");
        Assertions.assertEquals("Get", ht.get("1"));

    }

    @org.junit.jupiter.api.Test
    void drop() {
    }
}