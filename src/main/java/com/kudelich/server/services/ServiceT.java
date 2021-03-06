package com.kudelich.server.services;

import java.util.List;

public interface ServiceT<T>{
    List<T> getAll();
    T getById(long id);
    T save(T object);
    void delete(long id);
}
