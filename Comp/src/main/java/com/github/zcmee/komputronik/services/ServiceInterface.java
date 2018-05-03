package com.github.zcmee.komputronik.services;

import java.util.List;

public interface ServiceInterface<T> {
    List<T> findAll();
    T findById(Long id);
    T save(T obj);
}
