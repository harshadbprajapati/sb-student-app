package org.example.service;

import jakarta.transaction.Transactional;
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

    @Override
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

    @Transactional
    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Transactional
    @Override
    public Student update(Student updateStudent) {
        Student matchingStudent = findById(updateStudent.getId());
        if (matchingStudent!=null) {
            if(updateStudent.getName()!=null){
                matchingStudent.setName(updateStudent.getName());
            }
            if(updateStudent.getCity()!=null){
                matchingStudent.setCity(updateStudent.getCity());
            }
            if(updateStudent.getEmail()!=null){
                matchingStudent.setEmail(updateStudent.getEmail());
            }
            if(updateStudent.getGender()!=null){
                matchingStudent.setGender(updateStudent.getGender());
            }
            if(updateStudent.getSemester() != 0){
                matchingStudent.setSemester(updateStudent.getSemester());
            }
            if(updateStudent.getProgram()!=null){
                matchingStudent.setProgram(updateStudent.getProgram());
            }
        }
        if(matchingStudent==null){
            throw new StudentNotFoundException("Student with id " + updateStudent.getId() + " not found");
        }
        return studentRepository.save(matchingStudent);
    }

    @Transactional
    @Override
    public void deleteById(int studentId) {
        Student matchingStudent = findById(studentId);
        studentRepository.deleteById(studentId);
    }
}





















