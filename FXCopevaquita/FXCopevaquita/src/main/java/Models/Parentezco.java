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
public class Parentezco {
    private String cedula;
    private String empleado;
    private String nombre;
    private String apellidos;
    private Date fechaNacimiento;
    private String sexo;
    private byte status;
    private String parentezco;

    public Parentezco() {
        this.cedula = "";
        this.empleado = "";
        this.nombre = "";
        this.apellidos = "";
        this.fechaNacimiento = new java.sql.Date(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());
        this.sexo = "";
        this.status = 0;
        this.parentezco = "";
    }
 
    public Parentezco(String cedula, String empleado, String nombre, String apellidos, Date fechaNacimiento, String sexo, byte status, String parentezco) {
        this.cedula = cedula;
        this.empleado = empleado;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.status = status;
        this.parentezco = parentezco;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public String getParentezco() {
        return parentezco;
    }

    public void setParentezco(String parentezco) {
        this.parentezco = parentezco;
    }
}
