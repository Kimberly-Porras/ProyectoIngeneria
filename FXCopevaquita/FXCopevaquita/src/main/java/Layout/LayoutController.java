package Layout;

import DAO.ActividadDAO;
import MainApp.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 *
 * @author alber
 * @author kim03
 */

public class LayoutController implements Initializable {

    @FXML
    private Pane container_root;
    @FXML
    private Label link_infoPersonal;
    @FXML
    private Label link_registroFamiliar;
    @FXML
    private Label link_deducciones;
    @FXML
    private Label link_contratos;
    @FXML
    private Label link_pagos;
    @FXML
    private Label link_vacaciones;
    @FXML
    private Label link_aguinaldo;
    @FXML
    private Label link_incapacidades;
    @FXML
    private Label link_empleados;
    @FXML
    private Label link_socios;
    @FXML
    private Label link_planilla;
    @FXML
    private Label link_config;

    private void LoadNode(String fxml) throws IOException {
        var node = App.loadFXML(fxml);
        container_root.getChildren().clear();
        container_root.getChildren().add(node);
    }

    @FXML
    private void OnAbrirInfoPersonal(MouseEvent event) {
        try {
            LoadNode("/views/secondary");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void OnAbrirConfiguracion(MouseEvent event) {
        try {
            LoadNode("/views/Configuracion");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("consultando...");
        ActividadDAO ado = new ActividadDAO();
        var all = ado.obtenerTodos();
        System.out.println("todos: " + all.size());
    }

}
