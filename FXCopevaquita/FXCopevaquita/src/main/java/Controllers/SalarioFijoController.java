/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import DAO.PlanillaSociosDAO;
import Models.Empleado;
import Models.PlanillaSocios;
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
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author User
 */
public class SalarioFijoController implements Initializable {

    @FXML
    private TextField filtrarEmpleado;
    @FXML
    private TableView<PlanillaSocios> tblSalarioFijo;
    @FXML
    private TableColumn<PlanillaSocios, String> colCedula;
    @FXML
    private TableColumn<PlanillaSocios, String> colNombre;
    @FXML
    private TableColumn<PlanillaSocios, String> colMonto;
    @FXML
    private TableColumn<PlanillaSocios, String> colEstado;

    /**
     * Initializes the controller class.
     */
    
    ObservableList<Empleado> ObservableEmpleado = FXCollections.observableArrayList();
    ObservableList<PlanillaSocios> Observableplanilla = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        configurar();
        cargarPlanillasSocios();
    }    

    public void configurar() {
        colCedula.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmpleado()));
        colNombre.setCellValueFactory(cellData -> new SimpleStringProperty(GetNombreCompleto(cellData.getValue().getEmpleado())));
        colMonto.setCellValueFactory(new PropertyValueFactory<>("monto"));
        colEstado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().isStatus()? "Pendiente" : "Cancelado"));
    }
    
     private String GetNombreCompleto(String cedula) {
        Optional<Empleado> empleadoOptional = ObservableEmpleado.stream()
                .filter(x -> x.getCedula().equals(cedula))
                .findFirst();

        return empleadoOptional.map(Empleado::getNombreCompleto).orElse("");
    }
     
     public void cargarPlanillasSocios() {
        var ObservableIncapacidad
                = FXCollections.observableArrayList(new PlanillaSociosDAO().obtenerListaPlanillaSocios());
        tblSalarioFijo.setItems(ObservableIncapacidad);
    }
     
     private void filtrarPlanillas() {
        if (filtrarEmpleado.getText() != null && !filtrarEmpleado.getText().trim().equals("")) {
            Predicate<PlanillaSocios> pPlanillaSocios = x
                    -> x.getEmpleado().toLowerCase().contains(filtrarEmpleado.getText().toLowerCase());
            Predicate<Empleado> pEmpleado = x
                    -> x.getCedula().toLowerCase().contains(filtrarEmpleado.getText().toLowerCase())
                    || x.getNombre().toLowerCase().contains(filtrarEmpleado.getText().toLowerCase())
                    || x.getApellidos().toLowerCase().contains(filtrarEmpleado.getText().toLowerCase())
                    || x.getNombreCompleto().toLowerCase().contains(filtrarEmpleado.getText().toLowerCase());
            var listaTemporal = Observableplanilla.filtered((x) -> pEmpleado.test(Get(x.getEmpleado())) || pPlanillaSocios.test(x));
            tblSalarioFijo.setItems(listaTemporal);
        } else {
            tblSalarioFijo.setItems(Observableplanilla);
        }
    }
    
    private Empleado Get(String cedula) {
        return ObservableEmpleado.filtered(x -> x.getCedula().equals(cedula)).get(0);
    }
    @FXML
    private void OnAgregar(ActionEvent event) {
    }

    @FXML
    private void OnActualizar(ActionEvent event) {
    }

    @FXML
    private void PresioanrEnter(KeyEvent event) {
        filtrarPlanillas();
    }

    @FXML
    private void OnPresionarCualquierLado(MouseEvent event) {
    }
    
}
