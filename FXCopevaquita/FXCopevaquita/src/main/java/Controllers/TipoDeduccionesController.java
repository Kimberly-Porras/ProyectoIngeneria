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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author User
 */
public class TipoDeduccionesController implements Initializable {

    @FXML
    private TextField txtNombreTipoDeducciones;
    @FXML
    private CheckBox cbEstadoTipoDeducciones;
    @FXML
    private TextField txtFiltrarTipoDeducciones;
    @FXML
    private TableView<?> tblTipoDeduccion;
    @FXML
    private TableColumn<?, ?> colIdTipoDeduccion;
    @FXML
    private TableColumn<?, ?> colNombreTipoDeduccion;
    @FXML
    private TableColumn<?, ?> colEstadoTipoDeduccion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnGuardarTipoDeducciones(ActionEvent event) {
    }

    @FXML
    private void btnActualizarTipoDeducciones(ActionEvent event) {
    }

    @FXML
    private void OnPresionarTipoDeduccion(KeyEvent event) {
    }

    @FXML
    private void btnEditarTipoDeducciones(ActionEvent event) {
    }
    
}
