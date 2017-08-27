/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.pruebas_ps2.ldl;

import co.edu.udea.pruebas_ps2.modelo.Tupla;

/**
 * Clase que contiene la estructura de un nodo doble donde el campo dato contiene
 * una tupla (x ,y)
 * @author Frank Castrillón - frank.castrillon@udea.edu.co
 * @date 2017/08/27
 * @version v1
 */
public class NodoDoble {
    private Tupla dato;
    private NodoDoble siguiente;
    private NodoDoble anterior;
    
    /**
     * Contructor de la clase Node
     * @param next nodo siguiente
     * @param previous nodo anterior
     * @param dato tupla (x,y)
     */
    public NodoDoble(NodoDoble next, NodoDoble previous, Tupla dato) {
         this.siguiente = next;
         this.anterior = previous;
         this.dato = dato;
    }
    /**
     * Constructor de la clase node, se asigna null a los nodos next y previous
     * @param dato 
     */
    public NodoDoble(Tupla dato) {
         this.siguiente = null;
         this.anterior = null;
         this.dato = dato;
    }
    /**
     * Constructor de la clase node vacio, se asigna null a todos los atributos
     */
    public NodoDoble() {
         this.siguiente = null;
         this.anterior = null;
         this.dato = null;
    }

    public Tupla getDato() {
        return dato;
    }

    public void setDato(Tupla dato) {
        this.dato = dato;
    }

    NodoDoble getSiguiente() {
        return siguiente;
    }

    void setSiguiente(NodoDoble siguiente) {
        this.siguiente = siguiente;
    }

    NodoDoble getAnterior() {
        return anterior;
    }

    void setAnterior(NodoDoble anterior) {
        this.anterior = anterior;
    }
}
