/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.BitacoraEmpleado;
import Models.Incapacidad;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 *
 * @author alber
 * @author kim03
 */
@DisplayName("Corriendo los test de reporte díario")
public class BitacoraEmpleadoDAOTest {

    @Test
    public void InsertarReporteDiario() {
        BitacoraEmpleadoDAO dao = mock(BitacoraEmpleadoDAO.class);
        BitacoraEmpleado bit = new BitacoraEmpleado();
        when(dao.insertarBitacoraEmpleado(bit)).thenReturn(true);

        assertEquals(dao.insertarBitacoraEmpleado(bit), true);
    }
}
