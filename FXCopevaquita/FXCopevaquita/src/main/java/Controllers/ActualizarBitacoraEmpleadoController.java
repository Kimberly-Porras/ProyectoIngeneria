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
public class ActualizarBitacoraEmpleadoController implements Initializable {

    @FXML
    private ComboBox<?> cbxFiltrarEmpleadoActualizar;
    @FXML
    private TableView<?> tblReporteEmpleadoActualizar;
    @FXML
    private TableColumn<?, ?> colFechaRegistroActualizar;
    @FXML
    private TableColumn<?, ?> colActividadesActualizar;
    @FXML
    private TableColumn<?, ?> colAreasActualizar;
    @FXML
    private TableColumn<?, ?> colCantidadActualizar;
    @FXML
    private TableColumn<?, ?> colPrecioActualizar;
    @FXML
    private TableColumn<?, ?> colEstadoActualizar;
    @FXML
    private DatePicker dpFecharegistroAct;
    @FXML
    private ComboBox<?> cbxActividadesAct;
    @FXML
    private ComboBox<?> cbxAreasAct;
    @FXML
    private TextField txtCantidadAct;
    @FXML
    private TextField txtPrecioAct;
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
