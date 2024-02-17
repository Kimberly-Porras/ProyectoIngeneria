/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.Parentezco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class ParentezcoDAO {
    
    PreparedStatement ps;
    ResultSet rs;
    Connection acceso = Database.DatabaseConnection.getConnection();
    
    public List<Parentezco> obtenerListaParentezco() {
        List<Parentezco> listaParentezco = new ArrayList<>();

        try {
            String sql = "SELECT cedula,empleado, nombre, apellidos, "
                    + "fechaNacimiento, sexo, status, parentezco, contactoEmergencia "
                    + "FROM tbl_parentezco;";
            
            ps = acceso.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Parentezco parentezco = new Parentezco();
                parentezco.setCedula(rs.getString(1));
                parentezco.setEmpleado(rs.getString(2));
                parentezco.setNombre(rs.getString(3));
                parentezco.setApellidos(rs.getString(4));
                parentezco.setFechaNacimiento(rs.getDate(5));
                parentezco.setSexo(rs.getString(6));
                parentezco.setStatus(rs.getBoolean(7));
                parentezco.setParentezco(rs.getString(8));
                parentezco.setContactoEmergencia(rs.getString(9));
                

                listaParentezco.add(parentezco);
            }
        } catch (Exception e) {
            System.out.println("" + e.toString());
        }

        return listaParentezco;
    }
    
    public boolean insertarParentezco(Parentezco parentezco) {
        try {
            String sql = "INSERT INTO tbl_parentezco "
                    + "(cedula, empleado, nombre, apellidos, fechaNacimiento, "
                    + "sexo, status, parentezco, contactoEmergencia) "
                    + "VALUES (?,?,?,?,?,?,?,?,?);";

            
            ps = acceso.prepareStatement(sql);
            ps.setObject(1, parentezco.getCedula());
            ps.setObject(2, parentezco.getEmpleado());
            ps.setObject(3, parentezco.getNombre());
            ps.setObject(4, parentezco.getApellidos());
            ps.setObject(5, parentezco.getFechaNacimiento());
            ps.setObject(6, parentezco.getSexo());
            ps.setObject(7, parentezco.isStatus());
            ps.setObject(8, parentezco.getParentezco());
            ps.setObject(9, parentezco.getContactoEmergencia());

            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("" + e.toString());
            return false;
        }

    }

    public boolean actualizarParentezco(Parentezco parentezco) {
        try {

            String sql = "UPDATE tbl_parentezco SET empleado = ?, nombre = ?, apellidos = ?, sexo = ?, "
                    + "fechaNacimiento = ?,status = ?, parentezco = ?, contactoEmergencia = ? "
                    + "WHERE cedula = ?;";
            
            ps = acceso.prepareStatement(sql);
            ps.setObject(1, parentezco.getEmpleado());
            ps.setObject(2, parentezco.getNombre());
            ps.setObject(3, parentezco.getApellidos());
            ps.setObject(4, parentezco.getSexo());
            ps.setObject(5, parentezco.getFechaNacimiento());
            ps.setObject(6, parentezco.getSexo());
            ps.setObject(7, parentezco.isStatus());
            ps.setObject(8, parentezco.getParentezco());
            ps.setObject(9, parentezco.getContactoEmergencia());

            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
            return false;
        }
    }
    
     public List<Parentezco> obtenerListaParentezcosPorCedulaEmpleado(String cedulaEmpleado) {
        Parentezco parentezco;
        List<Parentezco> lista = new ArrayList<>();

        try {
            String sql = "SELECT cedula, nombre, apellidos, sexo, "
                    + "FechaNacimiento, status, parentezco, contactoEmergencia FROM tbl_parentezco "
                    + "WHERE empleado = ?;";

            ps = acceso.prepareStatement(sql);
            ps.setObject(1, cedulaEmpleado);
            rs = ps.executeQuery();

            while (rs.next()) {
                parentezco = new Parentezco();
                parentezco.setCedula(rs.getString(1));
                parentezco.setNombre(rs.getString(2));
                parentezco.setApellidos(rs.getString(3));
                parentezco.setSexo(rs.getString(4));
                parentezco.setFechaNacimiento(rs.getDate(5));
                parentezco.setStatus(rs.getBoolean(6));
                parentezco.setParentezco(rs.getString(7));
                parentezco.setContactoEmergencia(rs.getString(8));
                lista.add(parentezco);
            }
        } catch (Exception e) {
            System.out.println("" + e.toString());
        }

        return lista;
    }
}
