/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.Incapacidad;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class IncapacidadDAO {

    PreparedStatement ps;
    ResultSet rs;
    Connection acceso = Database.DatabaseConnection.getConnection();

    public List<Incapacidad> obtenerListaIncapacidades() {
        Incapacidad incapacidad;
        List<Incapacidad> lista = new ArrayList<>();

        try {
            String sql = "SELECT id,"
                    + "fecha, monto, motivo, empleado, status "
                    + "FROM tbl_incapacidad;";

            ps = acceso.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                incapacidad = new Incapacidad();
                incapacidad.setId(rs.getInt(1));
                incapacidad.setFecha(rs.getDate(2));
                incapacidad.setMonto(rs.getDouble(3));
                incapacidad.setMotivo(rs.getString(4));
                incapacidad.setEmpleado(rs.getString(5));
                incapacidad.setStatus(rs.getBoolean(6));
                lista.add(incapacidad);
            }
        } catch (Exception e) {
            System.out.println("" + e.toString());
        }

        return lista;
    }

    public List<Incapacidad> obtenerListaIncapacidadesEntreFechas(String cedula, Date fechaInicio, Date fechaFin) {
        Incapacidad incapacidad;
        List<Incapacidad> lista = new ArrayList<>();

        try {
            String sql = "SELECT id, fecha, monto, motivo, empleado, status "
                    + "FROM tbl_incapacidad "
                    + "WHERE fecha BETWEEN ? AND ? AND empleado = ?";

            ps = acceso.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(fechaInicio.getTime()));
            ps.setDate(2, new java.sql.Date(fechaFin.getTime()));
            ps.setString(3, cedula);
            System.out.println(ps.toString());

            rs = ps.executeQuery();

            while (rs.next()) {
                incapacidad = new Incapacidad();
                incapacidad.setId(rs.getInt(1));
                incapacidad.setFecha(rs.getDate(2));
                incapacidad.setMonto(rs.getDouble(3));
                incapacidad.setMotivo(rs.getString(4));
                incapacidad.setEmpleado(rs.getString(5));
                incapacidad.setStatus(rs.getBoolean(6));
                lista.add(incapacidad);
            }
        } catch (Exception e) {
            System.out.println("" + e.toString());
        }

        return lista;
    }

    public boolean insertarIncapacidad(Incapacidad incapacidad) {
        try {
            String sql = "INSERT INTO tbl_incapacidad "
                    + "(fecha, monto, motivo, empleado, status) "
                    + "VALUES (?,?,?,?,?);";

            ps = acceso.prepareStatement(sql);
            ps.setObject(1, incapacidad.getFecha());
            ps.setObject(2, incapacidad.getMonto());
            ps.setObject(3, incapacidad.getMotivo());
            ps.setObject(4, incapacidad.getEmpleado());
            ps.setObject(5, incapacidad.isStatus());

            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("" + e.toString());
            return false;
        }

    }

    public boolean actualizarIncapacidad(Incapacidad incapacidad) {
        try {

            String sql = "UPDATE tbl_incapacidad "
                    + "SET fecha = ?, monto = ?, motivo = ?, empleado = ?, status = ? "
                    + "WHERE id = ?;";
            ps = acceso.prepareStatement(sql);
            ps.setObject(1, incapacidad.getFecha());
            ps.setObject(2, incapacidad.getMonto());
            ps.setObject(3, incapacidad.getMotivo());
            ps.setObject(4, incapacidad.getEmpleado());
            ps.setObject(5, incapacidad.isStatus());
            ps.setObject(6, incapacidad.getId());

            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("" + e.toString());
            return false;
        }
    }

    public List<Incapacidad> obtenerListaIncapacidadPorCedulaEmpleado(String cedulaEmpleado) {
        Incapacidad incapacidad;
        List<Incapacidad> lista = new ArrayList<>();

        try {
            String sql = "SELECT id, fecha, "
                    + "monto, motivo, empleado, status FROM tbl_incapacidad "
                    + "WHERE empleado = ?;";

            ps = acceso.prepareStatement(sql);
            ps.setObject(1, cedulaEmpleado);
            rs = ps.executeQuery();

            while (rs.next()) {
                incapacidad = new Incapacidad();
                incapacidad.setId(rs.getInt(1));
                incapacidad.setFecha(rs.getDate(2));
                incapacidad.setMonto(rs.getDouble(3));
                incapacidad.setMotivo(rs.getString(4));
                incapacidad.setEmpleado(rs.getString(5));
                incapacidad.setStatus(rs.getBoolean(6));
                lista.add(incapacidad);
            }
        } catch (Exception e) {
            System.out.println("" + e.toString());
        }

        return lista;
    }
}
