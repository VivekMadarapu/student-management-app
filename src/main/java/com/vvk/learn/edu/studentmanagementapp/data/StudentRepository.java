package com.vvk.learn.edu.studentmanagementapp.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository  extends JpaRepository<Student, Long> {
    Iterable<Student> findStudentsByLastName(String name);
}
