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
    
    
    public BitacoraAsistencia() {
        this.id = 0;
        this.fecha = new java.sql.Date(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());
        this.estaPresente = false;
        this.justifica = false;
        this.empleado = "";
       
    }

    public BitacoraAsistencia(int id, Date fecha, boolean esta_presente, boolean justifica, String empleado) {
        this.id = id;
        this.fecha = fecha;
        this.estaPresente = esta_presente;
        this.justifica = justifica;
        this.empleado = empleado;
        
    }

    public boolean isJustifica() {
        return justifica;
    }

    public void setJustifica(boolean justifica) {
        this.justifica = justifica;
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

    public boolean isEstaPresente() {
        return estaPresente;
    }

    public void setEstaPresente(boolean estaPresente) {
        this.estaPresente = estaPresente;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

}
