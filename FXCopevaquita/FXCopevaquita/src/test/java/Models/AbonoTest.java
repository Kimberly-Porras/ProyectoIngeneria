/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import DAO.AbonoDAO;
import Models.Abono;
import Models.Deduccion;
import java.sql.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 *
 * @author aleke
 */
public class AbonoTest {
    @Test
    public void ElMontoEsCorrecto() {
        Abono abono = new Abono(0, 0, 100.1, new Date(10000), "nota");
        assertEquals(100.1, abono.getMonto());
    }
}
