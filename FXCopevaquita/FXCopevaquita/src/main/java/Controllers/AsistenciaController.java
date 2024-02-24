/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import DAO.BitacoraAsistenciaDAO;
import DAO.EmpleadoDAO;
import Helpers.OpenWindowsHandler;
import Models.BitacoraAsistencia;
import Models.Empleado;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
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
 *
 * @author User
 */
public class AsistenciaController implements Initializable {

    @FXML
    private TextField txtFiltrarEmpleado;
    @FXML
    private TableView<BitacoraAsistencia> tblBitacoraAsistencia;
    @FXML
    private TableColumn<BitacoraAsistencia, String> colCedula;
    @FXML
    private TableColumn<BitacoraAsistencia, String> colNombre;
    @FXML
    private TableColumn<BitacoraAsistencia, String> colFecha;
    @FXML
    private TableColumn<BitacoraAsistencia, String> colPresente;
    @FXML
    private TableColumn<BitacoraAsistencia, String> colJustifica;

    /**
     * Initializes the controller class.
     */
    ObservableList<Empleado> ObservableEmpleado = FXCollections.observableArrayList();
    ObservableList<BitacoraAsistencia> ObservableAsistencia = FXCollections.observableArrayList();
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        configurar();
        cargarBitacorasAsistencias();
    }

    public void configurar() {
        colCedula.setCellValueFactory(new PropertyValueFactory<>("empleado"));
        colNombre.setCellValueFactory(cellData -> {
            var empleado = new EmpleadoDAO().obtenerEmpleadoPorCedula(cellData.getValue().getEmpleado());
            if (empleado == null) {
                return new SimpleStringProperty("No disponible");
            }
            return new SimpleStringProperty(empleado.getNombreCompleto());
        });
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colPresente.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().isEstaPresente() ? "Presente" : "Ausente"));
        colJustifica.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().isJustifica()? "Justificada" : "Sin justificar"));
    }


    
    public void cargarBitacorasAsistencias() {
        var ObservableAsistencia
                = FXCollections.observableArrayList(new BitacoraAsistenciaDAO().obtenerListaBitacoraAsitencia());
        tblBitacoraAsistencia.setItems(ObservableAsistencia);
    }
    
//    private void filtrarAsistencias() {
//        if (txtFiltrarEmpleado.getText() != null && !txtFiltrarEmpleado.getText().trim().equals("")) {
//            Predicate<BitacoraAsistencia> pVacacion = x
//                    -> x.getEmpleado().toLowerCase().contains(txtFiltrarEmpleado.getText().toLowerCase());
//            Predicate<Empleado> pEmpleado = x
//                    -> x.getCedula().toLowerCase().contains(txtFiltrarEmpleado.getText().toLowerCase())
//                    || x.getNombre().toLowerCase().contains(txtFiltrarEmpleado.getText().toLowerCase())
//                    || x.getApellidos().toLowerCase().contains(txtFiltrarEmpleado.getText().toLowerCase())
//                    || x.getNombreCompleto().toLowerCase().contains(txtFiltrarEmpleado.getText().toLowerCase());
//            var listaTemporal = ObservableAsistencia.filtered((x) -> pEmpleado.test(Get(x.getEmpleado())) || pVacacion.test(x));
//            tblBitacoraAsistencia.setItems(listaTemporal);
//        } else {
//            tblBitacoraAsistencia.setItems(ObservableAsistencia);
//        }
//    }

    private Empleado Get(String cedula) {
        return ObservableEmpleado.filtered(x -> x.getCedula().equals(cedula)).get(0);
    }
    
    @FXML
    private void OnAgregar(ActionEvent event) {
        OpenWindowsHandler.AbrirVentanaAgregarBitaAsistencia("/views/AgregarAsistencia");
    }

    @FXML
    private void OnActualizar(ActionEvent event) {
         OpenWindowsHandler.AbrirVentanaActualizarBitaAsistencia("/views/ActualizarAsistencia");
    }

    @FXML
    private void PresionarEnter(KeyEvent event) {
//        filtrarAsistencias();
    }

    @FXML
    private void OnRefrescar(ActionEvent event) {
     cargarBitacorasAsistencias();
    }

}
