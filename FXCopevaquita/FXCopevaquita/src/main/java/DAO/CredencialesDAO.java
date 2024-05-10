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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alber
 * @author kim03
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

    public ArrayList<Credencial> obtenerTodos() {
        var credencial = new ArrayList<Credencial>();
        try {
            ps = acceso.prepareStatement("SELECT id, empleado, usuario, "
                    + "correo, contrasenia, status FROM tbl_credencial");
            rs = ps.executeQuery();

            while (rs.next()) {
                credencial.add(new Credencial(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getBoolean(6)
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TipoDeduccionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return credencial;
    }

    public boolean insertarCredencial(Credencial credencial) {
        try {
            String sql = "INSERT INTO tbl_credencial (empleado, usuario, "
                    + "correo, contrasenia, status) VALUES (?, ?, ?, ?, ?);";

            ps = acceso.prepareStatement(sql);
            ps.setObject(1, credencial.getEmpleado());
            ps.setObject(2, credencial.getUsuario());
            ps.setObject(3, credencial.getCorreo());
            ps.setObject(4, credencial.getContrasenia());
            ps.setObject(5, credencial.isStatus());

            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("" + e.toString());
            return false;
        }

    }

    public boolean actualizarCredencial(Credencial credencial) {
        try {

            String sql = "UPDATE tbl_credencial "
                    + "SET empleado = ?, usuario = ?, correo = ?, "
                    + "contrasenia = ?, status = ? "
                    + "WHERE id = ?;";

            ps = acceso.prepareStatement(sql);
            ps.setObject(1, credencial.getEmpleado());
            ps.setObject(2, credencial.getUsuario());
            ps.setObject(3, credencial.getCorreo());
            ps.setObject(4, credencial.getContrasenia());
            ps.setObject(5, credencial.isStatus());
            ps.setObject(6, credencial.getId());

            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("" + e.toString());
            return false;
        }
    }
}
