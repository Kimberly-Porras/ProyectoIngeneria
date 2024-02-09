/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import Models.Empleado;

/**
 *
 * @author aleke
 */
public class EmpleadoDAO {

    PreparedStatement ps;
    ResultSet rs;
    Connection acceso = Database.DatabaseConnection.getConnection();

    public  boolean insertarEmpleado(Empleado empleado) {
        try {
            String sql = "INSERT INTO tbl_empleado (cedula, nombre, apellidos, sexo, estadoCivil,"
                    + " tipoSangre, fechaNacimiento, fechaIngreso, tipo, numeroCuenta, nivelAcademico, status) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";
            ps = acceso.prepareStatement(sql);
            ps.setObject(1, empleado.getCedula());
            ps.setObject(2, empleado.getNombre());
            ps.setObject(3, empleado.getApellidos());
            ps.setObject(4, empleado.getSexo());
            ps.setObject(5, empleado.getEstadoCivil());
            ps.setObject(6, empleado.getTipoSangre());
            ps.setObject(7, empleado.getFechaNacimiento());
            ps.setObject(8, empleado.getFechaIngreso());
            ps.setObject(9, empleado.getTipo());
            ps.setObject(10, empleado.getNumeroCuenta());
            ps.setObject(11, empleado.getNivelAcademico());
            ps.setObject(12, empleado.isStatus());

            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("" + e.toString());
            return false;
        }
    }

    public boolean actualizarEmpleado(Empleado empleado) {
        try {

            String sql = "UPDATE tbl_empleado "
                    + "SET nombre = ?, "
                    + "apellidos = ?, "
                    + "sexo = ?, "
                    + "estadoCivil = ?, "
                    + "tipoSangre = ?, "
                    + "fechaNacimiento = ?, "
                    + "fechaIngreso = ?, "
                    + "tipo = ? ,"
                    + "numeroCuenta = ?, "
                    + "nivelAcademico = ?, "
                    + "status = ? "
                    + "WHERE cedula = ?;";
            ps = acceso.prepareStatement(sql);
            ps.setObject(1, empleado.getNombre());
            ps.setObject(2, empleado.getApellidos());
            ps.setObject(3, empleado.getSexo());
            ps.setObject(4, empleado.getEstadoCivil());
            ps.setObject(5, empleado.getTipoSangre());
            ps.setObject(6, empleado.getFechaNacimiento());
            ps.setObject(7, empleado.getFechaIngreso());
            ps.setObject(8, empleado.getTipo());
            ps.setObject(9, empleado.getNumeroCuenta());
            ps.setObject(10, empleado.getNivelAcademico());
            ps.setObject(11, empleado.isStatus());
            ps.setObject(12, empleado.getCedula());

            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Empleado obtenerEmpleadoPorCedula(String cedula) {
        Empleado empleado = new Empleado();

        try {
            String sql = "SELECT cedula, nombre, apellidos, sexo, estadoCivil,"
                    + " tipoSangre, fechaNacimiento, fechaIngreso, tipo, numeroCuenta, nivelAcademico, status "
                    + "FROM tbl_empleado "
                    + "WHERE cedula = ?;";
            ps = acceso.prepareStatement(sql);
            ps.setObject(1, cedula);
            rs = ps.executeQuery();

            while (rs.next()) {
                empleado.setCedula(rs.getString(1));
                empleado.setNombre(rs.getString(2));
                empleado.setApellidos(rs.getString(3));
                empleado.setSexo(rs.getString(4));
                empleado.setEstadoCivil(rs.getString(5));
                empleado.setTipoSangre(rs.getString(6));
                empleado.setFechaNacimiento(rs.getDate(7));
                empleado.setFechaIngreso(rs.getDate(8));
                empleado.setTipo(rs.getString(9));
                empleado.setNumeroCuenta(rs.getString(10));
                empleado.setNivelAcademico(rs.getString(11));
                empleado.setStatus(rs.getBoolean(12));
                break;
            }
        } catch (Exception e) {
            System.out.println("" + e.toString());
        }

        return empleado;
    }

    public Empleado obtenerEmpleadoPorEstado(boolean estado) {
        Empleado empleado = new Empleado();

        try {
            String sql = "SELECT cedula, nombre, apellidos, sexo, estadoCivil, "
                    + "tipoSangre, fechaNacimiento, fechaIngreso, tipo,  numeroCuenta, nivelAcademico, status "
                    + "FROM tbl_empleado "
                    + "WHERE estado = ?;";
            ps = acceso.prepareStatement(sql);
            ps.setObject(1, estado);
            rs = ps.executeQuery();

            while (rs.next()) {
                empleado.setCedula(rs.getString(1));
                empleado.setNombre(rs.getString(2));
                empleado.setApellidos(rs.getString(3));
                empleado.setSexo(rs.getString(4));
                empleado.setEstadoCivil(rs.getString(5));
                empleado.setTipoSangre(rs.getString(6));
                empleado.setFechaNacimiento(rs.getDate(7));
                empleado.setFechaIngreso(rs.getDate(8));
                empleado.setTipo(rs.getString(9));
                empleado.setNumeroCuenta(rs.getString(10));
                empleado.setNivelAcademico(rs.getString(11));
                empleado.setStatus(rs.getBoolean(12));
                break;
            }
        } catch (Exception e) {
            System.out.println("" + e.toString());
        }

        return empleado;
    }

    public List<Empleado> obtenerListaEmpleados() {
        List<Empleado> listaEmpleados = new ArrayList<>();

        try {
            String sql = "SELECT cedula, nombre, apellidos, sexo, estadoCivil,"
                    + " tipoSangre, fechaNacimiento, fechaIngreso, tipo, numeroCuenta, nivelAcademico, status "
                    + "FROM tbl_empleado;";
            ps = acceso.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Empleado empleado = new Empleado();
                empleado.setCedula(rs.getString(1));
                empleado.setNombre(rs.getString(2));
                empleado.setApellidos(rs.getString(3));
                empleado.setSexo(rs.getString(4));
                empleado.setEstadoCivil(rs.getString(5));
                empleado.setTipoSangre(rs.getString(6));
                empleado.setFechaNacimiento(rs.getDate(7));
                empleado.setFechaIngreso(rs.getDate(8));
                empleado.setTipo(rs.getString(9));
                empleado.setNumeroCuenta(rs.getString(10));
                empleado.setNivelAcademico(rs.getString(11));
                empleado.setStatus(rs.getBoolean(12));
                listaEmpleados.add(empleado);
            }
        } catch (Exception e) {
            System.out.println("" + e.toString());
        }

        return listaEmpleados;
    }
}
