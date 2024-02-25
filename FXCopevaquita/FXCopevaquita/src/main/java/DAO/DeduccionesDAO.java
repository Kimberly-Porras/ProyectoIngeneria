/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.Deduccion;
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
public class DeduccionesDAO {

    PreparedStatement ps;
    ResultSet rs;
    Connection acceso = Database.DatabaseConnection.getConnection();

    public List<Deduccion> obtenerListaDeduccion() {
        Deduccion deduccion;
        List<Deduccion> lista = new ArrayList<>();

        try {
            String sql = "SELECT id, tipo, monto, "
                    + "cuota, pendiente, empleado, status, fecha_registro "
                    + "FROM tbl_deduccion ;";

            ps = acceso.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                deduccion = new Deduccion();
                deduccion.setId(rs.getInt(1));
                deduccion.setTipo(rs.getInt(2));
                deduccion.setMonto(rs.getDouble(3));
                deduccion.setCuota(rs.getDouble(4));
                deduccion.setPendiente(rs.getDouble(5));
                deduccion.setEmpleado(rs.getString(6));
                deduccion.setStatus(rs.getBoolean(7));
                deduccion.setFecha_registro(rs.getDate(8));
                lista.add(deduccion);
            }
        } catch (Exception e) {
            System.out.println("" + e.toString());
        }

        return lista;
    }

    public List<Deduccion> obtenerListaDeduccionEntreFechas(String cedula, Date fechaInicio, Date fechaFin) {
        Deduccion deduccion;
        List<Deduccion> lista = new ArrayList<>();

        try {
            String sql = "SELECT id, tipo, monto, cuota, pendiente, empleado, status, fecha_registro "
                    + "FROM tbl_deduccion "
                    + "WHERE empleado = ? AND fecha_registro BETWEEN ? AND ?";

            ps = acceso.prepareStatement(sql);
            ps.setString(1, cedula);
            ps.setDate(2, new java.sql.Date(fechaInicio.getTime()));
            ps.setDate(3, new java.sql.Date(fechaFin.getTime()));
            rs = ps.executeQuery();

            while (rs.next()) {
                deduccion = new Deduccion();
                deduccion.setId(rs.getInt(1));
                deduccion.setTipo(rs.getInt(2));
                deduccion.setMonto(rs.getDouble(3));
                deduccion.setCuota(rs.getDouble(4));
                deduccion.setPendiente(rs.getDouble(5));
                deduccion.setEmpleado(rs.getString(6));
                deduccion.setStatus(rs.getBoolean(7));
                deduccion.setFecha_registro(rs.getDate(8));
                lista.add(deduccion);
            }
        } catch (Exception e) {
            System.out.println("" + e.toString());
        }

        return lista;
    }

    public boolean insertarDeduccion(Deduccion deduccion) {
        try {
            String sql = "INSERT INTO tbl_deduccion (tipo, monto, cuota, "
                    + "pendiente, empleado, status, fecha_registro) "
                    + "VALUES (?,?,?,?,?,?,?);";

            ps = acceso.prepareStatement(sql);
            ps.setObject(1, deduccion.getTipo());
            ps.setObject(2, deduccion.getMonto());
            ps.setObject(3, deduccion.getCuota());
            ps.setObject(4, deduccion.getPendiente());
            ps.setObject(5, deduccion.getEmpleado());
            ps.setObject(6, deduccion.isStatus());
            ps.setObject(7, deduccion.getFecha_registro());

            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("" + e.toString());
            return false;
        }

    }

    public boolean actualizarDeduccion(Deduccion deduccion) {
        try {

            String sql = "UPDATE tbl_deduccion SET tipo = ?, monto = ?, "
                    + "cuota = ?, pendiente = ? , empleado = ?, status = ?, fecha_registro = ? "
                    + "WHERE id = ?;";

            ps = acceso.prepareStatement(sql);
            ps.setObject(1, deduccion.getTipo());
            ps.setObject(2, deduccion.getMonto());
            ps.setObject(3, deduccion.getCuota());
            ps.setObject(4, deduccion.getPendiente());
            ps.setObject(5, deduccion.getEmpleado());
            ps.setObject(6, deduccion.isStatus());
            ps.setObject(7, deduccion.getFecha_registro());
            ps.setObject(8, deduccion.getId());

            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("" + e.toString());
            return false;
        }
    }

    public List<Deduccion> obtenerListaDeduccionesPorCedulaEmpleado(String cedulaEmpleado) {
        Deduccion deduccion;
        List<Deduccion> lista = new ArrayList<>();

        try {
            String sql = "SELECT id, tipo, monto, cuota, pendiente, empleado, status, fecha_registro FROM tbl_deduccion WHERE empleado = ?;";

            ps = acceso.prepareStatement(sql);
            ps.setObject(1, cedulaEmpleado);
            rs = ps.executeQuery();

            while (rs.next()) {
                deduccion = new Deduccion();
                deduccion.setId(rs.getInt(1));
                deduccion.setTipo(rs.getInt(2));
                deduccion.setMonto(rs.getDouble(3));
                deduccion.setCuota(rs.getDouble(4));
                deduccion.setPendiente(rs.getDouble(5));
                deduccion.setEmpleado(rs.getString(6));
                deduccion.setStatus(rs.getBoolean(7));
                deduccion.setFecha_registro(rs.getDate(8));
                lista.add(deduccion);
            }
        } catch (Exception e) {
            System.out.println("" + e.toString());
        }

        return lista;
    }
}
