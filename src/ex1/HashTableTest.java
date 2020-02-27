
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

    //A continuación se realizan los juegos de pruebas sobre el metodo Put
    @org.junit.jupiter.api.Test
    void put() {

        //Comprobamos si devuelve algo o está vacio.
        Assertions.assertEquals("", ht.toString());

        //Comprobamos que se inserta la clave-vaor 1-Test
        ht.put("1", "Test");
        Assertions.assertEquals("\n bucket[1] = [1, Test]", ht.toString());

        //COMPROBAMOS LA ANTERIOR CLAVE-VALOR HA SIDO INSERTADA CORRECTAMENTE ADEMÁS DE AÑADIR UNA NUEVA
        ht.put("2", "Uno");
        Assertions.assertEquals("\n bucket[1] = [1, Test]\n bucket[2] = [2, Uno]", ht.toString());

        //COMPROBAMOS LA ANTERIOR CLAVE-VALOR HA SIDO INSERTADA CORRECTAMENTE ADEMÁS DE AÑADIR UNA NUEVA, ESTA VEZ QUE COLISIONE CON EL HASH DE LA ANTERIOR
        ht.put("13", "Trece");
        Assertions.assertEquals("\n bucket[1] = [1, Test]\n bucket[2] = [2, Uno] -> [13, Trece]", ht.toString());

        //COMPROBAMOS QUE LAS ANTERIORES CLAVES-VALOR HAN SIDO CORRECTAMENTE AÑADIDAS ADEMÁS DE ESTA VEZ REEMPLAZAR
        //UNA CLAVE-VALOR QUE COLISIONA CON OTRA Y COMPROBAR QUE SOLO CAMBIA LA QUE QUEREMOS MODIFICAR.
        ht.put("13", "Reemplaza");
        Assertions.assertEquals("\n bucket[1] = [1, Test]\n bucket[2] = [2, Uno] -> [13, Reemplaza]", ht.toString());
    }

    //A CONTINUACIÓN REALIZAMOS LOS JUEGOS DE PRUEBAS SOBRE EL METODO GET
    @org.junit.jupiter.api.Test
    void get() {

        //Comprobamos si al estar vacio o no existir devuelve nulo
        Assertions.assertEquals(null, ht.get(""));

        //Comprobamos si podemos obtener algo nulo
        ht.put("1", null);
        Assertions.assertEquals(null, ht.get("1"));

        //Comprobamos que podemos obtener la primera-clave de la hashtable
        ht.put("1", "Get");
        Assertions.assertEquals("Get", ht.get("1"));

        //Comprobamos que podemos obtener una clave-valor cuando ha sido reemplazada
        ht.put("1", "GetReemplazado");
        Assertions.assertEquals("GetReemplazado", ht.get("1"));

        //Añadimos una segunda clave-valor y comprobamos que la obtiene correctamente
        ht.put("2", "GetDos");
        Assertions.assertEquals("GetDos", ht.get("2"));
    }

    @org.junit.jupiter.api.Test
    void drop() {
    }
}