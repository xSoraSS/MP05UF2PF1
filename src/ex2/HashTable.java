package ex2;

import java.util.ArrayList;

public class HashTable extends HashTableData {
    private int INITIAL_SIZE = 16;
    private int size = 0;
    private HashEntry[] entries = new HashEntry[INITIAL_SIZE];

    public int size() {
        /**
         * Siempre que haya una modificación, que no sean colisiones o sobreescriptura, este valor deberá variar.
         *
         * @param size
         */
        return this.size;
    }

    public int realSize() {
        /**
         * En cualquier caso de modificación este tamaño no debe aumentar.
         *
         * @param INITIAL_SIZE Tamaño Real.
         */
        return this.INITIAL_SIZE;
    }

    public void put(String key, String value) {
        int hash = getHash(key);
        HashEntry hashEntry = new HashEntry(key, value);

        /**
         * REFACCIÓ: Se realiza a continuación para una mejor compresión de lo se lleva a cabo en este metodo. Como al llamar al Put lo que hace es comprobar si debe
         * insertar directamente, existen colisiones o debe sobreescribir la mejor forma de comprender esto Es separarlo en uno o varios metodos con el nombre de lol que
         * hará.
         */
        comprobarInsertado(key, value, hash, hashEntry);
    }

    private void comprobarInsertado(String key, String value, int hash, HashEntry hashEntry) {
        if (entries[hash] == null) {
            entries[hash] = hashEntry;
            /**
             * ERROR: A continuación se ha añadido el size++ dentro del metodo Put para que cada vez que se inserte una nueva entrada esta se sume al tamaño.
             *
             * @param size Almacena el tamaño actual de la hashtable.
             */
            size++;

        } else {
            HashEntry temp = entries[hash];

            /**
             * ERROR: #se ha tenido que añadir las siguientes lineas para poder sobreescribir valores en el Put del Hash.
             */
            if (temp.key.equals(key)) {
                temp.value = value;
            } else {
                while (temp.next != null) {
                    temp = temp.next;
                    if (temp.key.equals(key)) {
                        temp.value = value;
                        hashEntry = null;
                    }
                }

                if (hashEntry != null) {
                    temp.next = hashEntry;
                    hashEntry.prev = temp;
                }
            }
        }
    }

    /**
     * Returns 'null' if the element is not found.
     */
    public String get(String key) {
        int hash = getHash(key);
        if (entries[hash] != null) {
            HashEntry temp = entries[hash];

            while (!temp.key.equals(key)) {
                temp = temp.next;
            }
            return temp.value;
        }

        return null;
    }

    public void drop(String key) {
        int hash = getHash(key);
        if (entries[hash] != null) {

            HashEntry temp = entries[hash];
            while (!temp.key.equals(key)) {
                temp = temp.next;
            }

            /**
             * REFACCIÓ: Aquí encontramos el mismo caso que con el metodo Put. Al haber una gran cantidad de código se puede volver complejo averiguar que hace esta parte
             * así que lo extraemos como metodo.
             */
            comprobarEliminado(key, hash, temp);
        }
    }

    private void comprobarEliminado(String key, int hash, HashEntry temp) {
        if (temp.prev == null) {
            /**
             * ERROR: A continuación se ha realizado la siguiente modificación para que al eliminar el primer elemento no elimine el resto que puedan colisionar.
             */
            entries[hash] = temp.next; // esborrar element únic (no col·lissió)
            /**
             * Se ha añadido la siguiente comprobación para modificar el tamaño.
             */
            if (temp.next == null) {
                size--;
            }
        } else {
            if (temp.next != null) {
                /**
                 * ERROR: La siguiente comrobación ha sido añadida para poder eliminar el elemento localizado entre principio y final sin eliminar el resto.
                 */
                if (temp.key.equals(key)) {
                    entries[hash] = temp.next;
                    System.out.println("\nNEXT*****");
                } else {
                    entries[hash] = temp.prev; // esborrem temp, per tant actualitzem l'anterior al següent
                }
            } else if (temp.next == null) {
                if (temp.key.equals(key)) {
                    temp.prev.next = temp.next; // esborrem temp, per tant actualitzem l'anterior al següent
                } else {
                    entries[hash] = temp.next;
                }
            }
        }
    }

    private int getHash(String key) {
        // piggy backing on java string
        // hashcode implementation.
        return key.hashCode() % INITIAL_SIZE;
    }

    private class HashEntry {
        String key;
        String value;

        // Linked list of same hash entries.
        HashEntry next;
        HashEntry prev;

        public HashEntry(String key, String value) {
            this.key = key;
            this.value = value;
            this.next = null;
            this.prev = null;
        }

        @Override
        public String toString() {
            return "[" + key + ", " + value + "]";
        }
    }

    @Override
    public String toString() {
        int bucket = 0;
        StringBuilder hashTableStr = new StringBuilder();
        for (HashEntry entry : entries) {
            if (entry == null) {
                bucket++;
                continue;
            }
            hashTableStr.append("\n bucket[").append(bucket).append("] = ").append(entry.toString());
            bucket++;
            HashEntry temp = entry.next;
            while (temp != null) {
                hashTableStr.append(" -> ");
                hashTableStr.append(temp.toString());
                temp = temp.next;
            }
        }
        return hashTableStr.toString();
    }

    public ArrayList<String> getCollisionsForKey(String key) {
        return getCollisionsForKey(key, 1);
    }

    public ArrayList<String> getCollisionsForKey(String key, int quantity) {
        /*
         * Main idea: alphabet = {0, 1, 2} Step 1: "000" Step 2: "001" Step 3: "002" Step 4: "010" Step 5: "011" ... Step N: "222" All those keys will be hashed and
         * checking if collides with the given one.
         */

        final char[] alphabet = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
        ArrayList<Integer> newKey = new ArrayList();
        ArrayList<String> foundKeys = new ArrayList();

        newKey.add(0);
        int collision = getHash(key);
        int current = newKey.size() - 1;

        while (foundKeys.size() < quantity) {
            // building current key
            String currentKey = "";
            for (int i = 0; i < newKey.size(); i++) {
                currentKey += alphabet[newKey.get(i)];
            }

            if (!currentKey.equals(key) && getHash(currentKey) == collision) {
                foundKeys.add(currentKey);
            }

            // increasing the current alphabet key
            newKey.set(current, newKey.get(current) + 1);

            // overflow over the alphabet on current!
            if (newKey.get(current) == alphabet.length) {
                int previous = current;
                do {
                    // increasing the previous to current alphabet key
                    previous--;
                    if (previous >= 0) {
                        newKey.set(previous, newKey.get(previous) + 1);
                    }
                } while (previous >= 0 && newKey.get(previous) == alphabet.length);

                // cleaning
                for (int i = previous + 1; i < newKey.size(); i++) {
                    newKey.set(i, 0);
                }

                // increasing size on underflow over the key size
                if (previous < 0) {
                    newKey.add(0);
                }

                current = newKey.size() - 1;
            }
        }

        return foundKeys;
    }

    /**
     * REFACCIÓ; A continuación se ha realizado una extracción de clase del metodo main() para que esta clase no se llame a sí misma. La clase se ha llamado HashTableData
     * en sentido que creará la tabla. Dejo constancia con el siguiente comentario para indicar que el metodo se encontraba localizado aquí.
     */
    // public HashTableData hashTableData = new HashTableData();
}
