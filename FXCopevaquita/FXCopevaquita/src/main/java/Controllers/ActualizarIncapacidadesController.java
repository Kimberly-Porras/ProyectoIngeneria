/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import Alertas.MensajePersonalizado;
import DAO.EmpleadoDAO;
import DAO.IncapacidadDAO;
import Models.Empleado;
import Models.Incapacidad;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author alber
 * @author kim03
 */
public class ActualizarIncapacidadesController implements Initializable {

    @FXML
    private TableView<Incapacidad> tblIncapacidadesActualizar;
    @FXML
    private ComboBox<Empleado> cbxFiltrarEmpleado;
    @FXML
    private TableColumn<Incapacidad, String> colFecha;
    @FXML
    private TableColumn<Incapacidad, String> colMotivo;
    @FXML
    private TableColumn<Incapacidad, String> colMonto;
    @FXML
    private TableColumn<Incapacidad, String> colEstado;
    @FXML
    private DatePicker dpFecha;
    @FXML
    private TextField txtMotivo;
    @FXML
    private TextField txtMonto;
    @FXML
    private CheckBox cbEstado;

    /**
     * Initializes the controller class.
     */
    IncapacidadDAO daoIncapacidad = new IncapacidadDAO();
    EmpleadoDAO daoEmpleado = new EmpleadoDAO();
    Incapacidad incapacidad = new Incapacidad();
    ObservableList<Empleado> ObservableEmpleado = FXCollections.observableArrayList();
    ObservableList<Incapacidad> ObservableIncapacidad = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurar();
    }

    public void configurar() {
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colMotivo.setCellValueFactory(new PropertyValueFactory<>("motivo"));
        colMonto.setCellValueFactory(new PropertyValueFactory<>("monto"));
        colEstado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().isStatus() ? "Pendiente" : "Cancelado"));

        ObservableEmpleado = FXCollections.observableArrayList(daoEmpleado.obtenerListaEmpleados());
        cbxFiltrarEmpleado.setItems(ObservableEmpleado);

        cbxFiltrarEmpleado.setConverter(new StringConverter<Empleado>() {
            @Override
            public String toString(Empleado t) {
                if (t == null) {
                    return "";
                }
                return t.getNombreCompleto();
            }

            @Override
            public Empleado fromString(String t) {
                if (t == null || t.isEmpty()) {
                    return null;
                }
                Predicate<String> find = (x) -> x != null && x.equals(t);
                Optional<Empleado> firstMatch = ObservableEmpleado.filtered(x -> find.test(x.getNombreCompleto())).stream().findFirst();
                return firstMatch.orElse(null);
            }
        });
    }

    private void actualizar() {
        if (VerificarEspaciosAtualizar()) {
            boolean exito = daoIncapacidad.actualizarIncapacidad(
                    new Incapacidad(
                            incapacidad.getId(),
                            java.sql.Date.valueOf(dpFecha.getValue()),
                            Double.parseDouble(txtMonto.getText()),
                            txtMotivo.getText(),
                            cbxFiltrarEmpleado.getValue().getCedula(),
                            cbEstado.isSelected()));

            if (exito) {
                MensajePersonalizado.Ver("EXITO AL ACTUALIZAR", "Incapacidad actualizado correctamente", Alert.AlertType.CONFIRMATION);
                limpiarCamposActualizar();
                FiltrarIncapacidadPorCedulaEmpleado();

            } else {
                MensajePersonalizado.Ver("ERROR", "Error al actualizar la incapacidad", Alert.AlertType.ERROR);
            }
        } else {
            MensajePersonalizado.Ver("INFORMACIÓN INCOMPLETA", "Los campos son requeridos, verifique que la información este completa", Alert.AlertType.WARNING);

        }
    }

    public boolean VerificarEspaciosAtualizar() {
        if (cbxFiltrarEmpleado.getValue() != null
                && !cbxFiltrarEmpleado.getValue().getCedula().trim().equals("")
                && dpFecha.getValue() != null
                && !dpFecha.getValue().format(DateTimeFormatter.ISO_DATE).trim().equals("")
                && txtMonto.getText() != null && !txtMonto.getText().trim().equals("")
                && txtMotivo.getText() != null && !txtMotivo.getText().trim().equals("")
                && !cbEstado.getText().trim().equals("")) {
            return true;
        } else {
            return false;
        }
    }

    private Empleado Get(String cedula) {
        return ObservableEmpleado.filtered(x -> x.getCedula().equals(cedula)).get(0);
    }

    private void cargarCamposActualizar() {
        dpFecha.setValue(incapacidad.getFecha().toLocalDate());
        cbxFiltrarEmpleado.setValue(Get(incapacidad.getEmpleado()));
        txtMonto.setText(incapacidad.getMonto() + "");
        txtMotivo.setText(incapacidad.getMotivo() + "");
        cbEstado.setSelected(incapacidad.isStatus());
    }

    private void limpiarCamposActualizar() {
        dpFecha.setValue(LocalDate.now());
        txtMonto.setText("");
        txtMotivo.setText("");
        cbEstado.setSelected(incapacidad.isStatus());
    }

    private void FiltrarIncapacidadPorCedulaEmpleado() {
        try {
            var empleado = cbxFiltrarEmpleado.getValue();
            if (empleado != null && !empleado.getCedula().isEmpty()) {
                var lista = FXCollections.observableArrayList(daoIncapacidad.obtenerListaIncapacidadPorCedulaEmpleado(empleado.getCedula()));
                tblIncapacidadesActualizar.setItems(lista);
            }
        } catch (Exception ex) {
            MensajePersonalizado.Ver("Error", "Error al buscar las incapacidades del empleado, más información: " + ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void cargarIncapacidadesPorEmpleado() {
        incapacidad = tblIncapacidadesActualizar.getSelectionModel().getSelectedItem();
        if (incapacidad != null && incapacidad.getId() != 0) {
            cargarCamposActualizar();
        } else {
            MensajePersonalizado.Ver("NO SELECCIONADO", "Por favor seleccione una incapacidad", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void FiltrarEmpleado(ActionEvent event) {
        FiltrarIncapacidadPorCedulaEmpleado();
    }

    @FXML
    private void onCargar(ActionEvent event) {
        cargarIncapacidadesPorEmpleado();
    }

    @FXML
    private void onLimpiar(ActionEvent event) {
        limpiarCamposActualizar();
    }

    @FXML
    private void btnActualizar(ActionEvent event) {
        actualizar();
    }

}
