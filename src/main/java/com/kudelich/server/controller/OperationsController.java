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

    //get courses by faculty id
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

    //get groups by course id
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

    //get students by group id
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

    //get schedule by group id
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

        boolean flag = true;
        while(flag){
            flag = false;

            for (int i = 0; i < result.size(); i++) {
                for (int j = 0; j < result.size(); j++) {
                    if (result.get(i).getDayOfWeek()>result.get(j).getDayOfWeek()){
                        Classes tmp = result.get(i);
                        result.set(i,result.get(j));
                        result.set(j,tmp);
                        flag = true;
                    }
                }
            }
        }

        return result;
    }

    //get all classes by user id
    @RequestMapping(value = "/faculties/courses/groups/student/schedule/{id}",method = RequestMethod.GET)
    @ResponseBody
    public List<Classes>getAllClassesByUserId(@PathVariable("id") long id){
        Student student = studentsService.getById(id);
        long groupId = student.getGroupId();

        return getScheduleByGroupId(groupId);
    }

    //get student id by login and password
    @RequestMapping(value = "/students/{login}/{password}",method = RequestMethod.GET)
    @ResponseBody
    public long getStudentIdByLoginAndPassword(@PathVariable("login") String login,@PathVariable("password") String password){
        List<Student> students = studentsService.getAll();

        for (Student student:students) {
            if (student.getLogin().equals(login)&&student.getPassword().equals(password)){
                return student.getId();
            }
        }

        return 0;
    }

    @RequestMapping(value = "/group/info/{id}",method = RequestMethod.GET)
    @ResponseBody
    public int getGroupNumberByGroupId(@PathVariable("id") long id){
        Group group = groupsService.getById(id);
        return group.getGroupNumber();
    }

    @RequestMapping(value = "/course/group/info/{id}",method = RequestMethod.GET)
    @ResponseBody
    public long getCourseNumberByGroupId(@PathVariable("id") long id){
        Group group = groupsService.getById(id);
        long courseId = group.getCourseId();
        Course course = coursesService.getById(courseId);
        return course.getCourseNumber();
    }

    @RequestMapping(value = "/faculty/course/group/info/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String getFacultyByGroupId(@PathVariable("id") long id){
        Group group = groupsService.getById(id);
        long courseId = group.getCourseId();
        Course course = coursesService.getById(courseId);
        long facultyId = course.getFacultyId();
        Faculty faculty = facultiesService.getById(facultyId);
        return faculty.getName();
    }

    @RequestMapping(value = "/student/info/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String[] getStudentInfoByStudentId(@PathVariable("id") long id){
        Student student = studentsService.getById(id);
        String name = student.getName();

        Group group = groupsService.getById(student.getGroupId());
        String group_number = Integer.toString(group.getGroupNumber());

        Course course = coursesService.getById(group.getCourseId());
        String course_number =Long.toString( course.getCourseNumber());

        Faculty faculty = facultiesService.getById(course.getFacultyId());
        String faculty_name = faculty.getName();

        return new String[]{name,group_number,course_number,faculty_name};
    }
}
