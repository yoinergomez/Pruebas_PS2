package co.edu.udea.pruebas_ps2.rl;

import co.edu.udea.pruebas_ps2.ldl.AritmeticaLDL;
import co.edu.udea.pruebas_ps2.ldl.LDL;
import co.edu.udea.pruebas_ps2.modelo.Tupla;

/**
 * Es la clase para hallar las medidas estadisticas asociadas a la regresión 
 * lineal
 * @author Frank Castrillón - castrillonfrank114@gmail.com
 * @date 2017/08/27
 * @version v1
 */
public class RegresionLineal {
    private final Double b1; 
    private final Double b0;
    private final Double rxy;
    private final Double r2;
    
    public RegresionLineal(LDL ldl) {
        AritmeticaLDL a = new AritmeticaLDL();
        Tupla tupla = a.suma(ldl);
        int n = ldl.longitud();
        Double  numerador, 
                denominador, 
                promedioX = a.promedio(ldl, 1), 
                promedioY = a.promedio(ldl, 2), 
                sumaXY = a.sumatoriaXY(ldl),
                x2 = a.suma(a.potencia(ldl, 2)).getX(),
                y2 = a.suma(a.potencia(ldl, 2)).getY();
        numerador = sumaXY - (n * promedioX * promedioY);
        denominador = x2 - n * Math.pow(promedioX, 2);
        if (denominador != 0) {
            this.b1 = numerador / denominador;
            this.b0 = promedioY - this.b1 * promedioX;
        }else{
            this.b1 = null;
            this.b0 = null;
        }
        numerador = n * sumaXY - (tupla.getX() * tupla.getY());
        denominador = Math.sqrt(((n * x2 - tupla.getX() * tupla.getX()) * 
                (n * y2 - Math.pow(tupla.getY(), 2))));
        if(denominador != 0){
            this.rxy = numerador / denominador;
            this.r2 = Math.pow(this.rxy, 2);
        }else{
            this.rxy = null;
            this.r2 = null;
        }
    }

    public Double getB1() {
        return b1;
    }

    public Double getB0() {
        return b0;
    }

    public Double getRxy() {
        return rxy;
    }

    public Double getR2() {
        return r2;
    }
    
}
