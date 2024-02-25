/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.Pagos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
}
