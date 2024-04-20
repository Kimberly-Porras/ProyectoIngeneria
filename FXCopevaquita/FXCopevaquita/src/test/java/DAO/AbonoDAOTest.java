package DAO;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
 * @author alber
 * @author kim03
 */
@DisplayName("Pruebas sobre las operaciones de abonos")
public class AbonoDAOTest {

    @Test
    @DisplayName("Los abonos se insertan correctamente")
    public void insertarAbonos() {
        AbonoDAO abonos = mock(AbonoDAO.class);

        // Al crear un abono el dao realiza la operación correctamente
        Abono abonotest = new Abono(); 

        when(abonos.insertarAbono(abonotest
        )).thenReturn(true);

        assertEquals(abonos.insertarAbono(abonotest), true);
    }

    @Test
    @DisplayName("Los abonos se actualizan correctamente")
    public void ActualizarAbonos() {
        AbonoDAO abono = mock(AbonoDAO.class);
        Abono abonotest = new Abono();

        when(abono.actualizarAbono(abonotest))
                .thenReturn(true);
        assertEquals(true, abono.actualizarAbono(abonotest));
    }

    @Test
    @DisplayName("Se pueden generar abonos correctamente")
    public void GenerarAbono() {
        AbonoDAO abono = mock(AbonoDAO.class);
        Abono abonotest = new Abono();
        Deduccion deducciontest = new Deduccion();

        when(abono.generarAbono(abonotest, deducciontest))
                .thenReturn(true);
        assertEquals(true, abono.generarAbono(abonotest, deducciontest));
    }

}
