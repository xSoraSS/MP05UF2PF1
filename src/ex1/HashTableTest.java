
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

    /**
     * A continuación se realizan los juegos de pruebas sobre el metodo Put
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
         * COMPROBAMOS LA ANTERIOR CLAVE-VALOR HA SIDO INSERTADA CORRECTAMENTE ADEMÁS DE AÑADIR UNA NUEVA
         */
        ht.put("2", "Uno");
        Assertions.assertEquals("\n bucket[1] = [1, Test]\n bucket[2] = [2, Uno]", ht.toString());

        /**
         * COMPROBAMOS LA ANTERIOR CLAVE-VALOR HA SIDO INSERTADA CORRECTAMENTE ADEMÁS DE AÑADIR UNA NUEVA, ESTA VEZ QUE COLISIONE CON EL HASH DE LA ANTERIOR
         */
        ht.put("13", "Trece");
        Assertions.assertEquals("\n bucket[1] = [1, Test]\n bucket[2] = [2, Uno] -> [13, Trece]", ht.toString());

        /**COMPROBAMOS QUE LAS ANTERIORES CLAVES-VALOR HAN SIDO CORRECTAMENTE AÑADIDAS ADEMÁS DE ESTA VEZ REEMPLAZAR
        *UNA CLAVE-VALOR QUE COLISIONA CON OTRA Y COMPROBAR QUE SOLO CAMBIA LA QUE QUEREMOS MODIFICAR.
         */
        ht.put("13", "Reemplaza");
        Assertions.assertEquals("\n bucket[1] = [1, Test]\n bucket[2] = [2, Uno] -> [13, Reemplaza]", ht.toString());
    }

    /**
     * A CONTINUACIÓN REALIZAMOS LOS JUEGOS DE PRUEBAS SOBRE EL METODO GET
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

    @org.junit.jupiter.api.Test
    void drop() {
    }
}