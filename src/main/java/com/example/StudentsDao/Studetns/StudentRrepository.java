package com.example.StudentsDao.Studetns;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRrepository extends JpaRepository<Student, Long> {

    Optional<Student> findStudentByEmail(String email);
}
