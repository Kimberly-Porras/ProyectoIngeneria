/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Models.PorcentajeRebajos;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aleke
 */
public class PorcentajeRebajosDAO {

    PreparedStatement ps;
    ResultSet rs;
    Connection acceso = Database.DatabaseConnection.getConnection();
    
    public PorcentajeRebajos obtenerPorcentajeRebajos() {
        PorcentajeRebajos por = new PorcentajeRebajos();

        try {
            String sql = "SELECT gobierno"
                    + " FROM tbl_porcentaje_rebajos;";

            ps = acceso.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                por.setGobierno(rs.getDouble(1));
            }

            return por;
            
        } catch (Exception e) {
            System.out.println("" + e.toString());
        }

        return null;
    }
    
    public boolean actualizarPorcentaje(PorcentajeRebajos porcentajeRebajos) {
        try {

            String sql = "UPDATE tbl_porcentaje_rebajos "
                    + "SET gobierno = ? "
                    + "WHERE id = ?";
            
            ps = acceso.prepareStatement(sql);
            ps.setObject(1, porcentajeRebajos.getGobierno());
            ps.setObject(2, porcentajeRebajos.getId());

            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("" + e.toString());
            return false;
        }
    }
}
