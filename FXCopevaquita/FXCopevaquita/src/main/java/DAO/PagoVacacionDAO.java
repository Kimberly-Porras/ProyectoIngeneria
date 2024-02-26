/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.PagoIncapacidad;
import Models.PagoVacacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author User
 */
public class PagoVacacionDAO {

    PreparedStatement ps;
    ResultSet rs;
    Connection acceso = Database.DatabaseConnection.getConnection();

    public boolean insertarVacacion(PagoVacacion pagoVacacion) {
        try {
            String sql = "INSERT INTO tbl_pago_vacacion "
                    + "(totalVacacion, pagoId) "
                    + "VALUES (?,?);";

            ps = acceso.prepareStatement(sql);
            ps.setObject(1, pagoVacacion.getTotalVacacion());
            ps.setObject(2, pagoVacacion.getPagoId());

            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("" + e.toString());
            return false;
        }
    }

    public PagoVacacion obtenerPagoVacacionPorPago(int pagoId) {
        PagoVacacion pago = new PagoVacacion();
        try {
            String sql = "SELECT totalVacacion "
                    + "FROM tbl_pago_vacacion "
                    + "WHERE pagoId = ?;";
            ps = acceso.prepareStatement(sql);
            ps.setObject(1, pagoId);
            rs = ps.executeQuery();

            while (rs.next()) {
                pago.setTotalVacacion(rs.getDouble(1));
                break;
            }
        } catch (Exception e) {
            System.out.println("" + e.toString());
        }

        return pago;
    }
}
