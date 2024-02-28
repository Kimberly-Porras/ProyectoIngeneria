/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.Area;
import java.sql.Connection;
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
public class AreaDAO {
    private Connection acceso = Database.DatabaseConnection.getConnection();
    PreparedStatement ps;
    ResultSet rs;
    
    public ArrayList<Area> obtenerTodos() {
        var actividades = new ArrayList<Area>();
        try {
            ps = acceso.prepareStatement("SELECT id, nombre, status FROM tbl_area");
            rs = ps.executeQuery();
            
            while (rs.next()) {
                actividades.add(new Area(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getBoolean(3)
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AreaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return actividades;
    }
    
    public boolean insertarArea(Area area) {
        try {
            String sql = "INSERT INTO tbl_area (nombre, status) VALUES (?, ?);";

           
            ps = acceso.prepareStatement(sql);
            ps.setObject(1, area.getNombre());
            ps.setObject(2, area.isStatus());

            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("" + e.toString());
            return false;
        }

    }

    public boolean actualizarArea(Area area) {
        try {

            String sql = "UPDATE tbl_area "
                    + "SET nombre = ?, "
                    + "status = ? "
                    + "WHERE id = ?;";
            
            ps = acceso.prepareStatement(sql);
            ps.setObject(1, area.getNombre());
            ps.setObject(2, area.isStatus());
            ps.setObject(3, area.getId());

            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("" + e.toString());
            return false;
        }
    } 
    public Area obtenerPorId(int id) {
        Area tipoDeduccion = null;
        
        try {
            ps = acceso.prepareStatement("SELECT id, nombre, status FROM tbl_area where id = ?");
            ps.setObject(1, id);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                tipoDeduccion = new Area(
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
    public List<Area> obtenerListaArea() {
        List<Area> listaAreas = new ArrayList<>();

        try {
            String sql = "SELECT id, nombre, status  "
                    + "FROM tbl_area;";
          
            ps = acceso.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Area area = new Area();
                area.setId(rs.getInt(1));
                area.setNombre(rs.getString(2));
                area.setStatus(rs.getBoolean(3));
                listaAreas.add(area);
            }
        } catch (Exception e) {
            System.out.println("" + e.toString());
        }

        return listaAreas;
    }
}
