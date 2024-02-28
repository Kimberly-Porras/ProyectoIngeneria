/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.PagoBitacora;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author alber
 * @author kim03
 */
public class PagoBitacoraDAO {

    PreparedStatement ps;
    ResultSet rs;
    Connection acceso = Database.DatabaseConnection.getConnection();

    public boolean insertarPagoBitacora(PagoBitacora pagoBitacora) {
        try {
            String sql = "INSERT INTO tbl_pago_bitacora "
                    + "(totalBitacora, IdPago) "
                    + "VALUES (?,?);";

            ps = acceso.prepareStatement(sql);
            ps.setObject(1, pagoBitacora.getTotalBitacora());
            ps.setObject(2, pagoBitacora.getPagoId());
            ps.executeUpdate();

            return true;
        } catch (Exception e) {
            System.out.println("" + e.toString());
            return false;
        }
    }

    public PagoBitacora obtenerPagoBitacoraPorPago(int pagoId) {
        PagoBitacora bitacora = new PagoBitacora();
        try {
            String sql = "SELECT totalBitacora "
                    + "FROM tbl_pago_bitacora "
                    + "WHERE IdPago = ?;";
            ps = acceso.prepareStatement(sql);
            ps.setObject(1, pagoId);
            rs = ps.executeQuery();

            while (rs.next()) {
                bitacora.setTotalBitacora(rs.getDouble(1));
                break;
            }
        } catch (Exception e) {
            System.out.println("" + e.toString());
        }

        return bitacora;
    }
}
