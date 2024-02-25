/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.Incapacidad;
import Models.Pagos;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author User
 */
public class PagosDAO {

    PreparedStatement ps;
    ResultSet rs;
    Connection acceso = Database.DatabaseConnection.getConnection();

    public boolean insertarPagos(Pagos pagos) {
        try {
            String sql = "INSERT INTO tbl_pagos "
                    + "(fecha, empleado, fecha_final) "
                    + "VALUES (?,?,?);";

            ps = acceso.prepareStatement(sql);
            ps.setObject(1, pagos.getFecha());
            ps.setObject(2, pagos.getEmpleado());
            ps.setObject(3, pagos.getFechaFinal());
            ps.executeUpdate();

            return true;
        } catch (Exception e) {
            System.out.println("" + e.toString());
            return false;
        }

    }

    public int obtenerIdPago(String cedula, Date inicio, Date fin) {
        var id = 0;
        try {
            String sql = "SELECT id from tbl_pagos where "
                    + "fecha = ? AND empleado = ? AND fecha_final = ? ";

            ps = acceso.prepareStatement(sql);
            ps.setObject(1, inicio);
            ps.setObject(2, cedula);
            ps.setObject(3, fin);
            rs = ps.executeQuery();
            // Obtener el ID generado
            while (rs.next()) {
                id = rs.getInt(1);
            }
            return id;
            
        } catch (Exception e) {
            System.out.println("" + e.toString());
            return id;
        }

    }
}
