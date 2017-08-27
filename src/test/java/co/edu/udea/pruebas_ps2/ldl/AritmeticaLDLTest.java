/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.pruebas_ps2.ldl;

import co.edu.udea.pruebas_ps2.modelo.Tupla;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Yoiner Gómez - yoiner.gomez22@gmail.com
 */
public class AritmeticaLDLTest {
    
    private LDL lista;
    private AritmeticaLDL instancia;
    
    public AritmeticaLDLTest() {
    }
    
    @Before
    public void setUp() {
        instancia = new AritmeticaLDL();
        lista = new LDL();
        
        lista.insertar(new NodoDoble(new Tupla(new Double("20"), new Double("40"))));
        lista.insertar(new NodoDoble(new Tupla(new Double("10.56"), new Double("21.12"))));
        lista.insertar(new NodoDoble(new Tupla(new Double("19.44"), new Double("38.88"))));
    }

    /**
     * Prueba que la funcion sumatoriaXY retorne el valor correspodiente 
     * con datos pertenecientes al conjunto de los reales
     */
    @Test
    public void testSumatoriaXY() {
        Double aux = 1778.8544;
        Double resultado = instancia.sumatoriaXY(lista);
        assertEquals(aux, resultado);
    }
    
    /**
     * Prueba que la funcion sumatoriaXY retorne el valor correspodiente 
     * con datos pertenecientes al conjunto de los enteros
     */
    @Test
    public void testSumatoriaEnteros() {
        Double aux = 10.0;
        LDL listaPrueba = new LDL();
        
        listaPrueba.insertar(new NodoDoble(new Tupla(1.0, 5.0)));
        listaPrueba.insertar(new NodoDoble(new Tupla(2.0, 6.0)));
        listaPrueba.insertar(new NodoDoble(new Tupla(-7.0, 1.0)));
        
        Double resultado = instancia.sumatoriaXY(listaPrueba);
        assertEquals(aux, resultado);
    }

    /**
     * Prueba que la X de la lista ligada sea sumado correctamente
     */
    @Test
    public void testSumaX() {
        Tupla aux = new Tupla(new Double("50"), new Double("100"));
        Tupla resultado = instancia.suma(lista);
        
        assertEquals(aux.getX(), resultado.getX());
    }
    
    /**
     * Prueba que la Y de la lista ligada sea sumada correctamente
     */
    @Test
    public void testSumaY() {
        Tupla aux = new Tupla(new Double("50"), new Double("100"));
        Tupla resultado = instancia.suma(lista);
        
        assertEquals(aux.getY(), resultado.getY());
    }
    
    /**
     * Prueba que el resultado de la lista sea correcta existiendo un nodo nulo
     */
    @Test
    public void testSumaNodoDobleNULL() {
        lista.insertar(new NodoDoble());
        
        Tupla aux = new Tupla(new Double("50"), new Double("100"));
        Tupla resultado = instancia.suma(lista);
        
        assertEquals(aux.getY(), resultado.getY());
    }
    
    /**
     * Prueba que el resultado de la lista sea correcta existiendo un nodo 
     * con una tupla nula
     */
    @Test
    public void testSumaNodoDobleConTuplaNULL() {
        lista.insertar(new NodoDoble(new Tupla()));
        
        Tupla aux = new Tupla(new Double("50"), new Double("100"));
        Tupla resultado = instancia.suma(lista);
        
        assertEquals(aux.getY(), resultado.getY());
    }
    
    /**
     * Prueba que el resultado de la lista sea correcta existiendo datos
     * pertenecientes al conjunto de los reales
     */
    @Test
    public void testSumaConDecimales() {
        lista.insertar(new NodoDoble(new Tupla(new Double("0.001"), new Double(0))));
        
        Tupla aux = new Tupla(new Double("50.001"), new Double("100"));
        Tupla resultado = instancia.suma(lista);
        
        assertEquals(aux.getX(), resultado.getX());
    }
    
    /**
     * Test of potencia method, of class AritmeticaLDL.
     */
//    @Test
//    public void testPotencia() {
//        System.out.println("potencia");
//        LDL lista = null;
//        int exponente = 0;
//        AritmeticaLDL instance = new AritmeticaLDL();
//        LDL expResult = null;
//        LDL result = instance.potencia(lista, exponente);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
