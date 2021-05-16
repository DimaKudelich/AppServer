package com.kudelich.server.services;

import com.kudelich.server.entity.Course;
import com.kudelich.server.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CourseService implements ServiceT<Course>{
    @Autowired
    private CourseRepository repository;

    public List<Course> getAll() {
        return repository.findAll();
    }

    public Course getById(long id) {
        return repository.findAllById(Collections.singleton(id)).get(0);
    }

    public Course save(Course object) {
        return repository.saveAndFlush(object);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }
}
