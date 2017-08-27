package co.edu.udea.pruebas_ps2.ldl;

import co.edu.udea.pruebas_ps2.modelo.Tupla;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Es la clase que contiene las pruebas unitarias para la clase NodoDoble.java
 * @author Frank Castrillón - castrillonfrank114@gmail.com
 * @date 2017/08/27
 * @version v1
 */
public class NodoDobleTest {

    
    /**
     * Test del método getDato, de la clase NodoDoble.
     */
    @Test
    public void testObtenerDato() {
        Tupla tupla = new Tupla(Double.NaN, Double.NaN);
        NodoDoble instancia = new NodoDoble(tupla);
        assertEquals(instancia.getDato(), tupla);
    }

    /**
     *  Test del método setDato, de la clase NodoDoble.
     */
    @Test
    public void testAsignarDato() {
        Tupla tupla = new Tupla(Double.NaN, Double.NaN);
        NodoDoble instancia = new NodoDoble();
        instancia.setDato(tupla);
        assertEquals(instancia.getDato(), tupla);
    }

    /**
     *  Test del método getSiguiente, de la clase NodoDoble.
     */
    @Test
    public void testGetSiguiente() {
        NodoDoble siguiente = new NodoDoble();
        NodoDoble instancia = new NodoDoble(siguiente, null, null);
        assertEquals(instancia.getSiguiente(), siguiente);
    }

    /**
     * Test del método setSiguiente, de la clase NodoDoble.
     */
    @Test
    public void testSetSiguiente() {
        NodoDoble siguiente = new NodoDoble();
        NodoDoble instancia = new NodoDoble();
        instancia.setSiguiente(siguiente);
        assertEquals(instancia.getSiguiente(), siguiente);
    }

    /**
     * Test del método getAnterior, de la clase NodoDoble.
     */
    @Test
    public void testGetAnterior() {
        NodoDoble anterior = new NodoDoble();
        NodoDoble instancia = new NodoDoble(null, anterior, null);
        assertEquals(instancia.getAnterior(), anterior);
    }

    /**
     * Test del método setAnterior, de la clase NodoDoble.
     */
    @Test
    public void testSetAnterior() {
        NodoDoble anterior = new NodoDoble();
        NodoDoble instancia = new NodoDoble();
        instancia.setAnterior(anterior);
        assertEquals(instancia.getAnterior(), anterior);
    }
  
}