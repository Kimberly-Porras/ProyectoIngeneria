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
    private boolean estaPresente;
    private boolean justifica;
    private String empleado;
    private boolean status;
    
    public BitacoraAsistencia() {
        this.id = 0;
        this.fecha = new java.sql.Date(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());
        this.estaPresente = false;
        this.justifica = false;
        this.empleado = "";
        this.status = false;
    }

    public BitacoraAsistencia(int id, Date fecha, boolean esta_presente, boolean justifica, String empleado, boolean status) {
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

    public boolean getEsta_presente() {
        return estaPresente;
    }

    public void setEsta_presente(boolean esta_presente) {
        this.estaPresente = esta_presente;
    }

    public boolean getJustifica() {
        return justifica;
    }

    public void setJustifica(boolean justifica) {
        this.justifica = justifica;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
