package com.dbconnect.Dao;

import com.dbconnect.models.Equipe;

import java.util.List;

public interface EquipeDAO extends Dao<Equipe,Long> {
    List<Equipe> findByKeyword(String keyword);
}



