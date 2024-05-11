/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import Models.PorcentajeRebajos;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
/**
 * FXML Controller class
 *
 * @author alber
 * @author kim03
 */

public class PorcentajesController implements Initializable {


    @FXML
    private CheckBox cbEstadoActividad;
    @FXML
    private TableView<PorcentajeRebajos> tblPorcentajes;
    @FXML
    private TableColumn<PorcentajeRebajos, String> colIdActividad;
    @FXML
    private TableColumn<PorcentajeRebajos, String> colPorcentaje;
    @FXML
    private TableColumn<PorcentajeRebajos, String> colEstadoActividad;
    @FXML
    private TextField txtPorcentaje;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void btnActualizarActividad(ActionEvent event) {
    }

    @FXML
    private void btnCargarDatos(ActionEvent event) {
    }

}