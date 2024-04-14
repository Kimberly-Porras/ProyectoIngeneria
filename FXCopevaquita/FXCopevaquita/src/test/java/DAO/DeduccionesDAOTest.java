/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.Deduccion;
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

@DisplayName("Corriendo los test de deducciones...")
public class DeduccionesDAOTest {
    
    @Test
    public void InsertarDeduccion() {
        DeduccionesDAO dao = mock(DeduccionesDAO.class);
        Deduccion deduc = new Deduccion();
        
        when(dao.insertarDeduccion(deduc))
                .thenReturn(true);
        
        assertEquals(dao.insertarDeduccion(deduc), true);
    }
}
