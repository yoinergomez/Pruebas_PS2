package co.edu.udea.pruebas_ps2.modelo;

/**
 * Clase que contiene la información de una tupla (x,y)
 * @author Frank Castrillón - castrillonfrank114@gmail.com
 * @date 2017/08/27
 * @version v1
 */
public class Tupla {
    private Double X;
    private Double Y;

    public Tupla(Double X, Double Y) {
        this.X = X;
        this.Y = Y;
    }
    
    public Tupla() {
        this.X = null;
        this.Y = null;
    }

    public Double getX() {
        return X;
    }

    public void setX(Double X) {
        this.X = X;
    }

    public Double getY() {
        return Y;
    }

    public void setY(Double Y) {
        this.Y = Y;
    }
    
    public void redondearValores() {
        if (this.X != null) {
            this.X = Math.round(this.X * 1e4) / 1e4;
        }
        if (this.Y != null) {
            this.Y = Math.round(this.Y * 1e4) / 1e4;
        }
    }
}
