package com.example.demo.student.services;


import com.example.demo.student.models.Student;
import com.example.demo.student.reopsitories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    public List<Student> getStudentsThierMarkesAbove50() {
        return repository.findAll()
                .stream()
                .filter(student -> student.getGpa() > 50)
                .collect(Collectors.toList());
    }

    public Student getHighestMark() {

        return repository.findAll()
                .stream()
                .max(Comparator.comparing(Student::getGpa))
                .orElseThrow(NoSuchElementException::new);
    }

    public Student getStudentById(int id) {
        return repository.findById(id).orElse(null);
    }

    public int getNumberOfStudents() {
        return (int) repository.count();
    }
}

