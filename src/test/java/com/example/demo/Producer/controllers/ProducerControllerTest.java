package com.example.demo.Producer.controllers;

import com.example.demo.student.models.Student;
import com.example.demo.student.services.StudentService;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import static org.mockito.Mockito.when;



@RunWith(SpringRunner.class)
@WebMvcTest(ProducerController.class)

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
}

