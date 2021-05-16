package com.kudelich.server.controller;

import com.kudelich.server.entity.Classes;
import com.kudelich.server.services.ServiceT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/classes")
@RestController
public class ClassController {
    @Autowired
    private ServiceT<Classes> service;

    @RequestMapping(value = "/get",method = RequestMethod.GET)
    @ResponseBody
    public List<Classes> getAllClasses(){
        return service.getAll();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Classes getClassesById(@PathVariable("id") long id){
        return service.getById(id);
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public Classes saveClasses(Classes classes){
        return service.save(classes);
    }

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.POST)
    @ResponseBody
    public void deleteById(@PathVariable("id") long id){
        service.delete(id);
    }
}
