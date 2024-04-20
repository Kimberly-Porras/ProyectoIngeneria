/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.Vacaciones;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 *
 * @author alber
 * @author kim03
 */

@DisplayName("Corriendo las pruebas para insertar una vacación")
public class VacacionesDAOTest {
    @Test
    public void InsertarVacacionTest(){
        VacacionesDAO dao = mock(VacacionesDAO.class);
        Vacaciones vac = new Vacaciones();
        
        when(dao.insertarVacacion(vac)).thenReturn(true);
        
        assertEquals(dao.insertarVacacion(vac), true);
    }
}
