package co.edu.udea.pruebas_ps2;

import co.edu.udea.pruebas_ps2.ldl.LDL;
import co.edu.udea.pruebas_ps2.util.ArchivoIO;
import co.edu.udea.pruebas_ps2.util.excepcion.ValidacionPS2;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * Es la clase principal que se encarga de ejecutar el proyecto.
 *
 * @author Frank Castrillón - castrillonfrank114@gmail.com
 * @date 2017/08/11
 * @version v1
 */
public class Principal {

    /**
     * Clase principal en la que se ejecutan el proyecto
     * @param args
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ValidacionPS2
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws URISyntaxException 
     */
    public static void main(String[] args) throws IOException, 
            FileNotFoundException, ValidacionPS2, 
            IllegalArgumentException, 
            IllegalAccessException, URISyntaxException { 
            ArchivoIO archivoIO = new ArchivoIO();   
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            System.out.println("Introduce el path del archivo de excel.");
            System.out.println("ejemplo: C:\\Users\\Laptop\\Downloads\\"
                    + "archivo.xls: ");
            System.out.println("path: ");
            String path = br.readLine();
            ArrayList<LDL> datos = archivoIO.convertirExcelALDL(path);
            File f=archivoIO.escribirResultados(datos,path);
            System.out.println("Revise los resultados en la ruta:\n" 
                    + f.getAbsolutePath());
            isr.close();
            br.close(); 
    }

}
