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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author alber
 * @author kim03
 */
public class ActividadDAO {
    
    private Connection acceso = Database.DatabaseConnection.getConnection();
    PreparedStatement ps;
    ResultSet rs;
    
    public ArrayList<Actividad> obtenerTodos() {
        var actividades = new ArrayList<Actividad>();
        try {
            ps = acceso.prepareStatement("SELECT id, nombre, status FROM tbl_actividad");
            rs = ps.executeQuery();
            
            while (rs.next()) {
                actividades.add(new Actividad(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getBoolean(3)
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ActividadDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return actividades;
    }
    
    
    public boolean insertarActividad(Actividad actividad) {
        try {
            String sql = "INSERT INTO tbl_actividad (nombre, status) VALUES (?, ?);";

           
            ps = acceso.prepareStatement(sql);
            ps.setObject(1, actividad.getNombre());
            ps.setObject(2, actividad.isStatus());

            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("" + e.toString());
            return false;
        }

    }

    public boolean actualizarActividad(Actividad actividad) {
        try {

            String sql = "UPDATE tbl_actividad "
                    + "SET nombre = ?, "
                    + "status = ? "
                    + "WHERE id = ?;";
            
            ps = acceso.prepareStatement(sql);
            ps.setObject(1, actividad.getNombre());
            ps.setObject(2, actividad.isStatus());
            ps.setObject(3, actividad.getId());

            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("" + e.toString());
            return false;
        }
    }
    public Actividad obtenerPorId(int id) {
        Actividad tipoDeduccion = null;
        
        try {
            ps = acceso.prepareStatement("SELECT id, nombre, status FROM tbl_actividad where id = ?");
            ps.setObject(1, id);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                tipoDeduccion = new Actividad(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getBoolean(3)
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(TipoDeduccionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return tipoDeduccion;
    }
    
    public List<Actividad> obtenerListaActividades() {
        List<Actividad> listaActividades = new ArrayList<>();

        try {
            String sql = "SELECT id, nombre, status FROM tbl_actividad;";
            
            ps = acceso.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Actividad actividad = new Actividad();
                actividad.setId(rs.getInt(1));
                actividad.setNombre(rs.getString(2));
                actividad.setStatus(rs.getBoolean(3));
                listaActividades.add(actividad);
            }
        } catch (Exception e) {
            System.out.println("" + e.toString());
        }

        return listaActividades;
    }
}
