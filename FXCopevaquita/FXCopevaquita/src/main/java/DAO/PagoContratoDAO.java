/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.PagoContrato;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author User
 */
public class PagoContratoDAO {
    
    PreparedStatement ps;
    ResultSet rs;
    Connection acceso = Database.DatabaseConnection.getConnection();
    
     public boolean insertarIncapacidad(PagoContrato pagoContrato) {
        try {
            String sql = "INSERT INTO tbl_pago_contrato "
                    + "(totalContrato, pagoId) "
                    + "VALUES (?,?);";

            ps = acceso.prepareStatement(sql);
            ps.setObject(1, pagoContrato.getTotalContrato());
            ps.setObject(2, pagoContrato.getPagoId());


            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("" + e.toString());
            return false;
        }
    }
}
