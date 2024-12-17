package com.dbconnect.Dao;


import com.dbconnect.models.Equipe;
import com.dbconnect.models.Joueur;

import java.util.List;

public interface JoueurDAO extends Dao<Joueur,Long> {
    List<Joueur> findByEquipe(Equipe equipe);
}

