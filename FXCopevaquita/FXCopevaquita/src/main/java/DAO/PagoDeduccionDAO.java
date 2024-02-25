/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.PagoDeduccion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author User
 */
public class PagoDeduccionDAO {
    PreparedStatement ps;
    ResultSet rs;
    Connection acceso = Database.DatabaseConnection.getConnection();
    
    public boolean insertarDeduccion(PagoDeduccion pagoDeduccion) {
        try {
            String sql = "INSERT INTO tbl_pago_deduccion "
                    + "(totalDeduccion, idPago) "
                    + "VALUES (?,?);";

            ps = acceso.prepareStatement(sql);
            ps.setObject(1, pagoDeduccion.getTotalDeduccion());
            ps.setObject(2, pagoDeduccion.getIdPago());


            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("" + e.toString());
            return false;
        }
    }
}
