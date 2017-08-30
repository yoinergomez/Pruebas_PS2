package co.edu.udea.pruebas_ps2.rl;

import co.edu.udea.pruebas_ps2.ldl.AritmeticaLDL;
import co.edu.udea.pruebas_ps2.ldl.LDL;
import co.edu.udea.pruebas_ps2.ldl.NodoDoble;
import co.edu.udea.pruebas_ps2.modelo.Tupla;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Es la clase que contiene las pruebas unitarias para la clase 
 * RegresionLineal.java
 * @author Frank Castrillón - castrillonfrank114@gmail.com
 * @date 2017/08/29
 * @version v1
 */
public class RegresionLinealTest {
    private LDL lista;
    private AritmeticaLDL instancia;
    private RegresionLineal regresionLineal;
    
    @Before
    public void setUp() {
        instancia = new AritmeticaLDL();
        lista = new LDL();
        lista.insertar(new NodoDoble(new Tupla(new Double("20"), new Double("40"))));
        lista.insertar(new NodoDoble(new Tupla(new Double("30"), new Double("60"))));
        lista.insertar(new NodoDoble(new Tupla(new Double("40"), new Double("80"))));
        lista.insertar(new NodoDoble(new Tupla(new Double("50"), new Double("100"))));
        lista.insertar(new NodoDoble(new Tupla(new Double("60"), new Double("120"))));
        regresionLineal = new RegresionLineal(lista);
    }

    /**
     * Test of getB1 method, of class RegresionLineal.
     */
    @Test
    public void testGetB1() {
        Double esperado = new Double("2");
        Double resultado = regresionLineal.getB1();
        System.out.println("B1:"+resultado);
        assertEquals(esperado, resultado);
    }
    
    /**
     * Test of getB1 method, of class RegresionLineal.
     */
    @Test
    public void testGetB1Nulo() {
        regresionLineal = new RegresionLineal(new LDL());
        Double esperado = null;
        Double resultado = regresionLineal.getB1();
        System.out.println("B1:"+resultado);
        assertEquals(esperado, resultado);
    }

    /**
     * Test of getB0 method, of class RegresionLineal.
     */
    @Test
    public void testGetB0() {
        Double esperado = new Double("0");
        Double resultado = regresionLineal.getB0();
        System.out.println("B0:"+resultado);
        assertEquals(esperado, resultado);
    }
    
    /**
     * Test of getB0 method, of class RegresionLineal.
     */
    @Test
    public void testGetB0Nulo() {
        regresionLineal = new RegresionLineal(new LDL());
        Double esperado = null;
        Double resultado = regresionLineal.getB0();
        System.out.println("B0:"+resultado);
        assertEquals(esperado, resultado);
    }

    /**
     * Test of getRxy method, of class RegresionLineal.
     */
    @Test
    public void testGetRxy() {
        Double esperado = new Double("1");
        Double resultado = regresionLineal.getRxy();
        System.out.println("Rxy:"+resultado);
        assertEquals(esperado, resultado);
    }
    
    /**
     * Test of getRxy method, of class RegresionLineal.
     */
    @Test
    public void testGetRxyNulo() {
        regresionLineal = new RegresionLineal(new LDL());
        Double esperado = null;
        Double resultado = regresionLineal.getRxy();
        System.out.println("Rxy:"+resultado);
        assertEquals(esperado, resultado);
    }

    /**
     * Test of getR2 method, of class RegresionLineal.
     */
    @Test
    public void testGetR2() {
        Double esperado = new Double("1");
        Double resultado = regresionLineal.getRxy();
        System.out.println("r2:"+resultado);
        assertEquals(esperado, resultado);
    }
    
    /**
     * Test of getR2 method, of class RegresionLineal.
     */
    @Test
    public void testGetR2lNulo() {
        regresionLineal = new RegresionLineal(new LDL());
        Double esperado = null;
        Double resultado = regresionLineal.getRxy();
        System.out.println("r2:"+resultado);
        assertEquals(esperado, resultado);
    }
    
}
