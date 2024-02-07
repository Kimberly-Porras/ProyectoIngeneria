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
public class Contrato {
    
     private int id;
    private String cedulaEmpleado;
    private Date fechaInicio;
    private Date fechaFinal;
    private Date fechaRegistro;
    private double monto;
    private byte status;
    private int actividad;

    public Contrato() {
        this.id = 0;
        this.cedulaEmpleado = "";
        this.fechaInicio = new java.sql.Date(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());
        this.fechaFinal = new java.sql.Date(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());
        this.fechaRegistro = new java.sql.Date(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());
        this.monto = 0.0;
        this.status = 0;
        this.actividad = 0;
    }
    public Contrato(int id, String cedula_empleado, Date fecha_inicio, Date fecha_final, Date fecha_registro, double monto, byte status, int actividad) {
        this.id = id;
        this.cedulaEmpleado = cedula_empleado;
        this.fechaInicio = fecha_inicio;
        this.fechaFinal = fecha_final;
        this.fechaRegistro = fecha_registro;
        this.monto = monto;
        this.status = status;
        this.actividad = actividad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCedulaEmpleado() {
        return cedulaEmpleado;
    }

    public void setCedulaEmpleado(String cedulaEmpleado) {
        this.cedulaEmpleado = cedulaEmpleado;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public int getActividad() {
        return actividad;
    }

    public void setActividad(int actividad) {
        this.actividad = actividad;
    }
}