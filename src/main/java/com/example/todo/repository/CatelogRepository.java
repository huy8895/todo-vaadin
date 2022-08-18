package com.example.todo.repository;

import com.example.todo.entity.Catelog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatelogRepository extends JpaRepository<Catelog, Long> {
}