package com.kudelich.server.repository;

import com.kudelich.server.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository  extends JpaRepository<Course,Long> {
}