/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AgregarContratosController implements Initializable {

    @FXML
    private ComboBox<?> cbxFiltrarEmpleadoActualizar;
    @FXML
    private TableView<?> tblContratoAct;
    @FXML
    private TableColumn<?, ?> colFechaInicioAct;
    @FXML
    private TableColumn<?, ?> colFechaFinalAct;
    @FXML
    private TableColumn<?, ?> colFechaRegistroAct;
    @FXML
    private TableColumn<?, ?> colMontoAct;
    @FXML
    private TableColumn<?, ?> colEstadoAct;
    @FXML
    private DatePicker dpFechaInicioAct;
    @FXML
    private DatePicker dpFechaFinalAct;
    @FXML
    private DatePicker dpFecharegistroAct;
    @FXML
    private TextField txtMontoAct;
    @FXML
    private CheckBox cbEstadoAct;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void OnAgregar(ActionEvent event) {
    }

    @FXML
    private void OnActualizar(ActionEvent event) {
    }

    @FXML
    private void FiltrarEmpleado(ActionEvent event) {
    }

    @FXML
    private void onCargar(ActionEvent event) {
    }

    @FXML
    private void onLimpiar(ActionEvent event) {
    }

    @FXML
    private void btnActualizar(ActionEvent event) {
    }
    
}
