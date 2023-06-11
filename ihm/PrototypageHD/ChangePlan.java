package ihm.PrototypageHD;

import java.io.IOException;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChangePlan {

    Stage stage;

    public void initialize() {
        System.out.println("Initialisation...");
    }

    public void Charge(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL fxmlFileUrl = getClass().getResource("Connexion.fxml");
        if (fxmlFileUrl == null) {
            System.out.println("Impossible de charger le fichier fxml");
            System.exit(-1);
        }
        loader.setLocation(fxmlFileUrl);
        Parent root = loader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("FXML demo");
        stage.show();
    }

    public void pressedConnexion(ActionEvent event) {

    }

    public void pressedButtonMoins(ActionEvent event) {
    }
}
