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
    
     private int idPago;
    private int idVacacion;

    public PagoVacacion() {
        this.idPago = 0;
        this.idVacacion = 0;
    }
    public PagoVacacion(int idPago, int idVacacion) {
        this.idPago = idPago;
        this.idVacacion = idVacacion;
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public int getIdVacacion() {
        return idVacacion;
    }

    public void setIdVacacion(int idVacacion) {
        this.idVacacion = idVacacion;
    }
}
