/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import Alertas.MensajePersonalizado;
import DAO.EmpleadoDAO;
import DAO.VacacionesDAO;
import Models.Empleado;
import Models.Incapacidad;
import Models.Vacaciones;
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
import javafx.scene.input.KeyEvent;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AgregarVacacionesController implements Initializable {

    @FXML
    private CheckBox cbEstadoAgre;
    @FXML
    private ComboBox<Empleado> cbxFiltrarEmpleado;
    @FXML
    private TextField txtMonto;
    @FXML
    private DatePicker dpFecha;

    /**
     * Initializes the controller class.
     */
    
    ObservableList<Empleado> ObservableEmpleado = FXCollections.observableArrayList();
    Vacaciones vacaciones = new Vacaciones();
    VacacionesDAO vacaionesDao = new VacacionesDAO();
    EmpleadoDAO empleadoDAO = new EmpleadoDAO();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        configurar();    }

    public void configurar() {
        
        ObservableEmpleado = FXCollections.observableArrayList(empleadoDAO.obtenerListaEmpleados());
        cbxFiltrarEmpleado.setItems(ObservableEmpleado);

        cbxFiltrarEmpleado.setConverter(new StringConverter<Empleado>() {
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
            boolean exito = vacaionesDao.insertarVacacion(
                    new Vacaciones(0,
                            cbxFiltrarEmpleado.getValue().getCedula(),
                            Double.parseDouble(txtMonto.getText()),
                            java.sql.Date.valueOf(dpFecha.getValue()),
                            cbEstadoAgre.isSelected()));

            if (exito) {
                MensajePersonalizado.Ver("EXITO AL INSERTAR", "Vacacion insertado correctamente", Alert.AlertType.CONFIRMATION);
                limpiarCamposAgregar();
            } else {
                MensajePersonalizado.Ver("ERROR", "Error al insertar la vacacion", Alert.AlertType.ERROR);
            }
        } else {
            MensajePersonalizado.Ver("INFORMACIÓN INCOMPLETA", "Los campos son requeridos, verifique que la información este completa", Alert.AlertType.WARNING);

        }
    }

    public boolean VerificarEspaciosAgregar() {
        if (cbxFiltrarEmpleado.getValue() != null
                && !cbxFiltrarEmpleado.getValue().getNombre().trim().equals("")
                && !dpFecha.getValue().format(DateTimeFormatter.ISO_DATE).trim().equals("")
                && dpFecha.getValue() != null
                && !txtMonto.getText().trim().equals("")
                && txtMonto.getText() != null) {
            return true;
        } else {
            return false;

        }
    }
    
    private void limpiarCamposAgregar() {
        cbxFiltrarEmpleado.setValue(ObservableEmpleado.get(0));
         dpFecha.setValue(LocalDate.now());
        txtMonto.setText("");
        cbEstadoAgre.setSelected(vacaciones.isStatus());
    }
    
    @FXML
    private void btnGuardar(ActionEvent event) {
        guardar();
    }


}
