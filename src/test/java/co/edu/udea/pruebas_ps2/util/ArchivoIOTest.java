/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.pruebas_ps2.util;

import co.edu.udea.pruebas_ps2.util.excepcion.ValidacionPS2;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.SystemUtils;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pc1
 */
public class ArchivoIOTest {
    
    public ArchivoIO archivoIO;
    
    public ArchivoIOTest() {
    }
    
    
    @Before
    public void inicializar() {
        archivoIO=new ArchivoIO();
    }
    
    public String corregirPath(String nombreRecurso) throws URISyntaxException {
        String path = this.getClass().getClassLoader().getResource(nombreRecurso)
                .toURI().toString();
        if (SystemUtils.IS_OS_WINDOWS) {
            return path.substring(6);
        }
        return path.substring(5);
    }


    @Test(expected = FileNotFoundException.class)
    public void testAbrirArchivoInexistente() throws
            ValidacionPS2, URISyntaxException, FileNotFoundException {
        String path = "esteArchivoNoExiste.xls";
        archivoIO.encontrarArchivo(path);
    }
        
    
    @Test
    public void testAbrirArchivoExistente() throws FileNotFoundException,
            ValidacionPS2, URISyntaxException {
        String path = corregirPath("prueba.xls");
        File f = archivoIO.encontrarArchivo(path);
        assertTrue(f.exists());
    }
    
    
    @Test
    public void testAbrirArchivosExcel() throws FileNotFoundException,
            ValidacionPS2, URISyntaxException {
        String path = corregirPath("prueba.xls");
        File f = archivoIO.encontrarArchivo(path);
        String ext = FilenameUtils.getExtension(f.getName());
        assertArrayEquals("xls".toCharArray(), ext.toCharArray());
    }

    /**
     *
     * @throws FileNotFoundException
     * @throws co.edu.udea.pruebas_ps2.util.excepcion.ValidacionPS2
     * @throws java.net.URISyntaxException
     */
    @Test(expected = ValidacionPS2.class)
    public void testAbrirArchivoDistintoExcel() throws FileNotFoundException,
            ValidacionPS2, URISyntaxException {
        String path = corregirPath("prueba.txt");
        archivoIO.encontrarArchivo(path);
    }
    
}
