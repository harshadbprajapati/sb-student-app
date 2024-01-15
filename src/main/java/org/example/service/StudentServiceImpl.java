package org.example.service;

import org.example.entity.Student;
import org.example.exception.StudentNotFoundException;
import org.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{
    private StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student findById(int studentId) {
        Optional<Student> foundStudent = studentRepository.findById(studentId);

        Student student = null;

        if(foundStudent.isPresent()) {
            student = foundStudent.get();
        } else {
            throw new StudentNotFoundException("Student with id " + studentId + " not found");
        }
        return student;
    }
}





















