/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.pruebas_ps2.util;

import co.edu.udea.pruebas_ps2.ldl.LDL;
import co.edu.udea.pruebas_ps2.ldl.NodoDoble;
import co.edu.udea.pruebas_ps2.modelo.Tupla;
import co.edu.udea.pruebas_ps2.util.excepcion.ValidacionPS2;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Objects;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.SystemUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Clase de prueba para ArchivoIO
 * @author Jhonatan Orozco Blandón
 * @date 2017/08/29
 * @version v1
 */
public class ArchivoIOTest {

    public ArchivoIO archivoIO;

    /**
     * Constructor de la clase
     */
    public ArchivoIOTest() {
    }

    /**
     * Método para incializar objetos que se ejecuten en todas las pruebas.
     */
    @Before
    public void inicializar() {
        archivoIO = new ArchivoIO();
    }

    /**
     * Retorna la ruta del recurso que se quiere encontrar.
     * @param nombreRecurso
     * @return
     * @throws URISyntaxException 
     */
    public String corregirPath(String nombreRecurso) throws URISyntaxException {
        String path = this.getClass().getClassLoader().getResource(nombreRecurso)
                .toURI().toString();
        if (SystemUtils.IS_OS_WINDOWS) {
            return path.substring(6);
        }
        return path.substring(5);
    }

    /**
     * Compara que las dos objetos LDL sean inguales.
     * @param l1
     * @param l2
     * @return 
     */
    public boolean iguales(LDL l1, LDL l2) {
        NodoDoble nodoL1 = l1.getPrimerNodo();
        NodoDoble nodoL2 = l2.getPrimerNodo();
        Tupla t1, t2;
        while (nodoL1 != null && nodoL2 != null) {
            t1 = nodoL1.getDato();
            t2 = nodoL2.getDato();
            if (!Objects.equals(t1.getX(), t2.getX())) {
                return false;
            }

            if (!Objects.equals(t1.getY(), t2.getY())) {
                return false;
            }
            nodoL1 = l1.siguienteNodo(nodoL1);
            nodoL2 = l1.siguienteNodo(nodoL2);
        }
        return true;

    }

    /**
     * Caso de prueba cuando la ruta hace referencia a un archivo que no existe.
     * Se espera un error de tipo FileNotFoundException
     *
     * @throws ValidacionPS2
     * @throws URISyntaxException
     * @throws FileNotFoundException
     */
    @Test(expected = FileNotFoundException.class)
    public void testAbrirArchivoInexistente() throws
            ValidacionPS2, URISyntaxException, FileNotFoundException {
        String path = "esteArchivoNoExiste.xls";
        archivoIO.encontrarArchivo(path);
    }

    /**
     * Caso de prueba cuando la ruta hace refrencia a un archivo existente.
     *
     * @throws FileNotFoundException
     * @throws ValidacionPS2
     * @throws URISyntaxException
     */
    @Test
    public void testAbrirArchivoExistente() throws FileNotFoundException,
            ValidacionPS2, URISyntaxException {
        String path = corregirPath("prueba.xls");
        File f = archivoIO.encontrarArchivo(path);
        assertTrue(f.exists());
    }

    /**
     * Caso de prueba con un archivo Excel de extensión xls.
     *
     * @throws FileNotFoundException
     * @throws ValidacionPS2
     * @throws URISyntaxException
     */
    @Test
    public void testAbrirArchivosExcel() throws FileNotFoundException,
            ValidacionPS2, URISyntaxException {
        String path = corregirPath("prueba.xls");
        File f = archivoIO.encontrarArchivo(path);
        String ext = FilenameUtils.getExtension(f.getName());
        assertArrayEquals("xls".toCharArray(), ext.toCharArray());
    }

    /**
     * Caso de prueba cuando se selecciona un archivo con una extensión distinta
     * a xls.Se espera un error d etipo ValidacionPS2
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

    /**
     * Caso de prueba que consiste en obtener el libro de Excel.
     *
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ValidacionPS2
     * @throws URISyntaxException
     */
    @Test
    public void testAbrirLibroExcel() throws FileNotFoundException, IOException,
            ValidacionPS2, URISyntaxException {
        String path = corregirPath("prueba.xls");
        File f = archivoIO.encontrarArchivo(path);
        Workbook w = archivoIO.abrirLibroExcel(f);
        assertNotNull(w);
    }

