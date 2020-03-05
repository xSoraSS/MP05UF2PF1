package ex3;

import org.junit.jupiter.api.Assertions;

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
         * A continuación insertamos varias clave-valor, reemplazando y haciendo colisionar sus hash para comprobar que el tamaño siempre es correcto. En caso de que un
         * hash colisione con otro (3 && 36) no deberá aumentar el tamaño, lo mismo en caso de ser reemplazados.
         */
        ht.put("1", "Primero");
        ht.put("3", "Segundo");
        ht.put("14", "Hola");
        ht.put("1", "Reemplazado");
        ht.put("36", "Segundo-Tercero");

        /**
         * Tras reemplazar y añadir una colisión comprobamos que el tamaño es correcto. Comprobamos que el tamaño sea 2.
         */
        Assertions.assertEquals(2, ht.size());

        ht.put("36", "Segundo-Reemplazado");
        ht.put("4", "Cuarto");
        Assertions.assertEquals("\n bucket[1] = [1, Reemplazado]\n bucket[3] = [3, Segundo] -> [14, Hola] -> [36, Segundo-Reemplazado]\n bucket[4] = [4, Cuarto]", ht.toString());

        /**
         * Volvemos a añadir un elemento y a reemplazar una colisión. Comprobamos que el tamaño sea 3.
         */

        Assertions.assertEquals(3, ht.size());

        /**
         * Ahora eliminamos un elemento que colisione y comprobamos que el tamaño se mantiene igual.
         */
        ht.drop("3");
        Assertions.assertEquals(3, ht.size());
        Assertions.assertEquals("\n bucket[1] = [1, Reemplazado]\n bucket[3] = [14, Hola] -> [36, Segundo-Reemplazado]\n bucket[4] = [4, Cuarto]", ht.toString());

        /**
         * A continuación eliminamos definitivamente la colisión (el elemento que colisionaba con el anterior) y comprobamos que el tamaño se reduce.
         */
        ht.drop("36");
        Assertions.assertEquals(2, ht.size());

        ht.put("3", "ASD");
        ht.put("14", "aa");
        ht.put("25", "DAS");

        /**
         * Tras añadir tres elementos de los cuales sus hash colisionan comprobamos que se pueda eliminar el de en medio sin reducir su tamaño.
         */
        ht.drop("25");
        ht.drop("3");
        ht.drop("14");
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
        ht.drop("1");

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
         * Probamos a insertar una clave con un valor nulo.
         */
        ht.put("3", null);
        Assertions.assertEquals("\n bucket[3] = [3, null]", ht.toString());
        /**
         * Aquí lo eliminamos para no tener que añadirlo al resto de comprobaciones, ya que ha sido añadido en último momento.
         */
        ht.drop("3");

        /**
         * A continuación se realiza la prueba de insertar una clave-valor nula y como es lógico debería de petar. Dejo constancia de la prueba a continuación.
         */
        // ht.put(null, null);

        /**
         * Comprobamos que se inserta la clave-vaor 1-Test
         *
         * @param key Aquí le pasamos al metodo la clave que deseamos aplicar.
         * @param value Aquí le pasamos al metodo el valor que deseamos aplicar y vincular a dicha clave.
         */
        ht.put("1", "Test");
        Assertions.assertEquals("\n bucket[1] = [1, Test]", ht.toString());

        /**
         * Se inserta una clave-valor que colisiona con el hash de otra clave que todavía no ha sido introducida. Se comprueba que esto es posible y que se guarda en la
         * posición que le corresponde.
         */
        ht.put("13", "Trece");
        Assertions.assertEquals("\n bucket[1] = [1, Test]\n bucket[2] = [13, Trece]", ht.toString());

        /**
         * Comprobamos que se puede insertar una clave valor con una colisión de hash sin orden alguno.
         */
        ht.put("2", "Uno");
        Assertions.assertEquals("\n bucket[1] = [1, Test]\n bucket[2] = [13, Trece] -> [2, Uno]", ht.toString());

        /**
         * Comprobamos que las anteriores claves siguen insertadas correctamente además de añadir Una clave-valor que colisiona con otra y comprobar que solo cambia la
         * modificada.
         */
        ht.put("13", "Reemplaza");
        Assertions.assertEquals("\n bucket[1] = [1, Test]\n bucket[2] = [13, Reemplaza] -> [2, Uno]", ht.toString());

        /**
         * Se inserta una clave-valor que colisiona con el hash de otra clave que todavía no ha sido introducida. Se comprueba que esto es posible y que se guarda en la
         * posición que le corresponde.
         */
        ht.put("36", "Tres");
        Assertions.assertEquals("\n bucket[1] = [1, Test]\n bucket[2] = [13, Reemplaza] -> [2, Uno]\n bucket[3] = [36, Tres]", ht.toString());

        /**
         * Tras haber realizado la modificación en el código para poder añadir objetos, se ha decidido crear una clase, ObjetoRandom. A continuación se realiza la primera
         * prueba con Objetos. Se ha tenido que añadir + object + al assetEquals para poder obtener el objeto@id que tenga autoasignado.
         */
        ObjetoRandom object = new ObjetoRandom("Funciona");
        ht.put("5", object);
        Assertions.assertEquals("\n bucket[1] = [1, Test]\n bucket[2] = [13, Reemplaza] -> [2, Uno]\n bucket[3] = [36, Tres]\n bucket[5] = [5, " + object + "]", ht.toString());

        /**
         * A continuación intentaremos insertar el String dentro del objeto y comprobaremos que se haya insertado.
         */
        ht.put("16", object.getNombre());
        Assertions.assertEquals("\n bucket[1] = [1, Test]\n bucket[2] = [13, Reemplaza] -> [2, Uno]\n bucket[3] = [36, Tres]\n bucket[5] = [5, " + object + "] -> [16, Funciona]", ht.toString());

        /**
         * A continuación los eliminamos y comprobamos que se haya realizado.
         */
        ht.drop("16");
        ht.drop("5");
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
        Assertions.assertEquals(null, ht.get(""));

        /**
         * Realizamos la misma comprobación pero utilizando una Key inexistente.
         */
        Assertions.assertEquals(null, ht.get("1"));

        /**
         * Comprobamos si podemos obtener un valor sin antes haber introducido una serie ordenada.
         *
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

        /**
         * Comprobamos si puede obtener un valor de una clave inexistente pero si de un hash repetido. He querido dejar constancia de esta prueba comentandola.
         */

        // Assertions.assertEquals("GetHashRepe", ht.get("35"));
    }

    @org.junit.jupiter.api.Test
    void drop() {

        /**
         * En el primer caso vamos a comprobar si hace algo al intentar eliminar algo vacio.
         */
        ht.drop("");
        /**
         * Como es lógico no podrá eliminar nada null, aún así dejamos constancia en la siguiente linea de la prueba realizada.
         */
        // ht.drop(null);

        /**
         * Vamos a comprobar que, tras insertar el primero podamos proceder a borrarlo y al obtenerlo con un Get nos devuelva null, nada.
         */
        ht.put("1", "Primero");
        Assertions.assertEquals("\n bucket[1] = [1, Primero]", ht.toString());
        ht.drop("1");
        Assertions.assertEquals(null, ht.get("1"));
        Assertions.assertEquals("", ht.toString());

        /**
         * Constancia de que se ha realizado la prueba para comprobar que se ha eliminado correctamente.
         */
        // Assertions.assertEquals("Primero", ht.get("1"));
        // Assertions.assertEquals("Primero", ht.toString());

        /**
         * A continuación se realiza la prueba para comprobar que se pueda borrar el último sin borrar el resto.
         */
        ht.put("3", "Segundo");
        ht.put("25", "Segundo-Hash");
        ht.put("36", "Tercer-Hash");
        Assertions.assertEquals("\n bucket[3] = [3, Segundo] -> [25, Segundo-Hash] -> [36, Tercer-Hash]", ht.toString());

        ht.drop("36");

        /**
         * A conitnuación se realizan las siguiente pruebas para comprobar que se haya eliminado correctamente.
         */
        Assertions.assertEquals("Segundo-Hash", ht.get("25"));
        Assertions.assertEquals("Segundo", ht.get("3"));
        Assertions.assertEquals("\n bucket[3] = [3, Segundo] -> [25, Segundo-Hash]", ht.toString());

        /**
         * A continuación se realiza la prueba para comprobar que se pueda borrar el primero sin borrar el resto.
         */
        ht.put("36", "Tercer-Hash");
        Assertions.assertEquals("\n bucket[3] = [3, Segundo] -> [25, Segundo-Hash] -> [36, Tercer-Hash]", ht.toString());
        ht.drop("3");

        Assertions.assertEquals("Segundo-Hash", ht.get("25"));
        Assertions.assertEquals("Tercer-Hash", ht.get("36"));
        Assertions.assertEquals("\n bucket[3] = [25, Segundo-Hash] -> [36, Tercer-Hash]", ht.toString());

        /**
         * A continuación se realiza la prueba para comprobar que se pueda borrar el que se encuentre en el medio sin borrar el resto.
         */
        ht.put("3", "Segundo");
        Assertions.assertEquals("\n bucket[3] = [25, Segundo-Hash] -> [36, Tercer-Hash] -> [3, Segundo]", ht.toString());
        ht.drop("25");

        Assertions.assertEquals("Segundo", ht.get("3"));
        Assertions.assertEquals("Tercer-Hash", ht.get("36"));
        Assertions.assertEquals("\n bucket[3] = [36, Tercer-Hash] -> [3, Segundo]", ht.toString());
    }
}
