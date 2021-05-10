package com.kudelich.server.controller;

import com.kudelich.server.entity.Student;
import com.kudelich.server.services.ServiceT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("students")
@RestController
public class StudentController {
    @Autowired
    private ServiceT<Student> service;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public List<Student> getAll() {
        return service.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public List<Student> getClassesById(@PathVariable("id") long id) {
        return service.getById(id);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Student saveClasses(Student object) {
        return service.save(object);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public void deleteById(@PathVariable("id") long id) {
        service.delete(id);
    }
}
