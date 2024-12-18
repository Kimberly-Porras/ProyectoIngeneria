/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Alertas.MensajePersonalizado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Database.DatabaseConnection;
import Models.Credencial;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;

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
                    + "`usuario` = ? and `contrasenia` = ? and status = 1";

            ps = acceso.prepareStatement(sql);
            ps.setObject(1, usuario);
            ps.setObject(2, contrasena);
            rs = ps.executeQuery();

            int counter = 0;

            while (rs.next()) {
                credencial.setId(rs.getInt(1));
                credencial.setEmpleado(rs.getString(2));
                credencial.setUsuario(rs.getString(3));
                credencial.setStatus(rs.getBoolean(4));
                counter++;
                break;
            }

            if (counter == 0) {
                return null;
            }

            return credencial;
        } catch (Exception e) {
            System.out.println("" + e.toString());
            return null;
        }
    }

    public ArrayList<Credencial> obtenerTodos() {
        ArrayList<Credencial> credenciales = new ArrayList<Credencial>();
        try {
            String sql = "SELECT * FROM tbl_credencial";

            ps = acceso.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                var credencialTemp = new Credencial();
                credencialTemp.setId(rs.getInt(1));
                credencialTemp.setEmpleado(rs.getString(2));
                credencialTemp.setUsuario(rs.getString(3));
                credencialTemp.setCorreo(rs.getString(4));
                credencialTemp.setContrasenia(rs.getString(5));
                credencialTemp.setStatus(rs.getBoolean(6));

                credenciales.add(credencialTemp);
            }

            return credenciales;
        } catch (Exception e) {
            System.out.println("" + e.toString());
            return null;
        }
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

    public boolean insertarCredencial(Credencial credencial) {
        try {
            String sql = "INSERT INTO tbl_credencial (empleado, usuario, correo, contrasenia, status) "
                    + "VALUES (?, ?, ?, ?, ?);";

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
                + "SET empleado = ?, usuario = ?, correo = ?, contrasenia = ?, status = ? "
                + "WHERE id = ?;";

        ps = acceso.prepareStatement(sql);
        ps.setString(1, credencial.getEmpleado()); // varchar(20)
        ps.setString(2, credencial.getUsuario());  // varchar(20)
        ps.setString(3, credencial.getCorreo());   // varchar(100)
        ps.setString(4, credencial.getContrasenia()); // varchar(20)
        ps.setBoolean(5, credencial.isStatus());   // bit(1)
        ps.setInt(6, credencial.getId());          // int(11)

        int rowsAffected = ps.executeUpdate();
        return rowsAffected > 0;
    } catch (Exception e) {
        e.printStackTrace(); // Imprime el error detallado
        return false;
    }
}

}
