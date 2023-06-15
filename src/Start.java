import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Start extends Application {

        public static Stage stage;

        public void start(Stage stage) throws IOException {
                Start.stage = stage;
                FXMLLoader loader = new FXMLLoader();
                URL fxmlFileUrl = getClass().getResource("ihm/PrototypageHD/Connexion.fxml");
                if (fxmlFileUrl == null) {
                        System.out.println("Impossible de charger le fichier fxml");
                        System.exit(-1);
                }
                loader.setLocation(fxmlFileUrl);
                Parent root = loader.load();

                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Connexion");
                stage.show();
        }

        public static void main(String[] args) {
                Application.launch(args);
        }
}