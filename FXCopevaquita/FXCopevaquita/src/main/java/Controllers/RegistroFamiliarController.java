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
public class RegistroFamiliarController implements Initializable {

    @FXML
    private TextField txtFiltrarParentezco;
    @FXML
    private TableView<?> tblParentezco;
    @FXML
    private TableColumn<?, ?> colCedula;
    @FXML
    private TableColumn<?, ?> colNombre;
    @FXML
    private TableColumn<?, ?> colApellidos;
    @FXML
    private TableColumn<?, ?> colParentezco;
    @FXML
    private TableColumn<?, ?> colSexo;
    @FXML
    private TableColumn<?, ?> colFechaNacimiento;
    @FXML
    private TableColumn<?, ?> colEstado;
    @FXML
    private TableColumn<?, ?> colContactos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void OnAgregar(ActionEvent event) {
            OpenWindowsHandler.AbrirVentanaFormularioRegistroFamiliar("/views/FormularioRegistroFamiliar");
    }

    @FXML
    private void OnActualizar(ActionEvent event) {
         OpenWindowsHandler.AbrirVentanaFormularioRegistroFamiliar("/views/FormularioRegistroFamiliar");
    }

    @FXML
    private void OnFiltrar(KeyEvent event) {
    }
    
}
