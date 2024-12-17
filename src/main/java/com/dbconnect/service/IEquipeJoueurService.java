package com.dbconnect.service;

import com.dbconnect.models.Equipe;
import com.dbconnect.models.Joueur;

import java.util.List;

public interface IEquipeJoueurService {
    List<Equipe> afficherEquipes();
    void ajouterEquipe(Equipe equipe);
    void supprimerEquipe(Long id);
    List<Joueur> afficherJoueurs(Equipe equipe);
    void ajouterJoueur(Joueur joueur);
    void supprimerJoueur(Long id);
    Joueur imprimerInformationsJoueur(Long id);
    List<Equipe> searchEquipe(String nom);
}

