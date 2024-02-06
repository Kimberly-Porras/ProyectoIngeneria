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
public class Abono {
     private int id;
    private int deduccion;
    private double monto;
    private Date fecha;
    private String nota;

    public Abono() {
        this.id = 0;
        this.deduccion = 0;
        this.monto = 0.0;
        this.fecha = new java.sql.Date(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());
        this.nota = "";
    }
    
     public Abono(int id, int deduccion, double monto, Date fecha, String nota) {
        this.id = id;
        this.deduccion = deduccion;
        this.monto = monto;
        this.fecha = fecha;
        this.nota = nota;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDeduccion() {
        return deduccion;
    }

    public void setDeduccion(int deduccion) {
        this.deduccion = deduccion;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }
}
