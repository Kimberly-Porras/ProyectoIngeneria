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
public class Empleado {
    private String cedula;
    private String nombre;
    private String apellidos;
    private String sexo;
    private String estadoCivil;
    private String tipoSangre;
    private Date fechaNacimiento;
    private Date fechaIngreso;
    private String tipo;
    private String numeroCuenta;
    private String nivelAcademico;
    private boolean status;
    
    public Empleado() {
        this.cedula = "";
        this.nombre = "";
        this.apellidos = "";
        this.sexo = "";
        this.estadoCivil = "";
        this.tipoSangre = "";
        this.fechaNacimiento = new java.sql.Date(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());
        this.fechaIngreso = new java.sql.Date(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());
        this.tipo = "";
        this.numeroCuenta = "";
        this.nivelAcademico = "";
        this.status = false;
    }
    public Empleado(String cedula, String nombre, String apellidos, String sexo, String estadoCivil, String tipoSangre, Date fechaNacimiento, Date fechaIngreso, String tipo, String numeroCuenta, String nivelAcademico, boolean status) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.sexo = sexo;
        this.estadoCivil = estadoCivil;
        this.tipoSangre = tipoSangre;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaIngreso = fechaIngreso;
        this.tipo = tipo;
        this.numeroCuenta = numeroCuenta;
        this.nivelAcademico = nivelAcademico;
        this.status = status;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getNivelAcademico() {
        return nivelAcademico;
    }

    public void setNivelAcademico(String nivelAcademico) {
        this.nivelAcademico = nivelAcademico;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
