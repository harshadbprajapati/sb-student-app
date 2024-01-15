package org.example.service;

import org.example.entity.Student;

import java.util.List;

public interface StudentService {
    public List<Student> findAll();
    public Student findById(int studentId);

    public Student save(Student student);

    public Student update(Student student);

    public void deleteById(int studentId);
}


