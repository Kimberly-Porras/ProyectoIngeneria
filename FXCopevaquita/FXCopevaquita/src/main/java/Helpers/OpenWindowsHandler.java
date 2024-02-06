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
        public static void openSecondWindows(String fxml) {
        try {
            var second = App.loadFXML(fxml);

            Scene secondScene = new Scene(second);
            Stage secondStage = new Stage();

            secondStage.setTitle("Segunda Ventana");
            secondStage.initModality(Modality.WINDOW_MODAL);
            secondStage.initOwner(App.scene.getWindow());
            secondStage.setScene(secondScene);

            secondStage.showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
        
}
