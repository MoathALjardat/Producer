package com.example.demo.Producer.controllers;

import com.example.demo.student.models.StudentTest;
import org.junit.jupiter.api.*;
import org.junit.platform.runner.JUnitPlatform;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import com.example.demo.student.models.Student;
import com.example.demo.student.services.StudentService;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RunWith(JUnitPlatform.class)
@ExtendWith(MockitoExtension.class)
public class ProducerControllerTest {

    @InjectMocks
    ProducerController producerController;
    @Mock
    StudentService studentService;


    @AfterEach
    public void tearDown() throws Exception {
        Logger logger = Logger.getLogger(StudentTest.class.getName());
        logger.info("Test Completed");
    }


    @Test
    @DisplayName("Check if in producer controller method getHighestMark is run successfully")
    @Tag("controller")
    public void getHighestMarkTest() throws Exception {

        // given
        Student studentHaveTheHighestMark = new Student(1, "Moath", 20, 99.9, "CS");

        when(studentService.getHighestMark()).thenReturn(studentHaveTheHighestMark);

        // when
        Student studentToCheck = producerController.getHighestMark();

        // then
        assertThat(studentToCheck.getId()).isEqualTo(studentHaveTheHighestMark.getId());
        assertThat(studentToCheck.getGpa()).isEqualTo(studentHaveTheHighestMark.getGpa());
        assertThat(studentToCheck.getMajor()).isEqualTo(studentHaveTheHighestMark.getMajor());
        assertThat(studentToCheck.getName()).isEqualTo(studentHaveTheHighestMark.getName());
        assertThat(studentToCheck.getAge()).isEqualTo(studentHaveTheHighestMark.getAge());

    }

    @Test
    @DisplayName("Check if in producer controller method countNumberOfStudentsTest is run successfully")
    @Tag("controller")
    public void countNumberOfStudentsTest() throws Exception {

        Integer numberOfStudents = new Integer(5);

        when(studentService.getNumberOfStudents()).thenReturn(5);

        Integer numberOfStudentsToCheck = producerController.countNumberOfStudents();

        assertThat(numberOfStudentsToCheck).isEqualTo(numberOfStudents);
    }


    @Test
    @DisplayName("Check if in producer controller method findStudentGpaByIdTest is run successfully")
    @Tag("controller")
    public void findStudentGpaByIdTest() throws InterruptedException {

        // given
        Student studentHaveId1 = new Student(1, "Moath", 20, 99.9, "CS");

        when(studentService.getStudentById(anyInt())).thenReturn(studentHaveId1);

        // when
        double gpaHaveId1ForCheck = producerController.findStudentGpaById(anyInt()).getGpa();

        // then
        assertThat(gpaHaveId1ForCheck).isEqualTo(studentHaveId1.getGpa());

    }

    @Test
    @DisplayName("Check if in producer controller method getStudentsThierMarkesAbove50Test is run successfully")
    @Tag("controller")
    public void getStudentsThierMarkesAbove50Test() throws InterruptedException {
        // given
        Student studentHaveId1 = new Student(1, "Moath", 20, 99.9, "CS");
        Student studentHaveId2 = new Student(2, "Layth", 21, 89.8, "CS");
        Student studentHaveId3 = new Student(3, "Mojahed", 22, 89.8, "CE");

        List<Student> studentsHavGpaAbove50 = new ArrayList<Student>();

        studentsHavGpaAbove50.add(studentHaveId1);
        studentsHavGpaAbove50.add(studentHaveId2);
        studentsHavGpaAbove50.add(studentHaveId3);


        when(studentService.getStudentsThierMarkesAbove50()).thenReturn(studentsHavGpaAbove50);

        // when
        List<Student> studentsHavGpaAbove50ToCheck = producerController.getStudentsThierMarkesAbove50();

        // then
        assertThat(studentsHavGpaAbove50ToCheck).isEqualTo(studentsHavGpaAbove50);
    }
}