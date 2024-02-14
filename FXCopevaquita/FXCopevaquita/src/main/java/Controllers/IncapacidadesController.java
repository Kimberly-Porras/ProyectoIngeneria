/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import DAO.EmpleadoDAO;
import DAO.IncapacidadDAO;
import Helpers.OpenWindowsHandler;
import Models.Empleado;
import Models.Incapacidad;
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
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author User
 */
public class IncapacidadesController implements Initializable {

    @FXML
    private TextField filtrarEmpleado;
    @FXML
    private TableView<Incapacidad> tblIncapacidad;
    @FXML
    private TableColumn<Incapacidad, String> colCedula;
    @FXML
    private TableColumn<Incapacidad, String> colNombre;
    @FXML
    private TableColumn<Incapacidad, String> colEstado;
    @FXML
    private TableColumn<Incapacidad, String> colFecha;
    @FXML
    private TableColumn<Incapacidad, String> colMonto;
    @FXML
    private TableColumn<Incapacidad, String> colMotivo;

    /**
     * Initializes the controller class.
     */
    
    ObservableList<Empleado> ObservableEmpleado = FXCollections.observableArrayList();
    ObservableList<Incapacidad> ObservableIncapacidad = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurar();
        cargarIncapacidades();
    }

    public void configurar() {
        colFecha.setCellValueFactory(new PropertyValueFactory<>("empleado"));
        colNombre.setCellValueFactory(cellData -> {
            var empleado = new EmpleadoDAO().obtenerEmpleadoPorCedula(cellData.getValue().getEmpleado());
            if(empleado == null) {
                return new SimpleStringProperty("No disponible");
            }
            return new SimpleStringProperty(empleado.getNombreCompleto());
        });
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colMonto.setCellValueFactory(new PropertyValueFactory<>("monto"));
        colMotivo.setCellValueFactory(new PropertyValueFactory<>("motivo"));
        colEstado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().isStatus()? "Pendiente" : "Cancelado"));
    }

    private String GetNombreCompleto(String cedula) {
        Optional<Empleado> empleadoOptional = ObservableEmpleado.stream()
                .filter(x -> x.getCedula().equals(cedula))
                .findFirst();

        return empleadoOptional.map(Empleado::getNombreCompleto).orElse("");
    }
    
    public void cargarIncapacidades() {
        var ObservableIncapacidad
                = FXCollections.observableArrayList(new IncapacidadDAO().obtenerListaIncapacidades());
        tblIncapacidad.setItems(ObservableIncapacidad);
    }
    
    private void filtrarIncapacidad() {
        if (filtrarEmpleado.getText() != null && !filtrarEmpleado.getText().trim().equals("")) {
            Predicate<Incapacidad> pVacacion = x
                    -> x.getEmpleado().toLowerCase().contains(filtrarEmpleado.getText().toLowerCase());
            Predicate<Empleado> pEmpleado = x
                    -> x.getCedula().toLowerCase().contains(filtrarEmpleado.getText().toLowerCase())
                    || x.getNombre().toLowerCase().contains(filtrarEmpleado.getText().toLowerCase())
                    || x.getApellidos().toLowerCase().contains(filtrarEmpleado.getText().toLowerCase())
                    || x.getNombreCompleto().toLowerCase().contains(filtrarEmpleado.getText().toLowerCase());
            var listaTemporal = ObservableIncapacidad.filtered((x) -> pEmpleado.test(Get(x.getEmpleado())) || pVacacion.test(x));
            tblIncapacidad.setItems(listaTemporal);
        } else {
            tblIncapacidad.setItems(ObservableIncapacidad);
        }
    }
    
    private Empleado Get(String cedula) {
        return ObservableEmpleado.filtered(x -> x.getCedula().equals(cedula)).get(0);
    }
    
    @FXML
    private void OnAgregar(ActionEvent event) {
        OpenWindowsHandler.AbrirVentanaAgregarIncapacidades("/views/AgregarIncapacidades");
    }

    @FXML
    private void OnActualizar(ActionEvent event) {
        OpenWindowsHandler.AbrirVentanaActualizarIncapacidades("/views/ActualizarIncapacidades");
    }

    @FXML
    private void PresioanrEnter(KeyEvent event) {
        filtrarIncapacidad();
    }

    @FXML
    private void OnPresionarCualquierLado(MouseEvent event) {
    }

}
