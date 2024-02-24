/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.PlanillaSocios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class PlanillaSociosDAO {
    PreparedStatement ps;
    ResultSet rs;
    Connection acceso = Database.DatabaseConnection.getConnection();

    public List<PlanillaSocios> obtenerListaPlanillaSocios() {
        PlanillaSocios planillaSocios;
        List<PlanillaSocios> lista = new ArrayList<>();

        try {
            String sql = "SELECT id,"
                    + "empleado, monto, status "
                    + "FROM tbl_planilla_socios;";

            ps = acceso.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                planillaSocios = new PlanillaSocios();
                planillaSocios.setId(rs.getInt(1));
                planillaSocios.setEmpleado(rs.getString(2));
                planillaSocios.setMonto(rs.getDouble(3));
                planillaSocios.setStatus(rs.getBoolean(4));
                lista.add(planillaSocios);
            }
        } catch (Exception e) {
            System.out.println("" + e.toString());
        }

        return lista;
    }

    public boolean insertarPlanillaSocios(PlanillaSocios planillaSocios) {
        try {
            String sql = "INSERT INTO tbl_planilla_socios "
                    + "(empleado, monto, status) "
                    + "VALUES (?,?,?);";

            ps = acceso.prepareStatement(sql);
            ps.setObject(1, planillaSocios.getEmpleado());
            ps.setObject(2, planillaSocios.getMonto());
            ps.setObject(3, planillaSocios.isStatus());

            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("" + e.toString());
            return false;
        }

    }

    public boolean actualizarPlanillaSocios(PlanillaSocios planillaSocios) {
        try {

            String sql = "UPDATE tbl_planilla_socios "
                    + "SET empleado = ?, monto = ?, status = ? "
                    + "WHERE id = ?;";
            ps = acceso.prepareStatement(sql);
            ps.setObject(1, planillaSocios.getEmpleado());
            ps.setObject(2, planillaSocios.getMonto());
            ps.setObject(3, planillaSocios.isStatus());
            ps.setObject(4, planillaSocios.getId());

            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("" + e.toString());
            return false;
        }
    }
    
    public List<PlanillaSocios> obtenerListaPlanillaPorCedulaEmpleado(String cedulaEmpleado) {
        PlanillaSocios planillaSocios;
        List<PlanillaSocios> lista = new ArrayList<>();

        try {
            String sql = "SELECT id, empleado, "
                    + "monto, status FROM tbl_planilla_socios "
                    + "WHERE empleado = ?;";

            ps = acceso.prepareStatement(sql);
            ps.setObject(1, cedulaEmpleado);
            rs = ps.executeQuery();

            while (rs.next()) {
                planillaSocios = new PlanillaSocios();
                planillaSocios.setId(rs.getInt(1));
                 planillaSocios.setEmpleado(rs.getString(2));
                planillaSocios.setMonto(rs.getDouble(3));
                planillaSocios.setStatus(rs.getBoolean(4));
                lista.add(planillaSocios);
            }
        } catch (Exception e) {
            System.out.println("" + e.toString());
        }

        return lista;
    }
}
