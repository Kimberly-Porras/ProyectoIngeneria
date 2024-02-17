/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases.userSession;

/**
 *
 * @author aleke
 */
public class AppUser {

    public AppUser(String ced, String tipo, String nombreUsuario, String correo) {
        this.ced = ced;
        this.tipo = tipo;
        this.nombreUsuario = nombreUsuario;
        this.correo = correo;
    }
    private String ced;
    private String tipo;
    private String nombreUsuario;
    private String correo;

    public String getCed() {
        return ced;
    }

    public void setCed(String ced) {
        this.ced = ced;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
