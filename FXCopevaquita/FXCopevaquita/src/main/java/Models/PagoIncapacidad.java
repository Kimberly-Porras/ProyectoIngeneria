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

    private int idIncapacidad;
    private int idPago;

    public PagoIncapacidad() {
        this.idIncapacidad = 0;
        this.idPago = 0;
    }

    public PagoIncapacidad(int idIncapacidad, int idPago) {
        this.idIncapacidad = idIncapacidad;
        this.idPago = idPago;
    }

    public int getIdIncapacidad() {
        return idIncapacidad;
    }

    public void setIdIncapacidad(int idIncapacidad) {
        this.idIncapacidad = idIncapacidad;
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }
}
