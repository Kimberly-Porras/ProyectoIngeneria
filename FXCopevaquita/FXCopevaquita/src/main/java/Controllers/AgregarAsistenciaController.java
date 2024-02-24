/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import Alertas.MensajePersonalizado;
import DAO.BitacoraAsistenciaDAO;
import DAO.EmpleadoDAO;
import Models.BitacoraAsistencia;
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
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AgregarAsistenciaController implements Initializable {

    @FXML
    private ComboBox<Empleado> cbxFiltrarEmpleadoAgre;
    @FXML
    private DatePicker dpFecha;
    @FXML
    private CheckBox cbPresente;
    @FXML
    private CheckBox cbJudtifica;

    /**
     * Initializes the controller class.
     */
    ObservableList<Empleado> ObservableEmpleado = FXCollections.observableArrayList();
     EmpleadoDAO daoEmpleado = new EmpleadoDAO();
     BitacoraAsistenciaDAO AsistenciaDao = new BitacoraAsistenciaDAO();
     BitacoraAsistencia asistencia = new BitacoraAsistencia();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    }
    
    private void guardar() {
        if (VerificarEspaciosAgregar()) {
            boolean exito = AsistenciaDao.insertarAsistencia(
                    new BitacoraAsistencia(0,
                            java.sql.Date.valueOf(dpFecha.getValue()),
                            cbPresente.isSelected(),
                            cbJudtifica.isSelected(),
                            cbxFiltrarEmpleadoAgre.getValue().getCedula()));

            if (exito) {
                MensajePersonalizado.Ver("EXITO AL INSERTAR", "Incapacidadn insertado correctamente", Alert.AlertType.CONFIRMATION);
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
        cbJudtifica.setSelected(asistencia.isJustifica());
        cbPresente.setSelected(asistencia.isEstaPresente());
    }

    public boolean VerificarEspaciosAgregar() {
        if (cbxFiltrarEmpleadoAgre.getValue() != null
                && !cbxFiltrarEmpleadoAgre.getValue().getNombre().trim().equals("")
                && dpFecha.getValue() != null
                && !dpFecha.getValue().format(DateTimeFormatter.ISO_DATE).trim().equals("")) {
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