    /**
     * Caso de prueba leyendo datos desde el archivo de Excel.
     * @throws URISyntaxException
     * @throws ValidacionPS2
     * @throws FileNotFoundException
     * @throws IOException 
     */
    @Test
    public void testConvertirExcelALDL() throws URISyntaxException,
            ValidacionPS2, FileNotFoundException, IOException {
        //Creacion de los datos que se esperan tener en el Excel
        LDL datosRegresionReales = new LDL();
        LDL datosEstimacionReales = new LDL();
        Double x = new Double(3);
        Double y = new Double(33);
        NodoDoble l = new NodoDoble(new Tupla(x, y));
        datosRegresionReales.insertar(l);

        x = new Double(1);
        y = new Double(45);
        l = new NodoDoble(new Tupla(x, y));
        datosRegresionReales.insertar(l);

        x = new Double(2);
        y = new Double(66);
        l = new NodoDoble(new Tupla(x, y));
        datosRegresionReales.insertar(l);

        x = new Double(7);
        l = new NodoDoble(new Tupla(x, null));
        datosEstimacionReales.insertar(l);

        x = new Double(8);
        l = new NodoDoble(new Tupla(x, null));
        datosEstimacionReales.insertar(l);

        String path = corregirPath("datosRegresion.xls");
        ArrayList<LDL> datos = archivoIO.convertirExcelALDL(path);
        LDL datosRegresion = datos.get(0);
        LDL datosEstimacion = datos.get(1);
        boolean b1 = iguales(datosRegresionReales, datosRegresion);
        boolean b2 = iguales(datosEstimacionReales, datosEstimacion);
        assertTrue(b1 && b2);

    }
    
    
    /**
     * Caso de prueba cuando en el archivo existe un registro de la columna 'Y' 
     * vacio.
     * @throws java.net.URISyntaxException
     * @throws co.edu.udea.pruebas_ps2.util.excepcion.ValidacionPS2
     * @throws java.io.IOException
     **/
    @Test(expected = ValidacionPS2.class)
    public void testDatoColumnaYVacio() throws URISyntaxException, ValidacionPS2,
            IOException{
     
        String path = corregirPath("datosColumnaYIncompleta.xls");
        ArrayList<LDL> datos = archivoIO.convertirExcelALDL(path);

    }
    
    
    /**
     * Caso de prueba cuando en el archivo existe un registro de la columna 'X' 
     * vacio.
     * @throws java.net.URISyntaxException
     * @throws co.edu.udea.pruebas_ps2.util.excepcion.ValidacionPS2
     * @throws java.io.IOException
     **/
    @Test(expected = ValidacionPS2.class)
    public void testDatoColumnaXVacio() throws URISyntaxException, ValidacionPS2,
            IOException{
     
        String path = corregirPath("datosColumnaXIncompleta.xls");
        ArrayList<LDL> datos = archivoIO.convertirExcelALDL(path);
 
    }
    
    
     /**
     * Caso de prueba cuando en el archivo no existen x de prueba para la regresión.
     * @throws java.net.URISyntaxException
     * @throws co.edu.udea.pruebas_ps2.util.excepcion.ValidacionPS2
     * @throws java.io.IOException
     **/
    @Test(expected = ValidacionPS2.class)
    public void testArchivoVacio() throws URISyntaxException, ValidacionPS2,
            IOException{
     
        String path = corregirPath("archivoVacio.xls");
        ArrayList<LDL> datos = archivoIO.convertirExcelALDL(path);
 
    }
    
     /**
     * Caso de prueba cuando no existen en el archivo de Excel x de prueba para 
     * validar la regresión.
     * @throws java.net.URISyntaxException
     * @throws co.edu.udea.pruebas_ps2.util.excepcion.ValidacionPS2
     * @throws java.io.IOException
     */
    @Test(expected = ValidacionPS2.class)
    public void testColumnaXPruebaVacia() throws URISyntaxException, 
            ValidacionPS2, IOException{
         String path = corregirPath("datosSinXDePrueba.xls");
        ArrayList<LDL> datos = archivoIO.convertirExcelALDL(path);
    }
    
    
    /**
     * Caso de prueba para cuando se encuentra en el archivo de Excel un valor
     * no númerico.
     * @throws java.net.URISyntaxException
     * @throws co.edu.udea.pruebas_ps2.util.excepcion.ValidacionPS2
     * @throws java.io.IOException
     */
    @Test(expected = ValidacionPS2.class)
    public void testLeerDatoNoNumerico() throws URISyntaxException, 
            ValidacionPS2, IOException{
         String path = corregirPath("archivoNoNumerico.xls");
        ArrayList<LDL> datos = archivoIO.convertirExcelALDL(path);
    }
    

}
