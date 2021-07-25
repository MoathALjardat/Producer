package com.example.demo.student.reopsitories;
import com.example.demo.student.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {

}
