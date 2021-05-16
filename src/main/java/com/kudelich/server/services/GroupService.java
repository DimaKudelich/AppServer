package com.kudelich.server.services;

import com.kudelich.server.entity.Group;
import com.kudelich.server.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class GroupService implements ServiceT<Group> {
    @Autowired
    private GroupRepository repository;

    public List<Group> getAll() {
        return repository.findAll();
    }

    public Group getById(long id) {
        return repository.findAllById(Collections.singleton(id)).get(0);
    }

    public Group save(Group object) {
        return repository.saveAndFlush(object);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }
}
