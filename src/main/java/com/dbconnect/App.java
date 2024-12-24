package com.dbconnect;

import com.dbconnect.models.Equipe;
import com.dbconnect.models.Joueur;
import com.dbconnect.service.EquipeJoueurService;
import com.dbconnect.service.IEquipeJoueurService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * JavaFX App
 */
public class App extends Application {

  private static Scene scene;

  @Override
  public void start(Stage stage) throws IOException {
    scene = new Scene(loadFXML("joueur"), 640, 480);
    stage.setScene(scene);
    stage.show();
  }

  static void setRoot(String fxml) throws IOException {
    scene.setRoot(loadFXML(fxml));
  }

  private static Parent loadFXML(String fxml) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
    return fxmlLoader.load();
  }


  public static void main(String[] args) {
    launch();
  }



  void consoleTest(){
    IEquipeJoueurService service = new EquipeJoueurService();  // Service métier
    Scanner scanner = new Scanner(System.in);

    while (true) {
      // Affichage du menu
      System.out.println("=======================================");
      System.out.println("1. Ajouter une équipe");
      System.out.println("2. Ajouter un joueur");
      System.out.println("3. Afficher toutes les équipes");
      System.out.println("4. Afficher les joueurs d'une équipe");
      System.out.println("5. Rechercher une équipe par mot-clé");
      System.out.println("6. Supprimer une équipe");
      System.out.println("7. Supprimer un joueur");
      System.out.println("8. Quitter");
      System.out.print("Choisissez une option: ");

      int choice = scanner.nextInt();
      scanner.nextLine();  // Consommer la nouvelle ligne

      switch (choice) {
        case 1:
          // Ajouter une équipe
          System.out.print("Nom de l'équipe: ");
          String nomEquipe = scanner.nextLine();
          System.out.print("Ville de l'équipe: ");
          String villeEquipe = scanner.nextLine();
          service.ajouterEquipe(new Equipe(null,nomEquipe, villeEquipe));
          break;

        case 2:
          // Ajouter un joueur
          System.out.print("Nom du joueur: ");
          String nomJoueur = scanner.nextLine();
          System.out.print("Position du joueur: ");
          String positionJoueur = scanner.nextLine();
          System.out.print("Numéro du joueur: ");
          int numeroJoueur = scanner.nextInt();
          scanner.nextLine(); // Consommer la nouvelle ligne

          System.out.print("ID de l'équipe à laquelle ajouter le joueur: ");
          Long equipeId = scanner.nextLong();
          scanner.nextLine(); // Consommer la nouvelle ligne
          service.ajouterJoueur(new Joueur(nomJoueur, positionJoueur, numeroJoueur, new Equipe(equipeId,null,null)));
          break;

        case 3:
          // Afficher toutes les équipes
          List<Equipe> equipes = service.afficherEquipes();
          System.out.println("Liste des équipes:");
          for (Equipe equipe : equipes) {
            System.out.println("ID: " + equipe.getId() + ", Nom: " + equipe.getNom() + ", Ville: " + equipe.getVille());
          }
          break;

        case 4:
          // Afficher les joueurs d'une équipe
          System.out.print("ID de l'équipe: ");
          long idEquipe = scanner.nextLong();
          scanner.nextLine(); // Consommer la nouvelle ligne

          List<Joueur> joueurs = service.afficherJoueurs(new Equipe(idEquipe,null,null));
          System.out.println("Joueurs de l'équipe:");
          for (Joueur joueur : joueurs) {
            System.out.println("Nom: " + joueur.getNom() + ", Position: " + joueur.getPosition() + ", Numéro: " + joueur.getNumero());
          }
          break;

        case 5:
          // Rechercher une équipe par mot-clé
          System.out.print("Mot-clé de recherche: ");
          String keyword = scanner.nextLine();
          List<Equipe> equipesRecherche = service.searchEquipe(keyword);
          System.out.println("Résultats de la recherche:");
          for (Equipe equipeSearch : equipesRecherche) {
            System.out.println("ID: " + equipeSearch.getId() + ", Nom: " + equipeSearch.getNom() + ", Ville: " + equipeSearch.getVille());
          }
          break;

        case 6:
          // Supprimer une équipe
          System.out.print("ID de l'équipe à supprimer: ");
          long equipeIdSupprimer = scanner.nextLong();
          scanner.nextLine(); // Consommer la nouvelle ligne
          service.supprimerEquipe(equipeIdSupprimer);
          break;

        case 7:
          // Supprimer un joueur
          System.out.print("ID du joueur à supprimer: ");
          long joueurIdSupprimer = scanner.nextLong();
          scanner.nextLine(); // Consommer la nouvelle ligne
          service.supprimerJoueur(joueurIdSupprimer);
          break;

        case 8:
          // Quitter l'application
          System.out.println("Au revoir !");
          scanner.close();
          return;

        default:
          System.out.println("Option invalide. Veuillez réessayer.");
          break;
      }
    }
  }

}