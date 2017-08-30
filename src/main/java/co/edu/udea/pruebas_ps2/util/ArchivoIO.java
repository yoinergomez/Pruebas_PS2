/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.pruebas_ps2.util;

import co.edu.udea.pruebas_ps2.util.excepcion.ValidacionPS2;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * Clase que se encarga de manejar la lectura y escritura de archivos.
 *
 * @author Jhonatan Orozco Blandón
 * @date 2017/08/29
 * @version v1
 */
public class ArchivoIO {

    /**
     * Método que retorna el archivo que coincide con el nombre pasado como parametro.
     * @param nombreArchivo Ruta del archivo que se quiere encontrar
     * @return
     * @throws FileNotFoundException
     * @throws ValidacionPS2 
     */
    public File encontrarArchivo(String nombreArchivo) throws FileNotFoundException,
            ValidacionPS2 {
        File f = new File(nombreArchivo);
        String extArchivo;
        if (!f.exists()) {
            throw new FileNotFoundException("El archivo no existe");
        }
        extArchivo = FilenameUtils.getExtension(f.getName());
        if ("xls".compareTo(extArchivo) != 0) {
            throw new ValidacionPS2("La extensión es inválida");
        }

        return f;
    }
    
    
}
