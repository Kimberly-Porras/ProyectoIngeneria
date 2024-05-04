/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import Alertas.MensajePersonalizado;
import DAO.ActividadDAO;
import DAO.AreaDAO;
import DAO.BitacoraEmpleadoDAO;
import DAO.EmpleadoDAO;
import Models.Actividad;
import Models.Area;
import Models.BitacoraEmpleado;
import Models.Empleado;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 * @author alber
 * @author kim03
 */
public class AgregarBitacoraEmpleadoController implements Initializable {

    @FXML
    private ComboBox<Empleado> cbxFiltrarEmpleadoAgre;
    @FXML
    private DatePicker dpFechaIngresoAgre;
    @FXML
    private ComboBox<Actividad> cbxActividadesAgre;
    @FXML
    private ComboBox<Area> cbxAreasAgre;
    @FXML
    private TextField txtCantidadAgre;
    @FXML
    private TextField txtPrecioAgre;
    @FXML
    private CheckBox cbEstadoAgre;

    /**
     * Initializes the controller class.
     */
    BitacoraEmpleadoDAO daoBitacoraEmpleado = new BitacoraEmpleadoDAO();
    EmpleadoDAO daoEmpleado = new EmpleadoDAO();
    ActividadDAO daoActividad = new ActividadDAO();
    AreaDAO daoArea = new AreaDAO();
    ObservableList<Empleado> ObservableEmpleado = FXCollections.observableArrayList();
    ObservableList<Actividad> ObservableActividad = FXCollections.observableArrayList();
    ObservableList<Area> ObservableArea = FXCollections.observableArrayList();
    BitacoraEmpleado reporteDiario = new BitacoraEmpleado();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurar();
    }

    private void configurar() {
        ObservableActividad = FXCollections.observableArrayList(daoActividad.obtenerListaActividades());

        cbxActividadesAgre.setItems(ObservableActividad);

        cbxActividadesAgre.setConverter(new StringConverter<Actividad>() {
            @Override
            public String toString(Actividad t) {
                if (t == null) {
                    return ""; // o cualquier otro valor predeterminado que desees
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
                return firstMatch.orElse(null);
            }
        });
        ObservableArea = FXCollections.observableArrayList(daoArea.obtenerListaArea());
        cbxAreasAgre.setItems(ObservableArea);
        cbxAreasAgre.setConverter(new StringConverter<Area>() {
            @Override
            public String toString(Area t) {
                if (t == null) {
                    return ""; // o cualquier otro valor predeterminado que desees
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
                return firstMatch.orElse(null);
            }
        });
        ObservableEmpleado = FXCollections.observableArrayList(daoEmpleado.obtenerListaEmpleadosExternos());
        cbxFiltrarEmpleadoAgre.setItems(ObservableEmpleado);
        cbxFiltrarEmpleadoAgre.setConverter(new StringConverter<Empleado>() {
            @Override
            public String toString(Empleado t) {
                if (t == null) {
                    return ""; // o cualquier otro valor predeterminado que desees
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
    }

    private void guardar() {
        if (VerificarEspaciosAgregar()) {
            boolean exito = daoBitacoraEmpleado.insertarBitacoraEmpleado(
                    new BitacoraEmpleado(0,
                            cbxFiltrarEmpleadoAgre.getValue().getCedula(),
                            cbxActividadesAgre.getValue().getId(),
                            cbxAreasAgre.getValue().getId(),
                            java.sql.Date.valueOf(dpFechaIngresoAgre.getValue()),
                            Double.parseDouble(txtPrecioAgre.getText()),
                            Double.parseDouble(txtCantidadAgre.getText()),
                            cbEstadoAgre.isSelected()));

            if (exito) {
                MensajePersonalizado.Ver("EXITO AL INSERTAR", "Usuario insertado correctamente", Alert.AlertType.CONFIRMATION);

                limpiarCamposAgregar();

            } else {
                MensajePersonalizado.Ver("ERROR", "Error al insertar el usuario", Alert.AlertType.ERROR);
            }
        } else {
            MensajePersonalizado.Ver("INFORMACIÓN INCOMPLETA", "Los campos son requeridos, verifique que la información este completa", Alert.AlertType.WARNING);

        }
    }

    private void limpiarCamposAgregar() {
        txtCantidadAgre.setText("");
        txtPrecioAgre.setText("");
        cbxFiltrarEmpleadoAgre.setValue(ObservableEmpleado.get(0));
        cbxActividadesAgre.setValue(ObservableActividad.get(0));
        cbxAreasAgre.setValue(ObservableArea.get(0));
        dpFechaIngresoAgre.setValue(LocalDate.now());
        cbEstadoAgre.setSelected(reporteDiario.isStatus());
    }

    public boolean VerificarEspaciosAgregar() {
        if (txtCantidadAgre.getText() != null && !txtCantidadAgre.getText().trim().equals("")
                && txtPrecioAgre.getText() != null && !txtPrecioAgre.getText().trim().equals("")
                && cbxFiltrarEmpleadoAgre.getValue() != null
                && !cbxFiltrarEmpleadoAgre.getValue().getNombre().trim().equals("")
                && cbxActividadesAgre.getValue() != null
                && !cbxActividadesAgre.getValue().getNombre().trim().equals("")
                && cbxAreasAgre.getValue() != null
                && !cbxAreasAgre.getValue().getNombre().trim().equals("")
                && dpFechaIngresoAgre.getValue() != null
                && !dpFechaIngresoAgre.getValue().format(DateTimeFormatter.ISO_DATE).trim().equals("")) {
            return true;
        } else {
            return false;
        }
    }

    @FXML
    private void btnGuardar(ActionEvent event) {
        guardar();
    }

}
