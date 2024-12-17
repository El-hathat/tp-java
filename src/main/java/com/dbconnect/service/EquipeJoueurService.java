package com.dbconnect.service;

import com.dbconnect.Dao.EquipeDAO;
import com.dbconnect.Dao.EquipeDAOImpl;
import com.dbconnect.Dao.JoueurDAO;
import com.dbconnect.Dao.JoueurDAOImpl;
import com.dbconnect.models.Equipe;
import com.dbconnect.models.Joueur;

import java.util.List;

public class EquipeJoueurService implements IEquipeJoueurService {

    private EquipeDAO equipeDAO = new EquipeDAOImpl();
    private JoueurDAO joueurDAO = new JoueurDAOImpl();

    @Override
    public List<Equipe> afficherEquipes() {
        return equipeDAO.findAll();

    }

    @Override
    public void ajouterEquipe(Equipe equipe) {

    }


    @Override
    public void supprimerEquipe(Long id) {
        equipeDAO.deleteById(id);
    }

    @Override
    public List<Joueur> afficherJoueurs(Equipe equipe) {
        return joueurDAO.findByEquipe(equipe);

    }

    @Override
    public void ajouterJoueur(Joueur joueur) {
        joueurDAO.save(joueur);
    }



    @Override
    public void supprimerJoueur(Long id) {
        joueurDAO.deleteById(id);
    }

    @Override
    public Joueur imprimerInformationsJoueur(Long id) {
        return joueurDAO.findById(id);

    }

    @Override
    public List<Equipe> searchEquipe(String nom) {
       return  equipeDAO.findByKeyword(nom);
    }
}

