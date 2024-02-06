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
public class BitacoraAsistencia {
    
    private int id;
    private Date fecha;
    private byte estaPresente;
    private byte justifica;
    private String empleado;
    private byte status;
    
    public BitacoraAsistencia() {
        this.id = 0;
        this.fecha = new java.sql.Date(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());
        this.estaPresente = 0;
        this.justifica = 0;
        this.empleado = "";
        this.status = 0;
    }

    public BitacoraAsistencia(int id, Date fecha, byte esta_presente, byte justifica, String empleado, byte status) {
        this.id = id;
        this.fecha = fecha;
        this.estaPresente = esta_presente;
        this.justifica = justifica;
        this.empleado = empleado;
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

    public byte getEsta_presente() {
        return estaPresente;
    }

    public void setEsta_presente(byte esta_presente) {
        this.estaPresente = esta_presente;
    }

    public byte getJustifica() {
        return justifica;
    }

    public void setJustifica(byte justifica) {
        this.justifica = justifica;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }
}
