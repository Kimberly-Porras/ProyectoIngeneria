/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.Abono;
import Models.Deduccion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alber
 * @author kim03
 */
public class AbonoDAO {

    private Connection acceso = Database.DatabaseConnection.getConnection();
    PreparedStatement ps;
    ResultSet rs;

    public boolean insertarAbono(Abono abono) {
        try {
            String sql = "INSERT INTO tbl_abono "
                    + "(deduccion, monto, fecha, nota) "
                    + "VALUES (?,?,?,?);";

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

    public boolean generarAbono(Abono abono, Deduccion deduccion) {
        Connection conn = null;
        PreparedStatement psInsertar = null;
        PreparedStatement psActualizar = null;
        try {
            conn = acceso;
            // 1. Desactivar auto-commit para iniciar la transacción
            conn.setAutoCommit(false);

            // 2.1. Primer operación: Insertar el abono
            String insertarAbono = "INSERT INTO tbl_abono "
                    + "(deduccion, monto, fecha, nota) "
                    + "VALUES (?,?,?,?);";
            psInsertar = conn.prepareStatement(insertarAbono);
            psInsertar.setObject(1, abono.getDeduccion());
            psInsertar.setObject(2, abono.getMonto());
            psInsertar.setObject(3, abono.getFecha());
            psInsertar.setObject(4, abono.getNota());
            psInsertar.executeUpdate();

            // 2.2. Segunda operación: Actualizar la deducción
            String sqlActualizar = "UPDATE tbl_deduccion SET tipo = ?, monto = ?, "
                    + "cuota = ?, pendiente = ? , empleado = ?, status = ?, fecha_registro = ? "
                    + "WHERE id = ?;";
            psActualizar = conn.prepareStatement(sqlActualizar);
            psActualizar.setObject(1, deduccion.getTipo());
            psActualizar.setObject(2, deduccion.getMonto());
            psActualizar.setObject(3, deduccion.getCuota());
            psActualizar.setObject(4, deduccion.getPendiente());
            psActualizar.setObject(5, deduccion.getEmpleado());
            psActualizar.setObject(6, deduccion.isStatus());
            psActualizar.setObject(7, deduccion.getFecha_registro());
            psActualizar.setObject(8, deduccion.getId());
            psActualizar.executeUpdate();

            // 3. Commit de la transacción
            conn.commit();

            return true;
        } catch (Exception e) {
            System.out.println("" + e.toString());
            if (conn != null) {
                try {
                    // 4. Rollback en caso de error
                    conn.rollback();
                } catch (SQLException exRollback) {
                    System.out.println("Error en rollback: " + exRollback.toString());
                }
            }
            return false;
        } finally {
            // Restaurar auto-commit y cerrar recursos
            try {
                if (psInsertar != null) {
                    psInsertar.close();
                }
                if (psActualizar != null) {
                    psActualizar.close();
                }
                if (conn != null) {
                    conn.setAutoCommit(true);
//                conn.close();
                }
            } catch (SQLException exClose) {
                System.out.println("Error al cerrar recursos: " + exClose.toString());
            }
        }
    }
    
    public boolean AjustarAbono(Abono abono, Deduccion deduccion) {
        Connection conn = null;
        PreparedStatement psInsertar = null;
        PreparedStatement psActualizar = null;
        try {
            conn = acceso;
            // 1. Desactivar auto-commit para iniciar la transacción
            conn.setAutoCommit(false);
            
            // 2.1. Primer operación: actualizar el abono
            String actualizarAbono = "UPDATE tbl_abono "
                    + "SET deduccion = ?, monto = ?, fecha = ?, nota = ? "
                    + "WHERE id = ?;";

            psActualizar = acceso.prepareStatement(actualizarAbono);
            psActualizar.setObject(1, abono.getDeduccion());
            psActualizar.setObject(2, abono.getMonto());
            psActualizar.setObject(3, abono.getFecha());
            psActualizar.setObject(4, abono.getNota());
            psActualizar.setObject(5, abono.getId());

            psActualizar.executeUpdate();

            // 2.2. Segunda operación: Actualizar la deducción
            String sqlActualizar = "UPDATE tbl_deduccion SET tipo = ?, monto = ?, "
                    + "cuota = ?, pendiente = ? , empleado = ?, status = ?, fecha_registro = ? "
                    + "WHERE id = ?;";
            psActualizar = conn.prepareStatement(sqlActualizar);
            psActualizar.setObject(1, deduccion.getTipo());
            psActualizar.setObject(2, deduccion.getMonto());
            psActualizar.setObject(3, deduccion.getCuota());
            psActualizar.setObject(4, deduccion.getPendiente());
            psActualizar.setObject(5, deduccion.getEmpleado());
            psActualizar.setObject(6, deduccion.isStatus());
            psActualizar.setObject(7, deduccion.getFecha_registro());
            psActualizar.setObject(8, deduccion.getId());
            psActualizar.executeUpdate();

            // 3. Commit de la transacción
            conn.commit();

            return true;
        } catch (Exception e) {
            System.out.println("" + e.toString());
            if (conn != null) {
                try {
                    // 4. Rollback en caso de error
                    conn.rollback();
                } catch (SQLException exRollback) {
                    System.out.println("Error en rollback: " + exRollback.toString());
                }
            }
            return false;
        } finally {
            // Restaurar auto-commit y cerrar recursos
            try {
                if (psInsertar != null) {
                    psInsertar.close();
                }
                if (psActualizar != null) {
                    psActualizar.close();
                }
                if (conn != null) {
                    conn.setAutoCommit(true);
//                conn.close();
                }
            } catch (SQLException exClose) {
                System.out.println("Error al cerrar recursos: " + exClose.toString());
            }
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
            ps.setObject(5, abono.getId());

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
                    + "WHERE deduccion = ?";

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

    public ArrayList<Abono> obtenerAbonosPorDeduccion(int idDeduccion) {
        ArrayList<Abono> abonos = new ArrayList<>();
        try {
            String sql = "SELECT id, deduccion, monto, fecha, nota "
                    + "FROM tbl_abono "
                    + "WHERE deduccion = ?";

            ps = acceso.prepareStatement(sql);
            ps.setObject(1, idDeduccion);
            rs = ps.executeQuery();

            while (rs.next()) {
                abonos.add(new Abono(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getDouble(3),
                        rs.getDate(4),
                        rs.getString(5)
                ));
            }

        } catch (Exception e) {
            System.out.println("" + e.toString());
        }

        return abonos;
    }
}
