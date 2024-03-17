/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.PlanillaSocios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Models.PorcentajeRebajos;

/**
 *
 * @author aleke
 */
public class PorcentajeRebajosDAO {

    PreparedStatement ps;
    ResultSet rs;
    Connection acceso = Database.DatabaseConnection.getConnection();

    public PorcentajeRebajos obtenerPorcentajesRebajos() {
        PorcentajeRebajos por = new PorcentajeRebajos();

        try {
            String sql = "SELECT id, gobierno, exceso1"
                    + " FROM tbl_porcentaje_rebajos;";

            ps = acceso.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                por.setId(rs.getInt(1));
                por.setGobierno(rs.getInt(2));
                por.setExceso(rs.getInt(3));
            }

            return por;
        } catch (Exception e) {
            System.out.println("" + e.toString());
        }

        return null;
    }
}
