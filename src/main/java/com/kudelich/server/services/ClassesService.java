package com.kudelich.server.services;

import com.kudelich.server.entity.Classes;
import com.kudelich.server.entity.Faculty;
import com.kudelich.server.repository.ClassesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ClassesService implements ServiceT<Classes>{
    @Autowired
    private ClassesRepository repository;

    public List<Classes> getAll() {
        return repository.findAll();
    }

    public List<Classes> getById(long id) {
        return repository.findAllById(Collections.singleton(id));
    }

    public Classes save(Classes object) {
        return repository.saveAndFlush(object);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }
}
