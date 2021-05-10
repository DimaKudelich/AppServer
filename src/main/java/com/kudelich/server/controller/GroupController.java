package com.kudelich.server.controller;

import com.kudelich.server.entity.Group;
import com.kudelich.server.services.ServiceT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("groups")
@RestController
public class GroupController {
    @Autowired
    private ServiceT<Group>service;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public List<Group> getAllClasses() {
        return service.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public List<Group> getClassesById(@PathVariable("id") long id) {
        return service.getById(id);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Group saveClasses(Group object) {
        return service.save(object);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public void deleteById(@PathVariable("id") long id) {
        service.delete(id);
    }
}
