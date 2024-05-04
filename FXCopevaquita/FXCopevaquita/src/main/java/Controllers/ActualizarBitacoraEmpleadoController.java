/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import Alertas.MensajePersonalizado;
import DAO.ActividadDAO;
import DAO.AreaDAO;
import DAO.EmpleadoDAO;
import DAO.BitacoraEmpleadoDAO;
import Models.Empleado;
import Models.Actividad;
import Models.Area;
import Models.BitacoraEmpleado;
import java.net.URL;
import java.sql.Date;
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
 *
 * @author alber
 * @author kim03
 */
public class ActualizarBitacoraEmpleadoController implements Initializable {

    @FXML
    private ComboBox<Empleado> cbxFiltrarEmpleadoActualizar;
    @FXML
    private TableView<BitacoraEmpleado> tblReporteEmpleadoActualizar;
    @FXML
    private TableColumn<BitacoraEmpleado, String> colFechaRegistroActualizar;
    @FXML
    private TableColumn<BitacoraEmpleado, String> colActividadesActualizar;
    @FXML
    private TableColumn<BitacoraEmpleado, String> colAreasActualizar;
    @FXML
    private TableColumn<BitacoraEmpleado, String> colCantidadActualizar;
    @FXML
    private TableColumn<BitacoraEmpleado, String> colPrecioActualizar;
    @FXML
    private TableColumn<BitacoraEmpleado, String> colEstadoActualizar;
    @FXML
    private DatePicker dpFecharegistroAct;
    @FXML
    private ComboBox<Actividad> cbxActividadesAct;
    @FXML
    private ComboBox<Area> cbxAreasAct;
    @FXML
    private TextField txtCantidadAct;
    @FXML
    private TextField txtPrecioAct;
    @FXML
    private CheckBox cbEstadoAct;

    ActividadDAO actividadService = new ActividadDAO();
    AreaDAO areaService = new AreaDAO();

    EmpleadoDAO empleadoService = new EmpleadoDAO();
    BitacoraEmpleadoDAO bitacoraService = new BitacoraEmpleadoDAO();

    ObservableList<Empleado> ObservableEmpleado;
    ObservableList<Actividad> ObservableActividad;
    ObservableList<Area> ObservableArea;

