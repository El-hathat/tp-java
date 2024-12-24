package com.dbconnect.controller;

import com.dbconnect.Dao.EquipeDAOImpl;
import com.dbconnect.Dao.JoueurDAOImpl;
import com.dbconnect.models.Equipe;
import com.dbconnect.models.Joueur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class JoueurController implements Initializable {
    @FXML TableView<Joueur> tableJoueurs;
    @FXML TableColumn<Joueur,String> nom;
    @FXML TableColumn<Joueur,String> position;
    @FXML TableColumn<Joueur,Integer> numero;
    @FXML TableColumn<Joueur,Long> equipeID;
    @FXML TableColumn<Equipe,String> equipeNom;
    @FXML TableColumn<Equipe,String> ville;
    @FXML TableColumn<Joueur,Long> joueurID;
    @FXML TextField rechercherText;
    @FXML TextField nomText;
    @FXML TextField positionText;
    @FXML TextField numeroText;
    @FXML ComboBox<Long> equipeText;

    ObservableList<Joueur> joueursList= FXCollections.observableArrayList();
    ObservableList<Long> equipeList= FXCollections.observableArrayList();

    JoueurDAOImpl service=new JoueurDAOImpl();
    EquipeDAOImpl eqService=new EquipeDAOImpl();

    public void ajouterJ(ActionEvent actionEvent) {
    service.save(new Joueur(nomText.getText(),positionText.getText(),Integer.parseInt(numeroText.getText()),new Equipe(1L,null,null)));
    actualiser(service.findAll());
    }

    public void modifierJ(ActionEvent actionEvent) {
        Joueur selectedJoueur=tableJoueurs.getSelectionModel().getSelectedItem();
        selectedJoueur.setNom(nomText.getText());
        selectedJoueur.setPosition(positionText.getText());
        selectedJoueur.setNumero(Integer.parseInt(numeroText.getText()));
        selectedJoueur.setEquipe(new Equipe(1L,null,null));

        service.update(selectedJoueur);
        actualiser(service.findAll());
    }


    public void rechercherJ(ActionEvent actionEvent) {
        if(rechercherText.getText().length()>0) {
            actualiser(service.findByKeyword(rechercherText.getText()));
        }else{
            actualiser(service.findAll());
        }
    }

    public void supprimerJ(ActionEvent actionEvent) {
        Joueur selectedJoueur=tableJoueurs.getSelectionModel().getSelectedItem();
        Alert alr=new Alert(Alert.AlertType.WARNING);
        alr.setTitle("Warning");
        alr.setContentText("aucun ligne selectionne");
        if(selectedJoueur==null){alr.show();}else{
            alr=new Alert(Alert.AlertType.CONFIRMATION);
            alr.setTitle("Affirmation");
            alr.setContentText("voulez-vous vraiment supprimer ce joueur");
            Optional<ButtonType> result = alr.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                service.deleteById(selectedJoueur.getId());
                actualiser(service.findAll());
            }
            }

    }



    List<Long> extarctIdFromEquipes(){
        List<Long> lst=new ArrayList<>();
        for (Equipe eq:eqService.findAll()) {
            lst.add(eq.getId());
        }
        return lst;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        actualiser(service.findAll());
        equipeList.addAll(extarctIdFromEquipes());
        equipeText.setItems(equipeList);



        tableJoueurs.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                Joueur selectedJoueur=tableJoueurs.getSelectionModel().getSelectedItem();
                nomText.setText(selectedJoueur.getNom());
                positionText.setText(selectedJoueur.getPosition());
                numeroText.setText(selectedJoueur.getNumero()+"");

            }
        });

    }

    void actualiser( List<Joueur> lst){
        joueursList.clear();
        joueursList.addAll(lst);
        joueurID.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        position.setCellValueFactory(new PropertyValueFactory<>("position"));
        numero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        equipeID.setCellValueFactory(new PropertyValueFactory<>("equipe"));
        tableJoueurs.setItems(joueursList);
    }
}
