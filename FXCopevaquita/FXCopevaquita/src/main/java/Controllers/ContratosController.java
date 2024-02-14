/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import Helpers.OpenWindowsHandler;
import Models.Contrato;
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
public class ContratosController implements Initializable {


    @FXML
    private TextField txtFiltrarEmpleado;
    @FXML
    private TableView<Contrato> tblListaContrato;
    @FXML
    private TableColumn<Contrato, String> colCedula;
    @FXML
    private TableColumn<Contrato, String> colNombre;
    @FXML
    private TableColumn<Contrato, String> colFechaInicio;
    @FXML
    private TableColumn<Contrato, String> colFechaFinal;
    @FXML
    private TableColumn<Contrato, String> colFechaRegistro;
    @FXML
    private TableColumn<Contrato, String> colMonto;
    @FXML
    private TableColumn<Contrato, String> colEstado;
    @FXML
    private TableColumn<Contrato, String> colActividad;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void OnAgregar(ActionEvent event) {
        OpenWindowsHandler.AbrirVentanaActualizarContratos("/views/ActualizarContratos");
    }

    @FXML
    private void OnActualizar(ActionEvent event) {
        OpenWindowsHandler.AbrirVentanaAreas("/views/AgregarContratos");
    }

    @FXML
    private void PresionarEnter(KeyEvent event) {
    }

}
