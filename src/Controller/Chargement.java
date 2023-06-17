package Controller;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SplitMenuButton;
import javafx.stage.Stage;

public class Chargement {
    public void Charge(Stage stage, String fichier, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL fxmlFileUrl = getClass().getResource(fichier);
        if (fxmlFileUrl == null) {
            System.out.println("Impossible de charger le fichier fxml");
            System.exit(-1);
        }
        loader.setLocation(fxmlFileUrl);
        Parent root = loader.load();

        formCountryList = (SplitMenuButton) loader.getNamespace().get("formCountryList");
        // Platform plateform = new Platform();
        // File file = new File("res/TeenagerList.csv");
        // plateform.importListTeenagers(file);
        // for (Teenager t: plateform.getTeenagerList()) {
        //         listeTeenager.getItems().add(t.toString());
        //     }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();
    }
}
