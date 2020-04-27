package com.jjdev.persistence.dao;

import java.util.List;
import java.util.Optional;

public interface BaseDao<T> {

    void create(T entity);

    List<T> readAll();

    Optional<T> readById(Long id);

    void update(T entity, Long id);

    void delete(Long id);
}
