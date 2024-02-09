/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import Alertas.MensajePersonalizado;
import DAO.ActividadDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import Models.Actividad;
import java.util.List;
import java.util.function.Predicate;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author alber
 * @author kim03
 */
public class ActividadesController implements Initializable {

    @FXML
    private TextField txtNombreActividad;
    @FXML
    private CheckBox cbEstadoActividad;
    @FXML
    private TextField txtFiltrarActividades;
    @FXML
    private TableView<Actividad> tblActividades;
    @FXML
    private TableColumn<Actividad, String> colIdActividad;
    @FXML
    private TableColumn<Actividad, String> colNombreActividad;
    @FXML
    private TableColumn<Actividad, String> colEstadoActividad;

    private ActividadDAO dao = new ActividadDAO();

    /**
     * Initializes the controller class.
     */
    
    ActividadDAO ActividadDao = new ActividadDAO();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        configurar();
    }

    public void configurar() {
        colIdActividad.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombreActividad.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colEstadoActividad.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().isStatus() ? "Activo" : "Inactivo"));
        cbEstadoActividad.setSelected(true);

        List<Actividad> listaActividad = dao.obtenerTodos();
        ObservableList<Actividad> ActividadObservable = FXCollections.observableArrayList(listaActividad);
        tblActividades.setItems(ActividadObservable);
    }

    public void guardar() {
        if (VerificarEspaciosAgregarActividad()) {
            boolean exito = ActividadDao.insertarActividad(
                    new Actividad(0,
                            txtNombreActividad.getText(),
                            cbEstadoActividad.isSelected())
            );
            if (exito) {
                MensajePersonalizado.Ver("EXITO AL INSERTAR", "Actividad insertada correctamente", Alert.AlertType.CONFIRMATION);
                limpiarCamposAgregarActividad();
                configurar();
            } else {
                MensajePersonalizado.Ver("ERROR", "Error al insertar la actividad", Alert.AlertType.ERROR);
            }

        } else {
            MensajePersonalizado.Ver("INFORMACIÓN INCOMPLETA", "Los campos son requeridos, verifique que la información este completa", Alert.AlertType.INFORMATION);

        }
    }
    
    public boolean VerificarEspaciosAgregarActividad() {
        if (!txtNombreActividad.getText().trim().equals("")
                && !cbEstadoActividad.getText().trim().equals("")) {

            return true;
        } else {
            return false;
        }
    }

    public void limpiarCamposAgregarActividad() {
        txtNombreActividad.setText("");
        cbEstadoActividad.setSelected(true);
    }

    public void actualizar() {
        var actividad = tblActividades.getSelectionModel().getSelectedItem();

        if (actividad != null && actividad.getId() != 0) {
            if (VerificarEspaciosAgregarActividad()) {
                actividad.setNombre(txtNombreActividad.getText());
                actividad.setStatus(cbEstadoActividad.isSelected());
                boolean exito = ActividadDao.actualizarActividad(
                        actividad
                );
                if (exito) {
                    MensajePersonalizado.Ver("EXITO AL ACTUALIZAR", "Actividad actualizada correctamente", Alert.AlertType.CONFIRMATION);
                    limpiarCamposAgregarActividad();
                    configurar();
                } else {
                    MensajePersonalizado.Ver("ERROR", "Error al actualizar la actividad", Alert.AlertType.ERROR);
                }

            } else {
                MensajePersonalizado.Ver("INFORMACIÓN INCOMPLETA", "Los campos son requeridos, verifique que la información este completa", Alert.AlertType.INFORMATION);

            }
        } else {
            MensajePersonalizado.Ver("SELECCIONE", "Debe de seleccionar una activadad a modificar", Alert.AlertType.WARNING);
        }
    }

    public void cargarDatos() {
        var actividad = tblActividades.getSelectionModel().getSelectedItem();

        if (actividad != null && actividad.getId() != 0) {
            txtNombreActividad.setText(actividad.getNombre());
            cbEstadoActividad.setSelected(actividad.isStatus());
        }
    }
    
//    private void filtrarActividades() {
//        ObservableList<Actividad> ObservableActvidad = FXCollections.observableArrayList();
//        String filtro = txtFiltrarActividades.getText().trim().toLowerCase();
//
//        if (!filtro.isEmpty()) {
//            Predicate<Actividad> pActividad = x -> x.getNombre().toLowerCase().contains(filtro);
//            var listaTemporal = ObservableActvidad.filtered(x -> pActividad.test(x));
//            tblActividades.setItems(listaTemporal);
//        } else {
//            tblActividades.setItems(ObservableActvidad);
//        }
//    }

    @FXML
    private void btnGuardarActividad(ActionEvent event) {
        guardar();
    }

    @FXML
    private void btnActualizarActividad(ActionEvent event) {
        actualizar();
    }

    @FXML
    private void OnFiltrarActividad(KeyEvent event) {
//        filtrarActividades();
    }

    @FXML
    private void btnCargarDatos(ActionEvent event) {
        cargarDatos();
    }

}
