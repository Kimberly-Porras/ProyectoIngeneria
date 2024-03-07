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
    private Label link_config;
    @FXML
    private Label link_salariosFijos;
    @FXML
    private Label link_asistencia;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    private void LoadNode(String fxml) throws IOException {
        var node = App.loadFXML(fxml);
        container_root.getChildren().clear();
        container_root.getChildren().add(node);
    }

    @FXML
    private void OnAbrirInfoPersonal(MouseEvent event) {
        try {
            LoadNode("/views/Empleados");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void OnAbrirRegistroFamiliar(MouseEvent event) {
        try {
            LoadNode("/views/RegistroFamiliar");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void OnAbrirDeducciones(MouseEvent event) {
        try {
            LoadNode("/views/Deducciones");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void OnAbrirContratos(MouseEvent event) {
        try {
            LoadNode("/views/Contratos");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void OnAbrirPagos(MouseEvent event) {
        try {
            LoadNode("/views/Pagos");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void OnAbrirVacaciones(MouseEvent event) {
        try {
            LoadNode("/views/Vacaciones");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void OnAbrirAguinaldos(MouseEvent event) {
    }

    @FXML
    private void OnAbrirIncapacidades(MouseEvent event) {
        try {
            LoadNode("/views/Incapacidades");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void OnAbrirEmpleados(MouseEvent event) {
        try {
            LoadNode("/views/BitacoraEmpleado");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void OnAbrirSocios(MouseEvent event) {
       try {
            LoadNode("/views/BitacoraSocio");
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
    }

    private void OnAbrirPlanilla(MouseEvent event) {
        try {
            LoadNode("/views/Planilla");
        } catch (Exception ex) {
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

    @FXML
    private void OnAbrirSalariosFijos(MouseEvent event) {
        try {
            LoadNode("/views/SalarioFijo");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void OnAbrirAsistencia(MouseEvent event) {
        try {
            LoadNode("/views/Asistencia");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void OnCerrar(MouseEvent event) {
        
    }
}
