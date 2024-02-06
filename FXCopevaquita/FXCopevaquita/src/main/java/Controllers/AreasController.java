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
public class AreasController implements Initializable {

    @FXML
    private TextField txtNombreArea;
    @FXML
    private CheckBox cbEstadoArea;
    @FXML
    private TextField txtFiltrarArea;
    @FXML
    private TableView<?> tblAreas;
    @FXML
    private TableColumn<?, ?> colIdArea;
    @FXML
    private TableColumn<?, ?> colNombreArea;
    @FXML
    private TableColumn<?, ?> colEstadoArea;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnGuardarArea(ActionEvent event) {
    }

    @FXML
    private void btnActualizarArea(ActionEvent event) {
    }

    @FXML
    private void OnPresionarArea(KeyEvent event) {
    }

    @FXML
    private void btnEditarArea(ActionEvent event) {
    }
    
}
