package org.example.restcontroller;

import org.example.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {
    @GetMapping("/students")
    List<Student> getAllStudents(){
        List<Student> allStudents = new ArrayList<>();
        allStudents.add(new Student(1, "Tom",
                "tom@gmail.com", 6,
                "Ahmedabad", "Male", "B.Tech-IT"));
        allStudents.add(new Student(2, "Will",
                "will@gmail.com", 6,
                "Vadodara", "Male", "B.Tech-IT"));
        allStudents.add(new Student(3, "Jenifer",
                "jenifer@gmail.com", 6,
                "Vadodara", "Female", "B.Tech-IT"));
        return allStudents;
    }
}

