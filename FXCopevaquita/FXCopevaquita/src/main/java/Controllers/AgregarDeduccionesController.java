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
 *
 * @author User
 */
public class AgregarDeduccionesController implements Initializable {

    @FXML
    private ComboBox<Empleado> cbxFiltrarEmpleadoAgre;
    @FXML
    private ComboBox<TipoDeduccion> cbxTipoDeduccionAgre;
    @FXML
    private CheckBox cbEstadoAgre;
    @FXML
    private TextField txtMonto;
    @FXML
    private TextField txtCuota;

    /**
     * Initializes the controller class.
     */
    ObservableList<Empleado> ObservableEmpleado = FXCollections.observableArrayList();
    ObservableList<TipoDeduccion> ObservableTipoDeduccion = FXCollections.observableArrayList();
    EmpleadoDAO daoEmpleado = new EmpleadoDAO();
    Deduccion deduccion = new Deduccion();
    DeduccionesDAO daoDeduccion = new DeduccionesDAO();
    TipoDeduccionDAO daoTipoDeduccion = new TipoDeduccionDAO();
    @FXML
    private DatePicker dpFecha;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurar();
    }

    public void configurar() {

        ObservableEmpleado = FXCollections.observableArrayList(daoEmpleado.obtenerListaEmpleados());
        cbxFiltrarEmpleadoAgre.setItems(ObservableEmpleado);

        cbxFiltrarEmpleadoAgre.setConverter(new StringConverter<Empleado>() {
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
        cbxTipoDeduccionAgre.setItems(ObservableTipoDeduccion);
        cbxTipoDeduccionAgre.setConverter(new StringConverter<TipoDeduccion>() {
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

    private void guardar() {
        if (VerificarEspaciosAgregar()) {
            boolean exito = daoDeduccion.insertarDeduccion(
                    new Deduccion(0,
                            cbxTipoDeduccionAgre.getValue().getId(),
                            Double.parseDouble(txtMonto.getText()),
                            Double.parseDouble(txtCuota.getText()),
                            Double.parseDouble(txtMonto.getText()),
                            cbxFiltrarEmpleadoAgre.getValue().getCedula(),
                            cbEstadoAgre.isSelected()));

            if (exito) {
                MensajePersonalizado.Ver("EXITO AL INSERTAR", "Deducción insertada correctamente", Alert.AlertType.CONFIRMATION);
                limpiarCamposAgregar();
            } else {
                MensajePersonalizado.Ver("ERROR", "Error al insertar la deducción", Alert.AlertType.ERROR);
            }
        } else {
            MensajePersonalizado.Ver("INFORMACIÓN INCOMPLETA", "Los campos son requeridos, verifique que la información este completa", Alert.AlertType.WARNING);

        }
    }

    public boolean VerificarEspaciosAgregar() {
        if (cbxFiltrarEmpleadoAgre.getValue() != null
                && !cbxFiltrarEmpleadoAgre.getValue().getNombre().trim().equals("")
                && cbxTipoDeduccionAgre.getValue() != null
                && !cbxTipoDeduccionAgre.getValue().getNombre().trim().equals("")
                && txtMonto.getText() != null && !txtMonto.getText().trim().equals("")
                && txtCuota.getText() != null && !txtCuota.getText().trim().equals("")) {
            return true;
        } else {
            return false;
        }
    }

    private void limpiarCamposAgregar() {
        txtCuota.setText("");
        txtMonto.setText("");
        cbxFiltrarEmpleadoAgre.setValue(ObservableEmpleado.get(0));
        cbxTipoDeduccionAgre.setValue(ObservableTipoDeduccion.get(0));
        cbEstadoAgre.setSelected(deduccion.isStatus());
    }

    @FXML
    private void btnGuardar(ActionEvent event) {
        guardar();
    }

}
