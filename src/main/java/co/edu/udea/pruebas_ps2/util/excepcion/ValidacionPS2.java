/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.pruebas_ps2.util.excepcion;

/**
 * Clase que se encarga de egestionar las excepciones producidas por las reglas
 * del negocio.
 * @author Jhonatan Orozco Blandón
 * @date 2017/08/29
 * @version v1.
 */
public class ValidacionPS2 extends Exception {
    
    /**
     * Constructor de la clase.
     * @param arg0 
     */
    public ValidacionPS2(String arg0) {
        super(arg0);
    }
    
}
