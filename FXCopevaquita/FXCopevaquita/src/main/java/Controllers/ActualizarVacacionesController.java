/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import Alertas.MensajePersonalizado;
import DAO.EmpleadoDAO;
import DAO.VacacionesDAO;
import Models.Empleado;
import Models.Vacaciones;
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
 * @author alber
 * @author kim03
 */
public class ActualizarVacacionesController implements Initializable {

    @FXML
    private ComboBox<Empleado> cbxFiltrarEmpleadoActualizar;
    @FXML
    private TableView<Vacaciones> tblVacacionesActualizar;
    @FXML
    private TableColumn<Vacaciones, String> colFecha;
    @FXML
    private TableColumn<Vacaciones, String> colMonto;
    @FXML
    private TableColumn<Vacaciones, String> colEstadoAct;
    @FXML
    private CheckBox cbEstadoAct;
    @FXML
    private DatePicker dpFecha;
    @FXML
    private TextField txtMonto;

    /**
     * Initializes the controller class.
     */
    ObservableList<Empleado> ObservableEmpleado = FXCollections.observableArrayList();
    EmpleadoDAO daoEmpleado = new EmpleadoDAO();
    Vacaciones vacacion = new Vacaciones();
    VacacionesDAO daoVacaciones = new VacacionesDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       configurar();
    }

    public void configurar() {
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colMonto.setCellValueFactory(new PropertyValueFactory<>("monto"));
        colEstadoAct.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().isStatus() ? "Pendiente" : "Cancelado"));

        ObservableEmpleado = FXCollections.observableArrayList(daoEmpleado.obtenerListaEmpleados());
        cbxFiltrarEmpleadoActualizar.setItems(ObservableEmpleado);

        cbxFiltrarEmpleadoActualizar.setConverter(new StringConverter<Empleado>() {
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
            boolean exito = daoVacaciones.actualizarVacacion(
                    new Vacaciones(
                            vacacion.getId(),
                            cbxFiltrarEmpleadoActualizar.getValue().getCedula(),
                            Double.parseDouble(txtMonto.getText()),
                            java.sql.Date.valueOf(dpFecha.getValue()),
                            cbEstadoAct.isSelected()));

            if (exito) {
                MensajePersonalizado.Ver("EXITO AL ACTUALIZAR", "Contrato actualizado correctamente", Alert.AlertType.CONFIRMATION);
                limpiarCamposActualizar();
            } else {
                MensajePersonalizado.Ver("ERROR", "Error al actualizar la vacacion", Alert.AlertType.ERROR);
            }
        } else {
            MensajePersonalizado.Ver("INFORMACIÓN INCOMPLETA", "Los campos son requeridos, verifique que la información este completa", Alert.AlertType.WARNING);

        }
    }

    public boolean VerificarEspaciosAtualizar() {
        if (cbxFiltrarEmpleadoActualizar.getValue() != null
                && !cbxFiltrarEmpleadoActualizar.getValue().getCedula().trim().equals("")
                && dpFecha.getValue() != null
                && !dpFecha.getValue().format(DateTimeFormatter.ISO_DATE).trim().equals("")
                && txtMonto.getText() != null && !txtMonto.getText().trim().equals("")
                && !cbEstadoAct.getText().trim().equals("")) {
            return true;
        } else {
            return false;
        }
    }

    private void limpiarCamposActualizar() {
        dpFecha.setValue(LocalDate.now());
        txtMonto.setText("");
        cbEstadoAct.setSelected(vacacion.isStatus());
        dpFecha.setValue(null);
    }

    
    private void FiltrarVacacionesPorCedulaEmpleado() {
        try {
            var empleado = cbxFiltrarEmpleadoActualizar.getValue();
            
            if (empleado != null && !empleado.getCedula().isEmpty()) {
                var lista = FXCollections.observableArrayList(daoVacaciones.obtenerListaVacacionesPorCedulaEmpleado(empleado.getCedula()));
                System.out.println("lista: " + lista.size());
                tblVacacionesActualizar.setItems(lista);
            }
        } catch (Exception ex) {
            MensajePersonalizado.Ver("Error", "Error al buscar las vacaciones del empleado, más información: " + ex.getMessage(), Alert.AlertType.ERROR);
        }
    }
    
    private Empleado Get(String cedula) {
        return ObservableEmpleado.filtered(x -> x.getCedula().equals(cedula)).get(0);
    }
    
    private void cargarCamposActualizar() {
        dpFecha.setValue(vacacion.getFecha().toLocalDate());
        cbxFiltrarEmpleadoActualizar.setValue(Get(vacacion.getEmpleado()));
        txtMonto.setText(vacacion.getMonto() + "");
        cbEstadoAct.setSelected(vacacion.isStatus());
    }
    
    private void cargarVacaionesPorEmpleado() {
        vacacion = tblVacacionesActualizar.getSelectionModel().getSelectedItem();
        
        if (vacacion != null && vacacion.getId() != 0) {
            cargarCamposActualizar();
        } else {
            MensajePersonalizado.Ver("NO SELECCIONADO", "Por favor seleccione una incapacidad", Alert.AlertType.WARNING);
        }
    }
    
    @FXML
    private void FiltrarEmpleado(ActionEvent event) {
        FiltrarVacacionesPorCedulaEmpleado();
    }

    @FXML
    private void onCargar(ActionEvent event) {
        cargarVacaionesPorEmpleado();
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
