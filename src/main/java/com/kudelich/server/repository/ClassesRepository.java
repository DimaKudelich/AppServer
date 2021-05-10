package com.kudelich.server.repository;

import com.kudelich.server.entity.Classes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassesRepository extends JpaRepository<Classes,Long> {
}
