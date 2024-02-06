/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author alber
 * @author kim03
 */
public class BitacoraEmpleado {
    
    private int id;
    private String empleado;
    private int actividad;
    private int area;
    private Date fecha;
    private double costo;
    private double cantidad;
    private byte status;

    public BitacoraEmpleado() {
        this.id = 0;
        this.empleado = "";
        this.actividad = 0;
        this.area = 0;
        this.fecha = new java.sql.Date(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());
        this.costo = 0.0;
        this.cantidad = 0.0;
        this.status = 0;
    }

    public BitacoraEmpleado(int id, String empleado, int actividad, int area, Date fecha, double costo, double cantidad, byte status) {
        this.id = id;
        this.empleado = empleado;
        this.actividad = actividad;
        this.area = area;
        this.fecha = fecha;
        this.costo = costo;
        this.cantidad = cantidad;
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

    public int getActividad() {
        return actividad;
    }

    public void setActividad(int actividad) {
        this.actividad = actividad;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }
}
