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
public class Deduccion {
    
    private int id;
    private int tipo;
    private double monto;
    private double cuota;
    private double pendiente;
    private String empleado;
    private boolean status;

    public Deduccion() {
        this.id = 0;
        this.tipo = 0;
        this.monto = 0.0;
        this.cuota = 0.0;
        this.pendiente = 0.0;
        this.empleado = "";
        this.status = false;
    }
    public Deduccion(int id, int tipo, double monto, double cuota, double pendiente, String empleado, boolean status) {
        this.id = id;
        this.tipo = tipo;
        this.monto = monto;
        this.cuota = cuota;
        this.pendiente = pendiente;
        this.empleado = empleado;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public double getCuota() {
        return cuota;
    }

    public void setCuota(double cuota) {
        this.cuota = cuota;
    }

    public double getPendiente() {
        return pendiente;
    }

    public void setPendiente(double pendiente) {
        this.pendiente = pendiente;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
