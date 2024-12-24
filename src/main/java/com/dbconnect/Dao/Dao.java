package com.dbconnect.Dao;

import com.dbconnect.models.Equipe;

import java.util.List;

public interface Dao<T,E> {
    List<T> findAll();
    T findById(Long id);
    void save(T entity);
    void deleteById(Long id);
    void update(T entity);
    List<T> findByKeyword(String keyword);
}

