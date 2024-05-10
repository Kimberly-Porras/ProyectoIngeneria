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

    
    public ArrayList<PorcentajeRebajos> obtenerTodos() {
        var porcentajeRebajos = new ArrayList<PorcentajeRebajos>();
        try {
            ps = acceso.prepareStatement("SELECT id, gobierno, exceso1, exceso2, exceso3 FROM tbl_porcentaje_rebajos");
            rs = ps.executeQuery();
            
            while (rs.next()) {
                porcentajeRebajos.add(new PorcentajeRebajos(
                        rs.getInt(1),
                        rs.getDouble(2),
                        rs.getDouble(3),
                        rs.getDouble(4),
                        rs.getDouble(5)
                        
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TipoDeduccionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return porcentajeRebajos;
    }
    public PorcentajeRebajos obtenerPorcentajesRebajos() {
        PorcentajeRebajos por = new PorcentajeRebajos();

        try {
            String sql = "SELECT id, gobierno, exceso1, exceso2, exceso3"
                    + " FROM tbl_porcentaje_rebajos;";

            ps = acceso.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                por.setId(rs.getInt(1));
                por.setGobierno(rs.getInt(2));
                por.setExceso1(rs.getInt(3));
                por.setExceso2(rs.getInt(4));
                por.setExceso3(rs.getInt(5));
            }

            return por;
        } catch (Exception e) {
            System.out.println("" + e.toString());
        }

        return null;
    }
    
    public boolean actualizarTipoDeduccion(PorcentajeRebajos porcentajeRebajos) {
        try {

            String sql = "UPDATE tbl_porcentaje_rebajos "
                    + "SET gobierno = ?, "
                    + "exceso1 = ? , exceso2 = ?,exceso3 = ? "
                    + "WHERE id = ?;";
            
            ps = acceso.prepareStatement(sql);
            ps.setObject(1, porcentajeRebajos.getGobierno());
            ps.setObject(2, porcentajeRebajos.getExceso1());
            ps.setObject(3, porcentajeRebajos.getExceso2());
            ps.setObject(4, porcentajeRebajos.getExceso3());
            ps.setObject(5, porcentajeRebajos.getId());

            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("" + e.toString());
            return false;
        }
    }
}
