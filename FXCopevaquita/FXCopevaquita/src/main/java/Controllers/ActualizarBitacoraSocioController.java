/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import Alertas.MensajePersonalizado;
import DAO.BitacoraSocioDAO;
import DAO.EmpleadoDAO;
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
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;

/**
 *
 * @author alber
 * @author kim03
 */
public class ActualizarBitacoraSocioController implements Initializable {

    @FXML
    private ComboBox<Empleado> cbxFiltrarEmpleadoActualizar;
    @FXML
    private TableView<BitacoraSocio> tblReporteSociooActualizar;
    @FXML
    private TableColumn<BitacoraSocio, String> colCantidadHorasActualizar;
    @FXML
    private TableColumn<BitacoraSocio, String> colDescripcionActualizar;
    @FXML
    private TableColumn<BitacoraSocio, String> colEstadoActualizar;
    @FXML
    private TextField txtCantidadHorasAct;
    @FXML
    private TextField txtDescripcionAct;
    @FXML
    private CheckBox cbEstadoAct;

    /**
     * Initializes the controller class.
     */
    ObservableList<Empleado> ObservableEmpleado = FXCollections.observableArrayList();
    EmpleadoDAO daoEmpleado = new EmpleadoDAO();
    BitacoraSocio bitacoraSocio = new BitacoraSocio();
    BitacoraSocioDAO daoBitacora = new BitacoraSocioDAO();
    ObservableList<BitacoraSocio> ObservableBitacoraSocio = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurar();
        cargar();
    }

    public void cargar() {
        ObservableEmpleado = FXCollections.observableArrayList(daoEmpleado.obtenerListaEmpleadosInternos());
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

    public void configurar() {
        colCantidadHorasActualizar.setCellValueFactory(new PropertyValueFactory<>("horas"));
        colDescripcionActualizar.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        colEstadoActualizar.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().isStatus() ? "Activo" : "Inactivo"));
    }

    private void actualizar() {
        if (VerificarEspaciosActualizar()) {
            boolean exito = daoBitacora.actualizarBitacoraSocio(
                    new BitacoraSocio(
                            bitacoraSocio.getId(),
                            cbxFiltrarEmpleadoActualizar.getValue().getCedula(),
                            Double.parseDouble(txtCantidadHorasAct.getText()),
                            cbEstadoAct.isSelected(),
                            txtDescripcionAct.getText()
                    ));

            if (exito) {
                MensajePersonalizado.Ver("EXITO AL ACTUALIZAR", "Bitacora de socio actualizado correctamente", Alert.AlertType.CONFIRMATION);
                limpiarCamposActualizar();
                cargarBItacoras(cbxFiltrarEmpleadoActualizar.getValue().getCedula());
            } else {
                MensajePersonalizado.Ver("ERROR", "Error al actualizar la bitacora socio", Alert.AlertType.ERROR);
            }
        } else {
            MensajePersonalizado.Ver("Los campos son requeridos, verifique que la información este completa", "INFORMACIÓN INCOMPLETA", Alert.AlertType.WARNING);

        }
    }

    public boolean VerificarEspaciosActualizar() {
        if (bitacoraSocio != null && bitacoraSocio.getId() != 0
                && txtCantidadHorasAct.getText() != null
                && !txtCantidadHorasAct.getText().trim().equals("")
                && txtDescripcionAct.getText() != null
                && !txtDescripcionAct.getText().trim().equals("")) {
            return true;
        } else {
            return false;
        }
    }

    private void limpiarCamposActualizar() {
        txtCantidadHorasAct.setText("");
        txtDescripcionAct.setText("");
        cbEstadoAct.setSelected(false);
    }

    private void cargarCamposActualizar() {
        txtCantidadHorasAct.setText(bitacoraSocio.getHoras() + "");
        txtDescripcionAct.setText(bitacoraSocio.getDescripcion() + "");
        cbxFiltrarEmpleadoActualizar.setValue(Get(bitacoraSocio.getCedula_empleado()));
        cbEstadoAct.setSelected(bitacoraSocio.isStatus());
    }

    private Empleado Get(String cedula) {
        return ObservableEmpleado.filtered(x -> x.getCedula().equals(cedula)).get(0);
    }

    private void cargarReporteSocioPorEmpleado() {
        bitacoraSocio = tblReporteSociooActualizar.getSelectionModel().getSelectedItem();
        if (bitacoraSocio != null && bitacoraSocio.getId() != 0) {
            cargarCamposActualizar();
        } else {
            MensajePersonalizado.Ver("NO SELECCIONADO", "Por favor seleccione un reporte diario", Alert.AlertType.WARNING);
        }
    }

    private void FiltrarBitacoraSocioPorCedulaEmpleado() {
        try {
            var empleado = cbxFiltrarEmpleadoActualizar.getValue();
            if (empleado != null && !empleado.getCedula().isEmpty()) {
                var lista = FXCollections.observableArrayList(daoBitacora.obtenerListaBitacoraSocioPorCedula(empleado.getCedula()));
                tblReporteSociooActualizar.setItems(lista);
            }
        } catch (Exception ex) {
            MensajePersonalizado.Ver("Error", "Error al buscar los reportes del socio, más información: " + ex.getMessage(), Alert.AlertType.ERROR);
        }
    }
    
    private void cargarBItacoras(String Cedula) {
        try {
            ObservableBitacoraSocio 
                    = FXCollections.observableArrayList(daoBitacora.obtenerListaBitacoraSocioPorCedula(Cedula));

                tblReporteSociooActualizar.setItems(ObservableBitacoraSocio);
            
        } catch (Exception ex) {
            MensajePersonalizado.Ver("Error", "Error al buscar los reportes del socio, más información: " + ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void FiltrarEmpleado(ActionEvent event) {
        FiltrarBitacoraSocioPorCedulaEmpleado();
    }

    @FXML
    private void onCargar(ActionEvent event) {
        cargarReporteSocioPorEmpleado();
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
