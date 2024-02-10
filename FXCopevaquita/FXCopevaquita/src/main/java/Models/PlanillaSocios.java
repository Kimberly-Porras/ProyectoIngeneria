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
public class PlanillaSocios {
    
     private int id;
    private String empleado;
    private double monto;
    private boolean status;

    public PlanillaSocios() {
        this.id = 0;
        this.empleado = "";
        this.monto = 0.0;
        this.status = false;
    }
    public PlanillaSocios(int id, String empleado, double monto, boolean status) {
        this.id = id;
        this.empleado = empleado;
        this.monto = monto;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
}
