package co.edu.udea.pruebas_ps2.ldl;

import co.edu.udea.pruebas_ps2.modelo.Tupla;

/**
 * Clase que contiene la estructura de una lista doblemente ligada con nodo 
 * cabeza, donde el dato del nodo cabeza almacena la cantidad de nodos en la
 * lista
 * @author Frank Castrillón - frank.castrillon@udea.edu.co
 * @date 2017/08/27
 * @version v1
 */
public class LDL {
    
    private NodoDoble cabeza;
    private NodoDoble ultimo;

    public LDL() {
        Tupla tupla = new Tupla(new Double("0"), new Double("0"));
        cabeza = new NodoDoble(null, null, tupla);
        ultimo = cabeza;
    }
    /**
     * Inserta el nuevo nodo al final de la lista
     * @param nuevo 
     */
    public void insertar(NodoDoble nuevo) {
        Tupla tupla;
        if (nuevo != cabeza && nuevo != ultimo){
            ultimo.setSiguiente(nuevo);
            nuevo.setAnterior(ultimo);
            ultimo = nuevo;
            tupla = cabeza.getDato();
            tupla.setX(tupla.getX() + new Double("1"));
            cabeza.setDato(tupla);
        }
    }
    
    /**
     * Elimina el nodo que se pasa por referencia, si existe retorna true 
     * de lo contrario false
     * @param candidato
     * @return 
     */
    public boolean eliminar(NodoDoble candidato) {
        Tupla tupla;
        if (candidato == cabeza){
            return false;
        }
        if (candidato == ultimo){
            candidato.getAnterior().setSiguiente(null);
        }else{
            NodoDoble actual = cabeza;
            while (actual.getSiguiente() != candidato 
                    && actual.getSiguiente() != null){
                actual = actual.getSiguiente();
            }
            if (actual == ultimo){
                return false;
            }
            actual.setSiguiente(candidato.getSiguiente());
            candidato.getSiguiente().setAnterior(actual);
        }
        tupla = cabeza.getDato();
        tupla.setX(tupla.getX() - new Double("1"));
        cabeza.setDato(tupla);
        return true;
    }
    
    /**
     * Imprime los datos de la lista ligada
     */
    public void imprimirLista() {
        NodoDoble aux = cabeza;
        System.out.print("HEAD["+aux.getDato().getX()+"]");
        aux = aux.getSiguiente();
        while (aux != null) {
            System.out.print(" <=> "+"("+aux.getDato().getX()+","+
                    aux.getDato().getY()+")");
            aux = aux.getSiguiente();
        }
    }

    public NodoDoble getUltimo() {
        return ultimo;
    }

    public NodoDoble getPrimerNodo() {
        return cabeza.getSiguiente();
    }
    
    /**
     * Obtiene la longitud de la lista ligada
     * @return 
     */
    public int longitud() {
        return cabeza.getDato().getX().intValue();
    }
    
    public NodoDoble siguienteNodo(NodoDoble nodoActual){
        return nodoActual.getSiguiente();
    }
}
