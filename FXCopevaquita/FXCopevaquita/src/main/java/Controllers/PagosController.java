/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import Helpers.OpenWindowsHandler;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import DAO.*;
import Models.*;
import java.sql.Date;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author User
 */
public class PagosController implements Initializable {

    @FXML
    private ComboBox<Empleado> cbxEmpleado;
    @FXML
    private TableView<Pagos> tblPagos;
    @FXML
    private TableColumn<Pagos, String> colEmpleado;
    @FXML
    private TableColumn<Pagos, String> colFechaInicio;
    @FXML
    private TableColumn<Pagos, String> colFechaFin;
    @FXML
    private TableColumn<Pagos, String> colIncapacidades;
    @FXML
    private TableColumn<Pagos, String> colVacaciones;
    @FXML
    private TableColumn<Pagos, String> colDeducciones;
    @FXML
    private TableColumn<Pagos, String> colContratos;
    @FXML
    private TableColumn<Pagos, String> colSalarioBase;

    private ObservableList<Pagos> observablePagos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        configure();
    }

    public void cargarPagos() {
        observablePagos = FXCollections.observableArrayList(new PagosDAO().obtenerListaPagos());
        tblPagos.setItems(observablePagos);
    }

    public void configure() {
        cargarPagos();
        
        colFechaInicio.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFechaFinal().toString()));
        colFechaFin.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFecha().toString()));

        // Cargar los datos de la base de datos ...
        colEmpleado.setCellValueFactory((cellData) -> {
            var cedula = cellData.getValue().getEmpleado();
            var empleado = new EmpleadoDAO().obtenerEmpleadoPorCedula(cedula);
            return new SimpleStringProperty(empleado.getNombreCompleto());
        });

        colSalarioBase.setCellValueFactory((data) -> {
            var pagoId = data.getValue().getId();
            var pago = new PagoBitacoraDAO().obtenerPagoBitacoraPorPago(pagoId);
            return new SimpleStringProperty(pago.getTotalBitacora() + "");
        });

        colContratos.setCellValueFactory((data) -> {
            var pagoId = data.getValue().getId();
            var pago = new PagoContratoDAO().obtenerPagoContratoPorPago(pagoId);
            return new SimpleStringProperty(pago.getTotalContrato() + "");
        });

        colDeducciones.setCellValueFactory((data) -> {
            var pagoId = data.getValue().getId();
            var pago = new PagoDeduccionDAO().obtenerPagoDeduccionPorPago(pagoId);
            return new SimpleStringProperty(pago.getTotalDeduccion() + "");
        });

        colIncapacidades.setCellValueFactory((data) -> {
            var pagoId = data.getValue().getId();
            var pago = new PagoIncapacidadDAO().obtenerPagoIncapacidadPorPago(pagoId);
            return new SimpleStringProperty(pago.getTotalIncapacidad() + "");
        });

        colVacaciones.setCellValueFactory((data) -> {
            var pagoId = data.getValue().getId();
            var pago = new PagoVacacionDAO().obtenerPagoVacacionPorPago(pagoId);
            return new SimpleStringProperty(pago.getTotalVacacion() + "");
        });
    }

    @FXML
    private void cbxEmpleado(ActionEvent event) {
    }

    @FXML
    private void OnGenerarPago(ActionEvent event) {
        OpenWindowsHandler.AbrirVentanaGenerarPagos("/views/GenerarPagos");
    }

    @FXML
    private void OnRefrescar(ActionEvent event) {
        cargarPagos();
    }

}
