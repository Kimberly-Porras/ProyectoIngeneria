/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.Vacaciones;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class VacacionesDAO {

    PreparedStatement ps;
    ResultSet rs;
    Connection acceso = Database.DatabaseConnection.getConnection();

    public List<Vacaciones> obtenerListaVacaciones() {
        Vacaciones vacaciones;
        List<Vacaciones> lista = new ArrayList<>();

        try {
            String sql = "SELECT id,"
                    + "empleado, monto, fecha, status "
                    + "FROM tbl_vacaciones;";

            ps = acceso.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                vacaciones = new Vacaciones();
                vacaciones.setId(rs.getInt(1));
                vacaciones.setEmpleado(rs.getString(2));
                vacaciones.setMonto(rs.getDouble(3));
                vacaciones.setFecha(rs.getDate(4));
                vacaciones.setStatus(rs.getBoolean(5));
                lista.add(vacaciones);
            }
        } catch (Exception e) {
            System.out.println("" + e.toString());
        }

        return lista;
    }

    public boolean insertarVacacion(Vacaciones vacacion) {
        try {
            String sql = "INSERT INTO tbl_vacaciones"
                    + "(empleado, monto, fecha, status) "
                    + "VALUES (?,?,?,?);";

            
            ps = acceso.prepareStatement(sql);
            ps.setObject(1, vacacion.getEmpleado());
            ps.setObject(2, vacacion.getMonto());
            ps.setObject(3, vacacion.getFecha());
            ps.setObject(4, vacacion.isStatus());

            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("" + e.toString());
            return false;
        }

    }

    public boolean actualizarVacacion(Vacaciones vacacion) {
        try {

            String sql = "UPDATE tbl_vacaciones"
                    + "SET empleado = ?, monto = ?, fecha = ?, status = ?"
                    + " WHERE id = ?;";
          
            ps = acceso.prepareStatement(sql);
            ps.setObject(1, vacacion.getEmpleado());
            ps.setObject(2, vacacion.getMonto());
            ps.setObject(3, vacacion.getFecha());
            ps.setObject(4, vacacion.isStatus());
            ps.setObject(5, vacacion.getId());

            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("" + e.toString());
            return false;
        }
    }
    
    public List<Vacaciones> obtenerListaVacacionesPorCedulaEmpleado(String cedulaEmpleado) {
        Vacaciones vacaciones;
        List<Vacaciones> lista = new ArrayList<>();

        try {
            String sql = "SELECT id, empleado, "
                    + "monto, fecha, status FROM tbl_vacaciones "
                    + "WHERE empleado = ?;";

            ps = acceso.prepareStatement(sql);
            ps.setObject(1, cedulaEmpleado);
            rs = ps.executeQuery();

            while (rs.next()) {
                vacaciones = new Vacaciones();
                vacaciones.setId(rs.getInt(1));
                vacaciones.setFecha(rs.getDate(2));
                vacaciones.setMonto(rs.getDouble(3));
                vacaciones.setStatus(rs.getBoolean(4));
                lista.add(vacaciones);
            }
        } catch (Exception e) {
            System.out.println("" + e.toString());
        }

        return lista;
    }
}
