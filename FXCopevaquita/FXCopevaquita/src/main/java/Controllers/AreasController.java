/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import Alertas.MensajePersonalizado;
import DAO.AreaDAO;
import Models.Area;
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
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 * @author alber
 * @author kim03
 */
public class AreasController implements Initializable {

    @FXML
    private TextField txtNombreArea;
    @FXML
    private CheckBox cbEstadoArea;
    @FXML
    private TextField txtFiltrarArea;
    @FXML
    private TableView<Area> tblAreas;
    @FXML
    private TableColumn<Area, String> colIdArea;
    @FXML
    private TableColumn<Area, String> colNombreArea;
    @FXML
    private TableColumn<Area, String> colEstadoArea;

    /**
     * Initializes the controller class.
     */
    AreaDAO dao = new AreaDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        configurar();
    }

    public void configurar() {
        List<Area> listaArea = dao.obtenerTodos();
        ObservableList<Area> AreaObservable = FXCollections.observableArrayList(listaArea);
        tblAreas.setItems(AreaObservable);

        colIdArea.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombreArea.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colEstadoArea.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().isStatus() ? "Activo" : "Inactivo"));
        cbEstadoArea.setSelected(true);
    }

    public void guardar() {
        if (VerificarEspaciosAgregarArea()) {
            boolean exito = dao.insertarArea(
                    new Area(0,
                            txtNombreArea.getText(),
                            cbEstadoArea.isSelected())
            );
            if (exito) {
                MensajePersonalizado.Ver("EXITO AL INSERTAR", "Área insertada correctamente", Alert.AlertType.CONFIRMATION);
                limpiarCamposAgregarArea();
                configurar();
            } else {
                MensajePersonalizado.Ver("ERROR", "Error al insertar el área", Alert.AlertType.ERROR);
            }

        } else {
            MensajePersonalizado.Ver("INFORMACIÓN INCOMPLETA", "Los campos son requeridos, verifique que la información este completa", Alert.AlertType.INFORMATION);

        }
    }

    public void actualizar() {
        var area = tblAreas.getSelectionModel().getSelectedItem();

        if (area != null && area.getId() != 0) {
            if (VerificarEspaciosAgregarArea()) {
                area.setNombre(txtNombreArea.getText());
                area.setStatus(cbEstadoArea.isSelected());
                boolean exito = dao.actualizarArea(
                        area
                );
                if (exito) {
                    MensajePersonalizado.Ver("EXITO AL ACTUALIZAR", "Área actualizada correctamente", Alert.AlertType.CONFIRMATION);
                    limpiarCamposAgregarArea();
                    configurar();
                } else {
                    MensajePersonalizado.Ver("ERROR", "Error al actualizar la área", Alert.AlertType.ERROR);
                }

            } else {
                MensajePersonalizado.Ver("INFORMACIÓN INCOMPLETA", "Los campos son requeridos, verifique que la información este completa", Alert.AlertType.INFORMATION);

            }
        } else {
            MensajePersonalizado.Ver("SELECCIONE", "Debe de seleccionar una área a modificar", Alert.AlertType.WARNING);
        }
    }

    public boolean VerificarEspaciosAgregarArea() {
        if (!txtNombreArea.getText().trim().equals("")
                && !cbEstadoArea.getText().trim().equals("")) {

            return true;
        } else {
            return false;
        }
    }

    public void limpiarCamposAgregarArea() {
        txtNombreArea.setText("");
        cbEstadoArea.setSelected(true);
    }


    public void cargarDatos() {
        var area = tblAreas.getSelectionModel().getSelectedItem();

        if (area != null && area.getId() != 0) {
            txtNombreArea.setText(area.getNombre());
            cbEstadoArea.setSelected(area.isStatus());
        }
    }

    
    //    private void filtrarAreas() {
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
    private void btnGuardarArea(ActionEvent event) {
        guardar();
    }

    @FXML
    private void btnActualizarArea(ActionEvent event) {
        actualizar();
    }

    @FXML
    private void OnFiltrarArea(KeyEvent event) {
    }

    @FXML
    private void btnCargarDatos(ActionEvent event) {
        cargarDatos();
    }

}
