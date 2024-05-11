/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import Alertas.MensajePersonalizado;
import DAO.EmpleadoDAO;
import DAO.PlanillaSociosDAO;
import Models.Empleado;
import Models.PlanillaSocios;
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
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
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
public class ActualizarSalarioFijoController implements Initializable {

    @FXML
    private ComboBox<Empleado> cbxFiltrarEmpleado;
    @FXML
    private TableView<PlanillaSocios> tblSalarios;
    @FXML
    private TableColumn<PlanillaSocios, String> colMonto;
    @FXML
    private TableColumn<PlanillaSocios, String> colEstado;
    @FXML
    private CheckBox cbEstado;
    @FXML
    private TextField txtMonto;

    /**
     * Initializes the controller class.
     */
    ObservableList<Empleado> ObservableEmpleado = FXCollections.observableArrayList();
    ObservableList<PlanillaSocios> ObservablePlanillaSocios = FXCollections.observableArrayList();
    EmpleadoDAO daoEmpleado = new EmpleadoDAO();
    PlanillaSocios planilla = new PlanillaSocios();
    PlanillaSociosDAO daoPlanilla = new PlanillaSociosDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurar();
    }

    public void configurar() {
        colMonto.setCellValueFactory(new PropertyValueFactory<>("monto"));
        colEstado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().isStatus() ? "Activo" : "Inactivo"));

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
            boolean exito = daoPlanilla.actualizarPlanillaSocios(
                    new PlanillaSocios(
                            planilla.getId(),
                            cbxFiltrarEmpleado.getValue().getCedula(),
                            Double.parseDouble(txtMonto.getText()),
                            cbEstado.isSelected()));

            if (exito) {
                MensajePersonalizado.Ver("EXITO AL ACTUALIZAR", "Salario actualizado correctamente", Alert.AlertType.CONFIRMATION);
                limpiarCamposActualizar();
                cargarSalarios(cbxFiltrarEmpleado.getValue().getCedula());

            } else {
                MensajePersonalizado.Ver("ERROR", "Error al actualizar el salario", Alert.AlertType.ERROR);
            }
        } else {
            MensajePersonalizado.Ver("INFORMACIÓN INCOMPLETA", "Los campos son requeridos, verifique que la información este completa", Alert.AlertType.WARNING);

        }
    }

    public boolean VerificarEspaciosAtualizar() {
        if (cbxFiltrarEmpleado.getValue() != null
                && !cbxFiltrarEmpleado.getValue().getCedula().trim().equals("")
                && txtMonto.getText() != null && !txtMonto.getText().trim().equals("")) {
            return true;
        } else {
            return false;
        }
    }

    private void limpiarCamposActualizar() {

        txtMonto.setText("");
        cbEstado.setSelected(planilla.isStatus());

    }

    private Empleado Get(String cedula) {
        return ObservableEmpleado.filtered(x -> x.getCedula().equals(cedula)).get(0);
    }

    private void cargarCamposActualizar() {
        cbxFiltrarEmpleado.setValue(Get(planilla.getEmpleado()));
        txtMonto.setText(planilla.getMonto() + "");
        cbEstado.setSelected(planilla.isStatus());
    }

    private void cargarPlanillasPorEmpleado() {
        planilla = tblSalarios.getSelectionModel().getSelectedItem();
        if (planilla != null && planilla.getId() != 0) {
            cargarCamposActualizar();
        } else {
            MensajePersonalizado.Ver("NO SELECCIONADO", "Por favor seleccione una incapacidad", Alert.AlertType.WARNING);
        }
    }

    private void FiltrarPlanillaPorCedulaEmpleado() {
        try {
            var empleado = cbxFiltrarEmpleado.getValue();
            if (empleado != null && !empleado.getCedula().isEmpty()) {
                var lista = FXCollections.observableArrayList(daoPlanilla.obtenerListaPlanillaPorCedulaEmpleado(empleado.getCedula()));
                tblSalarios.setItems(lista);
            }
        } catch (Exception ex) {
            MensajePersonalizado.Ver("Error", "Error al buscar las incapacidades del empleado, más información: " + ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void cargarSalarios(String cedula) {
        try {
            ObservablePlanillaSocios 
                    = FXCollections.observableArrayList(daoPlanilla.obtenerListaPlanillaPorCedulaEmpleado(cedula));
                tblSalarios.setItems(ObservablePlanillaSocios);
            
        } catch (Exception ex) {
            MensajePersonalizado.Ver("Error", "Error al buscar las incapacidades del empleado, más información: " + ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void FiltrarEmpleado(ActionEvent event) {
        FiltrarPlanillaPorCedulaEmpleado();
    }

    @FXML
    private void onCargar(ActionEvent event) {
        cargarPlanillasPorEmpleado();
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
