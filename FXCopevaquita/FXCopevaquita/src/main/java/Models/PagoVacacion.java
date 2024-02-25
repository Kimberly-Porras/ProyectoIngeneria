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
public class PagoVacacion {

    private int id;
    private double totalVacacion;
    private int pagoId;

    public PagoVacacion() {
        this.id = 0;
        this.totalVacacion = 0.0;
        this.pagoId = 0;
    }

    public PagoVacacion(int id, Double totalVacacion, int pagoId) {
        this.id = id;
        this.totalVacacion = totalVacacion;
        this.pagoId = pagoId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotalVacacion() {
        return totalVacacion;
    }

    public void setTotalVacacion(double totalVacacion) {
        this.totalVacacion = totalVacacion;
    }

    public int getPagoId() {
        return pagoId;
    }

    public void setPagoId(int pagoId) {
        this.pagoId = pagoId;
    }
    
    
}
