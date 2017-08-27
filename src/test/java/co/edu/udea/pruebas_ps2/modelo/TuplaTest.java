package co.edu.udea.pruebas_ps2.modelo;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Es la clase que contiene las pruebas unitarias para la clase Tupla.java
 * @author Frank Castrillón - castrillonfrank114@gmail.com
 * @date 2017/08/27
 * @version v1
 */
public class TuplaTest {
    
    public TuplaTest() {
    }
    
    /**
     * Test para asignar un desbordamiento de memoria
     */
    @Test
    public void testAgregarDatoGigante(){
        Double x = Double.MAX_VALUE*Double.MAX_VALUE;
        Double y = Double.MAX_VALUE*Double.MAX_VALUE*Double.MAX_VALUE;
        Tupla tupla = new Tupla(x, y);
    }
   
    /**
     * Método que indica cuando se desea ingresar un string 
     */
    @Test(expected = NumberFormatException.class)
    public void testSetDatoStringX() {
        Tupla instancia = new Tupla();
        instancia.setX(new Double("sadasda"));
    }
    
    /**
     * Método que indica cuando se desea ingresar un string 
     */
    @Test(expected = NumberFormatException.class)
    public void testSetDatoStringY() {
        Tupla instancia = new Tupla();
        instancia.setY(new Double("sadasda"));
    }
    
    /**
     * Test para el contructor de la clase con parametros.
     */
    @Test
    public void testConstructorConParametros() {
        Tupla instancia = new Tupla(Double.NaN, Double.NaN);
        assertEquals(instancia.getX(), new Double(Double.NaN));
    }
    
    /**
     * Test of getX method, of class Tupla.
     */
    @Test
    public void testGetX() {
        System.out.println("getX");
        Tupla instancia = new Tupla();
        Double resultado = instancia.getX();
        assertNull(resultado);
    }

    /**
     * Test of setX method, of class Tupla.
     */
    @Test
    public void testSetX() {
        System.out.println("setX");
        Double X = new Double("1020");
        Tupla instancia = new Tupla();
        instancia.setX(X);
        assertEquals(instancia.getX(), X);
    }

    /**
     * Test of getY method, of class Tupla.
     */
    @Test
    public void testGetY() {
        System.out.println("getY");
        Tupla instancia = new Tupla();
        Double resultado = instancia.getY();
        assertNull(resultado);
    }

    /**
     * Test of setY method, of class Tupla.
     */
    @Test
    public void testSetY() {
        System.out.println("setY");
        Double Y = new Double("30");
        Tupla instancia = new Tupla();
        instancia.setY(Y);
        assertEquals(instancia.getY(), Y);
    }
    
}