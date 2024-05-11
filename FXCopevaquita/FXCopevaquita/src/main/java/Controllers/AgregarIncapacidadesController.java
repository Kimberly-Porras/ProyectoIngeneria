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
public class AgregarIncapacidadesController implements Initializable {

    @FXML
    private ComboBox<Empleado> cbxFiltrarEmpleadoAgre;
    @FXML
    private CheckBox cbEstadoAgre;
    @FXML
    private DatePicker dpFecha;
    @FXML
    private TextField txtMotivo;
    @FXML
    private TextField txtMonto;

    /**
     * Initializes the controller class.
     */
    IncapacidadDAO dao = new IncapacidadDAO();
    EmpleadoDAO daoEmpleado = new EmpleadoDAO();
    ObservableList<Empleado> ObservableEmpleado = FXCollections.observableArrayList();
    Incapacidad incapacidad = new Incapacidad();

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
            boolean exito = dao.insertarIncapacidad(
                    new Incapacidad(0,
                            java.sql.Date.valueOf(dpFecha.getValue()),
                            Double.parseDouble(txtMonto.getText()),
                            txtMotivo.getText(),
                            cbxFiltrarEmpleadoAgre.getValue().getCedula(),
                            cbEstadoAgre.isSelected()));

            if (exito) {
                MensajePersonalizado.Ver("EXITO AL INSERTAR", "Incapacidad insertado correctamente", Alert.AlertType.CONFIRMATION);
                limpiarCamposAgregar();
            } else {
                MensajePersonalizado.Ver("ERROR", "Error al insertar la incapacidad", Alert.AlertType.ERROR);
            }
        } else {
            MensajePersonalizado.Ver("INFORMACIÓN INCOMPLETA", "Los campos son requeridos, verifique que la información este completa", Alert.AlertType.WARNING);

        }
    }

    private void limpiarCamposAgregar() {
        cbxFiltrarEmpleadoAgre.setValue(ObservableEmpleado.get(0));
        dpFecha.setValue(LocalDate.now());
        txtMonto.setText("");
        txtMotivo.setText("");
        cbEstadoAgre.setSelected(incapacidad.isStatus());
    }

    public boolean VerificarEspaciosAgregar() {
        if (cbxFiltrarEmpleadoAgre.getValue() != null
                && !cbxFiltrarEmpleadoAgre.getValue().getNombre().trim().equals("")
                && dpFecha.getValue() != null
                && !dpFecha.getValue().format(DateTimeFormatter.ISO_DATE).trim().equals("")
                && txtMonto.getText() != null
                && !txtMonto.getText().trim().equals("")
                && txtMotivo.getText() != null
                && !txtMotivo.getText().trim().equals("")) {
            return true;
        } else {
            return false;

        }
    }

    @FXML
    private void FiltrarEmpleado(ActionEvent event) {
    }

    @FXML
    private void btnGuardar(ActionEvent event) {
        guardar();
    }

}
