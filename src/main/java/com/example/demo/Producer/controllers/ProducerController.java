package com.example.demo.Producer.controllers;

import com.example.demo.student.Student;
import com.example.demo.student.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProducerController {
    @Autowired
    StudentService studentService;

    @GetMapping("/count")
    public int countNumberOfStudents() throws InterruptedException {
        Thread.sleep(2000);
        return studentService.getNumberOfStudents();
    }

    @GetMapping("/getGpa/{id}")
    public Student findStudentGpaById(@PathVariable int id) throws InterruptedException {
        Thread.sleep(2000);
        return studentService.getStudentById(id);
    }

    @GetMapping("/getGpa/max")
    public Student getHighestMark() throws InterruptedException {
        Thread.sleep(2000);
        return studentService.getHighestMark();
    }

    @GetMapping("/getGpa/above50")
    public List<Student> getStudentsThierMarkesAbove50() throws InterruptedException {
        return studentService.getStudentsThierMarkesAbove50();
    }
}