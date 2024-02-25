/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author aleke
 */
public class PagoBitacora {

    private int id;
    private double totalBitacora;
    private int pagoId;

    public PagoBitacora() {
        this.id = 0;
        this.totalBitacora = 0.0;
        this.pagoId = 0;
    }

    public PagoBitacora(double total, int pagoId) {
        this.id = 0;
        this.totalBitacora = total;
        this.pagoId = pagoId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotalBitacora() {
        return totalBitacora;
    }

    public void setTotalBitacora(double totalBitacora) {
        this.totalBitacora = totalBitacora;
    }

    public int getPagoId() {
        return pagoId;
    }

    public void setPagoId(int pagoId) {
        this.pagoId = pagoId;
    }
}
