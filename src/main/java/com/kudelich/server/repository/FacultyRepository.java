package com.kudelich.server.repository;

import com.kudelich.server.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepository extends JpaRepository<Faculty,Long> {
}
