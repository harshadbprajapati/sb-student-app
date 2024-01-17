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
    public List<Student> getAllStudents(){
        return studentService.findAll();
    }

    @GetMapping("/students/{studentId}")
    public Student getStudentById(@PathVariable  int studentId){
        return studentService.findById(studentId);
    }

    @PostMapping("/students")
    public Student createStudent(@RequestBody Student student){
        return studentService.save(student);
    }

    @PatchMapping("/students/{studentId}")
    public Student updateStudent(@RequestBody Student student,
                                 @PathVariable int studentId){
        return studentService.update(studentId, student);
    }

    @DeleteMapping("/students/{studentId}")
    public String deleteStudentById(@PathVariable int studentId){
        studentService.deleteById(studentId);
        return "Student with id " + studentId+" was deleted successfully";
    }
}


















