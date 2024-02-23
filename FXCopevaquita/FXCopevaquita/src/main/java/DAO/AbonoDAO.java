/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.Abono;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author User
 */
public class AbonoDAO {

    private Connection acceso = Database.DatabaseConnection.getConnection();
    PreparedStatement ps;
    ResultSet rs;

    public boolean insertarAbono(Abono abono) {
        try {
            String sql = "INSERT INTO tbl_abono "
                    + "(deduccion, monto, fecha, nota) "
                    + "VALUES (?,?,?,?,?,?);";

            ps = acceso.prepareStatement(sql);
            ps.setObject(1, abono.getDeduccion());
            ps.setObject(2, abono.getMonto());
            ps.setObject(3, abono.getFecha());
            ps.setObject(4, abono.getNota());;

            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("" + e.toString());
            return false;
        }

    }

    public boolean actualizarAbono(Abono abono) {
        try {

            String sql = "UPDATE tbl_abono "
                    + "SET deduccion = ?, monto = ?, fecha = ?, nota = ? "
                    + "WHERE id = ?;";
            
            ps = acceso.prepareStatement(sql);
            ps.setObject(1, abono.getDeduccion());
            ps.setObject(2, abono.getMonto());
            ps.setObject(3, abono.getFecha());
            ps.setObject(4, abono.getNota());
            ps.setObject(7, abono.getId());

            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("" + e.toString());
            return false;
        }
    }
    
    public Abono obtenerDetalleAbonoIdDeduccion(int idDeduccion) {
        Abono abono = new Abono();

        try {
            String sql = "SELECT id, deduccion, monto, fecha, nota "
                    + "FROM tbl_abono "
                    + "WHERE id_deduccion = ?;";
            
            ps = acceso.prepareStatement(sql);
            ps.setObject(1, idDeduccion);
            rs = ps.executeQuery();

            while (rs.next()) {
                abono.setId(rs.getInt(1));
                abono.setDeduccion(rs.getInt(2));
                abono.setMonto(rs.getDouble(3));
                abono.setFecha(rs.getDate(4));
                abono.setNota(rs.getString(5));
                break;
            }
        } catch (Exception e) {
            System.out.println("" + e.toString());
        }

        return abono;
    }
}
