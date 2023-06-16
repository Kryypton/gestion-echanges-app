import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import Criterion.Country;
import Criterion.Criterion;
import Criterion.CriterionName;
import Platform.Platform;
import Tennager.Teenager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ChangePlan<Eleve> {
    public static Platform platform = new Platform();
    RadioButton gender, pairGender, history;
    SplitMenuButton country ;
    TextField name , forename , hobbies;
    CheckBox hostVegetarian , guestNuts  , hostNuts, guestVegetarian;
    DatePicker birthDate;
    RadioButton allergieAnimaux , hostAnimal;
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
    TableView<Teenager> infoTeen;
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
    ListView<Teenager> listeTeenager = new ListView<>(); // Liste des Teenager
    @FXML
    ListView<Map<Teenager,Teenager>> listeAppariement; // Liste des Appariement

    /*
     * Pour la page de formulaire
     */
    @FXML
    TextField formName;
    @FXML
    TextField formForname;
    @FXML
    TextField formCountry;
    @FXML
    TextField formHobbies;
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
    CheckBox formGuestNotNonuts;
    @FXML
    CheckBox formGuestVegetarian;
    @FXML
    CheckBox formHostNotNonuts;
    @FXML
    CheckBox formHostVegetarian;
    @FXML
    SplitMenuButton formCountryList;
    @FXML
    MenuItem menuItemGermany;
    @FXML
    MenuItem menuItemSpain;
    @FXML
    MenuItem menuItemFrance;
    @FXML
    MenuItem menuItemItaly;
    @FXML
    TextField nameEntry;
    @FXML
    TextField forenameEntry;
    @FXML
    TextField preferencesEntry;
    @FXML
    DatePicker birthEntry;

    /*Criterion AnimalV;
    Criterion AnimalH;
    Criterion History;
    Criterion gender;
    Criterion otherGender;*/
    

    // public void initialize() {
    //     System.out.println("Initialisation...");
    // }

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
        afficherEleve(event);
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

    public void SelectHistory(ActionEvent event) throws IOException {
        Charge(Start.stage,"ihm/PrototypageHD/historique.fxml","Historique");
    }

    public void SelectYesFormAnimal(ActionEvent event) throws IOException {
        formAnimalYesV.setSelected(true);
        formAnimalNoV.setSelected(false);
        this.allergieAnimaux = formAnimalYesV;
    }

    public void SelectNoFormAnimal(ActionEvent event) throws IOException {
        formAnimalYesV.setSelected(false);
        formAnimalNoV.setSelected(true);
        this.allergieAnimaux = formAnimalNoV;
    }

    public void SelectYesFormAnimalH(ActionEvent event) throws IOException {
        formAnimalYesH.setSelected(true);
        formAnimalNoH.setSelected(false);
        this.hostAnimal = formAnimalYesH;
    }

    public void SelectNoFormAnimalH(ActionEvent event) throws IOException {
        formAnimalYesH.setSelected(false);
        formAnimalNoH.setSelected(true);
        this.hostAnimal = formAnimalNoH;
    }

    public void SelectMaleFormGender(ActionEvent event) throws IOException {
        formGenderMale.setSelected(true);
        formGenderFemale.setSelected(false);
        formGenderOther.setSelected(false);
        this.gender = formGenderMale;
    }

    public void SelectFemaleFormGender(ActionEvent event) throws IOException {
        formGenderMale.setSelected(false);
        formGenderFemale.setSelected(true);
        formGenderOther.setSelected(false);
        this.gender = formGenderFemale;
    }

    public void SelectOtherFormGender(ActionEvent event) throws IOException {
        formGenderMale.setSelected(false);
        formGenderFemale.setSelected(false);
        formGenderOther.setSelected(true);
        this.gender = formGenderOther;
    }

    public void SelectMaleFormOtherGender(ActionEvent event) throws IOException {
        formOtherGenderMale.setSelected(true);
        formOtherGenderFemale.setSelected(false);
        formOtherGenderOther.setSelected(false);
        formOtherGenderNull.setSelected(false);
        this.pairGender = formOtherGenderMale;
    }

    public void SelectFemaleFormOtherGender(ActionEvent event) throws IOException {
        formOtherGenderMale.setSelected(false);
        formOtherGenderFemale.setSelected(true);
        formOtherGenderOther.setSelected(false);
        formOtherGenderNull.setSelected(false);
        this.pairGender = formOtherGenderFemale;
    }

    public void SelectOtherFormOtherGender(ActionEvent event) throws IOException {
        formOtherGenderMale.setSelected(false);
        formOtherGenderFemale.setSelected(false);
        formOtherGenderOther.setSelected(true);
        formOtherGenderNull.setSelected(false);
        this.pairGender = formOtherGenderOther;
    }

    public void SelectNullFormOtherGender(ActionEvent event) throws IOException {
        formOtherGenderMale.setSelected(false);
        formOtherGenderFemale.setSelected(false);
        formOtherGenderOther.setSelected(false);
        formOtherGenderNull.setSelected(true);
        this.pairGender = formOtherGenderNull;
    }

    public void SelectSameFormHistory(ActionEvent event) throws IOException {
        formHistorySame.setSelected(true);
        formHistoryOther.setSelected(false);
        formHistoryNull.setSelected(false);
        this.history = formHistorySame;
    }

    public void SelectOtherFormHistory(ActionEvent event) throws IOException {
        formHistorySame.setSelected(false);
        formHistoryOther.setSelected(true);
        formHistoryNull.setSelected(false);
        this.history = formHistoryOther;
    }

    public void SelectNullFormHistory(ActionEvent event) throws IOException {
        formHistorySame.setSelected(false);
        formHistoryOther.setSelected(false);
        formHistoryNull.setSelected(true);
        this.history = formHistoryNull;
    }

    public void selectCountry(ActionEvent event) {
        MenuItem menuItem = (MenuItem) event.getSource();
        String countryName = menuItem.getText();
        formCountryList.setText(countryName);
        this.country = formCountryList;
    }

    public void nameEntryAction(ActionEvent event) {
        this.name = nameEntry;
    }

    public void forenameEntryAction(ActionEvent event) {
        this.forename = forenameEntry;
    }

    public void preferencesEntryAction(ActionEvent event) {
        this.hobbies = preferencesEntry;
    }

    public void birthEntryAction(ActionEvent event) {
        this.birthDate = birthEntry;
    }

    public void SelectFormGuestNotNonuts(ActionEvent event) {
        this.guestNuts = formGuestNotNonuts;
    }

    public void SelectFormGuestVegetarian(ActionEvent event) {
        this.guestVegetarian = formGuestVegetarian;
    } 

    public void SelectFormHostNotNonuts(ActionEvent event) {
        this.hostNuts = formHostNotNonuts;
    } 

    public void SelectFormHostVegetarian(ActionEvent event) {
        this.hostVegetarian = formHostVegetarian;
    } 
    /*public void FormGermanySelect(ActionEvent event) throws IOException {
        formGermany.setText("Allemagne");
        formFrance.setText("France");
        formSpain.setText("Spain");
        formItaly.setText("Italie");
    }
    
    public void FormFranceSelect(ActionEvent event) throws IOException {
        formGermany.setText("Germany");
        formFrance.setText("France");
        formSpain.setText("Spain");
        formItaly.setText("Italie");
    }

    public void FormSpainSelect(ActionEvent event) throws IOException {
        formGermany.setText("Germany");
        formFrance.setText("France");
        formSpain.setText("Spain");
        formItaly.setText("Italie");
    }

    public void FormItalieSelect(ActionEvent event) throws IOException {
        formGermany.setText("Germany");
        formFrance.setText("France");
        formSpain.setText("Spain");
        formItaly.setText("Italie");
    }*/

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    //      FORMULAIRE
    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    // public void AjoutEleveFormulaire(ActionEvent event) throws IOException {
    //     String name = formName.getCharacters().toString();
    //     String forname = formForname.getCharacters().toString();
    //     String countryName = formCountry.getCharacters().toString();
    //     LocalDate birthDate = formDate.getValue();
    //     Map<String, Criterion> requirements;

    //     Teenager teen = new Teenager(name,forname);
    // }





    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    //      ACTION
    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    

    ///////////////////////////////////////////////////
    //      Verification de la validité des champs
    ///////////////////////////////////////////////////

    public boolean champsValid(TextField t){
        if (t.getText() == null) return false;
        String regex = "[a-zA-Z]+";
        String text = t.getText();
        if(text.isEmpty()) return false;
        if (!text.matches(regex)) return false;
        if (text.length() > 20) return false;
        return true;
    }

    public boolean dateValid(DatePicker t){
        if (t == null) return false;
        LocalDate date = t.getValue();
        if(date == null) return false;
        if( date.isAfter(LocalDate.now())) return false;
        return true;
    }

    public boolean isChecked(CheckBox t){
        if (t == null) return false;
        if(t.isSelected()) return true;
        return false;
    }

    public boolean isChoised(RadioButton t){
        if (t == null) return false;
        if(t.isSelected()) return true;
        return false;
    }

    public boolean isSelected (SplitMenuButton t){
        if (t == null) return false;
        if(t.getText().equals("Pays")) return false;
        return true;
    }

    ///////////////////////////////////////////////////
    //  Crétation des critères
    ///////////////////////////////////////////////////

    /*public Criterion animalAllergy(){
        if(allergieAnimaux.isSelected()){
            return new Criterion( "yes" , CriterionName.GUEST_ANIMAL_ALLERGY);
        }
        return new Criterion( "no" , CriterionName.GUEST_ANIMAL_ALLERGY);
    }*/

    public Criterion genreTeenager(){
        if(gender.idProperty().getValue().toString().equals("formGenderFemale")){
        return  new Criterion("female", CriterionName.GENDER);
        }
        if(gender.idProperty().getValue().toString().equals("formGenderMale")){
            return new Criterion("male", CriterionName.GENDER);
        }
        return new Criterion("other", CriterionName.GENDER);
    }

    public Criterion haveAnimal(){
        if(hostAnimal.idProperty().getValue().toString().equals("formAnimalYesH")){
            return new Criterion("yes" , CriterionName.HOST_HAS_ANIMAL);
        }
        return new Criterion("no" , CriterionName.HOST_HAS_ANIMAL);
    }

    public Criterion haveAllergie(){
        if(allergieAnimaux.idProperty().getValue().toString().equals("formAnimalYesV")){
            return new Criterion("yes" , CriterionName.GUEST_ANIMAL_ALLERGY);
        } else if(allergieAnimaux.idProperty().getValue().toString().equals("formAnimalNoV")){
            return new Criterion("no" , CriterionName.GUEST_ANIMAL_ALLERGY);
        }
        return new Criterion("no" , CriterionName.GUEST_ANIMAL_ALLERGY);
    }

    public Criterion regimeAlimentaire(String food){
        if(food.length() > 0){
        return new Criterion( food.substring(0, food.length() -1 ), CriterionName.GUEST_FOOD);
        }
        return new Criterion( null , CriterionName.GUEST_FOOD);
    }



    public Criterion history(){
        if(history.idProperty().getValue().toString().equals("formHistorySame")){
            return new Criterion("same", CriterionName.HISTORY);
        }
        return new Criterion("other", CriterionName.HISTORY);
    }

    public Criterion hobbiesTeenager(){
        return new Criterion(hobbies.getText(), CriterionName.HOBBIES);
    }

    public Criterion pairGender(){
        if(pairGender.idProperty().getValue().toString().equals("formOtherGenderFemale")){
            return new Criterion("female", CriterionName.PAIR_GENDER);
        }
        if(pairGender.idProperty().toString().equals("formOtherGenderMale")){
            return new Criterion("male", CriterionName.PAIR_GENDER);
        }
        return new Criterion("other", CriterionName.PAIR_GENDER);
    }

    ///////////////////////////////////////////////////

    @FXML
    public void handleSendButtonAction(){
        Teenager teenager;
        String food = "";
        if(champsValid(name) && champsValid(forename) && dateValid(birthDate) && isChoised(gender) && isSelected(country)){
            if (platform.containsSame(name.getText(), forename.getText())) return;
            teenager = new Teenager(name.getText(), forename.getText(), birthDate.getValue(), Country.valueOf(country.getText().toUpperCase()));

            //teenager.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(), animalAllergy());
            teenager.addCriterion(CriterionName.GENDER.name() , genreTeenager());

            if(isChecked(guestNuts)){ food = food + "nonuts,";}
            if(isChecked(guestVegetarian)){ food = food + "vegetarian,";}
            if(food.length() > 0){ teenager.addCriterion(CriterionName.GUEST_FOOD.name(), regimeAlimentaire(food));}


        
            teenager.addCriterion(CriterionName.HOST_HAS_ANIMAL.name() , haveAnimal());
            teenager.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name() , haveAllergie());
            food = "";

            if(isChecked(hostNuts)){ food = food + "nonuts,";}
            if(isChecked(hostVegetarian)){ food = food + "vegetarian,";}
            if(food.length() > 0){ teenager.addCriterion(CriterionName.HOST_FOOD.name(), regimeAlimentaire(food));}

            teenager.addCriterion(CriterionName.HISTORY.name(), history());
            teenager.addCriterion(CriterionName.HOBBIES.name(), hobbiesTeenager());

            if(isChoised(pairGender)){ teenager.addCriterion(CriterionName.PAIR_GENDER.name(), pairGender()); }
            System.out.println(teenager.teenagerToString());
            platform.addTeenager(teenager);

            System.out.println(platform.getTeenagerList().toString());

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
       platform.exportTeenagers((ArrayList) platform.getTeenagerList() , "res/teenagerList.csv");
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

        // // Teenager t1, t2, t3, t4, t5;
        // // int id1, id2, id3, id4 ,id5;
        // // String name1, name2, name3, name4 ,name5;
        // // String forname1, forname2, forname3, forname4, forname5;
        // // String gender1, gender2, gender3, gender4, gender5;
        // // Country countryName1, countryName2, countryName3, countryName4, countryName5;
        // // LocalDate birthDate1, birthDate2, birthDate3, birthDate4, birthDate5;
        // // Map<String, Criterion> requirements1, requirements2, requirements3, requirements4, requirements5;
        // // id1 = 1;
        // // id2 = 2;
        // // id3 = 3;
        // // id4 = 4;
        // // id5 = 5;
        // // name1 = "Alice";
        // // name2 = "Bruno";
        // // name3 = "Clément";
        // // name4 = "Dylan";
        // // name5 = "Eva";
        // // forname1 = "Brown";
        // // forname2 = "Dumont";
        // // forname3 = "Garnier";
        // // forname4 = "Lefebvre";
        // // forname5 = "Martin";
        // // countryName1 = Country.FRANCE;
        // // countryName2 = Country.GERMANY;
        // // countryName3 = Country.SPAIN;
        // // countryName4 = Country.ITALY;
        // // countryName5 = Country.FRANCE;
        // // birthDate1 = LocalDate.parse("2000-01-01");
        // // birthDate2 = LocalDate.parse("2002-08-04");
        // // birthDate3 = LocalDate.parse("2001-11-21");
        // // birthDate4 = LocalDate.parse("2003-03-15");
        // // birthDate5 = LocalDate.parse("2001-12-03");

        // // Criterion estAlergique = new Criterion("yes", CriterionName.GUEST_ANIMAL_ALLERGY);
        // // Criterion estPasAlergique = new Criterion("no", CriterionName.GUEST_ANIMAL_ALLERGY);
        // // Criterion aUnAnimal = new Criterion("yes", CriterionName.HOST_HAS_ANIMAL);
        // // Criterion aPasAnimal = new Criterion("no", CriterionName.HOST_HAS_ANIMAL);
        // // Criterion possedeVege = new Criterion("végétarien", CriterionName.HOST_FOOD);
        // // Criterion possedeSport = new Criterion("sport", CriterionName.HOST_FOOD);
        // // Criterion posseDeTout = new Criterion("none", CriterionName.HOST_FOOD);
        // // Criterion mangeTout = new Criterion("none", CriterionName.GUEST_FOOD);  
        // // Criterion mangeVege = new Criterion("végétarien", CriterionName.GUEST_FOOD);
        // // Criterion mangeSport = new Criterion("sport", CriterionName.GUEST_FOOD); 
        // // Criterion saisieIncorrect = new Criterion("pasBien", CriterionName.NUMERIC);
        // // Criterion biologie = new Criterion("biologie", CriterionName.HOBBIES);

        // // requirements2 = new HashMap<String, Criterion>();
        // // requirements2.put(CriterionName.GUEST_ANIMAL_ALLERGY.name(), estPasAlergique);
        // // requirements2.put(CriterionName.HOST_HAS_ANIMAL.name(), aUnAnimal);
        // // requirements2.put(CriterionName.HOST_FOOD.name(), possedeVege);
        // // requirements2.put(CriterionName.GUEST_FOOD.name(), mangeVege);

        // // requirements3 = new HashMap<String, Criterion>();
        // // requirements3.put(CriterionName.GUEST_ANIMAL_ALLERGY.name(), estAlergique);
        // // requirements3.put(CriterionName.HOST_HAS_ANIMAL.name(), aPasAnimal);
        // // requirements3.put(CriterionName.HOST_FOOD.name(), possedeSport);
        // // requirements3.put(CriterionName.GUEST_FOOD.name(), mangeSport);
        
        // // t1 = new Teenager(id1, name1, forname1, birthDate1, countryName1);
        // // t2 = new Teenager(id2, name2, forname2, birthDate2, countryName2, requirements2);
        // // t3 = new Teenager(id3, name3, forname3, birthDate3, countryName3, requirements3);
        // // t4 = new Teenager(id4, forname4, forname4, birthDate4, countryName4);
        // // t5 = new Teenager(id5, name5, forname5, birthDate5, countryName5);

        // // t1.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(), estPasAlergique);
        // // t1.addCriterion(CriterionName.HOST_HAS_ANIMAL.name(), aPasAnimal);
        // // t1.addCriterion(CriterionName.HOST_FOOD.name(), posseDeTout);
        // // t1.addCriterion(CriterionName.GUEST_FOOD.name(), mangeTout);
        // // t1.addCriterion(CriterionName.GUEST_FOOD.name(), biologie);

        // // t4.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(), estPasAlergique);
        // // t4.addCriterion(CriterionName.HOST_HAS_ANIMAL.name(), aPasAnimal);
        // // t4.addCriterion(CriterionName.HOST_FOOD.name(), saisieIncorrect);
        // // t4.addCriterion(CriterionName.GUEST_FOOD.name(), saisieIncorrect);

        // // t5.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(), estPasAlergique);
        // // t5.addCriterion(CriterionName.HOST_HAS_ANIMAL.name(), aPasAnimal);
        // // t5.addCriterion(CriterionName.HOST_FOOD.name(), saisieIncorrect);
        // // t5.addCriterion(CriterionName.GUEST_FOOD.name(), saisieIncorrect);
        // // t5.addCriterion(CriterionName.HOBBIES.name(), biologie);

        // listeTeenager.getItems().add(listeTeenager.getItems().size(),t1);
        // listeTeenager.scrollTo(t1);
        // listeTeenager.edit(listeTeenager.getItems().size() - 1);
        // listeTeenager.getItems().add(listeTeenager.getItems().size(),t2);
        // listeTeenager.scrollTo(t2);
        // listeTeenager.edit(listeTeenager.getItems().size() - 1);
        // listeTeenager.getItems().add(listeTeenager.getItems().size(),t3);
        // listeTeenager.scrollTo(t3);
        // listeTeenager.edit(listeTeenager.getItems().size() - 1);
        // listeTeenager.getItems().add(listeTeenager.getItems().size(),t4);
        // listeTeenager.scrollTo(t4);
        // listeTeenager.edit(listeTeenager.getItems().size() - 1);

        
        Collection<Teenager> t = platform.getTeenagerList();

        listeTeenager.getItems().clear();

        for(Teenager teen: t){
            listeTeenager.getItems().add(listeTeenager.getItems().size(),teen);
            listeTeenager.scrollTo(teen);
            listeTeenager.edit(listeTeenager.getItems().size() - 1);
            // System.out.println(teen);
        }
    }

    public void afficherCase(MouseEvent event) throws IOException{
        infoTeen.getItems().setAll();
    }
}