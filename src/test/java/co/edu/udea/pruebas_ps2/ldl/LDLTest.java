package co.edu.udea.pruebas_ps2.ldl;

import co.edu.udea.pruebas_ps2.modelo.Tupla;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * Es la clase que contiene las pruebas unitarias para la clase LDL.java
 * @author Frank Castrillón - castrillonfrank114@gmail.com
 * @date 2017/08/27
 * @version v1
 */
public class LDLTest {
    
    private LDL instancia;
    
    public LDLTest() {
    }
    
    @Before
    public void setUp() {
        Tupla tupla;
        instancia = new LDL();
        tupla = new Tupla(new Double("1"), new Double("2"));
        instancia.insertar(new NodoDoble(tupla));
        tupla = new Tupla(new Double("2"), new Double("3"));
        instancia.insertar(new NodoDoble(tupla));
        tupla = new Tupla(new Double("3"), new Double("4"));
        instancia.insertar(new NodoDoble(tupla));
    } 

    /**
     * Test del método insertar, de la clase LDL.
     */
    @Test
    public void testInsertar() {
        NodoDoble nuevo = new NodoDoble();
        instancia.insertar(nuevo);
        assertEquals(instancia.getUltimo(), nuevo);
    }

    /**
     * Test del método eliminar el unico nodo de la lista
     */
    @Test
    public void testEliminar() {
        Tupla tupla = new Tupla(new Double("1"), new Double("2"));
        NodoDoble candidato = new NodoDoble();
        instancia.insertar(candidato);
        boolean resultado = instancia.eliminar(candidato);
        instancia.imprimirLista();
        assertEquals(Boolean.TRUE, resultado);
    }
    
    /**
     * Test para eliminar un nodo al intermedio de la lista.
     */
    @Test
    public void testEliminarNodo() {
        Tupla tupla = new Tupla(new Double("2"), new Double("3"));
        NodoDoble candidato = new NodoDoble(tupla);
        LDL instancia = new LDL();
        tupla = new Tupla(new Double("1"), new Double("2"));
        instancia.insertar(new NodoDoble(tupla));
        tupla = new Tupla(new Double("4"), new Double("5"));
        instancia.insertar(new NodoDoble(tupla));
        instancia.insertar(candidato);
        tupla = new Tupla(new Double("6"), new Double("7"));
        instancia.insertar(new NodoDoble(tupla));
        boolean resultado = instancia.eliminar(candidato);
        instancia.imprimirLista();
        assertEquals(Boolean.TRUE, resultado);
    }
    
    /**
     * Test para eliminar una nodo que no existe en la lista
     */
    @Test
    public void testEliminarNoExist() {
        Tupla tupla = new Tupla(new Double("100"), new Double("200"));
        NodoDoble candidato = new NodoDoble(tupla);
        LDL instancia = new LDL();
        tupla = new Tupla(new Double("1"), new Double("2"));
        instancia.insertar(new NodoDoble(tupla));
        tupla = new Tupla(new Double("3"), new Double("4"));
        instancia.insertar(new NodoDoble(tupla));
        tupla = new Tupla(new Double("5"), new Double("6"));
        instancia.insertar(new NodoDoble(tupla));
        boolean resultado = instancia.eliminar(candidato);
        assertEquals(Boolean.FALSE, resultado);
    }
    
    /**
     * Test para eliminar el nodo cabeza
     */
    @Test
    public void testEliminarCabeza() {
        boolean resultado = instancia.eliminar(instancia.getPrimerNodo().getAnterior());
        instancia.imprimirLista();
        assertEquals(Boolean.FALSE, resultado);
    }
    
    /**
     * Test para eliminar el primer nodo
     */
    @Test
    public void testEliminarUltimo() {
        boolean resultado = instancia.eliminar(instancia.getUltimo());
        instancia.imprimirLista();
        assertEquals(Boolean.TRUE, resultado);
    }   
    
    /**
     * Test del método getUltimo, de la clase LDL.
     */
    @Test
    public void testGetUltimo() {
        Tupla tupla = new Tupla(new Double("300"), new Double("400"));
        NodoDoble candidato = new NodoDoble(tupla);
        instancia.insertar(candidato);
        NodoDoble resultadoado = instancia.getUltimo();
        assertEquals(candidato, resultadoado);
    }
    
    /**
     * Test del método longitud, de la clase LDL.
     */
    @Test
    public void testLongitud() {
        int tamaño = instancia.longitud();
        assertEquals(3, tamaño);
    }   
}