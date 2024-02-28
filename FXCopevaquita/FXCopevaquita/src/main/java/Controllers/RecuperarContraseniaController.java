/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import Alertas.MensajePersonalizado;
import Gmail.MailSender;
import DAO.CredencialesDAO;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 * @author alber
 * @author kim03
 */
public class RecuperarContraseniaController implements Initializable {

    @FXML
    private TextField tf_password;
    @FXML
    private TextField tf_code;
    @FXML
    private TextField tf_correo;
    @FXML
    private Button btn_save;

    private Stage stage;
    @FXML
    private Button btn_sendCode;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void onSendCode(ActionEvent event) {
        var mailSender = new MailSender();
        var userEmail = tf_correo.getText();

        if (userEmail.isEmpty()) {
            return; // El usuario es subnormal
        }
        try {
            mailSender.sendRecoveryCode(userEmail);
            tf_correo.setEditable(true);
            tf_password.setEditable(true);
            tf_code.setEditable(true);

            btn_save.setDisable(false);
        } catch (Exception e) {
            MensajePersonalizado.Ver("Proceso Fallido", "Puede cerrar esta ventana y reintentar más tarde", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void onSave(ActionEvent event) throws IOException {

        var isOk = MailSender.validateCode(tf_code.getText());

        if (isOk && !tf_correo.getText().isEmpty() && !tf_password.getText().isEmpty()) {

            System.out.println("todo va bien, queda guardar los cambios...");
            new CredencialesDAO().actualizarContrasenia(tf_correo.getText(), tf_password.getText());
            MensajePersonalizado.Ver("Proceso realizado con exito", "Puede cerrar esta ventana", Alert.AlertType.INFORMATION);

            tf_correo.setEditable(false);
            tf_password.setEditable(false);
            tf_code.setEditable(false);

            btn_save.setDisable(true);
            btn_sendCode.setDisable(true);
            return;
        }
        MensajePersonalizado.Ver("Proceso fallido", "Cierre esta ventana e intente de nuevo", Alert.AlertType.ERROR);
    }
}
