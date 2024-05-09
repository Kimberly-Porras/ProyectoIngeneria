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
    private double exceso1;
    private double exceso2;
    private double exceso3;

    public PorcentajeRebajos() {
        this.id = 0;
        this.gobierno = 0.0;
        this.exceso1 = 0.0;
        this.exceso2 = 0.0;
        this.exceso3 = 0.0;
    }
    
     public PorcentajeRebajos(int id, double gobierno, double exceso1, double exceso2, double exceso3) {
        this.id = id;
        this.gobierno = gobierno;
        this.exceso1 = exceso1;
        this.exceso2 = exceso2;
        this.exceso3 = exceso3;
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

    public double getExceso1() {
        return exceso1;
    }

    public void setExceso1(double exceso1) {
        this.exceso1 = exceso1;
    }

    public double getExceso2() {
        return exceso2;
    }

    public void setExceso2(double exceso2) {
        this.exceso2 = exceso2;
    }

    public double getExceso3() {
        return exceso3;
    }

    public void setExceso3(double exceso3) {
        this.exceso3 = exceso3;
    }
}
