package com.example.studentmanagementsystem.repository;

import com.example.studentmanagementsystem.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student, UUID>{


}