/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.AguinaldoExportar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author aleke
 */
public class AguinaldoExportarDAO {

    private Connection acceso = Database.DatabaseConnection.getConnection();
    PreparedStatement ps;
    ResultSet rs;

    public boolean removeAguinaldos() {
        try {
            String sql = "DELETE FROM `tbl_aguinaldo_exportar` WHERE 1";
            ps = acceso.prepareStatement(sql);
            ps.execute();

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean insertarAguinaldos(List<AguinaldoExportar> aguinaldos) {
        try {
            // Iniciar transacción...
            acceso.setAutoCommit(false); // Desactivar el modo de auto-commit

            String sql = "INSERT INTO `tbl_aguinaldo_exportar`(`nombre`, "
                    + "`apellidos`, `numeroCuenta`, `tipo`, "
                    + "`aguinaldoCalculado`) VALUES (?,?,?,?,?)";

            for (AguinaldoExportar aguinaldo : aguinaldos) {
                try {
                    ps = acceso.prepareStatement(sql);
                    ps.setObject(1, aguinaldo.getNombre());
                    ps.setObject(2, aguinaldo.getApellidos());
                    ps.setObject(3, aguinaldo.getNumeroCuenta());
                    ps.setObject(4, aguinaldo.getTipo());
                    ps.setObject(5, aguinaldo.getAguinaldoCalculado());
                    ps.executeUpdate();
                } catch (SQLException e) {
                    System.out.println("Error al insertar aguinaldo: " + e.toString());
                    acceso.rollback(); // Realizar rollback en caso de error
                    return false;
                }
            }

            // Commit la transacción si todas las inserciones se completaron correctamente
            acceso.commit();
            return true;

        } catch (SQLException ex) {
            System.out.println("Error al iniciar la transacción: " + ex.toString());
            return false;

        } finally {
            try {
                acceso.setAutoCommit(true); // Restaurar el modo de auto-commit
            } catch (SQLException ex) {
                System.out.println("Error al restaurar el modo de auto-commit: " + ex.toString());
            }
        }
    }

}
