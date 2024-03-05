/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import Alertas.MensajePersonalizado;
import DAO.DeduccionesDAO;
import DAO.EmpleadoDAO;
import DAO.TipoDeduccionDAO;
import Models.Deduccion;
import Models.Empleado;
import Models.TipoDeduccion;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
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
public class ActualizarDeduccionesController implements Initializable {
    
    @FXML
    private ComboBox<Empleado> cbxFiltrarEmpleadoAct;
    @FXML
    private TableView<Deduccion> tblDeduccionesEmpleadosAct;
    @FXML
    private TableColumn<Deduccion, String> colTipoDeduccionAct;
    @FXML
    private TableColumn<Deduccion, String> colEstadoAct;
    @FXML
    private ComboBox<TipoDeduccion> cbxTipoDeduccionAct;
    @FXML
    private TextField txtMonto;
    @FXML
    private TextField txtCuota;
    @FXML
    private CheckBox cbEstado;

    /**
     * Initializes the controller class.
     */
    ObservableList<Empleado> ObservableEmpleado = FXCollections.observableArrayList();
    ObservableList<TipoDeduccion> ObservableTipoDeduccion = FXCollections.observableArrayList();
    EmpleadoDAO daoEmpleado = new EmpleadoDAO();
    TipoDeduccionDAO daoTipoDeduccion = new TipoDeduccionDAO();
    Deduccion deduccion = new Deduccion();
    DeduccionesDAO daoDeducciones = new DeduccionesDAO();
    @FXML
    private TableColumn<Deduccion, String> colMontoAct;
    @FXML
    private TableColumn<Deduccion, String> colCuota;
    @FXML
    private DatePicker dpFecha;
    @FXML
    private TableColumn<Deduccion, String> colPendiente;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurar();
    }
    
    public void configurar() {
        ObservableEmpleado = FXCollections.observableArrayList(daoEmpleado.obtenerListaEmpleados());
        cbxFiltrarEmpleadoAct.setItems(ObservableEmpleado);
        
        colEstadoAct.setCellValueFactory((cellData) -> new SimpleStringProperty(cellData.getValue().isStatus() ? "Activo" : "Inactivo"));
        
        colMontoAct.setCellValueFactory(new PropertyValueFactory<>("monto"));
        
        colTipoDeduccionAct.setCellValueFactory((celldata) -> {
            var model = celldata.getValue();
            var tipoDeduccion = new TipoDeduccionDAO().obtenerPorId(model.getTipo());
            
            return new SimpleStringProperty(tipoDeduccion.getNombre());
        });
        
        colCuota.setCellValueFactory(new PropertyValueFactory<>("cuota"));
        
        colPendiente.setCellValueFactory(new PropertyValueFactory<>("pendiente"));
        
        cbxFiltrarEmpleadoAct.setConverter(new StringConverter<Empleado>() {
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
        
        ObservableTipoDeduccion = FXCollections.observableArrayList(daoTipoDeduccion.obtenerListaTipoDeduccion());
        cbxTipoDeduccionAct.setItems(ObservableTipoDeduccion);
        cbxTipoDeduccionAct.setConverter(new StringConverter<TipoDeduccion>() {
            @Override
            public String toString(TipoDeduccion t) {
                if (t == null) {
                    return "";
                }
                return t.getNombre();
            }
            
            @Override
            public TipoDeduccion fromString(String t) {
                if (t == null || t.isEmpty()) {
                    return null;
                }
                Predicate<String> find = (x) -> x != null && x.equals(t);
                Optional<TipoDeduccion> firstMatch = ObservableTipoDeduccion.filtered(x -> find.test(x.getNombre())).stream().findFirst();
                return firstMatch.orElse(null);
            }
        });
    }
    
    private void actualizar() {
        if (VerificarEspaciosActualizar()) {
            boolean exito = daoDeducciones.actualizarDeduccion(
                    new Deduccion(
                            deduccion.getId(),
                            cbxTipoDeduccionAct.getValue().getId(),
                            Double.parseDouble(txtMonto.getText()),
                            Double.parseDouble(txtCuota.getText()),
                            Double.parseDouble(txtMonto.getText()),
                            cbxFiltrarEmpleadoAct.getValue().getCedula(),
                            cbEstado.isSelected(), 
                            Date.valueOf(dpFecha.getValue())
                    ));
            
            if (exito) {
                MensajePersonalizado.Ver("EXITO AL ACTUALIZAR", "Deducción actualizada correctamente", Alert.AlertType.CONFIRMATION);
                limpiarCamposActualizar();
            } else {
                MensajePersonalizado.Ver("ERROR", "Error al actualizar la deducción", Alert.AlertType.ERROR);
            }
        } else {
            MensajePersonalizado.Ver("Los campos son requeridos, verifique que la información este completa", "INFORMACIÓN INCOMPLETA", Alert.AlertType.WARNING);
            
        }
    }
    
    public boolean VerificarEspaciosActualizar() {
        if (deduccion != null && deduccion.getId() != 0
                && txtCuota.getText() != null && !txtCuota.getText().trim().equals("")
                && txtMonto.getText() != null && !txtMonto.getText().trim().equals("")
                && cbxTipoDeduccionAct.getValue() != null
                && !cbxTipoDeduccionAct.getValue().getNombre().trim().equals("")
                && dpFecha.getValue() != null) {
            return true;
        } else {
            return false;
        }
    }
    
    private void limpiarCamposActualizar() {
        txtCuota.setText("");
        txtMonto.setText("");
        cbxTipoDeduccionAct.setValue(null);
        cbEstado.setSelected(false);
        deduccion = new Deduccion();
        dpFecha.setValue(null);
    }
    
    private Empleado Get(String cedula) {
        return ObservableEmpleado.filtered(x -> x.getCedula().equals(cedula)).get(0);
    }
    
    private void cargarCamposActualizar() {
        cbxFiltrarEmpleadoAct.setValue(Get(deduccion.getEmpleado()));
        TipoDeduccion tipoDeduccion = daoTipoDeduccion.obtenerPorId(deduccion.getTipo());
        cbxTipoDeduccionAct.setValue(tipoDeduccion);
        txtMonto.setText(deduccion.getMonto() + "");
        txtCuota.setText(deduccion.getCuota() + "");
        cbEstado.setSelected(deduccion.isStatus());
        dpFecha.setValue(deduccion.getFecha_registro().toLocalDate());
    }
    
    private void cargarDeduccionesPorEmpleado() {
        deduccion = tblDeduccionesEmpleadosAct.getSelectionModel().getSelectedItem();
        if (deduccion != null && deduccion.getId() != 0) {
            cargarCamposActualizar();
        } else {
            MensajePersonalizado.Ver("NO SELECCIONADO", "Por favor seleccione una deduccion", Alert.AlertType.WARNING);
        }
    }
    
    private void FiltrarDeduccionesPorCedulaEmpleado() {
        try {
            var empleado = cbxFiltrarEmpleadoAct.getValue();
            if (empleado != null && !empleado.getCedula().isEmpty()) {
                var lista = FXCollections.observableArrayList(daoDeducciones.obtenerListaDeduccionesPorCedulaEmpleado(empleado.getCedula()));
                System.out.println("Si hay registros");
                tblDeduccionesEmpleadosAct.setItems(lista);
            }
        } catch (Exception ex) {
            MensajePersonalizado.Ver("Error", "Error al buscar las deducciones del empleado, más información: " + ex.getMessage(), Alert.AlertType.ERROR);
        }
    }
    
    @FXML
    private void OnFiltrarEmpleado(ActionEvent event) {
        FiltrarDeduccionesPorCedulaEmpleado();
    }
    
    @FXML
    private void onCargar(ActionEvent event) {
        cargarDeduccionesPorEmpleado();
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
