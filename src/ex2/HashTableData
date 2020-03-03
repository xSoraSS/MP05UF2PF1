package ex2;

public class HashTableData {

    public static void main(String[] args) {
        HashTable hashTable = new HashTable();

        System.out.println(hashTable.getCollisionsForKey("3", 9));
        // Put some key values.
        for (int i = 0; i < 30; i++) {
            final String key = String.valueOf(i);
            hashTable.put(key, key);
        }

        // Print the HashTable structure
        log("****   HashTable  ***");
        log(hashTable.toString());
        log("\nValue for key(20) : " + hashTable.get("20"));
    }

    static void log(String msg) {
        System.out.println(msg);
    }

    public HashTableData() {
        super();
    }
}
