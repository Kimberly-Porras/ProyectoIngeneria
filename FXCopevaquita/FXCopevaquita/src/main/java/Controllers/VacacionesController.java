/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import Helpers.OpenWindowsHandler;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author User
 */
public class VacacionesController implements Initializable {

    @FXML
    private TextField filtrarEmpleado;
    @FXML
    private TableView<?> tblVacacion;
    @FXML
    private TableColumn<?, ?> colCedula;
    @FXML
    private TableColumn<?, ?> colNombre;
    @FXML
    private TableColumn<?, ?> colDiasDisponibles;
    @FXML
    private TableColumn<?, ?> colAnno;
    @FXML
    private TableColumn<?, ?> colTotal;
    @FXML
    private TableColumn<?, ?> colEstado;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void OnAgregar(ActionEvent event) {
        OpenWindowsHandler.AbrirVentanaAgregarVacaciones("/views/AgregarVacaciones");
    }

    @FXML
    private void OnActualizar(ActionEvent event) {
         OpenWindowsHandler.AbrirVentanaActualizarVacaciones("/views/ActualizarVacaciones");

    }

    @FXML
    private void PresioanrEnter(KeyEvent event) {
    }

    
}
