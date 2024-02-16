/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.Contrato;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class ContratoDAO {
    PreparedStatement ps;
    ResultSet rs;
    Connection acceso = Database.DatabaseConnection.getConnection();
    
    public List<Contrato> obtenerListaContratos() {
        Contrato contrato;
        List<Contrato> lista = new ArrayList<>();

        try {
            String sql = "SELECT id, cedula_empleado, fechaInicio, fechaFinal, fechaRegistro, "
                    + "monto, status, motivo "
                    + "FROM tbl_contrato;";
            
            ps = acceso.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                contrato = new Contrato();
                contrato.setId(rs.getInt(1));
                contrato.setCedulaEmpleado(rs.getString(2));
                contrato.setFechaInicio(rs.getDate(3));
                contrato.setFechaFinal(rs.getDate(4));
                contrato.setFechaRegistro(rs.getDate(5));
                contrato.setMonto(rs.getDouble(6));
                contrato.setStatus(rs.getBoolean(7));
                contrato.setMotivo(rs.getInt(8));
                lista.add(contrato);
            }
        } catch (Exception e) {
            System.out.println("" + e.toString());
        }

        return lista;
    }
    
    public boolean insertarContrato(Contrato contrato) {
        try {
            String sql = "INSERT INTO tbl_contrato (cedula_empleado, fechaInicio, fechaFinal, "
                    + "fechaRegistro, monto, status, motivo) "
                    + "VALUES (?,?,?,?,?,?,?);";

            
            ps = acceso.prepareStatement(sql);
            ps.setObject(1, contrato.getCedulaEmpleado());
            ps.setObject(2, contrato.getFechaInicio());
            ps.setObject(3, contrato.getFechaFinal());
            ps.setObject(4, contrato.getFechaRegistro());
            ps.setObject(5, contrato.getMonto());
            ps.setObject(6, contrato.isStatus());
            ps.setObject(7, contrato.getMotivo());

            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("" + e.toString());
            return false;
        }

    }

    public boolean actualizarContrato(Contrato contrato) {
        try {

            String sql = "UPDATE tbl_contrato SET cedula_empleado = ?, fechaInicio = ?, fechaFinal = ?, "
                    + "fechaRegistro = ?, monto = ?, estado = ? , motivo = ? "
                    + "WHERE id = ?;";
            
            ps = acceso.prepareStatement(sql);
            ps.setObject(1, contrato.getCedulaEmpleado());
            ps.setObject(2, contrato.getFechaInicio());
            ps.setObject(3, contrato.getFechaFinal());
            ps.setObject(4, contrato.getFechaRegistro());
            ps.setObject(5, contrato.getMonto());
            ps.setObject(6, contrato.isStatus());
            ps.setObject(7, contrato.getMotivo());
            ps.setObject(8, contrato.getId());

            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("" + e.toString());
            return false;
        }
    }
    
    public List<Contrato> obtenerListaContratoPorCedulaEmpleado(String cedulaEmpleado) {
        Contrato contrato;
        List<Contrato> lista = new ArrayList<>();

        try {
            String sql = "SELECT id, cedula_empleado, fechaInicio, fechaFinal, fechaRegistro,"
                    + " monto, status,motivo FROM tbl_contrato "
                    + "WHERE cedula_empleado = ?;";
            ps = acceso.prepareStatement(sql);
            ps.setObject(1, cedulaEmpleado);
            rs = ps.executeQuery();

            while (rs.next()) {
                contrato = new Contrato();
                contrato.setId(rs.getInt(1));
                contrato.setCedulaEmpleado(rs.getString(2));
                contrato.setFechaInicio(rs.getDate(3));
                contrato.setFechaFinal(rs.getDate(4));
                contrato.setFechaRegistro(rs.getDate(5));
                contrato.setMonto(rs.getDouble(6));
                contrato.setStatus(rs.getBoolean(7));
                contrato.setMotivo(rs.getInt(8));
                lista.add(contrato);
            }
        } catch (Exception e) {
            System.out.println("" + e.toString());
        }

        return lista;
    }
}
