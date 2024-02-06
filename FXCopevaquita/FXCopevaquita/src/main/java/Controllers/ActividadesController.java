/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

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
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        configurar();
    }

    public void configurar() {
        colIdActividad.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombreActividad.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colEstadoActividad.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus() == 1 ? "Activo" : "Inactivo"));

        List<Actividad> listaActividad = dao.obtenerTodos();
        ObservableList<Actividad> ActividadObservable = FXCollections.observableArrayList(listaActividad);
        tblActividades.setItems(ActividadObservable);
    }

    @FXML
    private void btnGuardarActividad(ActionEvent event) {
    }

    @FXML
    private void btnActualizarActividad(ActionEvent event) {
    }

    @FXML
    private void OnPresionarActividad(KeyEvent event) {
    }

    @FXML
    private void btnEditarActividad(ActionEvent event) {
    }

}
