/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.TipoDeduccion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class TipoDeduccionDAO {
    private Connection acceso = Database.DatabaseConnection.getConnection();
    PreparedStatement ps;
    ResultSet rs;
    
    public ArrayList<TipoDeduccion> obtenerTodos() {
        var tipoDeduccion = new ArrayList<TipoDeduccion>();
        try {
            ps = acceso.prepareStatement("SELECT id, nombre, status FROM tbl_tipo_deduccion");
            rs = ps.executeQuery();
            
            while (rs.next()) {
                tipoDeduccion.add(new TipoDeduccion(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getBoolean(3)
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TipoDeduccionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return tipoDeduccion;
    }
    
    public boolean insertarTipoDeduccion(TipoDeduccion tipoDeduccion) {
        try {
            String sql = "INSERT INTO tbl_tipo_deduccion (nombre, status) VALUES (?, ?);";

           
            ps = acceso.prepareStatement(sql);
            ps.setObject(1, tipoDeduccion.getNombre());
            ps.setObject(2, tipoDeduccion.isStatus());

            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("" + e.toString());
            return false;
        }

    }

    public boolean actualizarTipoDeduccion(TipoDeduccion tipoDeduccion) {
        try {

            String sql = "UPDATE tbl_tipo_deduccion "
                    + "SET nombre = ?, "
                    + "status = ? "
                    + "WHERE id = ?;";
            
            ps = acceso.prepareStatement(sql);
            ps.setObject(1, tipoDeduccion.getNombre());
            ps.setObject(2, tipoDeduccion.isStatus());
            ps.setObject(3, tipoDeduccion.getId());

            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("" + e.toString());
            return false;
        }
    }
}
