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
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AbonoDeduccionesController implements Initializable {

    @FXML
    private ComboBox<?> cbxFiltrarEmpleadoDetalle;
    @FXML
    private TableView<?> tblDeduccionesEmpleadosDetalle1;
    @FXML
    private TableColumn<?, ?> colTipoDeduccionAct1;
    @FXML
    private TableColumn<?, ?> colMotivoAct1;
    @FXML
    private TableColumn<?, ?> colFrecuenciaAct1;
    @FXML
    private TableColumn<?, ?> colCuotaAct1;
    @FXML
    private TableColumn<?, ?> colPorcentualAct1;
    @FXML
    private TableColumn<?, ?> colTotalAct1;
    @FXML
    private TableColumn<?, ?> colEstadoAct1;
    @FXML
    private TextField txtTipoDeduccionDet;
    @FXML
    private TextField txtMontoTotalDet;
    @FXML
    private DatePicker dpFechaRegistroDet;
    @FXML
    private TextField txtMontoDet;
    @FXML
    private TextField txtPendienteDet;
    @FXML
    private TextField txtDescripcionDet;
    @FXML
    private CheckBox cbEstadoDet;
    @FXML
    private TableView<?> tblDetalle;
    @FXML
    private TableColumn<?, ?> colFechaDet;
    @FXML
    private TableColumn<?, ?> colMontoDet;
    @FXML
    private TableColumn<?, ?> colPendienteDet;
    @FXML
    private TableColumn<?, ?> colDescripcionDet;
    @FXML
    private TableColumn<?, ?> colEstadoDet;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void OnFiltrarEmpleadoDet(ActionEvent event) {
    }

    @FXML
    private void btnCargarDeduccionDet(ActionEvent event) {
    }

    @FXML
    private void OnAgregarDetalle(ActionEvent event) {
    }

    @FXML
    private void OnOnEditarDetalle(ActionEvent event) {
    }

    @FXML
    private void OnCargarDatosDetalle(ActionEvent event) {
    }

}
