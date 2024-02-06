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
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ActualizarDeduccionesController implements Initializable {

    @FXML
    private ComboBox<?> cbxFiltrarEmpleadoAct;
    @FXML
    private TableView<?> tblDeduccionesEmpleadosAct;
    @FXML
    private TableColumn<?, ?> colTipoDeduccionAct;
    @FXML
    private TableColumn<?, ?> colMotivoAct;
    @FXML
    private TableColumn<?, ?> colFrecuenciaAct;
    @FXML
    private TableColumn<?, ?> colCuotaAct;
    @FXML
    private TableColumn<?, ?> colPorcentualAct;
    @FXML
    private TableColumn<?, ?> colTotalAct;
    @FXML
    private TableColumn<?, ?> colEstadoAct;
    @FXML
    private ComboBox<?> cbxTipoDeduccionAct;
    @FXML
    private TextField txtMotivoAct;
    @FXML
    private ComboBox<?> cbxFrecuenciaAct;
    @FXML
    private TextField txtCuotaAct;
    @FXML
    private TextField txtPorcentualAct;
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
    private void OnFiltrarEmpleado(ActionEvent event) {
    }

    @FXML
    private void OnPresionarCualquierLado(MouseEvent event) {
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
