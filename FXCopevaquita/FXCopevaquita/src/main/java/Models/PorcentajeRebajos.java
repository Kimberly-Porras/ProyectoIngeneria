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

    public PorcentajeRebajos() {
        this.id = 0;
        this.gobierno = 0.0;
    }
    
     public PorcentajeRebajos(int id, double gobierno) {
        this.id = id;
        this.gobierno = gobierno;
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
}
