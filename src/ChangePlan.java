import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import javax.swing.JFileChooser;

import Criterion.Country;
import Criterion.Criterion;
import Criterion.CriterionName;
import Platform.Platform;
import Tennager.Teenager;
import graph.AffectationUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ChangePlan {
    public static Platform platform = new Platform();
    RadioButton gender, pairGender, history;
    SplitMenuButton country ;
    SplitMenuButton hostCountrySelected;
    SplitMenuButton visitorCountrySelected;
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


    @FXML
    TextField preferenceArea;
    @FXML
    TextField redibitoireArea;
    @FXML
    SplitMenuButton hoteMenu;
    @FXML
    SplitMenuButton visitorMenu;
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
    TableView<Eleve> infoHote;
    @FXML
    TableColumn<Eleve, String> hostName;
    @FXML
    TableColumn<Eleve, String> hostForname;
    @FXML
    SplitMenuButton hostCountry;
    @FXML
    TableColumn<Eleve, Criterion> hostHobbies;
    @FXML
    TableColumn<Eleve, Criterion> hostGender;
    

    @FXML
    TableView<Eleve> infoVisiteur;
    @FXML
    TableColumn<Eleve, String> visitorName;
    @FXML
    TableColumn<Eleve, String> visitorForname;
    @FXML
    SplitMenuButton visitorCountry;
    @FXML
    TableColumn<Eleve, Criterion> visitorHobbies;
    @FXML
    TableColumn<Eleve, Criterion> visitorGender;


    @FXML
    ListView<Teenager> listeTeenager = new ListView<>(); // Liste des Teenager
    Teenager selecTeenager;


    //@FXML
    //ListView<Map<Teenager,Teenager>> listeAppariement = new ListView<>(); // Liste des Appariement
    @FXML
    ListView<Map<Teenager, Teenager>> listeAppariement = new ListView<>();
    Map<Teenager,Teenager> selectAppariement;

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
        visitorMenu = (SplitMenuButton) loader.getNamespace().get("visitorMenu");
        hoteMenu = (SplitMenuButton) loader.getNamespace().get("hoteMenu");
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

    public void preferenceSelect(ActionEvent event) {
    }

    public void redibitoireSelect(ActionEvent event) {
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

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    //      Action Formulaire
    /////////////////////////////////////////////////////////////////////////////////////////////////////////

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

    public void hoteSelect(ActionEvent event) {
        MenuItem menuItem = (MenuItem) event.getSource();
        String countryName = menuItem.getText();
        hoteMenu.setText(countryName);
        this.hostCountry = hoteMenu;
    }

    public void visitorSelect(ActionEvent event) {
        MenuItem menuItem = (MenuItem) event.getSource();
        String countryName = menuItem.getText();
        visitorMenu.setText(countryName);
        this.visitorCountry = visitorMenu;
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




    public void supprimerEleve(ActionEvent event) throws IOException{
        platform.removeTeenager(selecTeenager);
        afficherEleve(event);
    }

    public void sauvegardePlateforme() throws IOException{
       Platform.exportTeenagers(platform.getTeenagerArrayList() , "res/teenagerList.csv");
       Platform.exportCompatibleTeenager(selectAppariement, "res/compatibleTeenagerList.csv");
    }

    public void supprimerAppariment(ActionEvent event) throws IOException {
        if(selectAppariement != null){
            for(Teenager k: selectAppariement.keySet()){
                platform.removeCompatibleTeenager(k,platform.getCompatibleTeenagers().get(k));
            }
            afficherAppariement(event);
        }
        
    }

    // public void modifierEleve(ActionEvent event) throws IOException {
    //     Charge(Start.stage,"ReappariementEleve.fxml","ReappariementEleve");
    // }

    public void genererAppariement(ActionEvent event) throws IOException {
        platform.setCompatibleTeenagers(AffectationUtil.listAreteToListTeen(AffectationUtil.affectation(platform.getTeenagerList(),Country.FRANCE,Country.GERMANY)));
    }

    public void importationEleve(ActionEvent event) throws IOException {
        JFileChooser dialogue = new JFileChooser(".");
		PrintWriter sortie;
		File fichier;

		if (dialogue.showOpenDialog(null) ==  JFileChooser.APPROVE_OPTION) {
			fichier = dialogue.getSelectedFile();
			sortie = new PrintWriter(new FileWriter(fichier.getPath(), true));
			sortie.close();
            
            platform.setTeenagerList(Platform.importListTeenagers(fichier));
		}
        
    }

    public void importationAppariement(ActionEvent event) throws IOException{
        JFileChooser dialogue = new JFileChooser(".");
		PrintWriter sortie;
		File fichier;

		if (dialogue.showOpenDialog(null) ==  JFileChooser.APPROVE_OPTION) {
			fichier = dialogue.getSelectedFile();
			sortie = new PrintWriter(new FileWriter(fichier.getPath(), true));
			sortie.close();
            
            platform.setCompatibleTeenagers(Platform.importCompatibleTeenagers(fichier));
		}
    }

    public void afficherEleve(ActionEvent event) throws IOException{
        List<Teenager> t = platform.getTeenagerList();

        listeTeenager.getItems().clear();

        for(Teenager teen: t){
            listeTeenager.getItems().add(listeTeenager.getItems().size(),teen);
            listeTeenager.scrollTo(teen);
            listeTeenager.edit(listeTeenager.getItems().size() - 1);
        }
    }

    public void afficherEleveCase(MouseEvent event) throws IOException{
        //crée une ObservableList (on en a besoin)
        ObservableList<Eleve> data = FXCollections.observableArrayList();

        //permet de récuperer le teeneger selectionner dans la listeView
        selecTeenager = listeTeenager.getItems().get(listeTeenager.getSelectionModel().getSelectedIndex());

        //creation de eleve a partir de teenager (servent juste a contourne le requirement)
        Eleve t1 = new Eleve(selecTeenager);
        data.add(t1);


        ////////////// affecte les colonne /////////////////////
        userNameCol.setCellValueFactory(new PropertyValueFactory<Eleve, String>("name"));
        userFornameCol.setCellValueFactory(new PropertyValueFactory<Eleve, String>("forname"));
        userCountry.setCellValueFactory(new PropertyValueFactory<Eleve, Country>("countryName"));
        userDate.setCellValueFactory(new PropertyValueFactory<Eleve, LocalDate>("birthDate"));

        userGAnimal.setCellValueFactory(new PropertyValueFactory<Eleve, Criterion>("GuestHanimal"));
        userHAnimal.setCellValueFactory(new PropertyValueFactory<Eleve, Criterion>("HostAnimal"));
        userGFood.setCellValueFactory(new PropertyValueFactory<Eleve, Criterion>("GuestFood"));
        userHFood.setCellValueFactory(new PropertyValueFactory<Eleve, Criterion>("HostFood"));

        userHobbies.setCellValueFactory(new PropertyValueFactory<Eleve, Criterion>("Hobbies"));
        userGender.setCellValueFactory(new PropertyValueFactory<Eleve, Criterion>("Gender"));
        userPairGender.setCellValueFactory(new PropertyValueFactory<Eleve, Criterion>("PairGender"));
        userHistory.setCellValueFactory(new PropertyValueFactory<Eleve, Criterion>("History"));

        // mettre les valeur dans le tableView (prend en param ObservableList)
        infoTeen.setItems(data);
    }

    /* public void afficherAppariement(ActionEvent event) throws IOException{
        Map<Teenager, Teenager> t = platform.getCompatibleTeenagers();

        listeAppariement.getItems().clear();

        String s;

        for(Teenager teen: t.keySet()){
            s = "";
            s += teen +";"+platform.getCompatibleTeenagers().get(teen);
            listeAppariement.getItems().add(listeAppariement.getItems().size(),s);
            listeAppariement.scrollTo(s);
            listeAppariement.edit(listeAppariement.getItems().size() - 1);
        }
    }*/

    /*public void afficherAppariement(ActionEvent event) throws IOException{
        Map<Teenager, Teenager> t = platform.getCompatibleTeenagers();
        Map<Teenager, Teenager> nt;
        ObservableList<String> obl = FXCollections.observableArrayList();

        listeAppariement.getItems().clear();

        String s;
        for(Teenager teen: t.keySet()){
            // System.out.println(teen);
            // nt = new HashMap<Teenager, Teenager>();
            // nt.put(teen,t.get(teen));
            s = "";
            s += teen +";"+platform.getCompatibleTeenagers().get(teen);
            obl.add(s);
            //nt.clear();
        }
        listeAppariement.setItems(obl);
        System.out.println(listeAppariement.getItems());
    }*/

    public void afficherAppariement(ActionEvent event) throws IOException{
        Map<Teenager, Teenager> t = platform.getCompatibleTeenagers();
        Map<Teenager, Teenager> nt;
        ObservableList<Map<Teenager, Teenager>> obl = FXCollections.observableArrayList();

        listeAppariement.getItems().clear();

        String s;
        for(Teenager teen: t.keySet()){
            // System.out.println(teen);
            nt = new HashMap<Teenager, Teenager>();
            nt.put(teen,t.get(teen));
            obl.add(nt);
        }
        listeAppariement.setItems(obl);
        System.out.println(listeAppariement.getItems());
    }

    public void afficherAppariementCase(MouseEvent event) throws IOException{
        selectAppariement = listeAppariement.getItems().get(listeAppariement.getSelectionModel().getSelectedIndex());

        ObservableList<Eleve> Hostdata = FXCollections.observableArrayList();
        ObservableList<Eleve> Visitordata = FXCollections.observableArrayList();

        Eleve hote;
        Eleve visitor;

        for(Teenager teen: selectAppariement.keySet()){
            hote = new Eleve(teen);
            visitor = new Eleve(selectAppariement.get(teen));
            Hostdata.add(hote);
            Visitordata.add(visitor);
        }

        

        hostName.setCellValueFactory(new PropertyValueFactory<Eleve, String>("name"));
        hostForname.setCellValueFactory(new PropertyValueFactory<Eleve, String>("forname"));
        hostCountry.setCellValueFactory(new PropertyValueFactory<Eleve, Country>("countryName"));
        hostHobbies.setCellValueFactory(new PropertyValueFactory<Eleve, Criterion>("Hobbies"));
        hostGender.setCellValueFactory(new PropertyValueFactory<Eleve, Criterion>("Gender"));

        visitorName.setCellValueFactory(new PropertyValueFactory<Eleve, String>("name"));
        visitorForname.setCellValueFactory(new PropertyValueFactory<Eleve, String>("forname"));
        visitorCountry.setCellValueFactory(new PropertyValueFactory<Eleve, Country>("countryName"));
        visitorHobbies.setCellValueFactory(new PropertyValueFactory<Eleve, Criterion>("Hobbies"));
        visitorGender.setCellValueFactory(new PropertyValueFactory<Eleve, Criterion>("Gender"));

        infoHote.setItems(Hostdata);
        infoVisiteur.setItems(Visitordata);
    }

}