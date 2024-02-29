/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import Alertas.MensajePersonalizado;
import DAO.BitacoraAsistenciaDAO;
import DAO.EmpleadoDAO;
import Models.BitacoraAsistencia;
import Models.Empleado;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;

/**
 *
 * @author alber
 * @author kim03
 */
public class ActualizarAsistenciaController implements Initializable {

    @FXML
    private ComboBox<Empleado> cbxFiltrarEmpleadoActualizar;
    @FXML
    private TableView<BitacoraAsistencia> tblAsistencia;
    @FXML
    private TableColumn<BitacoraAsistencia, String> colFecha;
    @FXML
    private TableColumn<BitacoraAsistencia, String> colPresente;
    @FXML
    private TableColumn<BitacoraAsistencia, String> colJudtifica;
    @FXML
    private DatePicker dpFecha;
    @FXML
    private CheckBox cbPresente;
    @FXML
    private CheckBox cbJudtifica;

    /**
     * Initializes the controller class.
     */
    ObservableList<Empleado> ObservableEmpleado = FXCollections.observableArrayList();
    EmpleadoDAO daoEmpleado = new EmpleadoDAO();
    BitacoraAsistencia asistencia = new BitacoraAsistencia();
    BitacoraAsistenciaDAO daoAsistencia = new BitacoraAsistenciaDAO();


  @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurar();
    }

    public void configurar() {
         
        
        
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colPresente.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().isEstaPresente() ? "Presente" : "Ausente"));
        colJudtifica.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().isJustifica() ? "Justificada" : "Sin justificar"));

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
            boolean exito = daoAsistencia.actualizarAsistencia(
                    new BitacoraAsistencia(
                            asistencia.getId(),
                            java.sql.Date.valueOf(dpFecha.getValue()),
                            cbPresente.isSelected(),
                            cbJudtifica.isSelected(),
                            cbxFiltrarEmpleadoActualizar.getValue().getCedula()));

            if (exito) {
                MensajePersonalizado.Ver("ÉXITO AL ACTUALIZAR", "Incapacidad actualizada correctamente", Alert.AlertType.CONFIRMATION);
                limpiarCamposActualizar();
                FiltrarAsistenciaPorCedulaEmpleado();

            } else {
                MensajePersonalizado.Ver("ERROR", "Error al actualizar la incapacidad", Alert.AlertType.ERROR);
            }
        } else {
            MensajePersonalizado.Ver("INFORMACIÓN INCOMPLETA", "Los espacios son requeridos, verifique que la información esté completa", Alert.AlertType.WARNING);

        }
    }

    public boolean VerificarEspaciosAtualizar() {
        if (cbxFiltrarEmpleadoActualizar.getValue() != null
                && !cbxFiltrarEmpleadoActualizar.getValue().getCedula().trim().equals("")
                && dpFecha.getValue() != null
                && !dpFecha.getValue().format(DateTimeFormatter.ISO_DATE).trim().equals("")) {
            return true;
        } else {
            return false;
        }
    }

    private void limpiarCamposActualizar() {
        dpFecha.setValue(LocalDate.now());
        cbPresente.setSelected(asistencia.isEstaPresente());
        cbJudtifica.setSelected(asistencia.isJustifica());
    }

    private void cargarCamposActualizar() {
        dpFecha.setValue(asistencia.getFecha().toLocalDate());
        cbxFiltrarEmpleadoActualizar.setValue(Get(asistencia.getEmpleado()));
        cbPresente.setSelected(asistencia.isEstaPresente());
         cbJudtifica.setSelected(asistencia.isJustifica());
    }
    
    private void cargarAsistenciaPorEmpleado() {
        asistencia = tblAsistencia.getSelectionModel().getSelectedItem();
        if (asistencia != null && asistencia.getId() != 0) {
            cargarCamposActualizar();
        } else {
            MensajePersonalizado.Ver("NO SELECCIONADO", "Por favor seleccione una asistencia", Alert.AlertType.WARNING);
        }
    }
    
    private Empleado Get(String cedula) {
        return ObservableEmpleado.filtered(x -> x.getCedula().equals(cedula)).get(0);
    }
    
    private void FiltrarAsistenciaPorCedulaEmpleado() {
        try {
            var empleado = cbxFiltrarEmpleadoActualizar.getValue();
            if (empleado != null && !empleado.getCedula().isEmpty()) {
                var lista = FXCollections.observableArrayList(daoAsistencia.obtenerListaAsietenciaPorCedulaEmpleado(empleado.getCedula()));
                tblAsistencia.setItems(lista);
            }
        } catch (Exception ex) {
            MensajePersonalizado.Ver("Error", "Error al buscar las asistencias del empleado, más información: " + ex.getMessage(), Alert.AlertType.ERROR);
        }
    }
    @FXML
    private void FiltrarEmpleado(ActionEvent event) {
        FiltrarAsistenciaPorCedulaEmpleado();
    }

    @FXML
    private void onCargar(ActionEvent event) {
        cargarAsistenciaPorEmpleado();
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
