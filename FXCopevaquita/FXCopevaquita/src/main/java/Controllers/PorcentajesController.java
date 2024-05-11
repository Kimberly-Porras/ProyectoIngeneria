<<<<<<< HEAD:FXCopevaquita/FXCopevaquita/target/classes/Controllers/PorcentajesController.java
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import Models.PorcentajeRebajos;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
/**
 * FXML Controller class
 *
 * @author alber
 * @author kim03
 */

public class PorcentajesController implements Initializable {


    @FXML
    private CheckBox cbEstadoActividad;
    @FXML
    private TableView<PorcentajeRebajos> tblPorcentajes;
    @FXML
    private TableColumn<PorcentajeRebajos, String> colIdActividad;
    @FXML
    private TableColumn<PorcentajeRebajos, String> colPorcentaje;
    @FXML
    private TableColumn<PorcentajeRebajos, String> colEstadoActividad;
    @FXML
    private TextField txtPorcentaje;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void btnActualizarActividad(ActionEvent event) {
    }

    @FXML
    private void btnCargarDatos(ActionEvent event) {
    }

}
=======
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controllers;

import DAO.PorcentajeRebajosDAO;
import Models.PorcentajeRebajos;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
/**
 * FXML Controller class
 *
 * @author alber
 * @author kim03
 */

public class PorcentajesController implements Initializable {


    @FXML
    private TableView<PorcentajeRebajos> tblPorcentajes;
    @FXML
    private TableColumn<PorcentajeRebajos, String> colPorcentaje;
    @FXML
     private TableColumn<PorcentajeRebajos, String> colId;
    @FXML
    private TableColumn<PorcentajeRebajos, String> colEstadoPorcentaje;
    @FXML
    private TextField txtPorcentaje;
    @FXML
    private CheckBox cbEstadoPorcentaje;
    
    PorcentajeRebajosDAO daoRebajos = new PorcentajeRebajosDAO();
    /**
     * Initializes the controller class.
     */
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void cargar(){
    List<PorcentajeRebajos> listaTipoDeduccion = daoRebajos.obtenerTodos();
        ObservableList<PorcentajeRebajos> TipoDeduccionObservable = FXCollections.observableArrayList(listaTipoDeduccion);
        tblPorcentajes.setItems(TipoDeduccionObservable);
        
        
    };
    
    @FXML
    private void btnActualizarActividad(ActionEvent event) {
    }

    @FXML
    private void btnCargarDatos(ActionEvent event) {
    }

}
>>>>>>> 2f182e7950cc8cb0f07e61ded1a1deadab83bcdc:FXCopevaquita/FXCopevaquita/src/main/java/Controllers/PorcentajesController.java
