/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Models.PlanillaSocios;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 *
 * @author aleke
 */
@DisplayName("Corriendo los test de salario fijo en socios")
public class PlanillaSociosDAOTest {

    @Test
    public void insertarPlanillaSociosTest() {
        PlanillaSociosDAO dao = mock(PlanillaSociosDAO.class);
        PlanillaSocios pla = new PlanillaSocios();
        when(dao.insertarPlanillaSocios(pla)).thenReturn(true);

        assertEquals(dao.insertarPlanillaSocios(pla), true);
    }
}
