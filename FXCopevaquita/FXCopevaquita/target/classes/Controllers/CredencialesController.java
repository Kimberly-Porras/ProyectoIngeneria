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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author aleke
 */
public class CredencialesController implements Initializable {

    @FXML
    private ComboBox<?> cbxFiltrarEmpleado;
    @FXML
    private TextField txtUsuario;
    @FXML
    private TextField txtContrasenia;
    @FXML
    private TextField txtCorreo;
    @FXML
    private CheckBox cbEstado;
    @FXML
    private TextField txtFiltrarCredenciales;
    @FXML
    private TableView<?> tblCredencial;
    @FXML
    private TableColumn<?, ?> colId;
    @FXML
    private TableColumn<?, ?> colCedula;
    @FXML
    private TableColumn<?, ?> colUsuario;
    @FXML
    private TableColumn<?, ?> colContrasenia;
    @FXML
    private TableColumn<?, ?> colCorreo;
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
    private void OnFiltrarEmpleado(ActionEvent event) {
    }

    @FXML
    private void btnGuardar(ActionEvent event) {
    }

    @FXML
    private void btnActualizar(ActionEvent event) {
    }

    @FXML
    private void OnFiltrarCredenciales(KeyEvent event) {
    }

    @FXML
    private void btnCargarDatos(ActionEvent event) {
    }

    @FXML
    private void OnRefrescar(ActionEvent event) {
    }
    
}
