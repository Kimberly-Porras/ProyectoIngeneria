/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 *
 * @author aleke
 */
public class EmpleadoTest {
    @Test
    @DisplayName("El nombre completo es correcto")
    public void NombreCompleto() {
        Empleado empleado = new Empleado();
        empleado.setNombre("Alexander");
        empleado.setApellidos("Barquero");

        assertEquals(empleado.getNombre() + " " + empleado.getApellidos(), empleado.getNombreCompleto());
    }
}
