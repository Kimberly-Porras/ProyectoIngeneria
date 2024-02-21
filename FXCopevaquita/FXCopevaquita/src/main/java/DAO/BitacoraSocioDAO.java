package DAO;

import Models.BitacoraSocio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author User
 */
public class BitacoraSocioDAO {
    
    PreparedStatement ps;
    ResultSet rs;
    Connection acceso = Database.DatabaseConnection.getConnection();
    
    public boolean insertarBitacoraSocio(BitacoraSocio bitacoraSocio) {
        try {
            String sql = "INSERT INTO tbl_bitacora_socio"
                    + "(cedula_empleado, horas, status, descripcion) "
                    + "VALUES (?,?,?,?);";

            
            ps = acceso.prepareStatement(sql);
            ps.setObject(1, bitacoraSocio.getCedula_empleado());
            ps.setObject(2, bitacoraSocio.getHoras());
            ps.setObject(3, bitacoraSocio.isStatus());
            ps.setObject(4, bitacoraSocio.getDescripcion());

            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("" + e.toString());
            return false;
        }

    }

    public boolean actualizarBitacoraSocio(BitacoraSocio bitacoraSocio) {
        try {

            String sql = "UPDATE tbl_bitacora_socio SET cedula_empleado = ?, horas = ?, status = ?, descripcion = ? WHERE id = ?;";
            
            ps = acceso.prepareStatement(sql);
            ps.setObject(1, bitacoraSocio.getCedula_empleado());
            ps.setObject(2, bitacoraSocio.getHoras());
            ps.setObject(3, bitacoraSocio.isStatus());
            ps.setObject(4, bitacoraSocio.getDescripcion());
            ps.setObject(5, bitacoraSocio.getId());

            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("" + e.toString());
            return false;
        }
    }
    
     public List<BitacoraSocio> obtenerListaBitacoraSocio() {
        BitacoraSocio bitacoraSocio;
        List<BitacoraSocio> lista = new ArrayList<>();

        try {
            String sql = "SELECT id, cedula_empleado, horas, status, descripcion "
                    + "FROM tbl_bitacora_socio;";
           
            ps = acceso.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                bitacoraSocio = new BitacoraSocio();
                bitacoraSocio.setId(rs.getInt(1));
                bitacoraSocio.setCedula_empleado(rs.getString(2));
                bitacoraSocio.setHoras(rs.getDouble(3));
                bitacoraSocio.setStatus(rs.getBoolean(4));
                bitacoraSocio.setDescripcion(rs.getString(5));
                lista.add(bitacoraSocio);
            }
        } catch (Exception e) {
            System.out.println("" + e.toString());
        }

        return lista;
    }
    
     public List<BitacoraSocio> obtenerListaBitacoraSocioPorCedula( String cedulaEmpleado) {
        BitacoraSocio bitacoraSocio;
        List<BitacoraSocio> lista = new ArrayList<>();

        try {
            String sql = "SELECT id, cedula_empleado, horas, status, descripcion "
                    + "FROM tbl_bitacora_socio "
                    + "WHERE cedula_empleado = ?;";
            
            ps = acceso.prepareStatement(sql);
            ps.setObject(1, cedulaEmpleado);
            rs = ps.executeQuery();

            while (rs.next()) {
                bitacoraSocio = new BitacoraSocio();
                bitacoraSocio.setId(rs.getInt(1));
                bitacoraSocio.setCedula_empleado(rs.getString(2));
                bitacoraSocio.setHoras(rs.getDouble(3));
                bitacoraSocio.setStatus(rs.getBoolean(4));
                bitacoraSocio.setDescripcion(rs.getString(5));
                lista.add(bitacoraSocio);
            }
        } catch (Exception e) {
            System.out.println("" + e.toString());
        }

        return lista;
    }
}
