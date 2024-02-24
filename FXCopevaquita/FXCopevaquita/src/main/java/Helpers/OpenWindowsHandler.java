/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helpers;

import MainApp.App;
import java.io.IOException;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author alber
 * @author kim03
 */
public class OpenWindowsHandler {

    public static void AbrirVentanaRecuperarContrasenia(String fxml) {
        try {
            var second = App.loadFXML(fxml);

            Scene secondScene = new Scene(second);
            Stage secondStage = new Stage();

            secondStage.setTitle("Recuperación de contraseña");
            secondStage.initModality(Modality.WINDOW_MODAL);
            secondStage.initOwner(App.scene.getWindow());
            secondStage.setScene(secondScene);

            secondStage.showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void AbrirVentanaActividades(String fxml) {
        try {
            var second = App.loadFXML(fxml);

            Scene secondScene = new Scene(second);
            Stage secondStage = new Stage();

            secondStage.setTitle("Actividades");
            secondStage.initModality(Modality.WINDOW_MODAL);
            secondStage.initOwner(App.scene.getWindow());
            secondStage.setScene(secondScene);

            secondStage.showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void AbrirVentanaAreas(String fxml) {
        try {
            var second = App.loadFXML(fxml);

            Scene secondScene = new Scene(second);
            Stage secondStage = new Stage();

            secondStage.setTitle("Areas");
            secondStage.initModality(Modality.WINDOW_MODAL);
            secondStage.initOwner(App.scene.getWindow());
            secondStage.setScene(secondScene);

            secondStage.showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void AbrirVentanaTipoDeducciones(String fxml) {
        try {
            var second = App.loadFXML(fxml);

            Scene secondScene = new Scene(second);
            Stage secondStage = new Stage();

            secondStage.setTitle("Tipo de deducciones");
            secondStage.initModality(Modality.WINDOW_MODAL);
            secondStage.initOwner(App.scene.getWindow());
            secondStage.setScene(secondScene);

            secondStage.showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void AbrirVentanaAgregarEmpleado(String fxml) {
        try {
            var second = App.loadFXML(fxml);

            Scene secondScene = new Scene(second);
            Stage secondStage = new Stage();

            secondStage.setTitle("Agregar empleado");
            secondStage.initModality(Modality.WINDOW_MODAL);
            secondStage.initOwner(App.scene.getWindow());
            secondStage.setScene(secondScene);

            secondStage.showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void AbrirVentanaActualizarEmpleado(String fxml) {
        try {
            var second = App.loadFXML(fxml);

            Scene secondScene = new Scene(second);
            Stage secondStage = new Stage();

            secondStage.setTitle("Actualizar empleado");
            secondStage.initModality(Modality.WINDOW_MODAL);
            secondStage.initOwner(App.scene.getWindow());
            secondStage.setScene(secondScene);

            secondStage.showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void AbrirVentanaAgregarRegistroFamiliar(String fxml) {
        try {
            var second = App.loadFXML(fxml);

            Scene secondScene = new Scene(second);
            Stage secondStage = new Stage();

            secondStage.setTitle("Agregar registro familiar");
            secondStage.initModality(Modality.WINDOW_MODAL);
            secondStage.initOwner(App.scene.getWindow());
            secondStage.setScene(secondScene);

            secondStage.showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void AbrirVentanaActualizarRegistroFamiliar(String fxml) {
        try {
            var second = App.loadFXML(fxml);

            Scene secondScene = new Scene(second);
            Stage secondStage = new Stage();

            secondStage.setTitle("Actualizar registro familiar");
            secondStage.initModality(Modality.WINDOW_MODAL);
            secondStage.initOwner(App.scene.getWindow());
            secondStage.setScene(secondScene);

            secondStage.showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void AbrirVentanaAgregarDeduccion(String fxml) {
        try {
            var second = App.loadFXML(fxml);

            Scene secondScene = new Scene(second);
            Stage secondStage = new Stage();

            secondStage.setTitle("Insercion de deducciones");
            secondStage.initModality(Modality.WINDOW_MODAL);
            secondStage.initOwner(App.scene.getWindow());
            secondStage.setScene(secondScene);

            secondStage.showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void AbrirVentanaActualizarDeduccion(String fxml) {
        try {
            var second = App.loadFXML(fxml);

            Scene secondScene = new Scene(second);
            Stage secondStage = new Stage();

            secondStage.setTitle("Actualizacion de deducciones");
            secondStage.initModality(Modality.WINDOW_MODAL);
            secondStage.initOwner(App.scene.getWindow());
            secondStage.setScene(secondScene);

            secondStage.showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void AbrirVentanaAbonoDeduccion(String fxml) {
        try {
            var second = App.loadFXML(fxml);

            Scene secondScene = new Scene(second);
            Stage secondStage = new Stage();

            secondStage.setTitle("Abono de deducciones");
            secondStage.initModality(Modality.WINDOW_MODAL);
            secondStage.initOwner(App.scene.getWindow());
            secondStage.setScene(secondScene);

            secondStage.showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void AbrirVentanaActualizarContratos(String fxml) {
        try {
            var second = App.loadFXML(fxml);

            Scene secondScene = new Scene(second);
            Stage secondStage = new Stage();

            secondStage.setTitle("Actualizacion de contratos");
            secondStage.initModality(Modality.WINDOW_MODAL);
            secondStage.initOwner(App.scene.getWindow());
            secondStage.setScene(secondScene);

            secondStage.showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void AbrirVentanaAgregarContratos(String fxml) {
        try {
            var second = App.loadFXML(fxml);

            Scene secondScene = new Scene(second);
            Stage secondStage = new Stage();

            secondStage.setTitle("Insercion de contratos");
            secondStage.initModality(Modality.WINDOW_MODAL);
            secondStage.initOwner(App.scene.getWindow());
            secondStage.setScene(secondScene);

            secondStage.showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void AbrirVentanaGenerarPagos(String fxml) {
        try {
            var second = App.loadFXML(fxml);

            Scene secondScene = new Scene(second);
            Stage secondStage = new Stage();

            secondStage.setTitle("Generar pagos");
            secondStage.initModality(Modality.WINDOW_MODAL);
            secondStage.initOwner(App.scene.getWindow());
            secondStage.setScene(secondScene);

            secondStage.showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void AbrirVentanaAgregarVacaciones(String fxml) {
        try {
            var second = App.loadFXML(fxml);

            Scene secondScene = new Scene(second);
            Stage secondStage = new Stage();

            secondStage.setTitle("Agregar vacaciones");
            secondStage.initModality(Modality.WINDOW_MODAL);
            secondStage.initOwner(App.scene.getWindow());
            secondStage.setScene(secondScene);

            secondStage.showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void AbrirVentanaActualizarVacaciones(String fxml) {
        try {
            var second = App.loadFXML(fxml);

            Scene secondScene = new Scene(second);
            Stage secondStage = new Stage();

            secondStage.setTitle("Actualizar vacaciones");
            secondStage.initModality(Modality.WINDOW_MODAL);
            secondStage.initOwner(App.scene.getWindow());
            secondStage.setScene(secondScene);

            secondStage.showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void AbrirVentanaAgregarIncapacidades(String fxml) {
        try {
            var second = App.loadFXML(fxml);

            Scene secondScene = new Scene(second);
            Stage secondStage = new Stage();

            secondStage.setTitle("Agregar incapacidades");
            secondStage.initModality(Modality.WINDOW_MODAL);
            secondStage.initOwner(App.scene.getWindow());
            secondStage.setScene(secondScene);

            secondStage.showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void AbrirVentanaActualizarIncapacidades(String fxml) {
        try {
            var second = App.loadFXML(fxml);

            Scene secondScene = new Scene(second);
            Stage secondStage = new Stage();

            secondStage.setTitle("Actualizar incapacidades");
            secondStage.initModality(Modality.WINDOW_MODAL);
            secondStage.initOwner(App.scene.getWindow());
            secondStage.setScene(secondScene);

            secondStage.showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void AbrirVentanaAgregarBitacoraEmpleado(String fxml) {
        try {
            var second = App.loadFXML(fxml);

            Scene secondScene = new Scene(second);
            Stage secondStage = new Stage();

            secondStage.setTitle("Agregar bitacora empleado");
            secondStage.initModality(Modality.WINDOW_MODAL);
            secondStage.initOwner(App.scene.getWindow());
            secondStage.setScene(secondScene);

            secondStage.showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void AbrirVentanaActualizarBitacoraEmpleado(String fxml) {
        try {
            var second = App.loadFXML(fxml);

            Scene secondScene = new Scene(second);
            Stage secondStage = new Stage();

            secondStage.setTitle("Actualizar bitacora empleado");
            secondStage.initModality(Modality.WINDOW_MODAL);
            secondStage.initOwner(App.scene.getWindow());
            secondStage.setScene(secondScene);

            secondStage.showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void AbrirVentanaAgregarBitacoraSocio(String fxml) {
        try {
            var second = App.loadFXML(fxml);

            Scene secondScene = new Scene(second);
            Stage secondStage = new Stage();

            secondStage.setTitle("Agregar bitacora socio");
            secondStage.initModality(Modality.WINDOW_MODAL);
            secondStage.initOwner(App.scene.getWindow());
            secondStage.setScene(secondScene);

            secondStage.showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void AbrirVentanaActualizarBitacoraSocio(String fxml) {
        try {
            var second = App.loadFXML(fxml);

            Scene secondScene = new Scene(second);
            Stage secondStage = new Stage();

            secondStage.setTitle("Actualizar bitacora socio");
            secondStage.initModality(Modality.WINDOW_MODAL);
            secondStage.initOwner(App.scene.getWindow());
            secondStage.setScene(secondScene);

            secondStage.showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void AbrirVentanaAgregarBitaAsistencia(String fxml) {
        try {
            var second = App.loadFXML(fxml);

            Scene secondScene = new Scene(second);
            Stage secondStage = new Stage();

            secondStage.setTitle("Agregar bitacora asistencia");
            secondStage.initModality(Modality.WINDOW_MODAL);
            secondStage.initOwner(App.scene.getWindow());
            secondStage.setScene(secondScene);

            secondStage.showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
     public static void AbrirVentanaActualizarBitaAsistencia(String fxml) {
        try {
            var second = App.loadFXML(fxml);

            Scene secondScene = new Scene(second);
            Stage secondStage = new Stage();

            secondStage.setTitle("Actualizar bitacora asistencia");
            secondStage.initModality(Modality.WINDOW_MODAL);
            secondStage.initOwner(App.scene.getWindow());
            secondStage.setScene(secondScene);

            secondStage.showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