    BitacoraEmpleado bitacora = new BitacoraEmpleado();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        configurar();
    }

    public void configurar() {
        colActividadesActualizar.setCellValueFactory(cellData -> {
            var tipo = actividadService.obtenerPorId(cellData.getValue().getActividad());
            if (tipo == null) {
                return new SimpleStringProperty("NO DISPONIBLE");
            }
            return new SimpleStringProperty(tipo.getNombre());
        });

        colAreasActualizar.setCellValueFactory(cellData -> {
            var tipo = areaService.obtenerPorId(cellData.getValue().getArea());
            if (tipo == null) {
                return new SimpleStringProperty("NO DISPONIBLE");
            }
            return new SimpleStringProperty(tipo.getNombre());
        });

        colCantidadActualizar.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        colPrecioActualizar.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colEstadoActualizar.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().isStatus() ? "Pendiente" : "Cancelado"));
        colFechaRegistroActualizar.setCellValueFactory(new PropertyValueFactory<>("fecha"));

        // LLENANDO EL CBX
        ObservableEmpleado = FXCollections.observableArrayList(empleadoService.obtenerListaEmpleadosExternos());
        cbxFiltrarEmpleadoActualizar.setItems(ObservableEmpleado);

        ObservableActividad = FXCollections.observableArrayList(actividadService.obtenerListaActividades());
        cbxActividadesAct.setItems(ObservableActividad);

        ObservableArea = FXCollections.observableArrayList(areaService.obtenerListaArea());
        cbxAreasAct.setItems(ObservableArea);

        // LLENANDO LOS CONVERTER
        cbxFiltrarEmpleadoActualizar.setConverter(new StringConverter<Empleado>() {
            @Override
            public String toString(Empleado t) {
                if (t == null) {
                    return "No hay datos"; // o cualquier otro valor predeterminado que desees
                }
                return t.getNombreCompleto();
            }

            @Override
            public Empleado fromString(String t) {
                if (t == null || t.isEmpty()) {
                    return null; // Manejar el caso de entrada vacía o nula
                }
                Predicate<String> find = (x) -> x != null && x.equals(t);
                Optional<Empleado> firstMatch = ObservableEmpleado.filtered(x -> find.test(x.getNombreCompleto())).stream().findFirst();
                return firstMatch.orElse(null); // Devuelve el primer empleado encontrado o null si no se encontró ninguna coincidencia.
            }
        });

        cbxActividadesAct.setConverter(new StringConverter<Actividad>() {
            @Override
            public String toString(Actividad t) {
                if (t == null) {
                    return "No hay datos"; // o cualquier otro valor predeterminado que desees
                }
                return t.getNombre();
            }

            @Override
            public Actividad fromString(String t) {
                if (t == null || t.isEmpty()) {
                    return null; // Manejar el caso de entrada vacía o nula
                }
                Predicate<String> find = (x) -> x != null && x.equals(t);
                Optional<Actividad> firstMatch = ObservableActividad.filtered(x -> find.test(x.getNombre())).stream().findFirst();
                return firstMatch.orElse(null); // Devuelve el primer empleado encontrado o null si no se encontró ninguna coincidencia.
            }
        });

        cbxAreasAct.setConverter(new StringConverter<Area>() {
            @Override
            public String toString(Area t) {
                if (t == null) {
                    return "No hay datos"; // o cualquier otro valor predeterminado que desees
                }
                return t.getNombre();
            }

            @Override
            public Area fromString(String t) {
                if (t == null || t.isEmpty()) {
                    return null; // Manejar el caso de entrada vacía o nula
                }
                Predicate<String> find = (x) -> x != null && x.equals(t);
                Optional<Area> firstMatch = ObservableArea.filtered(x -> find.test(x.getNombre())).stream().findFirst();
                return firstMatch.orElse(null); // Devuelve el primer empleado encontrado o null si no se encontró ninguna coincidencia.
            }
        });
    }

    @FXML
    private void FiltrarEmpleado(ActionEvent event) {
        try {
            var empleado = cbxFiltrarEmpleadoActualizar.getValue();
            if (empleado != null && !empleado.getCedula().isEmpty()) {
                var lista = FXCollections.observableArrayList(bitacoraService.obtenerListaBitacoraPorCedulaEmpleado(empleado.getCedula())
                );
                tblReporteEmpleadoActualizar.setItems(lista);
            }
        } catch (Exception ex) {
            MensajePersonalizado.Ver("Error", "Error al buscar los resportes del empleado, más información: " + ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void cargarCamposActualizar() {
        txtCantidadAct.setText(bitacora.getCantidad() + "");
        txtPrecioAct.setText(bitacora.getPrecio() + "");
        dpFecharegistroAct.setValue(bitacora.getFecha().toLocalDate());

        cbEstadoAct.setSelected(bitacora.isStatus());

        var actividad = actividadService.obtenerPorId(bitacora.getActividad());
        cbxActividadesAct.setValue(actividad);

        var area = areaService.obtenerPorId(bitacora.getArea());
        cbxAreasAct.setValue(area);
    }

    private void cargarBitacoraPorEmpleado() {
        bitacora = tblReporteEmpleadoActualizar.getSelectionModel().getSelectedItem();
        if (bitacora != null && bitacora.getId() != 0) {
            cargarCamposActualizar();
        } else {
            MensajePersonalizado.Ver("NO SELECCIONADO", "Por favor seleccione un reporte diario", Alert.AlertType.WARNING);
        }
    }

    private void actualizar() {
        if (VerificarEspaciosAtualizar()) {
            boolean exito = bitacoraService.actualizarBitacoraEmpleado(
                    new BitacoraEmpleado(
                            bitacora.getId(),
                            bitacora.getEmpleado(),
                            cbxActividadesAct.getSelectionModel().getSelectedItem().getId(),
                            cbxAreasAct.getSelectionModel().getSelectedItem().getId(),
                            Date.valueOf(dpFecharegistroAct.getValue()),
                            Double.parseDouble(txtPrecioAct.getText()),
                            Double.parseDouble(txtCantidadAct.getText()),
                            cbEstadoAct.isSelected()
                    ));
            if (exito) {
                MensajePersonalizado.Ver("EXITO AL ACTUALIZAR", "Reporte diario actualizado correctamente", Alert.AlertType.CONFIRMATION);
                limpiarCampos();
            } else {
                MensajePersonalizado.Ver("ERROR", "Error al actualizar el reporte diario", Alert.AlertType.ERROR);
            }
        } else {
            MensajePersonalizado.Ver("INFORMACIÓN INCOMPLETA", "Los campos son requeridos, verifique que la información este completa", Alert.AlertType.WARNING);

        }
    }

    private void limpiarCampos() {
        txtCantidadAct.setText("");
        txtPrecioAct.setText("");
        // Establecer el valor del DatePicker como null
        dpFecharegistroAct.setValue(null);
        cbxActividadesAct.setItems(ObservableActividad);
        cbxAreasAct.setItems(ObservableArea);
        cbxFiltrarEmpleadoActualizar.setItems(ObservableEmpleado);
        cbEstadoAct.setSelected(false);
    }

    public boolean VerificarEspaciosAtualizar() {
        if (dpFecharegistroAct.getValue() == null) {
            return false;
        }

        if (txtCantidadAct.getText().isEmpty() || txtCantidadAct.getText() == "") {
            return false;
        }

        if (txtPrecioAct.getText().isEmpty() || txtCantidadAct.getText() == "") {
            return false;
        }

        if (cbxActividadesAct.getSelectionModel().getSelectedItem() == null || cbxAreasAct.getSelectionModel().getSelectedItem() == null) {
            return false;
        }

        return true;
    }

    @FXML
    private void onCargar(ActionEvent event) {
        cargarBitacoraPorEmpleado();
    }

    @FXML
    private void onLimpiar(ActionEvent event) {
        limpiarCampos();
    }

    @FXML
    private void btnActualizar(ActionEvent event) {
        actualizar();
    }

}
