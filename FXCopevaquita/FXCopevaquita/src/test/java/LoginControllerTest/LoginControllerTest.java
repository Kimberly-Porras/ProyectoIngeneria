/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LoginControllerTest;

import org.junit.jupiter.api.Test;
import Controllers.LoginController;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author alber
 * @author kim03
 */

public class LoginControllerTest {
    @Test
    public void fieldsValidator() {
        LoginController login = new LoginController();
        assertEquals(false, login.isValidFields("", ""));
    }
}
