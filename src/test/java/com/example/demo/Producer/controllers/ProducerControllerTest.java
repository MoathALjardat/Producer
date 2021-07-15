package com.example.demo.Producer.controllers;

import org.junit.platform.runner.JUnitPlatform;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import com.example.demo.student.Student;
import com.example.demo.student.services.StudentService;

import java.util.ArrayList;
import java.util.List;

@RunWith(JUnitPlatform.class)
@ExtendWith(MockitoExtension.class)
public class ProducerControllerTest {

    @InjectMocks
    ProducerController producerController;
    @Mock
    StudentService studentService;

    @Test
    public void getHighestMarkTest() throws Exception {

        // given
        Student student = new Student(1, "Moath", 20, 99.9, "CS");

        when(studentService.getHighestMark()).thenReturn(student);

        // when
        Student studentToCheck = producerController.getHighestMark();

        // then
        assertThat(studentToCheck.getId()).isEqualTo(student.getId());
        assertThat(studentToCheck.getGpa()).isEqualTo(student.getGpa());
        assertThat(studentToCheck.getMajor()).isEqualTo(student.getMajor());
        assertThat(studentToCheck.getName()).isEqualTo(student.getName());
        assertThat(studentToCheck.getAge()).isEqualTo(student.getAge());

    }

    @Test
    public void countNumberOfStudentsTest() throws Exception {

        Integer count = new Integer(5);

        when(studentService.getNumberOfStudents()).thenReturn(5);

        Integer countForCheck = producerController.countNumberOfStudents();

        assertThat(countForCheck).isEqualTo(count);
    }


    @Test
    public void findStudentGpaByIdTest() throws InterruptedException {

        // given
        Student student = new Student(1, "Moath", 20, 99.9, "CS");

        when(studentService.getStudentById(anyInt())).thenReturn(student);

        // when
        double gpaByIdForCheck = producerController.findStudentGpaById(anyInt()).getGpa();

        // then
        assertThat(gpaByIdForCheck).isEqualTo(student.getGpa());

    }

    @Test
    public void getStudentsThierMarkesAbove50Test() throws InterruptedException {
        // given
        Student student1 = new Student(1, "Moath", 20, 99.9, "CS");
        Student student2 = new Student(2, "Layth", 21, 89.8, "CS");
        Student student3 = new Student(3, "Mojahed", 22, 89.8, "CE");

        List<Student> students = new ArrayList<Student>();

        students.add(student1);
        students.add(student2);
        students.add(student3);


        when(studentService.getStudentsThierMarkesAbove50()).thenReturn(students);

        // when
        List<Student> studentsToCheck = producerController.getStudentsThierMarkesAbove50();

        // then
        assertThat(studentsToCheck).isEqualTo(students);
    }
}