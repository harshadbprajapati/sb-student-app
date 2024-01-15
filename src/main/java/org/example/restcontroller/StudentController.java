package org.example.restcontroller;

import org.example.entity.Student;
import org.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {
    StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    List<Student> getAllStudents(){
        return studentService.findAll();
    }

    @GetMapping("/students/{studentId}")
    Student getStudentById(@PathVariable  int studentId){
        return studentService.findById(studentId);
    }

}


















