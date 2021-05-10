package com.kudelich.server.controller;


import com.kudelich.server.entity.Faculty;
import com.kudelich.server.services.ServiceT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/faculties")
@RestController
public class FacultyController {
    @Autowired
    private ServiceT<Faculty> service;

    @RequestMapping(value = "/get",method = RequestMethod.GET)
    @ResponseBody
    public List<Faculty> getAllClasses(){
        return service.getAll();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public List<Faculty> getClassesById(@PathVariable("id") long id){
        return service.getById(id);
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public Faculty saveClasses(Faculty object){
        return service.save(object);
    }

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.POST)
    @ResponseBody
    public void deleteById(@PathVariable("id") long id){
        service.delete(id);
    }
}
