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
     * @author Yoiner Gómez - yoiner.gomez22@gmail.com
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
    
    public LDL potencia(LDL lista, int exponente) {
        return lista;
    }
    
    private Double getX(NodoDoble nodo) {
        if (nodo.getDato() != null && nodo.getDato().getX() != null) {
            return nodo.getDato().getX();
        }
        return new Double(0);
    }
    
    private Double getY(NodoDoble nodo) {
        if (nodo.getDato() != null && nodo.getDato().getY() != null) {
            return nodo.getDato().getY();
        }
        return new Double(0);
    }
}
