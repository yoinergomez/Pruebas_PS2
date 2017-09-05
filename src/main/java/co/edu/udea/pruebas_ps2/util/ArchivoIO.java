/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.pruebas_ps2.util;

import co.edu.udea.pruebas_ps2.ldl.LDL;
import co.edu.udea.pruebas_ps2.ldl.NodoDoble;
import co.edu.udea.pruebas_ps2.modelo.Tupla;
import co.edu.udea.pruebas_ps2.rl.RegresionLineal;
import co.edu.udea.pruebas_ps2.util.excepcion.ValidacionPS2;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import java.net.URISyntaxException;

/**
 * Clase que se encarga de manejar la lectura y escritura de archivos.
 *
 * @author Jhonatan Orozco Blandón
 * @date 2017/08/29
 * @version v1
 */
public class ArchivoIO {

    private Workbook workbook;
    private Sheet sheet;
    private ArrayList<Row> filas;

    public ArchivoIO() {
        filas = new ArrayList();
    }

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
        filas.clear();
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
            filas.add(fila);
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
                            nodoT2 = new NodoDoble(t2);
                            datosEstimacion.insertar(nodoT2);
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

            datosRegresion.insertar(nodoT1);

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

    /**
     * Revisa que la celda no sea nula,que no este vacia y que sea de tupo
     * númerico. Si se cumplen todas esas condiciones retorna true.
     *
     * @param celda
     * @return
     * @throws ValidacionPS2
     */
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

    /**
     * Recibe los datos, calcula la regresión y escribe en el archivo de Excel los
     * resultados.
     * @param datos Los datos de la regresion y los x de prueba, para validar la 
     * regresión.
     * @param nombreArchivo Nombre del archivo de Excel donde se escriben los 
     * resultados.
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws URISyntaxException
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public File escribirResultados(ArrayList<LDL> datos, String nombreArchivo) 
            throws IllegalArgumentException, IllegalAccessException, 
            URISyntaxException,FileNotFoundException, IOException {
        LDL datosRegresion = datos.get(0);
        LDL datosEstimacion = datos.get(1);
        RegresionLineal regresion = new RegresionLineal(datosRegresion);
        escribirResultadosRegresion(regresion);
        escribirDatosEstimados(datosEstimacion, regresion);
        FileOutputStream outputStream = new FileOutputStream(nombreArchivo);
        workbook.write(outputStream);
        File f = new File(nombreArchivo);
        return f;
    }

    /**
     * Método que escribe en el archivo de Excel el nombre del item con su 
     * respectivo valor.
     * @param nombreItem Nombre del item.
     * @param valor Valor del item
     * @param indiceFila Indice de la fila donde se quiere escribir.
     */
    private void escribirItem(String nombreItem, Double valor, int indiceFila) {
        Row r;
        int numeroFilas = filas.size();
        if (indiceFila < numeroFilas) {
            r = filas.get(indiceFila);
        } else {
            r = sheet.createRow(indiceFila);
        }
        Cell c = r.createCell(5);
        c.setCellValue(nombreItem);
        c = r.createCell(6);
        c.setCellValue(valor);

    }

    /**
     * Método que escribe en el archivo de Excel los 'y' estimados por la regresión.
     * @param datosEstimacion Lista de los x con los que se verifica la regresión.
     * @param rg La regresión lineal.
     */
    private void escribirDatosEstimados(LDL datosEstimacion, RegresionLineal rg) {
        NodoDoble p = datosEstimacion.getPrimerNodo();
        Row r;
        Cell c;
        int i = 0;
        double x;
        double y;
        Tupla t;
        double b0 = rg.getB0();
        double b1 = rg.getB1();
        while (p != null) {
            t = p.getDato();
            x = p.getDato().getX();
            y = b0 + b1 * x;
            r = filas.get(i);
            c = r.createCell(3);
            c.setCellValue(y);
            i++;
            p = datosEstimacion.siguienteNodo(p);
        }

    }

    /**
     * Método que escribe en el archivo de Excel los resultados de aplicar la 
     * regresión al conjunto de datos.
     * @param rg La regresión lineal.
     */
    private void escribirResultadosRegresion(RegresionLineal rg) {
        double b0 = rg.getB0();
        double b1 = rg.getB1();
        escribirItem("B0", b0, 0);
        escribirItem("B1", b1, 1);
        escribirItem("Rxy", rg.getRxy(), 2);
        escribirItem("r^2", rg.getR2(), 3);
    }


}
