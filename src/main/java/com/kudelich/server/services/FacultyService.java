package com.kudelich.server.services;

import com.kudelich.server.entity.Faculty;
import com.kudelich.server.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class FacultyService implements ServiceT<Faculty> {
    @Autowired
    private FacultyRepository repository;

    public List<Faculty> getAll() {
        return repository.findAll();
    }

    public List<Faculty> getById(long id) {
        return repository.findAllById(Collections.singleton(id));
    }

    public Faculty save(Faculty object) {
        return repository.saveAndFlush(object);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }
}
