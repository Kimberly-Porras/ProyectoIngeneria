/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.Pagos;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alber
 * @author kim03
 */
public class PagosDAO {

    PreparedStatement ps;
    ResultSet rs;
    Connection acceso = Database.DatabaseConnection.getConnection();

    public List<Pagos> obtenerListaPagos() {
        List<Pagos> listaPagos = new ArrayList<>();
        try {
            String sql = "SELECT `id`, `fecha`, `empleado`, `fecha_final`, status FROM `tbl_pagos` WHERE 1";
            ps = acceso.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Pagos pago = new Pagos(
                        rs.getInt(1),
                        rs.getDate(2),
                        rs.getString(3),
                        rs.getDate(4),
                        rs.getBoolean(5)
                );
                listaPagos.add(pago);
            }
        } catch (Exception e) {
            System.out.println("" + e.toString());
        }

        return listaPagos;
    }

    public List<Pagos> obtenerPagosPorEmpleadoPosterioresAUnaFecha(String id, String date, String date2) {
        List<Pagos> listaPagos = new ArrayList<>();
        
        try {
            String sql = "SELECT `id`, `fecha`, `empleado`, `fecha_final`, status FROM `tbl_pagos` WHERE empleado = ? "
                    + "AND fecha >= ? AND fecha <= ? AND status = 1";
            ps = acceso.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, date);
            ps.setString(3, date2);
            rs = ps.executeQuery();

            while (rs.next()) {
                Pagos pago = new Pagos(
                        rs.getInt(1),
                        rs.getDate(2),
                        rs.getString(3),
                        rs.getDate(4),
                        rs.getBoolean(5)
                );
                listaPagos.add(pago);
            }
        } catch (Exception e) {
            System.out.println("" + e.toString());
        }
        System.out.println("el total de pagos: " + listaPagos.size());
        return listaPagos;
    }
    
     public List<Pagos> obtenerPagosPorEmpleadoPosterioresAUnaFechaExportar(String id, String date, String date2) {
        List<Pagos> listaPagos = new ArrayList<>();
        
        try {
            String sql = "SELECT `id`, `fecha`, `empleado`, `fecha_final`, status FROM `tbl_pagos` WHERE empleado = ? "
                    + "AND fecha >= ? AND fecha <= ? AND status = 0";
            ps = acceso.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, date);
            ps.setString(3, date2);
            rs = ps.executeQuery();

            while (rs.next()) {
                Pagos pago = new Pagos(
                        rs.getInt(1),
                        rs.getDate(2),
                        rs.getString(3),
                        rs.getDate(4),
                        rs.getBoolean(5)
                );
                listaPagos.add(pago);
            }
        } catch (Exception e) {
            System.out.println("" + e.toString());
        }
        System.out.println("el total de pagos: " + listaPagos.size());
        return listaPagos;
    }

    public boolean insertarPagos(Pagos pagos) {
        try {
            String sql = "INSERT INTO tbl_pagos "
                    + "(fecha, empleado, fecha_final, status) "
                    + "VALUES (?,?,?,?);";

            ps = acceso.prepareStatement(sql);
            ps.setObject(1, pagos.getFecha());
            ps.setObject(2, pagos.getEmpleado());
            ps.setObject(3, pagos.getFechaFinal());
            ps.setObject(4, pagos.isStatus());

            ps.executeUpdate();

            return true;
        } catch (Exception e) {
            System.out.println("" + e.toString());
            return false;
        }

    }

    public int obtenerIdPago(String cedula, Date inicio, Date fin) {
        var id = 0;
        try {
            String sql = "SELECT id from tbl_pagos where "
                    + "fecha = ? AND empleado = ? AND fecha_final = ? ";

            ps = acceso.prepareStatement(sql);
            ps.setObject(1, inicio);
            ps.setObject(2, cedula);
            ps.setObject(3, fin);
            rs = ps.executeQuery();
            // Obtener el ID generado
            while (rs.next()) {
                id = rs.getInt(1);
            }
            return id;

        } catch (Exception e) {
            System.out.println("" + e.toString());
            return id;
        }
    }

    public boolean actualizarStatusPago(int status, int pagoId) {
        try {

            String sql = "UPDATE tbl_pagos "
                    + "SET status = ? "
                    + "WHERE id = ?";

            ps = acceso.prepareStatement(sql);
            ps.setObject(1, status);
            ps.setObject(2, pagoId);

            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

//    public List<Pagos> obtenerListaIncapacidadPorCedulaEmpleado(String cedulaEmpleado) {
//        Pagos pagos0;
//        List<Pagos> lista = new ArrayList<>();
//
//        try {
//            String sql = "SELECT id, fecha, "
//                    + "monto, motivo, empleado, status FROM tbl_incapacidad "
//                    + "WHERE empleado = ?;";
//
//            ps = acceso.prepareStatement(sql);
//            ps.setObject(1, cedulaEmpleado);
//            rs = ps.executeQuery();
//
//            while (rs.next()) {
//                incapacidad = new Incapacidad();
//                incapacidad.setId(rs.getInt(1));
//                incapacidad.setFecha(rs.getDate(2));
//                incapacidad.setMonto(rs.getDouble(3));
//                incapacidad.setMotivo(rs.getString(4));
//                incapacidad.setEmpleado(rs.getString(5));
//                incapacidad.setStatus(rs.getBoolean(6));
//                lista.add(incapacidad);
//            }
//        } catch (Exception e) {
//            System.out.println("" + e.toString());
//        }
//
//        return lista;
//    }
}
