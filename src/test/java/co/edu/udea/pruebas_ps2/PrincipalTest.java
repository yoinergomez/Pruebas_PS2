/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.pruebas_ps2;

import co.edu.udea.pruebas_ps2.util.excepcion.ValidacionPS2;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import org.apache.commons.lang.SystemUtils;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Yoiner Gómez - yoiner.gomez22@gmail.com
 */
public class PrincipalTest {
    
    public PrincipalTest() {
    }
    
    public String corregirPath(String nombreRecurso) throws URISyntaxException {
        String path = this.getClass().getClassLoader().getResource(nombreRecurso)
                .toURI().toString();
        if (SystemUtils.IS_OS_WINDOWS) {
            return path.substring(6);
        }
        return path.substring(5);
    }

    /**
     * Test of main method, of class Principal.
     */
    @Test
    public void testMain() throws IOException, FileNotFoundException, ValidacionPS2, IllegalArgumentException, IllegalAccessException, URISyntaxException {
        Principal p = new Principal();

        String data = corregirPath("datosRegresion.xls");
        String[] args = null;
        final InputStream original = System.in;
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Principal.main(args);
        System.setIn(original);
    }
    
}
