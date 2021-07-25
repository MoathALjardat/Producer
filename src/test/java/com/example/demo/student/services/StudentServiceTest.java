package com.example.demo.student.services;

import com.example.demo.student.models.Student;
import com.example.demo.student.models.StudentTest;
import com.example.demo.student.reopsitories.StudentRepository;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@RunWith(JUnitPlatform.class)
@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @InjectMocks
    StudentService studentService;
    @Mock
    StudentRepository repository;

    List<Student> students = new ArrayList<Student>();
    Student studentHaveId1 = new Student(1, "Moath", 20, 99.9, "CS");

    @BeforeEach
    public void setUp() throws Exception {
        // given
        Student studentHaveId2 = new Student(2, "Layth", 21, 89.8, "CS");
        Student studentHaveId3 = new Student(3, "Mojahed", 22, 89.8, "CE");
        Student studentHaveId4 = new Student(4, "Ramiz", 25, 39.7, "M");

        students.add(studentHaveId1);
        students.add(studentHaveId2);
        students.add(studentHaveId3);
        students.add(studentHaveId4);
    }


    @Test
    @DisplayName("Check if in Student Service method getStudentsThierMarkesAbove50Test is run successfully")
    @Tag("Service")
    public void getStudentsThierMarkesAbove50Test() {
        when(repository.findAll()).thenReturn(students);

        List<Student> AllStudentsToCheck = studentService.getStudentsThierMarkesAbove50();

        students = students
                .stream()
                .filter(student -> student.getGpa() > 50)
                .collect(Collectors.toList());

        List<Student> AllStudentsHaveGpaAbove50ToCheck = AllStudentsToCheck
                .stream()
                .filter(student -> student.getGpa() > 50)
                .collect(Collectors.toList());

        assertThat(AllStudentsHaveGpaAbove50ToCheck).isEqualTo(students);

        Logger logger = Logger.getLogger(StudentTest.class.getName());
        logger.info("Test Get Students Their Marks Above 50 Test Completed");
    }

    @Test
    @DisplayName("Check if in Student Service method getHighestMark is run successfully")
    @Tag("Service")
    public void getHighestMark() {


        when(repository.findAll()).thenReturn(students);

        Student studentHaveHighestGpaToCheck = studentService.getHighestMark();

        Student studentHaveHighestGpa = students
                .stream()
                .max(Comparator.comparing(Student::getGpa))
                .orElseThrow(NoSuchElementException::new);

        assertThat(studentHaveHighestGpaToCheck).isEqualTo(studentHaveHighestGpa);

        Logger logger = Logger.getLogger(StudentTest.class.getName());
        logger.info("Test Get Highest Mark Completed");
    }

    @Test
    @DisplayName("Check if in Student Service method getStudentById is run successfully")
    @Tag("Service")
    public void getStudentById() {
        when(repository.findById(1)).thenReturn(java.util.Optional.ofNullable(studentHaveId1));

        Student studentHaveId1ToCheck = repository.findById(1).orElse(null);

        assertThat(studentHaveId1ToCheck).isEqualTo(studentHaveId1);

        Logger logger = Logger.getLogger(StudentTest.class.getName());
        logger.info("Test Get Student By Id Completed");

    }

    @Test
    @DisplayName("Check if in Student Service method getNumberOfStudents is run successfully")
    @Tag("Service")
    public void getNumberOfStudents() {

        int numberOfStudents = students.size();

        when(repository.count()).thenReturn(4L);
        int numberOfStudentsToCheck = (int) repository.count();

        assertThat(numberOfStudentsToCheck).isEqualTo(numberOfStudents);

        Logger logger = Logger.getLogger(StudentTest.class.getName());
        logger.info("Test Get Number Of Students Completed");
    }
}