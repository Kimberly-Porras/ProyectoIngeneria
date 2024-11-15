/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;
import java.sql.Date;
/**
 *
 * @author alber
 * @author kim03
 */
public class BitacoraSocio {
    
    private int id;
    private String cedulaEmpleado;
    private double horas;
    private boolean status;
    private String descripcion;
    private Date fechaRegistro;

    public BitacoraSocio() {
        this.id = 0;
        this.cedulaEmpleado = "";
        this.horas = 0.0;
        this.status = false;
        this.descripcion = "";
    }
    public BitacoraSocio(int id, String cedula_empleado, double horas, boolean status, String descripcion, Date fechaRegistro) {
        this.id = id;
        this.cedulaEmpleado = cedula_empleado;
        this.horas = horas;
        this.status = status;
        this.descripcion = descripcion;
        this.fechaRegistro = fechaRegistro;
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
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCedulaEmpleado() {
        return cedulaEmpleado;
    }

    public void setCedulaEmpleado(String cedulaEmpleado) {
        this.cedulaEmpleado = cedulaEmpleado;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
}
