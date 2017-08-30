/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.udea.pruebas_ps2.ldl;

import co.edu.udea.pruebas_ps2.modelo.Tupla;

/**
 * 
 * @author Yoiner Gómez - yoiner.gomez22@gmail.com
 * @date 2017/08/27
 * @version v1.0
 */
public class AritmeticaLDL {
    
    /**
     * Retorna el promedio de X o Y
     *
     * @param lista
     * @param tipo 1 para realizar el promedio por X y 2 para hacerlo por Y
     * @return el promedio de la lista ligada
     */
    public Double promedio(LDL lista, int tipo) {
        Double suma = new Double("0");
        NodoDoble puntero = lista.getPrimerNodo();
        while (puntero != null) {
            if (tipo == 1) {
                suma = suma + puntero.getDato().getX();
            } else if (tipo == 2) {
                suma = suma + puntero.getDato().getY();
            }
            puntero = puntero.getSiguiente();
        }
        int total = lista.longitud();
        if (total != 0) {
            suma = suma / total;
        }
        return suma;
    }

    /**
     * Realiza una sumatoria de la forma ∑(x*y) para cada tupla de la
     * lista ligada ingresada como argumento
     * @param lista
     * @return 
     */
    public Double sumatoriaXY(LDL lista) {
        Double suma = new Double(0);
        
        NodoDoble aux = lista.getPrimerNodo();
        while (aux != null) {
            suma += getX(aux)*getY(aux);
            aux = aux.getSiguiente(); // Mover al siguiente nodo
        }
        
        return Math.round(suma * 1e4) / 1e4;
    }
    
    /**
     * Suma los datos X y Y de la lista ligada de manera independiente
     * @param lista
     * @return 
     */
    public Tupla suma(LDL lista) {
        Tupla resultado;
        Double sumaX = new Double(0);
        Double sumaY = new Double(0);
        
        NodoDoble aux = lista.getPrimerNodo();
        while (aux != null) {
            sumaX += getX(aux);
            sumaY += getY(aux);
            aux = aux.getSiguiente(); // Mover al siguiente nodo
        }
        
        resultado = new Tupla(sumaX, sumaY);
        resultado.redondearValores();
        
        return resultado;
    }
    
    /**
     * Eleva cada valor de la tupla perteneciente a 
     * la lista ligada ingresada como argumento
     * @param lista
     * @param exponente
     * @return lista ligada con cada tupla elevada al argumento exponente
     */
    public LDL potencia(LDL lista, int exponente) {
        LDL resultado = new LDL();
        Double x;
        Double y;
        
        NodoDoble aux = lista.getPrimerNodo();
        while (aux != null) {
            x = Math.pow(getX(aux), exponente);
            y = Math.pow(getY(aux), exponente);
            
            resultado.insertar(new NodoDoble(new Tupla(x, y)));
            aux = aux.getSiguiente(); // Mover al siguiente nodo
        }
        
        return resultado;
    }
    
    /**
     * Obtiene el valor de X de la tupla que pertenece al nodo
     * ingresado como argumento de la función
     * @param nodo
     * @return 
     */
    private Double getX(NodoDoble nodo) {
        if (nodo.getDato() != null && nodo.getDato().getX() != null) {
            return nodo.getDato().getX();
        }
        return new Double(0);
    }
    
    /**
     * Obtiene el valor de Y de la tupla que pertenece al nodo
     * ingresado como argumento de la función
     * @param nodo
     * @return 
     */
    private Double getY(NodoDoble nodo) {
        if (nodo.getDato() != null && nodo.getDato().getY() != null) {
            return nodo.getDato().getY();
        }
        return new Double(0);
    }
}
