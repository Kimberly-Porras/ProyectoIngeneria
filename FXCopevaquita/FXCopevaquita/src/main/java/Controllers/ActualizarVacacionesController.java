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

/**
 * FXML Controller class
 *
 * @author User
 */
public class ActualizarVacacionesController implements Initializable {

    @FXML
    private ComboBox<?> cbxFiltrarEmpleadoActualizar;
    @FXML
    private TableView<?> tblVacacionesActualizar;
    @FXML
    private TableColumn<?, ?> colDiasDisponiblesAct;
    @FXML
    private TableColumn<?, ?> colAnnoAct;
    @FXML
    private TableColumn<?, ?> colTotalAct;
    @FXML
    private TableColumn<?, ?> colEstadoAct;
    @FXML
    private TextField txtDiasDisponiblesAct;
    @FXML
    private TextField txtAnnoAct;
    @FXML
    private TextField txtTotalAct;
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
