package org.alonso.repository;

import java.util.List;

public interface RepositoryDao<T> {
    List<T> index();
    void create(T t);
    T show(Integer id);
    void edit(T t);
    void destroy(Integer id);
}
