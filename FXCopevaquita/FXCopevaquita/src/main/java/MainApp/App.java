package MainApp;

import Gmail.RandomCodeGenerator;
import Clases.userSession.AppUser;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    public static Scene scene;
    public static AppUser appUser = null;

    public static void setUser(AppUser user) {
        App.appUser = user;
    }

    public static AppUser getUser() {
        return App.appUser;
    }

    private static String recuperationCode;

    public static String getCode() {
        return App.recuperationCode;
    }

    public static void setCode() {
        App.recuperationCode = RandomCodeGenerator.generateRandomCode(4);
    }

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("/views/Login"), 640, 480);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public interface ControllerConsumer<T> {
        void accept(T controller);
    }
    
    public static <T> Parent loadFXMLWithController(String fxml, ControllerConsumer<T> controllerConsumer) throws IOException {
        FXMLLoader fxmlLoader =  new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        Parent parent = fxmlLoader.load();
        T controller = fxmlLoader.getController();
        controllerConsumer.accept(controller);
        return parent;
    }
    
    public static void main(String[] args) {
        launch();
    }
    
}

