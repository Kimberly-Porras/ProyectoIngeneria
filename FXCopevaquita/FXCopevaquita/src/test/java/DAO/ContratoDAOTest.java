/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.Contrato;
import DAO.ContratoDAO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 *
 * @author aleke
 */
@DisplayName("Corriendo los test de contratos")
public class ContratoDAOTest {
    
    @Test
    public void InsertarContratoTest(){
        ContratoDAO dao = mock(ContratoDAO.class);
        Contrato contra = new Contrato();
        when(dao.insertarContrato(contra)).thenReturn(true);
        
        assertEquals(dao.insertarContrato(contra), true);
    }
}
