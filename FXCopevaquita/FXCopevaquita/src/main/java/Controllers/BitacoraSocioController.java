/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import DAO.BitacoraSocioDAO;
import Helpers.OpenWindowsHandler;
import Models.BitacoraSocio;
import Models.Empleado;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author User
 */
public class BitacoraSocioController implements Initializable {

    @FXML
    private TextField txtFiltrarEmpleado;
    @FXML
    private TableView<BitacoraSocio> tblListarReporteSocio;
    @FXML
    private TableColumn<BitacoraSocio, String> colCedula;
    @FXML
    private TableColumn<BitacoraSocio, String> colNombre;
    @FXML
    private TableColumn<BitacoraSocio, Double> colHoras;
    @FXML
    private TableColumn<BitacoraSocio, String> colDescripcion;
    @FXML
    private TableColumn<BitacoraSocio, String> colEstado;
    
    ObservableList<Empleado> ObservableEmpleado = FXCollections.observableArrayList();
    ObservableList<BitacoraSocio> ObservableBitacoraSocio = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Configurar();
        cargarBitacoraSocio();
        
    }    
    
    private void Configurar() {
        colCedula.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCedula_empleado()));
        colNombre.setCellValueFactory(cellData -> new SimpleStringProperty(GetNombreCompleto(cellData.getValue().getCedula_empleado())));
        colHoras.setCellValueFactory(new PropertyValueFactory<>("horas"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        colEstado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().isStatus() ? "Activo" : "Inactivo"));
    }
    private String GetNombreCompleto(String cedula) {
        Optional<Empleado> empleadoOptional = ObservableEmpleado.stream()
                .filter(x -> x.getCedula().equals(cedula))
                .findFirst();

        return empleadoOptional.map(Empleado::getNombreCompleto).orElse("");
    }
    
    private void filtrarBitacoraSocio() {
        if (txtFiltrarEmpleado.getText() != null && !txtFiltrarEmpleado.getText().trim().equals("")) {
            Predicate<BitacoraSocio> pReporte = x
                    -> x.getCedula_empleado().toLowerCase().contains(txtFiltrarEmpleado.getText().toLowerCase());
            Predicate<Empleado> pEmpleado = x
                    -> x.getCedula().toLowerCase().contains(txtFiltrarEmpleado.getText().toLowerCase())
                    || x.getNombre().toLowerCase().contains(txtFiltrarEmpleado.getText().toLowerCase())
                    || x.getApellidos().toLowerCase().contains(txtFiltrarEmpleado.getText().toLowerCase())
                    || x.getNombreCompleto().toLowerCase().contains(txtFiltrarEmpleado.getText().toLowerCase());
            var listaTemporal = ObservableBitacoraSocio.filtered((x) -> pEmpleado.test(Get(x.getCedula_empleado())) || pReporte.test(x));
            tblListarReporteSocio.setItems(listaTemporal);
        } else {
            tblListarReporteSocio.setItems(ObservableBitacoraSocio);
        }
    }
    public void cargarBitacoraSocio() {
        var ObservableIncapacidad
                = FXCollections.observableArrayList(new BitacoraSocioDAO().obtenerListaBitacoraSocio());
        tblListarReporteSocio.setItems(ObservableIncapacidad);
    }

    private Empleado Get(String cedula) {
        return ObservableEmpleado.filtered(x -> x.getCedula().equals(cedula)).get(0);
    }
        @FXML
    private void OnAgregar(ActionEvent event) {
        OpenWindowsHandler.AbrirVentanaAgregarBitacoraSocio("/views/AgregarBitacoraSocio");
    }

    @FXML
    private void OnActualizar(ActionEvent event) {
        OpenWindowsHandler.AbrirVentanaActualizarBitacoraSocio("/views/ActualizarBitacoraSocio");
    }

    @FXML
    private void OnReporteDiarioSocio(ActionEvent event) {
    }

    @FXML
    private void PresionarEnter(KeyEvent event) {
        filtrarBitacoraSocio();
    }
    
}
