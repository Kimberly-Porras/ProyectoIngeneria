package DAO;

import Models.Aguinaldo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AguinaldoDAO {

    private Connection acceso = Database.DatabaseConnection.getConnection();
    PreparedStatement ps;
    ResultSet rs;

    // Método para insertar un nuevo registro de aguinaldo
    public boolean insertarAguinaldo(Aguinaldo aguinaldo) {
        try {
            String query = "INSERT INTO tbl_aguinaldo (total, fecha_ini, fecha_fin, empleado) VALUES (?, ?, ?, ?)";
            ps = acceso.prepareStatement(query);
            ps.setDouble(1, aguinaldo.getTotal());
            ps.setDate(2, aguinaldo.getFecha_ini());
            ps.setDate(3, aguinaldo.getFecha_fin());
            ps.setString(4, aguinaldo.getEmpleado());

            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println("Error al insertar aguinaldo: " + ex.getMessage());
            return false;
        }
    }

    // Método para actualizar un registro de aguinaldo existente
    public boolean actualizarAguinaldo(Aguinaldo aguinaldo) {
        try {
            String query = "UPDATE tbl_aguinaldo SET total = ?, fecha_ini = ?, fecha_fin = ?, empleado = ? WHERE id = ?";
            ps = acceso.prepareStatement(query);
            ps.setDouble(1, aguinaldo.getTotal());
            ps.setDate(2, aguinaldo.getFecha_ini());
            ps.setDate(3, aguinaldo.getFecha_fin());
            ps.setString(4, aguinaldo.getEmpleado());
            ps.setInt(5, aguinaldo.getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println("Error al actualizar aguinaldo: " + ex.getMessage());
            return false;
        }
    }

    // Método para eliminar un registro de aguinaldo
    public boolean eliminarAguinaldo(int id) {
        try {
            String query = "DELETE FROM tbl_aguinaldo WHERE id = ?";
            ps = acceso.prepareStatement(query);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println("Error al eliminar aguinaldo: " + ex.getMessage());
            return false;
        }
    }

    // Método para obtener todos los registros de aguinaldo
    public List<Aguinaldo> obtenerTodosAguinaldos() {
        List<Aguinaldo> aguinaldos = new ArrayList<>();
        try {
            String query = "SELECT * FROM tbl_aguinaldo";
            ps = acceso.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Aguinaldo aguinaldo = new Aguinaldo();
                aguinaldo.setId(rs.getInt("id"));
                aguinaldo.setTotal(rs.getDouble("total"));
                aguinaldo.setFecha_ini(rs.getDate("fecha_ini"));
                aguinaldo.setFecha_fin(rs.getDate("fecha_fin"));
                aguinaldo.setEmpleado(rs.getString("empleado"));

                aguinaldos.add(aguinaldo);
            }
        } catch (SQLException ex) {
            System.out.println("Error al obtener todos los aguinaldos: " + ex.getMessage());
        }
        return aguinaldos;
    }

    // Método para obtener un solo registro de aguinaldo por su ID
    public Aguinaldo obtenerAguinaldoPorId(int id) {
        try {
            String query = "SELECT * FROM tbl_aguinaldo WHERE id = ?";
            ps = acceso.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Aguinaldo aguinaldo = new Aguinaldo();
                aguinaldo.setId(rs.getInt("id"));
                aguinaldo.setTotal(rs.getDouble("total"));
                aguinaldo.setFecha_ini(rs.getDate("fecha_ini"));
                aguinaldo.setFecha_fin(rs.getDate("fecha_fin"));
                aguinaldo.setEmpleado(rs.getString("empleado"));
                return aguinaldo;
            }
        } catch (SQLException ex) {
            System.out.println("Error al obtener aguinaldo por ID: " + ex.getMessage());
        }
        return null;
    }
}
