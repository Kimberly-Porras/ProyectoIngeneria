/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import Alertas.MensajePersonalizado;
import DAO.PorcentajeRebajosDAO;
import Models.PorcentajeRebajos;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author alber
 * @author kim03
 */
public class PorcentajesController implements Initializable {

    @FXML
    private TextField txtPorcentaje;
    @FXML
    private Label lb_porce;

    final PorcentajeRebajosDAO porcentajeService = new PorcentajeRebajosDAO();
    @FXML
    private Button btn_actualizar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        startup();
    }

    private void startup() {
        var porcentaje = porcentajeService.obtenerPorcentajeRebajos();
        if (porcentaje == null) {
            lb_porce.setText("Porcentaje no disponible");
            txtPorcentaje.setText("Porcentaje no disponible");
            return;
        }

        lb_porce.setText(porcentaje.getGobierno() + "%");
        txtPorcentaje.setText(porcentaje.getGobierno() + "");
    }

    private void notifyChange(double newValue) {
        lb_porce.setText(newValue + "%");
        txtPorcentaje.setText(newValue + "");
    }
    
    private void changePercent() {
        double value = 0;
        try {
            value = Double.parseDouble(txtPorcentaje.getText());
        } catch (Exception e) {
            value = 0;
        }

        var result = porcentajeService.actualizarPorcentaje(new PorcentajeRebajos(1, value));
        if (!result) {
            MensajePersonalizado.Ver("Error", "no se pudo actualizar correctamente", Alert.AlertType.ERROR);
            return;
        }

        notifyChange(value);
    }

    @FXML
    private void onClick(ActionEvent event) {
        changePercent();
    }

    @FXML
    private void onEnter(KeyEvent event) {
        if(event.getCode().toString() == "ENTER"){
            changePercent();
        }
    }


}
