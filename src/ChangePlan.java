import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.AccessibleAttribute;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ChangePlan {

    Platform platform ;



    RadioButton gender, pairGender, history;
    ListView<Country> country;
    TextField name , forename , hobbies;
    CheckBox allergieAnimaux , hostAnimal , hostNuts  , hostVegetarian , guestNuts  , guestVegetarian;
    DatePicker birthDate;

    /*
     * Pour la page de connexion
     */

    @FXML
    TextField login;
    @FXML
    TextField password;

    /*
     * Pour les pages gestion élèves et appariement
     */
    @FXML
    TableView<Eleve> infoTeen;

    @FXML
    TableColumn<Eleve, String> userNameCol;
    @FXML
    TableColumn<Eleve, String> userFornameCol;
    @FXML
    TableColumn<Eleve, Country> userCountry;
    @FXML
    TableColumn<Eleve, LocalDate> userDate;
    @FXML
    TableColumn<Eleve, Criterion> userGAnimal;
    @FXML
    TableColumn<Eleve, Criterion> userHAnimal;
    @FXML
    TableColumn<Eleve, Criterion> userGFood;
    @FXML
    TableColumn<Eleve, Criterion> userHFood;
    @FXML
    TableColumn<Eleve, Criterion> userHobbies;
    @FXML
    TableColumn<Eleve, Criterion> userGender;
    @FXML
    TableColumn<Eleve, Criterion> userPairGender;
    @FXML
    TableColumn<Eleve, Criterion> userHistory;


    @FXML
    ListView<Teenager> listeTeenager; // Liste des Teenager
    @FXML
    ListView<Map<Teenager,Teenager>> listeAppariement; // Liste des Appariement

    /*
     * Pour la page de formulaire
     */

    @FXML
    TextField formName,formForname,formCountry,formHobbies;
    @FXML
    DatePicker formDate;
    @FXML
    RadioButton formAnimalYesV;
    @FXML
    RadioButton formAnimalNoV;
    @FXML
    RadioButton formAnimalYesH;
    @FXML
    RadioButton formAnimalNoH;
    @FXML
    RadioButton formGenderMale;
    @FXML
    RadioButton formGenderFemale;
    @FXML
    RadioButton formGenderOther;
    @FXML
    RadioButton formOtherGenderMale;
    @FXML
    RadioButton formOtherGenderFemale;
    @FXML
    RadioButton formOtherGenderOther;
    @FXML
    RadioButton formOtherGenderNull;
    @FXML
    RadioButton formHistorySame;
    @FXML
    RadioButton formHistoryOther;
    @FXML
    RadioButton formHistoryNull;
    @FXML
    CheckBox formNotNonuts,formVegetarian,formOtherNotNonuts,formOtherVegetarian;

    /*Criterion AnimalV;
    Criterion AnimalH;
    Criterion History;
    Criterion gender;
    Criterion otherGender;*/
    

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

    public void SelectYesFormAnimal(ActionEvent event) throws IOException {
        formAnimalYesV.setSelected(true);
        formAnimalNoV.setSelected(false);
    }

    public void SelectNoFormAnimal(ActionEvent event) throws IOException {
        formAnimalYesV.setSelected(false);
        formAnimalNoV.setSelected(true);
    }

    public void SelectYesFormAnimalH(ActionEvent event) throws IOException {
        formAnimalYesH.setSelected(true);
        formAnimalNoH.setSelected(false);
    }

    public void SelectNoFormAnimalH(ActionEvent event) throws IOException {
        formAnimalYesH.setSelected(false);
        formAnimalNoH.setSelected(true);
    }

    public void SelectMaleFormGender(ActionEvent event) throws IOException {
        formGenderMale.setSelected(true);
        formGenderFemale.setSelected(false);
        formGenderOther.setSelected(false);
    }

    public void SelectFemaleFormGender(ActionEvent event) throws IOException {
        formGenderMale.setSelected(false);
        formGenderFemale.setSelected(true);
        formGenderOther.setSelected(false);
    }

    public void SelectOtherFormGender(ActionEvent event) throws IOException {
        formGenderMale.setSelected(false);
        formGenderFemale.setSelected(false);
        formGenderOther.setSelected(true);
    }

    public void SelectMaleFormOtherGender(ActionEvent event) throws IOException {
        formOtherGenderMale.setSelected(true);
        formOtherGenderFemale.setSelected(false);
        formOtherGenderOther.setSelected(false);
        formOtherGenderNull.setSelected(false);
    }

    public void SelectFemaleFormOtherGender(ActionEvent event) throws IOException {
        formOtherGenderMale.setSelected(false);
        formOtherGenderFemale.setSelected(true);
        formOtherGenderOther.setSelected(false);
        formOtherGenderNull.setSelected(false);
    }

    public void SelectOtherFormOtherGender(ActionEvent event) throws IOException {
        formOtherGenderMale.setSelected(false);
        formOtherGenderFemale.setSelected(false);
        formOtherGenderOther.setSelected(true);
        formOtherGenderNull.setSelected(false);
    }

    public void SelectNullFormOtherGender(ActionEvent event) throws IOException {
        formOtherGenderMale.setSelected(false);
        formOtherGenderFemale.setSelected(false);
        formOtherGenderOther.setSelected(false);
        formOtherGenderNull.setSelected(true);
    }

    public void SelectSameFormHistory(ActionEvent event) throws IOException {
        formHistorySame.setSelected(true);
        formHistoryOther.setSelected(false);
        formHistoryNull.setSelected(false);
    }

    public void SelectOtherFormHistory(ActionEvent event) throws IOException {
        formHistorySame.setSelected(false);
        formHistoryOther.setSelected(true);
        formHistoryNull.setSelected(false);
    }

    public void SelectNullFormHistory(ActionEvent event) throws IOException {
        formHistorySame.setSelected(false);
        formHistoryOther.setSelected(false);
        formHistoryNull.setSelected(true);
    }
    

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    //      FORMULAIRE
    /////////////////////////////////////////////////////////////////////////////////////////////////////////





    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    //      ACTION
    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    

    ///////////////////////////////////////////////////
    //      Verification de la validité des champs
    ///////////////////////////////////////////////////

    public boolean champsValid(TextField t){
        String regex = "[a-zA-Z]+";
        String text = t.getText();
        if(text.isEmpty()) return false;
        if (text.matches(regex)) return false;
        if (text.length() > 20) return false;
        return true;
    }

    public boolean dateValid(DatePicker t){
        LocalDate date = t.getValue();
        if(date == null) return false;
        if( date.isAfter(LocalDate.now())) return false;
        return true;
    }

    public boolean isChecked(CheckBox t){
        if(t.isSelected()) return true;
        return false;
    }

    public boolean isChoised(RadioButton t){
        if(t.isSelected()) return true;
        return false;
    }

    public boolean isSelected (ListView<Country> t){
        if(t.getSelectionModel().getSelectedItem() == null) return false;
        return true;
    }

    ///////////////////////////////////////////////////
    //  Crétation des critères
    ///////////////////////////////////////////////////

    public Criterion animalAllergy(){
        if(allergieAnimaux.isSelected()){
            return new Criterion( "yes" , CriterionName.GUEST_ANIMAL_ALLERGY);
        }
        return new Criterion( "no" , CriterionName.GUEST_ANIMAL_ALLERGY);
    }

    public Criterion genreTeenager(){
        if(gender.idProperty().toString().equals("formGenderFemale")){
        return  new Criterion("female", CriterionName.GENDER);
        }
        if(gender.idProperty().toString().equals("formGenderMale")){
            return new Criterion("male", CriterionName.GENDER);
        }
        return new Criterion("other", CriterionName.GENDER);
        //TODO : A voir si on met un null ou un other
    }

    public Criterion haveAnimal(){
        if(hostAnimal.isSelected()){
            return new Criterion("yes" , CriterionName.HOST_HAS_ANIMAL);
        }
        return new Criterion("no" , CriterionName.HOST_HAS_ANIMAL);
    }


    public Criterion regimeAlimentaire(String food){
        if(food.length() > 0){
        return new Criterion( food.substring(0, food.length() -1 ), CriterionName.GUEST_FOOD);
        }
        return new Criterion( null , CriterionName.GUEST_FOOD);
    }



    public Criterion history(){
        if(history.idProperty().toString().equals("formHistorySame")){
            return new Criterion("same", CriterionName.HISTORY);
        }
        return new Criterion("other", CriterionName.HISTORY);
    }

    public Criterion hobbiesTeenager(){
        return new Criterion(hobbies.getText(), CriterionName.HOBBIES);
    }

    public Criterion pairGender(){
        if(pairGender.idProperty().toString().equals("formOtherGenderFemale")){
            return new Criterion("female", CriterionName.PAIR_GENDER);
        }
        if(pairGender.idProperty().toString().equals("formOtherGenderMale")){
            return new Criterion("male", CriterionName.PAIR_GENDER);
        }
        return new Criterion("other", CriterionName.PAIR_GENDER);
        //TODO : A voir si on met un null ou un other
    }

    ///////////////////////////////////////////////////



    public void addNewTeenager(ActionEvent event) throws IOException{
        Teenager teenager;
        String food = "";

        if(champsValid(name) && champsValid(forename) && dateValid(birthDate) && isChoised(gender) && isSelected(country)){
            
            teenager = new Teenager(name.getText(), forename.getText(), birthDate.getValue(), country.getSelectionModel().getSelectedItem());

            teenager.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(), animalAllergy());
            teenager.addCriterion(CriterionName.GENDER.name() , genreTeenager());

            if(isChecked(guestNuts)){ food = food + "nonuts,";}
            if(isChecked(guestVegetarian)){ food = food + "vegetarian,";}
            if(food.length() > 0){ teenager.addCriterion(CriterionName.GUEST_FOOD.name(), regimeAlimentaire(food));}

            teenager.addCriterion(CriterionName.HOST_HAS_ANIMAL.name() , haveAnimal());

            food = "";

            if(isChecked(hostNuts)){ food = food + "nonuts,";}
            if(isChecked(hostVegetarian)){ food = food + "vegetarian,";}
            if(food.length() > 0){ teenager.addCriterion(CriterionName.HOST_FOOD.name(), regimeAlimentaire(food));}

            teenager.addCriterion(CriterionName.HISTORY.name(), history());
            teenager.addCriterion(CriterionName.HOBBIES.name(), hobbiesTeenager());

            if(isChoised(pairGender)){ teenager.addCriterion(CriterionName.PAIR_GENDER.name(), pairGender()); }

            platform.addTeenager(teenager);
            try {
                sauvegardePlateforme();
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Erreur lors de la sauvegarde de la plateforme !");
            }

        }
    }


    public void supprimerEleve(ActionEvent event) throws IOException {
        String eleve = listeTeenager.getSelectionModel().getSelectedItem().toString().split("\t")[0];
        Teenager t = platform.getIndexFromTeenager(Integer.parseInt(eleve));
        if(t != null){
            listeTeenager.getItems().remove(eleve);
            platform.removeTeenager(t);
            sauvegardePlateforme();
        }
        // Charge(Start.stage,"ReappariementEleve.fxml","ReappariementEleve"); Je sais pas a quoi sa sert cette ligne
    }

    public void sauvegardePlateforme() throws IOException{ //// Cette doit permettre de sauvgarder la plateforme dans un fichier mais je sais pas le faire mdrrrrrrr signer Adham
        Platform.exportTeenagers(null, null);
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

        // public void ImportationEleve(ActionEvent event) throws IOException {
    //     Charge(Start.stage,"ReappariementEleve.fxml","ReappariementEleve");
    // }



     public void afficherEleve(ActionEvent event) throws IOException{

        Teenager t1, t2, t3, t4, t5;
        int id1, id2, id3, id4 ,id5;
        String name1, name2, name3, name4 ,name5;
        String forname1, forname2, forname3, forname4, forname5;
        String gender1, gender2, gender3, gender4, gender5;
        Country countryName1, countryName2, countryName3, countryName4, countryName5;
        LocalDate birthDate1, birthDate2, birthDate3, birthDate4, birthDate5;
        Map<String, Criterion> requirements1, requirements2, requirements3, requirements4, requirements5;
        id1 = 1;
        id2 = 2;
        id3 = 3;
        id4 = 4;
        id5 = 5;
        name1 = "Alice";
        name2 = "Bruno";
        name3 = "Clément";
        name4 = "Dylan";
        name5 = "Eva";
        forname1 = "Brown";
        forname2 = "Dumont";
        forname3 = "Garnier";
        forname4 = "Lefebvre";
        forname5 = "Martin";
        countryName1 = Country.FRANCE;
        countryName2 = Country.GERMANY;
        countryName3 = Country.SPAIN;
        countryName4 = Country.ITALY;
        countryName5 = Country.FRANCE;
        birthDate1 = LocalDate.parse("2000-01-01");
        birthDate2 = LocalDate.parse("2002-08-04");
        birthDate3 = LocalDate.parse("2001-11-21");
        birthDate4 = LocalDate.parse("2003-03-15");
        birthDate5 = LocalDate.parse("2001-12-03");

        Criterion estAlergique = new Criterion("yes", CriterionName.GUEST_ANIMAL_ALLERGY);
        Criterion estPasAlergique = new Criterion("no", CriterionName.GUEST_ANIMAL_ALLERGY);
        Criterion aUnAnimal = new Criterion("yes", CriterionName.HOST_HAS_ANIMAL);
        Criterion aPasAnimal = new Criterion("no", CriterionName.HOST_HAS_ANIMAL);
        Criterion possedeVege = new Criterion("végétarien", CriterionName.HOST_FOOD);
        Criterion possedeSport = new Criterion("sport", CriterionName.HOST_FOOD);
        Criterion posseDeTout = new Criterion("none", CriterionName.HOST_FOOD);
        Criterion mangeTout = new Criterion("none", CriterionName.GUEST_FOOD);  
        Criterion mangeVege = new Criterion("végétarien", CriterionName.GUEST_FOOD);
        Criterion mangeSport = new Criterion("sport", CriterionName.GUEST_FOOD); 
        Criterion saisieIncorrect = new Criterion("pasBien", CriterionName.NUMERIC);
        Criterion biologie = new Criterion("biologie", CriterionName.HOBBIES);

        requirements2 = new HashMap<String, Criterion>();
        requirements2.put(CriterionName.GUEST_ANIMAL_ALLERGY.name(), estPasAlergique);
        requirements2.put(CriterionName.HOST_HAS_ANIMAL.name(), aUnAnimal);
        requirements2.put(CriterionName.HOST_FOOD.name(), possedeVege);
        requirements2.put(CriterionName.GUEST_FOOD.name(), mangeVege);

        requirements3 = new HashMap<String, Criterion>();
        requirements3.put(CriterionName.GUEST_ANIMAL_ALLERGY.name(), estAlergique);
        requirements3.put(CriterionName.HOST_HAS_ANIMAL.name(), aPasAnimal);
        requirements3.put(CriterionName.HOST_FOOD.name(), possedeSport);
        requirements3.put(CriterionName.GUEST_FOOD.name(), mangeSport);
        
        t1 = new Teenager(id1, name1, forname1, birthDate1, countryName1);
        t2 = new Teenager(id2, name2, forname2, birthDate2, countryName2, requirements2);
        t3 = new Teenager(id3, name3, forname3, birthDate3, countryName3, requirements3);
        t4 = new Teenager(id4, forname4, forname4, birthDate4, countryName4);
        t5 = new Teenager(id5, name5, forname5, birthDate5, countryName5);

        t1.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(), estPasAlergique);
        t1.addCriterion(CriterionName.HOST_HAS_ANIMAL.name(), aPasAnimal);
        t1.addCriterion(CriterionName.HOST_FOOD.name(), posseDeTout);
        t1.addCriterion(CriterionName.GUEST_FOOD.name(), mangeTout);
        t1.addCriterion(CriterionName.GUEST_FOOD.name(), biologie);

        t4.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(), estPasAlergique);
        t4.addCriterion(CriterionName.HOST_HAS_ANIMAL.name(), aPasAnimal);
        t4.addCriterion(CriterionName.HOST_FOOD.name(), saisieIncorrect);
        t4.addCriterion(CriterionName.GUEST_FOOD.name(), saisieIncorrect);

        t5.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(), estPasAlergique);
        t5.addCriterion(CriterionName.HOST_HAS_ANIMAL.name(), aPasAnimal);
        t5.addCriterion(CriterionName.HOST_FOOD.name(), saisieIncorrect);
        t5.addCriterion(CriterionName.GUEST_FOOD.name(), saisieIncorrect);
        t5.addCriterion(CriterionName.HOBBIES.name(), biologie);

        listeTeenager.getItems().add(listeTeenager.getItems().size(),t1);
        listeTeenager.scrollTo(t1);
        listeTeenager.edit(listeTeenager.getItems().size() - 1);
        listeTeenager.getItems().add(listeTeenager.getItems().size(),t2);
        listeTeenager.scrollTo(t2);
        listeTeenager.edit(listeTeenager.getItems().size() - 1);
        listeTeenager.getItems().add(listeTeenager.getItems().size(),t3);
        listeTeenager.scrollTo(t3);
        listeTeenager.edit(listeTeenager.getItems().size() - 1);
        listeTeenager.getItems().add(listeTeenager.getItems().size(),t4);
        listeTeenager.scrollTo(t4);
        listeTeenager.edit(listeTeenager.getItems().size() - 1);
    }

    public void afficherCase(MouseEvent event) throws IOException{
        //System.out.println(""+listeTeenager.getItems().get(listeTeenager.getSelectionModel().getSelectedIndex()));
        ObservableList<Eleve> list = infoTeen.getItems();
        // listeTeenager.getItems().get(listeTeenager.getSelectionModel().getSelectedIndex());
        //userFornameCol.cellValueFactoryProperty(new PropertyValueFactory<Teenager, String>(name));
        //infoTeen.setItems(list);

        // Teenager teen = listeTeenager.getItems().get(listeTeenager.getSelectionModel().getSelectedIndex());

        // userFornameCol.setCellValueFactory(new PropertyValueFactory<Teenager, String>("Prenom"));
        // userNameCol.setCellValueFactory(new PropertyValueFactory<Teenager, String>("Nom"));
        // userCountry.setCellValueFactory(new PropertyValueFactory<Teenager, Country>("Pays"));

        System.out.println("ok");

        Teenager teen1 = listeTeenager.getItems().get(0);
        Teenager teen2 = listeTeenager.getItems().get(1);
        Teenager teen3 = listeTeenager.getItems().get(2);
        Teenager teen4 = listeTeenager.getItems().get(3);

        System.out.println("ok");

        Eleve t1 = new Eleve(teen1);
        Eleve t2 = new Eleve(teen2);
        Eleve t3 = new Eleve(teen3);
        Eleve t4 = new Eleve(teen4);

        System.out.println(teen1.toString());

        list.addAll(t1,t2,t3,t4);

        System.out.println(list.toString());

        System.out.println(infoTeen.toString());

        infoTeen.setItems(list);

        System.out.println(infoTeen.getColumns().get(0).toString());
        System.out.println("fin");
    }


    // A FAIRE VITE 
    /*
     * afficherCaseAffectation
     * affectation
     * 
     */
}