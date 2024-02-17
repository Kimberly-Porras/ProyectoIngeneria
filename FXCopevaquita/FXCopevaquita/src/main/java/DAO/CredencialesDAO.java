/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Database.DatabaseConnection;
import Models.Credencial;

/**
 *
 * @author aleke
 */
public class CredencialesDAO {

    PreparedStatement ps;
    ResultSet rs;
    Connection acceso = DatabaseConnection.getConnection();

    public Credencial obtenerCredencialPorUsuarioYContrasena(String usuario, String contrasena) {
        Credencial credencial = new Credencial();

        try {
            String sql = "SELECT `id`, `empleado`, `usuario`, `status`,"
                    + " `status` FROM `tbl_credencial` WHERE "
                    + "`usuario` = ? and `contrasenia` = ?";
            ps = acceso.prepareStatement(sql);
            ps.setObject(1, usuario);
            ps.setObject(2, contrasena);
            rs = ps.executeQuery();

            while (rs.next()) {
                credencial.setId(rs.getInt(1));
                credencial.setEmpleado(rs.getString(2));
                credencial.setUsuario(rs.getString(3));
                credencial.setStatus(rs.getBoolean(4));
                break;
            }
        } catch (Exception e) {
            System.out.println("" + e.toString());
            return null;
        }

        return credencial;
    }

//    public boolean insertarCredencial(Credencial credencial) {
//        try {
//            String sql = "INSERT INTO tbl_credencial (cedula_empleado, tipo, "
//                    + "usuario, contrasenna, correo, estado) "
//                    + "VALUES (?,?,?,?,?,?);";
//
//            ps = acceso.prepareStatement(sql);
//            ps.setObject(1, credencial.getEmpleado());
//            ps.setObject(2, credencial.getTipo());
//            ps.setObject(3, credencial.getUsuario());
//            ps.setObject(4, credencial.getContrasenna());
//            ps.setObject(5, credencial.getCorreo());
//            ps.setObject(6, credencial.isEstado());
//
//            ps.executeUpdate();
//            return true;
//        } catch (Exception e) {
//            System.out.println("" + e.toString());
//            return false;
//        }
//
//    }
//    
    
    public boolean actualizarContrasenia(String correo, String nuevaContraseña) {
    try {
        String sql = "UPDATE tbl_credencial SET contrasenia = ? WHERE correo = ?";
        ps = acceso.prepareStatement(sql);
        ps.setString(1, nuevaContraseña);
        ps.setString(2, correo);

        int filasAfectadas = ps.executeUpdate();
        return filasAfectadas > 0;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}
    
//
//
//    public boolean actualizarCredencial(Credencial credencial) {
//        try {
//
//            String sql = "UPDATE tbl_credencial SET cedula_empleado = ?, tipo = ?, "
//                    + "usuario = ?, contrasenna = ?, correo = ?, estado = ? "
//                    + "WHERE id = ?;";
//            ps = acceso.prepareStatement(sql);
//            ps.setObject(1, credencial.getCedula_empleado());
//            ps.setObject(2, credencial.getTipo());
//            ps.setObject(3, credencial.getUsuario());
//            ps.setObject(4, credencial.getContrasenna());
//            ps.setObject(5, credencial.getCorreo());
//            ps.setObject(6, credencial.isEstado());
//
//            ps.executeUpdate();
//            return true;
//        } catch (Exception e) {
//            System.out.println("" + e.toString());
//            return false;
//        }
//    }
//
//    public Credencial obtenerCredencialPorId(int id) {
//        Credencial credencial = new Credencial();
//
//        try {
//            String sql = "SELECT id, cedula_empleado, tipo, "
//                    + "usuario, contrasenna, correo, estado FROM tbl_credencial "
//                    + "WHERE id = ?;";
//            ps = acceso.prepareStatement(sql);
//            ps.setObject(1, id);
//            rs = ps.executeQuery();
//
//            while (rs.next()) {
//                credencial.setId(rs.getInt(1));
//                credencial.setCedula_empleado(rs.getString(2));
//                credencial.setTipo(rs.getString(3));
//                credencial.setUsuario(rs.getString(4));
//                credencial.setContrasenna(rs.getString(5));
//                credencial.setCorreo(rs.getString(6));
//                credencial.setEstado(rs.getBoolean(7));
//                break;
//            }
//        } catch (Exception e) {
//            System.out.println("" + e.toString());
//        }
//
//        return credencial;
//    }
//    public Credencial obtenerCredencialPorEstado(boolean estado) {
//        Credencial credencial = new Credencial();
//
//        try {
//            String sql = "SELECT id, cedula_empleado, tipo, "
//                    + "usuario, contrasenna, correo, estado FROM tbl_credencial "
//                    + "WHERE estado = ?;";
//            ps = acceso.prepareStatement(sql);
//            ps.setObject(1, estado);
//            rs = ps.executeQuery();
//
//            while (rs.next()) {
//                credencial.setId(rs.getInt(1));
//                credencial.setCedula_empleado(rs.getString(2));
//                credencial.setTipo(rs.getString(3));
//                credencial.setUsuario(rs.getString(4));
//                credencial.setContrasenna(rs.getString(5));
//                credencial.setCorreo(rs.getString(6));
//                credencial.setEstado(rs.getBoolean(7));
//                break;
//            }
//        } catch (Exception e) {
//            System.out.println("" + e.toString());
//        }
//
//        return credencial;
//    }
//    public Credencial obtenerCredencialPorUsuario(String usuario) {
//        Credencial credencial = new Credencial();
//
//        try {
//            String sql = "SELECT id, cedula_empleado, tipo, usuario, "
//                    + "contrasenna, correo, estado "
//                    + "FROM tbl_credencial WHERE usuario = ?;";
//            ps = acceso.prepareStatement(sql);
//            ps.setObject(1, usuario);
//            rs = ps.executeQuery();
//
//            while (rs.next()) {
//                credencial.setId(rs.getInt(1));
//                credencial.setCedula_empleado(rs.getString(2));
//                credencial.setTipo(rs.getString(3));
//                credencial.setUsuario(rs.getString(4));
//                credencial.setContrasenna(rs.getString(5));
//                credencial.setCorreo(rs.getString(6));
//                credencial.setEstado(rs.getBoolean(7));
//                break;
//            }
//        } catch (Exception e) {
//            System.out.println("" + e.toString());
//        }
//
//        return credencial;
//    }
}
