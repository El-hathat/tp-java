package com.dbconnect.Dao;

import java.util.List;

public interface Dao<T,E> {
    List<T> findAll();
    T findById(Long id);
    void save(T entity);
    void deleteById(Long id);
    void update(T entity);
}

