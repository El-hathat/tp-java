package com.dbconnect;

import java.io.IOException;

import com.dbconnect.Dao.EquipeDAOImpl;
import com.dbconnect.Dao.JoueurDAOImpl;
import com.dbconnect.models.Equipe;
import com.dbconnect.models.Joueur;
import javafx.fxml.FXML;



import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.List;

public class PrimaryController {

    @FXML
    private TableView<Equipe> equipesTable;
    @FXML
    private TableColumn<Equipe, String> colNomEquipe;
    @FXML
    private TableColumn<Equipe, String> colVilleEquipe;

    @FXML
    private TableView<Joueur> joueursTable;
    @FXML
    private TableColumn<Joueur, String> colNomJoueur;
    @FXML
    private TableColumn<Joueur, String> colPositionJoueur;
    @FXML
    private TableColumn<Joueur, Integer> colNumeroJoueur;

    @FXML
    private TextField searchField;
    @FXML
    private TextField equipeNomField;
    @FXML
    private TextField equipeVilleField;
    @FXML
    private TextField joueurNomField;
    @FXML
    private TextField joueurPositionField;
    @FXML
    private TextField joueurNumeroField;

    private EquipeDAOImpl equipeDAO = new EquipeDAOImpl();
    private JoueurDAOImpl joueurDAO = new JoueurDAOImpl();

    // Méthode pour rechercher une équipe par mot-clé
    @FXML
    private void handleSearch() {
        String keyword = searchField.getText();
        List<Equipe> equipes = equipeDAO.findByKeyword(keyword);
        equipesTable.getItems().setAll(equipes);
    }

    // Méthode pour ajouter une équipe
    @FXML
    private void handleAddEquipe() {
        String nom = equipeNomField.getText();
        String ville = equipeVilleField.getText();
        Equipe equipe = new Equipe();
        equipe.setNom(nom);
        equipe.setVille(ville);
        equipeDAO.save(equipe);
        refreshEquipesTable();
    }

    // Méthode pour ajouter un joueur
    @FXML
    private void handleAddJoueur() {
        String nom = joueurNomField.getText();
        String position = joueurPositionField.getText();
        int numero = Integer.parseInt(joueurNumeroField.getText());

        // Sélectionner une équipe
        Equipe equipe = equipesTable.getSelectionModel().getSelectedItem();
        if (equipe != null) {
            Joueur joueur = new Joueur();
            joueur.setNom(nom);
            joueur.setPosition(position);
            joueur.setNumero(numero);
            joueur.setEquipe(equipe);
            joueurDAO.save(joueur);
            refreshJoueursTable(equipe);
        }
    }

    // Rafraîchir la table des équipes
    private void refreshEquipesTable() {
        List<Equipe> equipes = equipeDAO.findAll();
        equipesTable.getItems().setAll(equipes);
    }

    // Rafraîchir la table des joueurs d'une équipe
    private void refreshJoueursTable(Equipe equipe) {
        List<Joueur> joueurs = joueurDAO.findByEquipe(equipe);
        joueursTable.getItems().setAll(joueurs);
    }

    // Afficher les joueurs lorsqu'une équipe est sélectionnée
    @FXML
    private void handleEquipeClick(MouseEvent event) {
        Equipe equipe = equipesTable.getSelectionModel().getSelectedItem();
        if (equipe != null) {
            refreshJoueursTable(equipe);
        }
    }
}

