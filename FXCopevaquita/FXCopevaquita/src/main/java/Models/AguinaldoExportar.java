/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author aleke
 */
public class AguinaldoExportar {
    private int id;
    private String nombre;
    private String apellidos;
    private String numeroCuenta;
    private String tipo;
    private double aguinaldoCalculado;

    public AguinaldoExportar(String nombre, String apellidos, String numeroCuenta, String tipo, double aguinaldoCalculado) {
        this.id = 0;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.numeroCuenta = numeroCuenta;
        this.tipo = tipo;
        this.aguinaldoCalculado = aguinaldoCalculado;
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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getAguinaldoCalculado() {
        return aguinaldoCalculado;
    }

    public void setAguinaldoCalculado(double aguinaldoCalculado) {
        this.aguinaldoCalculado = aguinaldoCalculado;
    }
}
