/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import Alertas.MensajePersonalizado;
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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import DAO.EmpleadoDAO;
import java.time.format.DateTimeFormatter;

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
    EmpleadoDAO EmpleadoDao = new EmpleadoDAO();

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
                    return "";
                }
                return t.getNombre() + " " + t.getApellidos();
            }

            @Override
            public Empleado fromString(String t) {
                if (t == null || t.isEmpty()) {
                    return null;
                }
                Predicate<String> find = (x) -> x != null && x.equals(t);
                Optional<Empleado> firstMatch = observableEmpleado.filtered(x -> find.test(x.getNombre() + " " + x.getApellidos())).stream().findFirst();
                return firstMatch.orElse(null);
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

    public void actualizar() {
        if (VerificarEspaciosActualizar()) {
            boolean exito = EmpleadoDao.actualizarEmpleado(
                    new Empleado(
                            txtCedula1.getText(),
                            txtNombre1.getText(),
                            txtApellidos1.getText(),
                            cbxSexo1.getValue(),
                            txtEstadoCilvil1.getText(),
                            txtTipoSangre1.getText(),
                            java.sql.Date.valueOf(dpFechaNacimiento1.getValue()),
                            java.sql.Date.valueOf(dpFechaIngreso1.getValue()),
                            cbxTipoEmpleado1.getValue(),
                            txtCuentaAct.getText(),
                            txtNivelAcamicoAct.getText(),
                            cbEstado1.isSelected())
            );

            if (exito) {
                MensajePersonalizado.Ver("EXITO AL ACTUALIZAR", "Usuario actualizado correctamente", Alert.AlertType.CONFIRMATION);
                cbxFiltrarEmpleado.setValue(null);
                limpiarCamposActualizar();
            } else {
                MensajePersonalizado.Ver("ERROR", "Error al actualizar el usuario", Alert.AlertType.ERROR);
            }
        } else {
            MensajePersonalizado.Ver("INFORMACIÓN INCOMPLETA", "Los campos son requeridos, verifique que la información este completa", Alert.AlertType.INFORMATION);
        }
    }

    public boolean VerificarEspaciosActualizar() {
        if (!txtCedula1.getText().trim().equals("")
                && !txtNombre1.getText().trim().equals("")
                && !txtApellidos1.getText().trim().equals("")
                && !txtEstadoCilvil1.getText().trim().equals("")
                && !txtTipoSangre1.getText().trim().equals("")
                && !cbxSexo1.getValue().equals("")
                && !txtCuentaAct.getText().equals("")
                && !txtNivelAcamicoAct.getText().equals("")
                && dpFechaNacimiento1.getValue() != null
                && dpFechaIngreso1.getValue() != null
                && !dpFechaNacimiento1.getValue().format(DateTimeFormatter.ISO_DATE).trim().equals("")
                && !dpFechaIngreso1.getValue().format(DateTimeFormatter.ISO_DATE).trim().equals("")
                && !cbxTipoEmpleado1.getValue().equals("")
                && !cbEstado1.getText().trim().equals("")) {

            return true;
        } else {
            return false;
        }
    }

    public void limpiarCamposActualizar() {
        txtCedula1.setText("");
        txtNombre1.setText("");
        txtApellidos1.setText("");
        txtEstadoCilvil1.setText("");
        txtTipoSangre1.setText("");
        cbxSexo1.setValue("");
        txtCuentaAct.setText("");
        txtNivelAcamicoAct.setText("");
        dpFechaNacimiento1.setValue(LocalDate.now());
        dpFechaIngreso1.setValue(LocalDate.now());
        cbxTipoEmpleado1.setValue("");
        cbEstado1.setText("");
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
        actualizar();
    }
}
