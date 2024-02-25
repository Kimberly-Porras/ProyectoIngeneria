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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
/**
 * FXML Controller class
 *
 * @author User
 */
public class PagosController implements Initializable {


    @FXML
    private ComboBox<?> cbxEmpleado;
    @FXML
    private TableView<?> tblPagos;
    @FXML
    private TableColumn<?, ?> colEmpleado;
    @FXML
    private TableColumn<?, ?> colFechaInicio;
    @FXML
    private TableColumn<?, ?> colFechaFin;
    @FXML
    private TableColumn<?, ?> colIncapacidades;
    @FXML
    private TableColumn<?, ?> colVacaciones;
    @FXML
    private TableColumn<?, ?> colDeducciones;
    @FXML
    private TableColumn<?, ?> colTotal;
    @FXML
    private TableColumn<?, ?> colContratos;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    

    @FXML
    private void cbxEmpleado(ActionEvent event) {
    }

    @FXML
    private void OnGenerarPago(ActionEvent event) {
        OpenWindowsHandler.AbrirVentanaGenerarPagos("/views/GenerarPagos");
    }

}
