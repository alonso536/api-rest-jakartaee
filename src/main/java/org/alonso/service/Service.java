package org.alonso.service;

import jakarta.ejb.Local;

import java.util.List;
import java.util.Optional;

@Local
public interface Service<T> {
    List<T> index();
    void create(T t);
    Optional<T> show(Integer id);
    void edit(T t);
    void destroy(Integer id);
}
