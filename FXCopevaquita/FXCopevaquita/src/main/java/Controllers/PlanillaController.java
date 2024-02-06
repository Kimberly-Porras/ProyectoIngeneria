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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author User
 */
public class PlanillaController implements Initializable {

    @FXML
    private DatePicker dpFechaInicio;
    @FXML
    private DatePicker dpFechaFinal;
    @FXML
    private ComboBox<?> cbxEmpleado;
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnExportarExcel;
    @FXML
    private Button btnExportarPDF;
    @FXML
    private TableView<?> tblReporte;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void OnChangeEmpleado(ActionEvent event) {
    }

    @FXML
    private void OnBuscar(ActionEvent event) {
    }

    @FXML
    private void OnExportarExcel(ActionEvent event) {
    }

    @FXML
    private void OnExportarPDF(ActionEvent event) {
    }
    
}
