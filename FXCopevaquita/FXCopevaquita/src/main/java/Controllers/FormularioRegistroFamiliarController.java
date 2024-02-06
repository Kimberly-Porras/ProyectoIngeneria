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
public class FormularioRegistroFamiliarController implements Initializable {

    @FXML
    private ComboBox<?> cbxBuscarEmpleado;
    @FXML
    private TextField txtCedula;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellidos;
    @FXML
    private TextField txtParentezco;
    @FXML
    private ComboBox<?> cbxSexo;
    @FXML
    private DatePicker dpFechaNacimiento;
    @FXML
    private CheckBox cbEstadoEmpleado;
    @FXML
    private ComboBox<?> cbxTipoContacto;
    @FXML
    private TextField txtDescripcionContacto;
    @FXML
    private CheckBox cbEstadoContacto;
    @FXML
    private TableView<?> tblContacto;
    @FXML
    private TableColumn<?, ?> colTipoContacto;
    @FXML
    private TableColumn<?, ?> colDescripcionContacto;
    @FXML
    private TableColumn<?, ?> colEstadoContacto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void OnAgregarContacto(ActionEvent event) {
    }

    @FXML
    private void OnEditarContacto(ActionEvent event) {
    }

    @FXML
    private void OnEditar(ActionEvent event) {
    }

    @FXML
    private void btnGuardar(ActionEvent event) {
    }
    
}
