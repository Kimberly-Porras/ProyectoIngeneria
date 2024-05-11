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
public class Credencial {
    
    private int id;
    private String empleado ;
    private String usuario;
    private String correo;
    private String contrasenia;
    private boolean status;

    public Credencial() {
        this.id = 0;
        this.empleado = "";
        this.usuario = "";
        this.contrasenia = "";
        this.correo = "";
        this.status = false;
    }
    public Credencial(int id, String empleado, String usuario, String contrasenia,String correo, boolean status) {
        this.id = id;
        this.empleado = empleado;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.correo = correo;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    
    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public boolean isStatus() {
        return status;
    }

}
