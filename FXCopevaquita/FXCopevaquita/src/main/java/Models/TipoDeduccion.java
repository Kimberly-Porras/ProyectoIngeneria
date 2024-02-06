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
public class TipoDeduccion {
    
    private int id;
    private String nombre;
    private byte status; 

    public TipoDeduccion() {
        this.id = 0;
        this.nombre = "";
        this.status = 0;
    }
    public TipoDeduccion(int id, String nombre, byte status) {
        this.id = id;
        this.nombre = nombre;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }
}
