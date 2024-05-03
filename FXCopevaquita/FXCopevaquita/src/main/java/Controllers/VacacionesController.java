/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import DAO.EmpleadoDAO;
import DAO.VacacionesDAO;
import Helpers.OpenWindowsHandler;
import Models.Empleado;
import Models.Vacaciones;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 * @author alber
 * @author kim03
 */
public class VacacionesController implements Initializable {

    @FXML
    private TextField filtrarEmpleado;
    @FXML
    private TableView<Vacaciones> tblVacacion;
    @FXML
    private TableColumn<Vacaciones, String> colCedula;
    @FXML
    private TableColumn<Vacaciones, String> colNombre;
    @FXML
    private TableColumn<Vacaciones, String> colEstado;
    @FXML
    private TableColumn<Vacaciones, String> colMonto;
    @FXML
    private TableColumn<Vacaciones, String> colFecha;
    @FXML
    private ComboBox<String> cbx_status;

    ObservableList<Empleado> ObservableEmpleado = FXCollections.observableArrayList();
    ObservableList<Vacaciones> ObservableVacaciones = FXCollections.observableArrayList();
    ObservableList<String> observableStatus = FXCollections.observableArrayList("Pendiente", "Cancelado");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurar();
        cargarVacaciones(true, false);
    }

    public void configurar() {
        colCedula.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmpleado()));
        colNombre.setCellValueFactory(cellData -> {
            var empleado = new EmpleadoDAO().obtenerEmpleadoPorCedula(cellData.getValue().getEmpleado());
            if (empleado == null) {
                return new SimpleStringProperty("No disponible");
            }
            return new SimpleStringProperty(empleado.getNombreCompleto());
        });
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colMonto.setCellValueFactory(new PropertyValueFactory<>("monto"));
        colEstado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().isStatus() ? "Pendiente" : "Cancelado"));
        cbx_status.setItems(observableStatus);
        
        cbx_status.setOnAction(event -> {
            var value = cbx_status.getValue();
            if (value.equals("Pendiente")) {
                cargarVacaciones(true, true);
            } else {
                cargarVacaciones(false, true);
            }
        });
    }

    private String GetNombreCompleto(String cedula) {
        Optional<Empleado> empleadoOptional = ObservableEmpleado.stream()
                .filter(x -> x.getCedula().equals(cedula))
                .findFirst();

        return empleadoOptional.map(Empleado::getNombreCompleto).orElse("");
    }

    public void cargarVacaciones(boolean status, boolean filtro) {
        if (filtro) {
            var ObservableIncapacidad
                    = FXCollections.observableArrayList(new VacacionesDAO().obtenerListaVacaciones())
                            .filtered(vac -> vac.isStatus() == status);
            tblVacacion.setItems(ObservableIncapacidad);
        } else {
            var ObservableIncapacidad
                    = FXCollections.observableArrayList(new VacacionesDAO().obtenerListaVacaciones());
            tblVacacion.setItems(ObservableIncapacidad);
        }
    }

    private Empleado Get(String cedula) {
        return ObservableEmpleado.filtered(x -> x.getCedula().equals(cedula)).get(0);
    }

    private void filtrarVacacion() {
        if (filtrarEmpleado.getText() != null && !filtrarEmpleado.getText().trim().equals("")) {
            Predicate<Vacaciones> pVacacion = x
                    -> x.getEmpleado().toLowerCase().contains(filtrarEmpleado.getText().toLowerCase());
            Predicate<Empleado> pEmpleado = x
                    -> x.getCedula().toLowerCase().contains(filtrarEmpleado.getText().toLowerCase())
                    || x.getNombre().toLowerCase().contains(filtrarEmpleado.getText().toLowerCase())
                    || x.getApellidos().toLowerCase().contains(filtrarEmpleado.getText().toLowerCase())
                    || x.getNombreCompleto().toLowerCase().contains(filtrarEmpleado.getText().toLowerCase());
            var listaTemporal = ObservableVacaciones.filtered((x) -> pEmpleado.test(Get(x.getEmpleado())) || pVacacion.test(x));
            tblVacacion.setItems(listaTemporal);
        } else {
            cargarVacaciones(true, false);
        }
    }

    @FXML
    private void OnAgregar(ActionEvent event) {
        OpenWindowsHandler.AbrirVentanaAgregarVacaciones("/views/AgregarVacaciones");
    }

    @FXML
    private void OnActualizar(ActionEvent event) {
        OpenWindowsHandler.AbrirVentanaActualizarVacaciones("/views/ActualizarVacaciones");

    }

    @FXML
    private void PresioanrEnter(KeyEvent event) {
        filtrarVacacion();
    }

    @FXML
    private void OnRefrescar(ActionEvent event) {
        cargarVacaciones(true, false);
    }

}
