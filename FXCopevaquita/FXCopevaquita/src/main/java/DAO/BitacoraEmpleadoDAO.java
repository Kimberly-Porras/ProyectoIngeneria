/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.BitacoraEmpleado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class BitacoraEmpleadoDAO {
    private Connection acceso = Database.DatabaseConnection.getConnection();
    PreparedStatement ps;
    ResultSet rs;
    
    public List<BitacoraEmpleado> obtenerListaBitacoraEmpleado() {
        BitacoraEmpleado bitacoraEmpleado;
        List<BitacoraEmpleado> lista = new ArrayList<>();

        try {
            String sql = "SELECT id, empleado, actividad, area, fecha, precio, cantidad,   "
                    + "status FROM tbl_bitacora_empleado;";
            
            ps = acceso.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                bitacoraEmpleado = new BitacoraEmpleado();
                bitacoraEmpleado.setId(rs.getInt(1));
                bitacoraEmpleado.setEmpleado(rs.getString(2));
                bitacoraEmpleado.setActividad(rs.getInt(3));
                bitacoraEmpleado.setArea(rs.getInt(4));
                bitacoraEmpleado.setFecha(rs.getDate(5));
                bitacoraEmpleado.setPrecio(rs.getDouble(6));
                bitacoraEmpleado.setCantidad(rs.getInt(7));
                bitacoraEmpleado.setStatus(rs.getBoolean(8));
                lista.add(bitacoraEmpleado);
            }
        } catch (Exception e) {
            System.out.println("" + e.toString());
        }

        return lista;
    }
    
    public boolean insertarBitacoraEmpleado(BitacoraEmpleado bitacoraEmpleado) {
        try {
            String sql = "INSERT INTO tbl_bitacora_empleado "
                    + "(empleado, actividad, area, fecha, precio, cantidad, status) "
                    + "VALUES (?,?,?,?,?,?,?);";

            ps = acceso.prepareStatement(sql);
            ps.setObject(1, bitacoraEmpleado.getEmpleado());
            ps.setObject(2, bitacoraEmpleado.getActividad());
            ps.setObject(3, bitacoraEmpleado.getArea());
            ps.setObject(4, bitacoraEmpleado.getFecha());
            ps.setObject(5, bitacoraEmpleado.getPrecio());
            ps.setObject(6, bitacoraEmpleado.getCantidad());
            ps.setObject(7, bitacoraEmpleado.isStatus());

            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("" + e.toString());
            return false;
        }

    }

    public boolean actualizarBitacoraEmpleado(BitacoraEmpleado bitacoraEmpleado) {
        try {

            String sql = "UPDATE tbl_bitacora_empleado SET empleado = ?, actividad = ?, area = ?,  "
                    + "fecha = ?, precio = ?, cantidad = ?, status = ? "
                    + "WHERE id = ?;";
            
            ps = acceso.prepareStatement(sql);
            ps.setObject(1, bitacoraEmpleado.getEmpleado());
            ps.setObject(2, bitacoraEmpleado.getActividad());
            ps.setObject(3, bitacoraEmpleado.getArea());
            ps.setObject(4, bitacoraEmpleado.getFecha());
            ps.setObject(5, bitacoraEmpleado.getPrecio());
            ps.setObject(6, bitacoraEmpleado.getCantidad());
            ps.setObject(7, bitacoraEmpleado.isStatus());
            ps.setObject(8, bitacoraEmpleado.getId());

            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("" + e.toString());
            return false;
        }
    }
}
