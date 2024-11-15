/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import Alertas.MensajePersonalizado;
import DAO.TipoDeduccionDAO;
import Models.TipoDeduccion;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


/**
 * FXML Controller class
 * @author alber
 * @author kim03
 */
public class TipoDeduccionesController implements Initializable {

    @FXML
    private TextField txtNombreTipoDeducciones;
    @FXML
    private CheckBox cbEstadoTipoDeducciones;
    @FXML
    private TableView<TipoDeduccion> tblTipoDeduccion;
    @FXML
    private TableColumn<TipoDeduccion, String> colIdTipoDeduccion;
    @FXML
    private TableColumn<TipoDeduccion, String> colNombreTipoDeduccion;
    @FXML
    private TableColumn<TipoDeduccion, String> colEstadoTipoDeduccion;

    TipoDeduccionDAO dao = new TipoDeduccionDAO();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurar();
    }

    public void configurar() {
        List<TipoDeduccion> listaTipoDeduccion = dao.obtenerTodos();
        ObservableList<TipoDeduccion> TipoDeduccionObservable = FXCollections.observableArrayList(listaTipoDeduccion);
        tblTipoDeduccion.setItems(TipoDeduccionObservable);

        colIdTipoDeduccion.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombreTipoDeduccion.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colEstadoTipoDeduccion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().isStatus() ? "Activo" : "Inactivo"));
        cbEstadoTipoDeducciones.setSelected(true);
    }

    public void guardar() {
        if (VerificarEspaciosAgregarTipoDeduccion()) {
            boolean exito = dao.insertarTipoDeduccion(
                    new TipoDeduccion(0,
                            txtNombreTipoDeducciones.getText(),
                            cbEstadoTipoDeducciones.isSelected())
            );
            if (exito) {
                MensajePersonalizado.Ver("EXITO AL INSERTAR el tipo de deducción", "Tipo de deducción insertado correctamente", Alert.AlertType.CONFIRMATION);
                limpiarCamposAgregarTipoDeduccion();
                configurar();
            } else {
                MensajePersonalizado.Ver("ERROR", "Error al insertar el tipo de dducción", Alert.AlertType.ERROR);
            }

        } else {
            MensajePersonalizado.Ver("INFORMACIÓN INCOMPLETA", "Los campos son requeridos, verifique que la información este completa", Alert.AlertType.INFORMATION);

        }
    }

    public boolean VerificarEspaciosAgregarTipoDeduccion() {
        if (!txtNombreTipoDeducciones.getText().trim().equals("")
                && !cbEstadoTipoDeducciones.getText().trim().equals("")) {

            return true;
        } else {
            return false;
        }
    }

    public void limpiarCamposAgregarTipoDeduccion() {
        txtNombreTipoDeducciones.setText("");
        cbEstadoTipoDeducciones.setSelected(true);
    }

    public void actualizar() {
        var TipoDeduccion = tblTipoDeduccion.getSelectionModel().getSelectedItem();

        if (TipoDeduccion != null && TipoDeduccion.getId() != 0) {
            if (VerificarEspaciosAgregarTipoDeduccion()) {
                TipoDeduccion.setNombre(txtNombreTipoDeducciones.getText());
                TipoDeduccion.setStatus(cbEstadoTipoDeducciones.isSelected());
                boolean exito = dao.actualizarTipoDeduccion(
                        TipoDeduccion
                );
                if (exito) {
                    MensajePersonalizado.Ver("EXITO AL ACTUALIZAR", "Tipo de deducción actualizado correctamente", Alert.AlertType.CONFIRMATION);
                    limpiarCamposAgregarTipoDeduccion();
                    configurar();
                } else {
                    MensajePersonalizado.Ver("ERROR", "Error al actualizar el tipo de deducción", Alert.AlertType.ERROR);
                }
            } else {
                MensajePersonalizado.Ver("INFORMACIÓN INCOMPLETA", "Los campos son requeridos, verifique que la información este completa", Alert.AlertType.INFORMATION);

            }
        } else {
            MensajePersonalizado.Ver("SELECCIONE", "Debe de seleccionar un tipo de deducción a modificar", Alert.AlertType.WARNING);
        }
    }

    public void cargarDatos() {
        var tipoDeduccion = tblTipoDeduccion.getSelectionModel().getSelectedItem();

        if (tipoDeduccion != null && tipoDeduccion.getId() != 0) {
            txtNombreTipoDeducciones.setText(tipoDeduccion.getNombre());
            cbEstadoTipoDeducciones.setSelected(tipoDeduccion.isStatus());
        }
    }

    //    private void filtrarTipoDeduccion() {
//        if (txtFiltrarEmpleado.getText() != null && !txtFiltrarEmpleado.getText().trim().equals("")) {
//            Predicate<Contrato> pContrato = x
//                    -> x.getCedula_empleado().toLowerCase().contains(txtFiltrarEmpleado.getText().toLowerCase());
//            Predicate<Empleado> pEmpleado = x
//                    -> x.getCedula().toLowerCase().contains(txtFiltrarEmpleado.getText().toLowerCase())
//                    || x.getNombre().toLowerCase().contains(txtFiltrarEmpleado.getText().toLowerCase())
//                    || x.getApellidos().toLowerCase().contains(txtFiltrarEmpleado.getText().toLowerCase())
//                    || x.getNombreCompleto().toLowerCase().contains(txtFiltrarEmpleado.getText().toLowerCase());
//            var listaTemporal = ObservableContrato.filtered((x) -> pEmpleado.test(Get(x.getCedula_empleado())) || pContrato.test(x));
//            tblListaContrato.setItems(listaTemporal);
//        } else {
//            tblListaContrato.setItems(ObservableContrato);
//        }
//    }
    @FXML
    private void btnGuardarTipoDeducciones(ActionEvent event) {
        guardar();
    }

    @FXML
    private void btnActualizarTipoDeducciones(ActionEvent event) {
        actualizar();
    }


    @FXML
    private void btnCargarDatos(ActionEvent event) {
        cargarDatos();
    }

}
