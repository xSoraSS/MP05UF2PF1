
package ex1;

import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {

    /**
     * Instanciar nuestra hashTable es necesario para realizar los siguientes juegos de prueba.
     */
    HashTable ht = new HashTable();

    /**
     * A continuación se realizan los juegos de pruebas sobre el metodo Size.
     */
    @org.junit.jupiter.api.Test
    void size() {
        /**
         * A continuación insertamos varias clave-valor, reemplazando y haciendo colisionar sus hash para comprobar que el tamaño siempre es correcto.
         * En caso de que un hash colisione con otro (3 && 36) no deberá aumentar el tamaño, lo mismo en caso de ser reemplazados.
         */
        ht.put("1", "Primero");
        ht.put("3", "Segundo");
        ht.put("1", "Reemplazado");
        ht.put("36", "Segundo-Tercero");
        Assertions.assertEquals(2, ht.size());
        ht.put("36", "Segundo-Tercero-Reemplazado");
        ht.put("4", "Segundo-Tercero-Reemplazado");

        Assertions.assertEquals(3, ht.size());

        ht.drop("3");
        Assertions.assertEquals(3, ht.size());
        ht.drop("36");
        Assertions.assertEquals(2, ht.size());

    }

    /**
     * A continuación se realizan los juegos de pruebas sobre el metodo RealSize.
     */
    @org.junit.jupiter.api.Test
    void realSize() {
        /**
         * A continuación insertamos varias clave-valor, reemplazando y haciendo colisionar sus hash para comprobar que el tamaño real siempre sea el mismo predefenido.
         * En cualquiero caso de modificación este valor no deberá aumentar.
         */
        ht.put("1", "Primero");
        ht.put("3", "Segundo");
        ht.put("1", "Reemplazado");
        ht.put("36", "Segundo-Tercero");

        Assertions.assertEquals(16, ht.realSize());
    }

    /**
     * A continuación se realizan los juegos de pruebas sobre el metodo Put.
     */
    @org.junit.jupiter.api.Test
    void put() {

        /**
         * Comprobamos si devuelve algo o está vacio.
         */
        Assertions.assertEquals("", ht.toString());

        /**
         * Comprobamos que se inserta la clave-vaor 1-Test
         * @param key Aquí le pasamos al metodo la clave que deseamos aplicar.
         * @param value Aquí le pasamos al metodo el valor que deseamos aplicar y vincular a dicha clave.
         */
        ht.put("1", "Test");
        Assertions.assertEquals("\n bucket[1] = [1, Test]", ht.toString());

        /**
         * Se inserta una clave-valor que colisiona con el hash de otra clave que todavía no ha sido introducida.
         * Se comprueba que esto es posible y que se guarda en la posición que le corresponde.
         * */
        ht.put("13", "Trece");
        Assertions.assertEquals("\n bucket[1] = [1, Test]\n bucket[2] = [13, Trece]", ht.toString());

        /**
         * Comprobamos que se puede insertar una clave valor con una colisión de hash sin orden alguno.
         */
        ht.put("2", "Uno");
        Assertions.assertEquals("\n bucket[1] = [1, Test]\n bucket[2] = [13, Trece] -> [2, Uno]", ht.toString());

        /** Comprobamos que las anteriores claves siguen insertadas correctamente además de añadir
        * Una clave-valor que colisiona con otra y comprobar que solo cambia la modificada.
         */
        ht.put("13", "Reemplaza");
        Assertions.assertEquals("\n bucket[1] = [1, Test]\n bucket[2] = [13, Reemplaza] -> [2, Uno]", ht.toString());

        /**
         * Se inserta una clave-valor que colisiona con el hash de otra clave que todavía no ha sido introducida.
         * Se comprueba que esto es posible y que se guarda en la posición que le corresponde.
         */
        ht.put("36", "Tres");
        Assertions.assertEquals("\n bucket[1] = [1, Test]\n bucket[2] = [13, Reemplaza] -> [2, Uno]\n bucket[3] = [36, Tres]", ht.toString());
    }

    /**
     * A continuación se realizan los juegos de pruebas sobre el metodo Get.
     */
    @org.junit.jupiter.api.Test
    void get() {

        /**
         * Comprobamos si al no existir (haber sido insertado) devuelve nulo
         */
        Assertions.assertEquals(null, ht.get("1"));

        /**
         * Comprobamos si podemos obtener un valor sin antes haber introducido una serie ordenada.
         * @param key Aquí le especificamos, en el caso del Get, a que clave queremos acceder para retornar su valor.
         * @return El valor vinculado a la clave introducida.
         */
        ht.put("36", "ObtenerSinOrden");
        Assertions.assertEquals("ObtenerSinOrden", ht.get("36"));

        /**
         * Comprobamos que podemos obtener la primera-clave de la hashtable
         */
        ht.put("1", "Get");
        Assertions.assertEquals("Get", ht.get("1"));

        /**
         * Comprobamos que podemos obtener una clave-valor cuando ha sido reemplazada
         */
        ht.put("1", "GetReemplazado");
        Assertions.assertEquals("GetReemplazado", ht.get("1"));

        /**
         * Añadimos una segunda clave-valor y comprobamos que la obtiene correctamente
         */
        ht.put("2", "GetDos");
        Assertions.assertEquals("GetDos", ht.get("2"));

        /**
         * Añadimos una clave-valor con un hash repetido y comprobamos si puede obtenerlo
         */
        ht.put("24", "GetHashRepe");
        Assertions.assertEquals("GetHashRepe", ht.get("24"));

        /**Comprobamos si puede obtener un valor de una clave inexistente pero si de un hash repetido
        *Assertions.assertEquals("GetHashRepe", ht.get("35"));
        *He querido dejar constancia de esta prueba comentandola
         */

    }

    // no borrar si esta vacio

    // no borrar si no encuentra key en esa posicion

    // borrar caso solitario

    // al borrar una entrada que este en medio, remplazar por la siguiente. hay que jugar con el .next y .previous
    // si es el ultimo, .previous.next = null
    // si es el primero, entries[i] =  .next, .next.previous = null

    @org.junit.jupiter.api.Test
    void drop() {
        /**
         * En el primer caso lo que vamos a comprobar es que tras insertar el primero podamos proceder a borrarlo y al obtenerlo con un Get nos devuelva null.
         */
        ht.put("1", "Primero");
        Assertions.assertEquals("\n bucket[1] = [1, Primero]", ht.toString());
        ht.drop("1");
        Assertions.assertEquals(null, ht.get("1"));
        Assertions.assertEquals("", ht.toString());

        ht.drop("1");
        Assertions.assertEquals(null, ht.get("1"));
        Assertions.assertEquals("", ht.toString());

        ht.put("3", "Segundo");
        ht.put("25", "Segundo-Hash");
        ht.put("36", "Tercer-Hash");
        Assertions.assertEquals("\n bucket[3] = [3, Segundo] -> [25, Segundo-Hash] -> [36, Tercer-Hash]", ht.toString());
        ht.drop("3");
        Assertions.assertEquals("Segundo", ht.get("3"));
        Assertions.assertEquals("\n bucket[3] = [36, Tercer-Hash]", ht.toString());

    }
}