/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.PagoDeduccion;
import Models.PagoIncapacidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author alber
 * @author kim03
 */
public class PagoIncapacidadDAO {

    PreparedStatement ps;
    ResultSet rs;
    Connection acceso = Database.DatabaseConnection.getConnection();

    public boolean insertarIncapacidad(PagoIncapacidad pagoIncapacidad) {
        try {
            String sql = "INSERT INTO tbl_pago_incapacidad "
                    + "(totalIncapacidad, pagoId) "
                    + "VALUES (?,?);";

            ps = acceso.prepareStatement(sql);
            ps.setObject(1, pagoIncapacidad.getTotalIncapacidad());
            ps.setObject(2, pagoIncapacidad.getPagoId());

            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("" + e.toString());
            return false;
        }
    }

    public PagoIncapacidad obtenerPagoIncapacidadPorPago(int pagoId) {
        PagoIncapacidad pago = new PagoIncapacidad();
        try {
            String sql = "SELECT totalIncapacidad "
                    + "FROM tbl_pago_incapacidad "
                    + "WHERE pagoId = ?;";
            ps = acceso.prepareStatement(sql);
            ps.setObject(1, pagoId);
            rs = ps.executeQuery();

            while (rs.next()) {
                pago.setTotalIncapacidad(rs.getDouble(1));
                break;
            }
        } catch (Exception e) {
            System.out.println("" + e.toString());
        }

        return pago;
    }
}
