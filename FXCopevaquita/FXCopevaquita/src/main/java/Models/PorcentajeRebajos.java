/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author alber
 * @author kim03
 */
public class PorcentajeRebajos {
    
    private int id;
    private double gobierno;
    private double exceso;

    public PorcentajeRebajos() {
        this.id = 0;
        this.gobierno = 0.0;
        this.exceso = 0.0;
    }
    
     public PorcentajeRebajos(int id, double gobierno, double exceso) {
        this.id = id;
        this.gobierno = gobierno;
        this.exceso = exceso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getGobierno() {
        return gobierno;
    }

    public void setGobierno(double gobierno) {
        this.gobierno = gobierno;
    }

    public double getExceso() {
        return exceso;
    }

    public void setExceso(double exceso) {
        this.exceso = exceso;
    }
}
