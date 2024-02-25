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
public class PagoIncapacidad {

    private int id;
    private  double totalIncapacidad;
    private int pagoId;
    

    public PagoIncapacidad() {
        this.id = 0;
        this.totalIncapacidad = 0.0;
        this.pagoId = 0;
    }
    public PagoIncapacidad(Double totalIncapacidad, int pagoId) {
        this.id = 0;
        this.totalIncapacidad = totalIncapacidad;
        this.pagoId = pagoId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotalIncapacidad() {
        return totalIncapacidad;
    }

    public void setTotalIncapacidad(double totalIncapacidad) {
        this.totalIncapacidad = totalIncapacidad;
    }

    public int getPagoId() {
        return pagoId;
    }

    public void setPagoId(int pagoId) {
        this.pagoId = pagoId;
    }
    
    
}
