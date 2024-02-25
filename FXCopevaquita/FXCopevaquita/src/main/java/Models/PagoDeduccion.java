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
    
    private int id;
    private  double totalDeduccion;
    private int idPago;
    

    public PagoDeduccion() {
        this.id = 0;
        this.totalDeduccion = 0.0;
        this.idPago = 0;
    }
    public PagoDeduccion(Double totalDeduccion, int idPago) {
        this.id = 0;
        this.totalDeduccion = totalDeduccion;
        this.idPago = idPago;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotalDeduccion() {
        return totalDeduccion;
    }

    public void setTotalDeduccion(double totalDeduccion) {
        this.totalDeduccion = totalDeduccion;
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

}
