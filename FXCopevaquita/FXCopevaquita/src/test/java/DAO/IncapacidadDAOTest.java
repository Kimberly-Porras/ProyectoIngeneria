/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

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

@DisplayName("Corriendo los Test de incapacidades")
public class IncapacidadDAOTest {

    @Test
    public void InsertarIncapacidad() {
        IncapacidadDAO dao = mock(IncapacidadDAO.class);
        Incapacidad inca = new Incapacidad();

        when(dao.insertarIncapacidad(inca)).thenReturn(true);

        assertEquals(dao.insertarIncapacidad(inca), true);
    }
}
