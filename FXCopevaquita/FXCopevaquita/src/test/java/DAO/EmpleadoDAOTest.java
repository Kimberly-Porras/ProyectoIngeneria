/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.Empleado;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 *
 * @author alber
 * @author kim03
 */

@DisplayName("Pruebas sobre insertar un empleado")
public class EmpleadoDAOTest {

    
    @Test
    public void InsertarEmpleadoTest() {
        EmpleadoDAO empleadoDAO = mock(EmpleadoDAO.class);
        Empleado empleado = new Empleado();
        
        when(empleadoDAO.insertarEmpleado(empleado))
                .thenReturn(true);

        assertEquals(empleadoDAO.insertarEmpleado(empleado), true);
    }
}
