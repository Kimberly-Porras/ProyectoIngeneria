/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.BitacoraAsistencia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class BitacoraAsistenciaDAO {
    PreparedStatement ps;
    ResultSet rs;
    Connection acceso = Database.DatabaseConnection.getConnection();

    public List<BitacoraAsistencia> obtenerListaBitacoraAsitencia() {
        BitacoraAsistencia bitacoraAsistencia;
        List<BitacoraAsistencia> lista = new ArrayList<>();

        try {
            String sql = "SELECT id, fecha, estaPresente, "
                    + "justifica, empleado FROM tbl_bitacora_asistencia";
            
            ps = acceso.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                bitacoraAsistencia = new BitacoraAsistencia();
                bitacoraAsistencia.setId(rs.getInt(1));
                bitacoraAsistencia.setFecha(rs.getDate(2));
                bitacoraAsistencia.setEstaPresente(rs.getBoolean(3));
                bitacoraAsistencia.setJustifica(rs.getBoolean(4));
                bitacoraAsistencia.setEmpleado(rs.getString(5));
                lista.add(bitacoraAsistencia);
            }
        } catch (Exception e) {
            System.out.println("" + e.toString());
        }

        return lista;
    }
    
    public boolean insertarAsistencia(BitacoraAsistencia bitacoraAsistencia) {
        try {
            String sql = "INSERT INTO tbl_bitacora_asistencia "
                    + "(fecha, estaPresente, justifica, empleado) "
                    + "VALUES (?,?,?,?);";

            ps = acceso.prepareStatement(sql);
            ps.setObject(1, bitacoraAsistencia.getFecha());
            ps.setObject(2, bitacoraAsistencia.isEstaPresente());
            ps.setObject(3, bitacoraAsistencia.isJustifica());
            ps.setObject(4, bitacoraAsistencia.getEmpleado());

            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("" + e.toString());
            return false;
        }

    }

    public boolean actualizarAsistencia(BitacoraAsistencia bitacoraAsistencia) {
        try {

            String sql = "UPDATE tbl_bitacora_asistencia "
                    + "SET fecha = ?, estaPresente = ?, justifica = ?, empleado = ? "
                    + "WHERE id = ?;";
            ps = acceso.prepareStatement(sql);
            ps.setObject(1, bitacoraAsistencia.getFecha());
            ps.setObject(2, bitacoraAsistencia.isEstaPresente());
            ps.setObject(3, bitacoraAsistencia.isJustifica());
            ps.setObject(4, bitacoraAsistencia.getEmpleado());
            ps.setObject(6, bitacoraAsistencia.getId());

            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("" + e.toString());
            return false;
        }
    }
    
    public List<BitacoraAsistencia> obtenerListaAsietenciaPorCedulaEmpleado(String cedulaEmpleado) {
        BitacoraAsistencia bitacoraAsistencia;
        List<BitacoraAsistencia> lista = new ArrayList<>();

        try {
            String sql = "SELECT id, fecha, "
                    + "estaPresente, justifica, empleado FROM tbl_bitacora_asistencia "
                    + "WHERE empleado = ?;";

            ps = acceso.prepareStatement(sql);
            ps.setObject(1, cedulaEmpleado);
            rs = ps.executeQuery();

            while (rs.next()) {
                bitacoraAsistencia = new BitacoraAsistencia();
                bitacoraAsistencia.setId(rs.getInt(1));
                bitacoraAsistencia.setFecha(rs.getDate(2));
                bitacoraAsistencia.setEstaPresente(rs.getBoolean(3));
                bitacoraAsistencia.setJustifica(rs.getBoolean(4));
                bitacoraAsistencia.setEmpleado(rs.getString(5));
                
                lista.add(bitacoraAsistencia);
            }
        } catch (Exception e) {
            System.out.println("" + e.toString());
        }

        return lista;
    }
}
