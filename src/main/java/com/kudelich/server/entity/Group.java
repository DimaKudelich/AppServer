package com.kudelich.server.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment",strategy = "increment")
    private long id;

    @Column(name = "course_id",nullable = false)
    private long courseId;

    @Column(name = "curator",nullable = false)
    private String curator;

    @Column(name= "group_number", nullable = false)
    private int groupNumber;

    public int getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    public Group(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public String getCurator() {
        return curator;
    }

    public void setCurator(String curator) {
        this.curator = curator;
    }
}
