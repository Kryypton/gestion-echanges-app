import java.io.IOException;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ChangePlan {

    Platform platform ;

    @FXML
    TextField login;
    @FXML
    TextField password;

    ListView listeTeenager; // Liste des Teenager
    ListView listeAppariement; // Liste des Appariement

    

    public void initialize() {
        System.out.println("Initialisation...");
    }

    public void Charge(Stage stage, String fichier, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL fxmlFileUrl = getClass().getResource(fichier);
        if (fxmlFileUrl == null) {
            System.out.println("Impossible de charger le fichier fxml");
            System.exit(-1);
        }
        loader.setLocation(fxmlFileUrl);
        Parent root = loader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    //      CHANGEMENT DE PAGE
    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void ConnexionValid(ActionEvent event) throws IOException {
        if(login.getCharacters().toString().equals("root") && password.getCharacters().toString().equals("root")){
            Charge(Start.stage,"ihm/PrototypageHD/accueil.fxml","Accueil");
        }else if(login.getCharacters().toString().equals("eleve") && password.getCharacters().toString().equals("eleve")){
            Charge(Start.stage,"ihm/PrototypageHD/Formulaire.fxml","Formulaire");
        }
    }


    public void SelectConnexion(ActionEvent event) throws IOException {
        Charge(Start.stage,"ihm/PrototypageHD/Connexion.fxml","Connexion");
    }

    public void SelectAccueil(ActionEvent event) throws IOException {
        Charge(Start.stage,"ihm/PrototypageHD/accueil.fxml","Accueil");
    }

    public void SelectGestionEleve(ActionEvent event) throws IOException {
        Charge(Start.stage,"ihm/PrototypageHD/GestionEleve.fxml","GestionEleve");
    }

    public void SelectGestionAppariemment(ActionEvent event) throws IOException {
        Charge(Start.stage,"ihm/PrototypageHD/GestionAppariement.fxml","GestionAppariemment");
    }

    public void SelectCreationAppariement(ActionEvent event) throws IOException {
        Charge(Start.stage,"ihm/PrototypageHD/CreationAppariement.fxml","CreationAppariement");
    }

    public void SelectCreeAppariement(ActionEvent event) throws IOException {
        Charge(Start.stage,"ihm/PrototypageHD/CreeAppariement.fxml","CreeAppariement");
    }

    public void SelectReappariementEleve(ActionEvent event) throws IOException {
        Charge(Start.stage,"ihm/PrototypageHD/ReappariementEleve.fxml","ReappariementEleve");
    }



    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    //      ACTION
    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    // public void ImportationEleve(ActionEvent event) throws IOException {
    //     Charge(Start.stage,"ReappariementEleve.fxml","ReappariementEleve");
    // }

    public void supprimerEleve(ActionEvent event) throws IOException {
        String eleve = listeTeenager.getSelectionModel().getSelectedItem().toString().split("\t")[0];
        Teenager t = platform.getIndexFromTeenager(Integer.parseInt(eleve));
        if(t != null){
            listeTeenager.getItems().remove(eleve);
            platform.removeTeenager(t);
            sauvgardePlateforme();
        }

        // Charge(Start.stage,"ReappariementEleve.fxml","ReappariementEleve"); Je sais pas a quoi sa sert cette ligne
    }

    public void sauvgardePlateforme(){ //// Cette doit permettre de sauvgarder la plateforme dans un fichier mais je sais pas le faire mdrrrrrrr signer Adham
        platform.exportTeenagers(null, null);

    
    }

    // public void SupprimerAppariment(ActionEvent event) throws IOException {
    //     Charge(Start.stage,"ReappariementEleve.fxml","ReappariementEleve");
    // }

    // public void ModifierEleve(ActionEvent event) throws IOException {
    //     Charge(Start.stage,"ReappariementEleve.fxml","ReappariementEleve");
    // }

    // public void CreeAppariement(ActionEvent event) throws IOException {
    //     weigth
    // }


}
