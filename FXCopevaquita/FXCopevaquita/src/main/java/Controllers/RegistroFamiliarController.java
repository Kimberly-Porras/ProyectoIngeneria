/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import DAO.EmpleadoDAO;
import DAO.ParentezcoDAO;
import Helpers.OpenWindowsHandler;
import Models.Empleado;
import Models.Parentezco;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author alber
 * @author kim03
 */
public class RegistroFamiliarController implements Initializable {

    @FXML
    private TextField txtFiltrarParentezco;
    @FXML
    private TableView<Parentezco> tblParentezco;
    @FXML
    private TableColumn<Parentezco, String> colCedula;
    @FXML
    private TableColumn<Parentezco, String> colNombre;
    @FXML
    private TableColumn<Parentezco, String> colApellidos;
    @FXML
    private TableColumn<Parentezco, String> colParentezco;
    @FXML
    private TableColumn<Parentezco, String> colSexo;
    @FXML
    private TableColumn<Parentezco, String> colFechaNacimiento;
    @FXML
    private TableColumn<Parentezco, String> colEstado;
    @FXML
    private TableColumn<Parentezco, String> colContactos;
    @FXML
    private TableColumn<Parentezco, String> colEmpleado;
    @FXML
    private ComboBox<String> cbx_status;

    /**
     * Initializes the controller class.
     */
    ObservableList<Empleado> ObservableEmpleado = FXCollections.observableArrayList();
    ObservableList<Parentezco> ObservableParentezco = FXCollections.observableArrayList();
    ObservableList<String> observableStatus = FXCollections.observableArrayList("Activo", "Inactivo");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        configurar();
        cargarParentezcos(true, false);
    }

    private void configurar() {
        colEmpleado.setCellValueFactory(cellData -> {
            var empleado = new EmpleadoDAO().obtenerEmpleadoPorCedula(cellData.getValue().getEmpleado());
            if (empleado == null) {
                return new SimpleStringProperty("No disponible");
            }
            return new SimpleStringProperty(empleado.getNombreCompleto());
        });
        colCedula.setCellValueFactory(new PropertyValueFactory<Parentezco, String>("cedula"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Parentezco, String>("nombre"));
        colApellidos.setCellValueFactory(new PropertyValueFactory<Parentezco, String>("apellidos"));
        colParentezco.setCellValueFactory(new PropertyValueFactory<Parentezco, String>("parentezco"));
        colSexo.setCellValueFactory(new PropertyValueFactory<Parentezco, String>("sexo"));
        colFechaNacimiento.setCellValueFactory(new PropertyValueFactory<Parentezco, String>("fechaNacimiento"));
        colContactos.setCellValueFactory(new PropertyValueFactory<Parentezco, String>("contactoEmergencia"));
        colEstado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().isStatus() ? "Activo" : "Inactivo"));

        cbx_status.setItems(observableStatus);
        cbx_status.setOnAction(event -> {
            var value = cbx_status.getValue();
            if (value.equals("Activo")) {
                cargarParentezcos(true, true);
            } else {
                cargarParentezcos(false, true);
            }
        });
    }

    public void cargarParentezcos(boolean status, boolean filtro) {
        if (filtro) {
            ObservableParentezco
                    = FXCollections.observableArrayList(new ParentezcoDAO().obtenerListaParentezco())
                            .filtered(empl -> empl.isStatus() == status);
            tblParentezco.setItems(ObservableParentezco);
        } else {
            ObservableParentezco
                    = FXCollections.observableArrayList(new ParentezcoDAO().obtenerListaParentezco());
            tblParentezco.setItems(ObservableParentezco);
        }
    }

    private void filtrarParentezco() {

        if (txtFiltrarParentezco.getText() != null && !txtFiltrarParentezco.getText().trim().equals("")) {
            
            var listaTemporal = ObservableParentezco.filtered((x) -> {
                var empleado = new EmpleadoDAO().obtenerEmpleadoPorCedula(x.getEmpleado());
                
                return x.getCedula().toLowerCase().contains(txtFiltrarParentezco.getText().toLowerCase())
                        || x.getNombre().toLowerCase().contains(txtFiltrarParentezco.getText().toLowerCase())
                        || x.getApellidos().toLowerCase().contains(txtFiltrarParentezco.getText().toLowerCase())
                        || x.getNombreCompleto().toLowerCase().contains(txtFiltrarParentezco.getText().toLowerCase())
                        || x.getSexo().toLowerCase().contains(txtFiltrarParentezco.getText().toLowerCase())
                        || x.getContactoEmergencia().toLowerCase().contains(txtFiltrarParentezco.getText().toLowerCase())
                        || empleado.getNombreCompleto().toLowerCase().contains(txtFiltrarParentezco.getText().toLowerCase());
            });
            
            tblParentezco.setItems(listaTemporal);
        } else {
            cargarParentezcos(true, false);
        }
    }

    @FXML
    private void OnAgregar(ActionEvent event) {
        OpenWindowsHandler.AbrirVentanaAgregarRegistroFamiliar("/views/AgregarRegistroFamiliar");
    }

    @FXML
    private void OnActualizar(ActionEvent event) {
        OpenWindowsHandler.AbrirVentanaActualizarRegistroFamiliar("/views/ActualizarRegistroFamiliar");
    }

    @FXML
    private void OnFiltrar(KeyEvent event) {
        filtrarParentezco();
    }

    @FXML
    private void OnRefrescar(ActionEvent event) {
        cargarParentezcos(true, false);
    }
}
