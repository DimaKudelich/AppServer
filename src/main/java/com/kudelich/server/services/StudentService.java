package com.kudelich.server.services;

import com.kudelich.server.entity.Student;
import com.kudelich.server.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class StudentService implements ServiceT<Student>{
    @Autowired
    private StudentRepository repository;

    public List<Student> getAll() {
        return repository.findAll();
    }

    public Student getById(long id) {
        return repository.findAllById(Collections.singleton(id)).get(0);
    }

    public Student save(Student object) {
        return repository.saveAndFlush(object);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }
}
