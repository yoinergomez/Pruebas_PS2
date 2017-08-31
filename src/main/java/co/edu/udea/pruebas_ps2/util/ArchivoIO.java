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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;

/**
 * Clase que se encarga de manejar la lectura y escritura de archivos.
 *
 * @author Jhonatan Orozco Blandón
 * @date 2017/08/29
 * @version v1
 */
public class ArchivoIO {

    private Sheet sheet;
    private Workbook workbook;

    /**
     * Método que retorna el archivo que coincide con el nombre pasado como
     * parametro.
     *
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

    /**
     * Método que permite abrir el libro de Excel.
     *
     * @param f El archivo de Excel
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public Workbook abrirLibroExcel(File f) throws FileNotFoundException,
            IOException {
        InputStream in = new FileInputStream(f);
        Workbook w = new HSSFWorkbook(in);

        return w;
    }

    /**
     * Convierte los datos guardados en el archivo de Excel en un ArrayList de
     * tipo LDL de dos posiciones. La posición cero es una LDL que contiene las
     * tuplas (x,y) que serán usadas para la regresión. La posición uno es una
     * LDL que tiene los x de prueba con los que el usuario quiere predecir y,
     * con base en la regresión.
     *
     * @param nombreArchivo Ruta del archivo.
     * @return
     * @throws FileNotFoundException
     * @throws ValidacionPS2
     * @throws IOException
     */
    public ArrayList<LDL> convertirExcelALDL(String nombreArchivo) throws
            FileNotFoundException, ValidacionPS2, IOException {
        File f = encontrarArchivo(nombreArchivo);
        workbook = abrirLibroExcel(f);
        sheet = workbook.getSheetAt(0);
        LDL datosRegresion = new LDL();
        LDL datosEstimacion = new LDL();
        ArrayList<LDL> datos = new ArrayList();
        Tupla t1, t2;
        NodoDoble nodoT1, nodoT2;
        boolean existeXprueba = false;
        boolean existeDatos = false;
        int indiceColumna;
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row fila = rowIterator.next();
            Iterator<Cell> cellIterator = fila.cellIterator();
            t1 = new Tupla();
            t2 = new Tupla();
            while (cellIterator.hasNext()) {
                Cell celda = cellIterator.next();
                indiceColumna = celda.getColumnIndex();
                if (esCeldaValida(celda)) {
                    switch (indiceColumna) {
                        case 0:
                            t1.setX(celda.getNumericCellValue());
                            existeDatos = true;
                            break;

                        case 1:
                            t1.setY(celda.getNumericCellValue());
                            break;

                        case 2:
                            t2.setX(celda.getNumericCellValue());
                            existeXprueba = true;
                            break;
                    }
                }

            }
            if (t1.getX() == null) {
                throw new ValidacionPS2("Existe al menos un par (x,y) que está "
                        + "incompleto.La x esta vacia");
            }
            if (t1.getY() == null) {
                throw new ValidacionPS2("Existe al menos un par (x,y) que está "
                        + "incompleto.La y esta vacia");
            }
            nodoT1 = new NodoDoble(t1);
            nodoT2 = new NodoDoble(t2);
            datosRegresion.insertar(nodoT1);
            datosEstimacion.insertar(nodoT2);

        }
        if (!existeDatos) {
            throw new ValidacionPS2("No existen datos");
        }
        if (!existeXprueba) {
            throw new ValidacionPS2("Ingrese al menos un x de prueba para validar"
                    + " la regresión");
        }

        datos.add(datosRegresion);
        datos.add(datosEstimacion);
        return datos;
    }

    public boolean esCeldaValida(Cell celda) throws ValidacionPS2 {
        if (celda == null || celda.getCellType() == Cell.CELL_TYPE_BLANK) {
            CellReference cr = new CellReference(celda);
            return false;
        } else if (celda.getCellType() != Cell.CELL_TYPE_NUMERIC) {
            CellReference cr = new CellReference(celda);
            throw new ValidacionPS2("Error leyendo la celda " + cr.formatAsString());
        }
        return true;
    }

}
