/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.time.LocalDate;
import java.sql.Date;

/**
 *
 * @author alber
 * @author kim03
 */
public class Pagos {
    
    private int id;
    private Date fecha;
    private String empleado;
    private Date fechaFinal;
    private boolean status;

    public Pagos() {
        this.id = 0;
        this.fecha = new java.sql.Date(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());
        this.empleado = "";
        this.fechaFinal = new java.sql.Date(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());
        this. status = false;
    }
    public Pagos(int id, Date fecha, String empleado, Date fechaFinal) {
        this.id = id;
        this.fecha = fecha;
        this.empleado = empleado;
        this.fechaFinal = fechaFinal;
        this.status = true;
    }
    
    public Pagos(int id, Date fecha, String empleado, Date fechaFinal, boolean status) {
        this.id = id;
        this.fecha = fecha;
        this.empleado = empleado;
        this.fechaFinal = fechaFinal;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
