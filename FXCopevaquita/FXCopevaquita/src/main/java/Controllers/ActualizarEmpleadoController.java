/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import Models.Empleado;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

import DAO.EmpleadoDAO;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ActualizarEmpleadoController implements Initializable {

    @FXML
    private ComboBox<Empleado> cbxFiltrarEmpleado;
    @FXML
    private TextField txtCedula1;
    @FXML
    private TextField txtNombre1;
    @FXML
    private TextField txtApellidos1;
    @FXML
    private ComboBox<String> cbxSexo1;
    @FXML
    private TextField txtEstadoCilvil1;
    @FXML
    private TextField txtTipoSangre1;
    @FXML
    private DatePicker dpFechaNacimiento1;
    @FXML
    private TextField txtNivelAcamicoAct;
    @FXML
    private TextField txtCuentaAct;
    @FXML
    private DatePicker dpFechaIngreso1;
    @FXML
    private ComboBox<String> cbxTipoEmpleado1;
    @FXML
    private CheckBox cbEstado1;

    ObservableList<String> observableSexo = FXCollections.observableArrayList("MASCULINO", "FEMENINO", "OTRO");
    ObservableList<String> observableTipoEmpleados = FXCollections.observableArrayList("ADMINISTRADOR", "SECRETARIO", "SOCIO", "PEON");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarElementos();
    }

    private void configurarElementos() {

        var observableEmpleado
                = FXCollections.observableArrayList(new EmpleadoDAO().obtenerListaEmpleados());

        cbxFiltrarEmpleado.setConverter(new StringConverter<Empleado>() {
            @Override
            public String toString(Empleado t) {
                if (t == null) {
                    return ""; // o cualquier otro valor predeterminado que desees
                }
                return t.getNombre() + " " + t.getApellidos();
            }

            @Override
            public Empleado fromString(String t) {
                if (t == null || t.isEmpty()) {
                    return null; // Manejar el caso de entrada vacía o nula
                }
                Predicate<String> find = (x) -> x != null && x.equals(t);
                Optional<Empleado> firstMatch = observableEmpleado.filtered(x -> find.test(x.getNombre() + " " + x.getApellidos())).stream().findFirst();
                return firstMatch.orElse(null); // Devuelve el primer empleado encontrado o null si no se encontró ninguna coincidencia.
            }
        });

        cbxFiltrarEmpleado.setItems(observableEmpleado);
        cbxSexo1.setItems(observableSexo);
        cbxTipoEmpleado1.setItems(observableTipoEmpleados);

        cbxFiltrarEmpleado.valueProperty().addListener((observable, oldValue, newValue) -> {
            // Método que se ejecuta al cambiar el ComboBox
            var empleadoSeleccionado = (Empleado) newValue;
            if (empleadoSeleccionado != null && !"".equals(empleadoSeleccionado.getCedula().trim())) {

                txtCedula1.setText(empleadoSeleccionado.getCedula());
                txtNombre1.setText(empleadoSeleccionado.getNombre());
                txtApellidos1.setText(empleadoSeleccionado.getApellidos());
                txtEstadoCilvil1.setText(empleadoSeleccionado.getEstadoCivil());
                txtTipoSangre1.setText(empleadoSeleccionado.getTipoSangre());
                txtCuentaAct.setText(empleadoSeleccionado.getNumeroCuenta());

                txtNivelAcamicoAct.setText(empleadoSeleccionado.getNivelAcademico());

                var nacimiento = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(
                        empleadoSeleccionado.getFechaNacimiento()
                ));

                var ingreso = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(
                        empleadoSeleccionado.getFechaIngreso()
                ));

                dpFechaNacimiento1.setValue(nacimiento);
                dpFechaIngreso1.setValue(ingreso);

                cbxSexo1.setValue(empleadoSeleccionado.getSexo());
                cbxTipoEmpleado1.setValue(empleadoSeleccionado.getTipo());
                cbEstado1.setSelected(empleadoSeleccionado.isStatus());
            }
        });
    }

    @FXML
    private void FiltrarEmpleado(ActionEvent event) {
    }

    @FXML
    private void btnActualizar(ActionEvent event) {
        // Que los campos de no esten vacios...
        
        
        // recordar hacer la conversión a Date
        //     public static void main(String[] args) {
        // Crear un objeto LocalDate
        // LocalDate fechaLocalDate = LocalDate.of(2023, 12, 31);
        // Convertir LocalDate a java.sql.Date
        // Date fechaSQL = Date.valueOf(fechaLocalDate);
        // Imprimir la fecha SQL
        // System.out.println("Fecha SQL: " + fechaSQL);
    }
}
