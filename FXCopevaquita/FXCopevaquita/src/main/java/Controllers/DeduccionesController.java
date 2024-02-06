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
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author User
 */
public class DeduccionesController implements Initializable {

    @FXML
    private TextField txtfiltrarEmpleado;
    @FXML
    private TableView<?> tblDeduccionesEmpleados;
    @FXML
    private TableColumn<?, ?> colCedula;
    @FXML
    private TableColumn<?, ?> colNombre;
    @FXML
    private TableColumn<?, ?> colTipoDeduccion;
    @FXML
    private TableColumn<?, ?> colMotivo;
    @FXML
    private TableColumn<?, ?> colFrecuencia;
    @FXML
    private TableColumn<?, ?> colCuota;
    @FXML
    private TableColumn<?, ?> colPorcentual;
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
        OpenWindowsHandler.AbrirVentanaAgregarDeduccion("/views/AgregarDeducciones");
    }

    @FXML
    private void OnActualizar(ActionEvent event) {
        OpenWindowsHandler.AbrirVentanaActualizarDeduccion("/views/ActualizarDeducciones");
    }

    @FXML
    private void OnAbonos(ActionEvent event) {
        OpenWindowsHandler.AbrirVentanaAbonoDeduccion("/views/AbonoDeducciones");
    }
    
    @FXML
    private void PresioanrEnter(KeyEvent event) {
    }

    @FXML
    private void OnPresionarCualquierLado(MouseEvent event) {
    }
    
}
