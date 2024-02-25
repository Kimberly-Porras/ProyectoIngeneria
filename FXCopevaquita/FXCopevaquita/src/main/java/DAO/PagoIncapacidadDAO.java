/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.PagoIncapacidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author User
 */
public class PagoIncapacidadDAO {
    PreparedStatement ps;
    ResultSet rs;
    Connection acceso = Database.DatabaseConnection.getConnection();
    
    public boolean insertarIncapacidad(PagoIncapacidad pagoIncapacidad) {
        try {
            String sql = "INSERT INTO tbl_pago_incapacidad "
                    + "(totalIncapacidad, pagoId) "
                    + "VALUES (?,?);";

            ps = acceso.prepareStatement(sql);
            ps.setObject(1, pagoIncapacidad.getTotalIncapacidad());
            ps.setObject(2, pagoIncapacidad.getPagoId());


            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("" + e.toString());
            return false;
        }
    }
}
