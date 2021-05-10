package com.kudelich.server.controller;

import com.kudelich.server.entity.*;
import com.kudelich.server.services.ServiceT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("")
@RestController
public class OperationsController {
    @Autowired
    private ServiceT<Classes> classesService;

    @Autowired
    private ServiceT<Course> coursesService;

    @Autowired
    private ServiceT<Faculty> facultiesService;

    @Autowired
    private ServiceT<Group> groupsService;

    @Autowired
    private ServiceT<Student> studentsService;

    @RequestMapping(value = "/faculties/courses/{id}",method = RequestMethod.GET)
    @ResponseBody
    public List<Course> getCoursesByFacultyId(@PathVariable("id") long id){
        List<Course> allCourses = coursesService.getAll();

        List<Course>result = new ArrayList<Course>();

        for (int i = 0; i < allCourses.size(); i++) {
            if (allCourses.get(i).getFacultyId() == id){
                result.add(allCourses.get(i));
            }
        }

        return result;
    }

    @RequestMapping(value = "/faculties/courses/groups/{id_course}",method = RequestMethod.GET)
    @ResponseBody
    public List<Group> getGroupsByCourseId(@PathVariable("id_course") long courseId){
        List<Group>groups = groupsService.getAll();
        List<Group>result = new ArrayList<>();

        for (Group group:groups) {
            if (group.getCourseId() == courseId){
                result.add(group);
            }
        }

        return result;
    }

    @RequestMapping(value = "/faculties/courses/groups/students/{id_group}",method = RequestMethod.GET)
    @ResponseBody
    public List<Student> getStudentsByGroupId(@PathVariable("id_group") long groupId){
        List<Student>students = studentsService.getAll();
        List<Student>result = new ArrayList<>();

        for (Student student:students) {
            if (student.getGroupId()==groupId){
                result.add(student);
            }
        }

        return result;
    }

    @RequestMapping(value = "/faculties/courses/groups/schedule/{id_group}",method = RequestMethod.GET)
    @ResponseBody
    public List<Classes> getScheduleByGroupId(@PathVariable("id_group") long groupId){
        List<Classes>classes = classesService.getAll();
        List<Classes>result = new ArrayList<>();

        for (Classes clas:classes) {
            if (clas.getGroupId()==groupId){
                result.add(clas);
            }
        }

        return result;
    }
}
