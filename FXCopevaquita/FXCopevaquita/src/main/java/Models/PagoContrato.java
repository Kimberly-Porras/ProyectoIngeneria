/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author User
 */
public class PagoContrato {
    private int id;
    private double totalContrato;
    private int pagoId;
    
    public PagoContrato(){
    this.id = 0;
    this.totalContrato = 0.0;
    this.pagoId = 0;
    }
    
    public PagoContrato(int id, Double totalContrato, int pagoId){
    this.id = id;
    this.totalContrato = totalContrato;
    this.pagoId = pagoId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotalContrato() {
        return totalContrato;
    }

    public void setTotalContrato(double totalContrato) {
        this.totalContrato = totalContrato;
    }

    public int getPagoId() {
        return pagoId;
    }

    public void setPagoId(int pagoId) {
        this.pagoId = pagoId;
    }
    
    
}
