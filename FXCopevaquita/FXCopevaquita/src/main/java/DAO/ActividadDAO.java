/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import Models.Actividad;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author alber
 * @author kim03
 */
public class ActividadDAO {
    
    private Connection conexionBaseDeDatos = Database.DatabaseConnection.getConnection();
    PreparedStatement ps;
    ResultSet rs;
    
    public ArrayList<Actividad> obtenerTodos() {
        var actividades = new ArrayList<Actividad>();
        try {
            ps = conexionBaseDeDatos.prepareStatement("SELECT id, nombre, status FROM tbl_actividad");
            rs = ps.executeQuery();
            
            while (rs.next()) {
                actividades.add(new Actividad(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getByte(3)
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ActividadDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return actividades;
    }
}
