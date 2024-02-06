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
public class PagoDeduccion {
    
    private int idPago;
    private int idDeduccion;

    public PagoDeduccion() {
        this.idPago = 0;
        this.idDeduccion = 0;
    }
    public PagoDeduccion(int id, int idDeduccion) {
        this.idPago = id;
        this.idDeduccion = idDeduccion;
    }

    public int getId() {
        return idPago;
    }

    public void setId(int id) {
        this.idPago = id;
    }

    public int getIdDeduccion() {
        return idDeduccion;
    }

    public void setIdDeduccion(int idDeduccion) {
        this.idDeduccion = idDeduccion;
    }
}
