/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;


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
    
    ObservableList<Empleado> ObservableEmpleado = FXCollections.observableArrayList();
    ObservableList<Vacaciones> ObservableVacaciones = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurar();
        cargarVacaciones();
    }    
    
    public void configurar(){
        colCedula.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmpleado()));
        colNombre.setCellValueFactory(cellData -> new SimpleStringProperty(GetNombreCompleto(cellData.getValue().getEmpleado())));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colMonto.setCellValueFactory(new PropertyValueFactory<>("monto"));
        colEstado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().isStatus()? "Pendiente" : "Cancelado"));
    }
    private String GetNombreCompleto(String cedula) {
        Optional<Empleado> empleadoOptional = ObservableEmpleado.stream()
                .filter(x -> x.getCedula().equals(cedula))
                .findFirst();

        return empleadoOptional.map(Empleado::getNombreCompleto).orElse("");
    }
    
    public void cargarVacaciones() {
        var ObservableIncapacidad
                = FXCollections.observableArrayList(new VacacionesDAO().obtenerListaVacaciones());
        tblVacacion.setItems(ObservableIncapacidad);
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
            tblVacacion.setItems(ObservableVacaciones);
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

    
}
