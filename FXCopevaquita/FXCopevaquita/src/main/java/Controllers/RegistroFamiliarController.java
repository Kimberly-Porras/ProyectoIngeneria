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
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author User
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

    /**
     * Initializes the controller class.
     */
    
    ObservableList<Empleado> ObservableEmpleado = FXCollections.observableArrayList();
    ObservableList<Parentezco> ObservableParentezco = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        configurar();
        cargarParentezcos();
    }

    private void configurar() {
        colEmpleado.setCellValueFactory(cellData -> {
            var empleado = new EmpleadoDAO().obtenerEmpleadoPorCedula(cellData.getValue().getEmpleado());
            if(empleado == null) {
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
    }
    
    public void cargarParentezcos() {
        var ObservableParentezco
                = FXCollections.observableArrayList(new ParentezcoDAO().obtenerListaParentezco());
        tblParentezco.setItems(ObservableParentezco);
    }
    
    
    private void filtrarParentezco() {
        if (txtFiltrarParentezco.getText() != null && !txtFiltrarParentezco.getText().trim().equals("")) {
            Predicate<Empleado> pEmpleado = x
                    -> x.getCedula().toLowerCase().contains(txtFiltrarParentezco.getText().toLowerCase())
                    || x.getNombre().toLowerCase().contains(txtFiltrarParentezco.getText().toLowerCase())
                    || x.getApellidos().toLowerCase().contains(txtFiltrarParentezco.getText().toLowerCase())
                    || x.getNombreCompleto().toLowerCase().contains(txtFiltrarParentezco.getText().toLowerCase());
            Predicate<Parentezco> pParentezco = x
                    -> x.getCedula().toLowerCase().contains(txtFiltrarParentezco.getText().toLowerCase())
                    || x.getNombre().toLowerCase().contains(txtFiltrarParentezco.getText().toLowerCase())
                    || x.getApellidos().toLowerCase().contains(txtFiltrarParentezco.getText().toLowerCase())
                    || x.getNombreCompleto().toLowerCase().contains(txtFiltrarParentezco.getText().toLowerCase());

            var listaTemporal = ObservableParentezco.filtered((x) -> pEmpleado.test(Get(x.getEmpleado())) || pParentezco.test(x));
            tblParentezco.setItems(listaTemporal);
        } else {
            tblParentezco.setItems(ObservableParentezco);
        }
    }
    
    private Empleado Get(String cedula) {
        return ObservableEmpleado.filtered(x -> x.getCedula().equals(cedula)).get(0);
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

}
