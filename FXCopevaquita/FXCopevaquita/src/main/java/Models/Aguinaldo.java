/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.sql.Date;

/**
 *
 * @author aleke
 */
public class Aguinaldo {

    private int id;
    private double total;
    private Date fecha_ini;
    private Date fecha_fin;
    private String empleado;

    public Aguinaldo() {
        this.id = 0;
        this.total = 0;
        this.fecha_ini = null;
        this.fecha_fin = null;
        this.empleado = "";

    }

    public Aguinaldo(int id, double total, Date fecha_ini, Date fecha_fin, String empleado) {
        this.id = id;
        this.total = total;
        this.fecha_ini = fecha_ini;
        this.fecha_fin = fecha_fin;
        this.empleado = empleado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getFecha_ini() {
        return fecha_ini;
    }

    public void setFecha_ini(Date fecha_ini) {
        this.fecha_ini = fecha_ini;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }
}
