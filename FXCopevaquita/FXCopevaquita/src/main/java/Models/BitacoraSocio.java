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
public class BitacoraSocio {
    
     private int id;
    private String cedulaEmpleado;
    private double horas;
    private byte status;
    private String descripcion;

    public BitacoraSocio() {
        this.id = 0;
        this.cedulaEmpleado = "";
        this.horas = 0.0;
        this.status = 0;
        this.descripcion = "";
    }
    public BitacoraSocio(int id, String cedula_empleado, double horas, byte status, String descripcion) {
        this.id = id;
        this.cedulaEmpleado = cedula_empleado;
        this.horas = horas;
        this.status = status;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCedula_empleado() {
        return cedulaEmpleado;
    }

    public void setCedula_empleado(String cedula_empleado) {
        this.cedulaEmpleado = cedula_empleado;
    }

    public double getHoras() {
        return horas;
    }

    public void setHoras(double horas) {
        this.horas = horas;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
